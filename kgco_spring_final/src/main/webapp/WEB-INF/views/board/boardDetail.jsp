<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Detail View</title>
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
    
   <!-- nav -->
   <jsp:include page="/WEB-INF/views/inc/header2.jsp" />
   <!-- nav -->

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
            <h2 class="mb-3">자유게시판</h2>
   
            <div class="pt-5 mt-5">

              
              <div class="comment-form-wrap pt-5">
                
							<form action="/board/boardModify" method="post"  name="frm"  enctype="multipart/form-data" class="p-5 bg-light">
							 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><!-- post로 쓰는곳마다 집어넣을것 -->
							<input type="hidden" class="paramboardId" value="${boardDetail.boardId}">
							<input type="hidden" name="boardId" value=${param.boardId }>
							<input type="hidden" name="pageIndex" value=${param.pageIndex }>
							<sec:authentication property="principal" var="personalinfo"/>	
							
								<div class="form-group">
									<label class="labelBold"><b>글번호</b></label> 
									<label class="labelDetail">${boardDetail.boardId}</label>
								</div>
							
								<div class="form-group">
									<label class="labelBold"><b>제목</b></label>  
									<label class="labelDetail">${boardDetail.subject}</label>
								</div>

							
									<div class="form-group d-flex align-items-center pt-5">

											<div class="form-group domain-name">
												<label class="labelBold"><b>작성자</b></label> 
												<label class="labelDetail">${boardDetail.eid }</label>
											</div>
				
									</div>
						
								
								<div class="form-group">
									<label class="labelBold"><b>파일</b></label> <br>
										<c:if test="${not empty attachList }">
											<c:forEach items="${attachList}" var="attach">
											<span id="filespan">	${attach.filename} 
											
						&nbsp;<button type="button" onclick="location.href='/board/download?fileName=${attach.uploadpath}/${attach.uuid}_${attach.filename}'"><b>다운로드</b></button></span><br/>
											</c:forEach>
										</c:if>		
									
								</div>

								
								<div class="space-70"></div>



								<div class="form-group">
									<label class="labelBold"><b>내용</b></label> <br/><br/>
									<label class="labelDetail">${boardDetail.content}</label>

								</div>
								
								
								
								<div class="space-50"></div>
								
								<div class="form-group">
								
									<c:if test="${personalinfo.employee.eid eq boardDetail.eid  }">
										<input type="button"  value="수정" onclick="location.href='/board/boardModify?pageIndex=${param.pageIndex }&boardId=${boardDetail.boardId}'" class="btn py-3 px-4 btn-primary"/>
										<input type="button" value="삭제" onclick="location.href='/board/boardDelete?boardId=${boardDetail.boardId}'" class="btn py-3 px-4 btn-primary"/>
									</c:if>
										<input type="button" onclick="location.href='/board/boardList'" value="목록으로 돌아가기"
													class="btn py-3 px-4 btn-primary">
								</div>

							</form>
						</div>
						
		<div class="pt-5 mt-5">
              <h3 id="replyCountbox" class="mb-5 font-weight-bold"></h3>
              
              
              
							<ul class="comment-list">


								


							</ul>
							<!-- END comment-list -->





							<div class="comment-form-wrap pt-5">
								<input type="hidden" class="presentname"
									value="${personalinfo.employee.ename}">
								<input type="hidden" class="presentId"
									value="${personalinfo.employee.eid}">
								<input type="hidden" class="presentLevel"
									value="${personalinfo.employee.level}">
								<div class="form-group">
									<label for="message">Message</label>
									<textarea name="replyContent" id="replyContent" cols="30"
										rows="5" class="form-control"></textarea>
								</div>
								<div class="form-group">
									<input type="button" onclick="insertReply();"
										value="Post Comment" class="btn py-3 px-4 btn-primary">
								</div>
							</div>




						</div>
						
						
            </div>

          </div> <!-- .col-md-8 -->
          <div class="col-md-4 sidebar ftco-animate fadeInUp ftco-animated">
 
          
       		

            <div class="sidebar-box ftco-animate fadeInUp ftco-animated">
              <h3>현재 조회수가 가장 많은 글</h3>
              
              <div class="space-50"></div>
               <c:forEach var="mostReaditem" items="${mostReadList }" >
       				
       			
              
		              <div class="block-21 mb-4 d-flex">
		                
		                <div class="text">        
		                  <h3 class="heading"><a href="/board/boardDetail?pageIndex=${pageIndex}&boardId=${mostReaditem.boardId}&auth=${auth}">${mostReaditem.subject }</a></h3>
		                  <div class="meta">
		                    <div><a href="#"><span class="icon-calendar"></span> ${mostReaditem.regDate }</a></div>
		                    <div><a href="#"><span class="icon-person"></span>Written by ${mostReaditem.eid }</a></div>
		                    <div><a href="#"><span class="icon-chat"></span> 조회수 ${mostReaditem.readcount }</a></div>
		                  </div>
		                </div>
		              </div>
              </c:forEach>
              

              
              
              
              
            </div>

          

          </div>

        </div>
      </div>
    </section> <!-- .section -->

    
    
  

  <!-- loader -->
  <jsp:include page="/WEB-INF/views/inc/footer.jsp" /> 
<script src="/resources/js/jquery-3.3.1.min.js"></script>


<script>

function insertReply(){
	
	var bID=${param.boardId};
	var pIndex=${param.pageIndex};
	var content=$('textarea#replyContent').val();
	var ename=$('.presentname').val();
	var eId=$('.presentId').val();
	var level= $('.presentLevel').val();
	
	alert('댓글을 등록하시겠습니까?');
	
	$.ajax({
		
		type:'POST',
		data: {
			replyContent : content,
			ename : ename,
			eId : eId,
			level : level
		},
		url:'/board/boardDetailReply?pageIndex='+pIndex+'&boardId='+bID,
		beforeSend : function (xhr) {
            xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
         },
		success : function(result){
			selectReplyList();
			replyCount();
			$('textarea#replyContent').val('');
		}	
		
	}); 

};	 //insert 

 $(document).ready(function() {
	selectReplyList();
	replyCount();
});


function selectReplyList(){

	var boardId=$('.paramboardId').val();
	var str = '';
	
		$.ajax({
		
		type:'POST',

		url:'/board/boardReplySelect?boardId='+boardId,
		beforeSend : function (xhr) {
            xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
         },
		success : function(result){
 				$.each(result, function(index, reply){
 					str+='<li class="comment">';
 					str+='<div class="vcard bio">';
 					if(reply.level==5){
 						str+='<img src="/resources/images/kingcrop.png" alt="Image placeholder">';
 						
 					}else if(reply.level==4){
 						str+='<img src="/resources/images/eonicrop.png" alt="Image placeholder">';
 						
 					}else if(reply.level==3){
 						str+='<img src="/resources/images/sunsquir.png" alt="Image placeholder">';
 						
 					}
 					else if(reply.level==1 || reply.level==2){
 						str+='<img src="/resources/images/ggobucrop.png" alt="Image placeholder">';
 						
 					}
 					str+='</div>';
 					str+='<div class="comment-body">';
 					str+='<h3>'+reply.ename+'</h3>';
 					str+='<div class="meta">'+reply.replyregDate+'</div>';
 					str+='<p class="replyListDiv">'+reply.replyContent+'</p>';
 					str+='</div>';
 					str+='</li>';
 					str+='';
 					str+='';
 					
					
					}); 
 				$('ul.comment-list').html(str);
		}
			
	}); 
	
	
	

}

function replyCount(){

	var boardId=$('.paramboardId').val();
	
		$.ajax({
		type:'POST',
		url:'/board/boardReplyCount?boardId='+boardId,
		beforeSend : function (xhr) {
            xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
         },
		success : function(result){
			$('h3#replyCountbox').html(result+'   Comments');
		}
			
	}); 
	
	
	

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
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&amp;sensor=false"></script>
  <script src="/resources/js/google-map.js"></script>
  <script src="/resources/js/main.js"></script>
    
  
</body></html>