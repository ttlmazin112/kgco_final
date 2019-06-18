<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/page.js"></script>
<script src="/resources/js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<h1>password 확인</h1>
	<c:set var="emVO" value="${emVO }"></c:set>
	<form action="/modifyPwCheck" id="modi" name="modi" method="post">
		<input type="hidden" name="eid" value="${emVO.eid }"> 
		<input type="password" name="epassword"> <input type="hidden"
			name="epassword" value="${emVO.epassword }"> <input
			type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit" value="전송" id="tt" >
	</form>

	
		<div class="nav ftco-animate nav-pills" id="v-pills-tab"
			role="tablist" aria-orientation="vertical">

			<input type="button" id="saveBtn" name="memberUpdateSave"
				onclick="infoSave();" value="저장" style="display: none;">
		</div>
	
</body>
</html>