<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>도서목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/book/booklist-search.css">
    <link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
</head>

<body>
<jsp:include page="../header/header.jsp" />


    <main>
        <section>
            <article id="title-area">
                <div id="title" class="container">${bookCateStr } : ${cateStr}</div>
            </article>
			<article id="list-area" class="container">
			
			</article>

            <article id="list-area" class="container">
                <div class="list-title row">
                    <div class="col-md-10 col-6">

                    </div>
                    <div class="col-md-2 col-6 sort-icon">
                       <a href="/book/bookCatogoryList.do?bookCate=${bookCate }&category=${category}&step=all">
                        등록순 </a>
                        | 
                        <a href="/book/bookCatogoryList.do?bookCate=${bookCate }&category=${category}&step=count">
                        조회순 
                        </a>
                        | <a href="/book/bookCatogoryList.do?bookCate=${bookCate }&category=${category}&step=star">
                        별점순
                        </a>
                    </div>
                </div>

                <div class="books-list-area row">
                <!-- 책 반복문 -->
                	<c:forEach items="${bList }" var="book">
              
	                    <!-- 피넛오리지널인지 일반도서인지에 따라 연결되는 페이지가 달라짐 -->
	                    <div class="col-md-2 col-4 row OneBook"
	                    <c:if test="${bookCate == 'origin' }">
	                    onclick="location.href='/book/oriBookInfo?bookNo=${book.bookNo }'"
	                    </c:if>
	                    <c:if test="${bookCate == 'normal' }">
	                    onclick="location.href='/book/norBookInfo?bookNo=${book.bookNo }'"
	                    </c:if>
	                     title ="${book.bookTitle }"
	                    >
	                        <div class="book-img">
	                            <img src="/resources/bookImg/${book.coverRename }">
	                        </div>
	                        <div class="book-title text-truncate">${book.bookTitle }</div>
	                        <div class="book-info text-truncate">${book.bookInfo }</div>
	                        <c:if test="${book.writer == null}">
	                        <div class="book-info text-truncate">작가 : ${book.memberNickName }</div>
	                        </c:if>
	                        <c:if test="${book.writer != null}">
	                        <div class="book-info text-truncate">작가 : ${book.writer }</div>
	                        </c:if>
	                        
	                       	
	                    </div>
                   
                	</c:forEach><!-- 책 반복문 종료 -->

                </div>
            </article>

             <article id="page-area">
                <ul id="pagination">

               		<!-- 이전페이지 -->
               		<c:if test="${startNavi ne 1 && startNavi > 0}">
                    	<li><a href="/book/bookSearchList.do?category=${category }&step=${step }&bookCate=${bookCate}&page=${startNavi-1}">
                    		«</a></li>
                    </c:if>
                    
                    <!-- 페이지 숫자 -->
                    <c:forEach var="p" begin="${startNavi }" end="${endNavi }">

                    	<c:if test="${currentPage == p}">
                    		<li><a href="#" class="active">${p }</a></li>
                    	</c:if>
                    	<c:if test="${currentPage != p  }">
                    		<li><a href="/book/bookSearchList.do?category=${category }&step=${step }&bookCate=${bookCate}&page=${p}" >
                    			${p }</a></li>
                    	</c:if>
                    </c:forEach>
                    
                    
                   <!-- 다음페이지 -->
                   <c:if test="${endNavi < maxPage }">
                    <li><a href="/book/bookCatogoryList.do?category=${category }&step=${step }&bookCate=${bookCate}&page=${endNavi+1}">»</a></li>
                    </c:if>
                </ul>



            </article>

			<article id="back"> 
			<c:if test="${bookCate == 'origin'}">
			<button onclick="location.href='/book/bookOriList.do'">피넛오리지널 목록으로</button>
			</c:if>
			<c:if test="${bookCate == 'normal'}">
			<button onclick="location.href='/book/bookNorList.do'">도서 목록으로</button>
			</c:if>
			</article>
        </section>
    </main>
    
    
    <jsp:include page="../footer/footer.jsp" />
    
</body>
<script type="text/javascript" src="/resources/js/book/booklist-search.js"></script>

</html>