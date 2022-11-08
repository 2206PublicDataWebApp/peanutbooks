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
                <div id="status-area" class="age-area col-4">
                    
                    <br>
                    

                </div>
                <div id="status-area" class="status-area col-8">
                    피넛 공주님 <br>
                    힘 : 100 / 근성 : 100 / 학력 : 100<br>
                    요리 : 100 / 음악 : 100 / 미술 : 100<br>
                    예절 : 100 / 스트레스 : 100
                </div>

            </article>

            <article id="center-area" class="row">
                <div id="sche-farm">
                    <img src="/resources/img/peanutcess/taue_girl2.gif" alt="" srcset="">


                </div>



            </article>

            
            <article id="down-area" onclick="nextText();">
                <div id="name-area">
                    피넛스터 :

                </div>
                <span id="text">
                   스케쥴 실행중...

                </span>

            </article>

        </section>
    </main>
        <jsp:include page="../footer/footer.jsp" />
</body>

 

<script type="text/javascript">
var nick = '${nickName}';
var name = '${pCess.name}'
var mon = Number(${mon});
thisTurn = ${thisTurn};
if(thisTurn == 1){
	var thisT = Number(4);	
}else if(thisTurn == 2){
	var thisT = Number(3);
}else if(thisTurn == 3){
	var thisT = Number(2);	
}else if(thisTurn == 4){
		var thisT = Number(1);	
}
var sche1 = '${sche1}';
var sche2 = '${sche2}';
var sche3 = '${sche3}';
var sche4 = '${sche4}';

var sche1Result = '${sche1Result}';
var sche2Result = '${sche2Result}';
var sche3Result = '${sche3Result}';
var sche4Result = '${sche4Result}';

var sche1Birth = ${sche1Birth};
var sche2Birth = ${sche2Birth};
var sche3Birth = ${sche3Birth};
var sche4Birth = ${sche4Birth};

var Trun = ${turn};

var age = ${age};
var monThis = mon- thisT;

</script>
<script src="/resources/js/peanutcess/peanutcess-job.js">

</script>
</html>