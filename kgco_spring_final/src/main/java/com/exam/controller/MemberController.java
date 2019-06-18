package com.exam.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.domain.EmployeeVo;
import com.exam.domain.EmployeeWorkNoteVO;
import com.exam.mapper.EmployeeMapper;
import com.exam.service.EmployeeService;
import com.exam.util.AttendDao;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member/*")
@AllArgsConstructor
@Log4j
public class MemberController {

	@Setter(onMethod_ = @Autowired)
	private EmployeeService service;

	@Setter(onMethod_ = @Autowired)
	private EmployeeMapper mapper;

	// 회원페이지 이동
	@GetMapping("/memberhome")
	public String head(Principal principal, Model model, HttpSession session, HttpServletResponse response) {
		String eid = principal.getName();
		EmployeeVo emplyeeVo = service.getMemberById(eid);

		model.addAttribute("emplyeeVo", emplyeeVo);

		return "/member/memberhome";
	}// head

	// Top
	@GetMapping("/header2")
	public String header(Principal principal, Model model, HttpSession session, HttpServletResponse response) {
		String eid = principal.getName();

		System.out.println("eid======" + principal.getName());
		EmployeeVo emplyeeVo = service.getMemberById(eid);

		model.addAttribute("emplyeeVo", emplyeeVo);

		return "/inc/header2";
	}// header
	
    @GetMapping("/userInfoMody")
    public String userInfoMody(Principal principal, EmployeeVo emVO, Model model) {
        String eid = principal.getName();
       emVO= service.getMemberById(eid);
       model.addAttribute("emVO", emVO);
        return "member/userInfoMody";
    }

	// 구글차트
	@GetMapping("/pieChart")
	public void pieChart(Principal principal, EmployeeVo employee, Model model, HttpServletResponse response,
			HttpSession session) {
		String id = principal.getName();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		employee = service.getMemberById(id);

		String str = employee.getEid();
		String str1 = str.substring(0, 2);
		String str2 = str.substring(2, 4);
		String str3 = str.substring(0, 4);
		List<String> roleNames = new ArrayList<>();

		Collection<GrantedAuthority> collection = (Collection<GrantedAuthority>) auth.getAuthorities();

		for (GrantedAuthority authority : collection) {
			roleNames.add(authority.getAuthority());
		}
		String authName = roleNames.toString();

		response.setContentType("application/json; charset=UTF-8");

		if (employee.getEid().contains(str1) && authName.equals("[ROLE_HEAD]")) {

			JSONArray jArray = service.getCountEstate(str1);
			System.out.println("====================jArray" + jArray);
			model.addAttribute("jArray", jArray);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(jArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (employee.getEid().contains(str3) && authName.equals("[ROLE_EXAGGERATION]")) {

			JSONArray jArray = service.getCountEstate(str3);
			System.out.println("====================jArray" + jArray);
			model.addAttribute("jArray", jArray);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(jArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (authName.equals("[ROLE_MANAGER]")) {

			JSONArray jArray = service.getCountEstate(str3);
			System.out.println("====================jArray" + jArray);
			model.addAttribute("jArray", jArray);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(jArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// pieChart

	@GetMapping("/exaggeration")
	public String exaggeration(Principal principal, Model model) {
		String eid = principal.getName();

		EmployeeVo emplyeeVo = service.getMemberById(eid);

		model.addAttribute("emplyeeVo", emplyeeVo);

		return "/member/exaggeration";
	}// exaggeration

	@GetMapping("/manager")
	public String manager(Principal principal, Model model) {

		String eid = principal.getName();
		EmployeeVo emplyeeVo = service.getMemberById(eid);

		model.addAttribute("emplyeeVo", emplyeeVo);
		return "/member/manager";
	}// manager

	@GetMapping("/calender")
	public String calender() {
		return "member/calender";
	}

	// 출근 달력
	@PostMapping("/calender")
	@ResponseBody
	public JSONObject workCalender(@RequestParam(value = "date") String date, Principal principal) {
		StringTokenizer st = new StringTokenizer(date, "-");
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		// 오늘 날짜값
		Date curDate = new Date();
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		int curDateNum = Integer.parseInt(sdfDate.format(curDate));
		int curMonthNum = Integer.parseInt(sdfMonth.format(curDate));
		int curYearNum = Integer.parseInt(sdfYear.format(curDate));

		Map<String, String> monthStateMap = new HashMap<String, String>();
		List<String> monthList = new ArrayList<String>(); // 한달치 리스트
		Map<String, Object> map = new HashMap<String, Object>();
		monthList = AttendDao.planning(year, month);

		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		JSONObject objState = new JSONObject();
		List<EmployeeWorkNoteVO> empWorkNote = service.getWorkNote();
		for (EmployeeWorkNoteVO vo : empWorkNote) {
			obj.put(vo.getDate(), vo.getEidAndWorkNote());
		}

		// String -> List<Map<String,Object>>
		JSONParser parser = new JSONParser();
		Object object = new Object();
		// 이번달
		if (curYearNum == year && curMonthNum == month) {
			for (int i = 1; i <= monthList.size(); i++) {
				try {
					if(obj.get(monthList.get(i - 1))!=null) {
						object = parser.parse(obj.get(monthList.get(i - 1)).toString());
						array = (JSONArray) object;
						List<Map<String, Object>> listMap = AttendDao.getListMapFromJsonArray(array);
						map = listMap.get(0); // 하루치 근무일지
						if(map.get(principal.getName()) == null){
						    monthStateMap= null;
						}else {
						monthStateMap.put(monthList.get(i - 1), (String) map.get(principal.getName()));
						objState = AttendDao.getJsonStringFromMapss(monthStateMap);
						}
					}else if(obj.get(monthList.get(i - 1))==null) {
				
						monthStateMap= null;
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
	
		} else if (curYearNum != year || curMonthNum != month) {
			// 지난 달
			for (String dateList : monthList) {
				try {
					if(obj.get(dateList)!=null) {
						object = parser.parse(obj.get(dateList).toString());
						array = (JSONArray) object;
						List<Map<String, Object>> listMap = AttendDao.getListMapFromJsonArray(array);
						map = listMap.get(0); // 하루치 근무일지
						monthStateMap.put(dateList, (String) map.get(principal.getName()));
						objState = AttendDao.getJsonStringFromMapss(monthStateMap);
					}else if(obj.get(dateList)==null) {
						monthStateMap= null;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}// for
		}

		return objState;
	}//workCalender
}
