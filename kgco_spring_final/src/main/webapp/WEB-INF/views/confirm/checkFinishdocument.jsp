<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
   uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>

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
	
#divDocumentconfirm table tr td img{
	padding: 9px;
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
<!-- 보고 전체 페이지 -->
</head>
<body>
	<div id="divDocumentGrid">
	
		<div id="divDocumentHeadGrid">
			<div id="divDocumentHead">

				<table>
					<tr>
					<c:if test="${documentVO.doctype eq 'vacation'}"><c:set var="documnetName" value="출타 결제서" /></c:if>
					<c:if test="${documentVO.doctype eq 'work'}"><c:set var="documnetName" value="업무 보고서" /></c:if>
					<c:if test="${documentVO.doctype eq 'hr'}"><c:set var="documnetName" value="인사 결제서" /></c:if>
					<c:if test="${documentVO.doctype eq 'flight'}"><c:set var="documnetName" value="운항 결제서" /></c:if>

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
						<!-- 보고 -->
						<td><c:if test="${documentVO.confirm1 != null}"><img src="/resources/images/confirm/comfirm.png"/></c:if></td>
						<td><c:if test="${documentVO.confirm2 != null}"><img src="/resources/images/confirm/comfirm.png"/></c:if></td>
						<td><c:if test="${documentVO.confirm3 != null}"><img src="/resources/images/confirm/comfirm.png"/></c:if></td>
							<!-- 보고 -->
					</tr>
					<tr>
						<!-- 승인자 -->
						<td style="font-size: 12px;">${documentVO.confirm1}</td>
						<td style="font-size: 12px;">${documentVO.confirm2}</td>
						<td style="font-size: 12px;">${documentVO.confirm3}</td>
					</tr>
				</table>
			</div>
		</div>

		<div id="divDocumentUser">
			<table>
				<tr>
					<th> ${documentVO.doctype}</th>
					<th> ${documentVO.type}</th>
					<th>작성자 : 	${documentVO.writer}</th>
				</tr>
				<tr>
					<!-- 승인이되면 png -->
					<td>제목</td>
					<td colspan="2"> ${documentVO.subject}</td>
	
				</tr>
				<tr style="height: 100px;">
					<!-- 승인자 -->
					<td>내용</td>
					<td colspan="2"><textarea name="context"   style="width: 100%; height: 98%; resize: none; margin: auto;" readonly="readonly">${documentVO.context}</textarea></td>
				</tr>
			</table>
		</div>
		<div id="divDocumentOption">
			<c:if test="${documentVO.doctype eq 'vacation'}">
				<c:if test="${documentVO.key1 != null}">
				신청 날짜 : ${documentVO.key1}
				</c:if>
			</c:if>
			<c:if test="${documentVO.doctype eq 'hr'}">
				<c:if test="${documentVO.type eq 'position'}">
				부서이동<br>
				대상 : ${documentVO.key1} <br>
				이동 : ${documentVO.key2}
				</c:if>
				
				<c:if test="${documentVO.type eq 'promotion'}">
				진급대상<br>
				대상 : ${documentVO.key1} <br>
				</c:if>
			</c:if>
			
			<c:if test="${documentVO.doctype eq 'flight'}">
				<c:if test="${documentVO.type eq 'cancellation'}">
				결항신청<br>
				대상 : ${documentVO.key1} <br>
				</c:if>
				<c:if test="${documentVO.type eq 'cancellationCancel'}">
				결항 취소 신청<br>
				대상 : ${documentVO.key2} <br>
				</c:if>
			</c:if>
			
		</div> 

		<!-- 결제, 취소, 닫기 만들기 -->
		<div> 

		<button onclick="window.close()">닫기</button> 
		</div>


	</div>

</body>
</html>