<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
 <style>
	.container {
		width: 70%;
		max-width: 1000px;
		margin: 0 auto;
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
</style> 


<body>
<!-- header start -->
<jsp:include page="../header/header.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
<br><br>	
<div class="container">

	<h3 align="center">공지사항 작성</h3>
	<br><br>
	
	<form action="/notice/register.kh" method="post" enctype="multipart/form-data" name="noticeForm">
		<table align="center" class="table col-10">
			<tr>
				<td  class="col-2" scope="col" align="center">선택</td>
				<td>
					<select name="noticeCategory" id="noticeCategory" class="form-select" aria-label="Default select example" required>
						<option value="0" label="공지" selected></option>
						<option value="1" label="업데이트"></option>
						<option value="2" label="이벤트"></option>
						<option value="3" label="안내"></option>
					</select>	
				</td>
			<tr>
				<td  class="col-2" scope="col" align="center">작성자</td>
				<td><input type="text" name="noticeWriter"  class="form-control" value="${sessionScope.loginMember.mNickname}" readonly></td>
			</tr>
			<tr>
				<td  class="col-2" scope="col" align="center">제목</td>
				<td><input type="text" name="noticeTitle" onkeyup="titleLengthCk(this);" class="form-control"></td>
			</tr>
			<tr>
				<td  class="col-2" scope="col" align="center">내용</td>
				<td> <textarea class="form-control" id="exampleFormControlTextarea1" cols="50" rows="20" name="noticeContents"></textarea>  </td>
			</tr>
			<tr>
				<td  class="col-2" scope="col" align="center">첨부파일</td>
				<td><input type="file" name="uploadFile"></td>
			</tr>
			<tr>
				<td colspan="2" align="center" style="border:none;">
					<input onclick="noticeCheck();" type="button" value="등록" class="btn btn-warning btn-sm">
					<input type="reset" value="취소" class="btn btn-warning btn-sm">
					<button type="button" onclick="location.href='/notice/list.kh'" class="btn btn-warning btn-sm">목록</button> 
					 
				</td>
			</tr>
		</table>
	</form>
</div>
<br><br>
</main>
<!-- main contents End -->


<script>
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