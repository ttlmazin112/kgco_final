<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원인원편성</title>
<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="/resources/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="/resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/css/util.css">
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<!--===============================================================================================-->


</head>

<body>
	<div class="table1 m-b-110" >
	<h3>직원 근무 현황</h3>
		<div>
			
		</div>
	<div >	
		<table data-vertable="ver5" id="tableList">
			<thead style="margin: 0px;">
				<tr class="row100 head" >
					<th>직원 아이디</th>
					<th>직급</th>
					<th>이름</th>
					<th>편성게이트</th>
					<th>업무상황</th>
					<th>스케줄</th>
				</tr>
			</thead>
			<tbody style="padding: 0px;">
			<c:set var="statusVO" value="${statusVO }"></c:set>
						<tr class="row100">
						<td>${statusVO.EId}</td>
						<td>${statusVO.EClass }</td>
						<td>${statusVO.EName }</td>
						<td>${statusVO.EGate }</td>
						<td>${statusVO.EState }</td>
						<td>${statusVO.EGroup }</td>
					</tr>
			
			</tbody>
		</table>
</div>
	</div>
</body>
</html>