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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel="stylesheet" href="/resources/css/book/bookregist.css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
</head>

<body onload="contentsInSumNote()">
	<jsp:include page="../header/header.jsp" />
	<main>
		<form action="/book/oriSeriesModify.do" method="post" enctype="multipart/form-data">
		<section class="container">
			<article>
				<h1>도서 수정</h1>
				<div id="title-area">
					<div class="title-text">

						<input type="text" id="title-text" readonly="readonly"
							value="${oBook.bookTitle}">
							<input type="hidden" name="bookNo" value="${oBook.bookNo }">
					</div>


				</div>

			</article>
			<article class="info-area row">
				<div class="col-md-6" id="pic-area">
					<h5 class="cover-title">표지</h5>

					<div class="align-items-center">
						<div class="align-items-center">
							<img class="headerimg"
								src="/resources/bookImg/${oBook.coverRename }">


						</div>

					</div>

				</div>
				<div class="col-md-6">
					<div class="category">카테고리 : ${oBook.category }</div>
					<h5>작품소개</h5>
					<div id="info-text1">${oBook.bookInfo }</div>
					<div id="tag-area"></div>

				</div>


			</article>

			<hr>
			<article id="first-contents">
				<h2>수정</h2>
				<span> 
				<c:if test="${oSeries.seriesNo != 1 }">
				<label> <input type="hidden" name="seriesNo"
							value="${oSeries.seriesNo}"> 
							<input type="checkbox"
							name="paidCheck" id="pay-check" <c:if test="${oSeries.paidCheck == 'Y '}">checked</c:if> > 유료화 여부
							<input type="hidden" id="paidCheckHidden"  name="paidCheck" value="N">
					
					</label> 
					</c:if>
					</span>
				<div id="contents-text">
					<input type="text" class="text-input" value="${oSeries.title }" name="title" maxlength="50">


				</div>

				<div id="contents-pic-area">
					<div class="align-items-center">
						<div class="align-items-center">
							<img class="contentsimg"
								src="/resources/bookImg/${oSeries.subPicRename }"> <label
								class="input-file-button float-md-start"> <img
								src="/resources/img/book/icons8-camera-96.png" alt=""
								id="camera2"> 
								<input type="file" name="subPicture" class="isFile" style="display: none" accept="image/jpeg, image/png, image/jpg"
								onchange="imgView(this);">
							</label>

						</div>

					</div>
				</div>

				<div class="contents-textarea">

					<div id="summernote"></div>
					<textarea rows="" cols="" style="display: none" name="modifyContents"
						required="required">
                    ${oSeries.contents }
                    </textarea>
					<input type="hidden" name ="subPicRename" value="${oSeries.subPicRename }">
					 <input type="hidden" name="subPic" value="${oSeries.subPic }">
					 <input type="hidden" name="seriesNo" value="${oSeries.seriesNo }">
				</div>
				<div class="contents-button row">

					<button class="col-6">수정신청</button>
					<button class="col-6" type="button" onclick="history.go(-1);">취소</button>
				</div>


			</article>
		</section>
		</form>
		
	</main>
	<jsp:include page="../footer/footer.jsp" />
</body>
<script type="text/javascript">

</script>
<script src="/resources/js/book/bookregist-modify.js"></script>

</html>