<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세페이지</title>
<!-- jQuery, bootstrap  -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- summernote css/js -->
  <script src="/resources/js/summernote-lite.js"></script>
  <script src="/resources/js/summernote/lang/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="/resources/css/summernote-lite.css">
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
</head>
 <style>
	.container {
		width: 70%;
		max-width: 1000px;
		margin: 0 auto;
	}
</style> 
<body>
<!-- header start -->
<jsp:include page="../header/header.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
<br><br>	
<div class="container">

	<h3 align="center">${notice.noticeNo }번 공지사항 상세 페이지</h3>
	<br><br>
		
		<table align="center" class="table col-10">
			<tr>
				<td  class="col-2" scope="col" align="center">종류</td>
				<td>
					<c:if test="${notice.noticeCategory == '0' }">
						공지
					</c:if>
					<c:if test="${notice.noticeCategory == '1' }">
						업데이트
					</c:if>
					<c:if test="${notice.noticeCategory == '2' }">
						이벤트
					</c:if>
					<c:if test="${notice.noticeCategory == '3' }">
						안내
					</c:if>
				</td>
			<tr>
				<td  class="col-2" scope="col" align="center">작성자</td>
				<td><input type="text" name="noticeWriter"  class="form-control" value="${notice.noticeWriter }" readonly></td>
			</tr>
			<tr>
				<td  class="col-2" scope="col" align="center">제목</td>
				<td><input type="text" name="noticeTitle" onkeyup="titleLengthCk(this);" class="form-control" value="${notice.noticeTitle }"></td>
			</tr>
			<tr>
				<td  class="col-2" scope="col" align="center">내용</td>
				<td> <textarea class="form-control" id="exampleFormControlTextarea1" cols="50" rows="20" name="noticeContents">${notice.noticeContents }</textarea>  </td>
			</tr>
			<tr>
				<td  class="col-2" scope="col" align="center">첨부파일</td>
				<td><img alt="본문이미지" src="/resources/nUploadFiles/${notice.noticeFileRename }"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input onclick="noticeModify(${notice.noticeNo }, ${page});" type="button" value="수정" class='btn btn-dark'>
					<input onclick="noticeRemove(${notice.noticeNo }, ${page});" type="button" value="삭제" class='btn btn-dark'>
					<button type="button" onclick="location.href='/notice/list.kh'" class='btn btn-dark'>목록</button> 
					 
				</td>
			</tr>
		</table>
</div>
<br><br>
</main>
<!-- main contents End -->


<script>
	function noticeModify(noticeNo, page) {
		location.href="/notice/modifyView.kh?noticeNo="+noticeNo+"&page="+page;
	}
	function noticeRemove(noticeNo, page) {
		event.preventDefault(); // 하이퍼링크 이동 방지
		if(confirm("게시물을 삭제하시겠습니까?")) {
			location.href="/notice/remove.kh?noticeNo="+noticeNo+"&page="+page;
		}
	}

</script>

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>