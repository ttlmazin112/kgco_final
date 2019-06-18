<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Flight Status</title>
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
* {
	margin: auto;
	padding: 1px;
}

#FlightStatusGrid {
	background-image: url("/resources/images/FlightStatus/TopView.png");
	background-repeat: no-repeat;
	background-size: cover;
	height: 800px;
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	width: 70%;
	margin: auto;
	padding: 0px;
	min-height: 100%;
	background-position: center;
	background-size: cover;
}

#FlightStatusGrid div {
	padding: 0px;
}

.gate input {
	width: 100px;
	font-size: 15px;
	border: none;
	background: transparent;
	font-weight: bold;
	text-align: center;
}


.gate {
	text-align: center;
}
</style>
</head>
<body>

<!-- nav -->
	<jsp:include page="/WEB-INF/views/inc/header2.jsp" />
	<!-- nav -->
	<div class="hero-wrap hero-wrap-2"
		style="background-image: url('images/bg_1.jpg');"
		data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-end justify-content-start">
				<div class="col-md-8 ftco-animate text-center text-md-left mb-5">
					<p class="breadcrumbs mb-0">
						<span class="mr-3"><a href="index.html">Home <i
								class="ion-ios-arrow-forward"></i></a></span> <span>About</span>
					</p>
					<h1 class="mb-3 bread">About</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section ftco-no-pt">
		<div class="container">
			<div class="row d-md-flex">
				<div id="FlightStatusGrid"
					class="col-md-6 ftco-animate img about-image"
					style="background-image: url('/resources/images/FlightStatus/TopView.png');">
					<div class="gate">
						<input type="text" id="gateA"></input><br>GATE_A
					</div>
					<div class="gate">
						<input type="text" id="gateC"></input><br>GATE_C
					</div>
					<div class="gate">
						<input type="text" id="gateE"></input><br>GATE_E
					</div>
					<div class="gate">
						<input type="text" id="gateG"></input><br>GATE_G
					</div>
					<div class="gate">
						<input type="text" id="gateB"></input><br>GATE_B
					</div>
					<div class="gate">
						<input type="text" id="gateD"></input><br>GATE_D
					</div>
					<div class="gate">
						<input type="text" id="gateF"></input><br>GATE_F
					</div>
					<div class="gate">
						<input type="text" id="gateH"></input><br>GATE_H
					</div>
					<div></div>
					<div class="gate">
						spare<br>GATE_I
					</div>
					<div class="gate">
						spare<br>GATE_J
					</div>
				</div>
				<div class="col-md-6 ftco-animate ">
					<div class="row py-md-5">
						<div class="col-md-12 nav-link-wrap mb-5">
							<div class="nav ftco-animate nav-pills" id="v-pills-tab"
								role="tablist" aria-orientation="vertical">
								<a class="nav-link active" id="v-pills-whatwedo-tab"
									data-toggle="pill" href="#v-pills-whatwedo" role="tab"
									aria-controls="v-pills-whatwedo" aria-selected="true">Import</a>
								<a class="nav-link" id="v-pills-mission-tab" data-toggle="pill"
									href="#v-pills-mission" role="tab"
									aria-controls="v-pills-mission" aria-selected="false">Export</a>
								<span class="setCnt"></span>
							</div>
						</div>
						<div class="col-md-12 d-flex align-items-center">

							<div class="tab-content ftco-animate" id="v-pills-tabContent">

								<div class="tab-pane fade show active" id="v-pills-whatwedo"
									role="tabpanel" aria-labelledby="v-pills-whatwedo-tab">
									<div>
										<table id="inGate" class="table">
											<thead class="thead-primary">
												<tr>
													<th class="color">Gate</th>
													<th>AirFln</th>
													<th>State</th>
													<th>Number</th>
													<th>Std</th>
												</tr>
											</thead>
											<tr id="gateTrA"></tr>
											<tr id="gateTrB"></tr>
											<tr id="gateTrC"></tr>
											<tr id="gateTrD"></tr>
										</table>
									</div>
								</div>

								<div class="tab-pane fade" id="v-pills-mission" role="tabpanel"
									aria-labelledby="v-pills-mission-tab">
									<div>
										<table id="outGate" class="table">
											<thead class="thead-primary">
												<tr>
													<th class="color">Gate</th>
													<th>AirFln</th>
													<th>State</th>
													<th>Number</th>
													<th>Std</th>
												</tr>
											</thead>
											<tr id="gateTrE"></tr>
											<tr id="gateTrF"></tr>
											<tr id="gateTrG"></tr>
											<tr id="gateTrH"></tr>
										</table>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!-- footer -->
	 <jsp:include page="/WEB-INF/views/inc/footer.jsp" />  
	<!-- footer -->




	<!-- loader -->

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
	<script src="/resources/js/jquery-3.3.1.min.js"></script>
	<script>
	var j = 30;
	var i = j;
	function timerSet(){
		
		i--;
		var str = i + '초 후에 업데이트 됩니다.';
		$('.setCnt').empty();
		$('.setCnt').html(str);
		
	}
	setInterval(timerSet, 1 * 1000);
	setInterval(statusUpdate, i * 1000);
	function statusUpdate() {
			i = 30;
			
			
			
			
			var str = "";
			//Out EGFH
			//In  ABCD
			$.ajax({
				url : '/schedule/flightStatus',
				method : 'POST',
				beforeSend : function(xhr) {
					xhr.setRequestHeader('${_csrf.headerName}',
							'${_csrf.token}');
				},
				success : function(result) {

					var gateListOut = result.gateListOut;
					var gateListIn = result.gateListIn;

					$.each(gateListOut, function(index, status) {

						str = "";

						str += '<td class="color">' + status.gate + '</td>';
						str += '<td>' + status.airFln + '</td>';
						str += '<td>' + status.gateState + '</td>';
						str += '<td>' + status.airNumber + '</td>';
						str += '<td>' + status.std + '</td>';

						if (status.gate == 'E') {
							$('#gateTrE').html(str);
							$('#gateE').val(status.gateState);
						}
						if (status.gate == 'F') {
							$('#gateTrF').html(str);
							$('#gateF').val(status.gateState);
						}
						if (status.gate == 'G') {
							$('#gateTrG').html(str);
							$('#gateG').val(status.gateState);
						}
						if (status.gate == 'H') {
							$('#gateTrH').html(str);
							$('#gateH').val(status.gateState);
						}
					});
					$.each(gateListIn, function(index, status) {

						str = "";

						str += '<td class="color">' + status.gate + '</td>';
						str += '<td>' + status.airFln + '</td>';
						str += '<td>' + status.gateState + '</td>';
						str += '<td>' + status.airNumber + '</td>';
						str += '<td>' + status.std + '</td>';

						if (status.gate == 'A') {
							$('#gateTrA').html(str);
							$('#gateA').val(status.gateState);
						}
						if (status.gate == 'B') {
							$('#gateTrB').html(str);
							$('#gateB').val(status.gateState);
						}
						if (status.gate == 'C') {
							$('#gateTrC').html(str);
							$('#gateC').val(status.gateState);
						}
						if (status.gate == 'D') {
							$('#gateTrD').html(str);
							$('#gateD').val(status.gateState);
						}
					});
				}
			});

		}

		

		
		$(document).ready(function() {	 	statusUpdate();
		});
		

	</script>
</body>
</html>