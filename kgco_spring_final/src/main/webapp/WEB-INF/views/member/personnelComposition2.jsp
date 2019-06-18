<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<title>PersonnelComposition</title>
<meta charset="utf-8">
<link rel="icon" type="image/png" href="/resources/images/icons/favicon.png" />
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
<script >
//검색창  
function openPopupWindow()
{
	var selec =$('select[name=selec]').val();
	 var search =$('#search').val();

	var form = document.searchForm;
	  form.action = "/memberSelect"; 
   document.domain = "localhost"; //document.domain 값이 팝업과 부모창 동일해야 합니다.
   window.open("/memberSelect?selec="+selec+"&search="+search, "popup", "width=650, height=650") ;
} 

</script>
</head>
<body>
	<!-- nav -->
	<jsp:include page="/WEB-INF/views/inc/header2.jsp" />
	<!-- nav -->
	<section class="ftco-domain">
		<div class="container">
			<div class="row d-flex align-items-center pt-5">
			<div  class="row d-flex align-items-center pt-5" style="width: 500px; height: 200px;" >
				<img src="/resources/images/icons/logo2.png"  alt="User Icon"  style="width: 300px; height: 150px;"  />
				</div>
				<div  class="row d-flex align-items-center pt-5" style="width: 10px; height: 200px;" >
				</div>
				<div class="col-lg-7 py-lg-5 ftco-wrap ftco-wrap-2 ftco-animate">
					<form onsubmit="return false"  id="searchForm" name="searchForm"  method="get" class="domain-form d-flex mb-3">
						<div class="form-group domain-name">
							<input type="text"  id="search" name="search" class="form-control name px-4"
								placeholder="Enter Search">
						</div>
						<div class="form-group domain-select d-flex">
							<div class="select-wrap">
								<div class="icon">
									<span class="ion-ios-arrow-down"></span>
								</div>
								<select name="selec" id="selec" class="form-control">
									<option value="ename">이름</option>
									<option value="eclass">직급</option>
								</select>
							</div>
							<input type="submit"
							id="btnSearch"  value="검색" onclick="openPopupWindow()"
								class="search-domain btn btn-primary text-center" value="Search">
						</div>
					</form>
					<p class="domain-price mt-2">
						<span><c:set var="emplyeeVo" value="${employeeVo }"></c:set>
							${employeeVo.ename } &#40;${employeeVo.eid } &#41;
							${employeeVo.eclass }님 접속 중입니다</span>
					</p>
				</div>
			</div>
		</div>
	</section>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center mb-5">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<h2 class="mb-4">부서별 인원 현황</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 ftco-animate">
					<div class="table-responsive">
						<table class="table">
							<thead class="thead-primary">
								<tr>
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
									<tr class="color">
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
				</div>
			</div>
			<div class="row justify-content-center mb-5">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<h2 class="mb-4">게이트별 인원 현황</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 ftco-animate">
					<div class="table-responsive">
						<table class="table">
							<thead class="thead-primary">
								<tr>
									<th>게이트</th>
									<th>운송과 배치인원</th>
									<th>정비과 배치인원</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="pebyGate" items="${pebyGateList }">
									<tr class="color">
										<td>${pebyGate.gate }</td>
										<td>${pebyGate.byMaintenance }</td>
										<td>${pebyGate.byTransport }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="row justify-content-center mb-5">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<h2 class="mb-4">금일 직원 근무 현황</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 ftco-animate">
					<div class="table-responsive" style="height: 500px;">
						<table class="table">
							<thead class="thead-primary">
								<tr>
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
									<tr class="color">
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
				</div>
			</div>

		</div>
	</section>

	

	<!-- footer -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- footer -->


	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>


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
		src="/resources/https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script src="/resources/js/google-map.js"></script>
	<script src="/resources/js/main.js"></script>

</body>
</html>