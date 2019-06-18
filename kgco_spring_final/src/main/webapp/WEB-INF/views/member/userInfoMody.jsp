<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--===============================================================================================-->
<link rel="icon" type="image/png"
   href="/resources/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
   href="/resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
   href="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
   href="/resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
   href="/resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
   href="/resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/css/util.css">
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<!--===============================================================================================-->

<script src="/resources/js/jquery-3.3.1.min.js"></script>


</head>
<body>
   <div id="writeUpdateInfo" class="table1 m-b-110">
      <c:set var="member" value="${emVO }"></c:set>
      <form action="/userInfoUpdate" method="post" id="save" name="save">

         <input type="hidden" name="${_csrf.parameterName}"
            value="${_csrf.token}">
         <table class="table" id="tableList">
            <thead class="thead-primary">
               <tr>
                  <th>직원 아이디</th>
                  <td>${member.eid }</td>
               <tr>
                  <th>이름</th>
                  <td><input type="text" name="ename" value="${member.ename }"></td>
               </tr>
               <tr>
                  <th>직급</th>
                  <td>${member.eclass }</td>
               </tr>
               <tr>
                  <th>전화번호</th>
                  <td><input type="text" name="ephone"
                     value="${member.ephone }"></td>
               </tr>
            </thead>
         </table>
         <input type="submit" id="saveBtn" name="memberUpdateSave" value="저장">
      </form>
   </div>
</body>

</html>