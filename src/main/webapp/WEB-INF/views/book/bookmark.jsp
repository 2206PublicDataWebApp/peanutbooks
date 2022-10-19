<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
 <link rel="stylesheet" href="/resources/css/bookmark-style.css">

</head>
<body>
	<jsp:include page="../header/header.jsp" />



    <main>
        <section class="container">
            <article id="bookmark-haed-area">
                <div id="bookmark-title">보관함</div>
                <div id="bookmark-haed-button">
                    <button>내 서재</button>
                    <button>소장</button>
                </div>
                <div id="bookmark-search-area" class="row">
                    <div class="col-md-3" id="gubun">전체 | 일반도서 | 피넛 오리지널</div>
                    <div class="col-md-6"></div>
                    <div class="col-md-3 row" id="search">
                        <div class="wrap">
                            <div class="search">
                                <input type="text" class="searchTerm" placeholder="제목을 입력하세요">
                                <button type="submit" class="searchButton">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-search" viewBox="0 0 16 16">
                                        <path
                                            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                                    </svg>
                                </button>
                            </div>
                        </div>


                    </div>

                </div>
                <hr>
            </article>


            <article id="booklist-area">
                <div class="row" id="list-firstline">
                    <div class="col-md-9 d-md-block d-none">21 작품</div>
                    <div class="col-md-3">등록순 | 별점순 | 조회순</div>

                </div>

                <div id="book-list" class="row">
                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>
                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>

                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>

                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>


                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>

                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>

                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/teacup-g35f438ceb_1280.png">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>

                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/teacup-g35f438ceb_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>


                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>

                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>

                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/christmas-g4fdade62f_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
                    </div>

                    <div class="col-md-3 col-6" id="one-book-area">
                        <div class="one-book-pic">
                            <img src="/resources/img/main/woman-g803af235f_1280.jpg">
                        </div>
                        <div class="book-name">
                            제목

                        </div>
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
<script type="text/javascript" src="/resources/js/bookmark.js"></script>
</html>