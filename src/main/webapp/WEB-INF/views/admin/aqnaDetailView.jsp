<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항 상세화면</title>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<link rel="stylesheet" href="/resources/css/admin/qna/css.css" ></link> 
  
</head>

<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
	<!-- 세부페이지 head 시작 -->
	<!-- 세부페이지 큰 제목 -->
	<div class="board_wrap">
		<div class="main_title">
		   <h6>${qna.memberId}님의 문의 내역 확인</h6>        
		</div>
	<!-- 세부페이지 큰 제목 끝 -->

		<!-- 세부 메뉴 시작 -->
		<div class="sub_menu">
		     <ul class="amount">
		        <li>
		          <div>
		            <div class="contents1"><a href="/admin/qnaList.kh">총문의</a></div>
		            <div class="result"><a href="/admin/qnaList.kh">${totalQna }</a></div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1"><a href="/admin/qnaList.kh?qnaStatus=Y">답변완료</a></div>
		            <div class="result"><a href="/admin/qnaList.kh?qnaStatus=Y">${totalAnswer }</a></div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1"><a href="/admin/qnaList.kh?qnaStatus=N">처리중</a></div>
		            <div class="result"><a href="/admin/qnaList.kh?qnaStatus=N">${totalNoAnswer }</a></div>
		          </div>
		        </li>
		     </ul>
	     </div>
		<hr>
		<!-- amount end -->

		<!-- 상세페이지 시작 -->
 		<div class="board_title">
       		<h4 style="text-align:center;">[ 1:1문의내역 ]</h4>
        </div>
        <div class="board_view_wrap">
        	<!-- 작성자 문의 상세보기 -->
			<div class="board_view">
               <div class="title">
				<c:if test="${qna.qnaCategory == 'member' }">[회원관련]</c:if>
				<c:if test="${qna.qnaCategory == 'point' }">[포인트관련]</c:if>
				<c:if test="${qna.qnaCategory == 'books' }">[도서관련]</c:if>
				<c:if test="${qna.qnaCategory == 'others' }">[기타]</c:if>
				${qna.qnaTitle }
               </div>
               <div class="info">
                   <dl>
                       <dt>번호</dt>
                       <dd>${qna.qnaNo }</dd>
                   </dl>
                   <dl>
                       <dt>작성자</dt>
                       <dd>${qna.memberId }</dd>
                   </dl>
                   <dl>
                       <dt>작성일</dt>
                       <dd>${qna.qCreateDate }</dd>
                   </dl>
                   <c:if test="${qna.qUpdateDate ne null}">
                   <dl>
                       <dt>수정일</dt>
                       <dd>${qna.qUpdateDate }</dd>
                   </dl>
                   </c:if>
               </div>
               <div class="cont">
                   ${qna.qnaContents }
               </div>
               <div class="add_file">
	               <div class="accordion" style="cursor:pointer;">
					<b>첨부파일 보기</b>
						</div>
						<div class="panel">
		                    <!-- 첨부파일 1영역 -->
							<div id="file1">
								 <c:if test="${qna.qnaFileRename01 ne null}">
				 			        <img src="/resources/qnaUploadFiles/${qna.qnaFileRename01 }" style="width:400px; height:100%;">
			 			        </c:if>
							</div>
							<!-- 첨부파일1영역종료 -->
		
							<!-- 첨부파일 2영역 -->
							<div id="file2">
								<c:if test="${qna.qnaFileRename02 ne null}">
				 			        <img src="/resources/qnaUploadFiles/${qna.qnaFileRename02 }" style="width:400px; height:100%;">
			 			        </c:if>
							</div>
							<!-- 첨부파일2영역종료 -->
							<!-- 첨부파일 3영역 -->
							<div id="file3">
								<c:if test="${qna.qnaFileRename03 ne null}">
				 			        <img src="/resources/qnaUploadFiles/${qna.qnaFileRename03 }" style="width:400px; height:100%;">
			 			        </c:if>
						</div>
						<!-- 첨부파일3영역종료 -->
					</div>
               </div>
           	</div>
           	<!-- 작성자 문의 상세보기 -->
            <br><br>
            <!-- 관리자 답변 폼 -->
            <!-- 상세페이지 시작 -->
	 		<div class="board_title">
	       		<h4 style="text-align:center;">관리자 답변 작성</h4>
	        </div>
			<form action="/admin/answer.kh" method="post" name="aqnaForm" id="aqnaForm">
		        <div class="board_view_wrap">
		        <!-- 관리자 답변 작성 폼  -->
				<input type="hidden" name="page" value="${page }">
				<input type="hidden" name="qnaNo" value="${qna.qnaNo }">
		 		<input type="hidden" name="searchCondition" value="${searchCondition}">
				<input type="hidden" name="searchValue" value="${searchValue}"> 
		        	<div class="board_view">
						<div class="info">
							<dl>
							    <dt>번호</dt>
							    <dd>${qna.qnaNo }</dd>
							</dl>
							<dl>
							    <dt>답변자</dt>
							    <dd>${sessionScope.loginMember.memberId}</dd>
							</dl>
							<dl>
						    	<c:if test="${qna.qnaStatus eq 'N' }">
								    <dt>답변일</dt>
									    <dd>
											작성 안함.
										</dd>
								</c:if>
						    	<c:if test="${qna.qnaStatus eq 'Y' }">
									<dt>답변일</dt>
									    <dd>
											${qna.aCreateDate }
										</dd>
								</c:if>
							</dl>
		               </div>
		               <div class="cont">
		                   <c:if test="${qna.answerContents eq null }">
								<td class="col-4">
									<textarea class="form-control" id="exampleFormControlTextarea1" cols="3" rows="5" name="answerContents" placeholder="고객 문의글 &#10; ${qna.qnaContents }"></textarea>
								</td>
							</c:if>
							<c:if test="${qna.answerContents ne null }">
								<td class="col-4">
									<textarea class="form-control" id="exampleFormControlTextarea1" cols="3" rows="7" name="answerContents" >${qna.answerContents }</textarea>
								</td>
							</c:if>
		               </div>
		         	</div>
		       	</div>
				<div class="bt_wrap">
	               	<a href="list.html" class="on">목록</a>
	               	<a href="edit.html">수정</a>
	               	<c:if test="${qna.qnaStatus eq 'Y' }">
					<button type="submit" class="on">답변수정</button>
					</c:if>
					<c:if test="${qna.qnaStatus eq 'N' }">
					<button type="submit" class="on">답변등록</button>
					</c:if>
					<button type="button" onclick="backBtn()" class="on">목록</button>
	           	</div>
	        </form>
		</div>
	</div>
	<br>
	<hr>
</main>
<!-- main contents End -->


<!-- 파일 업로드 스크립트 -->
<script>
	function backBtn() {
	    history.back();
	}

function aqnaCheck() {
	if(aqnaForm.qnaContents.value==""){
        alert("내용을 입력하세요");
        qnaForm.aqnaContents.focus();
        return false;
    }
	
	return aqnaForm.submit();
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

/* 아코디언 자바스크립트 */
	var acc = document.getElementsByClassName("accordion");
	var panel = document.getElementsByClassName('panel');
	 
	for (var i = 0; i < acc.length; i++) {
	    acc[i].onclick = function() {
	        var setClasses = !this.classList.contains('active');
	        setClass(acc, 'active', 'remove');
	        setClass(panel, 'show', 'remove');
	 
   			if (setClasses) {
				this.classList.toggle("active");
				this.nextElementSibling.classList.toggle("show");
	        }
	    }
	}
	 
	function setClass(els, className, fnName) {
	    for (var i = 0; i < els.length; i++) {
	        els[i].classList[fnName](className);
	    }
	}
</script>

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>