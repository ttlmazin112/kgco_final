<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
<meta charset="utf-8">

<meta name="author" content="Ashekur Rahman">
<meta name="description" content="">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Title -->
<title>Write.</title>
<!-- Place favicon.ico in the root directory -->
<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
<link rel="shortcut icon" type="image/ico" href="images/favicon.ico" />
<!-- Plugin-CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/linearicons.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="css/animate.css">
<!-- Main-Stylesheets -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="css/responsive.css">
<script src="js/vendor/modernizr-2.8.3.min.js"></script>
<!--[if lt IE 9]>
        <script src="//oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->

<script src="/resources/js/jquery-3.3.1.min.js"></script>


<script>
$(document).ready(function(){
	$('button.newFilesAdd').on('click', function(){
		$('div.newFilesAddDiv').append('<input type="file" name="files" multiple="multiple"/><br/>');

	});

	
});


</script>




</head>

<body data-spy="scroll" data-target=".mainmenu-area">
	<!-- Preloader-content -->
	<div class="preloader">
		<span><i class="lnr lnr-sun"></i></span>
	</div>
	<!-- MainMenu-Area -->
	<nav class="mainmenu-area" data-spy="affix" data-offset-top="200">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><img src="images/logo.png"
					alt="Logo"></a>
			</div>
			<div class="collapse navbar-collapse" id="primary_menu">

				<div class="right-button hidden-xs">
					<!-- <a href="#">Sign Up</a> -->
				</div>
			</div>
		</div>
	</nav>
	<!-- MainMenu-Area-End -->


	<header class="site-header">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 text-center">
					<h1 class="white-color"></h1>
					<ul class="breadcrumb">

					</ul>
				</div>
			</div>
		</div>
	</header>

	<div class="space-100"></div>

	<div class="container">
		<!-- 글쓰기 박스 -->
		<div class="col-xs-12 col-sm-4" style="width: 100%;">
			<div class="price-box">
				<form action="/board/boardWrite" method="post"  name="frm"  enctype="multipart/form-data">
       
     		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><!-- post로 쓰는곳마다 집어넣을것 -->
				
				<sec:authentication property="principal" var="personalinfo"/>
			
					<div class="price-header">

						<div class="price-icon">
							<table>
								<tr>
									<td>글제목</td>
									<td><input type="text" name="subject"></td>
								</tr>
								<tr>
									<td>작성자명</td>
									
									<td>
									<!-- sec:authentication property="principal.employee.eid" -->
										<input type="text" readonly="readonly" name="eid" value="${personalinfo.username }"/>
										<select name="auth">
											<c:forEach var="te" begin="1" end="${personalinfo.employee.level }" >
												<option value="${te }">
													<c:if test="${te eq 1 }">사원급</c:if>
													<c:if test="${te eq 2 }">대리급</c:if>
													<c:if test="${te eq 3 }">과장급</c:if>
													<c:if test="${te eq 4 }">차장급</c:if>
													<c:if test="${te eq 5 }">부장급</c:if>
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								

								<tr>
									<td>비밀번호</td>
									<td><input type="password" name="password" required></td>
								</tr>

								<tr>
									<td>파일 첨부</td>
									<td><input type="file" name="files" multiple="multiple">
										<div class="newFilesAddDiv"></div>
										<button type="button" class="newFilesAdd">추가 업로드</button>
									</td>
								</tr>


							</table>
						</div>

					</div>
					<div class="price-body"></div>
					<div class="price-rate">
						<span>글내용</span> <span><textarea style="resize:none" rows="13" cols="100"
								name="content"></textarea></span>
					</div>
					<div class="price-footer">
						<button type="submit">
							<a class="bttn-white" >등록</a>
						</button>
		
						<button type="button">
							<a href="/board/boardList" class="bttn-white">글목록</a>
						</button>
					</div>

				</form>

			</div>
			<div class="space-30 hidden visible-xs"></div>
		</div>
		<!-- 글쓰기 박스 끝 -->
	</div>


	<!-- Footer-Area -->
	<footer class="footer-area" id="contact_page">
		<div class="section-padding"></div>
		<!-- Footer-Bootom -->
		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-md-5">

						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						<span>Copyright &copy;<script>
							document.write(new Date().getFullYear());
						</script> All rights reserved | This template is made with <i
							class="lnr lnr-heart" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib</a></span>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						<div class="space-30 hidden visible-xs"></div>
					</div>
					<div class="col-xs-12 col-md-7">
						<div class="footer-menu">
							<ul>
								<li class="active"><a href="index.html#home_page">Home</a></li>
								<li><a href="index.html#about_page">About</a></li>
								<li><a href="index.html#features_page">Features</a></li>
								<li><a href="index.html#gallery_page">Gallery</a></li>
								<li><a href="index.html#price_page">Pricing</a></li>
								<li><a href="index.html#questions_page">FAQ</a></li>
								<li><a href="blog.html">Blog</a></li>
								<li><a href="index.html#contact_page">Contacts</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Footer-Bootom-End -->
	</footer>
	<!-- Footer-Area-End -->
	<!--Vendor-JS-->
	<script src="js/vendor/jquery-1.12.4.min.js"></script>
	<script src="js/vendor/jquery-ui.js"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<!--Plugin-JS-->
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/contact-form.js"></script>
	<script src="js/ajaxchimp.js"></script>
	<script src="js/scrollUp.min.js"></script>
	<script src="js/magnific-popup.min.js"></script>
	<script src="js/wow.min.js"></script>
	<!--Main-active-JS-->
	<script src="js/main.js"></script>
</body>

</html>
