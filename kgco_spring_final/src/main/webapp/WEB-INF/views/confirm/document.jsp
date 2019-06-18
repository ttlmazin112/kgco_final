<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script src="/resources/js/jquery-3.3.1.min.js"></script>

<style>
* {
	margin: 0px;
	padding: 0px;
}

#divDocumentGrid {
	width: 400px;
	height: 450px;
	margin: auto;
	padding: 20px;
}

#divDocumentHeadGrid {
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	width: 100%;
}

table {
	border-top: 1px solid #666;
	border-left: 1px solid #666;
	border-collapse: collapse;
	width: 100%;
	height: 100%;
}
table input{
	width: 98%;
	height: 100%;
}

table th, td {
	border-right: 1px solid #666;
	border-bottom: 1px solid #666;
}

#divDocumentconfirm table td {
	width: 60px;
}

#divDocumentconfirm table tr {
	height: 24px;
}
button {
   font-style : inherit;
   border-radius: 5px;
   float: right;
   width: 80px;
   height: 40px;
   background-color: aliceblue;
}
</style>
<title>보고서</title>
</head>
<body>
	<div id="divDocumentGrid">
	<form  name="documentFrm" id="documentFrm" action="/confirm/setDocument" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div id="divDocumentHeadGrid">
			<div id="divDocumentHead">

				<table>
					<tr>
					<c:if test="${param.select eq 'vacation'}"><c:set var="documnetName" value="출타 결제서" /></c:if>
					<c:if test="${param.select eq 'work'}"><c:set var="documnetName" value="업무 보고서" /></c:if>
					<c:if test="${param.select eq 'hr'}"><c:set var="documnetName" value="인사 결제서" /></c:if>
					<c:if test="${param.select eq 'flight'}"><c:set var="documnetName" value="운항 결제서" /></c:if>

						<th><h1>${documnetName}</h1></th>
					</tr>
				</table>
			</div>
			<div id="divDocumentconfirm">
				<table>
					<tr>
						<th>담당</th>
						<th>팀장</th>
						<th>부서장</th>
					</tr>
					<tr style="height: 60px;">
						<!-- 승인이되면 png -->
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<!-- 승인자 -->
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>

		<div id="divDocumentUser">
			<table>
				<tr>
					<th><input type="hidden" name="doctype" value="${param.select}"> ${param.select}</th>
					<th><select name="type" id="selectBox" style="width: 100%; height: 100%;">
						<c:if test="${param.select eq 'vacation'}">
							<option value="vacation">휴가</option>
							<option value="early">조퇴</option>
						</c:if>
						<c:if test="${param.select eq 'work'}">
							<option value="report">보고서</option>
							<option value="lieutenant">경위서</option>
							<option value="etc">기타</option>
						</c:if>
						<c:if test="${param.select eq 'hr'}">
							<option value="position">게이트이동</option>
							<!-- <option value="promotion">진급신청</option> -->
						</c:if>
						<c:if test="${param.select eq 'flight'}">
							<option value="cancellation">결항</option>
							<option value="cancellationCancel">결항취소</option>
						</c:if>
					</select></th>
					<th><input type="hidden" name="writer" value="${param.id}">작성자 : 	${param.id}</th>
				</tr>
				<tr>
					<!-- 승인이되면 png -->
					<td>제목</td>
					<td colspan="2"><input type="text" name="subject"></td>
	
				</tr>
				<tr style="height: 100px;">
					<!-- 승인자 -->
					<td>내용</td>
					<td colspan="2"><textarea name="context" style="width: 100%; height: 98%; resize: none; margin: auto;" ></textarea></td>
				</tr>
			</table>
		</div>
		<div id="divDocumentOption">
			<table id="CheckBoxDataInsertHere"></table>
			
		</div>
		<button type="submit">제출</button>
		</form>
	</div>

</body>

<script>
function changeSelectBox(){
	var type = $('select[name=type]').val();
	var str = "";
	if(type == "vacation"){
		str += '<tr><td>희망 날짜</td><td><input type="date" name="key1"></td></tr>';
	}else if(type == "position"){ /* 인사이동 */
		str += '<caption>인사이동</caption>';
		str += '<tr><td>직원찾기</td>'
			str += '	<td><select  id="employeeFind">';
			str += '			<option value="ename">이름</option>';
			str += '			<option value="eclass">직급</option>';
			str += '			<option value="a.eid">아이디</option>';
			str += '		</select>';
			str += '		<input style="width: 60px" type="text" id="employeeText" > <input style="width: 50px" type="button"   value="검색" onclick="openWindowEmployee()" > </td></tr>';
			str += '<tr><td><input type="text" name="key1" readonly></td>';
			str += '		<td><input type="text" name="key2" id="hrSelectId" onclick="hrSelect()" readonly></td></tr>';
	}
	else if(type == "promotion"){ /* 진급신청  값 받아온거에서 디비 한번 더 갔다오기*/
		str += '<caption>진급신청</caption>';
		str += '<tr><td>직원찾기</td>'
		str += '	<td><select  id="employeeFind">';
		str += '			<option value="ename">이름</option>';
		str += '			<option value="eclass">직급</option>';
		str += '			<option value="a.eid">아이디</option>';
		str += '		</select>';
		str += '		<input style="width: 60px" type="text" id="employeeText" > <input style="width: 50px" type="button"   value="검색" onclick="openWindowEmployee()" > </td></tr>';
		str += '<tr><td colspan="2"><input type="text" name="key1" readonly></td></tr>';
	}
	
	else if(type == "cancellation"){ /* 결항*/
		str += '<caption>결항 신청</caption>';
		str += '<tr><td>비행 찾기</td>'
			str += '	<td>';
			str += '		<select id="flightFind" >';
			str += '			<option value="airFln">편명</option>';
			str += '			<option value="std">예정 시간</option>';
			str += '		</select>';
			str += '		<input style="width: 60px" type="text" id="flightText"> <input style="width: 50px" type="button"   value="검색" onclick="openWindowflight()" ></td></tr>';
			str += '<tr><td><input type="text"name="key1" readonly></td>';
			str += '	<td><input type="text"name="key2" readonly></td></tr>';
	}
	
	else if(type == "cancellationCancel"){ /* 결항 취소*/
		str += '<caption>결항 취소</caption>';
		str += '<tr><td>비행 찾기</td>'
		str += '	<td>';
		str += '		<select id="flightFind" >';
		str += '			<option value="airFln">편명</option>';
		str += '			<option value="std">예정 시간</option>';
		str += '		</select>';
		str += '		<input style="width: 60px" type="text" id="flightText"> <input style="width: 50px" type="button" value="검색" onclick="openWindowflight()" ></td></tr>';
		str += '<tr><td><input type="text"name="key1" readonly></td>';
		str += '	<td><input type="text"name="key2" readonly></td></tr>';
	}
	
	$('#CheckBoxDataInsertHere').html(str);
}

function hrSelect(){
	var status = $('#hrSelectId').val();
	if(status == 'A,B,C,D')  	 $('#hrSelectId').val('E,F,G,H');
	else if(status == 'E,F,G,H')  $('#hrSelectId').val('E,F,I,J');
	else			  		 $('#hrSelectId').val('A,B,C,D');
};



$(document).ready(function () {
	changeSelectBox();
});

$('#selectBox').on('change', changeSelectBox);

function openWindowflight(){
	var find = $('#flightFind').val();
	var text = $('#flightText').val();
	var enable = $('#selectBox').val();
	
	window.open('/confirm/searchflight?text=' + text + '&find=' + find + '&enable=' + enable ,'','width=900,height=500');   
}
function openWindowEmployee(){
	var find = $('#employeeFind').val();
	var text = $('#employeeText').val();
	
	window.open('/confirm/searchEmployee?text=' + text + '&find=' + find ,'','width=600,height=500,location=no, resizable=no');	
}

$('#documentFrm').submit(function() {
	if (document.documentFrm.key1.value == "" ||document.documentFrm.key2.value == "" ) {
		alert('입력값을 확인해주세요.');
		return false;
	}
	return true;
});



</script>
</html>