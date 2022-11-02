<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>땅콩북스</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel="stylesheet" href="/resources/css/book/bookregist.css">
 <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

</head>
<body onload = "loadTitle();">
	<jsp:include page="../header/header.jsp" />
	<main>
		<form action="/book/ModifyOriBook.do" method="post" enctype="multipart/form-data">
			<section class="container">
				<article>
					<h1>도서등록</h1>
					<div id="title-area">
						<div class="title-text">
							<input type="hidden" id="hidden-title" value="${oBook.bookTitle }">
							<input type="hidden" id="hidden-cover" name ="coverRename" value="${oBook.coverRename }">
							<input type="hidden" id="bookNo" name ="bookNo" value="${oBook.bookNo }">
							<input type="text" id="title-text"
							name="bookTitle" required="required" maxlength="50">
						</div>


					</div>

				</article>
				<article class="info-area row">
					<div class="col-md-6" id="pic-area">
						<h5 class="cover-title">표지등록</h5>

						<div class="align-items-center">
							<div class="align-items-center">
								<img class="headerimg" src="/resources/bookImg/${oBook.coverRename }"> <label
									class="input-file-button float-md-start"> <img
									src="/resources/img/book/icons8-camera-96.png" alt=""
									id="camera1"> <input type="file" name="coverpic"
									class="isFile" style="display: none"
									accept="image/jpeg, image/png, image/jpg"
									onchange="imgView(this);">
								</label>

							</div>

						</div>

					</div>
					<div class="col-md-6">
						<div class="category">
							<select name="category" id="" required="required">
								<option value="">카테고리를 선택하세요</option>
								<option value="novel" 
								<c:if test="${oBook.category == 'novel'}">selected="selected"</c:if>
								>소설</option>
								<option value="essay"
								<c:if test="${oBook.category == 'essay'}">selected="selected"</c:if>
								>에세이</option>
								<option value="tale"
								<c:if test="${oBook.category == 'tale'}">selected="selected"</c:if>
								>동화</option>
								<option value="poem"
								<c:if test="${oBook.category == 'poem'}">selected="selected"</c:if>
								>시</option>
								<option value="other"
								<c:if test="${oBook.category == 'other'}">selected="selected"</c:if>
								>기타</option>
							</select>
						</div>
						<h5>작품소개</h5>
						<textarea name="bookInfo" id="info-text" cols="30" rows="10"
							placeholder="소개를 입력하세요"  onKeyUp="fnChkByte(this)">
							${oBook.bookInfo }
							</textarea>

						<div id="tag-area">
							<h5>해시태그</h5>
							<select name="hashTag1" id="">
								<option value="none">해시태그를 선택하세요</option>
								<option value="fantasy" 
								<c:if test="${hTag.hashTag1 == 'fantasy'}">selected="selected"</c:if>
								>판타지</option>
								<option value="now"
								<c:if test="${hTag.hashTag1 == 'now'}">selected="selected"</c:if>
								>현대</option>
								<option value="daily"
								<c:if test="${hTag.hashTag1 == 'daily'}">selected="selected"</c:if>
								>일상</option>
								<option value="history"
								<c:if test="${hTag.hashTag1 == 'history'}">selected="selected"</c:if>
								>역사</option>
							</select> <select name="hashTag2" id="">
								<option value="none">해시태그를 선택하세요</option>
								<option value="child"
								<c:if test="${hTag.hashTag2 == 'child'}">selected="selected"</c:if>
								>어린이를 위한</option>
								<option value="adult"
								<c:if test="${hTag.hashTag2 == 'adult'}">selected="selected"</c:if>
								>어른을 위한</option>
								<option value="woman"
								<c:if test="${hTag.hashTag2 == 'woman'}">selected="selected"</c:if>
								>여성을 위한</option>
								<option value="man"
								<c:if test="${hTag.hashTag2 == 'man'}">selected="selected"</c:if>
								>남성을 위한</option>
								<option value="all"
								<c:if test="${hTag.hashTag2 == 'all'}">selected="selected"</c:if>
								>모두를 위한</option>
							</select> <select name="hashTag3" id="">
								<option value="none">해시태그를 선택하세요</option>
								<option value="horror"
								<c:if test="${hTag.hashTag3 == 'horror'}">selected="selected"</c:if>
								>겁쟁이 출입금지</option>
								<option value="gag"
								<c:if test="${hTag.hashTag3 == 'gag'}">selected="selected"</c:if>
								>배꼽 빠지는</option>
								<option value="move"
								<c:if test="${hTag.hashTag3 == 'move'}">selected="selected"</c:if>
								>마음이 따뜻해 지는</option>
								<option value="heart"
								<c:if test="${hTag.hashTag3 == 'heart'}">selected="selected"</c:if>
								>설레이는</option>
								<option value="tear"
								<c:if test="${hTag.hashTag3 == 'tear'}">selected="selected"</c:if>
								>눈물이 나는</option>
								<option value="popcorn"
								<c:if test="${hTag.hashTag3 == 'popcorn'}">selected="selected"</c:if>
								>팝콘각</option>
								<option value="cider"
								<c:if test="${hTag.hashTag3 == 'cider'}">selected="selected"</c:if>
								>사이다 마시는</option>
							</select>


						</div>

					</div>


				</article>

				<hr>
				<article id="first-contents">
					
					<div class="contents-button row">

						<button class="col-6">수정</button>
						<button type="button" class="col-6" onclick="location.href='/book/oriBookInfo?bookNo=${oBook.bookNo}'">취소</button>
					</div>


				</article>
			</section>
		</form>
	</main>
	<jsp:include page="../footer/footer.jsp" />
</body>
<script src="/resources/js/book/bookModify-one.js"></script>
</html>