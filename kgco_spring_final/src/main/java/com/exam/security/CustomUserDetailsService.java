package com.exam.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.exam.domain.AuthVo;
import com.exam.domain.EmployeeVo;
import com.exam.mapper.AuthMapper;
import com.exam.mapper.EmployeeMapper;
import com.exam.security.domain.CustomUser;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

    @Setter(onMethod_ = @Autowired)
    private EmployeeMapper employeeMapper;
    
    @Setter(onMethod_ = @Autowired)
    private AuthMapper authMapper;
    
    //로그인하면 loadUserByUserNAME()메소드 자동호출됨
    //로그인 아이디가 username입력 파라미터로 전달됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 스프링 시큐리티에서 username은 아이디를 가리킴
        log.warn("username : " + username);
        
        //조인쿼리로  아이디에 해당하는 member레코드와 auth리스트 가져오기
        /* MemberVO member = memberMapper.getMemberAndAuthById(username); */
        
        EmployeeVo employee = employeeMapper.getMemberById(username);
        List<AuthVo> authList = authMapper.selectAuthListById(username);
        
        log.warn("employee : " + employee);
        
        String id = employee.getEid();
        String password = employee.getEpassword();
        
       // List<AuthVO> authList = member.getAuthList();
        
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        
        for (AuthVo auth : authList) {
            list.add(new SimpleGrantedAuthority(auth.getAuth()));
        }
        
        //PRINCIPAL :사용자인증이 완료된 사용자 정보
       CustomUser customUser = new CustomUser(id, password, list, employee);
        return customUser;
    }

}
