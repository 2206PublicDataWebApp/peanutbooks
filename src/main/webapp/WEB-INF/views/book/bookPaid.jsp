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
 <link rel="stylesheet" href="/resources/css/bookmark-style.css">

</head>
<body>
	<jsp:include page="../header/header.jsp" />



    <main>
        <section class="container">
            <article id="bookmark-haed-area">
                <div id="bookmark-title">보관함</div>
                <div id="bookmark-haed-button">
                    <button onclick="location.href='/book/mybooks.do'">내 서재</button>
                    <button onclick="location.href='/book/myPaid.do'">소장</button>
                </div>
                <div id="bookmark-search-area" class="row">
                    <div class="col-md-3" id="gubun">
                    
                    
                    </div>
                    
                    
                    <div class="col-md-6"></div>
                    <div class="col-md-3 row" id="search">
                      <!-- 검색시작 -->
                      <form action="/book/myPaid.do" method="get">
                        <div class="wrap">
                            <div class="search">
                                <input type="text" class="searchTerm" name="searchValue" placeholder="제목을 입력하세요">
                                
                                <button type="submit" class="searchButton">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-search" viewBox="0 0 16 16">
                                        <path
                                            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                                    </svg>
                                </button>
                                
                            </div>
                        </div>

                       </form>
					<!-- 검색영역 종료 -->
                    </div>

                </div>
                <hr>
            </article>


            <article id="booklist-area">
                <div class="row" id="list-firstline">
                    <div class="col-md-9 d-md-block d-none">${TotalCount } 작품</div>
                    <div class="col-md-3">
                   <a href="/book/myPaid.do?step=all&searchValue=${searchValue }">
                    등록순</a> | 
                   
                    <a href="/book/myPaid.do?step=star&searchValue=${searchValue }">
                    별점순</a> | 
                    <a href="/book/myPaid.do?step=count&searchValue=${searchValue }">
                    조회순</a>
                    </div>

                </div>

                <div id="book-list" class="row">
                
                <!-- 책 한권 반복 시작 -->
                <c:forEach items="${pList }" var="pSeries">
                <c:if test="${pSeries.bookTitle != null}">
                
                    <div class="col-md-3 col-6" id="one-book-area"
                    onclick="window.open('/book/OridetailSeries.do?bookNo=${pSeries.bookNo }&seriesNo=${pSeries.seriesNo }')" >
                    
                        <div class="one-book-pic">
                            <img src="/resources/bookImg/${pSeries.picName }">
                        </div>
                        <div class="book-name text-truncate">
                            ${pSeries.bookTitle }
                        </div>
                        <div class="book-name text-truncate">
							${pSeries.seriesNo }. ${pSeries.title }
						</div>
                    </div>
                    
                 </c:if>  
				</c:forEach>



                </div>

            </article>


<!-- 페이징영역시작 -->
              <article id="page-area">
                <ul id="pagination">

               		<!-- 이전페이지 -->
               		<c:if test="${startNavi ne 1 && startNavi > 0}">
                    	<li><a href="/book/mybooks.do?category=${category }&step=${step }&searchValue=${searchValue}&category=${category }&page=${startNavi-1}">
                    		«</a></li>
                    </c:if>
                    
                    <!-- 페이지 숫자 -->
                    <c:forEach var="p" begin="${startNavi }" end="${endNavi }">

                    	<c:if test="${currentPage == p}">
                    		<li><a href="#" class="active">${p }</a></li>
                    	</c:if>
                    	<c:if test="${currentPage != p  }">
                    		<li><a href="/book/mybooks.do?category=${category }&step=${step }&searchValue=${searchValue}&category=${category }&page=${p}" >
                    			${p }</a></li>
                    	</c:if>
                    </c:forEach>
                    
                    
                   <!-- 다음페이지 -->
                   <c:if test="${endNavi < maxPage }">
                    <li><a href="/book/mybooks.do?category=${category }&step=${step }&searchValue=${searchValue}&category=${category }&page=${endNavi+1}">»</a></li>
                    </c:if>
                </ul>



            </article>


        </section>
    </main>






	<jsp:include page="../footer/footer.jsp" />
</body>
<script type="text/javascript" src="/resources/js/bookmark.js"></script>
</html>