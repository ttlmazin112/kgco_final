package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.domain.AttachVo;
import com.exam.mapper.AttachMapper;

import lombok.Setter;


@Service
public class AttachServiceImpl implements AttachService {

    @Autowired
    AttachMapper mapper;
    
    @Override
    public void insertAttach(List<AttachVo> attachList) {
    	for(AttachVo attachVo:attachList) {
    		mapper.insertAttach(attachVo);
    	}
        
    }

    @Override
    public List<AttachVo> findByboardId(int bId) {
        return mapper.findByboardId(bId);
    }

	@Override
	public void deleteAttach(String uuid) {
		mapper.deleteAttach(uuid);
	}

	@Override
	public List<AttachVo> getAllattach() {
		return mapper.getAllattach();
	}
	
	
    
    
    
}
