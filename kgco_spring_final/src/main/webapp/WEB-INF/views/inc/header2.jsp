<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script>
function attend(){
	$.ajax({
		type : 'POST',
		url : '/attend',
		async : false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
		},
		success : function(result) { //할일
			location.href="/member/memberhome";
		}
	});
	
}
function logout(){
	$.ajax({
		type : 'POST',
		url : '/logout',
		async : false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
		},
		success : function(result) { //할일
			location.href="/";
		}
	});
	
}

</script>
<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<img src="/resources/images/icons/logo.png"	onclick="location.href='/member/memberhome'"  alt="User Icon"  style="width: 250px; height: 200px;"  />
				<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a onclick="attend();" class="nav-link">출근</a></li>
					<li class="nav-item"><a href="/personnelComposition" class="nav-link">근무 인원  정보</a></li>
					<li class="nav-item"><a href="/schedule/permission" class="nav-link">전체 운항 스케줄</a></li>
					<li class="nav-item"><a class="nav-link" href="/schedule/realtime">실시간 운항정보</a></li>
					<li class="nav-item"><a href="/board/boardList" class="nav-link">자유 게시판</a></li>
					<li class="nav-item"><a href="/schedule/flightStatus" class="nav-link">게이트별 현황</a></li>
					<li class="nav-item cta"><a onclick="logout();"
						class="nav-link"><span>로그아웃</span></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="hero-wrap hero-wrap-2"
		style="background-image: url('/resources/images/airbus.jpg');"
		data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-end justify-content-start">
				<div class="col-md-8 ftco-animate text-center text-md-left mb-5">
					<p class="breadcrumbs mb-0">
					</p>
				</div>
			</div>
		</div>
	</div>
</html>