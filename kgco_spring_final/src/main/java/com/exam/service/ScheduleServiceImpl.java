package com.exam.service;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.AirplnByGateVo;
import com.exam.domain.OpenApiDomesticDto;
import com.exam.domain.OpenApiInternationalDto;
import com.exam.domain.OpenApiRealTimeFlightDto;
import com.exam.mapper.ScheduleMapper;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	ScheduleMapper mapper;

	
	// domestic ------------------------------
    @Override
    public int domesticInsert(OpenApiDomesticDto domesticDto) {
        return mapper.domesticInsert(domesticDto);
    }

    @Override
    public List<OpenApiDomesticDto> selectAllDomestic() {
        return mapper.selectAllDomestic();
    }

    @Override
	public void updateGateSet(AirplnByGateVo airplnByGateVo) {
		mapper.updateGateSet(airplnByGateVo);
		
	}

	@Override
    public int deleteAllDomestic() {
        return mapper.deleteAllDomestic();
    }

    @Override
    public List<OpenApiDomesticDto> domesticSelectbyDateIo(String date, String io) {

        List<OpenApiDomesticDto> list = mapper.domesticSelectbyDate(date);
        List<OpenApiDomesticDto> setList = new ArrayList<OpenApiDomesticDto>();

        if (io.equals("OUT")) {
            for (OpenApiDomesticDto dto : list) {
                if (dto.getStartcity().equals("서울/김포")) {
                    setList.add(dto);
                }
            }

        } else if (io.equals("IN")) {
            for (OpenApiDomesticDto dto : list) {
                if (dto.getArrivalcity().equals("서울/김포")) {
                    setList.add(dto);
                }
            }
        }
        return setList;
    }

    @SuppressWarnings("null")
    @Override
    public int domesticUpdate(List<OpenApiDomesticDto> list) {
        int result=0;
        for (OpenApiDomesticDto apiDto : list) {  

            String apiDomesticNum = "";
            String apiStartTime = "";
            String apiArrivalTime = "";

            String apiDmMon = "";
            String apiDmTue = "";
            String apiDmWed = "";
            String apiDmThu = "";
            String apiDmFri = "";
            String apiDmSat = "";
            String apiDmSun = "";

            String apiDmStdate = "";
            String apiDmEddate = "";

            OpenApiDomesticDto dbDto = mapper.selectDomesticByKey(apiDto);

            if (apiDto.getDomesticNum() != null)
                apiDomesticNum = apiDto.getDomesticNum();

            if (apiDto.getDomesticStartTime() != null)
                apiStartTime = apiDto.getDomesticStartTime();
            if (apiDto.getDomesticArrivalTime() != null)
                apiArrivalTime = apiDto.getDomesticArrivalTime();

            if (apiDto.getDomesticMon() != null)
                apiDmMon = apiDto.getDomesticMon();
            if (apiDto.getDomesticTue() != null)
                apiDmTue = apiDto.getDomesticTue();
            if (apiDto.getDomesticWed() != null)
                apiDmWed = apiDto.getDomesticWed();
            if (apiDto.getDomesticThu() != null)
                apiDmThu = apiDto.getDomesticThu();
            if (apiDto.getDomesticFri() != null)
                apiDmFri = apiDto.getDomesticFri();
            if (apiDto.getDomesticSat() != null)
                apiDmSat = apiDto.getDomesticSat();
            if (apiDto.getDomesticSun() != null)
                apiDmSun = apiDto.getDomesticSun();

            if (apiDto.getDomesticStdate() != null)
                apiDmStdate = apiDto.getDomesticStdate();
            if (apiDto.getDomesticEddate() != null)
                apiDmEddate = apiDto.getDomesticEddate();

            /////////////////////////////////////////////////////////////////////
            String chkdDomesticNum = "";
            String chkdStartTime = "";
            String chkdArrivalTime = "";

            String chkdDmMon = "";
            String chkdDmTue = "";
            String chkdDmWed = "";
            String chkdDmThu = "";
            String chkdDmFri = "";
            String chkdDmSat = "";
            String chkdDmSun = "";

            String chkdDmStdate = "";
            String chkdDmEddate = "";
            if (dbDto.getDomesticNum() != null)
                chkdDomesticNum = dbDto.getDomesticNum();

            if (dbDto.getDomesticStartTime() != null)
                chkdStartTime = dbDto.getDomesticStartTime();
            if (dbDto.getDomesticArrivalTime() != null)
                chkdArrivalTime = dbDto.getDomesticArrivalTime();
            if (dbDto.getDomesticMon() != null)
                chkdDmMon = dbDto.getDomesticMon();
            if (dbDto.getDomesticTue() != null)
                chkdDmTue = dbDto.getDomesticTue();
            if (dbDto.getDomesticWed() != null)
                chkdDmWed = dbDto.getDomesticWed();
            if (dbDto.getDomesticThu() != null)
                chkdDmThu = dbDto.getDomesticThu();
            if (dbDto.getDomesticFri() != null)
                chkdDmFri = dbDto.getDomesticFri();
            if (dbDto.getDomesticSat() != null)
                chkdDmSat = dbDto.getDomesticSat();
            if (dbDto.getDomesticSun() != null)
                chkdDmSun = dbDto.getDomesticSun();

            if (dbDto.getDomesticStdate() != null)
                chkdDmStdate = dbDto.getDomesticStdate();
            if (dbDto.getDomesticEddate() != null)
                chkdDmEddate = dbDto.getDomesticEddate();

            if (!(chkdDomesticNum.equals(apiDomesticNum) && chkdStartTime.equals(apiStartTime)
                    && chkdArrivalTime.equals(apiArrivalTime) && chkdDmMon.equals(apiDmMon)
                    && chkdDmTue.equals(apiDmTue) && chkdDmWed.equals(apiDmWed) && chkdDmThu.equals(apiDmThu)
                    && chkdDmFri.equals(apiDmFri) && chkdDmSat.equals(apiDmSat) && chkdDmSun.equals(apiDmSun)
                    && chkdDmStdate.equals(apiDmStdate) && chkdDmEddate.equals(apiDmEddate))) { 

                mapper.domesticDisableUpdate(dbDto);
                result=mapper.domesticInsert(apiDto);
 
            }

        }
        return result;

    } //

    
    @Override
    public List<OpenApiDomesticDto> selectCountDomestic(int i, List<OpenApiDomesticDto> list) {
        List<OpenApiDomesticDto> devidedList = new ArrayList<OpenApiDomesticDto>();
        for (OpenApiDomesticDto domesticDto : list) {
            if (i == mapper.selectCountDomesticByKey(domesticDto)) {
                
                devidedList.add(domesticDto);
            }

        }
        return devidedList;
    }

// international ----------------------------
    @Override
    public int internationalInsert(OpenApiInternationalDto internationalDto) {
        return mapper.internationalInsert(internationalDto);
    }

    @Override
    public List<OpenApiInternationalDto> selectAllInternational() {
        return mapper.selectAllInternational();
    }

    @Override
    public int deleteAllInternational() {
        return mapper.deleteAllDomestic();
    }

    @Override
    public List<OpenApiInternationalDto> internationalSelectbyDateIo(String date, String io) {

        List<OpenApiInternationalDto> list = mapper.internationalSelectbyDate(date);
        List<OpenApiInternationalDto> setList = new ArrayList<OpenApiInternationalDto>();
        if (io.equals("OUT")) {
            for (OpenApiInternationalDto dto : list) {
                if (dto.getInternationalIoType().equals("OUT")) {
                    setList.add(dto);
                }
            }
        } else if (io.equals("IN")) {
            for (OpenApiInternationalDto dto : list) {
                if (dto.getInternationalIoType().equals("IN")) {
                    setList.add(dto);
                }
            }
        }
        return setList;

    }

    @SuppressWarnings("null")
    @Override
    public int internationalUpdate(List<OpenApiInternationalDto> list) {
        int result=0;
        for(OpenApiInternationalDto apiDto:list) {
    
            String apiAirport="";
            String apiCity="";
            String apiInternationalNum="";
            String apiInternationalTime="";
            String apiIntlMon="";
            String apiIntlTue="";
            String apiIntlWed="";
            String apiIntlThu="";
            String apiIntlFri="";
            String apiIntlSat="";
            String apiIntlSun="";
            String apiInternationalStdate="";
            String apiInternationalEddate="";
            
            OpenApiInternationalDto dbDto=mapper.selectInternationalByKey(apiDto);
            
            if(apiDto.getAirport() !=null) apiAirport = apiDto.getAirport();
            if(apiDto.getCity() !=null) apiCity = apiDto.getCity();
            if(apiDto.getInternationalNum() !=null) apiInternationalNum= apiDto.getInternationalNum();
            if(apiDto.getInternationalTime() !=null) apiInternationalTime=apiDto.getInternationalTime();
            if(apiDto.getInternationalMon() !=null) apiIntlMon=apiDto.getInternationalMon();
            if(apiDto.getInternationalTue() !=null) apiIntlTue=apiDto.getInternationalTue();
            if(apiDto.getInternationalWed() !=null) apiIntlWed=apiDto.getInternationalWed();
            if(apiDto.getInternationalThu() !=null) apiIntlThu=apiDto.getInternationalThu();
            if(apiDto.getInternationalFri() !=null) apiIntlFri=apiDto.getInternationalFri();
            if(apiDto.getInternationalSat() !=null) apiIntlSat=apiDto.getInternationalSat();
            if(apiDto.getInternationalSun() !=null) apiIntlSun=apiDto.getInternationalSun();
            if(apiDto.getInternationalStdate() !=null) apiInternationalStdate = apiDto.getInternationalStdate();
            if(apiDto.getInternationalEddate() !=null) apiInternationalEddate = apiDto.getInternationalEddate();

            String chkdAirport="";
            String chkdCity="";
            String chkdInternationalNum="";
            String chkdInternationalTime="";
            String chkdIntlMon="";
            String chkdIntlTue="";
            String chkdIntlWed="";
            String chkdIntlThu="";
            String chkdIntlFri="";
            String chkdIntlSat="";
            String chkdIntlSun="";
            String chkdInternationalStdate="";
            String chkdInternationalEddate="";
            
            if(dbDto.getAirport() != null)          chkdAirport = dbDto.getAirport();
            if(dbDto.getCity() != null)             chkdCity = dbDto.getCity();
            if(dbDto.getInternationalNum() != null) chkdInternationalNum = dbDto.getInternationalNum();
            if(dbDto.getInternationalTime() != null)chkdInternationalTime = dbDto.getInternationalTime();
            if(dbDto.getInternationalMon() != null) chkdIntlMon = dbDto.getInternationalMon();
            if(dbDto.getInternationalTue() != null) chkdIntlTue = dbDto.getInternationalTue();
            if(dbDto.getInternationalWed() != null) chkdIntlWed = dbDto.getInternationalWed();
            if(dbDto.getInternationalThu() != null) chkdIntlThu = dbDto.getInternationalThu();
            if(dbDto.getInternationalFri() != null) chkdIntlFri = dbDto.getInternationalFri();
            if(dbDto.getInternationalSat() != null) chkdIntlSat = dbDto.getInternationalSat();
            if(dbDto.getInternationalSun() != null) chkdIntlSun = dbDto.getInternationalSun();
            if(dbDto.getInternationalStdate() != null)  chkdInternationalStdate = dbDto.getInternationalStdate();
            if(dbDto.getInternationalEddate() != null)  chkdInternationalEddate = dbDto.getInternationalEddate();
            
            
            if(     !( apiAirport.equals(chkdAirport) && 
                    apiCity.equals(chkdCity) && 
                    apiInternationalNum.equals(chkdInternationalNum) && 
                    apiInternationalTime.equals(chkdInternationalTime) && 
                    apiIntlMon.equals(chkdIntlMon) && 
                    apiIntlTue.equals(chkdIntlTue) && 
                    apiIntlWed.equals(chkdIntlWed) && 
                    apiIntlThu.equals(chkdIntlThu) && 
                    apiIntlFri.equals(chkdIntlFri) && 
                    apiIntlSat.equals(chkdIntlSat) && 
                    apiIntlSun.equals(chkdIntlSun) && 
                    apiInternationalStdate.equals(chkdInternationalStdate) && 
                    apiInternationalEddate.equals(chkdInternationalEddate) )
                    ) {
                mapper.internationalDisableUpdate(dbDto);
                result=mapper.internationalInsert(apiDto);
            
            }
           
        }
        return result;
    }

    @Override
    public List<OpenApiInternationalDto> selectCountInternational(int i, List<OpenApiInternationalDto> list) {

        List<OpenApiInternationalDto> devidedList = new ArrayList<OpenApiInternationalDto>();
        for (OpenApiInternationalDto internationalDto : list) {
            if (i == mapper.selectCountInternationalByKey(internationalDto)) {

                devidedList.add(internationalDto);
            }

        }

        return devidedList;
    }

    // real-time ---------- 
    @Override
    public List<OpenApiRealTimeFlightDto> realtimeSelectOrderbyEtd(String io) {
        return mapper.realtimeSelectOrderbyEtd(io);
        
    }
    
    @Override
    public List<OpenApiRealTimeFlightDto> selectSearchRealTime(String text, String find) {
	    List<OpenApiRealTimeFlightDto> list = mapper.selectSearchRealTime(text, find);
	    for(OpenApiRealTimeFlightDto op : list) System.out.println(op.toString());
        return list;
    }


    @Override
    public List<OpenApiRealTimeFlightDto> showRealTimeEnable() {
        // TODO Auto-generated method stub
        return mapper.showRealTimeEnable();
    }

    
    @Override
    public void realTimeFlightDelete() {
        mapper.realTimeFlightDelete();
    }
    
    @Override
    public void realtimeInsert(OpenApiRealTimeFlightDto realtimeDto) {
        mapper.realTimeFlightInsert(realtimeDto);
    }

    @Override
    public void realTimeFlightInsert(List<OpenApiRealTimeFlightDto> list) {
        
        for(OpenApiRealTimeFlightDto openApiRealTimeFlightDto : list) {
            
            mapper.realTimeFlightInsert(openApiRealTimeFlightDto);
         }
       
    }
    @SuppressWarnings("null")
    @Override
    public List<OpenApiRealTimeFlightDto> realTimeFlightUpdate(List<OpenApiRealTimeFlightDto> list) {
       List<OpenApiRealTimeFlightDto> resultList = new ArrayList<OpenApiRealTimeFlightDto>();
        
        for(OpenApiRealTimeFlightDto openApiRealTimeFlightDto : list) { //list api
            OpenApiRealTimeFlightDto oartf = mapper.selectCountRealTimeUpdateCheck(openApiRealTimeFlightDto); //현제 유의미한 값 들고오기
            String apiEtd = "";
            String apiRmkEng = "";
            String apiAirport = "";
            
            String checketd = "";
            String checkrmkEng = "";
            String checkairPort = "";

            if (openApiRealTimeFlightDto.getEtd() != null)
                apiEtd = openApiRealTimeFlightDto.getEtd();
            if (openApiRealTimeFlightDto.getRmkEng() != null)
                apiRmkEng = openApiRealTimeFlightDto.getRmkEng();
            if (openApiRealTimeFlightDto.getAirport() != null)
                apiAirport = openApiRealTimeFlightDto.getAirport();

            if (oartf.getEtd() != null)
                checketd = oartf.getEtd();
            if (oartf.getRmkEng() != null)
                checkrmkEng = oartf.getRmkEng();
            if (oartf.getAirport() != null)
                checkairPort = oartf.getAirport();
            
            if(!(checketd.equals(apiEtd) &&
                 checkrmkEng.equals(apiRmkEng) &&
                 checkairPort.equals(apiAirport))) {
                
                mapper.realTimeFlightDisableUpdate(oartf);              //비교해서 원래 enable 되잇던 녀석을 disable
                mapper.realTimeFlightInsert(openApiRealTimeFlightDto);  //비교해서 바뀌는 api enable

                resultList.add(openApiRealTimeFlightDto);
            }
         }
        return resultList;
    }
    @Override
    public List<OpenApiRealTimeFlightDto> selectCountRealTime(int i, List<OpenApiRealTimeFlightDto> apiList) {  //insert
        List<OpenApiRealTimeFlightDto> selectRealTime = new ArrayList<OpenApiRealTimeFlightDto>();
        
        for(OpenApiRealTimeFlightDto openApiRealTimeFlightDto : apiList) {
           if(i ==  mapper.selectCountRealTime(openApiRealTimeFlightDto)) {
               selectRealTime.add(openApiRealTimeFlightDto);
           }
        }
        return selectRealTime;
    }

    @Override
    public List<OpenApiRealTimeFlightDto> selectUpdatedRealTime() {
        List<OpenApiRealTimeFlightDto> list = mapper.selectUpdatedRealTime();
        return mapper.selectUpdatedRealTime();
    }

    @Override
    public void selectUpdatedRealTimeInitialization(List<OpenApiRealTimeFlightDto> list) {
        for(OpenApiRealTimeFlightDto openApiRealTimeFlightDto : list) mapper.selectUpdatedRealTimeInitialization(openApiRealTimeFlightDto);
    }
    
  //airplnb----------------
    @Override
    public void airplnbByGateInsert(AirplnByGateVo airplnByGateVo) {
        mapper.airplnbByGateInsert(airplnByGateVo);
    }

    @Override   //결항
	public void setCansellation(String key1, String key2) {
		// TODO Auto-generated method stub
		boolean check = false;
		List<OpenApiRealTimeFlightDto> list = mapper.selectUpdatedRealTime();
		for(OpenApiRealTimeFlightDto openApiRealTimeFlightDto : list) {
			if(openApiRealTimeFlightDto.getAirFln().equals(key1) && openApiRealTimeFlightDto.getStd().equals(key2)) {
				check = true;
				mapper.setCansellation(openApiRealTimeFlightDto);
			}
		}
		if(check == false) {
			OpenApiRealTimeFlightDto openApiRealTimeFlightDto = mapper.selectCancellation(key1, key2);
			mapper.setCansellation(openApiRealTimeFlightDto);
		}
		
	}

	@Override
	public void setcancellationCancel(String key1, String key2) {
		// TODO Auto-generated method stub
		OpenApiRealTimeFlightDto openApiRealTimeFlightDto = mapper.selectCancellationCancel(key1, key2);
		mapper.updatecancellation(openApiRealTimeFlightDto);
	}


    @Override
    public void airplnbByGateDelete() {
        mapper.airplnbByGateDelete();
        
    }

    
    
    @Override
    public String getFlightNum(String city) {
        // TODO Auto-generated method stub
        return mapper.getFlightNum(city);
    }

    @Override
    public List<OpenApiRealTimeFlightDto> getNowGate(String time, String string) {
        // TODO Auto-generated method stub
        return  mapper.getNowGate(time, string);
    }



    @Override
    public AirplnByGateVo findGate(String airFln, String std) {
        // TODO Auto-generated method stub
        return mapper.findGate(airFln, std);
    }




}
