package com.exam.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeeVo {
	
	private String eid;
	private String epassword;
	private String ename;
	private String deptcode;
	private String eaddress; 
	private String ephone;
	private String egender;
	private String eclass;
	private String eposition;
	private int eyear;
	private int ehired;
	private Date ebirthday;
	private int level;

}
