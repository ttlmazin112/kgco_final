package com.exam.mapper;

import java.util.List;

import com.exam.domain.AttachVo;

public interface AttachMapper {
    
    public void insertAttach(AttachVo attachVo);
    public List<AttachVo> findByboardId(int bId);
    public void deleteAttach(String uuid);
    public void deleteAttachbyBoardId(int boardId);
    public List<AttachVo> getAllattach();

}
