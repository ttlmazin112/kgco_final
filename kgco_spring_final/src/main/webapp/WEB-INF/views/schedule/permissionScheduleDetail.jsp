<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/> 
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>운항일정 상세보기</title>

<style>



</style>

<link rel="stylesheet" href="/resources/css/scheDetail.css">
<script src="/resources/js/jquery-3.3.1.min.js"></script>

</head>
<body>
	
	
	<c:set var="diType" value="${param.diType }" />



	<c:if test="${diType eq 'd' }">
	
	
		<div class="flight-info-detail-heading">
			<div class="flight-info-detail-heading-title">Flight Info</div>
		</div>



	<table style="float:left; " >
			<tr>
				<th>항공사</th>
				<td>${domesticDetailDto.airlineKorean }</td>
			</tr>
			<tr>
				<th>항공편명</th>
				<td>${domesticDetailDto.domesticNum }</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${param.date }</td>
			</tr>
			<tr>
	
	</table>
	
	<table style="float:right; ">
	
		<tr>
				<th>일정 시작일</th>
				<td>${domesticDetailDto.domesticStdate }</td>
			</tr>
			<tr>
				<th>일정 종료일</th>
				<td>${domesticDetailDto.domesticEddate }</td>
			</tr>

	
	</table>
	
	
	
			<div class="td-like colspan-2" style="width: 389px;">

								
								<div class="flight-info-detail-airline">
									<div class="flight-info-detail-from f-left">
										<div class="flight-info-detail-date">
											From</div>
										<div class="flight-info-detail-country">										
											<strong> ${domesticDetailDto.startcity }</strong>
										</div>
										<div class="flight-time">${domesticDetailDto.domesticStartTime }</div>
									</div>
									
									
									
									<div class="flight-info-detail-from f-right">
										<div class="flight-info-detail-date">
											To</div>
										<div class="flight-info-detail-country">
																						
												<strong>
													${domesticDetailDto.arrivalcity }
												</strong>
											
										</div>
										
										<div class="flight-time">${domesticDetailDto.domesticArrivalTime }</div>
									</div>
								</div>
			</div>
	</c:if>



	<c:if test="${diType eq 'i' }">
	
		<div class="flight-info-detail-heading">
			<div class="flight-info-detail-heading-title">Flight Info</div>
		</div>
		
	<table style="float:left; " >
			<tr>
				<th>항공사</th>
				<td>${internationalDetailDto.airlineKorean }</td>
			</tr>
			<tr>
				<th>항공편명</th>
				<td>${internationalDetailDto.internationalNum }</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${param.date }</td>
			</tr>
			<tr>
	
	</table>
	
	<table style="float:right; ">
	
		<tr>
				<th>일정 시작일</th>
				<td>${internationalDetailDto.internationalStdate }</td>
			</tr>
			<tr>
				<th>일정 종료일</th>
				<td>${internationalDetailDto.internationalEddate }</td>
			</tr>

	
	</table>
	
	
				<div class="td-like colspan-2" style="width: 389px;">

								
								<div class="flight-info-detail-airline">
									<div class="flight-info-detail-from f-left">
										<div class="flight-info-detail-date">
											From</div>
										<div class="flight-info-detail-country">										
											<strong> ${internationalDetailDto.airport }</strong>
										</div>
										<div class="flight-time">${internationalDetailDto.internationalTime }</div>
									</div>
									
									
									
									<div class="flight-info-detail-from f-right">
										<div class="flight-info-detail-date">
											To</div>
										<div class="flight-info-detail-country">
																						
												<strong>
													${internationalDetailDto.city }
												</strong>
											
										</div>
										
										<div class="flight-time"></div>
									</div>
								</div>
			</div>
	
	
	
	



	</c:if>



</body>



</html>