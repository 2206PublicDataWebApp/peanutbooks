<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>땅콩북스</title>
<link rel="stylesheet" href="/resources/css/main-style.css">
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
</head>
<body>
	<jsp:include page="./header/header.jsp" />

	<main>

		<section id="slide-img">

			<div id="carouselExampleCaptions" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="2" aria-label="Slide 3"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="3" aria-label="Slide 4"></button>
				</div>
				<div class="carousel-inner">
				<!-- 프로모션 영역 조회수 1위에 작품이 자동 노출됨 -->
					<div class="carousel-item active" data-bs-interval="3000"
					<c:if test="${nList[0].category == 'origin' }">
					onclick="location.href='/book/oriBookInfo?bookNo=${nList[0].bookNo }'"
					</c:if>
					<c:if test="${nList[0].category == 'normal' }">
					onclick="location.href='/book/norBookInfo?bookNo=${nList[0].bookNo }'"
					</c:if>
					>
						<img src="/resources/bookImg/${nList[0].coverRename }"
							class="d-block" alt="프로모션 작품1">
						<div class="carousel-caption d-md-block text-truncate">
							<h4>${nList[0].bookTitle }</h4>
							<p>${nList[0].bookInfo }</p>
						</div>
					</div>
					<div class="carousel-item" data-bs-interval="3000"
					<c:if test="${nList[1].category == 'origin' }">
					onclick="location.href='/book/oriBookInfo?bookNo=${nList[1].bookNo }'"
					</c:if>
					<c:if test="${nList[1].category == 'normal' }">
					onclick="location.href='/book/norBookInfo?bookNo=${nList[1].bookNo }'"
					</c:if>
					>
						<img src="/resources/bookImg/${nList[1].coverRename }"
							class="d-block" alt="프로모션 작품2">
						<div class="carousel-caption d-md-block text-truncate">
							<h4>${nList[1].bookTitle }</h4>
							<p>${nList[1].bookInfo }</p>
						</div>
					</div>
					<div class="carousel-item" data-bs-interval="3000"
					<c:if test="${nList[2].category == 'origin' }">
					onclick="location.href='/book/oriBookInfo?bookNo=${nList[2].bookNo }'"
					</c:if>
					<c:if test="${nList[2].category == 'normal' }">
					onclick="location.href='/book/norBookInfo?bookNo=${nList[2].bookNo }'"
					</c:if>
					>
						<img src="/resources/bookImg/${nList[2].coverRename }"
							class="d-block" alt="프로모션작품3">
						<div class="carousel-caption d-md-block text-truncate">
							<h4>${nList[2].bookTitle }</h4>
							<p>${nList[2].bookInfo }</p>
						</div>
					</div>
					
					<!-- 이벤트 구역 -->
					<div class="carousel-item" data-bs-interval="3000"									
					onclick="location.href='/book/attendaceEvent.do'"					
					>
						<img src="/resources/img/event.png"
							class="d-block" alt="이벤트">
						<div class="carousel-caption d-md-block text-truncate">
							
						</div>
					</div>
				
				
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</section>

		<section class="container">
			<article id="sec2-art1">
				<div id="recommand-category">추천 카테고리</div>
				<div class="category-area">
					<div class="first-category">
						<div class="category-name">추리소설</div>
						<div id="main-recommand"
							class="card mb-3 rounded border container row">

							<div class="row g-0" onclick="location.href='/book/norBookInfo?bookNo=${topScoreDe.bookNo}'">
								<div class="col-md-3 rounded" id="recommnad-pic-1">
									
									<img src="./resources/bookImg/${topScoreDe.coverRename }"
										class="img-fluid rounded-start" alt="...">
								</div>
								<div id="recommnad-text-1" class="col-md-9">
									<div class="card-body">
										<h5 class="card-title" id="reco-text-1">${topScoreDe.bookTitle }</h5>
										<p class="card-text" id="reco-text-2">${topScoreDe.bookInfo }</p>
										<p class="card-text">
											<small class="text-muted">${topScoreDe.inserDate }</small>
										</p>
									</div>
								</div>
							</div>




						</div>
					</div>
					<div class="second-category row">
						<div class="category-name">동화</div>
						
						<!--  동화 탑 4 반복 시작 -->
						<c:forEach items="${topTale }" var="topTale">
						
						<figure class="snip1361 col-md-3 col-6" onclick="location.href='/book/norBookInfo?bookNo=${topTale.bookNo}'">
						  <div class="cate-img">
						 	 <div class="inner"></div>
						  	<img src="/resources/bookImg/${topTale.coverRename }"/>
						  </div>
						  <figcaption>
						    <h3 class="text-truncate">${topTale.bookTitle }</h3>
						    <p>${topTale.bookInfo }</p>
						  </figcaption>
						 
						</figure>
						
						</c:forEach>
							
					</div>


					<div class="third-category row">
						<div class="category-name">역사</div>
						
						<!-- 역사 영역 반복시작 -->
						<c:forEach items="${topHistory }" var="topHistory">
						
						
						<figure class="snip1361 col-md-3 col-6" onclick="location.href='/book/norBookInfo?bookNo=${topHistory.bookNo}'">
						  <div class="cate-img">
						   <div class="inner"></div>
						  	<img src="/resources/bookImg/${topHistory.coverRename }"/>
						  </div>
						  <figcaption>
						    <h3 class="text-truncate">${topHistory.bookTitle }</h3>
						    <p>${topHistory.bookInfo }</p>
						  </figcaption>
						  
						</figure>
						
						</c:forEach>


					</div>

				</div>

			</article>


		</section>

		<section class="container">
			<article id="sec3-art1">
				<div id="recommand-category">피넛 오리지널</div>
				<div class="category-area">
					<div class="first-category">
						<div class="category-name">소설</div>

						<div id="main-recommand"
							class="card mb-3 rounded border container row">

							<div class="row g-0" onclick="location.href='/book/oriBookInfo?bookNo=${topScoreNovel.bookNo}'">
								<div class="col-md-3 rounded" id="recommnad-pic-1">
									<img src="./resources/bookImg/${topScoreNovel.coverRename }"
										class="img-fluid rounded-start" alt="...">
								</div>
								<div id="recommnad-text-1" class="col-md-9">
									<div class="card-body">
										<h5 class="card-title" id="ori-text-1">${topScoreNovel.bookTitle }</h5>
										<p class="card-text" id="ori-text-2">${topScoreNovel.bookInfo }</p>
										<p class="card-text">
											<small class="text-muted">${topScoreNovel.insertDate }</small>
										</p>
									</div>
								</div>
							</div>
						</div>
						<div class="second-category row">
							<div class="category-name">동화</div>
							
							<!-- 반복문 시작 -->
							<c:forEach items="${topTOri }" var="oBook">
								<figure class="snip1361 col-md-3 col-6" onclick="location.href='/book/oriBookInfo?bookNo=${oBook.bookNo}'">
								  <div class="cate-img">
								   <div class="inner"></div>
								  	<img src="/resources/bookImg/${oBook.coverRename }" />
								  </div>
								  <figcaption>
								    <h3 class="text-truncate">${oBook.bookTitle }</h3>
								    <p>${oBook.bookInfo }</p>
								  </figcaption>
								  
								</figure>
							</c:forEach>
							
						</div>


						<div class="third-category row">
							<div class="category-name">시</div>
							<!-- 반복문 시작 -->
							<c:forEach items="${topPOri }" var="oBook">
							<figure class="snip1361 col-md-3 col-6" onclick="location.href='/book/oriBookInfo?bookNo=${oBook.bookNo}'">
								  <div class="cate-img">
								   <div class="inner"></div>
								  	<img src="/resources/bookImg/${oBook.coverRename }" />
								  </div>
								  <figcaption>
								    <h3 class="text-truncate">${oBook.bookTitle }</h3>
								    <p>${oBook.bookInfo }</p>
								  </figcaption>
								  
								</figure>
							</c:forEach>


						</div>
						</div>

					</div>
			</article>
			<article id="article3">
					<div class="category-name">이달의 문장</div>
				<div id="one-text">
					<div id="comment">
						<h4>시간이여 멈추어라 너는 정말 아름답구나</h4>
						- 괴테 파우스트 -
					</div>
				</div>
			</article>


		</section>


	</main>

	<script src="/resources/js/main.js"></script>

	<jsp:include page="./footer/footer.jsp" />


</body>
</html>