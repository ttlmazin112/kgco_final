package com.exam.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.AttachVo;
import com.exam.domain.BoardVo;
import com.exam.mapper.AttachMapper;
import com.exam.mapper.BoardMapper;
import com.exam.mapper.ReplyMapper;

import lombok.Setter;

@Transactional
@Service
public class BoardServiceImpl implements BoardService {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;
    @Setter(onMethod_ = @Autowired)
    private AttachMapper attachMapper;
    @Setter(onMethod_ = @Autowired)
    private ReplyMapper replyMapper;
    
    @Override
    public Integer getBoardId() {
        return mapper.getBoardId();
    }

    
    
//    @Override
//    public List<BoardVo> getAllBoards(int startRow, int amount, String search,  String searchSelect) {
//        return mapper.getAllBoards(startRow, amount, search);
//    }
    
    
    @Override
    public List<BoardVo> getAllBoards(int startRow, int amount, String search,  String searchSelect) {
        List<BoardVo> boardList=mapper.getAllBoards(startRow, amount, search, searchSelect);
        for(BoardVo b:boardList) {
           
            String regDate=    b.getRegDate().substring(0,16);
//            2019-06-13 19:11:00.0
            b.setRegDate(regDate);
        }
        return boardList;
    }
    
    
    

    @Override
    public List<BoardVo> getMostReadCount() {
        return mapper.getMostReadCount();
    }
    
    @Override
    public BoardVo getBoardbyBoardId(int boardId) {
        return mapper.getBoardbyBoardId(boardId);
    }

    @Override
    public void insertBoardAndAttach(BoardVo boardVo, List<AttachVo> attachList) {

        boardVo.setReadcount(0);
        
        mapper.insertBoard(boardVo);
        
        boardVo.setBoardId(mapper.getBoardId());
        boardVo.setAttachList(attachList);
        
        for(AttachVo attachVo:attachList) {
//        	
//        	if(attachVo.getFiletype()) {
//        		
//        	}
        	
        	attachMapper.insertAttach(attachVo);
    	}
        
        
    }
    
    @Override
    public boolean modifyBoard(BoardVo boardVo) {
        boolean isSuccessed=false;
        BoardVo dbBoard=mapper.getBoardbyBoardId(boardVo.getBoardId());
        if(boardVo.getPassword().equals(dbBoard.getPassword())) {
            mapper.updateBoard(boardVo);
            isSuccessed=true;
        }else {
            isSuccessed=false;
        }
        return isSuccessed;
        
    }

	@Override
	public boolean deleteBoard(int boardId, String pass) {
		boolean isSuccessed=false;
		BoardVo dbBoard=mapper.getBoardbyBoardId(boardId);
		if(pass.equals(dbBoard.getPassword())) {
			mapper.deleteBoard(dbBoard.getBoardId());
			isSuccessed=true;
		}else {
			isSuccessed=false;
		}
		
		return isSuccessed;
	}

	
	@Override
	public void updateReadcount(int boardId) {
		mapper.updateReadcount(boardId);
	}

    @Override
    public int getBoardCount(String search, String searchSelect) {
        return mapper.getBoardCount(search,searchSelect);
    }


	@Override
	public void deleteBoardAndAttach(int boardId) {
		mapper.deleteBoard(boardId);
		attachMapper.deleteAttachbyBoardId(boardId);
		replyMapper.deleteReplybyBoardId(boardId);

	}

    @Override
    public String boardAndattachJoinbyBoardId(int boardId) {
        return mapper.boardAndattachJoinbyBoardId(boardId);
    }

	

    

   
    
	
	
    
    
    
    
    
}
