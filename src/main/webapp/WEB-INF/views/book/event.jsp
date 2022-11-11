<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="/resources/css/book/event.css">
     <link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
    <title>땅콩북스</title>

</head>

<body>
<jsp:include page="../header/header.jsp" />
    <main>
        <div id="main">

            <div id="peanut-area">   
                <img src="/resources/img/book/peanut.png" alt="" id="peanutCheck">
            </div>
            <img src="/resources/img/book/eventImg.png" id="evenImg">
            <div id="eventText">출석도장을 모으면 포인트를 드려요</div>
    
            
        </div>
    </main>
    <jsp:include page="../footer/footer.jsp" />
</body>
<script type="text/javascript">
var check ='${check }';
var today =${today };
</script>
<script src="/resources/js/book/event.js"></script>
</html>