<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정페이지</title>
<!-- jQuery, bootstrap  -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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

	<h3 align="center">${notice.noticeNo }번 공지사항 수정하기</h3>
	<br><br>
		
	<form action="/notice/modify.kh" method="post" enctype="multipart/form-data" name="noticeForm">
		<input type="hidden" name="page" value="${page }">
		<input type="hidden" name="noticeNo" value="${notice.noticeNo }">
		<input type="hidden" name="noticeFilename" value="${notice.noticeFilename }">
		<input type="hidden" name="noticeFileRename" value="${notice.noticeFileRename }">
		<table align="center" class="table col-10">
			<tr>
				<td  class="col-2" scope="col" align="center">종류</td>
				<td>
					<select name="noticeCategory"  class="form-select" aria-label="Default select example" >
						<option style="color:FFF9B0" selected >
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
						</option>
						<option value="0" label="공지"></option>
						<option value="1" label="업데이트"></option>
						<option value="2" label="이벤트"></option>
						<option value="3" label="안내"></option>
					</select>
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
				<td>
					<input type="file" name="reloadFile">
					<a href="#">${notice.noticeFilename }</a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input onclick="noticeCheck();" type="button" value="수정" class='btn btn-dark'>
					<button type="button" onclick="location.href='/notice/list.kh'" class='btn btn-dark'>목록</button> 
					 
				</td>
			</tr>
		</table>
	</form>
</div>
<br><br>
</main>
<!-- main contents End -->


<script>
	$('.summernote').summernote({
		height : 300,
		lang : "ko-KR",
	});
	function titleLengthCk(thisInput){
	 	console.log(thisInput.value.length);
	 	if(thisInput.value.length>30){
	 		thisInput.value = thisInput.value.substr(0,30);
	 	}	
	}
	function noticeCheck() {
		if(noticeForm.noticeTitle.value=="") { // document 를 생략해도 됨
	        alert("제목을 입력하세요!");
	        noticeForm.noticeTitle.focus();
	    	return false;
	    }else if(noticeForm.noticeContents.value==""){
	        alert("내용을 입력하세요");
	        noticeForm.noticeContents.focus();
	        return false;
	    }
		return noticeForm.submit();
	 }
	
	
</script>

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>