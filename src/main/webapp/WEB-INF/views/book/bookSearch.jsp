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
				
				
				<form action="/book/bookSearchList.do" method="get">
				
					<div id="serch-category">
						<select name="category" id="serchCate">
							<option value="title">제목</option>
							<option value="writer">작가명</option>
						</select>
						</div>
					<div id="searchbar">
	
						<input type="text" class="searchTerm" name="searchValue" placeholder="검색어를 입력하세요">
						<button type="submit" class="searchButton">
							<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
								fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
	                            <path
									d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
	                        </svg>
						</button>
						
	
					</div>
				</form>
			</article>
			<hr>
			<!-- 태그 영역 -->
			<article id="tag-area" class="container">
				<div class="area-title">추천 해시태그</div>
				<div id="tagbox-area">
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=fantasy&bookCate=origin'">판타지</span> 
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=now&bookCate=origin'">현대</span> 
						<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=daily&bookCate=origin'">일상</span> 
						<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=history&bookCate=origin'">역사</span> 
						<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=child&bookCate=origin'">어린이를 위한</span> 
						<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=adult&bookCate=origin'">어른을 위한</span>
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=woman&bookCate=origin'">여성을 위한</span> 
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=man&bookCate=origin'">남성을 위한</span>
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=all&bookCate=origin'">모두를 위한</span> 
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=horror&bookCate=origin'">겁쟁이 출입금지</span>
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=gag&bookCate=origin'">배꼽 빠지는</span> 
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=move&bookCate=origin'">마음이 따뜻해지는</span>
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=heart&bookCate=origin'">설레이는</span> 
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=heart&bookCate=origin'">눈물이 나는</span>
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=popcorn&bookCate=origin'">팝콘 각</span>
					<span class="tagbax" onclick="location.href='/book/bookSearchList.do?tag=cider&bookCate=origin'">사이다 마시는</span>
				</div>


			</article>
			<hr>
			<!-- 카테고리 영역 -->
			<article id="recommend-cate-area" class="container">
				<div class="area-title">추천 카테고리</div>

				<div class="cate-area row">
					<div class="col-md-6">
						<div class="cate-area-title">일반도서</div>
						<div id="category-one"  onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=tale'">
							<div class="card mb-3" style="max-width: 540px;">
						 		<div class="row g-0">
									<div class="col-md-4">
										<img src="/resources/bookImg/${img1 }"
											class="img-fluid rounded-start" alt="...">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">동화</h5>
											<p class="card-text">어린이들을 위한 땅콩북스의 동화</p>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="category-one">
							<div class="card mb-3" style="max-width: 540px;">
								<div class="row g-0"  onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=poem'">
									<div class="col-md-4">
										<img src="/resources/bookImg/${img2 }"
											class="img-fluid rounded-start" alt="...">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">시</h5>
											<p class="card-text">여러 시인들이 만들어낸 멋진 시를 읽어보세요!</p>
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
								<div class="row g-0"  onclick="location.href='/book/bookCatogoryList.do?bookCate=origin&category=novel'">
									<div class="col-md-4">
										<img src="/resources/bookImg/${img3 }"
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
					 			<div class="row g-0" onclick="location.href='/book/bookCatogoryList.do?bookCate=origin&category=tale'">
									<div class="col-md-4">
										<img src="/resources/bookImg/${img4 }"
											class="img-fluid rounded-start" alt="...">
									</div>
									<div class="col-md-8">
										<div class="card-body" >
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