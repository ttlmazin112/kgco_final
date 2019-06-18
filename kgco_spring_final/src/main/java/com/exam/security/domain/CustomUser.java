package com.exam.security.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.exam.domain.EmployeeVo;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Getter
@Log4j
public class CustomUser extends User {
    
    private EmployeeVo employee;

    public CustomUser(String username, String password, 
            Collection<? extends GrantedAuthority> authorities,
            EmployeeVo employee) {
        super(username, password, authorities);
        this.employee  = employee ;
    }
   
    
}
