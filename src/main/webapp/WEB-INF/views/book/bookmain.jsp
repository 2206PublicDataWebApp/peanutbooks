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
<link rel="stylesheet" href="/resources/css/book/bookmain-style.css">
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
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

</head>

<body>
	<jsp:include page="../header/header.jsp" />

	<main>
		<section>
			<article class="container" id="fisrt-article">
				<div id="info-article" class="row">
					<div class="img-area col-md-6">
						<img src="/resources/bookImg/${oBook.coverRename}" alt="표지그림">

					</div>
					<div class="info-area col-md-6">
						<div id="title">${oBook.bookTitle }</div>
						<div id="wirter-text">작가 : ${oBook.memberNickName }</div>


						<div class="tag">
							<c:if test="${hTag.hashTag1 != 'none' }">
							#${hTag.hashTag1 }<br>
							</c:if>
							<c:if test="${hTag.hashTag2 != 'none'}">
							#${hTag.hashTag2 }<br>
							</c:if>
							<c:if test="${hTag.hashTag3 != 'none'}">
							#${hTag.hashTag3 }</c:if>
						</div>


						<div class="openbutton">
							<button id="bookopenbutton">상세 열람</button>
						</div>
						<div class="userbutton container">
							<div class="row">
								<div class="col-4">
									<img src="/resources/img/book/icons8-bookmark-52.png" alt="">
									<br> 내 서재
								</div>
								<div class="col-4">
									<img src="/resources/img/book/icons8-reply-48.png" alt="">
									<br> 감상쓰기
								</div>
								<div class="col-4">
									<img src="/resources/img/book/icons8-star-52.png" alt="">
									<br> 별점주기
								</div>
							</div>
						</div>
						<div id="plusbutton">
						
						<!-- 작가일때만 보이는 버튼 -->
						<c:if test="${loginMember.memberId == oBook.memberId}">
							<button>다음편 쓰기</button>
						</c:if>
						
						<!-- 관리자일때만 보이는 버튼 -->
						<c:if test="${loginMember.adminYN == 'Y' }">
							<button>삭제하기</button>
						</c:if>
							
						</div>
					</div>
				</div>

			</article>
			<hr>
			<article class="container">
				<div class="info-title">
					<div class="sub-title">작품소개</div>
					<div class="sub-info">${oBook.bookInfo }</div>
				</div>
				<div class="step-title">
					<div class="sub-title">목차</div>
					<div class="sub-info">

						<!-- 목차 반복문 시작 없으면 출력되지 않음-->
						<c:if test="${osList != null }">
							<ol>

								<c:forEach items="${osList }" var="oSeries">
									<li>${oSeries.title }
										<button>수정</button>
									</li>
								</c:forEach>

							</ol>
						</c:if>

						<!-- 목차 반복문 종료 -->

					</div>
				</div>

			</article>
			<article class="container" id="reply-area">
				<div id="star-area">
					<div class="sub-title">리뷰현황</div>
					<div class="sub-info row">
						<div class="star-area-1 col-md-6">
							<div class="star-title">사용자 평균(12명)</div>
							<div class="star-score">★★★☆☆</div>
						</div>
						<div class="star-area-2 col-md-6">
							<div class="star-title">별점주기</div>
							<div class="star-score">☆☆☆☆☆</div>
						</div>

					</div>
				</div>
				<div id="reply">
					<div class="sub-title">독자 감상(<span id="replyLength"></span>)</div>
					<div class="reply">

						<div class="container mt-1 view-relply">
							<!--리플 한게 보이는 영역-->

							<div class="row  d-flex justify-content-center repleOne">

								<div class="card p-3">

									<div class="d-flex justify-content-between align-items-center">

										<div class="user d-flex flex-row align-items-center">
											<span class="col-3 d-inline-block text-truncate">
												<small class="font-weight-bold username">사용자이름 길면 잘림</small>
											</span> 
											<span class="col-11">
												<small class="font-weight-bold">
													저는 이 책이 매우 마음에 들어요! cool
												</small>
											</span>

										</div>
									</div>
									<div class="mt-2">

										<div class="reply row">
											<div class="col-6">
												<span class="modify-del-button"> <small>삭제</small> <small>수정</small>
												</span>
											</div>
											<div class="text-truncate col-6 date">
												<small>2 days ago</small>
											</div>
										</div>

									</div>


								</div>


							</div>

						</div>
						<!--리플 한게 보이는 영역 끝-->

						<div class="container mt-3 view-relply">

							<div class="row">
								<div class="col-md-11 col-9">
									<textarea name="reContents" id="reply-text" rows="3"></textarea>
								</div>
								<div class="col-md-1 col-3 reply-button-area">
									<button id="reply-button" onclick="replyRegist(${oBook.bookNo })">등록</button>
								</div>

							</div>
						
						</div>
					</div>
				</div>
			</article>
			<div id="listbutton">
			<button onclick="history.go(-1);">이전으로</button>
			</div>
		</section>
	</main>

	<jsp:include page="../footer/footer.jsp" />
</body>
<script src="/resources/js/book/bookmain.js"></script>

</html>