package com.exam.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class authTest {

	public static void main(String[] args) {
		// 현재시간
		Date date = new Date();
		date.getDate(); // 현재시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		Long curTime = Long.parseLong(sdf.format(date).substring(0, 8)); // yyyy-mm
		Long time = Long.parseLong(sdf.format(date).substring(8, 12)); // hh-mm
		// 근무그룹판별
		/* 근무기준 
		 	출근기준 				|  근무투입기준(근무가능인원에 포함되는 시점)	
		 	A : 0900 ~ 1800		|  1000 ~ 1759
		 	B : 1700 ~ 2600		|  1800 ~ 2559
		 	C : 0100 ~ 1000		|  0200 ~ 0959
		 */		
		String eGroup = "";
		if (200 <= time && time < 1000) {
			eGroup = "C";
		}else if(1000 <= time && time < 1800) {
			eGroup = "A";
		}else if(1800 <= time || time < 200) {
			eGroup = "B";
		}
		String[] eGateArr = {"A","B","C","D","E","F","G","H","I","J"};
		String eIdTR = "TR";
		String eIdMA = "MA";
		String eGate ="";
		int gateNum =0;
		for(int i =1; i<=10; i++) {
			eGate= eGateArr[gateNum];
			System.out.println(eGate);
			//service.updatePebyGate(eIdTR, eIdMA, i, eGate);
			gateNum++;
		}
	}

}
