package com.exam.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.exam.domain.AirplnByGateVo;
import com.exam.domain.OpenApiRealTimeFlightDto;
import com.exam.service.ScheduleService;
import com.exam.util.ScheduleDao;

import lombok.extern.log4j.Log4j;

@Component //손들기
@Log4j
public class RealTimeTask {
    
    @Autowired
    ScheduleDao scheduleDao;
    
    @Autowired
    ScheduleService scheduleService;
    
    
    
    // http://www.cronmaker.com/
    @Scheduled(cron = "*/45 * * * * *") //초 분 시 일 월 주
    public void realTimeTaskUpdate() {
        
        log.info("TASK TO REALTIME" + System.currentTimeMillis());  //현재 시간
        
        List<OpenApiRealTimeFlightDto> apiList = scheduleDao.getAllRealTimeFlight(3, null);
        
        List<OpenApiRealTimeFlightDto> listUpdate = scheduleService.selectCountRealTime(1, apiList); //1 = update
        List<OpenApiRealTimeFlightDto> listInsert = scheduleService.selectCountRealTime(0, apiList); //0 = insert
        
        if(listUpdate.size() != 0)  scheduleService.realTimeFlightUpdate(listUpdate);
        if(listInsert.size() != 0)  scheduleService.realTimeFlightInsert(listInsert);
    }
    
//    @Scheduled(cron = "* * * * * *")
//    public void printSimpleLog() {
//        log.info("printSimpleLog()................");
//        log.info("================================");
//    }
    
    //할일
    @Scheduled(cron = "*/30 * * * * *") //초 분 시 일 월 주
 public void airplnGateAssign() {
        
        List<OpenApiRealTimeFlightDto> outList=scheduleService.realtimeSelectOrderbyEtd("O");
        List<OpenApiRealTimeFlightDto> inList=scheduleService.realtimeSelectOrderbyEtd("I");
        
        if(outList!=null && inList !=null) {
            scheduleService.airplnbByGateDelete();
            System.out.println("기존 편성 데이터 삭제");
            
            Date date = new Date();
            SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String strDate = sdf2.format(date).toString();
            
            int inCount=1; 
            for(OpenApiRealTimeFlightDto realtimedto:inList) {

                AirplnByGateVo airplnBygate=new AirplnByGateVo();
                airplnBygate.setAirFln(realtimedto.getAirFln());
                airplnBygate.setStd(realtimedto.getStd());
                airplnBygate.setAirNumber("");  //항공기종->차후 작업
                airplnBygate.setGateState(""); //차후 작업
                airplnBygate.setGate(scheduleDao.gateAlphabet(inCount));
                                                            //할일:airplnBygate를 시간순으로 정렬하려면 realtime에서 시간을 불러와서 정렬해야하지 않을까..?
                inCount = inCount + 1;// 1~4->ABCD
                if (inCount == 5)
                    inCount = 1;
                scheduleService.airplnbByGateInsert(airplnBygate);
               
            }
            
            System.out.println(strDate+" 출항게이트 편성");
            
            int outCount=5;
            for(OpenApiRealTimeFlightDto realtimedto:outList) {
                
                AirplnByGateVo airplnBygate=new AirplnByGateVo();
                airplnBygate.setAirFln(realtimedto.getAirFln());
                airplnBygate.setStd(realtimedto.getStd());
                airplnBygate.setAirNumber("");  //항공기종->차후 작업
                airplnBygate.setGateState(""); //차후 작업
                airplnBygate.setGate(scheduleDao.gateAlphabet(outCount));
                
                outCount = outCount + 1; //5~8->EFGH
                if (outCount == 9)
                    outCount = 5;
                
                scheduleService.airplnbByGateInsert(airplnBygate);
              
            }
            System.out.println(strDate+" 입항게이트 편성");
        }
        
        }
}
