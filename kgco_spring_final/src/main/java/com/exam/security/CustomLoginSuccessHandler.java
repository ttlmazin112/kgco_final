package com.exam.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication auth) throws IOException, ServletException {
        log.warn("Login Success");
        
        List<String> roleNames = new ArrayList<>();
        
        Collection<GrantedAuthority> collection 
            = (Collection<GrantedAuthority>) auth.getAuthorities();
        
        for (GrantedAuthority authority : collection) {
            roleNames.add(authority.getAuthority());
        }
        
        log.warn("ROLE NAMES: " + roleNames);
        
        if (roleNames.contains("ROLE_HEAD")) { //부장
            response.sendRedirect("/member/memberhome");
        	// response.sendRedirect("/schedule/permission");
            return;
        }
        if (roleNames.contains("ROLE_VICEHEAD")) { //차장
            response.sendRedirect("/member/memberhome");
            return;
        }
        
        if (roleNames.contains("ROLE_EXAGGERATION")) { // 과장
            response.sendRedirect("/member/memberhome");
            return;
        }
        if (roleNames.contains("ROLE_MANAGER")) { //대리
            response.sendRedirect("/member/memberhome");
            return;
        }
        if (roleNames.contains("ROLE_MEMBER")) { //사원 
            response.sendRedirect("/member/memberhome");
            return;
        }
        response.sendRedirect("/");
    }

}
