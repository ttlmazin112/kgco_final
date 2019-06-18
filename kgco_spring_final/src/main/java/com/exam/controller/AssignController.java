package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpNE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.domain.AirplnByGateVo;
import com.exam.domain.OpenApiRealTimeFlightDto;
import com.exam.service.ScheduleService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller  //servlet-context의 영역
@RequestMapping("/assign/*")
public class AssignController {
	
	@Setter(onMethod_ = @Autowired)
	private ScheduleService scheduleService;
	
	public String gateAlphabet(int count) {
		String gate="";
		if(count==1) 	  gate="A";	
		else if(count==2) gate="B";
		else if(count==3) gate="C";
		else if(count==4) gate="D";
		else if(count==5) gate="E";
		else if(count==6) gate="F";
		else if(count==7) gate="G";
		else if(count==8) gate="H";
		return gate;
	}
	
	@GetMapping("/airplnGateAssign")
	public String airplnGateAssign() {
		List<OpenApiRealTimeFlightDto> outList=scheduleService.realtimeSelectOrderbyEtd("O");
		List<OpenApiRealTimeFlightDto> inList=scheduleService.realtimeSelectOrderbyEtd("I");

		int outCount=1;	
		for(OpenApiRealTimeFlightDto realtimedto:outList) {

			AirplnByGateVo airplnBygate=new AirplnByGateVo();
			airplnBygate.setAirFln(realtimedto.getAirFln());
			airplnBygate.setAirNumber("");  //항공기종->차후 작업
			airplnBygate.setGateState(""); //차후 작업
			airplnBygate.setGate(gateAlphabet(outCount));
														//할일:airplnBygate를 시간순으로 정렬하려면 realtime에서 시간을 불러와서 정렬해야하지 않을까..?
			outCount = outCount + 1;// 1~4->ABCD
			if (outCount == 5)
				outCount = 1;
			scheduleService.airplnbByGateInsert(airplnBygate);
		}
		
		
		
		int inCount=5;
		for(OpenApiRealTimeFlightDto realtimedto:inList) {
			
			AirplnByGateVo airplnBygate=new AirplnByGateVo();
			airplnBygate.setAirFln(realtimedto.getAirFln());
			airplnBygate.setAirNumber("");  //항공기종->차후 작업
			airplnBygate.setGateState(""); //차후 작업
			airplnBygate.setGate(gateAlphabet(inCount));
			
			inCount = inCount + 1; //5~8->EFGH
			if (inCount == 9)
				inCount = 5;
			scheduleService.airplnbByGateInsert(airplnBygate);
		}
		return "";
	}
	
	
}
