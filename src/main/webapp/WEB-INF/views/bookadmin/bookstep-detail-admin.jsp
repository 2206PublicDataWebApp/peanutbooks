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
    <link rel="stylesheet" href="/resources/css/book/bookstep-detail.css">
    <link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    
        
</head>

<body>
    <main>
        <section id="One">
            <article id="header-area">
                <div id="title-area">
                    ${bookTitle }
                </div>
            </article>
            <article id="main-text" class="container">

                <div id="step-title">
                    ${nbSeries.seriesNo }. ${nbSeries.title }

                </div>
                <div id="contents-area">
                    ${nbSeries.contents }
                </div>

            </article>
           

        </section>
        
         <section id="next-button-area">
         
            	<img src="/resources/img/book/icons8-open-book-64.png" id="book-button" alt=""onclick="location.href='/book/bookStep.do?bookNo=${nbSeries.bookNo}&category=normal'">
            	<img src="/resources/img/book/icons8-twoleft.png" id="prev-button" alt="">
                <img src="/resources/img/book/icons8-twoTop.png" id="top-button" alt="" onclick=" window.scrollTo(0,0);"> 
                <img src="/resources/img/book/icons8-tworight.png" id="next-button" alt=""> 
                 
            </section>
        
         
         <section id="two" style="display:none;">
            <article id="header-area" class="header-area2">
                <div id="title-area" class="title-area2">
                    
                </div>
            </article>
            <article id="main-text" class="container main-text2">

                <div id="step-title" class="step-title2">
                    

                </div>
                <div id="contents-area" class="contents-area2">
                    
                </div>

            </article>
            <article class="container" id="next-button-area">
                 
            </article>

        </section>
       
    </main>

</body>
<script type="text/javascript">
document.querySelector('#header-area').style.backgroundImage="linear-gradient(to bottom,rgba(232, 232, 232, 0),rgba(255, 255, 255, 1)),url('/resources/bookImg/${nbSeries.subpicRename}')"
var seriesNo = ${nbSeries.seriesNo};
var bookNo = ${nbSeries.bookNo};

</script>

<script src="/resources/js/book/bookstep-detail.js"></script>

</html>