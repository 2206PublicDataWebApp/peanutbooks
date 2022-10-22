<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
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
<link rel="stylesheet" href="/resources/css/book/writemenu.css">
</head>
<body>
	<jsp:include page="../header/header.jsp" />
	<main>
		<section class="container">
			<article id="profile-area" class="row">


				<div class="col-md-6" id="profile">
					<c:if test="${oneWriter == null }">
						<button onclick="location.href='/book/writerView.do'">작가
							프로필등록</button>
					</c:if>

					<c:if test="${oneWriter != null }">
						<button onclick="location.href='/book/modifyView.do'">작가
							프로필수정</button>
					</c:if>

				</div>


				<div class="col-md-6" id="wirte" onclick="location.href='/book/bookRegistView.do'">
					<img src="/resources/img/book/icons8-write-48.png" alt="" >
					새로운 시리즈 등록
				</div>

			</article>
			<hr>
			<article id="list-area" class="row">
				<div id="title">내 시리즈 목록</div>
				<div id="book" class="col-md-3 col-6">
					<div id="check">승인 대기 중</div>
					<div id="img">
						<img src="/resources/img/main/christmas-g4fdade62f_1280.jpg"
							alt="">
					</div>
					<div id="book-title" class="text-truncate">멋진제목멋진제목멋진제목멋진제목멋진제목멋진제목멋진제목</div>
					<div id="book-info" class="text-truncate">멋진 소개멋진 소개멋진 소개멋진
						소개멋진 소개멋진 소개멋진 소개</div>
				</div>

			</article>
		</section>
	</main>
	<jsp:include page="../footer/footer.jsp" />
</body>
<script type="text/javascript" src="/resources/js/book/writemenu.js"></script>
</html>