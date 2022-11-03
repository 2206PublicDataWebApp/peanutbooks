<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>땅콩북스: 이메일 인증</title>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
    <div class="row">
        <div id="login-img" class="col-lg-8"></div>
        <input type="hidden" name="memberId" value="${memberId}">
        <div id="login-area" class="col-lg-4">	
            <div><h3 id="login-hl">이메일 인증</h3></div>
            <div id="msg-area">
                <input id="authKey" type="text" name="authKey" placeholder="인증번호">
                <div><button id="join-btn" type="button">확인</button></div>
            </div>
        </div>
    </div>
    <script>
    	$("join-btn").on("click", function(){
    		var authNum = $("#authKey").val();
    		
    	});
    </script>
</body>
</html>