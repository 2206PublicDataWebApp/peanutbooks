<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="/resources/css/peanutcess/peanutcess.css">
    <title>땅콩북스</title>

</head>

<body>
<jsp:include page="../header/header.jsp" />

    <main>
        <section class="container">
           <article id="up-area">

           </article>

            <article id="center-area">
                <span id="img-area">

                    <img src="/resources/img/peanutcess/gamelogo.png">
                </span>
                
            </article>
            <article id="down-area" onclick="location.href='/book/Peanutcess-1.do'">
                <span id="text">
                    ▷ 게임을 시작합니다.

                </span>
                
            </article>

        </section>
    </main>
    	<jsp:include page="../footer/footer.jsp" />
</body>

</html>