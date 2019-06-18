package com.exam.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.domain.EmployeeStatusVO;
import com.exam.domain.EmployeeVo;
import com.exam.domain.EmployeeWorkNoteVO;
import com.exam.domain.PebyDeptVO;
import com.exam.domain.PebyGateVO;
import com.exam.service.AuthService;
import com.exam.service.EmployeeService;

import com.exam.util.AttendDao;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/*")
public class EmployeeController {
	@Setter(onMethod_ = @Autowired)
	private AuthService authService;

	@Setter(onMethod_ = @Autowired)
	private EmployeeService service;

	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder;

	// @PreAuthorize("isAutenticated()") 로그인이 되어있다면
	@GetMapping("/test")
	public void test() {
		// eid을 list로 저장하여
		// 생성된 난수로 아이디를 선택
		// 일정 숫자만큼 근무형태를 임시 update
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

		String eid = null;
		String estate = null;

		List<EmployeeVo> employList = service.getAllEid();
		for (int i = 0; i < numList.size(); i++) {
			if (i < 218) {
				estate = "abse";
			} else if (i < 330) {
				estate = "late";
			} else if (i < 553) {
				estate = "va";
			} else {
				break;
			}
			EmployeeVo empVo = employList.get(numList.get(i) - 1);
			eid = empVo.getEid();
			service.updateEstate(eid, estate);

		}
	}// test

	@GetMapping("/personnelComposition")
	public String personnelComposition(Model model,Principal principal) {
		//금일 근무상태 업데이트
		List<EmployeeVo> eidList = service.getAllEid();

		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		List<EmployeeWorkNoteVO> empWorkNote = service.getWorkNote();
		for (EmployeeWorkNoteVO vo : empWorkNote) {
			obj.put(vo.getDate(), vo.getEidAndWorkNote());
		}
		// 오늘날짜
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(curDate);

		// String -> List<Map<String,Object>>
		JSONParser parser = new JSONParser();
		Object object = new Object();
		try {
			object = parser.parse(obj.get(date).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		array = (JSONArray) object;
		List<Map<String, Object>> listMap = AttendDao.getListMapFromJsonArray(array);
		Map<String, Object> map = listMap.get(0); // 하루치 근무일지

		for (EmployeeVo empVO : eidList) { // 금일 근무상태 업데이트
			String estate = map.get(empVO.getEid()).toString(); // 해당 eid의 근무상태
			service.updateByStatus(empVO.getEid(), estate);
		}
		
		
		// 부서별 인원 현황 업데이트 
		service.updatePeByDept();
		
		// 게이트별 인원 현황 업데이트 
		String[] eGateArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String eIdTR = "TR";
		String eIdMA = "MA";
		String eGate = "";
		int gateNum = 0;
		for (int i = 1; i <= 10; i++) {
			eGate = eGateArr[gateNum];
			service.updatePebyGate(eIdTR, eIdMA, i, eGate);
			gateNum++;
		}
		// 인원현황 관련 table 호출 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<String> roleNames = new ArrayList<>();
		String authName = "";
		Collection<GrantedAuthority> collection = (Collection<GrantedAuthority>) auth.getAuthorities();
		for (GrantedAuthority authority : collection) {
			roleNames.add(authority.getAuthority());
		} // [권한명]
		authName = roleNames.toString();
		int num = authName.indexOf("]");
		authName = authName.substring(1, num); // 권한명 저장

		List<EmployeeStatusVO> empStatusList = service.getEmployeeStatus(authName);
		List<PebyDeptVO> pebyDeptList = service.getPebyDept();
		List<PebyGateVO> pebyGateList = service.getPebyGate();
		for (PebyGateVO evo : pebyGateList) {
			System.out.println(evo);
		}
		EmployeeVo employeeVo =  service.getMemberById(principal.getName());
		model.addAttribute("employeeVo",employeeVo);
		model.addAttribute("empStatusList", empStatusList);
		model.addAttribute("pebyGateList", pebyGateList);
		model.addAttribute("pebyDeptList", pebyDeptList);
		return "/member/personnelComposition2";
	}// personnelComposition

	@GetMapping("/updatePass")
	// 비빌번호 암호화 업데이트
	public void updatePass() {
		String encodePassword = "";
		List<EmployeeVo> empList = service.getAllEid();
		for (EmployeeVo empVO : empList) {
			encodePassword = passwordEncoder.encode(empVO.getEid());
			service.updateByPass(empVO.getEid(), encodePassword);
			System.out.println(empVO.getEid() + " 업데이트 완료 ");
		}

	}// updatePass

	@GetMapping("/updateAuth")
	// 권한 업데이트
	public String updateAuth() {
		List<EmployeeVo> empList = service.getEmployeeList();
		for (EmployeeVo empVO : empList) {
			if (empVO.getEclass().equals("부장")) {
				String eid = empVO.getEid();
				String auth = "ROLE_HEAD";
				authService.updateAuth(eid, auth);
			}
			if (empVO.getEclass().equals("차장")) {
				String eid = empVO.getEid();
				String auth = "ROLE_VICEHEAD";
				authService.updateAuth(eid, auth);
			}
			if (empVO.getEclass().equals("과장")) {
				String eid = empVO.getEid();
				String auth = "ROLE_EXAGGERATION";
				authService.updateAuth(eid, auth);
			}
			if (empVO.getEclass().equals("대리")) {
				String eid = empVO.getEid();
				String auth = "ROLE_MANAGER";
				authService.updateAuth(eid, auth);
			}
			if (empVO.getEclass().equals("사원")) {
				String eid = empVO.getEid();
				String auth = "ROLE_MEMBER";
				authService.updateAuth(eid, auth);
			}
		}
		return "/home";
	}// updateAuth


	@GetMapping("/updateByWorkNote")
	public void updateByWorkNote() {
		// 현재 날짜 처리
		Date curDate = new Date();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdfDate.format(curDate);
		// String date = "2019-05-03"; // 임의날짜
		// all_employee 모든 정보 가져오기
		List<EmployeeVo> eidList = service.getAllEid();
		SimpleDateFormat sdfTime = new SimpleDateFormat("mmss");
		String time = sdfTime.format(curDate);
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
		for (int i = 0; i < numList.size(); i++) {
			if (i < 218) {
				eidAndWorkNote = "abse";
			} else if (i < 330) {
				eidAndWorkNote = "late";
			} else if (i < 553) {
				eidAndWorkNote = "va";
			} else {
				eidAndWorkNote = "nor";
			}
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
		service.insertByWorkNote(date, str);
	}// updateByWorkNote

	   @GetMapping("/modifyPwCheck1")
       public String modifyPwCheck(Principal prin, EmployeeVo emVO, Model model) {
           
           String eid = prin.getName();
           emVO=service.modifyPwCheck(eid, emVO.getEpassword());
           model.addAttribute("emVO", emVO);
           
           return "/member/modifyPwCheck";
       }
	@PostMapping("/attend")
	@ResponseBody
	public void attend(Principal principal) {
		String eid = principal.getName();
		log.info("출석 체크될 아이디값=>"+principal.getName());
		/* 출근 버튼 */
		// 오늘날짜
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(curDate);
		// 현재 시각 mmhhss
		SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
		int time = Integer.parseInt(sdfTime.format(curDate));

		// 정상출근 여부 판정
		String estate = "";
		String eGroup = service.getEmployeeStatusByEid(eid); // eid에 해당되는 근무그룹확인
		if (eGroup.equals("A")) {
			if (time < 90000) {
				estate = "nor";
			} else {
				estate = "late";
			}
		} else if (eGroup.equals("B")) {
			if (time < 170000) {
				estate = "nor";
			} else {
				estate = "late";
			}
		} else if (eGroup.equals("C")) {
			if (time < 10000) {
				estate = "nor";
			} else {
				estate = "late";
			}
		}

		int dateCheck = 0; // 금일 날짜 존재여부 체크
		JSONObject objUpdate = new JSONObject();
		List<EmployeeWorkNoteVO> empWorkNote = service.getWorkNote();
		for (EmployeeWorkNoteVO vo : empWorkNote) {
			objUpdate.put(vo.getDate(), vo.getEidAndWorkNote());
		}
		for (EmployeeWorkNoteVO vo : empWorkNote) {
			if (date.equals(vo.getDate())) { // 오늘날짜가 table에 존재한다면
				dateCheck++;
			} 
		} // for

		if (dateCheck ==1) { // 오늘날짜 레코드 존재 update
			JSONArray array = new JSONArray();
			// String -> List<Map<String,Object>>
			JSONParser parser = new JSONParser();
			Object object = new Object();
			try {
				object = parser.parse(objUpdate.get(date).toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			array = (JSONArray) object;
			List<Map<String, Object>> listMap = AttendDao.getListMapFromJsonArray(array);
			Map<String, Object> map = listMap.get(0); // 하루치 근무일지
			map.put(eid, estate); // 출근자  명단 추가 
		
			JSONObject objTemp = new JSONObject();
			objTemp = AttendDao.getJsonStringFromMap(map);
			service.updateByWorkNote(date, "["+objTemp.toJSONString()+"]");
		} else if (dateCheck != 1) { // 오늘날짜 레코드 존재 X insert
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put(eid, estate);
			array.add(obj);
			String str = array.toJSONString();
			service.insertByWorkNote(date, str);
		}
	}// attend
	
	@GetMapping("/memberSelect")
	public String memberSelect(String search,String selec,Model model) {
		System.out.println("search=="+search+", selec===="+selec);
		
		List<EmployeeStatusVO> searchList = service.memberSelect(selec, search);
		System.out.println("list=================="+searchList);
		
		model.addAttribute("searchList", searchList);
		return "/member/search";
	}
	   @GetMapping("/selfSchedule")
	    public String selfSchedule(String eid,Principal principal, Model model) {
	        eid = principal.getName();
	        EmployeeStatusVO statusVO = service.selfSchedule(eid);
	        System.out.println("statusVO============"+statusVO);
	        
	        model.addAttribute("statusVO", statusVO);
	        
	        return "/member/selfSchedule";
	    }
	      @ResponseBody
	      @PostMapping("/userInfoUpdate")
	      public ResponseEntity<String> userInfoUpdate(String eid, String ename, String ephone, Principal principal,Model model) {      
	          System.out.println("userInfoUpdate()======================");  
	          eid =principal.getName();
	                   
	         System.out.println("userinfoEid===="+eid+ename+ephone);
	         
	         service.userInfoUpdate(eid,ename,ephone);
	         
	         HttpHeaders headers = new HttpHeaders();
	           headers.add("Content-Type", "text/html; charset=UTF-8");
	           StringBuffer sb = new StringBuffer();
	           sb.append("<script>");
	           sb.append("alert('바꼈다!!!');");
	           sb.append("window.close();");
	           sb.append("opener.parent.location.reload();");
	           sb.append("</script>");
	 
	           return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);  
	            
	          
	      }
	   
	   @ResponseBody
       @PostMapping("/modifyPwCheck")
       public ResponseEntity<String> modifyPwCheck(Principal principal,EmployeeVo emVO,@RequestParam String eid,@RequestParam(value="epassword") String epassword, Model model) {
         System.out.println("패스워드 비교~~~");
         String encodePassword="";
         eid = principal.getName();
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
         
        String pw = passwordEncoder.encode(emVO.getEpassword());
         
         boolean check = passwordEncoder.matches(epassword,pw );
         System.out.println(check);
         System.out.println(epassword+"=========epassword"+"pw======"+pw);
       
         if(check == true) {
             System.out.println("성공");
              HttpHeaders headers = new HttpHeaders();
              headers.add("Content-Type", "text/html; charset=UTF-8");
              StringBuffer sb = new StringBuffer();
              sb.append("<script>");
              sb.append("alert('비밀번호 일치!!!');");
              sb.append("location.href='/member/userInfoMody';");
              sb.append("</script>");
    
              return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);                  
         }else {
             System.out.println("불일치");
              HttpHeaders headers = new HttpHeaders();
              headers.add("Content-Type", "text/html; charset=UTF-8");
              StringBuffer sb = new StringBuffer();
              sb.append("<script>");
              sb.append("alert('비밀번호 불일치!!!');");         
              sb.append("</script>");
    
              return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
            
         }
       }
       
       @GetMapping("/openChat")
       public String openChat() {
           return "/chat/chat";
       }  

}
