<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Real Time</title>
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
.table-responsive {
	height: 600px;
	overflow: auto;
}

#searchTable {
	width: 97%;
}
</style>
</head>
<body>
	<!-- nav -->
	<jsp:include page="/WEB-INF/views/inc/header2.jsp" />
	<!-- nav -->

	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row justify-content-center mb-5 pb-5">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<span class="subheading">Task Update</span>
					<h2 class="mb-4">Real Time Flight</h2>
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
							aria-controls="v-pills-nextgen" aria-selected="true">Search</a> <a
							class="nav-link" id="v-pills-performance-tab" data-toggle="pill"
							href="#v-pills-performance" role="tab"
							aria-controls="v-pills-performance" aria-selected="false">Import</a>

						<a class="nav-link" id="v-pills-effect-tab" data-toggle="pill"
							href="#v-pills-effect" role="tab" aria-controls="v-pills-effect"
							aria-selected="false">Export</a>
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
										<form id="searchForm" class="domain-form d-flex mb-3">
											<div class="form-group domain-name">
												<input type="text" id="searchText" name="text"
													class="form-control name px-4"
													placeholder="Enter Flight info...">
											</div>
											<div class="form-group domain-select d-flex">
												<div class="select-wrap">

													<select name="find" class="form-control"
														style="width: 110px;">
														<option value="airFln">항공편명</option>
														<option value="airport">기준공항</option>
														<option value="arrivedEng">도착공항</option>
														<option value="boardingEng">출발공항</option>
														<option value="etd">운항공항</option>
														<option value="io">입출항</option>
														<option value="line">국내국제</option>
														<option value="rmkEng">항공편 상태</option>
														<option value="std">예정시간</option>
													</select>
												</div>
												<input type="submit"
													class="search-domain btn btn-primary text-center"
													value="Search">
											</div>
										</form>
									</div>
								</div>
								<br>

							</div>
						</div>

						<div class="tab-pane fade" id="v-pills-performance"
							role="tabpanel" aria-labelledby="v-pills-performance-tab">
							<div class="d-md-flex">
								<div class="container">
									<div class="row justify-content-center mb-5">
										<div
											class="col-md-7 text-center heading-section ftco-animate fadeInUp ftco-animated">
											<h2 class="mb-4">실시간 입항 운항정보</h2>
											<p class="setCnt"></p>

										</div>
									</div>
									<div class="row">
										<div class="col-md-12 ftco-animate fadeInUp ftco-animated">
											<div class="table-responsive">
												<table class="table" id="realTimeTableImport">
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="tab-pane fade" id="v-pills-effect" role="tabpanel"
							aria-labelledby="v-pills-effect-tab">
							<div class="d-md-flex">
								<div class="container">
									<div class="row justify-content-center mb-5">
										<div
											class="col-md-7 text-center heading-section ftco-animate fadeInUp ftco-animated">
											<h2 class="mb-4">실시간 출항 운항정보</h2>
											<p class="setCnt"></p>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 ftco-animate fadeInUp ftco-animated">
											<div class="table-responsive">
												<table class="table" id="realTimeTableExport">
												</table>
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
	</section>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-12 ftco-animate fadeInUp ftco-animated">
				<div class="table-responsive">
					<table class="table" id="searchTable">
					</table>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>


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
		function timerSet() {

			i--;
			var str = i + '초 후에 업데이트 됩니다 >_<';
			$('.setCnt').empty();
			$('.setCnt').html(str);

		}
		function realTimeUpdateSet() {
			i = j;
			var str = "";

			var expertStr = "";
			var impertStr = "";

			$('#realTimeTableExport').empty();
			$('#realTimeTableImport').empty();

			str += '<thead class="thead-primary">';
			str += '<tr>';
			str += '<th>항공편명</th>';
			str += '<th>도착공항</th>';
			str += '<th>출발공항</th>';
			str += '<th>운항공항</th>';
			str += '<th>변경시간</th>';
			str += '<th>국내국제 구분</th>';
			str += '<th>항공편 상태</th>';
			str += '<th>예정시간</th>';
			str += '</tr>';
			str += '</thead>';
			str += '<tbody>';
			expertStr += str;
			impertStr += str;
			//이번에 바뀐 친구들
			$.ajax({
				url : '/schedule/showRealTimeUpdate',
				method : 'POST',
				beforeSend : function(xhr) {
					xhr.setRequestHeader('${_csrf.headerName}',
							'${_csrf.token}');
				},
				success : function(result) {
					$.each(result, function(index, update) {

						if (update.io == 'I') {
							impertStr += "<tr>";
							impertStr += '<td class="color">' + update.airFln
									+ '</td>';
							impertStr += '<td class="color">'
									+ update.arrivedEng + '</td>';
							impertStr += '<td class="color">'
									+ update.boardingEng + '</td>';
							impertStr += '<td class="color">' + update.airport
									+ '</td>';
							impertStr += '<td class="color">' + update.etd
									+ '</td>';
							impertStr += '<td class="color">' + update.line
									+ '</td>';
							impertStr += '<td class="color">' + update.rmkEng
									+ '</td>';
							impertStr += '<td class="color">' + update.std
									+ '</td>';
							impertStr += '</tr>';
						} else if (update.io == 'O') {
							expertStr += "<tr >";
							expertStr += '<td class="color">' + update.airFln
									+ '</td>';
							expertStr += '<td class="color">'
									+ update.arrivedEng + '</td>';
							expertStr += '<td class="color">'
									+ update.boardingEng + '</td>';
							expertStr += '<td class="color">' + update.airport
									+ '</td>';
							expertStr += '<td class="color">' + update.etd
									+ '</td>';
							expertStr += '<td class="color">' + update.line
									+ '</td>';
							expertStr += '<td class="color">' + update.rmkEng
									+ '</td>';
							expertStr += '<td class="color">' + update.std
									+ '</td>';
							expertStr += '</tr>';
						}
					});
				}//SECESS
			});

			$.ajax({
				url : '/schedule/showRealTime',
				method : 'POST',
				beforeSend : function(xhr) {
					xhr.setRequestHeader('${_csrf.headerName}',
							'${_csrf.token}');
				},
				success : function(result) {
					$.each(result, function(index, update) {

						if (update.io == 'I') {
							impertStr += "<tr>";
							impertStr += '<td>' + update.airFln + '</td>';
							impertStr += '<td>' + update.arrivedEng + '</td>';
							impertStr += '<td>' + update.boardingEng + '</td>';
							impertStr += '<td>' + update.airport + '</td>';
							impertStr += '<td>' + update.etd + '</td>';
							impertStr += '<td>' + update.line + '</td>';
							impertStr += '<td>' + update.rmkEng + '</td>';
							impertStr += '<td>' + update.std + '</td>';
							impertStr += '</tr>';
						} else if (update.io == 'O') {
							expertStr += "<tr class='realTr'>";
							expertStr += '<td>' + update.airFln + '</td>';
							expertStr += '<td>' + update.arrivedEng + '</td>';
							expertStr += '<td>' + update.boardingEng + '</td>';
							expertStr += '<td>' + update.airport + '</td>';
							expertStr += '<td>' + update.etd + '</td>';
							expertStr += '<td>' + update.line + '</td>';
							expertStr += '<td>' + update.rmkEng + '</td>';
							expertStr += '<td>' + update.std + '</td>';
							expertStr += '</tr>';
						}
					});
					impertStr += '</tbody>';
					expertStr += '</tbody>';
					$('#realTimeTableExport').html(expertStr);
					$('#realTimeTableImport').html(impertStr);
				}
			});

		}
		setInterval(realTimeUpdateSet, i * 1000);
		setInterval(timerSet, 1 * 1000);

		$(document)
				.ready(
						function() {
							realTimeUpdateSet();
							$('#searchForm')
									.submit(
											function() {

												if ($('#searchText').val()
														.trim() == "") {
													alert('입력값이 없습니다.');
												} else {
													var data = $('#searchForm')
															.serialize();
													$('#searchTable').empty();
													$
															.ajax({
																url : '/schedule/showRealTimeSearch',
																method : 'POST',
																data : data,
																beforeSend : function(
																		xhr) {
																	xhr
																			.setRequestHeader(
																					'${_csrf.headerName}',
																					'${_csrf.token}');
																},
																success : function(
																		result) {
																	str = "";
																	str += '<thead class="thead-primary">';
																	str += '<tr>';
																	str += '<td>AirFln</td>';
																	str += '<td>Arrived</td>';
																	str += '<td>Board</td>';
																	str += '<td>City</td>';
																	str += '<td>ETD</td>';
																	str += '<td>I/O</td>';
																	str += '<td>D/I</td>';
																	str += '<td>Status</td>';
																	str += '<td>Std</td>';
																	str += '<td>Enable</td>';
																	str += '</tr>';
																	str += '</thead>';

																	$
																			.each(
																					result,
																					function(
																							index,
																							search) {
																						str += "<tr>";
																						str += '<td class="color">'
																								+ search.airFln
																								+ '</td>';
																						str += '<td>'
																								+ search.arrivedEng
																								+ '</td>';
																						str += '<td>'
																								+ search.boardingEng
																								+ '</td>';
																						str += '<td>'
																								+ search.airport
																								+ '</td>';
																						str += '<td>'
																								+ search.etd
																								+ '</td>';
																						str += '<td>'
																								+ search.io
																								+ '</td>';
																						str += '<td>'
																								+ search.line
																								+ '</td>';
																						str += '<td>'
																								+ search.rmkEng
																								+ '</td>';
																						str += '<td>'
																								+ search.std
																								+ '</td>';
																						str += '<td>'
																								+ search.enable
																								+ '</td>';
																						str += '</tr>';
																					});
																	$(
																			'#searchTable')
																			.html(
																					str);
																}
															});
												}
												return false;
											});
						});
	</script>
</body>
</html>