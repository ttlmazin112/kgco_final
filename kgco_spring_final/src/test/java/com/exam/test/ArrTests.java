package com.exam.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
public class ArrTests {
	@Setter(onMethod_ = {@Autowired} )
	private EmployeeService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void test() {
		
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
		
		System.out.println("set size : " + set.size());
		
		for (Integer num : set) {
			System.out.println("num : " + num);
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
		
		outerloop:
		while (keyIter.hasNext()) {
			while (true) {
				num = r.nextInt(2968) + 1;
				System.out.println("랜덤선택 num : " + num);
				
				isUsed = map.get(num);
				if (isUsed == true) {
					continue;
				}
				map.put(num, true); // true 사용함
				numList.add(num);
				
//				Collection<Boolean> col = map.values();
//				boolean isAllTrue = col.stream().allMatch(used -> used == true);
//				if (isAllTrue) {
//					break outerloop;
//				}
				
				if (numList.size() == 2968) {
					break outerloop;
				}
			}
		}
	

		String eid = null;
		String estate = null;
		
		List<EmployeeVo> employList = service.getAllEid();
			for (int i=0; i<numList.size(); i++) {
			if (i < 218) {
				estate = "abse";
			} else if (i < 330) {
				estate ="late";
			} else if (i < 553) {
				estate ="va";
			} else {
				break;
			}
			eid = employList.get(numList.get(i) - 1).getEid();
			service.updateEstate(eid, estate);

		}
		
		
		
		
		
		
//		for (int i = 0; i < 218; i++) {
//		estate = "abse";
//		eid = employVO.get(arr[i]).getEid();
//		int num = service.updateEstate(eid, estate);
//		System.out.println(i+"===>"+eid+","+estate+","+num);
//	}	
//	for(int y = 218; y<330; y++) {
//		estate ="late";
//		eid = employVO.get(arr[y]).getEid();
//		int num=service.updateEstate(eid, estate);
//		System.out.println(y+"===>"+eid+","+estate+","+num);
//	}//for
//	
//	for(int k=330; k<553; k++) {
//		estate ="va";
//		eid = employVO.get(arr[k]).getEid();
//		int num =service.updateEstate(eid, estate);
//		System.out.println(k+"===>"+eid+","+estate+","+num);
//	}//for
		
		
		
		
		/*
		String eid = null;
		String estate = null;
		int[] arr = new int[2968]; // 배열선언
		int ran = 0; // 랜던값변수
		boolean cheak;
		Random r = new Random();
		for (int j = 0; j < arr.length; j++) {
			ran = r.nextInt(2968) + 1;
			cheak = true;
			for (int k = 0; k < j; k++) {
				if (arr[j] == ran) {
					j--;
					cheak = false;
				}
			}
			if (cheak)
				arr[j] = ran;
		}
		
		for (int i=0; i<arr.length; i++) {
			System.out.println("arr["+i+"] = " + arr[i]);
		}
		*/
		
		
		
		
		/*
		List<EmployeeVo> employList = service.getAllEid();
		Random r = new Random();
		
		for (EmployeeVo empVO: employList) {
			int ranNum = r.nextInt(100) + 1;
			if (ranNum <= 20) { // 20%
				
			} else if (ranNum <= 50) { // 30%
				
			} else if (ranNum <= 100) { // 50%
				
			}
			
			System.out.println(empVO);
		}
		*/
		
		
		
		
//		for (int i = 0; i < 218; i++) {
//			estate = "abse";
//			eid = employVO.get(arr[i]).getEid();
//			int num = service.updateEstate(eid, estate);
//			System.out.println(i+"===>"+eid+","+estate+","+num);
//		}	
//		for(int y = 218; y<330; y++) {
//			estate ="late";
//			eid = employVO.get(arr[y]).getEid();
//			int num=service.updateEstate(eid, estate);
//			System.out.println(y+"===>"+eid+","+estate+","+num);
//		}//for
//		
//		for(int k=330; k<553; k++) {
//			estate ="va";
//			eid = employVO.get(arr[k]).getEid();
//			int num =service.updateEstate(eid, estate);
//			System.out.println(k+"===>"+eid+","+estate+","+num);
//		}//for
		
	}// test
}
