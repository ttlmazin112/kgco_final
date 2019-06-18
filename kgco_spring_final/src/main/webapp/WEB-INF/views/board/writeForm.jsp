<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Write</title>
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
<link rel="stylesheet" href="/resources/css/board.css">

<style type="text/css">
.scrollax-performance, .scrollax-performance *, .scrollax-performance *:before,
	.scrollax-performance *:after {
	pointer-events: none !important;
	-webkit-animation-play-state: paused !important;
	animation-play-state: paused !important;
};
</style>

<script type="text/javascript" charset="UTF-8"
	src="https://maps.googleapis.com/maps-api-v3/api/js/37/4/intl/ko_ALL/common.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="https://maps.googleapis.com/maps-api-v3/api/js/37/4/intl/ko_ALL/util.js"></script>
</head>



<body data-aos-easing="slide" data-aos-duration="800" data-aos-delay="0">
    
	   <jsp:include page="/WEB-INF/views/inc/header2.jsp" />

    <div class="hero-wrap hero-wrap-2" style="background-image: url(&quot;images/bg_1.jpg&quot;); background-position: 50% 0px;" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-start">
          <div class="col-md-8 ftco-animate text-center text-md-left mb-5 fadeInUp ftco-animated">
          	
            
          </div>
        </div>
      </div>
    </div>

    <section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-md-8 ftco-animate fadeInUp ftco-animated">
            <h2 class="mb-3">게시글 작성</h2>
            
            
            
            
            
            
            
            
            
            
            
            
            


            <div class="pt-5 mt-5">
  
              
              <!-- END comment-list -->
              
              <div class="comment-form-wrap pt-5">

							<form action="/board/boardWrite" method="post"  name="frm"  enctype="multipart/form-data" class="p-5 bg-light">
							 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><!-- post로 쓰는곳마다 집어넣을것 -->
					
							<sec:authentication property="principal" var="personalinfo"/>	
							
								<div class="form-group">
									<label for="name">제목</label> 
									<input type="text"
										class="form-control" name="subject">
								</div>

							
									<div class="form-group d-flex align-items-center pt-5">

											<div class="form-group domain-name">
												<label for="email">작성자</label> 
												<input type="text"
													readonly="readonly" 
													name="eid" value="${personalinfo.username}"
													class="form-control name px-4">
											</div>
											
									
											<label class="margin20">게시물을</label>
											
											<div class="domain-select d-flex">
												<!-- <div class="icon">
													<span class="ion-ios-arrow-down"></span>
												</div> -->
												
												<select name="auth" id="" class="form-control">
												
													<c:forEach var="level" begin="1" end="${personalinfo.employee.level }" >
														<option value="${level }">
															<c:if test="${level eq 1 }">사원급</c:if>
															<c:if test="${level eq 2 }">대리급</c:if>
															<c:if test="${level eq 3 }">과장급</c:if>
															<c:if test="${level eq 4 }">차장급</c:if>
															<c:if test="${level eq 5 }">부장급</c:if>
														</option>
													</c:forEach>
												
												</select>
												
											</div>
												
											<label class="margin20">부터 공개합니다.</label>
									
										</div>
										
								


								<div class="form-group">
									<label for="website">비밀번호</label> 
									<input type="password"  name="password" required
										class="form-control" id="website">
								</div>
								
								<div class="form-group">
									<label for="website">파일 첨부하기</label> 
									<input type="file" name="files" multiple="multiple"
										class="form-control" id="website">
										<div class="newFilesAddDiv"></div>
									<label class="tagcloud" id="newFilesAdd"><a class="tag-cloud-link">추가 업로드</a></label>
								</div>

								<div class="form-group">
									<label for="message"></label>
									<textarea name="content" id="message" cols="100" rows="10"
										class="form-control"></textarea>
								</div>
								<div class="form-group">
									<input type="submit" value="완료"
										class="btn py-3 px-4 btn-primary">
									<input type="button" onclick="location.href='/board/boardList'" value="목록으로 돌아가기"
									class="btn py-3 px-4 btn-primary">
								</div>

							</form>
						</div>
            </div>

          </div> <!-- .col-md-8 -->


        </div>
      </div>
    </section> <!-- .section -->

        <jsp:include page="/WEB-INF/views/inc/footer.jsp" />  
    
  

  <!-- loader -->
  <div id="ftco-loader" class="fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"></circle><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"></circle></svg></div>
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	
	$('label#newFilesAdd').on('click', function(){
		$('div.newFilesAddDiv').append('<input type="file" name="files" multiple="multiple"/><br/>');

	});

	
});


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
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&amp;sensor=false"></script>
  <script src="/resources/js/google-map.js"></script>
  <script src="/resources/js/main.js"></script>
    
  
</body></html>