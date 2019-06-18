package com.exam.controller;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLConnection;
import java.security.Provider.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.exam.domain.AirplnByGateVo;
import com.exam.domain.OpenApiDomesticDto;
import com.exam.domain.OpenApiInternationalDto;
import com.exam.domain.OpenApiRealTimeFlightDto;
import com.exam.service.ScheduleService;
import com.exam.util.ScheduleDao;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/schedule/*")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ScheduleDao scheduleDao;

    @GetMapping("/permission")
    public String permission() {
        return "/schedule/permission2";
    }

    @GetMapping("/realtime") // 화면이동 해수
    public String realTime() {
        return "/schedule/realtime";
    }

    @GetMapping("/realtimeAPI") // btn작업 해수
    public String realtimaAPI() {
        List<OpenApiRealTimeFlightDto> list = null;
        list = scheduleDao.getAllRealTimeFlight(3, null);

        scheduleService.realTimeFlightInsert(list);
        log.info("삽입완료");
        return "/schedule/realtime";
    }

    @GetMapping("/realtimeAPIDelete") // btn작업 해수
    public String realtimaAPIDelete() {

        scheduleService.realTimeFlightDelete();
        log.info("삭제완료");
        return "/schedule/realtime";
    }

    @GetMapping("/permissionScheduleDetail")
    public String permissionScheduleDetail(Model model, @RequestParam String airFln, @RequestParam String start,
            @RequestParam String date, @RequestParam String diType, @RequestParam String ioType) {
        String getDate = scheduleDao.dateFormat(date);

        if (diType.equals("d")) {
            List<OpenApiDomesticDto> list = showDomesticBySelectedDate(getDate, ioType);
            OpenApiDomesticDto domesticDetailDto = new OpenApiDomesticDto();

            for (OpenApiDomesticDto dto : list) {
                if (dto.getDomesticNum().equals(airFln) && dto.getDomesticStartTime().equals(start)) {
                    domesticDetailDto = dto;
                }
            }
            model.addAttribute("domesticDetailDto", domesticDetailDto);
            log.info("detailDto" + domesticDetailDto);
        }

        else if (diType.equals("i")) {
            List<OpenApiInternationalDto> list = showInternationalBySelectedDate(getDate, ioType);
            OpenApiInternationalDto internationalDto = new OpenApiInternationalDto();

            for (OpenApiInternationalDto dto : list) {
                if (dto.getInternationalNum().equals(airFln) && dto.getInternationalTime().equals(start)) {
                    internationalDto = dto;
                }
            }
            model.addAttribute("internationalDetailDto", internationalDto);
            log.info("detailDto" + internationalDto);
        }

        return "/schedule/permissionScheduleDetail";
    }

    public List<OpenApiDomesticDto> domesticSelectSort(List<OpenApiDomesticDto> list, String io) { // API에서 바로 불러올때 쓰는
        // 메소드 중 하나
        List<OpenApiDomesticDto> setList = new ArrayList<OpenApiDomesticDto>();

        if (io.equals("")) { // in/out 설정없이 공백넣었을때 in/out을 전부 불러온다
            for (OpenApiDomesticDto openApiDomesticDto : list) {
                if (openApiDomesticDto.getStartcity().equals("서울/김포")
                        || openApiDomesticDto.getArrivalcity().equals("서울/김포")) {
                    setList.add(openApiDomesticDto);
                }
            }
        }

        if (io.equals("OUT")) {
            for (OpenApiDomesticDto openApiDomesticDto : list) {
                if (openApiDomesticDto.getStartcity().equals("서울/김포")) {
                    setList.add(openApiDomesticDto);
                }
            }
        } else if (io.equals("IN")) {
            for (OpenApiDomesticDto openApiDomesticDto : list) {
                if (openApiDomesticDto.getArrivalcity().equals("서울/김포")) {
                    setList.add(openApiDomesticDto);
                }
            }
        }
        return setList;
    } // domesticSelectSort
    
    @GetMapping("/domesticInsert")
    @ResponseBody
    public String domesticInsert() {
        Date date = new Date();
        date.getDate(); // 현재시간
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strDate = sdf.format(date).toString();
        String getDate = scheduleDao.dateFormat(strDate);

        log.info("getDAte:" + getDate);
        List<OpenApiDomesticDto> apiList = domesticSelectSort(scheduleDao.getAllDomestic(1, getDate), ""); // -> api에서 바로 받아오는 메소드
//      List<OpenApiDomesticDto> dbList=scheduleService.selectAllDomestic(); //기존 DB에 있는 것들

        for (OpenApiDomesticDto dto : apiList) {
            scheduleService.domesticInsert(dto);
        }

        System.out.println("api list size: " + apiList.size());
        return "insert 된 행 개수 " + apiList.size();
    } // domesticInsert
    
    @GetMapping("/domesticUpdate")
    public ResponseEntity<String> domesticUpdate() {
        Date date = new Date();
        date.getDate(); // 현재시간
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strDate = sdf.format(date).toString();
        String getDate = scheduleDao.dateFormat(strDate);

        List<OpenApiDomesticDto> apiList = domesticSelectSort(scheduleDao.getAllDomestic(1, getDate), ""); // -> api에서 바로 받아오는 메소드

// 새로 insert될 행 return / update될 행 return.. 두가지 분리하기
        List<OpenApiDomesticDto> insertList = scheduleService.selectCountDomestic(0, apiList);

        List<OpenApiDomesticDto> updateList = scheduleService.selectCountDomestic(1, apiList);

        int updateResult = 0;
        int insertResult = 0;
        if (updateList.size() != 0)      updateResult = scheduleService.domesticUpdate(updateList);
          
        if (insertList.size() != 0) {
            for (OpenApiDomesticDto dto : insertList) {
                insertResult = insertResult+  scheduleService.domesticInsert(dto);
                
            }
          
        }
        String resultStr = "";
        if(updateResult !=0 || insertResult != 0) resultStr = "alert(\'"+updateResult+"행, "+insertResult+" 행 삽입 변경 성공.\');";
        else resultStr = "alert('업데이트할 스케쥴이 없습니다.');";
        
//      else resultStr = "alert('개판이구만');";
                
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html; charset=utf-8");
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append(resultStr);
        sb.append("history.back(); ");
        sb.append("</script>");
        return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);

    }

    @PostMapping("/showDomesticBySelectedDate")
    @ResponseBody 
    public List<OpenApiDomesticDto> showDomesticBySelectedDate(@RequestParam String date, @RequestParam String io) {
    	log.info("showDomesticBySelectedDate============");
        String getDate = scheduleDao.dateFormat(date);
        List<OpenApiDomesticDto> list = scheduleService.domesticSelectbyDateIo(getDate, io); // -> DB에서 받아오는 메소드
//        List<OpenApiDomesticDto> list=domesticSelectSort(getAllDomestic(1, getDate), io);  -> api에서 바로 받아오는 메소드

        return list; // [{}, {}, {}]
    }// showDomestic
  //====================================== domestic end =========================================================== 

  //====================================== international start ==============================
    public List<OpenApiInternationalDto> InternationalSelectSort(List<OpenApiInternationalDto> list, String io) {
        List<OpenApiInternationalDto> setList = new ArrayList<OpenApiInternationalDto>();
        // DB로 insert할때 사용
        if (io.equals("")) { // in/out 설정없이 공백넣었을때 in/out을 전부 불러온다
            for (OpenApiInternationalDto openApiInternationalDto : list) {
                if (openApiInternationalDto.getAirport().equals("서울/김포")) {
                    setList.add(openApiInternationalDto);
                }
            }
        }
        // 이하 API에서 직접 불러올때 쓰는 것
        else if (io.equals("OUT")) {
            for (OpenApiInternationalDto openApiInternationalDto : list) {
                if (openApiInternationalDto.getAirport().equals("서울/김포")
                        && openApiInternationalDto.getInternationalIoType().equals("OUT")) {
                    setList.add(openApiInternationalDto);
                }
            }
        } else if (io.equals("IN")) {
            for (OpenApiInternationalDto openApiInternationalDto : list) {
                if (openApiInternationalDto.getAirport().equals("서울/김포")
                        && openApiInternationalDto.getInternationalIoType().equals("IN")) {
                    setList.add(openApiInternationalDto);
                }
            }
        }

        return setList;
    } // internationalSelect
    
    
    @GetMapping("/internationalInsert")
    public void internationalInsert() {
        Date date = new Date();
        date.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strDate = sdf.format(date).toString();
        String getDate = scheduleDao.dateFormat(strDate);
        log.info("getDate:" + getDate);
        List<OpenApiInternationalDto> list2 = InternationalSelectSort(scheduleDao.getAllInternational(2, getDate), "");
        System.out.print("list2" + list2);

        for (OpenApiInternationalDto dto : list2) {
            scheduleService.internationalInsert(dto);
        }
        System.out.println("api list size: " + list2.size());
//      return "insert 된 행 개수 " + list2.size();
    } // international
    
    
    @PostMapping("/showInternationalBySelectedDate")
    @ResponseBody 
    public List<OpenApiInternationalDto> showInternationalBySelectedDate(@RequestParam String date,
            @RequestParam String io) {

        String getDate = scheduleDao.dateFormat(date);
        List<OpenApiInternationalDto> list = InternationalSelectSort(scheduleDao.getAllInternational(2, getDate), io);
        log.info(" showInternationalBySelectedDate  확인 테스트 ");
        return list; // [{}, {}, {}]
    }// showInternational
    
    
    @GetMapping("/internationalUpdate")
    public ResponseEntity<String> internationalUpdate() {
        Date date = new Date();
        date.getDate(); // 현재시간
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strDate = sdf.format(date).toString();
        String getDate = scheduleDao.dateFormat(strDate);

        List<OpenApiInternationalDto> apiList = InternationalSelectSort(scheduleDao.getAllInternational(2, getDate), ""); // ->
                                                                                                                // api에서
                                                                                                                // 바로
                                                                                                                // 받아오는
                                                                                                                // 메소드

        List<OpenApiInternationalDto> insertList = scheduleService.selectCountInternational(0, apiList);
        List<OpenApiInternationalDto> updateList = scheduleService.selectCountInternational(1, apiList);
        
        int updateResult = 0;
        int insertResult = 0;
        if (updateList.size() != 0)      updateResult = scheduleService.internationalUpdate(updateList);
          
        if (insertList.size() != 0) {
            for (OpenApiInternationalDto dto : insertList) {
                insertResult = insertResult+  scheduleService.internationalInsert(dto);      
            }
          
        }
        
        String resultStr = "";
        if(updateResult !=0 || insertResult != 0) resultStr = "alert(\'"+updateResult+"행, "+insertResult+" 행 삽입 변경 성공.\');";
        else resultStr = "alert('업데이트할 스케쥴이 없습니다.');";
        
//      else resultStr = "alert('개판이구만');";
                
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html; charset=utf-8");
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append(resultStr);
        sb.append("history.back(); ");
        sb.append("</script>");
        return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);


    }

    // ====================================== international end ===========================================================

    
    
    // ====================================== realtime start ===========================================================
    

    @PostMapping("/showRealTime") // ajax 전체 항목 DB에서 가지고 오기 해수
    @ResponseBody
    public List<OpenApiRealTimeFlightDto> showRealTimeEnable() {
        List<OpenApiRealTimeFlightDto> list = scheduleService.showRealTimeEnable();
        return list;
    }

    @PostMapping("/showRealTimeUpdate") // ajax update항목 가저오기
    @ResponseBody
    public List<OpenApiRealTimeFlightDto> showRealTimeUpdate() {

        // 자료 가져오고 update enable2 가져오고 enable 2인거 1로바꾸기
        List<OpenApiRealTimeFlightDto> list = scheduleService.selectUpdatedRealTime();

        if (list.size() != 0)
            scheduleService.selectUpdatedRealTimeInitialization(list);
        return list;
    }

    @PostMapping("/showRealTimeSearch") // 모든 데이터중 필요한data select하기
    @ResponseBody
    public List<OpenApiRealTimeFlightDto> showRealTimeSearch(String text, String find) {
        log.info("text : " + text + ", find : " + find);
        List<OpenApiRealTimeFlightDto> list = scheduleService.selectSearchRealTime(text, find);

        return list;
    }
    
    @GetMapping("/flightStatus") 
    public String filghtStatus() {
        
        return "schedule/flightStatus";
    }
    
    @PostMapping("/flightStatus") // 모든 데이터중 필요한data select하기
    @ResponseBody
    public Map<String, List<AirplnByGateVo>> filghtStatusPost(Model model) {
        System.out.println("filghtStatusPost>_<");
        
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        String time = sdf.format (System.currentTimeMillis());
        List<OpenApiRealTimeFlightDto> listOut = scheduleService.getNowGate(time, "O");
        List<OpenApiRealTimeFlightDto> listIn = scheduleService.getNowGate(time, "I");
        List<AirplnByGateVo> gateListOut = new ArrayList<AirplnByGateVo>();
        List<AirplnByGateVo> gateListIn  = new ArrayList<AirplnByGateVo>();
        for(OpenApiRealTimeFlightDto openApiRealTimeFlightDto :listOut ) {
            //처음에 한번만
            System.out.println(openApiRealTimeFlightDto);
            AirplnByGateVo airplnByGateVo = scheduleService.findGate(openApiRealTimeFlightDto.getAirFln(), openApiRealTimeFlightDto.getStd());
            System.out.println(airplnByGateVo);
            System.out.println(openApiRealTimeFlightDto.getRmkEng());
            if(openApiRealTimeFlightDto.getRmkKor() != null)  airplnByGateVo.setGateState(openApiRealTimeFlightDto.getRmkKor());
            airplnByGateVo.setAirNumber(scheduleService.getFlightNum(openApiRealTimeFlightDto.getArrivedKor()));
            gateListOut.add(airplnByGateVo);
        }
        for(OpenApiRealTimeFlightDto openApiRealTimeFlightDto :listIn ) {
            //처음에 한번만
            AirplnByGateVo airplnByGateVo = scheduleService.findGate(openApiRealTimeFlightDto.getAirFln(), openApiRealTimeFlightDto.getStd());
            if(openApiRealTimeFlightDto.getRmkKor()!=null)  airplnByGateVo.setGateState(openApiRealTimeFlightDto.getRmkKor());
            airplnByGateVo.setAirNumber(scheduleService.getFlightNum(openApiRealTimeFlightDto.getBoardingKor()));
            gateListIn.add(airplnByGateVo);
        }
        
        Map<String, List<AirplnByGateVo>> map = new HashMap<>();
        map.put("gateListOut", gateListOut);
        map.put("gateListIn", gateListIn);
        
        
        return map;
    }
} // controller
