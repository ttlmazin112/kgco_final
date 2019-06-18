package com.exam.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
//       

		// AJAX를 통해 접근할 경우
		String ajaxHeader = request.getHeader("X-Ajax-call");
		String result = "";

		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setCharacterEncoding("UTF-8");

		if (ajaxHeader == null) { // ajax 방법이 아닌 일반적인 접근일 경우
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Object principal = auth.getPrincipal();
			if (principal instanceof UserDetails) {
				//String eId = ((UserDetails) principal).getUsername();
				log.error("Access Denied Handler");
				log.error("Redirect....");
				response.sendRedirect("/accessError");

			} // if
		}else  if("true".equals(ajaxHeader)) { // ajax 접근일 경우 
			 
			
		}
		
		
	}// handle

}
