<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>땅콩북스: 아이디 찾기</title>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
   	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
</head>
<body>
    <div class="row">
        <div id="login-img" class="col-lg-8"></div>
        <div id="idresult-area" class="col-lg-4">
            <h3 id="findId-hl">아이디 찾기 결과</h3>
            <p id="idresult-msg">회원님의 이메일로 가입한 아이디가 있습니다.</p>
            <div id="idresult"><p id="memberIdResult"></p></div>
            <p>비밀번호가 기억나지 않으세요? <a href="/member/forgotPw.pb">비밀번호 재설정</a></p>
            <div><button id="login-btn" type="button" onClick="location.href='/member/loginView.pb'">로그인</button></div>
        </div>
    </div>
	<script>
		var url = new URL(window.location.href);
		var memberId = url.searchParams.get("memberId");
		document.getElementById("memberIdResult").innerHTML = "[&nbsp;&nbsp;&nbsp;&nbsp;"+memberId+"&nbsp;&nbsp;&nbsp;&nbsp;]";
	</script>
</body>
</html>