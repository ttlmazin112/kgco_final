package com.exam.service;

import java.util.List;

import com.exam.domain.AttachVo;

public interface AttachService {

    public void insertAttach(List<AttachVo> attachList);
    public List<AttachVo> findByboardId(int bId);
    public void deleteAttach(String uuid);
    
    public List<AttachVo> getAllattach();
}
