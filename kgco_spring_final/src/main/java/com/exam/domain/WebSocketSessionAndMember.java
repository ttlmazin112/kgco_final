package com.exam.domain;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.WebSocketSession;

import lombok.Data;

@Data
public class WebSocketSessionAndMember {

	private WebSocketSession webSocketSession;
	private EmployeeVo employeeVO;
	private List<SimpleGrantedAuthority> authList;
}
