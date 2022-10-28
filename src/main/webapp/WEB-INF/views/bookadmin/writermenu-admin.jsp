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
						<button onclick="location.href='/book/writerView.do'">관리자용
							프로필등록</button>
					</c:if>

					<c:if test="${oneWriter != null }">
						<button onclick="location.href='/book/modifyView.do'">관리자용
							프로필수정</button>
					</c:if>

				</div>


				<div class="col-md-6" id="wirte"
					<c:if test="${oneWriter != null}">
					onclick="location.href='/book/bookRegistView.do'" 
				</c:if>
					<c:if test="${oneWriter == null}">
					onclick="alert('관리자용 프로필을 등록하세요')" 
				</c:if>>
					<img src="/resources/img/book/icons8-write-48.png" alt="">
					새로운 시리즈 등록
				</div>

			</article>
			<hr>
			<article id="list-area" class="row">
				<div id="title">일반도서 등록 목록</div>

				<!-- 내가 쓴 시리즈 출력 -->
				<c:if test="${nsList!=null }">
					<!-- 시리즈 반복 -->
					<c:forEach items="${nsList }" var="nSeries">

						<div id="book" class="col-md-3 col-6">


							<!-- 각 시리즈 삽화 출력 -->
							<div id="img"
								onclick="window.open('/book/NordetailSeries.do?bookNo=${nSeries.bookNo }&seriesNo=${nSeries.seriesNo }')">

								<img src="/resources/bookImg/${nSeries.subpicRename }" alt="">
							</div>

							<!-- 책이름 및 화수 시리즈 제목 출력 -->
							<div id="book-title" class="text-truncate"
								onclick="location.href='/book/norBookInfo?bookNo=${nSeries.bookNo }'">${nSeries.bookTitle }</div>
							<div id="book-info" class="text-truncate"
								onclick="window.open('/book/NordetailSeries.do?bookNo=${nSeries.bookNo }&seriesNo=${nSeries.seriesNo }')">${nSeries.seriesNo }화
								${nSeries.title }</div>


						</div>
					</c:forEach>
					<!-- 시리즈 반복문 종료 -->
				</c:if>
				<!-- 시리즈 출력 종료 -->

			</article>

			<!--  페이징 영역 -->
			<article id="page-area">

				<!-- 이전 페이지 출력 -->
				<c:if test="${bPage.startNavi != 1 && bPage.startNavi > 0  }">
					<span class="prev"> <a
						href="/book/writerMenu.do?page=${bPage.startNavi-1 }"> < </a>
					</span>
				</c:if>

				<!-- 페이지 번호 출력 -->
				<c:forEach var="p" begin="${bPage.startNavi}"
					end="${bPage.endNavi}">

					
					<c:if test="${p == bPage.currentPage  }">
						<span class="pageNow"> 
							${p }
						</span>	
					</c:if> 
					<c:if test="${p == 0  }">
						<span class="pageNow"> 
							${p+1 }	
						</span>	
					</c:if>
					

					<c:if test="${p != bPage.currentPage && p !=0}">
						<span class="pages"> <a href="/book/writerMenu.do?page=${p }">${p }</a>
						</span>
					</c:if>

				</c:forEach>
				<!-- 다음 페이지 출력 -->
				<c:if test="${bPage.endNavi ne bPage.maxPage  }">
					<span class="next"> <a
						href="/book/writerMenu.do?page=${bPage.endNavi+1 }"> > </a>
					</span>
				</c:if>


			</article>
			<!-- 페이징 영역 종료 -->


		</section>
	</main>
	<jsp:include page="../footer/footer.jsp" />
</body>
<script type="text/javascript" src="/resources/js/book/writemenu.js"></script>
</html>