<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- 반응형 화면 -->
	<title>땅콩북스: 로그인</title>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
	<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
	<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.min.js" charset="utf-8"></script>
</head>
<body>
	<form action="/member/login.pb" method="post" id="loginForm">
		<div class="row">
	        <div id="login-img" class="col-lg-8"></div> <!-- 배경 이미지 -->
	        <div id="login-area" class="col-lg-4"> <!-- 로그인 폼 -->
	            <div><h3 id="login-hl">로그인</h3></div>
	            <div id="login-id"><input id="login-id-data" class="login-inputs" type="text" placeholder="아이디" name="memberId"></div>
	            <div id="login-pw"><input id="login-pw-data" class="login-inputs" type="password" placeholder="비밀번호" name="memberPw"></div>
	            <div><button id="login-btn" type="button">로그인</button></div>
	            <div id="login-others">
	                <a href="/member/forgotId.pb">아이디 찾기</a> | <a href="/member/forgotPw.pb"> 비밀번호 재설정 </a> | <a href="/member/joinView.pb">회원가입</a>
	            </div>
	            <div><hr id="login-hr"></div>
	            <div id="login-social">
	                <img onclick="location.href='${urlNaver}'" class="naver-icon" src="../resources/img/member/naver_icon.png" alt="네이버">
	                <img onclick="location.href='${urlKakao}'" src="../resources/img/member/kakao_icon.png" alt="카카오">
	            </div>
	        </div>
	    </div>
    </form>
    <script>
    	
    	// 로그인 데이터 유효성 검사
    	$("#login-btn").on("click", function(){
    		var memberId = $("#login-id-data").val();
    		var memberPw = $("#login-pw-data").val();
    		$.ajax({
    			url: "/member/loginCheck.pb",
    			data: {"memberId": memberId,
    				   "memberPw": memberPw},
    			type: "post",
    			success: function(result){
    				if(result == "0"){
    					alert("아이디 또는 비밀번호가 일치하지 않습니다.");
    					return false;
    				}else{
    					$("#loginForm").submit();
    				}
    			}
    		});
    	});
    </script>
</body>
</html>