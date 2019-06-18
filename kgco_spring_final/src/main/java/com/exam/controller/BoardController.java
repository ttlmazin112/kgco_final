package com.exam.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.exam.domain.AttachVo;
import com.exam.domain.BoardVo;
import com.exam.domain.ReplyVo;
import com.exam.service.AttachService;
import com.exam.service.BoardService;
import com.exam.service.ReplyService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j
@Controller
@RequestMapping("/board/*")
public class BoardController {

    @Setter(onMethod_ = @Autowired)
    private BoardService boardService;
    
    @Setter(onMethod_ = @Autowired)
    private AttachService attachService;
    
    
    @Setter(onMethod_ = @Autowired)
    private ReplyService replyService;
    
    

    
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/boardMain")
    public String boardMain(
            @RequestParam(defaultValue = "1") int pageIndex, 
            @RequestParam(required=false) String search, Model model) {
        

        // 글 목록가져오기 getBoard()
        
        List<AttachVo> attachList=attachService.getAllattach();
        

        return "/board/boardList";
    }
    
    
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/boardList")
    public String showboardList(
            @RequestParam(defaultValue = "1") int pageIndex, 
            @RequestParam(required=false) String search,  @RequestParam(required=false)String searchSelect, Model model) {
        
        int amount=5;    
        int startRow=(pageIndex-1)*amount;
        // 글 목록가져오기 getBoard()
//        List<BoardVo> boardList=boardService.getAllBoards(startRow, amount, search);
        List<BoardVo> boardList=boardService.getAllBoards(startRow, amount, search, searchSelect);
        
        List<BoardVo> mostReadList=boardService.getMostReadCount();
        for(BoardVo b:boardList) {
            System.out.println(b);
        }
        
        List<AttachVo> attachList=attachService.getAllattach();
    
        
        // =========================================
        //  페이지 블록 구하기 작업
        // =========================================
        int allRowCount=0; // 전체 행 갯수
        
        allRowCount=boardService.getBoardCount(search,searchSelect);
        
        
        int maxPage=allRowCount/amount + (allRowCount%amount ==0 ? 0: 1);
        // 1페이지 ~ maxPage 페이지까지 존재함.
        // -> 페이지 블록단위로 끊어줌
        
        // 시작페이지번호(1)                   글페이지번호(10)
        // 1 2 3 4 5 6 7 8 9 10                 --[블록구성 10개]
        
        // 시작페이지번호 (11)                 글페이지번호(20)
        // 11 12 13 14 15 16 17 18 19 20        --[블록구성 10개]
        
        
        // 한 페이지블록을 구성하는 페이지갯수
        int pageBlockSize=5;
        
        // 시작페이지 번호
        int startPage=((pageIndex/pageBlockSize) - (pageIndex%pageBlockSize==0 ? 1: 0)) * pageBlockSize + 1;
        
        // 끝페이지번호 구하기
        int endPage=startPage+pageBlockSize - 1;
        if(endPage>maxPage) {
            endPage=maxPage;
        }
        
        Map<String, Integer> pageInfoMap =new HashMap<>();
        pageInfoMap.put("startPage", startPage);
        pageInfoMap.put("endPage", endPage);
        pageInfoMap.put("pageBlockSize",pageBlockSize);
        pageInfoMap.put("maxPage",maxPage);
        pageInfoMap.put("allRowCount",allRowCount);
        pageInfoMap.put("pageIndex", pageIndex); //사용자가 요청한 페이지번호
        
        model.addAttribute("search", search);
        model.addAttribute("searchSelect", searchSelect);
        model.addAttribute("pageInfoMap", pageInfoMap);
        model.addAttribute("boardList", boardList);
        model.addAttribute("attachList", attachList);
        model.addAttribute("mostReadList", mostReadList);
        return "/board/boardList";
    }
//    
//    @GetMapping("/boardDetail")
//    public String showDetail(@RequestParam int boardId,@RequestParam int pageIndex,Model model  ) {
//    	
//    	boardService.updateReadcount(boardId);
//        BoardVo boardDetail=boardService.getBoardbyBoardId(boardId);    
//        List<AttachVo> attachList=attachService.findByboardId(boardId);
//        List<ReplyVo> replyList=replyService.getAllRepliesByBoardId(boardId);
//        
//        model.addAttribute("boardDetail", boardDetail);
//        model.addAttribute("attachList", attachList);
//        model.addAttribute("replyList", replyList);
//    	
//    	return "/board/boardDetail";
//    }
//    
//    
//    @PreAuthorize("isAuthenticated() and (   ( principal.employee.level >= #board.auth )   )")
//    @PostMapping("/boardDetail")
//    public String showDetail(
//            @RequestParam int pageIndex,  
//            BoardVo board,
//            Model model) {
//    	
//    	int boardId=board.getBoardId();
//    	boardService.updateReadcount(boardId);
//        BoardVo boardDetail=boardService.getBoardbyBoardId(boardId);    
//        List<AttachVo> attachList=attachService.findByboardId(boardId);
//        List<ReplyVo> replyList=replyService.getAllRepliesByBoardId(boardId);
//        
//        model.addAttribute("boardDetail", boardDetail);
//        model.addAttribute("attachList", attachList);
//        model.addAttribute("replyList", replyList);
//        return "/board/boardDetail?pageIndex="+pageIndex+"&boardId="+boardId;
//    }
//    

    
    
//    
//    public String showDocuments(
//    		@RequestParam(defaultValue = "1") int pageIndex, 
//            @RequestParam(required=false) String search, Model model) {
//    	
//    	List<AttachVo>attachList=attachService.getAllattach();
//    	List<BoardVo> boardList=new ArrayList<BoardVo>();
//    	
//    	for(AttachVo attachVo:attachList) {
//    		BoardVo board=boardService.getBoardbyBoardId(attachVo.getBId());
//    		boardList.add(board);
//    	}
//    	model.addAttribute("boardList", boardList);
//    	return "";
//    }
//    
    
    @PreAuthorize("isAuthenticated() and (   ( principal.employee.level >= #auth )   )")
    @RequestMapping(value = "/boardDetail")
//    @GetMapping("/boardDetail")
    public String showDetail(@RequestParam int boardId, 
            @RequestParam int pageIndex,  
            @RequestParam int auth,  
            Model model) {
        
    	boardService.updateReadcount(boardId);
        BoardVo board=boardService.getBoardbyBoardId(boardId);    
        List<AttachVo> attachList=attachService.findByboardId(boardId);
        List<BoardVo> mostReadList=boardService.getMostReadCount();
//        List<ReplyVo> replyList=replyService.getAllRepliesByBoardId(boardId);
        
        model.addAttribute("boardDetail", board);
        model.addAttribute("attachList", attachList);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("mostReadList", mostReadList);
        model.addAttribute("auth", auth);

      
        return "/board/boardDetail";
    }

    
    
    @ResponseBody
    @PostMapping("/boardDetailReply")
    public void insertReply(
    		ReplyVo replyVo,
    		@RequestParam int boardId, 
    		@RequestParam int pageIndex
    		){
    	replyVo.setReplyGroup(boardId);
    	replyService.insertReply(replyVo);

    }
    
    
    
    @ResponseBody
    @PostMapping("/boardReplySelect")
    public  List<ReplyVo> showReply(
    		@RequestParam int boardId
    		){
    
    	 List<ReplyVo> replyList=replyService.getAllRepliesByBoardId(boardId);
    	 
//    	 for(ReplyVo replyVo : replyList) System.out.println("111111111111111" + replyVo.toString());
    	return replyList;

    }
    
    
    
    @ResponseBody
    @PostMapping("/boardReplyCount")
    public int showReplyCount(
            @RequestParam int boardId
            ){
    
         
         int replyCount=replyService.getReplycount(boardId);
       
        return replyCount;

    }
    
    
    
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/boardWrite")
    public String boardWrite() {
        return "/board/writeForm";
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/boardWrite")
    public String boardWrite(BoardVo boardVo, 
    		MultipartFile[] files, HttpServletRequest request) throws Exception{

    	int boardNum=boardService.getBoardId();
    	boardVo.setBoardId(boardNum);
    	
    	
        // 1 upload 폴더 만들고 물리적 경로
        ServletContext application=request.getServletContext();
        String realPath=application.getRealPath("/upload");
        // upload폴더의 물리적 경로
//        String realPath="C:\\upload";
        System.out.println("realPath:"+realPath); 
        
        //폴더 동적 생성하기  /upload/2019/05/17
        File uploadPath=new File(realPath, getFolder());
        log.info("uploadPath:"+uploadPath);
        
        if(!uploadPath.exists()) {
            uploadPath.mkdirs(); //업로드할 폴더 생성
        }
        
        List<AttachVo> attachList= new ArrayList<AttachVo>();
        
        
        for(MultipartFile multipartFile:files) {
            log.info("업로드 파일명:"+multipartFile.getOriginalFilename());
            log.info("업로드 파일크기: "+multipartFile.getSize());
            
            //할일 파일사이즈 경고!
            if(multipartFile.getSize() == 0) {
                continue;
            }
      
   
            String uploadFileName=multipartFile.getOriginalFilename();
            UUID uuid=UUID.randomUUID();
            uploadFileName=uuid.toString()+"_"+uploadFileName;
            log.info("업로드할 최종 파일명"+uploadFileName);
            
            File saveFile=new File(uploadPath, uploadFileName);

                multipartFile.transferTo(saveFile);

            AttachVo attachVo=new AttachVo();
            attachVo.setBid(boardNum);
            attachVo.setUuid(uuid.toString());
            attachVo.setUploadpath(getFolder());
            attachVo.setFilename(multipartFile.getOriginalFilename());
            
            if(checkDocumentType(saveFile)=="D") {
                attachVo.setFiletype("D");
            }else if(checkDocumentType(saveFile)=="I") {
                attachVo.setFiletype("I");
            }else if(checkDocumentType(saveFile)=="C") {
                attachVo.setFiletype("C");
            }
            else {
                attachVo.setFiletype("O");
            }
            
            attachList.add(attachVo);
        }   //for

        boardService.insertBoardAndAttach(boardVo, attachList);
        return "redirect:/board/boardList";
    }
    
    
    //할일 util 패키지로 옮기기
    private String getFolder() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date();
        return sdf.format(date);
 //       return str.replace("-", "/"); // File.separator
    }
    
    private String checkDocumentType(File file) {
        String isDocType="";
        try {
            String contentType=Files.probeContentType(file.toPath());
            log.info("contentType:"+contentType);
            if(contentType !=null) {
                    if(contentType.startsWith("application")) {
                        isDocType="D";
                    }else if(contentType.startsWith("image")) {
                        isDocType="I";
                    }else if(contentType.endsWith("x-zip-compressed")) {
                        isDocType="C"; //compressed file:압축파일
                    }
                
                
                //jar->null
                //application/pdf 피뎊
                //application/vnd.ms-powerpoint 파포
                //application/msword doc
                //application/vnd.openxmlformats-officedocument.spreadsheetml.sheet 엑셀
                // application/x-zip-compressed zip
            }else {
                isDocType="O";
            }

        } catch (IOException e) {
            log.info(e.getMessage());
        }
        
        return isDocType;
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/boardModify")
    public String boardModify(int boardId, Model model) {
        BoardVo board=boardService.getBoardbyBoardId(boardId);
        List<AttachVo> attachList=attachService.findByboardId(boardId);
        model.addAttribute("board", board);
        model.addAttribute("attachList", attachList);
        return "/board/modifyForm";
    }
    
    
    @PreAuthorize("isAuthenticated() and ((principal.employee.eid == #boardVo.eid))")
    @PostMapping("/boardModify")			
    public ResponseEntity<String> boardModify(
    		BoardVo boardVo, 
    		String[] delFiles, MultipartFile[] newFiles,
    		@RequestParam String pageIndex, HttpServletRequest request) {
    	
    	
        boolean isModified=boardService.modifyBoard(boardVo);
        if(isModified==false) {
            HttpHeaders headers=new HttpHeaders();
            headers.add("Content-Type", "text/html; charset=utf-8");
            StringBuilder sb=new StringBuilder();
            sb.append("<script>");
            sb.append("alert('비밀번호 오류');");
            sb.append("history.back();");
            sb.append("</script>");
            return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
        }else {
        	
        	ServletContext application = request.getServletContext();
            String realPath = application.getRealPath("/upload");
        	
            if (delFiles != null) {
                for (int i=0; i<delFiles.length; i++) {
                    String folder = delFiles[i].substring(0, 10); // 2019/05/21
                    String uuidAndFilename = delFiles[i].substring(11); // 2019/05/21/ 제외
                    
                    int index = uuidAndFilename.indexOf("_");
                    String uuid = uuidAndFilename.substring(0, index);
                    String filename = uuidAndFilename.substring(index + 1);
                    log.info("uuid : " + uuid);
                    log.info("filename : " + filename);
                    
                    
                    // 첨부파일 삭제항목 삭제하기
                    File deleteFile = new File(realPath + "/" + delFiles[i]);
                    
                    if (deleteFile.exists()) deleteFile.delete();
 
                    
                    // attach 테이블에서  행 삭제
                    attachService.deleteAttach(uuid);
                } // for
            } // if
            
            
            // 폴더 동적 생성하기   /upload/2019/05/17
            File uploadPath = new File(realPath, getFolder());
            log.info("uploadPath : " + uploadPath);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs(); // 업로드할 폴더 생성
            }

            // 새로 추가된 첨부파일 정보를 담을 리스트 준비
            List<AttachVo> newAttachList = new ArrayList<>();
            // 추가된 첨부파일 업로드하기
            for (MultipartFile multipartFile : newFiles) {
                if (multipartFile.isEmpty()) {
                    continue;
                }
                
                String uploadFileName = multipartFile.getOriginalFilename();
                UUID uuid = UUID.randomUUID();
                uploadFileName = uuid.toString() + "_" + uploadFileName;
                log.info("업로드할 최종 파일명: " + uploadFileName);
                
                File saveFile = new File(uploadPath, uploadFileName);
                try {
                    multipartFile.transferTo(saveFile); // 업로드 수행
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                AttachVo attachVO = new AttachVo();
                attachVO.setBid(boardVo.getBoardId());; // 게시글번호 저장
                attachVO.setUuid(uuid.toString());  // 첨부파일 UUID
                attachVO.setUploadpath(getFolder());
                attachVO.setFilename(multipartFile.getOriginalFilename());
                
                
                if(checkDocumentType(saveFile)=="D") {
                    attachVO.setFiletype("D");
                }else if(checkDocumentType(saveFile)=="I") {
                    attachVO.setFiletype("I");
                }else if(checkDocumentType(saveFile)=="C") {
                    attachVO.setFiletype("C");
                }
                else {
                    attachVO.setFiletype("O");
                }
                
                newAttachList.add(attachVO);
            } // for
            
            for (AttachVo attachVO : newAttachList) {
                log.info("attachVO : " + attachVO);
            }
            
            
            // 추가된 첨부파일 테이블 insert하기
            if (newAttachList.size() > 0) {
                attachService.insertAttach(newAttachList);
            }

            HttpHeaders headers=new HttpHeaders();
            headers.add("Location", "/board/boardList?pageIndex="+pageIndex);
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        }
        
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/boardDelete")
    public String boardDelete(@RequestParam int boardId, Model model) {
    	BoardVo board=boardService.getBoardbyBoardId(boardId);
    	model.addAttribute("board", board);
        return "/board/deletePasswordCheck";
    }
    
    
    @PreAuthorize("isAuthenticated() and ((principal.employee.eid == #bWriter))")
    @PostMapping("/boardDelete")
    public ResponseEntity<String> boardDelete(int boardId, String pass, String bWriter, HttpServletRequest request) {
    	BoardVo board=boardService.getBoardbyBoardId(boardId);
//    	boolean isDeleted=boardService.deleteBoard(boardId, pass);
    	if(!pass.equals(board.getPassword())) {
    		HttpHeaders headers=new HttpHeaders();
            headers.add("Content-Type", "text/html; charset=utf-8");
            StringBuilder sb=new StringBuilder();
            sb.append("<script>");
            sb.append("alert('비밀번호 오류');");
            sb.append("history.back();");
            sb.append("</script>");
            return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
    	}else {
    		List<AttachVo> attachList=attachService.findByboardId(boardId);
    		
    		ServletContext application=request.getServletContext();
            String realPath=application.getRealPath("/upload");
            
            for(AttachVo attach:attachList) {
                String path=realPath+"/"+attach.getUploadpath();
                String filename=attach.getUuid()+"_"+attach.getFilename();
           
                File deleteFile=new File(path, filename);
                if(deleteFile.exists()) deleteFile.delete();
                
                }
                    //원본파일 삭제하기
        
    		
    	boardService.deleteBoardAndAttach(boardId);
    	
    		 HttpHeaders headers=new HttpHeaders();
             headers.add("Location", "/board/boardList");
    		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    	}
    	
    }
    
    
    
    
    @GetMapping(value="/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request, @RequestHeader("User-Agent") String userAgent) {
        log.info("download file:"+fileName);
        
        ServletContext application=request.getServletContext();
        String realPath=application.getRealPath("/upload");
        
        Resource resource=new FileSystemResource(realPath+"/"+fileName);
        
        if(!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("resource"+resource);
        
        String resourceName=resource.getFilename();
        String resourceOriginalName=resourceName.substring(resourceName.indexOf("_")+1);
        HttpHeaders headers=new HttpHeaders();
        try {
            
            String downloadName= null;
            if(userAgent.contains("Trident")) {
                downloadName=URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\", " ");
            }else if(userAgent.contains("Edge")){
                downloadName=URLEncoder.encode(resourceOriginalName, "UTF-8");
            }else {
                log.info("chrome");
                downloadName=new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
                
            }
            
            headers.add("Content-Disposition", "attachment; fileName="+downloadName);
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        
        
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
    
    
    
    
    
    
}
