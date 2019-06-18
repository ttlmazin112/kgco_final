<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>운항 찾기</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,,500,600,700"
	rel="stylesheet">

<link rel="stylesheet"
	href="/resources/css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/animate.css">

<link rel="stylesheet" href="/resources/css/owl.carousel.min.css">
<link rel="stylesheet" href="/resources/css/owl.theme.default.min.css">
<link rel="stylesheet" href="/resources/css/magnific-popup.css">

<link rel="stylesheet" href="/resources/css/aos.css">

<link rel="stylesheet" href="/resources/css/ionicons.min.css">

<link rel="stylesheet" href="/resources/css/bootstrap-datepicker.css">
<link rel="stylesheet" href="/resources/css/jquery.timepicker.css">


<link rel="stylesheet" href="/resources/css/flaticon.css">
<link rel="stylesheet" href="/resources/css/icomoon.css">
<link rel="stylesheet" href="/resources/css/style.css">
<style>
tr:hover {
	border: solid; 1px;
}
</style>

</head>
<body>
	<div
		style="overflow: scroll; width: 600px; height: 500px; margin-top: 50px">
		<table class="table">
			<thead class="thead-primary">
				<tr class="row100 head">
					<th>직원 아이디</th>
					<th>직급</th>
					<th>이름</th>
					<th>편성게이트</th>
					<th>업무상황</th>
					<th>스케줄</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="searchList" items="${list}">
					<tr onclick="sendData('${searchList.EId}','${searchList.EClass }')">
						<td>${searchList.EId}</td>
						<td>${searchList.EClass }</td>
						<td>${searchList.EName }</td>
						<td>${searchList.EGate }</td>
						<td>${searchList.EState }</td>
						<td>${searchList.EGroup }</td>
					</tr>
				</c:forEach>


			</tbody>
		</table>
	</div>
</body>

<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script>
	function sendData(eId,eclass) {
		if(eclass == '부장') alert('직급이 부장이면 부서이동이 불가능 합니다.');
		else{
			var check = eId.substring(0,2);
			if(check =="FA"){
				alert('FA or OP do not have gate');
			}else if(check =="OP"){
				alert('FA or OP do not have gate');
			}
			else{
				opener.document.documentFrm.key1.value = eId;
				window.close();
			}

		}
	}
</script>
<script src="/resources/js/jquery.min.js"></script>
	<script src="/resources/js/jquery-migrate-3.0.1.min.js"></script>
	<script src="/resources/js/popper.min.js"></script>
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/js/jquery.easing.1.3.js"></script>
	<script src="/resources/js/jquery.waypoints.min.js"></script>
	<script src="/resources/js/jquery.stellar.min.js"></script>
	<script src="/resources/js/owl.carousel.min.js"></script>
	<script src="/resources/js/jquery.magnific-popup.min.js"></script>
	<script src="/resources/js/aos.js"></script>
	<script src="/resources/js/jquery.animateNumber.min.js"></script>
	<script src="/resources/js/bootstrap-datepicker.js"></script>
	<script src="/resources/js/jquery.timepicker.min.js"></script>
	<script src="/resources/js/scrollax.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script src="/resources/js/google-map.js"></script>
	<script src="/resources/js/main.js"></script>

</html>