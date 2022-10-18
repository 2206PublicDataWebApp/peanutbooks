<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>땅콩북스</title>
<link rel="stylesheet" href="/resources/css/main-style.css">
</head>
<body>
	<jsp:include page="./header/header.jsp" />

	<main>
		<section class="container" id="slide-img">

			<div id="carouselExampleCaptions" class="carousel slide"
				data-bs-ride="false">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="/resources/img/main/woman-g803af235f_1280.jpg"
							class="d-block" alt="프로모션 작품1">
						<div class="carousel-caption d-md-block">
							<h4>작품제목</h4>
							<p>작품설명 어쩌고 저쩌고</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="/resources/img/main/sailboat-g9d3a88421_1920.jpg"
							class="d-block" alt="프로모션 작품2">
						<div class="carousel-caption d-md-block">
							<h4>작품제목2</h4>
							<p>두번째 작품설명 이렇쿵 저렇쿵</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="/resources/img/main/christmas-g4fdade62f_1280.jpg"
							class="d-block alt=" 프로모션작품3">
						<div class="carousel-caption d-md-block">
							<h4>작품제목3</h4>
							<p>세번째 작품 설명 이렇쿵 저렇쿵</p>
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
						<div id="main-recommand" class="rounded border container row">
							<div id="recommnad-pic-1" class="col-md-3">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div id="recommnad-text-1" class="col-md-9">
								<div id="reco-text-1">이달의 추천</div>
								<div id="reco-text-2">이번 달에 땅콩북스에서 가장 추천하는 책(여기에는 작품 제목과
									작품 소개글이 자동 출력되어야 합니다) 이번 달에 땅콩북스에서 가장 추천하는 책(여기에는 작품 제목과 작품
									소개글이 자동 출력되어야 합니다) 이번 달에 땅콩북스에서 가장 추천하는 책(여기에는 작품 제목과 작품 소개글이
									자동 출력되어야 합니다) 이번 달에 땅콩북스에서 가장 추천하는 책(여기에는 작품 제목과 작품 소개글이 자동
									출력되어야 합니다)</div>
							</div>

						</div>
					</div>
					<div class="second-category row">
						<div class="category-name">동화</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>
					</div>


					<div class="third-category row">
						<div class="category-name">역사</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>


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
						<div id="main-recommand" class="rounded border container row">
							<div id="recommnad-pic-1" class="col-md-3">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div id="recommnad-text-1" class="col-md-9">
								<div id="ori-text-1">이달의 추천</div>
								<div id="ori-text-2">이번 달에 땅콩북스에서 가장 추천하는 책(여기에는 작품 제목과 작품
									소개글이 자동 출력되어야 합니다) 이번 달에 땅콩북스에서 가장 추천하는 책(여기에는 작품 제목과 작품 소개글이
									자동 출력되어야 합니다) 이번 달에 땅콩북스에서 가장 추천하는 책(여기에는 작품 제목과 작품 소개글이 자동
									출력되어야 합니다) 이번 달에 땅콩북스에서 가장 추천하는 책(여기에는 작품 제목과 작품 소개글이 자동 출력되어야
									합니다)</div>
							</div>

						</div>
					</div>
					<div class="second-category row">
						<div class="category-name">동화</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>
					</div>


					<div class="third-category row">
						<div class="category-name">시</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>
						<div class="col-4">
							<div class="cate-img">
								<img src="./resources/img/main/landscape-g2a33940ce_1280.jpg"
									class="rounded" alt="">
							</div>
							<div class="book-name">제목</div>
						</div>


					</div>

				</div>

			</article>


		</section>


	</main>

	<script src="/resources/js/main.js"></script>

	<jsp:include page="./footer/footer.jsp" />


</body>
</html>