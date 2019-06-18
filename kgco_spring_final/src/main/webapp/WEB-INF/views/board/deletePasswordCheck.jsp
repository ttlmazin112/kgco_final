<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,,500,600,700" rel="stylesheet">

    <link rel="stylesheet" href="/resources/css/open-iconic-bootstrap.min.css">
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
    <link rel="stylesheet" href="/resources/css/board.css">
  <style type="text/css">.scrollax-performance, .scrollax-performance *, .scrollax-performance *:before, .scrollax-performance *:after { pointer-events: none !important; -webkit-animation-play-state: paused !important; animation-play-state: paused !important; };</style><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/37/4/intl/ko_ALL/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/37/4/intl/ko_ALL/util.js"></script></head>


<title>Password Check</title>
</head>
<body>

 <jsp:include page="/WEB-INF/views/inc/header2.jsp" />

<div class="container">
<div class="space-100"></div>
<div class="space-100"></div>
<div class="space-100"></div>
<div class="space-50"></div>
<div class="col-lg-7 p-5 ftco-wrap ftco-animate fadeInUp ftco-animated" style="margin: auto;">

		<form action="/board/boardDelete" method="post"  class="domain-form d-flex mb-3">
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
             <input type="hidden" name="boardId" value="${param.boardId }">
             <input type="hidden" name="bWriter" value="${board.eid }">
             		 <div class="form-group domain-name">
              	
              		
                <input type="password" name="pass"  class="form-control name px-4" placeholder="게시글의 비밀번호를 입력하세요..">
              </div>
              <div></div>
              <div class="form-group domain-select d-flex">
	          
	              <div class="select-wrap">
                  <div></div>
                 
                </div>
                <button class="search-domain btn btn-primary text-center" type="submit">확인</button>
                
	            </div>
            </form>
     
    			</div>



</div>
<div class="space-100"></div>
<div class="space-100"></div>
<div class="space-100"></div>
<div class="space-100"></div>
 <jsp:include page="/WEB-INF/views/inc/footer.jsp" />  
</body>
</html>