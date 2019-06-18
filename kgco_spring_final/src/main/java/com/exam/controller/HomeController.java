package com.exam.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exam.domain.EmployeeVo;
import com.exam.domain.EmployeeWorkNoteVO;
import com.exam.service.EmployeeService;
import com.exam.util.AttendDao;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
public class HomeController {
	@Setter(onMethod_ = @Autowired)
	private EmployeeService service;
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(curDate);
		
		int dateCheck = 0; // 금일 날짜 존재여부 체크
		JSONObject objUpdate = new JSONObject();
		List<EmployeeWorkNoteVO> empWorkNote = service.getWorkNote();
		for (EmployeeWorkNoteVO vo : empWorkNote) {
			objUpdate.put(vo.getDate(), vo.getEidAndWorkNote());
		}
		for (EmployeeWorkNoteVO vo : empWorkNote) {
			if (date.equals(vo.getDate())) { // 오늘날짜가 table에 존재한다면
				dateCheck ++;
			} 
		} // for
		
		if(dateCheck != 1) {
		List<EmployeeVo> eidList = service.getAllEid();
		String workNote=AttendDao.attendByDaily(eidList);
		service.insertByWorkNote(date.toString(), workNote);
		}
		return "home";
	}

//    @GetMapping("/")
//    public String login(String error, String logout, Model model) {
//        log.info("error : " + error);
//        log.info("logout : " + logout);
//        
//        if (error != null) {
//            model.addAttribute("error", "Login Error. Check Your Account.");
//        }
//        
//        if (logout != null) {
//            model.addAttribute("logout", "Logout됨!!");
//        }
//        
//        return "member/login";
//    }


//	@PostMapping("/")
//	public ResponseEntity<String> login(EmployeeVo employeeVO, HttpSession session) {
//
//		int check = service.loginCheck(employeeVO.getEid(), employeeVO.getEpassword());
//		String name = service.getMemberById(employeeVO.getEid()).getEname();
//		String eclass = service.getMemberById(employeeVO.getEid()).getEclass();
//			if (check != 1) {
//			String message = "";
//			if (check == -1) {
//				message = "해당하는 아이디가 없습니다.";
//			} else if (check == 0) {
//				message = "비밀번호가 일치하지 않습니다.";
//			}
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Type", "text/html; charset=UTF-8");
//			StringBuffer sb = new StringBuffer();
//			sb.append("<script>");
//			sb.append("alert('" + message + "');");
//			sb.append("location.href='/';");
//			sb.append("</script>");
//
//			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
//		}
//		session.setAttribute("name", name);
//		System.out.println("name :" + name);
//		session.setAttribute("eclass", eclass);
//		System.out.println("eclass :" + eclass);
//		session.setAttribute("id", employeeVO.getEid());
//		System.out.println("id :" + employeeVO.getEid());
//
//		if (eclass.equals("부장") || eclass.equals("차장")) {
//
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Type", "text/html; charset=UTF-8");
//			StringBuffer sb = new StringBuffer();
//			sb.append("<script>");
//			sb.append("alert('" + name + eclass + "님 반가워요');");
//			sb.append("location.href='/member/headEmployee';");
//			sb.append("</script>");
//
//			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
//
//		} else if (eclass.equals("과장")) {
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Type", "text/html; charset=UTF-8");
//			StringBuffer sb = new StringBuffer();
//			sb.append("<script>");
//			sb.append("alert('" + name + eclass + "님 반가워요');");
//			sb.append("location.href='/member/exaggeration';");
//			sb.append("</script>");
//			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
//		} else if (eclass.equals("대리") || eclass.equals("사원")) {
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Type", "text/html; charset=UTF-8");
//			StringBuffer sb = new StringBuffer();
//			sb.append("<script>");
//			sb.append("alert('" + name + eclass + "님 반가워요');");
//			sb.append("location.href='/member/manager';");
//			sb.append("</script>");
//			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
//		}
//
//		return null;
//
//	}// login

	
}
