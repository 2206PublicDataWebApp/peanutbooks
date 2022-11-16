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
	main *{
		list-style: none;
	    text-decoration: none;
	}
	.board_wrap {
	    width: 1000px;
	    margin: 0px auto;
	}
	.board_title {
	    margin-bottom: 30px;
	    padding: 0;
	}
	main{
		font-size: 10px;
	}
	.main_title{
		background: #5e5e5e; 
		height: 45px;
		width: 100%;
	}
	.main_title > h6{
		text-align: center;
		padding-top: 9px;
		color: #fff; 
	}
	.sub_menu{
		width:100%;
		height: 80px;
	}
	.contents1{
	    font-size: 17px;
	    font-weight: 300;
	}
	.contents1_bold{
	    font-size: 18px;
	    font-weight: bold;
	}
	
	.result{
	    font-size: 20px;
	}
	.amount{
	    /* position: relative; */
	    top:66px;
	    display: flex;
	    width: 100%;
	    background: #fff;
	    /* box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); */
	    margin: auto;
	    margin-top: 10px;
	    padding: 0px;
	}
	.amount > li{
	    flex: 1;
	    height: 80px;
	}
	.amount > li > div{
	    text-align: center;
	    margin-top: 10px;
	    height: 57px;
	}
	.amount > li:not(:last-child) > div{
	    border-right: 1px solid #8b8b8b;
	}
	@media (max-width: 400px) {
		.board_wrap{
			max-width: 100%;
		}
	}
	@media (max-width: 768px) {
		.board_wrap{
			max-width: 100%;
		}
	}
</style>
</head>
<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->


	<main>
		<div class="board_wrap">
			<div class="main_title">
			   <h6>도서 리스트</h6>        
			</div>
		<!-- 세부 메뉴 시작 -->
			<div class="sub_menu" >
			     <ul class="amount row">
			        <li>
			          <div>
			            <div class="contents1">
			            	<a href="/admin/approveYN.kh">전체도서</a>
			            </div>
			            <div class="result"><a href="/admin/approveYN.kh">${allBooks }</a></div>
			          </div>
			        </li>
			        <li>
			          <div>
			            <div class="contents1">
			            	<a href="/admin/approveYN.kh?checkPermission=Y&step=date">승인도서</a>
					    	</div>
			            <div class="result">
			            	<a href="/admin/approveYN.kh?checkPermission=Y&step=date">${approveYes }</a>
					    </div>
			          </div>
			        </li>
			        <li>
			          <div>
			            <div class="contents1">
			            	<a href="/admin/approveYN.kh?checkPermission=N&step=number">보류도서</a>
					    </div>
			            <div class="result">
			            	<a href="/admin/approveYN.kh?checkPermission=N&step=number">${approveNo }</a>
			            </div>
			          </div>
			        </li>
			        <li>
			          <div>
			            <div class="contents1">
			            	<a href="/admin/reApproveList.kh">재승인도서</a>
			            </div>
			            <div class="result">
			            	<a href="/admin/reApproveList.kh">${reApproveBooks }</a>
			            </div>
			          </div>
			        </li>
			     </ul>
		     </div>
		<hr>
	<!-- amount end -->
			
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
								<button type="button" onclick="reApproveCheck('${oSeries.bookNo }','${oSeries.seriesNo }',${oSeries.modifyNo },${currentPage })" class="btn btn-warning btn-sm" style="width:50pt;height:20pt;">재승인</button>
								<button type="button" class="btn btn-dark btn-sm" style="width:40pt;height:20pt;">보류</button>
							</div>

							<!-- 각 시리즈 삽화 출력 -->
							<div id="img"
								onclick="window.open('/book/OridetailSeriesModify.do?bookNo=${oSeries.bookNo }&seriesNo=${oSeries.seriesNo }&modifyNo=${oSeries.modifyNo }')">

								<img src="/resources/bookImg/${oSeries.subPicRename }" alt="">
							</div>

							<!-- 책이름 및 화수 시리즈 제목 출력 -->
							<div id="book-title" class="text-truncate"
								onclick="location.href='/book/oriBookInfo?bookNo=${oSeries.bookNo }'">${oSeries.bookTitle }</div>
							<div id="book-info" class="text-truncate"
								onclick="window.open('/book/OridetailSeries.do?bookNo=${oSeries.bookNo }&seriesNo=${oSeries.seriesNo }')">${oSeries.seriesNo }화
								${oSeries.title }</div>

						</div>
					</c:forEach>
					<!-- 시리즈 반복문 종료 -->
				</c:if>
				<!-- 시리즈 출력 종료 -->
				<c:if test="${osList==null }">
					<b style="color: #616161; font-size: 16px; text-align: center;">재승인할 도서가 없습니다.</b>
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

		<hr>
		</div>
	</main>
	<jsp:include page="../footer/footer.jsp" />
<script>
	function reApproveCheck(bookNo, seriesNo,modifyNo, currentPage) {
		if(confirm("재승인하시겠습니까?")) {
			location.href = "/admin/reApprove.do?bookNo="+bookNo +"&seriesNo="+seriesNo+"&modifyNo="+modifyNo;
		}
	}

</script>
</body>
<script type="text/javascript" src="/resources/js/book/writemenu.js"></script>
</html>