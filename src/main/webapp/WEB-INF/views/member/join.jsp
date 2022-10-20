<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>땅콩북스: 회원가입</title>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
    <form action="/member/join.pb" method="post">
    	<div class="row">
        	<div id="login-img" class="col-lg-8"></div> <!-- 배경 이미지 -->
	        <div id="join-area" class="col-lg-4">
	            <div id="pn-logo"><img src="../resources/img/logo/logo.png" alt="땅콩북스"></div>
	            <div id="input-area"> <!-- 회원가입 폼 -->
	                <label for="join-nickname">별명</label>
	                <div><input class="join-inputs" id="join-nickname" type="text" maxlength="20" name="mNickname" required></div>
	                <p class="guide nick ok">사용 가능한 별명입니다.</p>
	                <p class="guide nick error">이미 사용중인 별명입니다.</p>
	                <label for="join-id">아이디</label>
	                <div><input class="join-inputs" id="join-id" type="text" placeholder="영문, 숫자 5-11자" minlength="5" maxlength="11" name="memberId" required></div>
	                <p class="guide ok id">사용 가능한 아이디입니다.</p>
	                <p class="guide error id-error-1">이미 사용중인 아이디입니다.</p>
	                <p class="guide error id-error-2">아이디는 영문소문자, 숫자, 특수기호(_)만 사용 가능합니다.</p>
	                <label for="join-pw">비밀번호</label>
	                <div><input class="join-inputs" id="join-pw" type="password" placeholder="숫자, 영문 조합 최소 5자" minlength="5" maxlength="16" name="memberPw" required></div>
	                <div><input class="join-inputs" id="join-pw2" type="password" placeholder="비밀번호 재입력"></div>
	                <label for="join-email">이메일</label>
	                <div><input class="join-inputs" id="join-email" type="text" name="mEmail" required></div>
	                <div><button id="join-btn" type="submit">이메일 인증하고 가입하기</button></div>
	            </div>
	        </div>
    	</div>
    </form>
    <script>
    	// 별명 유효성 검사
    	$("#join-nickname").on("keyup", function(){
    		var mNickname = $("#join-nickname").val();
    		if(mNickname == ""){
    			$(".guide.nick.ok").hide();
    			$(".guide.nick.error").hide();
    		}else{
    			$.ajax({
    				url: "/member/checkNickname.pb",
    				data: {"mNickname" : mNickname},
    				type: "get",
    				success: function(result){
    					if(result != 0){
    						$(".guide.nick.error").show();
    						$(".guide.nick.ok").hide();
    					}else{
    						$(".guide.nick.ok").show();
    						$(".guide.nick.error").hide();
    					}
    				}
    			})
    		}
    	});
    	// 아이디 유효성 검사
    	$("#join-id").on("keyup", function(){
    		var memberId = $("#join-id").val();
    		var reg = /^[a-z|0-9|_]+$/;
    		// 입력된 전화번호를 검사할 정규표현식: 첫글자는 0, 그 뒤로 숫자가 1개 이상 2개 이하, 하이픈(-), 숫자 3개이상 4개 이하, 하이픈, 숫자 4개
    		if(memberId == ""){
    			$(".guide.ok.id").hide();
    			$(".guide.error.id-error-1").hide();
    			$(".guide.error.id-error-2").hide();
    		}else if(!reg.test(memberId)){
    			$(".guide.ok.id").hide();
    			$(".guide.error.id-error-1").hide();
    			$(".guide.error.id-error-2").show();
    		}else{
    			$.ajax({
    				url: "/member/checkId.pb",
    				data: {"memberId" : memberId},
    				type: "get",
    				success: function(result){
    					if(result != 0){
    						$(".guide.error.id-error-1").show();
    						$(".guide.ok.id").hide();
    						$(".guide.error.id-error-2").hide();
    					}else{
    						$(".guide.ok.id").show();
    						$(".guide.error.id-error-1").hide();
    						$(".guide.error.id-error-2").hide();
    					}
    				}
    			})
    		}
    	});
    </script>
</body>
</html>