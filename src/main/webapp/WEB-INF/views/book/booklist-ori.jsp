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
                <div id="title"  class="container">피넛 오리지널 시리즈</div>        
            </article>
            <article id="list1-area" class="container">
                <div class="list-title row">
                    <div class="col-10">
                        소설
                    </div>
                    <div class="col-2 next-icon">
                        >>
                    </div>
                    <hr>
                </div>
                <div class="books-list-area row">
                
                <!-- 소설 반복 문 시작 -->
                <c:forEach items="${oNList }" var="novel">
                    <div class="col-md-2 col-4 row" onclick="location.href='/book/oriBookInfo?bookNo=${novel.bookNo}'">
                        <div class="book-img">
                            <img src="/resources/bookImg/${novel.coverRename }">
                        </div>
                        <div class="book-title text-truncate">${novel.bookTitle }</div>
                    </div>
                   

                </c:forEach>
                </div>
                

            </article>

            <article id="list2-area" class="container">
                <div class="list-title row">
                    <div class="col-10">
                        에세이
                    </div>
                    <div class="col-2 next-icon">
                        >>
                    </div>
                    <hr>
                </div>
                <div class="books-list-area row">
                    <!-- 에세이 반복 문 시작 -->
                <c:forEach items="${oEList }" var="essay">
                    <div class="col-md-2 col-4 row" onclick="location.href='/book/oriBookInfo?bookNo=${essay.bookNo}'">
                        <div class="book-img">
                            <img src="/resources/bookImg/${essay.coverRename }">
                        </div>
                        <div class="book-title text-truncate">${essay.bookTitle }</div>
                    </div>
                   </c:forEach>

                </div>
            </article>

            
            <article id="list3-area" class="container">
                <div class="list-title row">
                    <div class="col-10">
                        동화
                    </div>
                    <div class="col-2 next-icon">
                        >>
                    </div>
                    <hr>
                </div>
                <div class="books-list-area row">
                          <!-- 동화 반복 문 시작 -->
                <c:forEach items="${oTList }" var="tail">
                    <div class="col-md-2 col-4 row" onclick="location.href='/book/oriBookInfo?bookNo=${tail.bookNo}'">
                        <div class="book-img">
                            <img src="/resources/bookImg/${tail.coverRename }">
                        </div>
                        <div class="book-title text-truncate">${tail.bookTitle }</div>
                    </div>
                   </c:forEach>
                   
                   
                </div>
                </article>

            <article id="list2-area" class="container">
                <div class="list-title row">
                    <div class="col-10">
                        시
                    </div>
                    <div class="col-2 next-icon">
                        >>
                    </div>
                    <hr>
                </div>
                <div class="books-list-area row">
                             <!-- 시 반복 문 시작 -->
                <c:forEach items="${oPList }" var="poem">
                    <div class="col-md-2 col-4 row" onclick="location.href='/book/oriBookInfo?bookNo=${poem.bookNo}'">
                        <div class="book-img">
                            <img src="/resources/bookImg/${poem.coverRename }">
                        </div>
                        <div class="book-title text-truncate">${poem.bookTitle }</div>
                    </div>
                   </c:forEach>

                </div>
                </article>
                
        </section>
    </main>
    <jsp:include page="../footer/footer.jsp" />
</body>
<script type="text/javascript" src="/resources/js/book/booklist-ori.js"></script>
</html>