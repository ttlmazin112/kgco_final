package com.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.mapper.AuthMapper;

import lombok.Setter;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {
	@Setter(onMethod_ = @Autowired)
	private AuthMapper mapper;

	@Override
	public void updateAuth(String eid, String auth) {
		mapper.updateAuth(eid, auth);
		
	}
}
