package com.exam.domain;

import lombok.Data;

@Data
public class ReplyVo {

	/*
	
CREATE TABLE `reply` (
  `eId` varchar(20) NOT NULL,
  `replyContent` varchar(300) DEFAULT NULL,
  `replyPassword` varchar(45) DEFAULT NULL,
  `replyregDate` datetime DEFAULT NULL,
  `replyGroup` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `ename` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



    ename 컬럼 추가
    
	
	 */
	private String ename;
	private String eId;
	private String replyContent;
	private String replyPassword;
	private String replyregDate;
	private String level;
	private int replyGroup; //댓글이 존재하는 글의 번호


	
}
