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
 <jsp:include page="/WEB-INF/views/inc/header2.jsp" />
	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row justify-content-center mb-5 pb-5">
				<div class="col-md-7 text-center heading-section ftco-animate">
					<span class="subheading">Confirm</span>
					<h2 class="mb-4">Document Box</h2>
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
							aria-controls="v-pills-nextgen" aria-selected="true">결재 예정 서류</a>
						<a class="nav-link" id="v-pills-performance-tab"
							data-toggle="pill" href="#v-pills-performance" role="tab"
							aria-controls="v-pills-performance" aria-selected="false">결재
							완료 서류</a> <a class="nav-link" id="v-pills-effect-tab"
							data-toggle="pill" href="#v-pills-effect" role="tab"
							aria-controls="v-pills-effect" aria-selected="false">결재 반려 서류</a>
					</div>
				</div>
				<div class="col-md-12 align-items-center ftco-animate">

					<div class="tab-content ftco-animate" id="v-pills-tabContent">

						<div class="tab-pane fade show active" id="v-pills-nextgen"
							role="tabpanel" aria-labelledby="v-pills-nextgen-tab">
							<div class="d-md-flex">
								<div class="container">

									<div class="row justify-content-center mb-5">
										<div
											class="col-md-7 text-center heading-section ftco-animate fadeInUp ftco-animated">
											<h2 class="mb-4">결재 진행 서류</h2>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 ftco-animate fadeInUp ftco-animated">
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th>문서 번호</th>
															<th>제목</th>
															<th>작성자</th>
															<th>날짜</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="confirmList" items="${beforelist}">
															<tr
																onclick="showConfirmDocument('${confirmList.documentNo}')">
																<td>${confirmList.documentNo}</td>
																<td>${confirmList.subject }</td>
																<td>${confirmList.writer }</td>
																<td>${confirmList.regdate }</td>
															</tr>
														</c:forEach>


													</tbody>
												</table>
											</div>
										</div>
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
											<h2 class="mb-4">결제 완료 서류</h2>

										</div>
									</div>
									<div class="row">
										<div class="col-md-12 ftco-animate fadeInUp ftco-animated">
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th>문서 번호</th>
															<th>제목</th>
															<th>작성자</th>
															<th>날짜</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="confirmList" items="${confirmList}">
															<tr
																onclick="showConfirmFinishDocument('${confirmList.documentNo}')">
																<td>${confirmList.documentNo}</td>
																<td>${confirmList.subject }</td>
																<td>${confirmList.writer }</td>
																<td>${confirmList.regdate }</td>
															</tr>
														</c:forEach>


													</tbody>
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
											<h2 class="mb-4">결재 반려 서류</h2>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 ftco-animate fadeInUp ftco-animated">
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th>문서 번호</th>
															<th>제목</th>
															<th>작성자</th>
															<th>날짜</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="confirmList" items="${returnList}">
															<tr
																onclick="showConfirmFinishDocument('${confirmList.documentNo}')">
																<td>${confirmList.documentNo}</td>
																<td>${confirmList.subject }</td>
																<td>${confirmList.writer }</td>
																<td>${confirmList.regdate }</td>
															</tr>
														</c:forEach>


													</tbody>
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
 <jsp:include page="/WEB-INF/views/inc/footer.jsp" />  

	<script src="/resources/js/jquery-3.3.1.min.js"></script>
	<script>
		function showConfirmDocument(number) {
			window.open('/confirm/chekdocument?number=' + number, '',
					'width=600,height=500,location=no, resizable=no');
		}
		function showConfirmFinishDocument(number){
			window.open('/confirm/checkFinishdocument?number=' + number  ,'','width=600,height=500,location=no, resizable=no');	
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