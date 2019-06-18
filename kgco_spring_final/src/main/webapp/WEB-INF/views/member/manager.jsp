<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/grid.css" rel="stylesheet" type="text/css">
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

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Quicksand:300,400,500,700,900"
	rel="stylesheet">
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="/resources/js/page.js"></script>
</head>
<body>
	<div class="grid-container2">
		<header class="mm">
			<jsp:include page="/WEB-INF/views/inc/header.jsp" />

		</header>
		<nav class="mm">
			${emplyeeVo.ename } ${emplyeeVo.eclass }님 정보
			<c:set var="emplyeeVo" value="${emplyeeVo }"></c:set>

			<div class="table1 m-b-110">

				<div></div>
				<div>
					<form id="userInfoDeteil" name="userInfoDeteil" method="post">

						<table data-vertable="ver5" id="tableList">
							<tr class="row100 head">
								<th>직원 아이디</th>
								<td><a href="/selfSchedule" id="selfEid"
									onclick="selfEid(); return false;">${emplyeeVo.eid }</a></td>
							</tr>
							<tr class="row100 head">
								<th>이름</th>
								<td>${emplyeeVo.ename }</td>
							</tr>
							<tr class="row100 head">
								<th>직급</th>
								<td>${emplyeeVo.eclass }</td>
							</tr>
							<tr class="row100 head">
								<th>전화번호</th>
								<td>${emplyeeVo.ephone }</td>
							</tr>
						</table>

					</form>
					<input type="button" value="수정" id="userUpdate" name="userUpdate"
						onclick="userUpdate();">
				</div>
			</div>

			<div id="writeUpdateInfo">
				<form method="post" id="save">

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}">
					<table data-vertable="ver5" id="tableList1" style="display: none;">

						<tr class="row100 head">
							<th>직원 아이디</th>
							<td>${emplyeeVo.eid }</td>
						</tr>
						<tr class="row100 head">
							<th>이름</th>
							<td><input type="text" name="ename"
								value="${emplyeeVo.ename }"></td>
						</tr>
						<%-- 					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="epassword" value="${emplyeeVo.epassword }"></td>
					</tr> --%>
						<tr class="row100 head">
							<th>직급</th>
							<td>${emplyeeVo.eclass }</td>
						</tr>
						<tr class="row100 head">
							<th>전화번호</th>
							<td><input type="text" name="ephone"
								value="${emplyeeVo.ephone }"></td>
						</tr>
					</table>
					<input type="button" id="saveBtn" name="memberUpdateSave"
						onclick="infoSave();" value="저장" style="display: none;">
				</form>
			</div>
			<article class="mm" style="height: 400px;">
				<h3>메신저</h3>
				<textarea rows="30" cols="50" id="messages" readonly="readonly"
					style="font-size: small; height: 200px; width: 800px; overflow: scroll;"></textarea>
				<br> <input type="text" id="message-input"
					style="font-size: small;">

				<div>
					<button type="button" id="btnOpen" style="font-size: small;">접속</button>
					<button type="button" id="btnSend" style="font-size: small;">전송</button>
					<button type="button" id="btnClose" style="font-size: small;">닫기</button>
				</div>



			</article>
		</nav>


		<aside class="mm">

			<div id="pie_chart_div"
				style="width: 500px; height: 400px; padding: 0px; margin: 0px;"></div>

		</aside>
		<section class="mm">결제창</section>
		<footer class="mm">footer</footer>

	</div>

	<button onclick="location.href='/test'">직원상태자동편성</button>
	<button onclick="location.href='/personnelComposition'">직원인원현황
	</button>
	<button onclick="location.href='/updatePebyGate'">게이트별 인원 현황</button>
	<input type="button" onclick="location.href='/schedule/permission' "
		value="결재된 스케쥴 ">
	<input type="button" onclick="location.href='/schedule/realtime' "
		value="실시간 운항정보">
	<button onclick="location.href='/member/calender'">시발달력</button>
	<form action="/attend" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}"> <input type="hidden" name="eid"
			value="${ emplyeeVo.eid}">
		<button type="submit">출근</button>
	</form>
	<form action="/logout" id="join" method="post">
		<button type="submit" name="${_csrf.parameterName}"
			value="${_csrf.token}">로그아웃</button>
	</form>

</body>
</html>