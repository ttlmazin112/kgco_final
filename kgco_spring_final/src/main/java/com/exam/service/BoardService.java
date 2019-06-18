package com.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.AttachVo;
import com.exam.domain.BoardVo;

public interface BoardService {
    public Integer getBoardId();
    public List<BoardVo> getAllBoards(int startRow, int amount, String search, String searchSelect);
    public int getBoardCount(@Param("search") String search, @Param("searchSelect") String searchSelect);
    public BoardVo getBoardbyBoardId(int boardId);
    public List<BoardVo> getMostReadCount(); 
    public void updateReadcount(int boardId);
    public boolean modifyBoard(BoardVo boardVo);
    public boolean deleteBoard(int num, String pass);
    public void insertBoardAndAttach(BoardVo boardVo, List<AttachVo> attachList);
    
    public void deleteBoardAndAttach(int boardId);
    public String boardAndattachJoinbyBoardId(int boardId);
}
