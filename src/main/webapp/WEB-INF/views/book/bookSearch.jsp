<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>검색</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/book/bookSearch-style.css">
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
</head>

<body>
	<jsp:include page="../header/header.jsp" />

	<main>
		<section class="">
			<article id="serchbar-area" class="container">

				<div id="serch-category">
					<select name="searchCate" id="serchCate">
						<option value="title">제목</option>
						<option value="writer">작가명</option>
					</select>
				</div>
				<div id="searchbar">
					<input type="text" class="searchTerm" placeholder="검색어를 입력하세요">
					<button type="submit" class="searchButton">
						<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
							fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                            <path
								d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                        </svg>
					</button>

				</div>

			</article>
			<hr>
			<article id="tag-area" class="container">
				<div class="area-title">추천 해시태그</div>
				<div id="tagbox-area">
					<span class="tagbax">흥미진진한</span> <span class="tagbax">손수건이
						필요한</span> <span class="tagbax">겁많은 사람은 출입금지</span> <span class="tagbax">행복에
						겨운</span> <span class="tagbax">두근두근한</span> <span class="tagbax">꿀잼
						보장</span> <span class="tagbax">배꼽빠지는</span> <span class="tagbax">스릴넘치는</span>
					<span class="tagbax">유명한 이유가 있는</span> <span class="tagbax">완결작</span>
					<span class="tagbax">해피엔딩 기다리는</span> <span class="tagbax">감동적인</span>
					<span class="tagbax">고전명작</span> <span class="tagbax">반전 넘치는</span>
				</div>


			</article>
			<hr>
			<article id="recommend-cate-area" class="container">
				<div class="area-title">추천 카테고리</div>

				<div class="cate-area row">
					<div class="col-md-6">
						<div class="cate-area-title">일반도서</div>
						<div id="category-one">
							<div class="card mb-3" style="max-width: 540px;">
								<div class="row g-0">
									<div class="col-md-4">
										<img src="/resources/img/main/landscape-g2a33940ce_1280.jpg"
											class="img-fluid rounded-start" alt="...">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">추리소설</h5>
											<p class="card-text">땅콩북스만의 자신있는 추리 소설</p>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="category-one">
							<div class="card mb-3" style="max-width: 540px;">
								<div class="row g-0">
									<div class="col-md-4">
										<img src="/resources/img/main/christmas-g4fdade62f_1280.jpg"
											class="img-fluid rounded-start" alt="...">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">로맨스</h5>
											<p class="card-text">고전로맨스 명작들만 모았습니다.</p>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-6">
						<div class="cate-area-title">피넛 오리지널</div>
						<div id="category-one">
							<div class="card mb-3" style="max-width: 540px;">
								<div class="row g-0">
									<div class="col-md-4">
										<img src="/resources/img/main/woman-g803af235f_1280.jpg"
											class="img-fluid rounded-start" alt="...">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">소설</h5>
											<p class="card-text">땅콩북스의 작가님들이 만든 멋진 소설을 읽어보세요!</p>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="category-one">
							<div class="card mb-3" style="max-width: 540px;">
								<div class="row g-0">
									<div class="col-md-4">
										<img src="/resources/img/main/christmas-g4fdade62f_1280.jpg"
											class="img-fluid rounded-start" alt="...">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">동화</h5>
											<p class="card-text">땅콩북스에서 야심차게 준비한 어린이들을 위한 현대동화</p>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

				</div>




			</article>
		</section>

	</main>
	<jsp:include page="../footer/footer.jsp" />
</body>
<script type="text/javascript" src="/resources/js/book/bookSearch.js"></script>

</html>