package com.exam.domain;

import lombok.Data;

@Data
public class OpenApiInternationalDto {
	
/*
CREATE TABLE InternationalSch
(
    airlineKorean          VARCHAR(50)     NULL, 
    airport                VARCHAR(50)     NULL, 
    city                   VARCHAR(50)     NULL, 
    internationalIoType    VARCHAR(50)     NULL, 
    internationalNum       VARCHAR(50)     NULL, 
    internationalTime      VARCHAR(50)     NULL, 
    internationalMon       VARCHAR(50)     NULL, 
    internationalTue       VARCHAR(50)     NULL, 
    internationalWed       VARCHAR(50)     NULL, 
    internationalThu       VARCHAR(50)     NULL, 
    internationalFri       VARCHAR(50)     NULL, 
    internationalSat       VARCHAR(50)     NULL, 
    internationalSun       VARCHAR(50)     NULL, 
    internationalStdate    VARCHAR(50)     NULL, 
    internationalEddate    VARCHAR(50)     NULL, 
    regdate                DATETIME            NULL, 
    enable				   int				NOT NULL
)DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

enable 컬럼이 없을시
alter table internationalsch
add enable int not null;



ALTER TABLE InternationalSch
    ADD CONSTRAINT FK_InternationalSch_internatio FOREIGN KEY (internationalNum)
        REFERENCES realtimeflight (airFln)

 */	
	
	
	
	
	
	private String airlineKorean; // 항공사 홈페이지
	private String airport; // 기준공항
	private String city; // 운항구간 도착공항
	private String internationalIoType; // 입출항 코드 in out
	private String internationalNum; // 항공편 명
	private String internationalTime; // 계획시간
	// 해당날이면 Y, 아니면N
	private String internationalMon;
	private String internationalTue;
	private String internationalWed;
	private String internationalThu;
	private String internationalFri;
	private String internationalSat;
	private String internationalSun;

	private String internationalStdate; //
	private String internationalEddate; //
	private String regDate;
	private String enable;
	
//	<item>
//	<airlineKorean>원동항공</airlineKorean>
//	<airport>청주</airport>
//	<city>타이베이/타오위안</city>
//	<internationalEddate>2019-02-10T00:00:00+09:00</internationalEddate>
//	<internationalFri>N</internationalFri>
//	<internationalIoType>OUT</internationalIoType>
//	<internationalMon>N</internationalMon>
//	<internationalNum>FE8615</internationalNum>
//	<internationalSat>N</internationalSat>
//	<internationalStdate>2019-02-05T00:00:00+09:00</internationalStdate>
//	<internationalSun>N</internationalSun>
//	<internationalThu>N</internationalThu>
//	<internationalTime>0000</internationalTime>
//	<internationalTue>N</internationalTue>
//	<internationalWed>N</internationalWed>
//	</item>
	
	
}
