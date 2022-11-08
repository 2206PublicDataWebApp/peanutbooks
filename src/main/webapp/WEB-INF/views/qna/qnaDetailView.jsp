<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세화면</title>
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
	<br>
	<section class="container">
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
	
			<div class="row row-cols-1">
				<table align="center" class="table col-6" border="0" style="width:80%;">
					<tr>
						<td colspan="2"><h3>문의 내용</h3></td>
					<tr>
					<c:if test="${qna.qnaStatus eq 'Y'}">
						<td class="col-6" colspan="2" style="color:gray";>※관리자가 답변을 작성한 게시물은 수정할 수 없습니다.</td>
					</tr>
					</c:if>
					<tr>
						<td class="col-2" align="center">문의유형</td>
						<td class="col-4" align="left">
							<c:if test="${qna.qnaCategory == 'member' }">회원관련</c:if>
							<c:if test="${qna.qnaCategory == 'point' }">포인트관련</c:if>
							<c:if test="${qna.qnaCategory == 'books' }">도서관련</c:if>
							<c:if test="${qna.qnaCategory == 'others' }">기타</c:if>
						</td>
					</tr>
					<tr>
						<td class="col-2" align="center">제목</td>
						<td class="col-4" align="left">${qna.qnaTitle }</td>
					</tr>
					<tr>
						<td class="col-2" align="center">작성일</td>
						<td class="col-4" align="left">${qna.qCreateDate }</td>
					</tr>
					<c:if test="${qna.qUpdateDate ne null}">
					<tr>
						<td class="col-2" align="center">수정일</td>
						<td class="col-4" align="left">${qna.qUpdateDate }</td>
					</tr>
					</c:if>
					<tr>
						<td class="col-2" align="center">내용</td>
						<td class="col-4"><textarea class="form-control" id="exampleFormControlTextarea1" cols="5" rows="5" name="qnaContents" readonly>${qna.qnaContents }</textarea>  </td>
					</tr>
				<!-- 첨부파일 -->
					<tr>
						<td class="col-2" align="center" rowspan="3">첨부파일(선택)</td>
						<td class="col-4" style="border:none;" align="left">
					     <!-- 첨부파일 1영역 -->
							<div id="file1" class="row my-1" >
								 <c:if test="${qna.qnaFileRename01 ne null}">
				 			        <div class="col"><img src="/resources/qnaUploadFiles/${qna.qnaFileRename01 }" style="width:300px; height:300px;"></div>
			 			        </c:if>
							</div>
						</td>
						<!-- 첨부파일1영역종료 -->
					</tr>
					<tr>
						<td class="col-4" colspan="2" style="border:none";  align="left">
						<!-- 첨부파일 2영역 -->
							<div id="file2" class="row my-1">
								<c:if test="${qna.qnaFileRename02 ne null}">
				 			        <div class="col"><img src="/resources/qnaUploadFiles/${qna.qnaFileRename02 }" style="width:150px; height:150px;"></div>
			 			        </c:if>
							</div>
						</td>
						<!-- 첨부파일2영역종료 -->
					</tr>
					<tr>
						<td class="col-4" colspan="2"  align="left">
						<!-- 첨부파일 3영역 -->
							<div id="file3" class="row my-1">
								<c:if test="${qna.qnaFileRename03 ne null}">
				 			        <div class="col"><img src="/resources/qnaUploadFiles/${qna.qnaFileRename03 }" style="width:150px; height:150px;"></div>
			 			        </c:if>
							</div>
					    </td>
					<!-- 첨부파일3영역종료 -->
					</tr>
				<!-- 첨부파일 -->
					
					<c:if test="${qna.qnaStatus eq 'N'}">
					<tr>
						<td class="col-6" colspan="2" align="center" style="border:none;">
							<button type="button" onclick="location.href='/qna/modifyView.kh?qnaNo=${qna.qnaNo }&page=${page }&searchCondition=${searchCondition}&searchValue=${searchValue}'" class="btn btn-warning btn-sm">수정</button>
							<input onclick="qnaRemove(${qna.qnaNo}, ${page});" type="button" value="삭제" class="btn btn-warning btn-sm">
							<button type="button" onclick="backBtn()" class="btn btn-warning btn-sm">목록</button>
						</td>
					</tr>
					</c:if>
					<c:if test="${qna.qnaStatus eq 'Y'}">
					<tr>
						<td class="col-6" colspan="2" align="center" style="border:none;">
							<button type="button" onclick="backBtn()" class="btn btn-warning btn-sm">목록</button> 
						</td>
					</tr>
					<tr>
						<td class="col-2" align="center">작성자</td>
						<td class="col-4">${qna.answerWriter }</td>
					</tr>
					<tr>
						<td width="20%" align="center">작성일</td>
						<td width="80%">${qna.aCreateDate }</td>
					</tr>
					<tr>
						<td class="col-2" align="center">내용</td>
						<td class="col-4"><textarea class="form-control" id="exampleFormControlTextarea1" cols="3" rows="5" name="qnaContents" readonly>${qna.answerContents } </textarea></td>
					</tr>
					</c:if>
				</table>
			</div>
			<!-- 1:1 문의 입력 폼 End -->
		</div>
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

function qnaRemove(qnaNo, page) {
	event.preventDefault(); // 하이퍼링크 이동 방지
	if(confirm("게시물을 삭제하시겠습니까?")) {
		location.href="/qna/remove.kh?qnaNo="+qnaNo+"&page="+page;
	}
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

</script>

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>