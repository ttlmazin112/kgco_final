<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="/resources/images/icons/favicon.png" />
<link href="/resources/css/login.css" rel="stylesheet" type="text/css">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<title>로그인</title>
</head>
<body>
<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <img src="/resources/images/icons/logo.png"	 alt="User Icon"  style="width: 400px; height: 300px;"  />
    </div>

    <!-- Login Form -->
    <form action="/login" id="join" method="post" >
      <fieldset>
      <input type="text" id="userid" name="username" class="fadeIn second"  placeholder="아이디를 입력하세요">
      <input type="password" id="passwd" name="password" class="fadeIn third" name="epassword" placeholder="패스워드를 입력하세요">
      <input type="submit"  id="btn1" class="fadeIn fourth" value="로그인"><br/>   
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      <label>로그인 상태 유지 </label>
      <input type="checkbox" name ="remember-me">
      </fieldset>
    </form>
 
    <!-- Remind Passowrd -->

  </div>
</div>
</body>
</html>