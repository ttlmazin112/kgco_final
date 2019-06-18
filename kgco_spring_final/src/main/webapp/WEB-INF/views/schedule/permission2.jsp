<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Schedule</title>
<script src="/resources/js/jquery-3.3.1.min.js"></script>
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
#gridHead {
	display: grid;
	grid-template-columns: repeat(1, 6fr 4fr);
	width: 100%;
	margin: auto;
	padding: 10px;
}

section {
	width: 700px;
}

.table-responsive {
	height: 600px;
	overflow: auto;
}

.table-containerdiv {
	padding-right: 250px;
}

#searchTable {
	width: 97%;
}

tr:hover{
 background-color: aliceblue;
}


</style>
</head>
<body>
 <jsp:include page="/WEB-INF/views/inc/header2.jsp" />
	<section class="ftco-section bg-light">
		<div id="gridHead">
			<div id="gridLeft">
				<div class="container">
					<div class="row justify-content-center mb-5 pb-5">
						<div class="col-md-7 text-center heading-section ftco-animate">
							<span class="subheading">운항 스케줄</span>
							<h2 class="mb-4">Flight Schedule</h2>
						</div>
					</div>
					<div class="row">
						<div
							class="col-md-12 nav-link-wrap mb-5 pb-md-5 pb-sm-1 ftco-animate">
							<div
								class="nav ftco-animate nav-pills justify-content-center text-center"
								id="v-pills-tab" role="tablist" aria-orientation="vertical">
								<a class="nav-link active" id="v-pills-nextgen-tab"
									data-toggle="pill" href="#v-pills-nextgen" role="tab"
									aria-controls="v-pills-nextgen" aria-selected="true">KGCO
									AIR</a> <a class="nav-link" id="v-pills-performance-tab1"
									data-toggle="pill" href="#v-pills-performance1" role="tab"
									aria-controls="v-pills-performance1" aria-selected="false">국내선
									입국</a> <a class="nav-link" id="v-pills-performance-tab2"
									data-toggle="pill" href="#v-pills-performance2" role="tab"
									aria-controls="v-pills-performance2" aria-selected="false">국내선
									출국</a> <a class="nav-link" id="v-pills-performance-tab3"
									data-toggle="pill" href="#v-pills-performance3" role="tab"
									aria-controls="v-pills-performance3" aria-selected="false">국제선
									입국</a> <a class="nav-link" id="v-pills-performance-tab4"
									data-toggle="pill" href="#v-pills-performance4" role="tab"
									aria-controls="v-pills-performance4" aria-selected="false">국제선
									출국</a>
							</div>
						</div>
						<div class="col-md-12 align-items-center ftco-animate">

							<div class="tab-content ftco-animate" id="v-pills-tabContent">

								<div class="tab-pane fade show active" id="v-pills-nextgen"
									role="tabpanel" aria-labelledby="v-pills-nextgen-tab">
									<div class="d-md-flex">
										<div class="container">
											<div
												class="col-lg-7 p-5 ftco-wrap ftco-animate fadeInUp ftco-animated"
												style="margin: auto;">
												<!--  로고  -->
											</div>
										</div>
										<br>
									</div>
								</div>
								<div class="tab-pane fade" id="v-pills-performance1"
									role="tabpanel" aria-labelledby="v-pills-performance-tab">
									<div class="d-md-flex">
										<div class="container">
											<div class="row justify-content-center mb-5">
												<div
													class="col-md-7 text-center heading-section ftco-animate fadeInUp ftco-animated">
													<h2 class="mb-4">국내선 입국</h2>
												</div>
												
												
												
												
												<div class="form-group domain-name">
													<div
														class="col-lg-7 py-lg-5 ftco-wrap ftco-wrap-2 ftco-animate fadeInUp ftco-animated"
														style="max-width: 100%;">
														<form action="#" class="domain-form d-flex mb-3">
															<div class="form-group domain-name">
																<!-- <input type="text" class="form-control name px-4" placeholder="Enter your domain name..."> -->
													<!-- class:form-group domain-select d-flex  -->
													<input type="date"
																	class= "form-control name px-4" 
																	id="domesticInDate"
																	style="height: 60px; margin-left: 23px;">
															</div>
															<div class="form-group domain-select d-flex">
																<input type="submit"
																	class="search-domain btn btn-primary text-center"
																	value="Search" style="height: 60px;"
																	onclick="domesticInDateSet();">
															</div>
														</form>
													</div>
												</div>
												
												
												
												
												
											</div>
										</div>
									</div>
								</div>

								<div class="tab-pane fade" id="v-pills-performance2"
									role="tabpanel" aria-labelledby="v-pills-effect-tab">
									<div class="d-md-flex">
										<div class="container">
											<div class="row justify-content-center mb-5">
												<div
													class="col-md-7 text-center heading-section ftco-animate fadeInUp ftco-animated">
													<h2 class="mb-4">국내선 출국</h2>
												</div>	
													
													
															
												<div class="form-group domain-name">
													<div
														class="col-lg-7 py-lg-5 ftco-wrap ftco-wrap-2 ftco-animate fadeInUp ftco-animated"
														style="max-width: 100%;">
														<form action="#" class="domain-form d-flex mb-3">
															<div class="form-group domain-name">
																<!-- <input type="text" class="form-control name px-4" placeholder="Enter your domain name..."> -->
													<!-- class:form-group domain-select d-flex  -->
													<input type="date"
																	class= "form-control name px-4" 
																	id="domesticOutDate"
																	style="height: 60px; margin-left: 23px;">
															</div>
															<div class="form-group domain-select d-flex">
																<input type="submit"
																	class="search-domain btn btn-primary text-center"
																	value="Search" style="height: 60px;"
																	onclick="domesticOutDateSet();">
															</div>
														</form>
													</div>
												</div>
													
													
													
													
													
								
												
												
												
												
												
											</div>
											<!-- 원래 테이블 자리 -->
										</div>
									</div>
								</div>

								<div class="tab-pane fade" id="v-pills-performance3"
									role="tabpanel" aria-labelledby="v-pills-effect-tab">
									<div class="d-md-flex">
										<div class="container">
											<div class="row justify-content-center mb-5">
												<div
													class="col-md-7 text-center heading-section ftco-animate fadeInUp ftco-animated">
													<h2 class="mb-4">국제선 입국</h2>
												</div>
													
													
													
													
												<div class="form-group domain-name">
													<div
														class="col-lg-7 py-lg-5 ftco-wrap ftco-wrap-2 ftco-animate fadeInUp ftco-animated"
														style="max-width: 100%;">
														<form action="#" class="domain-form d-flex mb-3">
															<div class="form-group domain-name">
																<!-- <input type="text" class="form-control name px-4" placeholder="Enter your domain name..."> -->
													<!-- class:form-group domain-select d-flex  -->
													<input type="date"
																	class= "form-control name px-4" 
																	id="internationalInDate"
																	style="height: 60px; margin-left: 23px;">
															</div>
															<div class="form-group domain-select d-flex">
																<input type="submit"
																	class="search-domain btn btn-primary text-center"
																	value="Search" style="height: 60px;"
																	onclick="internationalInDateSet();">
															</div>
														</form>
													</div>
												</div>
													
													
													
			
													
				
											
											</div>
											<div class="row">
												<div class="col-md-12 ftco-animate fadeInUp ftco-animated">
												
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="tab-pane fade" id="v-pills-performance4"
									role="tabpanel" aria-labelledby="v-pills-effect-tab">
									<div class="d-md-flex">
										<div class="container">
											<div class="row justify-content-center mb-5">
												<div
													class="col-md-7 text-center heading-section ftco-animate fadeInUp ftco-animated">
													<h2 class="mb-4">국제선 출국</h2>
												</div>	
													
													<div class="form-group domain-name">
													<div
														class="col-lg-7 py-lg-5 ftco-wrap ftco-wrap-2 ftco-animate fadeInUp ftco-animated"
														style="max-width: 100%;">
														<form action="#" class="domain-form d-flex mb-3">
															<div class="form-group domain-name">
																<!-- <input type="text" class="form-control name px-4" placeholder="Enter your domain name..."> -->
													<!-- class:form-group domain-select d-flex  -->
													<input type="date"
																	class= "form-control name px-4" 
																	id="internationalOutDate"
																	style="height: 60px; margin-left: 23px;">
															</div>
															<div class="form-group domain-select d-flex">
																<input type="submit"
																	class="search-domain btn btn-primary text-center"
																	value="Search" style="height: 60px;"
																	onclick="internationalOutDateSet();">
															</div>
														</form>
													</div>
												</div>
													
													
												
												
												
												
											</div>
											
											

											
											
											
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>



			<div class="table-containerdiv">

			

<!-- table start -->


				<div class="row">
					<div class="col-md-12 ftco-animate fadeInUp ftco-animated">
						<div class="table-responsive">
							<table class="table" id="ShowApiViewGridBody">
							</table>
						</div>
					</div>
				</div>

	<!-- table end -->


			</div>
		</div>
	</section>
 <jsp:include page="/WEB-INF/views/inc/footer.jsp" />  
	<br>
	<br>
	<br>
	<br>
	<br>



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
		function windowOpen(a, b, c, d, e) {
	//		alert(a + ' ' + b + ' ' + c + ' ' + d + ' ' + e);

			window.open('/schedule/permissionScheduleDetail?airFln=' + a
					+ '&start=' + b + '&date=' + c + '&diType=' + d
					+ '&ioType=' + e, '',
					'width=850,height=300,location=no, resizable=no');

		}

	

		/*할일 : 주소창없애야함
		var windowObjectReference;
		var strWindowFeatures = "menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes";

		function openRequestedPopup() {
		  windowObjectReference = window.open("http://www.cnn.com/", "CNN_WindowName", strWindowFeatures);
		} */
	</script>
	<!-- ----------------------------------------------------------------------------------------------------- -->
	<script>
		function domesticOutDateSet() {
			var date = document.getElementById("domesticOutDate").value;
			
			if(date==''){
				 var local=new Date();
				var year=local.getFullYear();
				
				var month=local.getMonth()+1;
				var day=local.getDay();
				date=year+'0'+month+'0'+day;
			}
			
			
			
			var str = "";
			var diType = "d";
			var io = "OUT";
			$('#ShowApiViewGridBody').empty();
			str += '<thead class="thead-primary">';
			str += '</thead>';
			str += '<tr>';
			str += '<td>Flight No.</td>';
			str += '<td>Departure Time</td>';
			str += '<td>City</td>';
			str += '</tr>';
			str += '<tbody>';

			var csrfHeaderName = '${_csrf.headerName}';
			var csrfTokenValue = '${_csrf.token}';

			$.ajax({
				type : 'POST',
				url : '/schedule/showDomesticBySelectedDate',
				data : {
					date : date,
					io : "OUT"
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				},
				success : function(result) { //할일
					
					$.each(result, function(index, domestic) {

						str += "<tr class='trhover' onclick= \"windowOpen('"  //할일 HOVER
								+ domestic.domesticNum + "','"
								+ domestic.domesticStartTime + "','" + date
								+ "','" + diType + "','" + io + "')\"   >";

						str += '<td>' + domestic.domesticNum + '</td>';
						str += '<td>' + domestic.domesticStartTime + '</td>';
						
						str += '<td>' + domestic.arrivalcity + '</td>';

						str += '</tr>';
					});

					$('#ShowApiViewGridBody').html(str);
				}
			});
		}

		function domesticInDateSet() {
			var date = document.getElementById("domesticInDate").value;
			if(date==''){
				 var local=new Date();
				var year=local.getFullYear();
				
				var month=local.getMonth()+1;
				var day=local.getDay();
				date=year+'0'+month+'0'+day;
			}
			var str = "";
			var diType = "d";
			var io = "IN";
			$('#ShowApiViewGridBody').empty();
			str += '<thead class="thead-primary">';
			str += '</thead>';
			str += '<tr>';
			str += '<td>Flight No.</td>';
			str += '<td>Arrival Time</td>';
			str += '<td>City</td>';
			str += '</tr>';
			str += '<tbody>';

			$.ajax({
				type : 'POST',
				url : '/schedule/showDomesticBySelectedDate',
				data : {
					date : date,
					io : "IN"
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader('${_csrf.headerName}',
							'${_csrf.token}');
				},
				success : function(result) { //할일
					
					$.each(result, function(index, item) {
						str += "<tr onclick= \"windowOpen('"
								+ item.domesticNum + "','"
								+ item.domesticStartTime + "','" + date + "','"
								+ diType + "','" + io + "')\"   >";
						str += '<td>' + item.domesticNum + '</td>';
						str += '<td>' + item.domesticArrivalTime + '</td>';
						str += '<td>' + item.startcity + '</td>';
						str += '</tr>';
						str += '</tbody>';
					});
					$('#ShowApiViewGridBody').html(str);
				}
			});
		}

		function internationalOutDateSet() {
			var date = document.getElementById("internationalOutDate").value;
			if(date==''){
				 var local=new Date();
				var year=local.getFullYear();
				
				var month=local.getMonth()+1;
				var day=local.getDay();
				date=year+'0'+month+'0'+day;
			}
			var str = "";
			var diType = "i";
			var io = "OUT";
			$('#ShowApiViewGridBody').empty();

			str += '<thead class="thead-primary">';
			str += '</thead>';
			str += '<tr>';
			str += '<td>Flight No.</td>';
			str += '<td>Departure Time</td>';
			str += '<td>City</td>';
			str += '</tr>';
			str += '<tbody>';
			$.ajax({
				type : 'POST',
				url : '/schedule/showInternationalBySelectedDate',
				data : {
					date : date,
					io : "OUT"
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader('${_csrf.headerName}',
							'${_csrf.token}');
				},
				success : function(result) { //할일

					$.each(result, function(index, item) {

						str += "<tr  onclick= \"windowOpen('"
								+ item.internationalNum + "','"
								+ item.internationalTime + "','" + date + "','"
								+ diType + "','" + io + "')\"   >";
						str += '<td>' + item.internationalNum + '</td>';
						str += '<td>' + item.internationalTime + '</td>';
						str += '<td>' + item.city + '</td>';
						str += '</tr>';
					});
					$('#ShowApiViewGridBody').html(str);
				}
			});
		}
		function internationalInDateSet() {
			var date = document.getElementById("internationalInDate").value;
			if(date==''){
				 var local=new Date();
				var year=local.getFullYear();
				
				var month=local.getMonth()+1;
				var day=local.getDay();
				date=year+'0'+month+'0'+day;
			}
			var str = "";
			var diType = "i";
			var io = "IN";
			$('#ShowApiViewGridBody').empty();
			str += '<thead class="thead-primary">';
			str += '</thead>';
			str += '<tr>';
			str += '<td>Flight No.</td>';
			str += '<td>Arrival Time</td>';
			str += '<td>City</td>';
			str += '</tr>';
			str += '<tbody>';

			$.ajax({
				type : 'POST',
				url : '/schedule/showInternationalBySelectedDate',
				data : {
					date : date,
					io : "IN"
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader('${_csrf.headerName}',
							'${_csrf.token}');
				},
				success : function(result) {
				
					$.each(result, function(index, item) {
						str += "<tr  onclick= \"windowOpen('"
								+ item.internationalNum + "','"
								+ item.internationalTime + "','" + date + "','"
								+ diType + "','" + io + "')\"   >";
						str += '<td>' + item.internationalNum + '</td>';
						str += '<td>' + item.internationalTime + '</td>';
						str += '<td>' + item.city + '</td>';
						str += '</tr>';
					});
					$('#ShowApiViewGridBody').html(str);
				}
			});
		}
	</script>
</html>