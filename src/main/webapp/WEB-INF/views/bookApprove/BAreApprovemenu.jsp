<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>땅콩북스 재승인</title>
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
<style>
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
</head>
<body>
	<jsp:include page="../header/adminheader.jsp" />
	<main>
		<section class="container">
		<br><br>
			<div class="container text-center">
				<div class="row row-cols-1">
					<div class="col" style="background-color: #5e5e5e; color: white; height:45px; vertical-align: middle;">도서 리스트</div>
				</div>
			<br>
				<hr>
					<!-- <div id="title">전체 시리즈 목록</div> -->
					<ul class="mainUl">
			    		<li class="mainLi"><div style="text-align:right">
					    		<a href="/admin/writerMenu.do">전체회원<br></a>
					    	</div>
					    </li>
					    <li class="mainLi">
					    	<div class="col"><img src="/resources/img/sidebar.png"></div>
					    </li>
			    		<li class="mainLi">
			    			<div style="text-align:center">
					    		<a href="/admin/approveYN.kh?checkPermission=Y">승인도서</a>
					    	</div>
						</li>
						<li class="mainLi">
					    	<div class="col"><img src="/resources/img/sidebar.png"></div>
					    </li>
			    		<li class="mainLi">
			    			<div style="text-align:center">
					    		<a href="/admin/approveYN.kh?checkPermission=N">보류도서</a>
					    	</div>
						</li>
						<li class="mainLi">
					    	<div class="col"><img src="/resources/img/sidebar.png"></div>
					    </li>
			    		<li class="mainLi">
			    			<div style="text-align:left">
					    		<a href="/admin/reApproveList.kh">재승인도서</a>
					    	</div>
			    		</li>
			 		</ul>
				<hr>
			</div>
			<br>
		<article id="list-area" class="row" >
				<!-- 내가 쓴 시리즈 출력 -->
				<c:if test="${mbList!=null }">
					<!-- 시리즈 반복 -->
					<c:forEach items="${mbList }" var="oSeries">

						<div id="book" class="col-md-3 col-6">

							<!-- 승인여부 체크 -->
							<div id="check">
							<input type="hidden" value="${oSeries.bookNo }" name="bookNo">
								<button type="button" onclick="reApproveCheck('${oSeries.bookNo }','${oSeries.seriesNo }',${currentPage })" class="btn btn-warning btn-sm" style="width:50pt;height:20pt;">재승인</button>
								<button type="button" class="btn btn-dark btn-sm" style="width:40pt;height:20pt;">보류</button>
							</div>

							<!-- 각 시리즈 삽화 출력 -->
							<div id="img"
								onclick="window.open('/book/OridetailSeries.do?bookNo=${oSeries.bookNo }&seriesNo=${oSeries.seriesNo }')">

								<img src="/resources/bookImg/${oSeries.subPicRename }" alt="">
							</div>

							<!-- 책이름 및 화수 시리즈 제목 출력 -->
							<div id="book-title" class="text-truncate"
								onclick="location.href='/book/oriBookInfo?bookNo=${oSeries.bookNo }'">${oSeries.bookTitle }</div>
							<div id="book-info" class="text-truncate"
								onclick="window.open('/book/OridetailSeries.do?bookNo=${oSeries.bookNo }&seriesNo=${oSeries.seriesNo }')"">${oSeries.seriesNo }화
								${oSeries.title }</div>

						</div>
					</c:forEach>
					<!-- 시리즈 반복문 종료 -->
				</c:if>
				<!-- 시리즈 출력 종료 -->
				<c:if test="${mbList eq null }">
					<div id="book-title" class="text-truncate">데이터가 존재하지 않습니다.</div>
				</c:if>
			</article>

			<!--  페이징 영역 -->
			<article id="page-area">

				<!-- 이전 페이지 출력 -->
				<c:if test="${bPage.startNavi != 1 && bPage.startNavi > 0  }">
					<span class="prev"> <a
						href="/admin/writerMenu.do?page=${bPage.startNavi-1 }"> < </a>
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
						<span class="pages"> <a href="/admin/writerMenu.do?page=${p }">${p }</a>
						</span>
					</c:if>

				</c:forEach>
				<!-- 다음 페이지 출력 -->
				<c:if test="${bPage.endNavi ne bPage.maxPage  }">
					<span class="next"> <a
						href="/admin/writerMenu.do?page=${bPage.endNavi+1 }"> > </a>
					</span>
				</c:if>


			</article>
			<!-- 페이징 영역 종료 -->


		<br>
	<hr>
	</section>
</main>
	<jsp:include page="../footer/footer.jsp" />
<script>
	function reApproveCheck(bookNo, seriesNo, currentPage) {
		if(confirm("재승인하시겠습니까?")) {
			location.href = "/admin/reApprove.kh?bookNo="+bookNo +"&seriesNo="+seriesNo+"&page="+currentPage;
		}
	}

</script>
</body>
<script type="text/javascript" src="/resources/js/book/writemenu.js"></script>
</html>