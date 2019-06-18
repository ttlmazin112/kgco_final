<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>MemberHome</title>
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
<link rel="icon" type="image/png" href="/resources/images/icons/favicon.png" />

<!-- =============================================================================== -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
   rel="stylesheet">
<link
   href="https://fonts.googleapis.com/css?family=Quicksand:300,400,500,700,900"
   rel="stylesheet">
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
   src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="/resources/js/page.js"></script>

<!-- =============================================================================== -->
<style type="text/css">
.wrapper {
   display: grid;
   grid-template-columns: 500px 500px;
   grid-template-rows: 600px 600px;
   
}
/* .items3{
    grid-columns-start:1;
    grid-columns-end: 2;
} */
h3{
text-align: center;
}
.mt{
width: 50px;
} 
.btnWork{
width:100px;

    background-color: #38d39f;

    border: none;

    color:#fff;

    padding: 15px 0;

    text-align: center;

    text-decoration: none;

    display: inline-block;

    font-size: 15px;

    margin: 4px;

    cursor: pointer;
}
#btnChat{
width:100px;

    background-color: #38d39f;

    border: none;

    color:#fff;

    padding: 15px 0;

    text-align: center;

    text-decoration: none;

    display: inline-block;

    font-size: 15px;

    margin: 4px;

    cursor: pointer;
}

</style>

</head>
<body>
   <!-- nav -->
   <jsp:include page="/WEB-INF/views/inc/header2.jsp" />
   <!-- nav -->

   <div class="hero-wrap hero-wrap-2"
      style="background-image: url('images/bg_1.jpg');"
      data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
      <div class="container" style="text-align: center;" >
                  
                  
               </div>
         <div
            class="row no-gutters slider-text align-items-end justify-content-start">
            <div class="col-md-8 ftco-animate text-center text-md-left mb-5">
            </div>
         </div>
      </div>
   </div>

   <!--  -->
<section class="ftco-section ftco-no-pt" style="margin-left: 400px">
		<div class="wrapper">
			<div>
				<h3>${emplyeeVo.ename }(${emplyeeVo.eid }) ${emplyeeVo.eclass }님 정보</h3><input type="button" id="btnChat" value="메신저"  onclick="openChat();">
				<form id="userInfoDeteil" name="userInfoDeteil" method="post">
					<table class="table" id="tableList">
						<thead class="thead-primary">
							<tr>
								<th class="mt">직원 아이디</th>
								<td class="mt"><a href="/selfSchedule" id="selfEid"
									onclick="selfEid(); return false;">${emplyeeVo.eid }</a></td>
							<tr>
								<th class="mt">이름</th>
								<td>${emplyeeVo.ename }</td>
							</tr>
							<tr>
								<th class="mt">직급</th>
								<td>${emplyeeVo.eclass }</td>
							</tr>
							<tr>
								<th class="mt">전화번호</th>
								<td>${emplyeeVo.ephone }</td>
							</tr>
						</thead>
					</table>

					<div class="nav ftco-animate nav-pills" id="v-pills-tab"
						role="tablist" aria-orientation="vertical">

						<input class="nav-link" id="v-pills-goal-tab" data-toggle="pill"
							role="tab" aria-controls="v-pills-goal" aria-selected="false"
							aria-controls="v-pills-whatwedo" aria-selected="true"
							type="button" value="수정" name="v-pills-goal-tab"
							onclick="windowOpenPwCheck()">
					</div>

				</form>
		
			</div>
			<div style="padding-left: 200px;">
				<div>
					<jsp:include page="/WEB-INF/views/member/calender.jsp" />
				</div>	
			</div>
			<div>
            <div style="width: 500px;">

               <h2 style="text-align: center;">결재창</h2>




               <div
                  class="col-lg-7 p-5 ftco-wrap ftco-animate fadeInUp ftco-animated"
                  style="margin: auto; max-width: 100%">
                  
                  <input type="hidden" name="confirmId" value="${emplyeeVo.eid}">
                  <form class="domain-form d-flex mb-3">
                     <div class="form-group domain-name">
                        <input type="text" class="form-control name px-4"
                           placeholder="Search :" readonly="readonly"
                           style="background-color: blue;">
                     </div>
                     <div class="form-group domain-select d-flex">
                        <div class="select-wrap">

                           <select name="confirmSelect" style="font-size: xx-large;">
                              <option value="vacation">출타</option>
                              <option value="work">업무</option>
                              <c:if
                                 test="${emplyeeVo.eclass eq '과장' || emplyeeVo.eclass eq '차장' || emplyeeVo.eclass eq '부장' }">
                                 <option value="hr">인사</option>
                              </c:if>
                              <option value="flight">운항</option>
                           </select>
                        </div>
                        <input type="submit"
                           class="search-domain btn btn-primary text-center"
                           value="Search" onclick="showDocument()">
                     </div>
                  </form>
               </div>

               <button style="width: 90%; border-radius: 10px 0 10px 0;"
                  onclick="location.href='/confirm/documentBox?eid=${emplyeeVo.eid}'"
                  class="btnWork">서류함</button>

            </div>
         </div>
         <div>
         <div id="pie_chart_div"
               style="width: 500px; height: 400px; padding: 0px; margin: 0px; margin-left: 140px;"></div>
         
         </div>
      </div>


         </section>
          <jsp:include page="/WEB-INF/views/inc/footer.jsp" />  

<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script>
function showDocument(){
	var select = $('select[name=confirmSelect]').val();
	var id = $('input[name=confirmId]').val();
 	window.open('/confirm/document?select=' + select
			+ '&id=' + id, '', 'width=500,height=500,location=no, resizable=no'); 
}
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
   <script
      src="/resources/https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
   <script src="/resources/js/google-map.js"></script>
   <script src="/resources/js/main.js"></script>

</body>

</html>