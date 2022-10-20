<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>땅콩북스: 로그인</title>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
	<form action="/member/login.pb" method="post">
		<div class="row">
	        <div id="login-img" class="col-lg-8"></div> <!-- 배경 이미지 -->
	        <div id="login-area" class="col-lg-4"> <!-- 로그인 폼 -->
	            <div><h3 id="login-hl">로그인</h3></div>
	            <div id="login-id"><input class="login-inputs" type="text" placeholder="아이디" name="memberId"></div>
	            <div id="login-pw"><input class="login-inputs" type="password" placeholder="비밀번호" name="memberPw"></div>
	            <div><button id="login-btn" type="submit">로그인</button></div>
	            <div id="login-others">
	                <a href="#">아이디 찾기</a> | <a href="#"> 비밀번호 재설정 </a> | <a href="#">회원가입</a>
	            </div>
	            <div><hr id="login-hr"></div>
	            <div id="login-social">
	                <img id="naver-icon" src="../resources/img/member/naver_icon.png" alt="네이버">
	                <img src="../resources/img/member/kakao_icon.png" alt="카카오">
	            </div>
	        </div>
	    </div>
    </form>
</body>
</html>