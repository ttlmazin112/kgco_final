package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.AirplnByGateVo;
import com.exam.domain.OpenApiDomesticDto;
import com.exam.domain.OpenApiInternationalDto;
import com.exam.domain.OpenApiRealTimeFlightDto;

public interface ScheduleMapper {
    
 // domestic ------------------------
    public int domesticInsert(OpenApiDomesticDto domesticDto);
    public List<OpenApiDomesticDto> selectAllDomestic();
    public List<OpenApiDomesticDto> domesticSelectbyDate(String date);  //해당 날짜만 들고오기
    public int deleteAllDomestic();
    public OpenApiDomesticDto selectDomesticByKey(OpenApiDomesticDto domesticDto); //원하는 db값 하나 들고오기
    public int selectCountDomesticByKey(OpenApiDomesticDto domesticDto);
    public void domesticUpdate(OpenApiDomesticDto domesticDto);
    public void domesticDisableUpdate(OpenApiDomesticDto domesticDto);

 // international -------------------
    public int internationalInsert(OpenApiInternationalDto internationalDto);   
    public List<OpenApiInternationalDto> selectAllInternational();  
    public List<OpenApiInternationalDto> internationalSelectbyDate(String date);
    public int deleteAllInternational();
    public OpenApiInternationalDto selectInternationalByKey(OpenApiInternationalDto internationalDto);
    public int selectCountInternationalByKey(OpenApiInternationalDto internationalDto);
    public void internationalUpdate(OpenApiInternationalDto internationalDto);
    public void internationalDisableUpdate(OpenApiInternationalDto internationalDto);

    
 // real-time ---------- 
    public List<OpenApiRealTimeFlightDto> realtimeSelectOrderbyEtd(String io);
    
    public void realTimeFlightDelete();     //실시간 db전체 삭제
	public List<OpenApiRealTimeFlightDto> showRealTimeEnable(); //db에 가용 데이터만 찾기
    public int selectCountRealTime(OpenApiRealTimeFlightDto openApiRealTimeFlightDto);  //데이터 유무를 카운트로 찾기
    public void realTimeFlightInsert(OpenApiRealTimeFlightDto openApiRealTimeFlightDto);    //실시간 데이터 insert
    public OpenApiRealTimeFlightDto selectCountRealTimeUpdateCheck(OpenApiRealTimeFlightDto openApiRealTimeFlightDto);  //
    public List<OpenApiRealTimeFlightDto> selectSearchRealTime(@Param("text") String text, @Param("find") String find);
    public int realTimeFlightLogCountCheck(OpenApiRealTimeFlightDto openApiRealTimeFlightDto);
    public void realTimeFlightDisableUpdate(OpenApiRealTimeFlightDto oartf);
    public List<OpenApiRealTimeFlightDto> selectUpdatedRealTime();
    public void selectUpdatedRealTimeInitialization(OpenApiRealTimeFlightDto openApiRealTimeFlightDto);
    
 // ---------------------    
    public void airplnbByGateInsert(AirplnByGateVo airplnByGateVo);
    public void airplnbByGateDelete();
    public List<OpenApiRealTimeFlightDto> getNowGate(@Param("time")String time,@Param("string") String string);
    public AirplnByGateVo findGate(@Param("airFln")String airFln, @Param("std")String std);
    public String getFlightNum(String city);
  //----------------document
  	public void setCansellation(OpenApiRealTimeFlightDto openApiRealTimeFlightDto);
  	public OpenApiRealTimeFlightDto selectCancellation(@Param("key1")String key1, @Param("key2")String key2);
  	public OpenApiRealTimeFlightDto selectCancellationCancel(@Param("key1")String key1, @Param("key2")String key2);
  	public void updatecancellation(OpenApiRealTimeFlightDto openApiRealTimeFlightDto);
  	public int getCancellation(OpenApiRealTimeFlightDto openApiRealTimeFlightDto);
	public void updateGateSet(AirplnByGateVo airplnByGateVo);
}
