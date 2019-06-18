<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<table class="table">
	<thead class="thead-primary">
		<tr>
			<td>No</td>
			<td>Standard</td>
			<td>Arrived</td>
			<td>Start</td>
			<td>City</td>
			<td>Etd</td>
			<td>I/O</td>
			<td>Do/In</td>
			<td>State</td>
			<td>Std</td>
		</tr>
	</thead>

			<c:forEach var="realtime" items="${list}">
				<tr onclick="sendData('${realtime.airFln}','${realtime.std}')">
					<td>${realtime.airFln}</td>
					<td>${realtime.airport}</td>
					<td>${realtime.city}</td>
					<td>${realtime.arrivedEng}</td>
					<td>${realtime.boardingEng}</td>
					<td>${realtime.etd}</td>
					<td>${realtime.io}</td>
					<td>${realtime.line}</td>
					<td>${realtime.rmkEng}</td>
					<td>${realtime.std}</td>
				</tr>
			</c:forEach>

	</table>
</body>
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script>
function sendData(airFln, std){
	alert(airFln +' : '+ std);
	opener.document.documentFrm.key1.value = airFln;
	opener.document.documentFrm.key2.value = std;
	
	window.close();
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