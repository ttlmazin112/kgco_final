package com.exam.socket;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.exam.domain.AuthVo;
import com.exam.domain.EmployeeVo;
import com.exam.domain.WebSocketSessionAndMember;
import com.exam.mapper.AuthMapper;
import com.exam.mapper.EmployeeMapper;
import com.exam.security.domain.CustomUser;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class MyTextWebSocketHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSessionAndMember> usersMap = new HashMap<>();
	
	@Setter(onMethod_ =@Autowired )
	private EmployeeMapper employeeMapper;
	
	@Setter(onMethod_ =@Autowired )
	private AuthMapper authMapper;
	
	//클라이언트랑 연결된 이후에 호출되는 메소드이다.
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	log.info(session.getId()+"님이 접속하였습니다.");
	log.info("연결 IP : "+session.getRemoteAddress().getHostName());
	
	//스프링 시큐리티 연동 사용자정보 객체인 Principal 사용가능하다.
	Principal principal = session.getPrincipal();
	log.info("principal name : "+principal.getName());  //로그인 아이디
	
	String eid = principal.getName(); 
	EmployeeVo employeeVO=employeeMapper.getMemberById(eid);
	 List<AuthVo> authList=authMapper.selectAuthListById(eid);
	
	   List<SimpleGrantedAuthority> list = new ArrayList<>();
       
       for (AuthVo auth : authList) {
           list.add(new SimpleGrantedAuthority(auth.getAuth()));
       }
   
   WebSocketSessionAndMember sessionAndMember
   = new WebSocketSessionAndMember();
   
   sessionAndMember.setWebSocketSession(session);
   sessionAndMember.setEmployeeVO(employeeVO);
   sessionAndMember.setAuthList(list);
   
   usersMap.put(session.getId(), sessionAndMember);
   
   String str = employeeVO.getEname() + "(" + employeeVO.getEid() +")" + employeeVO.getEclass()+"님이 들어오셨습니다.";
   broadcast(session,str); 
	
	}

	//서버가 클라이언트로부터 메시지를 받았을때 호출되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info(session.getId() + "로부터 "+message.getPayload());
		
		WebSocketSessionAndMember sessAndMember = usersMap.get(session.getId());
		EmployeeVo employeeVO= sessAndMember.getEmployeeVO();
		
		String str = employeeVO.getEname() + "(" + employeeVO.getEid() + ")" +employeeVO.getEclass()+"▷ "+ message.getPayload();
		broadcast(session, str);
	}

	//클라이언트와 연결이 끊어졌을때 호출되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info(session.getId() + "님 연결이 끊어졌습니다.");
		
		WebSocketSessionAndMember sessAndMember = usersMap.get(session.getId());
		EmployeeVo employeeVO = sessAndMember.getEmployeeVO();
		
		String str = employeeVO.getEname() + "(" + employeeVO.getEid() + ")"+employeeVO.getEclass() +"님이 채팅방을 나갔습니다.";
		broadcast(session, str);
		
		usersMap.remove(session.getId());
	}
	
	//모든 사람에게 메시지 전송
	private void broadcast(WebSocketSession selfSession, String str) throws Exception {
		Set<String> keySet = usersMap.keySet(); //id
		for(String id : keySet) {
			WebSocketSessionAndMember sessAndMember = usersMap.get(id);
			WebSocketSession sess = sessAndMember.getWebSocketSession();
			
			if(!selfSession.getId().equals(sess.getId())) {
				sess.sendMessage(new TextMessage(str));
			}
		}
		
	}
	
	
	
}
