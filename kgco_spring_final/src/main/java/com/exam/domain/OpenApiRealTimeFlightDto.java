package com.exam.domain;

import lombok.Data;

@Data
public class OpenApiRealTimeFlightDto {
/*	
 CREATE TABLE realtimeflight
	(
	    airFln            VARCHAR(50)     NULL, 
	    airlineEnglish    VARCHAR(50)    NULL, 
	    airlineKorean     VARCHAR(50)    NULL, 
	    airport           VARCHAR(50)    NULL, 
	    arrivedEng        VARCHAR(50)    NULL, 
	    arrivedKor        VARCHAR(50)    NULL, 
	    boardingKor       VARCHAR(50)     NULL, 
	    boardingEng       VARCHAR(50)    NULL, 
	    city              VARCHAR(50)    NULL, 
	    etd               VARCHAR(50)    NULL, 
	    io                VARCHAR(50)     NULL, 
	    line              VARCHAR(50)     NULL, 
	    rmkkor            VARCHAR(50)     NULL, 
	    std               VARCHAR(50)    NULL, 
	    regdate           datetime,
	    enable            varchar(5)
	    CONSTRAINT REALTIMEFLIGHT_PK PRIMARY KEY (airFln)
	)DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;


	ALTER TABLE realtimeflight
	    ADD CONSTRAINT FK_realtimeflight_airFln_airpl FOREIGN KEY (airFln, rmkkor)
	        REFERENCES airplnByGate (airFln, gateState) ->아직 제약조건 안걸었다.
	
*/	
	
	private String airFln;			//항공 편명
	private String airport;			//기준 공항코드
	private String city;			//운항구간코드
	
	private String airlineEnglish;	//항공사 (영문)
	private String airlineKorean;	//항공사 (국문)
	private String arrivedEng;		//도착공항 영문
	private String arrivedKor;		//도착공항 국문
	private String boardingEng;		//출발공항 영문
	private String boardingKor;		//출발공항 국문
	private String etd;				//변경시간
	private String io;				//입출력
	private String line;			//국제 국내
	private String rmkEng;			//항공편 상태(영문)
	private String rmkKor;			//항공편 상태 (국문)
	private String std;				//예정시간
	private String regdate;         //insert시간
	private String enable;          //log관리
//	<airFln>7C2654</airFln>
//	<airlineEnglish>JEJU AIR</airlineEnglish>
//	<airlineKorean>제주항공</airlineKorean>
//	<airport>PUS</airport>
//	<arrivedEng>GIMHAE</arrivedEng>
//	<arrivedKor>부산/김해</arrivedKor>
//	<boardingEng>TAIPEI</boardingEng>
//	<boardingKor>타이베이/타오위안</boardingKor>
//	<city>TPE</city>
//	<etd>0603</etd>
//	<gate>4</gate>
//	<io>I</io>
//	<line>국제</line>
//	<rmkEng>ARRIVED </rmkEng>
//	<rmkKor>도착</rmkKor>
//	<std>0610</std>
}
