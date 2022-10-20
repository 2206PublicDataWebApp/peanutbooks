<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>땅콩북스</title>
    <link rel="stylesheet" href="/resources/css/first_header.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="/resources/img/icons8-book-32.png">

</head>

<body>
    <header>

        <div class="container">
            <div class="row" id="header-area">
                <div class="col-4 d-md-none d-block" id="menubar-area">
                    <img src="/resources/img//header/icons8-menu-60.png" id="menubar" alt="">
                </div>
                <div class="col-md-3 col-4">
                    <img src="/resources/img/logo/logo.png" id="logo" alt="" id="logo">


                </div>
                <div class="col-md-8 col-6 row d-md-block d-none" id="header-menu">
                    <div class="row" id="menu-text">
                        <div class="col-md-8 col-d-none"></div>
                        <div class="col-md-2 col-6 menu-font">도서문의</div>
                        <div class="col-md-2 col-6 menu-font"><a href="/member/joinView.pb">회원가입</a></div>

                    </div>


                </div>
                <div class="col-md-1 col-4" id="login-icon-area">
                    <a href="/member/loginView.pb"><img src="/resources/img/icons8-login-64.png" id="login-icon"></a>
                </div>
            </div>
        </div>
        <div id="toggle-menu">
            <div class="col-md-2 col-6 menu-font ps-4">도서문의</div>
            <div class="col-md-2 col-6 menu-font ps-4"><a href="/member/joinView.pb">회원가입</a></div>
        </div>


    </header>


    <main>
        <div id="main">
            <section class="container" id="section1">

                <div class="row" id="main-line">

                    <div class="col-md-4" id="main-text">
                        <div id="main-title">땅콩북스</div>
                        <div id="main-title2">
                            당신만의 인터넷 도서관 <br>
                            당신이 있는 그 곳이 당신만의 도서관이 됩니다<br>
                            당신이 바라는 당신만의 책, 당신이 만드는 당신만의 책, 당신이 읽고 싶은 책, 당신이 쓰고 싶은 책, 이 모든걸 땅콩북스가 후원합니다.
                        </div>

                        <div></div>
                    </div>
                    <div class="col-md-8">
                        <img src="/resources/img/main-book1.png" alt="">

                    </div>

                </div>
            </section>
        </div>

        <section class="container" id="section2">
            <div id="section2-title">
                땅콩북스에서 준비한 <br> 땅콩북스만의 오리지널 시리즈를 만나보세요

            </div>
        </section>
            <div class="book-cover-area" id="book-cover-area1">

            </div>

            <div class="book-cover-area" id="book-cover-area2">

            </div>

            <div class="book-cover-area" id="book-cover-area3">

            </div>

        

        <section id="faq">
            <div class="question-area container">
                <div id="faq-title">자주 묻는 질문</div>
                <div class="accordion rounded" id="accordionExample">
                    <div class="accordion-item rounded ">
                        <h2 class="accordion-header rounded" id="headingOne">
                            <button class="accordion-button rounded collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseOne" aria-controls="collapseOne">
                                정말로 책을 무료로 볼 수 있나요?
                            </button>
                        </h2>
                        <div id="collapseOne" class="accordion-collapse collapse rounded" aria-labelledby="headingOne">
                            <div class="accordion-body">
                                <b>땅콩북스의 도서는 대부분 무료입니다<br></b>

                                다만 땅콩북스에서 직접 도서를 작성하시는 작가님들의 작품의 경우 <br>
                                작가님의 의사에 따라 유/무료 여부가 결정됩니다<br>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item rounded">
                        <h2 class="accordion-header rounded" id="headingTwo">
                            <button class="accordion-button rounded collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                구독하면 어떤 혜택이 있나요?
                            </button>
                        </h2>
                        <div id="collapseTwo" class="accordion-collapse collapse rounded" aria-labelledby="headingTwo"
                            data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <strong>땅콩 북스의 도서를 유 무료 가리지 않고 전부 무료로 열람 할수 있습니다.</strong>
                                <br>땅콩북스의 모든 도서를 구독기간동안 자유롭게 열람하세요!
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item rounded">
                        <h2 class="accordion-header rounded" id="headingThree">
                            <button class="accordion-button collapsed rounded" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                외국어 책은 어떻게 읽나요?
                            </button>
                        </h2>
                        <div id="collapseThree" class="accordion-collapse collapse rounded"
                            aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                            <div class="accordion-body rounded">
                                <strong>번역기를 지원합니다</strong> <br>
                                땅콩북스는 저작권이 만료된 도서를 자유롭게 열람 하실수 있도록 지원하고 있습니다<br>
                                다만 번역에도 저작권이 있기때문에 원어로 책을 지원하고 있습니다.<br>
                                회원님들께서 편리하게 읽으실수 있도록 번역기를 지원해 독서를 돕고 있습니다
                            </div>
                        </div>
                    </div>
                </div>



            </div>


        </section>


    </main>
    <footer>
        <div class="container" id="footer-area">

            <div class="row">
                <div class="col-9" id="footer-title-text">
                    땅콩북스
                    <div id="footer-text-area">yu-jin, Yang &nbsp; | &nbsp; woo-young, Yang &nbsp; |&nbsp; eunjeong,
                        Shin &nbsp;|&nbsp; hana, Nam
                        <br>
                        땅콩북스의 모든 도서는 원저작자에게 저작권이 있으며 무단사용시 법적 책임을 질 수 있습니다. 땅콩북스의 도서를 허가없이 사용할 경우 생기는 일에 대해서는 일절 책임지지
                        않습니다.

                    </div>

                </div>
                <div class="col-3" id="footer-title-text1">
                    <img src="/resources/img/footer_image/icons8-facebook-64.png" alt="">
                    <img src="/resources/img/footer_image/icons8-instagram-64.png" alt="">
                    <img src="/resources/img/footer_image/icons8-tiktok-64.png" alt="">
                    <img src="/resources/img/footer_image/icons8-twitter-squared-64.png" alt="">



                </div>



            </div>
            <div id="text-area-2">
                © 2022 할수있쪼 peanut books <br> <a target="_blank" href="https://icons8.com/">icon by Icons8</a>
            </div>
        </div>
    </footer>

</body>
<script src="/resources/js/firstheaderJs.js"></script>
</html>