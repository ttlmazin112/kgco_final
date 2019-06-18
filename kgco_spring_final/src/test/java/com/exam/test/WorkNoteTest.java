package com.exam.test;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exam.domain.EmployeeVo;
import com.exam.service.EmployeeService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class WorkNoteTest {
	
	@Setter(onMethod_ = {@Autowired} )
	private EmployeeService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void updateByWorkNote() {
		// 오늘날짜로 직원 출결 임의 업데이트 
//		Date curDate = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String date = sdf.format(curDate);
		List<EmployeeVo> eidList = service.getAllEid();
	
		String date = "2019-06-16";
		// 난수 생성
		Set<Integer> set = new HashSet<>();

		int ran = 0; // 랜던값변수
		Random r = new Random();
		while (true) {
			if (set.size() >= 2968) {
				break;
			}
			ran = r.nextInt(2968) + 1;
			set.add(ran);
		}
		Map<Integer, Boolean> map = new HashMap<>();
		Iterator<Integer> iter = set.iterator();
		while (iter.hasNext()) {
			map.put(iter.next(), false);
		}

		Set<Integer> keySet = map.keySet();
		Iterator<Integer> keyIter = keySet.iterator();
		List<Integer> numList = new ArrayList<>();
		boolean isUsed = false;
		int num = 0;

		outerloop: while (keyIter.hasNext()) {
			while (true) {
				num = r.nextInt(2968) + 1;
				isUsed = map.get(num);
				if (isUsed == true) {
					continue;
				}
				map.put(num, true); // true 사용함
				numList.add(num);
				if (numList.size() == 2968) {
					break outerloop;
				}
			}
		}
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		String eid = "";
		String eidAndWorkNote = "";
		// 직원 상태 임의 편성
		for (int i = 0; i < numList.size(); i++) {
			if (i < 100) {
				eidAndWorkNote = "abse";
			} else if (i < 330) {
				eidAndWorkNote = "late";
			} else if (i < 553) {
				eidAndWorkNote = "va";
			} else {
				eidAndWorkNote = "nor";
			}
			
		// 실제 사용될 시  메인 페이지와 함께 전직원 데이터 non으로 업데이트
			
//			for (int i = 0; i < numList.size(); i++) {
//				eidAndWorkNote = "non";
//				}
				
			EmployeeVo empVo = eidList.get(numList.get(i) - 1);
			eid = empVo.getEid();
			obj.put(eid, eidAndWorkNote);
		}
		array.add(obj);
//		obj.put("FA000001", "nor");
//		array.add(obj);
//		for(Object o: array){ //출력
//			if ( o instanceof JSONObject ) {
//				System.out.println(o);
//		    }
//		    
//		}//for  

		String str = array.toJSONString();
		service.insertByWorkNote(date.toString(), str);

	}// updateByWorkNote
}
