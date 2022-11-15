<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항 상세페이지</title>
<!-- jQuery, bootstrap  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel="stylesheet" href="/resources/css/admin/notice/css.css" ></link>
</head>
 <style>

	main{
		width: 100%;
		max-width: 100%;
	}

	section{
		width: 1024px !important;
	}
	
	[name="noticeTitle"]{
		width: 80% !important;
	}

	#exampleFormControlTextarea1 img{
		max-width: 100%;
	}

	.table tr{
		max-width: 100%;
	}
	.table img{
		max-width: 100%;
	}

	.btn {
	  appearance: none;
	    -webkit-appearance: none;
	  font-family: sans-serif;
	  cursor: pointer;
	  padding: 12px;
	  min-width: 150px;
	  border: none;
	    -webkit-transition: background-color 100ms linear;
	    -ms-transition: background-color 100ms linear;
	     transition: background-color 100ms linear;
	}

	.main_title{
		justify-content: center;
}
	
	

	@keyframes blink-effect {
		50% {
		    opacity: 0;
		}
	}
	.blink {
	 	animation: blink-effect 1s step-end infinite;
	 }
	@media (max-width: 400px) {
		.container{
			max-width: 100%;
		}
		.btn {
			min-width: 80px;
		}

		main{
		width: 100%;
		max-width: 100%;
	}
	table {
		width: 100%;
		max-width: 100%;
	}
	

	}
	@media (max-width: 768px) {
		.container{
			max-width: 100%;
		}

		.btn {
			min-width: 80px;
		}

		section{
			width: 100%;
		}
	}
</style> 
<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
	<section class="container">
	<!-- 세부페이지 head 시작 -->
		<!-- 세부페이지 큰 제목 -->
		<div class="main_title">
			<div class="main_t1">
				<a href="/notice/list.kh"><p style="color:white";>공지사항 리스트</p></a>
			</div>
			<div class="main_t2">
				<a href="/notice/writeView.kh">공지사항 작성</a>
			</div>        
		</div>
		<!-- 세부페이지 큰 제목 끝 -->
	
		<!-- 세부 메뉴 시작 -->
		<div class="sub_menu">
		     <ul class="amount">
		        <li>
		          <div>
		            <div class="contents1">
		            	<a href="/notice/list.kh">전체</a>
		            </div>
		            <div class="result">
		            	<a href="/notice/list.kh">${totalBoard }</a>
		            </div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1">
		            	<a href="/notice/list.kh?nStatus=Y">게시</a>
			    	</div>
		            <div class="result">
		            	<a href="/notice/list.kh?nStatus=Y">${showBoard }</a>
		            </div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1">
		            	<a href="/notice/list.kh?nStatus=N">보류</a>
		            </div>
		            <div class="result">
		            	<a href="/notice/list.kh?nStatus=N">${hideBoard }</a>
		            </div>
		          </div>
		        </li>
		     </ul>
	     </div>
		<hr>
	<!-- amount end -->
		<!-- 상세페이지 시작 -->
 		<div class="board_title" style="font-size: 16px; text-align: center";>
			<h4 align="center">[ 공지사항 상세 페이지 ]</h4>
				<c:if test="${loginMember.adminYN=='Y' }">
					<table align="center" class="table col-10">
						<tr>
							<td class="col-md-3 col-2" scope="col" align="center">종류</td>
							<td class="col-md-7 col-8" align="left">
								<c:if test="${notice.noticeCategory == 'notice'}">공지</c:if>
								<c:if test="${notice.noticeCategory == 'update'}">업데이트</c:if>
								<c:if test="${notice.noticeCategory == 'event'}">이벤트</c:if>
								<c:if test="${notice.noticeCategory == 'info'}">안내</c:if>
							</td>
						</tr>
						<tr>
							<td  class="col-md-2 scope="col" align="center">작성자</td>
							<td><input type="text" name="noticeWriter"  class="form-control" value="${notice.noticeWriter }" readonly></td>
						</tr>
						<tr>
							<td  class="col-md-2" scope="col" align="center">제목</td>
							<td><input type="text" name="noticeTitle" onkeyup="titleLengthCk(this);" class="form-control" value="${notice.noticeTitle }"></td>
						</tr>
						<tr>
							<td  class="col-md-2" scope="col" align="center">내용</td>
							<td><textarea class="form-control" id="exampleFormControlTextarea1" cols="5" rows="5" name="noticeContents">${notice.noticeContents }</textarea>
								<br>
								<c:if test="${!empty notice.noticeFileRename }">
									<a href=""  onclick="fnImgPop(this.src)"><img alt="본문이미지" 
									src="/resources/nuploadFiles/${notice.noticeFileRename }" 
									onclick="fnImgPop(this.src)"></a>
								</c:if>
								<c:if test="${empty notice.noticeFileRename }">
									<input type="file" name="uploadFile">
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center" style="border:none;">
								<input onclick="noticeModify(${notice.noticeNo }, ${page});" type="button" value="수정" class="btn btn-warning btn-sm">
								<input onclick="noticeRemove(${notice.noticeNo }, ${page});" type="button" value="삭제" class="btn btn-warning btn-sm">
								<button type="button" onclick="backBtn()" class="btn btn-warning btn-sm">목록</button> 
								 
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${loginMember.adminYN=='N' }">
					<table align="center" class="table col-10">
						<tr>
							<td>
								<c:if test="${notice.noticeCategory == 'notice'}">[공지]</c:if>
								<c:if test="${notice.noticeCategory == 'update'}">[업데이트]</c:if>
								<c:if test="${notice.noticeCategory == 'event'}">[이벤트]</c:if>
								<c:if test="${notice.noticeCategory == 'info'}">[안내]</c:if>
								${notice.noticeTitle }
							</td>
						</tr>
						<tr>
							<td>${notice.noticeContents }
							<br>
							<c:if test="${!empty notice.noticeFileRename }">
								<img alt="본문이미지" src="/resources/nuploadFiles/${notice.noticeFileRename }">
							</c:if>
							<c:if test="${empty notice.noticeFileRename }">
								&nbsp;
							</c:if>
							<br><br>
							</td>
						</tr>
						<tr>
							<td align="center" style="border:none;">
								<button type="button" onclick="backBtn()" class="btn btn-warning btn-sm">목록</button> 
							</td>
						</tr>
					</table>
				</c:if>
			</div>
			<br>
		<hr>
	</section>
<br>
</main>
<!-- main contents End -->


<script>
	function backBtn() {
	    history.back();
	}
	
	function noticeModify(noticeNo, page) {
		location.href="/notice/modifyView.kh?noticeNo="+noticeNo+"&page="+page;
	}
	function noticeRemove(noticeNo, page) {
		event.preventDefault(); // 하이퍼링크 이동 방지
		if(confirm("게시물을 삭제하시겠습니까?")) {
			location.href="/notice/remove.kh?noticeNo="+noticeNo+"&page="+page;
		}
	}

	function fnImgPop(url){
		var img = new Image();
		img.src = url;
		var img_width = img.width;
		var win_width = img.width+25;
		var img_height = img.height;
		var win = img.height+30;
		var OpenWindow = window.open('','_blank', 'width = '+img_width+', height = '+img_height+', menubars = no, scrollbars = auto');
		OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
	 }
</script>
<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>