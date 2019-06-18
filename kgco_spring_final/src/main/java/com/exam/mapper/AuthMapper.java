package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.AuthVo;

public interface AuthMapper {
	public List<AuthVo> selectAuthListById(String eid);
	public void updateAuth(@Param("eid")String eid,@Param("auth")String auth);
}
