<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                <div id="title" class="container">검색어/카테고리</div>
            </article>


            <article id="list-area" class="container">
                <div class="list-title row">
                    <div class="col-md-10 col-6">

                    </div>
                    <div class="col-md-2 col-6 sort-icon">
                        등록순 | 조회순 | 별점순
                    </div>
                </div>

                <div class="books-list-area row">
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/christmas-g4fdade62f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/landscape-g2a33940ce_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/sailboat-g9d3a88421_1920.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>

                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/christmas-g4fdade62f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/landscape-g2a33940ce_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/sailboat-g9d3a88421_1920.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>

                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/christmas-g4fdade62f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/landscape-g2a33940ce_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/sailboat-g9d3a88421_1920.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>
                    <div class="col-md-2 col-4 row">
                        <div class="book-img">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-title text-truncate">긴제목 제목제목제목제ㅗㄱ</div>
                        <div class="book-info text-truncate">설명이 아주아주아주아중자아주지기</div>
                    </div>

                </div>
            </article>

            <article id="page-area">
                <ul id="pagination">
                    <li><a href="#">«</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#" class="active">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">6</a></li>
                    <li><a href="#">7</a></li>
                    <li><a href="#">»</a></li>
                </ul>



            </article>


        </section>
    </main>
    
    
    <jsp:include page="../footer/footer.jsp" />
    
</body>
<script type="text/javascript" src="/resources/js/book/booklist-search.js"></script>

</html>