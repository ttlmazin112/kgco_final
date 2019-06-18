<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<div class="table1 m-b-110">
		<div>
			<h3>부서별 인원 현황</h3>
		</div>
		<table data-vertable="ver5">
			<thead>
				<tr class="row100 head">
					<th>부서장</th>
					<th>부서코드</th>
					<th>부서위치</th>
					<th>현시각 책임자</th>
					<th>전체 인원</th>
					<th>근무 인원</th>
					<th>휴가자 인원</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pebyDept" items="${pebyDeptList }">
					<tr class="row100">
						<td>${pebyDept.deptboss}</td>
						<td>${pebyDept.deptcode }</td>
						<td>${pebyDept.deptaddress }</td>
						<td>${pebyDept.cdeptboss }</td>
						<td>${pebyDept.allcp}</td>
						<td>${pebyDept.wcp }</td>
						<td>${pebyDept.vcp }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<div class="table1 m-b-110">
		<div>
			<h3>게이트별 인원 현황</h3>
		</div>
		<table data-vertable="ver5">
			<thead>
				<tr class="row100 head">
					<th>게이트</th>
					<th>운송과 배치인원</th>
					<th>정비과 배치인원</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pebyGate" items="${pebyGateList }">
					<tr class="row100">
						<td>${pebyGate.gate }</td>
						<td>${pebyGate.byMaintenance }</td>
						<td>${pebyGate.byTransport }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="table1 m-b-110">
		<div>
			<h3>직원 근무 현황</h3>
		</div>
		<table data-vertable="ver5" id="tableList">
			<thead>
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
				<c:forEach var="empStatus" items="${empStatusList }">
					<tr class="row100">
						<td>${empStatus.EId}</td>
						<td>${empStatus.EClass }</td>
						<td>${empStatus.EName }</td>
						<td>${empStatus.EGate }</td>
						<td>${empStatus.EState }</td>
						<td>${empStatus.EGroup }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>