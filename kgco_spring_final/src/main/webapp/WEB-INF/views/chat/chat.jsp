<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.3.1.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script> 
<script src="/resources/js/page.js"></script>
<script type="text/javascript">
var sock;

function openSocket() {
	if (sock != null) {
		$('#messages').append('웹소켓이 이미 연결되었습니다.<br>');
		scroll();
		return;
	}
	sock = new SockJS('/chat-socket');
	//sock = new WebSocket('ws://localhost/chat-socket');
	
	// 서버와 연결이 완료된후 자동호출됨
	sock.onopen = function (event) {
		$('#messages').append('연결되었습니다.<br>');
		scroll();
	}
	
	// onmessage는 서버로부터 메시지를 받았을때 호출됨
	sock.onmessage = function (event) {
		var data = event.data;
		$('#messages').append(data + '<br>');
		scroll();	
	};
	
	sock.onclose = function (event) {
		$('#messages').append('연결이 끊어졌습니다.<br>');
		scroll();
	}
	
}

function closeSocket() {
	sock.close();
	sock = null;
}

function send() {
	var msg = $('#message-input').val();
	if (msg == '') {
		return;
	}
	sock.send(msg);
	
	var name = '<sec:authentication property="principal.employee.ename"/>';
	var eclass = '<sec:authentication property="principal.employee.eclass"/>';
	var id = '<sec:authentication property="principal.username"/>';
	var message = '<span style="color: red;">' + name + '(' + id + ')'+eclass +'▶ ' + msg + '</span>';
	$('#messages').append(message + '<br>');
	scroll();
	
	$('#message-input').val('');
}

function scroll() {
	var top = $('#messages').prop('scrollHeight');
	$('#messages').scrollTop(top);
}



$(document).ready(function () {
	
	$('#btnOpen').on('click', function () {
		openSocket();
	});
	
	$('#btnClose').on('click', function () {
		closeSocket();
	});
	
	$('#btnSend').on('click', function () {
		send();
	});
	
	$('#message-input').on('keydown', function (event) {
		if (event.keyCode == 13) { // 엔터키
			send();
		}
	});
	
});


</script>
</head>
<body>
		<h3>메신저</h3>
				<div id="messages" style="overflow: auto; width: 400px; height: 300px; border: 1px solid black;"></div>
				<br> <input type="text" id="message-input"
					style="font-size: small;">

				<div>
					<button type="button" id="btnOpen" style="font-size: small;">접속</button>
					<button type="button" id="btnSend" style="font-size: small;">전송</button>
					<button type="button" id="btnClose" style="font-size: small;">닫기</button>
				</div>
				
				
</body>
</html>