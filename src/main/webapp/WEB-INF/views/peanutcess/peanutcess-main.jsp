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
        <link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
    <title>땅콩북스</title>

</head>

<body>
<jsp:include page="../header/header.jsp" />
    <main>
        <section class="container">
           <article id="up-area" class="row">
            <div id="status-area" class="col-4">
            	<div id="age-area">
                	${age }살 ${mon}월
            	</div>
                <div id="money-area">
                	소지금 : <br> ${pCess.money } 피넛
                </div>

            </div>
            <div id="status-area" class="col-8 status-area">
                ${pCess.name } 공주님 <br>
                힘 : <span id="statusPower">${pCess.power }</span> /  근성 : <span id="statusstrong">${pCess.strong }</span> / 학력 : <span id="statusstudy">${pCess.study }</span><br>
                요리 : <span id="statuscook">${pCess.cook }</span> / 음악 : <span id="statusmusic">${pCess.music }</span> / 미술 : <span id="statusart">${pCess.art }</span><br>
                예절 : <span id="statusmanner">${pCess.manner }</span> / 스트레스 : <span id="statusstress">${pCess.stress }</span>
            </div>

           </article>

            <article id="center-area" class="row">
                <div class="col-6" id="sche-div">
                    <div id="sche-area1" class="sche-area">
                        <ul>
                            <li id="sche-plus"  class="hover">스케쥴을 작성한다.</li>
                            <li id="gotoShop"  class="hover">상점에 간다</li>
                            <li id="viewStatus"  class="hover">스테이터스보기</li>
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
</body>

 



<script type="text/javascript">
var nick = '${nickName}';
var name = '${pCess.name}'
var mon = ${mon};
var age = ${age};
var money =${pCess.money}

</script>
<script src="/resources/js/peanutcess/peanutcess-main.js"></script>
</html>