package com.exam.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.domain.DocumentVO;
import com.exam.domain.EmployeeStatusVO;
import com.exam.domain.EmployeeWorkNoteVO;
import com.exam.domain.OpenApiRealTimeFlightDto;
import com.exam.service.DocumentService;
import com.exam.service.EmployeeService;
import com.exam.service.ScheduleService;
import com.exam.util.AttendDao;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller // servlet-context�쓽 �쁺�뿭
@RequestMapping("/confirm/*")
public class ConfirmController {

	@Setter(onMethod_ = @Autowired)
	private DocumentService documentService;
	@Setter(onMethod_ = @Autowired)
	private ScheduleService scheduleService;
	@Setter(onMethod_ = @Autowired)
	private EmployeeService employeeService;

	@GetMapping("/document")
	public String showDocument(@RequestParam String select, @RequestParam String id, String _csrf, Model model) {
		log.info("confirmSelect" + select);
		log.info("confirmId" + id);

		return "confirm/document";
	}

	@GetMapping("/searchflight")
	public String searchflight(@RequestParam String text, @RequestParam String find, @RequestParam String enable,
			Model model) {
		log.info("text :" + text);
		log.info("find :" + find);
		String state = "";
		if (enable.equals("cancellation"))
			state = "1";
		if (enable.equals("cancellationCancel"))
			state = "9";
		List<OpenApiRealTimeFlightDto> list = scheduleService.selectSearchRealTime(text, find);
		List<OpenApiRealTimeFlightDto> resultList = new ArrayList<OpenApiRealTimeFlightDto>();
		for (OpenApiRealTimeFlightDto openApiRealTimeFlightDto : list) {
			if (openApiRealTimeFlightDto.getEnable().equals(state))
				resultList.add(openApiRealTimeFlightDto);
		}
		model.addAttribute("list", resultList);
		return "confirm/windowSearchflight";

	}

	@GetMapping("/searchEmployee")
	public String searchEmployee(@RequestParam String text, @RequestParam String find, Model model) {
		log.info("text :" + text);
		log.info("find :" + find);
		List<EmployeeStatusVO> list = employeeService.memberSelect(find, text);
		model.addAttribute("list", list);
		return "confirm/windowSearchEmployee";
	}

	// --------------------------------------------------------------------------------------------

	@PostMapping("/setDocument")
    public ResponseEntity<String> setDocument(DocumentVO documentVO) {
        // 臾몄꽌 踰덊샇 yyyydd-臾몄꽌醫낅쪟-cnt
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMM");
        Date date = new Date();

        String docNumDate = dataFormat.format(date); // 留� �븵�옄由�
        String docNumType = documentService.getDocumentType(documentVO.getType());
        String docNumCnt = documentService.getDocumentCnt(docNumDate + "-" + docNumType);
        if (docNumCnt.length() == 1)
            docNumCnt = "00" + docNumCnt;
        if (docNumCnt.length() == 2)
            docNumCnt = "0" + docNumCnt;

        String docNum = docNumDate + "-" + docNumType + "-" + docNumCnt;

        documentVO.setDocumentNo(docNum);

        // 작성자 와 문서 레벨비교하고 작성자가 더크면 작성자로
//        int user = Integer.parseInt(documentVO.getWriter().substring(2, 3));

        int user = employeeService.getEmployeeLev(documentVO.getWriter());
        int docLev = documentService.setDocumentLev(documentVO.getType());
        if (docLev == 1)
            documentVO.setDocstate("1");
        else if (user >= docLev)
            documentVO.setDocstate(Integer.toString(user));
        else//보고
            documentVO.setDocstate("3");
//          documentVO.setDocstate(Integer.toString(docLev));
        // -----------------------------------
        documentService.setDocument(documentVO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html; charset=UTF-8");

        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('처리 완료.');");
        sb.append("window.close()");
        sb.append("</script>");
        return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);

    }

    @GetMapping("/documentBox")
    public String documentBox(Model model, String eid) {
        String authLev = Integer.toString(employeeService.getEmployeeLev(eid));

        String selectStr = "";
        // 5 이면문자열 2개 자르기
        if (authLev.equals("5"))
            selectStr = eid.substring(0, 1);
        if (authLev.equals("4"))
            selectStr = eid.substring(0, 3);
        if (authLev.equals("3"))
            selectStr = eid.substring(0, 3);
        

        List<DocumentVO> confirmList = documentService.getFinishDocuments(eid, "10");
        List<DocumentVO> returnList = documentService.getFinishDocuments(eid, "9");

        
        // 3이나 4이면 문자열 2개 자르기
        // 현제 lev이 일치인 아이들만 나오는데 부서가 일치해야 나오게하기
        List<DocumentVO> beforelist = documentService.getConfirmDocuments(authLev, selectStr);
        
        model.addAttribute("confirmList", confirmList);
        model.addAttribute("returnList", returnList);
        model.addAttribute("beforelist", beforelist); //결제중인녀석들
        return "confirm/documentBox";
    }

    

     
    @GetMapping("/checkFinishdocument")
    public String chekFinishdocument(String number, Model model) {
        DocumentVO documentVO = documentService.getdocument(number);
        System.out.println("이게 나오냔 말이다!" + documentVO.toString());
        model.addAttribute("documentVO", documentVO);
        return "confirm/checkFinishdocument";
    }
    
	@GetMapping("/chekdocument")
	public String chekdocument(String number, Model model) {
		DocumentVO documentVO = documentService.getdocument(number);
		System.out.println(documentVO.toString());
		model.addAttribute("documentVO", documentVO);
		return "confirm/checkDocument";
	}

	@PreAuthorize("isAuthenticated()")
    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(String no, String check, String user) {
        if (check.equals("cancel")) {
            documentService.setDocumentCancel(no, user);
        } else {
            DocumentVO documentVO = documentService.getdocument(no);
            // 문서 lev, user lev 비교
            int docLev = documentService.setDocumentLev(documentVO.getType());
            int userLev = employeeService.getEmployeeLev(user);

            // 결제완료
            if (userLev >= docLev) {
                documentService.setDocumentConfirm(no, user);

                // 결제 되었을때 처리들 여기 넣기 할일
                // 조퇴
//보고
                if (documentVO.getType().equals("early") || documentVO.getType().equals("vacation")) {

                    String date = "";
                    String eid = documentVO.getWriter();
                    String estate = "";
                    if (documentVO.getType().equals("early")) {
                        date = documentVO.getRegdate();
                        estate = "early";
                    } else if (documentVO.getType().equals("vacation")) {
                        date = documentVO.getKey1();
                        estate = "va";
                    }
                    int dateCheck = 0; // 금일 날짜 존재여부 체크
                    JSONObject objUpdate = new JSONObject();
                    List<EmployeeWorkNoteVO> empWorkNote = employeeService.getWorkNote();
                    for (EmployeeWorkNoteVO vo : empWorkNote) {
                        objUpdate.put(vo.getDate(), vo.getEidAndWorkNote());
                    }
                    for (EmployeeWorkNoteVO vo : empWorkNote) {
                        if (date.equals(vo.getDate())) { // 오늘날짜가 table에 존재한다면
                            dateCheck++;
                        }
                    } // for
                 
                    if (dateCheck==1) { // 오늘날짜 레코드 존재 update
                        JSONArray array = new JSONArray();
                        // String -> List<Map<String,Object>>
                        JSONParser parser = new JSONParser();
                        Object object = new Object();
                        try {
                            object = parser.parse(objUpdate.get(date).toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        array = (JSONArray) object;
                        List<Map<String, Object>> listMap = AttendDao.getListMapFromJsonArray(array);
                        Map<String, Object> map = listMap.get(0); // 하루치 근무일지
                        map.put(eid, estate); // 출근자 명단 추가

                        JSONObject objTemp = new JSONObject();
                        objTemp = AttendDao.getJsonStringFromMap(map);
                        employeeService.updateByWorkNote(date, "[" + objTemp.toJSONString() + "]");
                    } else if (dateCheck !=1) { // 오늘날짜 레코드 존재 X insert
                        JSONArray array = new JSONArray();
                        JSONObject obj = new JSONObject();
                        obj.put(eid, estate);
                        array.add(obj);
                        String str = array.toJSONString();
                        employeeService.insertByWorkNote(date, str);
                    }
                }
              //--------------------------------------------------------------------------------------------------------
                // 인사이동
                if (documentVO.getType().equals("position")) {
                    employeeService.updateEmployeeStatus(documentVO.getKey1(),documentVO.getKey2());
                }
                // 진급
                if (documentVO.getType().equals("promotion")) {

                }
                // 결항
                if (documentVO.getType().equals("cancellation")) {
                    scheduleService.setCansellation(documentVO.getKey1(), documentVO.getKey2());
                }
                // 결항 취소
                if (documentVO.getType().equals("cancellationCancel")) {
                    scheduleService.setcancellationCancel(documentVO.getKey1(), documentVO.getKey2());
                }
            } else {
                // 다음 단계 진행
                if (userLev == 3)
                    documentService.setDocumentConfirmExaggeration(no, user);
                if (userLev == 4)
                    documentService.setDocumentConfirmDeputy(no, user);
            }

        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html; charset=UTF-8");

        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('처리 완료.');");
        sb.append("opener.parent.location.reload();");
        sb.append("window.close()");
        sb.append("</script>");
        return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
    }
}
