package com.exam.domain;

import lombok.Data;

@Data
public class OpenApiDomesticDto {
	
	/*

	CREATE TABLE domesticsch
(
    airlineKorean          VARCHAR(50)    NULL, 
    startcity              VARCHAR(50)    NULL, 
    arrivalcity            VARCHAR(50)    NULL, 
    domesticNum            VARCHAR(50)   NOT NULL, 
    domesticStartTime      VARCHAR(50)    NULL, 
    domesticArrivalTime    VARCHAR(50)    NULL, 
    domesticMon            VARCHAR(50)    NULL, 
    domesticTue            VARCHAR(50)    NULL, 
    domesticWed            VARCHAR(50)    NULL, 
    domesticThu            VARCHAR(50)    NULL, 
    domesticFri            VARCHAR(50)    NULL, 
    domesticSat            VARCHAR(50)    NULL, 
    domesticSun            VARCHAR(50)    NULL, 
    domesticStdate         VARCHAR(50)    NULL, 
    domesticEddate         VARCHAR(50)    NULL, 
    regdate                DATETIME       NULL, 
    enable				   int			  NULL

)DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

enable 컬럼이 없을시
alter table domesticsch
add enable int not null;




ALTER TABLE domesticsch
    ADD CONSTRAINT FK_domesticsch_domesticNum_rea FOREIGN KEY (domesticNum)
        REFERENCES realtimeflight (airFln)



	 */
	
    private String airlineKorean; // 항공사
    private String startcity;   //출발공항
    private String arrivalcity; //도착공항
    
    private String domesticNum; // 항공편 명
    private String domesticStartTime; //출발시간
    private String domesticArrivalTime; //도착시간
    // 해당요일 이면 Y, 아니면N
    private String domesticMon;
    private String domesticTue;
    private String domesticWed;
    private String domesticThu;
    private String domesticFri;
    private String domesticSat;
    private String domesticSun;

    
     //편항의 일정 시작일과 끝일 

    private String domesticStdate;
    private String domesticEddate;
    private String regdate;
    private String enable;
    
//  ex) 해석 및  db저장값
//  대한항공 05시 05 분 출발  05시 55분도착 (무안 -> 인천) 항공편이
//  12/01일부터 12/31일까지 운항한다. 운항요일은 월, 수, 목, 금, 토 이다
//  <airlineKorean>대한항공</airlineKorean>
//
//  <domesticStdate>2018-12-01T00:00:00+09:00</domesticStdate>
//  <domesticEddate>2018-12-31T00:00:00+09:00</domesticEddate>
//  
//  <domesticMon>Y</domesticMon>
//  <domesticTue>N</domesticTue>
//  <domesticWed>Y</domesticWed>
//  <domesticThu>Y</domesticThu>
//  <domesticFri>N</domesticFri>
//  <domesticSat>Y</domesticSat>
//  <domesticSun>Y</domesticSun>
//  
//  <domesticStartTime>0505</domesticStartTime>
//  <domesticArrivalTime>0555</domesticArrivalTime>
//  <startcity>무안</startcity>
//  <arrivalcity>인천</arrivalcity>
}
