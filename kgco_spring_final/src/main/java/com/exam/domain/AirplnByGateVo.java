package com.exam.domain;

import lombok.Data;

@Data
public class AirplnByGateVo {
/*	CREATE TABLE airplnByGate
	(
	    gate         VARCHAR(10)     NULL, 
	    airFln       VARCHAR(50)    NOT NULL, 
	    gateState    VARCHAR(50)     NULL, 
	    airNumber    VARCHAR(20)     NULL, 
	    CONSTRAINT AIRPLNBYGATE_PK PRIMARY KEY (airFln)
	);

	ALTER TABLE airplnByGate ADD CONSTRAINT FK_airplnByGate_gate_PebyGate_ FOREIGN KEY (gate)
	        REFERENCES PebyGate (gate);
	 */
	
	private String gate;
	private String airFln;
	private	String gateState;
	private String airNumber;
	private String std;
}
