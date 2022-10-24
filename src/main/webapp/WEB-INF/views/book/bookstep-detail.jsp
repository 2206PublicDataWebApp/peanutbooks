<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="/resources/css/book/bookstep-detail.css">
    <link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
    
        
</head>

<body>
    <main>
        <section id="">
            <article id="header-area">
                <div id="title-area">
                    ${bookTitle }
                </div>
            </article>
            <article id="main-text" class="container">

                <div id="step-title">
                    ${obSeries.seriesNo }. ${obSeries.title }
                </div>
                <div id="contents-area">
                    ${obSeries.contents }
                </div>

            </article>
            <article class="container" id="next-button-area">
                <img src="/resources/img/book/icons8-next-page-80.png" alt=""> 
            </article>

        </section>
    </main>

</body>
<script type="text/javascript">
document.querySelector('#header-area').style.backgroundImage="linear-gradient(to bottom,rgba(232, 232, 232, 0),rgba(255, 255, 255, 1)),url('/resources/bookImg/${obSeries.subPicRename}')"
</script>

</html>