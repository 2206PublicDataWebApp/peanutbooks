<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

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
        <link rel="shortcut icon" href="/resources/img/icons8-book-32.png">    
         <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>땅콩북스</title>

</head>

<body>
<jsp:include page="../header/header.jsp" />
    <main>
    <input type="hidden" id="result" value=${result }>
        <section class="container">
           <article id="up-area">

           </article>

            <article id="center-area">
                <span id="char-img">

                    <img src="/resources/img/peanutcess/animal_character_hamster.png" id="peanutster">
                </span>
                
            </article>
            <article id="down-area" onclick="nextText()">
                <div id="name-area">
                    피넛스터 : 

                </div>
                <span id="text">
                    안녕하세요 ${nickName } 님!

                </span>
                
            </article>

        </section>
    </main>
    <jsp:include page="../footer/footer.jsp" />
</body>
<script type="text/javascript">
var mName = '${nickName }'
</script>
<script src="/resources/js/peanutcess/peanutcess-1.js"></script>
</html>