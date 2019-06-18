<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>FREE BOARD</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<!-- <link rel="icon" type="image/png"
	href="/resources/images/icons/favicon.ico" /> -->
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

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!--===============================================================================================-->
<!-- <link rel="stylesheet" type="text/css" href="/resources/vendor/perfect-scrollbar/perfect-scrollbar.css"> -->
<!--===============================================================================================-->
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
<link rel="stylesheet" type="text/css" href="/resources/css/util.css">
<link rel="stylesheet" type="text/css" href="/resources/css/board.css">
<!--===============================================================================================-->
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script>

/* 
function authoredDetail(auth,pageIndex,boardId){

	alert('!!!?'+auth+''+pageIndex+''+boardId);

	$.ajax({
		type : 'POST',
		url : '/board/boardDetail?pageIndex='+pageIndex+'&boardId='+boardId,
		data :  auth,	
		beforeSend : function (xhr) {
            xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
         },
		success : function(result) { //할일
			location.href=result;
		}
	}); //ajax

}

 */
</script>


<style>
p {
	margin: 20px 0px;
}
</style>


</head>

<body>


 <sec:authentication property="principal" var="personalinfo"/> 


<jsp:include page="/WEB-INF/views/inc/header2.jsp" />
<section class="ftco-section">

	<div class="row">
	<div class="limiter">
	
	<div class="col-md-8 ftco-animate fadeInUp ftco-animated boardblock">
	

	
		
	
		<div class="container-table100">
			<div class="wrap-table100">

				<div class="table100 ver4 m-b-110">
					<div class="table100-head">
						<table>
							<thead>
								<tr class="row100 head">
									<th class="cell100 column1">글번호</th>
									<th class="cell100 column2">제목</th>
									<th class="cell100 column3">내용</th>
									<th class="cell100 column4">작성자</th>
									<th class="cell100 column5">등록일</th>
									<th class="cell100 column6">조회수</th>
									<th class="cell100 column7">파일첨부</th>
								</tr>
							</thead>
						</table>
					</div>
					<!-- th와 td가 다른 테이블로 나눠져있음 -->
					<div class="table100-body">
						<!--  js-pscroll -->
						<table>
							<tbody>

				



								<c:forEach items="${boardList }" var="boardItem" >
								
									<c:choose>
									<c:when test="${personalinfo.employee.level lt boardItem.auth}">
													
											<tr class="row100 body"
												onclick="alert('일정 직급 이상 열람가능한 게시물입니다.');">
												<td class="cell100 column1">${boardItem.boardId }</td>
												<td class="cell100 column2">${boardItem.subject }</td>
												<td class="cell100 column3">${boardItem.content }</td>
												<td class="cell100 column4">${boardItem.eid }</td>
												<td class="cell100 column5">${boardItem.regDate }</td>
												<td class="cell100 column6">${boardItem.readcount }</td>
												<td class="cell100 column7">
													<c:if test="${attachItem.bid eq boardItem.boardId }">
														<c:if test="${attachItem.filetype eq 'I'}"><span class="imgSpan"><img class="imgIcon" style="width: 55px; height: 55px;" src="/resources/images/1610666.png"/></span></c:if>
														<c:if test="${attachItem.filetype eq 'D'}"><span>&nbsp;&nbsp;</span><img class="docIcon"  style="width: 35px; height: 35px;" src="/resources/images/pngtree-vector-document-icon-png-image_555429-removebg-preview.png"/></c:if>
													</c:if> 
												</td>
											</tr>
				
									</c:when>
							
									<c:otherwise>
											
											<tr class="row100 body clicktr">
												
												<td class="cell100 column1">${boardItem.boardId }</td>
												<td class="cell100 column2">${boardItem.subject }</td>
												<td class="cell100 column3">${boardItem.content }</td>
												<td class="cell100 column4">${boardItem.eid }</td>
												<td class="cell100 column5">${boardItem.regDate }</td>
												<td class="cell100 column6">${boardItem.readcount }</td>
												
												<td class="cell100 column7">
													
													<c:forEach items="${attachList }" var="attachItem">
														<c:if test="${attachItem.bid eq boardItem.boardId }">	
															<c:if test="${attachItem.filetype eq 'I'}"><span class="imgSpan"><img class="imgIcon" style="width: 55px; height: 55px;" src="/resources/images/1610666.png"/></span></c:if>
															<c:if test="${attachItem.filetype eq 'D'}"><span>&nbsp;&nbsp;</span><img class="docIcon"  style="width: 35px; height: 35px;" src="/resources/images/pngtree-vector-document-icon-png-image_555429-removebg-preview.png"/></c:if>
														</c:if> 
													</c:forEach>
												</td>
											</tr> 
											
											<form action="/board/boardDetail?pageIndex=${pageInfoMap.pageIndex}&boardId=${boardItem.boardId}" method="post">
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<%-- 												<input type="hidden" name="pageIndex" value="${pageInfoMap.pageIndex}"> --%>
<%-- 												<input type="hidden" name="boardId" value="${boardItem.boardId}"> --%>
												<input type="hidden" name="auth" value="${boardItem.auth}">
											</form>
											
									</c:otherwise>
									
									</c:choose>
									
								</c:forEach>
							


							</tbody>
						</table>
					</div>
						
					<div class="space-50"></div>
						
					<input type="button" onclick="location.href='/board/boardWrite'" 
										value="글쓰기" class="btn py-3 px-4 btn-primary">
			
	
					

			
				
				</div>

				<!-- pagination -->

				<div class="col text-center">
					<div class="block-27">
						<ul>
							<c:if test="${pageInfoMap.allRowCount gt 0 }">
							<c:if test="${pageInfoMap.startPage gt pageInfoMap.pageBlockSize}">
								<li><a href="/board/boardList?pageIndex=${pageInfoMap.startPage-pageInfoMap.pageBlockSize}">&lt;</a></li>
							</c:if>
							
								<c:forEach var="i" begin="${pageInfoMap.startPage}" end="${pageInfoMap.endPage }">
									<c:choose>
										<c:when test="${i eq pageInfoMap.pageIndex}">
											<li class="active">
												<a href="/board/boardList?pageIndex=${i}&search=${search}&searchSelect=${searchSelect}">
													<c:out value="${i}" /></a>
											</li>
										</c:when>
										
										<c:otherwise>
											<li>
												<a href="/board/boardList?pageIndex=${i}&search=${search}&searchSelect=${searchSelect}">
													<c:out value="${i}" /></a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								
								<c:if test="${pageInfoMap.endPage lt pageInfoMap.maxPage}">
									<li><a href="/board/boardList?pageIndex=${pageInfoMap.startPage+pageInfoMap.pageBlockSize }">&gt;</a></li>
								</c:if>
							</c:if>
						
						
						
	
						</ul>
					</div>
				</div>
			
			
      

        </div>
      </div><!-- table -->
      
    </div>  
      
  
      
       	<div class="col-md-3 sidebar ftco-animate fadeInUp ftco-animated boardside" >
       				
   		
       	
       				<div class="sidebar-box">
		              <form action="/board/boardList" class="search-form">
		                <div class="form-group">
		                  
		                  <input type="text" name="search" class="search-form form-control " placeholder="게시글 검색..">
		                  <div class="space-20"></div>
		                  <select id="form-control" name="searchSelect" 
		                  style="height: 60px !important;
						    border: 1px solid #e6e6e6 !important;
						    padding: 0.375rem 0.75rem;
						    padding-top: 0.375rem;
						    padding-right: 0.75rem;
						    padding-bottom: 0.375rem;
						    padding-left: 0.75rem;">
		                  	<option value="subjectSearch">제목</option>
		                  	<option value="contentSearch">내용</option>
		                  	<option value="writerSearch">작성자</option>
		                  </select>
		                  		<input type="submit"
									value="search" class="btn py-3 px-4 btn-primary"/>
		                </div>
		              </form>
         		   </div>
       	
      
       	

	
          

            
          </div>
       
			</div>
		</div>

</section>
	 <jsp:include page="/WEB-INF/views/inc/footer.jsp" />

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

	<!--===============================================================================================-->
	<script src="/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="/resources/vendor/bootstrap/js/popper.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="/resources/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<!-- <script src="/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script> -->
	<!-- 	<script>
		$('.js-pscroll').each(function(){
			var ps = new PerfectScrollbar(this);

			$(window).on('resize', function(){
				ps.update();
			})
		});
			
		
	</script> -->
	<!--===============================================================================================-->
	<script src="/resources/js/main.js"></script>

<script>


fileIconCheck();

$(document).ready(function () {
	
	
	$('.clicktr').on('click', function () {

		
		console.log($(this).next());
		
		$(this).next().submit();
	});
	
}); //doc


function fileIconCheck(){


	$('td span.imgSpan').nextAll('td span.imgSpan').remove();
	$('td img.docIcon').nextAll('td img.docIcon').remove();
	

}



</script>


</body>
</html>