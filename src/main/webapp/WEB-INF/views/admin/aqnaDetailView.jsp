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
		<div class="container text-center">
			<div class="row row-cols-1">
			   <div class="col" style="background-color: #5e5e5e; color: white; height:45px" vertical-align: middle;>${qna.memberId}님의 문의 내역 확인</div>        
			</div>
		<!-- 세부페이지 큰 제목 끝 -->
		<br>
		<hr>
			<!-- 세부 메뉴 시작 -->
			<ul class="mainUl">
	    		<li class="mainLi"><div style="text-align:right">
			    		<a href="/admin/qnaList.kh">총문의<br>1&nbsp;&nbsp;&nbsp;&nbsp;</a>
			    	</div>
			    </li>
			    <li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/admin/qnaList.kh">답변완료<br>1</a>
			    	</div>
				</li>
				<li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/admin/qnaList.kh">처리중<br>1</a>
			    	</div>
				</li>
				<li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:left">
			    		<a href="/admin/qnaList.kh">접수<br>&nbsp;&nbsp;&nbsp;&nbsp;1</a>
			    	</div>
	    		</li>
	 		</ul>
	 		<!-- 세부 메뉴 끝 -->
		<hr>
		<br>
		<!-- 세부페이지 body 시작 -->

		<div class="row row-cols-1" style="width:80%";>
			<table align="center" class="table col-6" style="border-color:skyblue;">
				<tr>
					<td class="col-2" width="20%" align="center">문의유형</td>
					<td class="col-4" width="80%">
						<c:if test="${qna.qnaCategory == 'member' }">회원관련</c:if>
						<c:if test="${qna.qnaCategory == 'point' }">포인트관련</c:if>
						<c:if test="${qna.qnaCategory == 'books' }">도서관련</c:if>
						<c:if test="${qna.qnaCategory == 'others' }">기타</c:if>
					</td>
				</tr>
				<tr>
					<td class="col-2" align="center">작성자</td>
					<td class="col-4">
						<input type="text" name="qnaTitle" class="form-control" value="${qna.memberId }">
					</td>
				</tr>
				<tr>
					<td class="col-2" align="center">제목</td>
					<td class="col-4">
						<input type="text" name="qnaTitle" class="form-control" value="${qna.qnaTitle }">
					</td>
				</tr>
				<tr>
					<td class="col-2" align="center">작성일</td>
					<td class="col-4">${qna.qCreateDate }</td>
				</tr>
			<c:if test="${qna.qUpdateDate ne null}">
				<tr>
					<td class="col-2" align="center">수정일</td>
					<td class="col-4">${qna.qUpdateDate }</td>
				</tr>
			</c:if>
				<tr>
					<td class="col-2" align="center">내용</td>
					<td class="col-4">
						<textarea class="form-control" id="exampleFormControlTextarea1" cols="5" rows="5" name="qnaContents">${qna.qnaContents }</textarea>  
					</td>
				</tr>
			<!-- 첨부파일 -->
				<tr>
					<td class="col-2" align="center" rowspan="3">첨부파일</td>
					<td class="col-4" style="border:none";>
				     
			            <div class="question-area container">
			                <div class="accordion rounded" id="accordionExample">
			                    <div class="accordion-item rounded ">
			                        <h2 class="accordion-header rounded" id="headingOne">
			                            <button class="accordion-button rounded collapsed" type="button" data-bs-toggle="collapse"
			                                data-bs-target="#collapseOne" aria-controls="collapseOne">
			                                첨부된 파일 보기
			                            </button>
			                        </h2>
			                        <div id="collapseOne" class="accordion-collapse collapse rounded" aria-labelledby="headingOne">
			                            <div class="accordion-body">
									     <!-- 첨부파일 1영역 -->
											<div id="file1" class="row my-1" >
												 <c:if test="${qna.qnaFileRename01 ne null}">
								 			        <div class="col"><img src="/resources/qnaUploadFiles/${qna.qnaFileRename01 }" style="width:150px; height:150px;"></div>
							 			        </c:if>
											</div>
										<!-- 첨부파일1영역종료 -->
						
										<!-- 첨부파일 2영역 -->
											<div id="file2" class="row my-1">
												<c:if test="${qna.qnaFileRename02 ne null}">
								 			        <div class="col"><img src="/resources/qnaUploadFiles/${qna.qnaFileRename02 }" style="width:150px; height:150px;"></div>
							 			        </c:if>
											</div>
										<!-- 첨부파일2영역종료 -->
										<!-- 첨부파일 3영역 -->
											<div id="file3" class="row my-1">
												<c:if test="${qna.qnaFileRename03 ne null}">
								 			        <div class="col"><img src="/resources/qnaUploadFiles/${qna.qnaFileRename03 }" style="width:150px; height:150px;"></div>
							 			        </c:if>
											</div>
									<!-- 첨부파일3영역종료 -->
			                            </div>
			                        </div>
			                    </div>
							</div>
						</div>
					<!-- 첨부파일 -->
					</td>
				</tr>
				<tr>
					<td class="col-6" colspan="2" align="center">
					</td>
				</tr>
			</table>
		<br>
			<!-- 관리자 답변 작성 폼  -->
			<form action="/admin/answer.kh" method="post" name="aqnaForm" id="aqnaForm">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" name="qnaNo" value="${qna.qnaNo }">
	 		<input type="hidden" name="searchCondition" value="${searchCondition}">
			<input type="hidden" name="searchValue" value="${searchValue}">  
			<table class="table col-6" style="border-color:orange";>
				<tr>
					<td class="col-6" colspan="2" align="center"><b>회원 문의 답변</b></td>
				</tr>
				<tr>
					<td class="col-2" align="center">작성자</td>
					<td  class="col-4">
						<input type="text" name="answerWriter" class="form-control" value="${sessionScope.loginMember.memberId}">
					</td>
				</tr>
				<c:if test="${qna.aCreateDate ne null}">
				<tr>
					<td class="col-2" align="center">작성일</td>
					<td class="col-4">${qna.aCreateDate }</td>
				</tr>
				</c:if>
				<tr>
					<td class="col-2" align="center">답변</td>
						<c:if test="${qna.answerContents eq null }">
							<td class="col-4">
								<textarea class="form-control" id="exampleFormControlTextarea1" cols="3" rows="5" name="answerContents" placeholder="고객 문의글 &#10; ${qna.qnaContents }"></textarea>
							</td>
						</c:if>
						<c:if test="${qna.answerContents ne null }">
							<td class="col-4">
								<textarea class="form-control" id="exampleFormControlTextarea1" cols="3" rows="10" name="answerContents" >${qna.answerContents }</textarea>
							</td>
						</c:if>
				</tr>
				<tr>
					<td class="col-6" colspan="2" align="center" style="border:none;">
						<c:if test="${qna.qnaStatus eq 'Y' }">
							<button type="submit" class="btn btn-warning btn-sm">답변수정</button>
						</c:if>
						<c:if test="${qna.qnaStatus eq 'N' }">
							<button type="submit" class="btn btn-warning btn-sm">답변등록</button>
						</c:if>
						<button type="button" onclick="backBtn()" class="btn btn-warning btn-sm">목록</button>
					</td>
				</tr>
			</table>
			</form>
			<!-- 관리자 답변 작성 폼  -->
		</div>
	</section>
<br><br>
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

</script>

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>