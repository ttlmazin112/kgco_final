package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.BoardVo;

public interface BoardMapper {
    public Integer getBoardId();
//	public List<BoardVo> getAllBoards(@Param("startRow") int startRow, @Param("amount") int amount, @Param("search") String search);
	public List<BoardVo> getAllBoards(@Param("startRow") int startRow, @Param("amount") int amount, @Param("search") String search, @Param("searchSelect") String searchSelect);

	
	public int getBoardCount(@Param("search") String search, @Param("searchSelect") String searchSelect);
	public BoardVo getBoardbyBoardId(int boardId);
	public List<BoardVo> getMostReadCount();
	public void insertBoard(BoardVo boardVo);
	public void updateReadcount(int boardId);
	public void updateBoard(BoardVo boardVo);
	public void deleteBoard(int boardId);
	public String boardAndattachJoinbyBoardId(int boardId);
	
}
