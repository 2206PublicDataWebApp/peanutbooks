<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<br>
		<!-- 세부페이지 head 시작 -->
		<!-- 세부페이지 큰 제목 -->
		<div class="container text-center">
			<div class="row row-cols-2">
				<div class="col" id="colText" style="background-color: #5e5e5e; color: white; height:45px; vertical-align: middle;"><a href="/qna/list.kh">문의내역</a></div>
				<div class="col" id="colText" style="background-color: #c9c9c9; color: white; height:45px; vertical-align: middle;"><a href="/qna/writeView">문의작성</a></div>
			</div>
		<!-- 세부페이지 큰 제목 끝 -->
		<br>
		<hr>
			<!-- 세부 메뉴 시작 -->
			<ul class="mainUl">
	    		<li class="mainLi"><div style="text-align:center">
			    		<a href="/qna/list.kh">총문의<br>
			    		${totalQna }</a>
			    	</div>
			    </li>
			    <li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/qna/list.kh?qnaStatus=Y">답변완료<br>
						${totalAnswer }</a>
			    	</div>
				</li>
				<li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/qna/list.kh?qnaStatus=N">처리중<br>
						${totalNoAnswer }</a>
			    	</div>
				</li>
	 		</ul>
	 		<!-- 세부 메뉴 끝 -->
		<hr>
		<br>
		
		<!-- 세부페이지 body 시작 -->
		
		<!-- 1:1 문의 입력 폼 Start -->
			<div class="row row-cols-1" style="width:90% ; margin: 0 auto;">
				<form action="/qna/modify.kh" method="post" enctype="multipart/form-data" name="qnaForm" id="qnaForm">
				<input type="hidden" name="memberId" value="${loginMember.memberId }">
				<input type="hidden" name="page" value="${page }">
				<input type="hidden" name="qnaNo" value="${qna.qnaNo }">
				<table align="center" class="table col-6">
					<tr>
						<td colspan="2"><h3>문의 내용 수정</h3></td>
					<tr>
					<tr>
						<td class="col-2" align="center">문의유형</td>
						<td class="col-4">
							<select name="qnaCategory" class="form-select" aria-label="Default select example" >
								<option value="member" <c:if test="${qna.qnaCategory == 'member'}">selected</c:if>>회원 관련</option>
								<option value="point" <c:if test="${qna.qnaCategory == 'point'}">selected</c:if>>포인트관련</option>
								<option value="books" <c:if test="${qna.qnaCategory == 'books'}">selected</c:if>>도서관련</option>
								<option value="others" <c:if test="${qna.qnaCategory == 'others'}">selected</c:if>>기타</option>
							</select>	
						</td>
					<tr>
						<td class="col-2" align="center">작성자</td>
						<td class="col-4"><input type="text" name="memberId" class="form-control" value="${loginMember.memberId}" readonly></td>
					</tr>
					<tr>
						<td class="col-2" align="center">제목</td>
						<td class="col-4"><input type="text" name="qnaTitle" class="form-control" value="${qna.qnaTitle }"></td>
					</tr>
					<tr>
						<td class="col-2" align="center">내용</td>
						<td class="col-4"><textarea class="form-control" id="exampleFormControlTextarea1" cols="5" rows="5" name="qnaContents">${qna.qnaContents }</textarea>  </td>
					</tr>
					<tr>
						<td class="col-2" align="center" rowspan="3">첨부파일(선택)</td>
						<td class="col-4" align="left">
					       <!-- 첨부파일 1영역 -->
					       
							<div id="file1" class="row my-1" >
								<div id="file-bitton-aera" class="col-11">
								 <c:if test="${qna.qnaFileRename01 ne null}">
				 			        <div class="col"><img src="/resources/qnaUploadFiles/${qna.qnaFileRename01 }" style="width:150px; height:150px;"></div>
			 			        </c:if>
								<input type="text" id="text-box" readonly style="width:90%" placeholder="파일을 등록하세요" value="${qna.qnaFileRename01 }"> <label for="input-file" >
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="skyblue" class="bi bi-file-earmark-fill" viewBox="0 0 16 16">
								<path d="M4 0h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2zm5.5 1.5v2a1 1 0 0 0 1 1h2l-3-3z"/>
								</svg>
								
								파일등록 </label>
								<input type="file" accept="image/jpeg, image/png, image/jpg" class="isFile" id="input-file" style="display:none" name="reloadFile" onchange="filename(this)" onclick="imgCheck();">
							</div>
							<!-- 첨부파일1영역종료 -->
						</td>
					</tr>
					<tr>
						<td class="col-4" align="left">
							<!-- 첨부파일 2영역 -->
							<div id="file2" class="row my-1">
								<div id="file-bitton-aera" class="col-11">
								<c:if test="${qna.qnaFileRename02 ne null}">
				 			        <div class="col"><img src="/resources/qnaUploadFiles/${qna.qnaFileRename02 }" style="width:150px; height:150px;"></div>
			 			        </c:if>
								<input type="text" id="text-box" readonly style="width:90%" placeholder="파일을 등록하세요" value="${qna.qnaFileRename02 }"> <label for="input-file2" >
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="skyblue" class="bi bi-file-earmark-fill" viewBox="0 0 16 16">
									<path d="M4 0h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2zm5.5 1.5v2a1 1 0 0 0 1 1h2l-3-3z"/>
									</svg>
								파일등록 </label>
								<input type="file" accept="image/jpeg, image/png, image/jpg"  class="isFile" id="input-file2" style="display:none" name="reloadFile" onchange="filename(this)">
							</div>
							<!-- 첨부파일2영역종료 -->
						</td>
					</tr>
					<tr>
						<td class="col-4" align="left">
							<!-- 첨부파일 3영역 -->
							<div id="file3" class="row my-1">
								<div id="file-bitton-aera" class="col-11">
								<c:if test="${qna.qnaFileRename03 ne null}">
				 			        <div class="col"><img src="/resources/qnaUploadFiles/${qna.qnaFileRename03 }" style="width:150px; height:150px;"></div>
			 			        </c:if>
								<input type="text" id="text-box" readonly style="width:90%" placeholder="파일을 등록하세요" value="${qna.qnaFileRename03 }"> <label for="input-file3" >
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="skyblue" class="bi bi-file-earmark-fill" viewBox="0 0 16 16">
									<path d="M4 0h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2zm5.5 1.5v2a1 1 0 0 0 1 1h2l-3-3z"/>
									</svg>
								파일등록 </label>
								<input type="file" accept="image/jpeg, image/png, image/jpg" class="isFile" id="input-file3" style="display:none" name="reloadFile" onchange="filename(this)">
			
							</div>
							
							<!-- 첨부파일3영역종료 -->
							<!-- 파일 이름 재전송 -->
							<input type="hidden" name="qnaFilename01" value="${qna.qnaFilename01 }">
							<input type="hidden" name="qnaFileRename01" value="${qna.qnaFileRename01 }">
							
							
							<input type="hidden" name="qnaFilename02" value="${qna.qnaFilename02 }">
							<input type="hidden" name="qnaFileRename02" value="${qna.qnaFileRename02 }">
							
							<input type="hidden" name="qnaFilename03" value="${qna.qnaFilename03 }">
							<input type="hidden" name="qnaFileRename03" value="${qna.qnaFileRename03 }">
							
							
							<!-- 첨부파일 -->
					    </td>
					</tr>
					<tr>
						<td class="col-4" colspan="2" align="center" style="border:none;">
							<input onclick="qnaCheck();" type="button" value="수정" class="btn btn-warning btn-sm">
							<input type="reset" value="취소" class="btn btn-warning btn-sm">
							<!-- <button type="button" onclick="backBtn()" class="btn btn-warning btn-sm">목록</button> --> 
							<button type="button" onclick="location.href='/qna/list.kh?page=${page }&qnaStatus=${qna.qnaStatus}'" class="btn btn-warning btn-sm">목록</button>  
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
	<!-- 1:1 문의 입력 폼 End -->
		<hr>
	</section>
<br>
</main>
<!-- main contents End -->


<!-- 파일 업로드 스크립트 -->
<script>
function backBtn() {
    history.back();
}

//파일이름 출력용
function filename(obj) {
	var name = obj.files[0].name;
	var text = obj.previousElementSibling.previousElementSibling;
	text.value=name;
	
	imgCheck(obj);
	
	
}


///// 이미지파일 체크 시작 ////
var imgFile = document.querySelectorAll('.isFile');
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

function titleLengthCk(thisInput){
 	console.log(thisInput.value.length);
 	if(thisInput.value.length>30){
 		thisInput.value = thisInput.value.substr(0,30);
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