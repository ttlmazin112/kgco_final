package com.exam.domain;

import java.util.List;

import lombok.Data;

@Data
public class BoardVo {
	/*
	
	CREATE TABLE `board` (
  `boardId` int(11) NOT NULL,
  `eId` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `subject` varchar(50) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `readcount` int(11) DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  `auth` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`boardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

		
	 */

	private int boardId;
	private String eid;
	private String password;
	private String subject;
	private String content;
	private int readcount; // 조회수
	private String regDate; // 글작성 날짜시간
	private String auth;

	private List<AttachVo> attachList;
	
}
