<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
</head>
 <style>
	#colText{
		padding-top: 0.7rem;
	}
	/* 아코디언 메뉴 */
	*{
	  box-sizing: border-box; 
	}
	.title:first-child{
	    border-top: 1px solid black;
	  }
	  
	.title{
	  position: relative;
	  padding: 12px 0;
	  cursor: pointer;
	  font-size: 14px;
	  border-bottom: 1px solid #dddddd;
	  
	}
	  
	.title::before{
	  display: inline-block;
	  /* content: 'Title'; */
	  font-size: 14px;
	  color: #006633;
	  margin: 0 5px;
	}
	
	.title.on>span{
	  font-weight: bold;
	  color: #006633; 
	}
	  
	.contents {
	  display: none;
	    overflow: hidden;
	  font-size: 14px;
	  background-color: white;/* #f4f4f2 */
	  padding: 27px 0;
	}
	  
	.contents::before {
	  display: inline-block;
	 /*  content: 'A'; */
	  font-size: 14px;
	  font-weight: bold;
	  color: #666;
	  margin: 0 5px;
	}
	
	.arrow-wrap {
	  position: absolute;
	  top:50%; right: 10px;
	  transform: translate(0, -50%);
	}
	
	.title .arrow-top {
	  display: none;
	}
	.title .arrow-bottom {
	  display: block;
	}
	.title.on .arrow-bottom {
	  display: none;
	}
	.title.on .arrow-top {
	  display: block; 
	}
	#Accordion_wrap{
	width:70%; margin: auto;
	}
	@media(max-width:500px){
		.contents img{
		max-width : 100%;
		}
		#Accordion_wrap{
		width:100%;
		}
	}
</style> 
<body>
<!-- header start -->
<jsp:include page="../header/header.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
	<section class="container">
	<!-- 세부페이지 head 시작 -->
		<!-- 세부페이지 큰 제목 -->
		<br>
		<div class="container">
		<!-- 세부페이지 큰 제목 끝 -->
		<br>

		<!-- 세부페이지 body 시작 -->
	
	
		<!-- 리스트 출력 -->
		<div id="Accordion_wrap">
			<h1>공지사항</h1>
							<!-- 카테고리별 리스트 시작 -->
				<div style="float-right">
					<a href="/notice/noticeUserList.do" style="color: black"> 전체 | </a>
					<a href="/notice/noticeUserList.do?noticeCategory=notice" style="color: black"> 공지 |</a>
					<a href="/notice/noticeUserList.do?noticeCategory=update" style="color: black"> 업데이트 |</a>
					<a href="/notice/noticeUserList.do?noticeCategory=event" style="color: black"> 이벤트 |</a>
					<a href="/notice/noticeUserList.do?noticeCategory=info" style="color: black"> 안내 </a>
				</div>
				<hr>
				<c:if test="${!empty nList }">
					<c:forEach items="${nList }" var="notice" varStatus="i">
						<div class="title">
							<span>
								<c:if test="${notice.noticeCategory == 'notice'}"><b>[공지]</b></c:if>
								<c:if test="${notice.noticeCategory == 'update'}"><b>[업데이트]</b></c:if>
								<c:if test="${notice.noticeCategory == 'event'}"><b>[이벤트]</b></c:if>
								<c:if test="${notice.noticeCategory == 'info'}"><b>[안내]</b></c:if>
								${notice.noticeTitle } 
								<div style="font-size:0.8em;">${notice.nUpdateDate }</div>
							</span>
							<div class="arrow-wrap">
								<span class="arrow-top">↑</span>
								<span class="arrow-bottom">↓</span>
							</div>
						</div>
						<div class="contents">
							<span>${notice.noticeContents }</span><br>
							<div style="margin: auto;">
								<c:if test="${!empty notice.noticeFileRename }">
									<img alt="본문이미지" style="width:100%; height:auto;" src="/resources/nuploadFiles/${notice.noticeFileRename }">
								</c:if>
							</div>
						</div>
					</c:forEach>
				</c:if>
		</div>
				<c:if test="${empty nList }">
						<b>데이터가 존재하지 않습니다.</b>
				</c:if>
	</div>
	<br>
		<hr>
	</section>
<br>
</main>
<!-- main contents End -->
<script>
$(".title").click(function() {
	   $(this).next(".contents").stop().slideToggle(300);
	  $(this).toggleClass('on').siblings().removeClass('on');
	  $(this).next(".contents").siblings(".contents").slideUp(300); // 1개씩 펼치기
	});
 
</script>

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>