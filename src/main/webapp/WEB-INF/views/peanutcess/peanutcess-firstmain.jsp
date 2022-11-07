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
        <link rel="stylesheet" href="/resources/css/peanutcess/peanutcessmain.css">
        <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>땅콩북스</title>

</head>

<body>
<jsp:include page="../header/header.jsp" />
    <main>
        <section class="container">
           <article id="up-area" class="row">
            <div id="status-area" class="col-4">
                 ${age }살 ${mon}월
                <br>
                소지금 : ${pCess.money } 피넛

            </div>
            <div id="status-area" class="col-8 status-area">
                ${pCess.name } 공주님 <br>
                힘 : ${pCess.power } /  근성 : ${pCess.strong } / 학력 : ${pCess.study }<br>
                요리 : ${pCess.cook } / 음악 : ${pCess.music } / 미술 : ${pCess.art }<br>
                예절 : ${pCess.manner } / 스트레스 : ${pCess.stress }
            </div>

           </article>

            <article id="center-area" class="row">
                <div class="col-6" id="sche-div">
                    <div id="sche-area1" class="sche-area">
                        <ul>
                            <li id="sche-plus">스케쥴을 작성한다.</li>
                            <li id="gotoShop">상점에 간다</li>
                            <li id="viewStatus">스테이터스보기</li>
                        </ul>

                    </div>
                    <div id="sche-area2" class="sche-area">

                    </div>

                </div>
                <div class="col-6"> 
                    <span id="char-img">

                    <img src="/resources/img/peanutcess/royal_princess.png" id="princessImg">
                </span></div>
                
               
                
            </article>
            <article id="down-area">
                <div id="name-area">
                    피넛스터 : 

                </div>
                <span id="text">
                    무엇을 할까요?

                </span>
                
            </article>

        </section>
    </main>
        <jsp:include page="../footer/footer.jsp" />
       <c:if test="${bList != null}">
       	<c:forEach items="${bList}" var="book">
       		<input type="hidden" class="booktitle" value="${book.bookTitle }">
       		<input type="hidden" class="booktag" value="${book.hashTag3 }">
       		
       		
       	</c:forEach>
       		<!--  bList반환시에만 사용-->
       		<script src="/resources/js/peanutcess/peanutcess-main-first.js"></script>
       </c:if>
</body>

 

<script src="/resources/js/peanutcess/peanutcess-main.js">

</script>
<script type="text/javascript">
var nick = '${nickName}';
var name = '${pCess.name}'

</script>
</html>