<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
 
  
</head>
<style>
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
	.mainUl {
		list-style-type: none;
		padding: 0px;
	}
	
	.mainLi {
		display: inline-block;
		margin-left: 18px;
		margin-right: 18px;
		font-size: 18px;
		/* font-weight: bold; */
	}
	#colText{
		padding-top: 0.7rem;
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
	<br><br>
		<!-- 세부페이지 큰 제목 -->
		<div class="container text-center">
			<div class="row row-cols-2">
				<div class="col" id="colText" style="background-color: #5e5e5e; color: white; height:45px; vertical-align: middle;"><a href="/qna/list.kh">문의내역</a></div>
				<div class="col" id="colText" style="background-color: #c9c9c9; color: white; height:45px; vertical-align: middle;"><a href="/qna/writeView">문의작성</a></div>
			</div>
		<br>

			<!-- 세부페이지 큰 제목 끝 -->
	
		<h3>1:1 문의사항 작성</h1>
		<hr>
		<br>
		<!-- 세부페이지 body 시작 -->
	
		<!-- 1:1 문의 입력 폼 Start -->
		<div class="row row-cols-1" style="width:80%; margin: 0 auto";>
			<form action="/qna/register.kh" method="post" enctype="multipart/form-data" name="qnaForm" id="qnaForm">
			<%-- <input type="hidden" name="memberId" value="${loginMember.memberId }"> --%>
				<table align="center" class="table col-6" style="border-color:skyblue;">
					<tr>
						<td class="col-2" align="center">문의유형</td>
						<td class="col-4">
							<select name="qnaCategory"  class="form-select" aria-label="Default select example" >
								<option value="member" label="회원관련" selected></option>
								<option value="point" label="포인트관련"></option>
								<option value="books" label="도서관련"></option>
								<option value="others" label="기타"></option>
							</select>	
						</td>
					<tr>
						<td class="col-2" align="center">작성자</td>
						<td class="col-4"><input type="text" name="memberId" class="form-control" value="${loginMember.memberId}" readonly></td>
					</tr>
					<tr>
						<td width="20%" align="center">제목</td>
						<td width="80%"><input type="text" name="qnaTitle" id="inputTitle" onkeyup="titleLengthCheck(this);" class="form-control" placeholder="제목은 최대 30자까지만 쓸 수 있습니다."></td>
					</tr>
					<tr>
						<td class="col-2" align="center">내용</td>
						<td class="col-4"><textarea class="form-control" id="exampleFormControlTextarea1" cols="20" rows="20" name="qnaContents"></textarea>  </td>
					</tr>
					<tr>
						<!--첨부파일(선택)-->
						<td class="col-6" colspan="2">
							<div class="mb-3">
							  <input class="form-control" type="file" id="formFile1" accept="image/jpeg, image/png, image/jpg, image/gif" name="uploadFile" onchange="filename(this)">
							</div>
							<div class="mb-3">
							  <input class="form-control" type="file" id="formFile2" accept="image/jpeg, image/png, image/jpg, image/gif" name="uploadFile" onchange="filename(this)">
							</div>
							<div class="mb-3">
							  <input class="form-control" type="file" id="formFile3" accept="image/jpeg, image/png, image/jpg, image/gif" name="uploadFile" onchange="filename(this)">
							</div>
							<!-- 첨부파일 -->
					    </td>
					</tr>
					
					<tr>
						<td class="col-6" colspan="2" align="center" style="border:none;">
							<input onclick="qnaCheck();" type="button" value="등록" class="btn btn-warning btn-sm">
							<input type="reset" value="취소" class="btn btn-warning btn-sm">
							<button type="button" onclick="location.href='/qna/list.kh'" class="btn btn-warning btn-sm">목록</button> 
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 1:1 문의 입력 폼 End -->
		<hr>
	</section>
<br>
</main>
<!-- main contents End -->


<!-- 파일 업로드 스크립트 -->
<script>
//파일이름 출력용
function filename(obj) {
	var name = obj.files[0].name;
	var text = obj.previousElementSibling.previousElementSibling;
	text.value=name;
	
	imgCheck(obj);
	
	
}


///// 이미지파일 체크 시작 ////
var imgFile = document.querySelectorAll('.form-control');
var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/i;

function imgCheck(obj) {
for (var i = 0; i < imgFile.length; i++) {
	if (imgFile[i].value != "") {

		if (!imgFile[i].value.match(fileForm)) {
			alert("이미지 파일만 업로드 가능");
			imgFile[i].value = "";
			var text = obj.previousElementSibling.previousElementSibling
			text.value="";
			

		}
	}
}

};

//제목 길이 유효성 검사 30자까지
function titleLengthCheck(thisInput){
 	console.log(thisInput.value.length);
 	if(thisInput.value.length>20){
 		thisInput.value = thisInput.value.substr(0,20);
 	}	
}
function qnaCheck() {
	if(qnaForm.qnaTitle.value=="") { // document 를 생략해도 됨
        alert("제목을 입력하세요!");
        qnaForm.qnaTitle.focus();
    	return false;
    }else if(qnaForm.qnaContents.value==""){
        alert("내용을 입력하세요");
        qnaForm.qnaContents.focus();
        return false;
    }
	
	return qnaForm.submit();
 }


</script>

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>