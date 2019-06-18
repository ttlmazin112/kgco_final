package com.exam.service;

import java.util.List;

import com.exam.domain.AirplnByGateVo;
import com.exam.domain.OpenApiDomesticDto;
import com.exam.domain.OpenApiInternationalDto;
import com.exam.domain.OpenApiRealTimeFlightDto;

public interface ScheduleService {
    
 // domestic --------
    public int domesticInsert(OpenApiDomesticDto domesticDto);
    public List<OpenApiDomesticDto> selectAllDomestic();
    public int deleteAllDomestic();
    public List<OpenApiDomesticDto> domesticSelectbyDateIo(String date, String io);
    public int domesticUpdate(List<OpenApiDomesticDto> list);
    public List<OpenApiDomesticDto> selectCountDomestic(int i, List<OpenApiDomesticDto> list);
    
// international --------
    public int internationalInsert(OpenApiInternationalDto internationalDto);   
    public List<OpenApiInternationalDto> selectAllInternational();  
    public int deleteAllInternational();
    public List<OpenApiInternationalDto> internationalSelectbyDateIo(String date, String io);
    public int internationalUpdate(List<OpenApiInternationalDto> list);
    public List<OpenApiInternationalDto> selectCountInternational(int i, List<OpenApiInternationalDto> list);
    
    
// real-time ----------
    public List<OpenApiRealTimeFlightDto> realtimeSelectOrderbyEtd(String io);
    public void realtimeInsert(OpenApiRealTimeFlightDto realtimeDto);
    
    public List<OpenApiRealTimeFlightDto> showRealTimeEnable(); //삭제
    public List<OpenApiRealTimeFlightDto> selectCountRealTime(int i, List<OpenApiRealTimeFlightDto> apiList); //db에 데이터 있는지 유무 검색
    public List<OpenApiRealTimeFlightDto> selectUpdatedRealTime(); //이번 task에서 update 된 아이들 찾기
    public List<OpenApiRealTimeFlightDto> realTimeFlightUpdate(List<OpenApiRealTimeFlightDto> list); //api 데이터 db로 업데이트
    public List<OpenApiRealTimeFlightDto> selectSearchRealTime(String text, String find); //실시간 정보 검색
   
    public void realTimeFlightDelete();
    public void realTimeFlightInsert(List<OpenApiRealTimeFlightDto> list);  //실시간 정보 insert
    public void selectUpdatedRealTimeInitialization(List<OpenApiRealTimeFlightDto> list); //enable 상태 초기화
    
    
 // airplnb ---------------------    
    public void airplnbByGateInsert(AirplnByGateVo airplnByGateVo);
    public void setCansellation(String key1, String key2);
	public void setcancellationCancel(String key1, String key2);
    public void airplnbByGateDelete();
    public List<OpenApiRealTimeFlightDto> getNowGate(String time, String string);
    public AirplnByGateVo findGate(String airFln, String std);
    public String getFlightNum(String city);
	public void updateGateSet(AirplnByGateVo airplnByGateVo);
}
