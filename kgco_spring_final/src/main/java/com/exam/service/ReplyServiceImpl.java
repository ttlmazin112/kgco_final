package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.domain.ReplyVo;
import com.exam.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	

    @Autowired
	ReplyMapper mapper;
	
	@Override
	public List<ReplyVo> getAllRepliesByBoardId(int boardId) {
		return mapper.getAllRepliesByBoardId(boardId);
	}

	@Override
	public void insertReply(ReplyVo replyVo) {
//		ReplyVo replyVo=new ReplyVo();
//		replyVo.setReplyContent(replyVo.getReplyContent());
		mapper.insertReply(replyVo);
	}

	@Override
    public Integer getReplycount(int boardId) {
        return mapper.getReplycount(boardId);
    }
	
	
	@Override
	public void deleteReply() {

	}

}
