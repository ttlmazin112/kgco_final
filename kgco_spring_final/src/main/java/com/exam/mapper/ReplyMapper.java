package com.exam.mapper;

import java.util.List;


import com.exam.domain.ReplyVo;

public interface ReplyMapper {
	
	public List<ReplyVo> getAllRepliesByBoardId(int boardId);
	public void insertReply(ReplyVo replyVo);
	public Integer getReplycount(int boardId);
	public void deleteReplybyBoardId(int boardId);
}
