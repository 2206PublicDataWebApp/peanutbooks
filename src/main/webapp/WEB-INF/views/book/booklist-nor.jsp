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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/book/booklist.css">
      <link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
</head>
<body>
<jsp:include page="../header/header.jsp" />
    <main>
        <section>
            <article id="title-area">
                <div id="title"  class="container">도서</div>        
            </article>
            <article id="list1-area" class="container">
                <div class="list-title row">
                    <div class="col-10">
                        추리
                    </div>
                    <div class="col-2 next-icon" onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=detective'">
                        >>
                    </div>
                    <hr>
                </div>
                <div class="books-list-area row">
                
                <!-- 카테고리별 반복문 시작 -->
                <c:forEach items="${nDList }" var="book">
                    <div class="col-md-2 col-4 row"  onclick="location.href='/book/norBookInfo?bookNo=${book.bookNo}'">
                        <div class="book-img">
                            <img src="/resources/bookImg/${book.coverRename }">
                        </div>
                        <div class="book-title text-truncate">${book.bookTitle }</div>
                    </div>
                </c:forEach>
                

                </div>
                

            </article>

            <article id="list2-area" class="container">
                <div class="list-title row" >
                    <div class="col-10">
                        역사
                    </div>
                    <div class="col-2 next-icon" onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=history'">
                        >>
                    </div>
                    <hr>
                </div>
                <div class="books-list-area row">
                
                     <!-- 카테고리별 반복문 시작 -->
                <c:forEach items="${nHList }" var="book">
                    <div class="col-md-2 col-4 row" onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=history'">
                        <div class="book-img">
                            <img src="/resources/bookImg/${book.coverRename }">
                        </div>
                        <div class="book-title text-truncate">${book.bookTitle }</div>
                    </div>
                </c:forEach>
                   
                </div>
            </article>

            
            <article id="list3-area" class="container">
                <div class="list-title row"  onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=romance'">
                    <div class="col-10">
                        로맨스소설
                    </div>
                    <div class="col-2 next-icon" onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=romance'">
                        >>
                    </div>
                    <hr>
                </div>
                <div class="books-list-area row">
                
                     <!-- 카테고리별 반복문 시작 -->
                <c:forEach items="${nRList }" var="book">
                    <div class="col-md-2 col-4 row"  onclick="location.href='/book/norBookInfo?bookNo=${book.bookNo}'">
                        <div class="book-img">
                            <img src="/resources/bookImg/${book.coverRename }">
                        </div>
                        <div class="book-title text-truncate">${book.bookTitle }</div>
                    </div>
                </c:forEach>
                   
                </div>
                </article>

            <article id="list4-area" class="container">
                <div class="list-title row">
                    <div class="col-10">
                        동화
                    </div>
                    <div class="col-2 next-icon" onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=tale'">
                        >>
                    </div>
                    <hr>
                </div>
                <div class="books-list-area row">
                
                     <!-- 카테고리별 반복문 시작 -->
                <c:forEach items="${nTList }" var="book">
                    <div class="col-md-2 col-4 row"  onclick="location.href='/book/norBookInfo?bookNo=${book.bookNo}'">
                        <div class="book-img">
                            <img src="/resources/bookImg/${book.coverRename }">
                        </div>
                        <div class="book-title text-truncate">${book.bookTitle }</div>
                    </div>
                </c:forEach>
                    
                </div>
                </article>


                <article id="list5-area" class="container">
                    <div class="list-title row">
                        <div class="col-10">
                            시
                        </div>
                        <div class="col-2 next-icon" onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=poem'">
                            >>
                        </div>
                        <hr>
                    </div>
                    <div class="books-list-area row">
                    
                        <!-- 카테고리별 반복문 시작 -->
		                <c:forEach items="${nPList }" var="book">
		                    <div class="col-md-2 col-4 row"  onclick="location.href='/book/norBookInfo?bookNo=${book.bookNo}'">
		                        <div class="book-img">
		                            <img src="/resources/bookImg/${book.coverRename }">
		                        </div>
		                        <div class="book-title text-truncate">${book.bookTitle }</div>
		                    </div>
		                </c:forEach>
                        
    
                    </div>
                    </article>
                <article id="list6-area" class="container">
                    <div class="list-title row">
                        <div class="col-10">
                            일반소설
                        </div>
                        <div class="col-2 next-icon" onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=novel'">
                            >>
                        </div>
                        <hr>
                    </div>
                    <div class="books-list-area row">
                         <!-- 카테고리별 반복문 시작 -->
		                <c:forEach items="${nNList }" var="book">
		                    <div class="col-md-2 col-4 row"  onclick="location.href='/book/norBookInfo?bookNo=${book.bookNo}'"> 
		                        <div class="book-img">
		                            <img src="/resources/bookImg/${book.coverRename }">
		                        </div>
		                        <div class="book-title text-truncate">${book.bookTitle }</div>
		                    </div>
		                </c:forEach>
                        
    
                    </div>
                    </article>
                <article id="list7-area" class="container">
                    <div class="list-title row">
                        <div class="col-10">
                            기타
                        </div>
                        <div class="col-2 next-icon" onclick="location.href='/book/bookCatogoryList.do?bookCate=normal&category=other'">
                            >>
                        </div>
                        <hr>
                    </div>
                    <div class="books-list-area row">
                    
                        <!-- 카테고리별 반복문 시작 -->
		                <c:forEach items="${nOList }" var="book">
		                    <div class="col-md-2 col-4 row" onclick="location.href='/book/norBookInfo?bookNo=${book.bookNo}'">
		                        <div class="book-img">
		                            <img src="/resources/bookImg/${book.coverRename }">
		                        </div>
		                        <div class="book-title text-truncate">${book.bookTitle }</div>
		                    </div>
		                </c:forEach>
		                        
    
                    </div>
                    </article>
                
        </section>
    </main>
    
        <jsp:include page="../footer/footer.jsp" />
</body>
<script type="text/javascript" src="/resources/js/book/booklist-nor.js"></script>
</html>