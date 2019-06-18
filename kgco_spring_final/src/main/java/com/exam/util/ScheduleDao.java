package com.exam.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.exam.domain.OpenApiDomesticDto;
import com.exam.domain.OpenApiInternationalDto;
import com.exam.domain.OpenApiRealTimeFlightDto;

@Component
public class ScheduleDao {
    
    private final String DomesticUrl = "http://openapi.airport.co.kr/service/rest/FlightScheduleList/getDflightScheduleList?";
    private final String InternationalUrl = "http://openapi.airport.co.kr/service/rest/FlightScheduleList/getIflightScheduleList?";
    private final String RealTimeUrl = "http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList?";

    private final String ServiceKey = "7zHqnqCdK99PKFqmlVscqsSDVGdosPUVUJeTJOgIGYofSf5s7O7lxVD9P2vBVQp4iEXg6ojJtFaJaMEUUewdyQ%3D%3D";
    
    public String findUrl(int url, String date) {
        String makeUrl = "";
        if (url == 1) {
            makeUrl = DomesticUrl + "ServiceKey=" + ServiceKey + "&schDate=" + date;
        } else if (url == 2) {
            makeUrl = InternationalUrl + "ServiceKey=" + ServiceKey + "&schDate=" + date;
        } else if (url == 3) {
            makeUrl = RealTimeUrl + "ServiceKey=" + ServiceKey;
        }
        return makeUrl;
    }
    
    
 // db 들어가는 table 일치하리 (realtime)
    // 시간값 string으로 20190505 이런식으로 포멧하는 함수
    public String getApiTotalCnt(int iUrl, String date) {
        String path = findUrl(iUrl, date);
        URL url;
        URLConnection con = null;
        InputStream is = null;
        try {
            url = new URL(path);
            con = url.openConnection();
            is = con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        NodeList totalCNT = document.getElementsByTagName("totalCount");
        String totalCnt = "&numOfRows=" + totalCNT.item(0).getFirstChild().getNodeValue();

        return totalCnt;
    }//
    
    
    
    public String dateFormat(String date) {

        String dateAll[] = date.split("-");
        String dateTrim = "";
        for (int i = 0; i < dateAll.length; i++) {
            dateTrim += dateAll[i];
        }
        System.out.println(dateTrim);
        return dateTrim;
    }//dateFormat    
    
    
    public List<OpenApiDomesticDto> getAllDomestic(int iUrl, String date) {

        List<OpenApiDomesticDto> list = new ArrayList<>();

        String totalCnt = getApiTotalCnt(iUrl, date);
        String setUrl = findUrl(iUrl, date);

        String urlPath = setUrl + totalCnt;
        URL url;
        URLConnection con = null;
        InputStream is = null;
        try {
            url = new URL(urlPath);
            con = url.openConnection();
            is = con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        NodeList openApiItems = document.getElementsByTagName("item");
        for (int i = 0; i < openApiItems.getLength(); i++) {
            OpenApiDomesticDto openApiDomesticDto = new OpenApiDomesticDto();
            Node openApiItem = openApiItems.item(i);

            NodeList childNodeList = openApiItem.getChildNodes();

            for (int j = 0; j < childNodeList.getLength(); j++) {
                Node childNode = childNodeList.item(j);

                if (childNode.getNodeName().equals("airlineKorean")) {
                    String AirlineKorean = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setAirlineKorean(AirlineKorean);
                } else if (childNode.getNodeName().equals("arrivalcity")) {
                    String Arrivalcity = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setArrivalcity(Arrivalcity);
                } else if (childNode.getNodeName().equals("startcity")) {
                    String Startcity = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setStartcity(Startcity);
                } else if (childNode.getNodeName().equals("domesticArrivalTime")) {
                    String domesticArrivalTime = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticArrivalTime(domesticArrivalTime);
                } else if (childNode.getNodeName().equals("domesticEddate")) {
                    String domesticEddate = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticEddate(domesticEddate);
                } else if (childNode.getNodeName().equals("domesticFri")) {
                    String domesticFri = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticFri(domesticFri);
                } else if (childNode.getNodeName().equals("domesticMon")) {
                    String domesticMon = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticMon(domesticMon);
                } else if (childNode.getNodeName().equals("domesticNum")) {
                    String domesticNum = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticNum(domesticNum);
                } else if (childNode.getNodeName().equals("domesticSat")) {
                    String domesticSat = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticSat(domesticSat);
                } else if (childNode.getNodeName().equals("domesticStdate")) {
                    String domesticStdate = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticStdate(domesticStdate);
                } else if (childNode.getNodeName().equals("domesticSun")) {
                    String domesticSun = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticSun(domesticSun);
                } else if (childNode.getNodeName().equals("domesticThu")) {
                    String domesticThu = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticThu(domesticThu);
                } else if (childNode.getNodeName().equals("domesticTue")) {
                    String domesticTue = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticTue(domesticTue);
                } else if (childNode.getNodeName().equals("domesticWed")) {
                    String domesticWed = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticWed(domesticWed);
                } else if (childNode.getNodeName().equals("domesticStartTime")) {
                    String domesticStartTime = childNode.getFirstChild().getNodeValue();
                    openApiDomesticDto.setDomesticStartTime(domesticStartTime);
                }
            }

            if (openApiDomesticDto.getAirlineKorean().equals("대한항공")) {
                list.add(openApiDomesticDto);
            }
        }
        return list;
    } //getAllDomestic
    
//====================================== international start ==================================================== 
    
    public List<OpenApiInternationalDto> getAllInternational(int iUrl, String date){
        List<OpenApiInternationalDto> list = new ArrayList<>();

        String totalCnt = getApiTotalCnt(iUrl, date);
        String setUrl = findUrl(iUrl, date);

        String urlPath = setUrl + totalCnt;
        URL url;
        URLConnection con = null;
        InputStream is = null;
        try {
            url = new URL(urlPath);
            con = url.openConnection();
            is = con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        NodeList openApiItems = document.getElementsByTagName("item");
        for (int i = 0; i < openApiItems.getLength(); i++) {
            OpenApiInternationalDto openApiInternationalDto = new OpenApiInternationalDto();
            Node openApiItem = openApiItems.item(i);

            NodeList childNodeList = openApiItem.getChildNodes();

            for (int j = 0; j < childNodeList.getLength(); j++) {
                Node childNode = childNodeList.item(j);

                if (childNode.getNodeName().equals("airlineKorean")) {
                    String AirlineKorean = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setAirlineKorean(AirlineKorean);
                } else if (childNode.getNodeName().equals("airport")) {
                    String Airport = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setAirport(Airport);
                } else if (childNode.getNodeName().equals("city")) {
                    String City = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setCity(City);
                } else if (childNode.getNodeName().equals("internationalEddate")) {
                    String InternationalEddate = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalEddate(InternationalEddate);
                } else if (childNode.getNodeName().equals("internationalFri")) {
                    String InternationalFri = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalFri(InternationalFri);
                } else if (childNode.getNodeName().equals("internationalIoType")) {
                    String InternationalIoType = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalIoType(InternationalIoType);
                } else if (childNode.getNodeName().equals("internationalMon")) {
                    String InternationalMon = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalMon(InternationalMon);
                } else if (childNode.getNodeName().equals("internationalNum")) {
                    String InternationalNum = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalNum(InternationalNum);
                } else if (childNode.getNodeName().equals("internationalSat")) {
                    String InternationalSat = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalSat(InternationalSat);
                } else if (childNode.getNodeName().equals("internationalStdate")) {
                    String InternationalStdate = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalStdate(InternationalStdate);
                } else if (childNode.getNodeName().equals("internationalSun")) {
                    String InternationalSun = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalSun(InternationalSun);
                } else if (childNode.getNodeName().equals("internationalThu")) {
                    String InternationalThu = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalThu(InternationalThu);
                } else if (childNode.getNodeName().equals("internationalTime")) {
                    String InternationalTime = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalTime(InternationalTime);
                } else if (childNode.getNodeName().equals("internationalTue")) {
                    String InternationalTue = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalTue(InternationalTue);
                } else if (childNode.getNodeName().equals("internationalWed")) {
                    String InternationalWed = childNode.getFirstChild().getNodeValue();
                    openApiInternationalDto.setInternationalWed(InternationalWed);
                }

            }

            if (openApiInternationalDto.getAirlineKorean().equals("대한항공")) {
                list.add(openApiInternationalDto);
            }

        }

        return list;
    }
    
    public List<OpenApiRealTimeFlightDto> getAllRealTimeFlight(int iUrl, String date) {

        List<OpenApiRealTimeFlightDto> list = new ArrayList<>();

        String totalCnt = getApiTotalCnt(iUrl, date);
        String setUrl = findUrl(iUrl, date);

        String urlPath = setUrl + totalCnt;
        URL url;
        URLConnection con = null;
        InputStream is = null;
        try {
            url = new URL(urlPath);
            con = url.openConnection();
            is = con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        NodeList nodeList = document.getElementsByTagName("item");

        for (int i = 0; i < nodeList.getLength(); i++) {
            OpenApiRealTimeFlightDto openApiRealTimeFlightDto = new OpenApiRealTimeFlightDto();
            Node node = nodeList.item(i);

            NodeList childNodeList = node.getChildNodes();

            for (int j = 0; j < childNodeList.getLength(); j++) {
                Node childNode = childNodeList.item(j);

                if (childNode.getNodeName().equals("airFln")) {
                    String airFln = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setAirFln(airFln);
                } else if (childNode.getNodeName().equals("airlineEnglish")) {
                    String airEng = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setAirlineEnglish(airEng);
                } else if (childNode.getNodeName().equals("airlineKorean")) {
                    String airKor = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setAirlineKorean(airKor);
                } else if (childNode.getNodeName().equals("airport")) {
                    String airport = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setAirport(airport);
                } else if (childNode.getNodeName().equals("arrivedEng")) {
                    String arrivedEng = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setArrivedEng(arrivedEng);
                } else if (childNode.getNodeName().equals("arrivedKor")) {
                    String arrivedKor = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setArrivedKor(arrivedKor);
                } else if (childNode.getNodeName().equals("boardingEng")) {
                    String boardingEng = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setBoardingEng(boardingEng);
                } else if (childNode.getNodeName().equals("boardingKor")) {
                    String boardingKor = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setBoardingKor(boardingKor);
                } else if (childNode.getNodeName().equals("city")) {
                    String city = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setCity(city);
                } else if (childNode.getNodeName().equals("etd")) {
                    String etd = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setEtd(etd);
                } else if (childNode.getNodeName().equals("io")) {
                    String io = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setIo(io);
                } else if (childNode.getNodeName().equals("line")) {
                    String line = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setLine(line);
                } else if (childNode.getNodeName().equals("rmkEng")) {
                    String rmkEng = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setRmkEng(rmkEng);
                } else if (childNode.getNodeName().equals("rmkKor")) {
                    String rmkKor = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setRmkKor(rmkKor);
                } else if (childNode.getNodeName().equals("std")) {
                    String std = childNode.getFirstChild().getNodeValue();
                    openApiRealTimeFlightDto.setStd(std);
                }
            } 

            if (openApiRealTimeFlightDto.getAirlineKorean().equals("대한항공") && openApiRealTimeFlightDto.getAirport().equals("GMP")) {
                list.add(openApiRealTimeFlightDto);
            }
        }
        return list;
    }
    
    public String gateAlphabet(int count) {
        String gate="";
        if(count==1)      gate="A"; 
        else if(count==2) gate="B";
        else if(count==3) gate="C";
        else if(count==4) gate="D";
        else if(count==5) gate="E";
        else if(count==6) gate="F";
        else if(count==7) gate="G";
        else if(count==8) gate="H";
        return gate;
    }
}
