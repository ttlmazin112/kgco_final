<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<style type="text/css">
td {
	width: 60px;
	height: 60px;
	text-align: center;
	font-size: 20px;
	font-family: 바탕;
	border: 2px solid #007bff;
	border-radius: 8px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<title>Calender</title>
<script language="javascript" type="text/javascript">
	var today = new Date(); // 오늘 날짜//지신의 컴퓨터를 기준으로
	//today 에 Date객체를 넣어줌 //ex)5일  
	function prevCalendar() {

		today = new Date(today.getFullYear(), today.getMonth() - 1, today
				.getDate());
		buildCalendar(); // 현재 달 
	}

	function nextCalendar() {

		today = new Date(today.getFullYear(), today.getMonth() + 1, today
				.getDate());
		buildCalendar();
	}

	function buildCalendar() {// 현재 달fur
		var nMonth = new Date(today.getFullYear(), today.getMonth(), 1); // 이번 달의 첫째 날
		var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0); // 이번 달의 마지막 날
		var tblCalendar = document.getElementById("calendar"); // 테이블 달력을 만들 테이블
		var tblCalendarYM = document.getElementById("calendarYM"); // yyyy년 m월 출력할 곳
		tblCalendarYM.innerHTML = today.getFullYear() + "년 "
				+ (today.getMonth() + 1) + "월"; // yyyy년 m월 출력
		yearMonth = today.getFullYear() + "-" + (today.getMonth() + 1);
		var resultObj = workNote();
		var keys = Object.keys(resultObj);
		var valuess= Object.values(resultObj);
		// 기존 테이블에 뿌려진 줄, 칸 삭제

		while (tblCalendar.rows.length > 2) {
			tblCalendar.deleteRow(tblCalendar.rows.length - 1);
		}
		var row = null;
		row = tblCalendar.insertRow();
		var cnt = 0;
		// 1일이 시작되는 칸을 맞추어 줌
		for (i = 0; i < nMonth.getDay(); i++) {
			cell = row.insertCell();
			cnt = cnt + 1;
		}

		for (i = 1; i <= lastDate.getDate(); i++) {
			var numdate= "";
			if(i<10){
				numdate = "0"+i;
			}else{
				numdate =i;
			}
			
			var numMonth= today.getMonth()+1;
			if(numMonth<10){
				numMonth = "0"+numMonth;
			}
			
			var keydate = today.getFullYear()+"-"+numMonth+"-"+numdate;
			console.log(keydate);
			console.log("벨류값 ->"+resultObj[keydate]);
			cell = row.insertCell();
			var str;
			//$(#cell).html("i");  
			//배경색 선정 
			if (resultObj[keydate] == 'nor') {
				// 정상출근 : 녹색 
				str = "<div style=' height:55px;  width:55px; background-color: #008000; display: table-cell; vertical-align: middle;'>"
						+ i + "</div>";
			} else if (resultObj[keydate] == 'late') {
				// 지각 : 노란색 
				str = "<div style=' height:55px;  width:55px; background-color: #ffff00; display: table-cell; vertical-align: middle;'>"
						+ i + "</div>";
			} else if (resultObj[keydate] == 'abse') {
				// 결근 : 빨간색 
				str = "<div style=' height:55px;  width:55px; background-color: #ff0000; display: table-cell; vertical-align: middle;'>"
						+ i + "</div>";
			} else if (resultObj[keydate] == 'va') {
				// 휴가 : 파랑색  
				str = "<div style=' height:55px;  width:55px; background-color: #0000ff; display: table-cell; vertical-align: middle;'>"
						+ i + "</div>";
			}else if (resultObj[keydate] == 'early') {
				// 휴가 : 파랑색  
				str = "<div style=' height:55px;  width:55px; background-color: #6610f2; display: table-cell; vertical-align: middle;'>"
						+ i + "</div>";
			}else if(resultObj[keydate] == null){
				str = i;
			}else{
				str = i;
			}
			cell.innerHTML = str;
			cnt = cnt + 1;
			if (cnt % 7 == 0)// 1주일이 7일 이므로
				row = calendar.insertRow();// 줄 추가
		}

	}

	function workNote() {
		var date = yearMonth;
		$.ajax({
			type : 'POST',
			url : '/member/calender',
			async : false,
			data : {
				date : date

			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
			},
			success : function(result) { //할일
				$.each(result, function(key, value) {
					console.log('key:' + key + ' / ' + 'value:' + value);
				});
				resultObj = result;
			}
		});
		return resultObj;
	}
</script>

</head>
<body>
	<table id="calendar" boarder="3" align="center">
		<tr>
			<td><label onclick="prevCalendar()"><</label></td>
			<td colspan="5" align="center" id="calendarYM">yyyy년 m월</td>
			<td><label onclick="nextCalendar()">> </label></td>
		</tr>
		<tr>
			<td align="center">일</td>
			<td align="center">월</td>
			<td align="center">화</td>
			<td align="center">수</td>
			<td align="center">목</td>
			<td align="center">금</td>
			<td align="center">토</td>
		</tr>


	</table>
	<script language="javascript" type="text/javascript">
		buildCalendar();
	</script>
</body>
</html>