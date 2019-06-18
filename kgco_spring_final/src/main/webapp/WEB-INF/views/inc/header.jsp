<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<span style="font-size:10px; text-align:right;">
<c:set var="emplyeeVo" value="${emplyeeVo }"></c:set>
${emplyeeVo.ename } &#40;${emplyeeVo.eid } &#41; ${emplyeeVo.eclass }님
접속 중입니다
</span>
<title>상단 영역</title>

<link rel="stylesheet" href="/resources/css/bootstrap.min.css">



<script type="text/javascript">
	function changeView(value) {

		if (value == "0") {
			location.href = "";
		} else if (value == "1") {
			location.href = "";
		} else if (value == "2") {
			location.href = "";
		} else if (value == "3") {
			window.confirm("로그아웃되었습니다.")
			var form = document.forms['logoutForm'];
			form.action = '/logout'
			form.submit();
		} else if (value == "4") {
			location.href = "";
		} else if (value == "5") {
			location.href = "";
		} else if (value == "6") {
			location.href = "";
		} else if (value == "7") {
			location.href = "";
		} else if (value == "8") {
			location.href = "/test";
		}
	}
</script>
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

	<div id="wrap">
		<p>
			<button class="btn btn-success" onclick="changeView(0)">버튼</button>

			<!-- // 로그인 안되었을 경우 - 로그인, 회원가입 버튼을 보여준다. -->
			<c:if test="${sessionScope.id==null}">
				<button id="loginBtn" class="btn btn-primary"
					onclick="changeView(1)">버튼</button>
				<button id="joinBtn" class="btn btn-primary" onclick="changeView(2)">버튼</button>
			</c:if>

			<!-- // 로그인 되었을 경우 - 로그아웃, 내정보 버튼을 보여준다. -->
			<c:if test="${emplyeeVo.eid ne null}">
				<form action="/logout" name="logoutForm" method="post">
					<button type="submit" id="logoutBtn" class="btn btn-primary"
						onclick="changeView(3)">로그아웃</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}">
				</form>
			</c:if>
			<button id="updateBtn" class="btn btn-primary"
				onclick="changeView(8)">직원상태자동편성</button>
			<button id="joinBtn" class="btn btn-info" onclick="changeView(6)">버튼</button>
			<button id="joinBtn" class="btn btn-info" onclick="changeView(7)">버튼</button>

			<!--  관리자 로그인 -->
			<c:if test="${sessionScope.id !=null && sessionScope.id=='admin'}">
				<button id="memberViewBtn" class="btn btn-warning"
					onclick="changeView(5)">버튼</button>
				<button id="updateBtn" class="btn btn-primary"
					onclick="changeView(4)">버튼</button>

			</c:if>
			<br>

		</p>
	</div>
   <!-- 전체 검색 -->
<form onsubmit="return false"  id="searchForm" name="searchForm"  method="get" >
<select name="selec"  id="selec" style="height: 20px; font-size: small;">
   <option value="ename">이름</option>
   <option value="eclass">직급</option>
</select>
<%--  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> --%>
<input type="text"  id="search" name="search" style="font-size: small;"> <input type="submit" id="btnSearch"  value="검색" onclick="openPopupWindow()" style="font-size: small;" >
</form>	

</body>
</html>