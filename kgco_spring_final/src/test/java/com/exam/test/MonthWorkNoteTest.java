package com.exam.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exam.domain.EmployeeVo;
import com.exam.domain.EmployeeWorkNoteVO;
import com.exam.service.EmployeeService;
import com.exam.util.AttendDao;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MonthWorkNoteTest {

	@Setter(onMethod_ = { @Autowired })
	private EmployeeService service;

	@Test
	public void MonthNote() {
		int year = 2019;
		int month = 4;
		String eid = "FA000001";
		// 오늘 날짜값 
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		int curDateNum = Integer.parseInt(sdf.format(curDate));
		
		Map<String,String> monthStateMap = new HashMap<String, String>();
		List<String> monthList = new ArrayList<String>(); // 한달치 리스트
		Map<String, Object> map = new HashMap<String, Object>();
		monthList = planning(year, month);
		
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		List<EmployeeWorkNoteVO> empWorkNote = service.getWorkNote();
		for (EmployeeWorkNoteVO vo : empWorkNote) {
			obj.put(vo.getDate(), vo.getEidAndWorkNote());
		}

		// String -> List<Map<String,Object>>
		JSONParser parser = new JSONParser();
		Object object = new Object();


		
		for(int i =1; i<=curDateNum; i++) {
		try {
			object = parser.parse(obj.get(monthList.get(i-1)).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		array = (JSONArray) object;
		List<Map<String, Object>> listMap = AttendDao.getListMapFromJsonArray(array);
		map = listMap.get(0); // 하루치 근무일지
		monthStateMap.put(monthList.get(i-1), (String) map.get(eid));

		}
		for(int i =1; i<=curDateNum; i++) {
			System.out.println(monthList.get(i-1)+":"+monthStateMap.get(monthList.get(i-1)));
		}
		
//		//Map 출력
//		for(String oneday: monthList) {
//			System.out.println(oneday+":"+monthStateMap.get(oneday));
//		}

	}// MonthNote

	public List<String> planning(int year, int month) {
		// 해당달에 대한 날짜 생성
		List<String> monthList = new ArrayList<String>();
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			for (int date = 1; date < 32; date++) {
				StringBuffer sb = new StringBuffer();
				sb.append(String.valueOf(year));
				sb.append("-");
				// 달
				if (month < 10) {
					sb.append("0");
					sb.append(String.valueOf(month));
					sb.append("-");
				} else {
					sb.append(String.valueOf(month));
					sb.append("-");
				}
				if (date < 10) {
					sb.append("0");
					sb.append(String.valueOf(date));
				} else {
					sb.append(String.valueOf(date));
				}
				monthList.add(sb.toString());
			} // for
		} else if (month == 2) {
			for (int date = 1; date < 28; date++) {
				StringBuffer sb = new StringBuffer();
				sb.append(String.valueOf(year));
				sb.append("-");
				// 달
				if (month < 10) {
					sb.append("0");
					sb.append(String.valueOf(month));
					sb.append("-");
				} else {
					sb.append(String.valueOf(month));
					sb.append("-");
				}
				if (date < 10) {
					sb.append("0");
					sb.append(String.valueOf(date));
				} else {
					sb.append(String.valueOf(date));
				}
				monthList.add(sb.toString());
			} // for
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			for (int date = 1; date < 31; date++) {
				StringBuffer sb = new StringBuffer();
				sb.append(String.valueOf(year));
				sb.append("-");
				// 달
				if (month < 10) {
					sb.append("0");
					sb.append(String.valueOf(month));
					sb.append("-");
				} else {
					sb.append(String.valueOf(month));
					sb.append("-");
				}
				if (date < 10) {
					sb.append("0");
					sb.append(String.valueOf(date));
				} else {
					sb.append(String.valueOf(date));
				}
				monthList.add(sb.toString());
			} // for
		}
		return monthList;
	}// planning

}
