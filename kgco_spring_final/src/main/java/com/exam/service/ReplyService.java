package com.exam.service;

import java.util.List;

import com.exam.domain.ReplyVo;

public interface ReplyService {
	
	public List<ReplyVo> getAllRepliesByBoardId(int boardId);
	public Integer getReplycount(int boardId);
	public void insertReply(ReplyVo replyVo);
	public void deleteReply();
}
