<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
	                <p class="guide ok nick">사용 가능한 별명입니다.</p>
	                <p class="guide error nick-error-1">이미 사용중인 별명입니다.</p>
	                <p class="guide error nick-error-2">별명은 필수 정보입니다.</p>
	                <label for="join-id">아이디</label>
	                <div><input class="join-inputs" id="join-id" type="text" placeholder="영문, 숫자 5-11자" minlength="5" maxlength="11" name="memberId" required></div>
	                <p class="guide ok id">사용 가능한 아이디입니다.</p>
	                <p class="guide error id-error-1">이미 사용중인 아이디입니다.</p>
	                <p class="guide error id-error-2">아이디는 영문소문자, 숫자, 특수기호(_)만 사용 가능합니다.</p>
	                <p class="guide error id-error-3">아이디는 5자 이상이어야 합니다.</p>
	                <p class="guide error id-error-4">아이디는 필수 정보입니다.</p>
	                <label for="join-pw">비밀번호</label>
	                <div><input class="join-inputs" id="join-pw" type="password" placeholder="숫자, 영문 조합 최소 5자" minlength="5" maxlength="16" name="memberPw" required></div>
	                <p class="guide error pw-error-1">5~16자 이내로 입력해 주십시오.</p>
	                <p class="guide error pw-error-2">숫자, 영문 대소문자 조합으로 입력해 주십시오.</p>
	                <p class="guide error pw-error-3">비밀번호는 필수 정보입니다.</p>
	                <div><input class="join-inputs" id="join-pw2" type="password" placeholder="비밀번호 재입력"></div>
	                <p class="guide error pw2-error-1">비밀번호가 일치하지 않습니다.</p>
	                <p class="guide error pw2-error-2">비밀번호 재확인은 필수 정보입니다.</p>
	                <label for="join-email">이메일</label>
	                <div><input class="join-inputs" id="join-email" type="text" name="mEmail" required></div>
	                <div class="guide ok email">사용 가능한 이메일입니다.</div>
	                <div class="guide error email-error-1">이미 사용중인 이메일입니다.</div>
	                <div class="guide error email-error-2">이메일 주소가 올바르지 않습니다.</div>
	                <div class="guide error email-error-3">이메일은 필수 정보입니다.</div>
               		<div class="join-btn"><button id="join-btn" type="submit">이메일 인증하고 가입하기</button></div>
               		<!-- <div class="inactive-btn"><button id="inactive-btn" type="button">이메일 인증하고 가입하기</button></div> -->
	            </div>
	        </div>
    	</div>
    </form>
    <script>
    	// 별명 유효성 검사
    	$("#join-nickname").on("keyup", function(){
    		var mNickname = $("#join-nickname").val();
    		if(mNickname == ""){
    			$(".guide.ok.nick").hide();
    			$(".guide.error.nick-error-1").hide();
    			$(".guide.error.nick-error-2").show();
    		}else{
    			$.ajax({
    				url: "/member/checkNickname.pb",
    				data: {"mNickname" : mNickname},
    				type: "get",
    				success: function(result){
    					if(result != 0){
    						$(".guide.ok.nick").hide();
    						$(".guide.error.nick-error-1").show();
    						$(".guide.error.nick-error-2").hide();
    					}else{
    						$(".guide.ok.nick").show();
    						$(".guide.error.nick-error-1").hide();
    						$(".guide.error.nick-error-2").hide();
    					}
    				}
    			})
    		}
    	});
    	// 아이디 유효성 검사
    	$("#join-id").on("keyup", function(){
    		var memberId = $("#join-id").val();
    		var regEx = /^[a-z|0-9|_]+$/; // 아이디로 영문소문자, 숫자, _만 입력 가능
    		if(memberId == ""){
    			$(".guide.ok.id").hide();
    			$(".guide.error.id-error-1").hide();
    			$(".guide.error.id-error-2").hide();
    			$(".guide.error.id-error-3").hide();
    			$(".guide.error.id-error-4").show();
    		}else if(memberId.length < 5){
    			$(".guide.ok.id").hide();
    			$(".guide.error.id-error-1").hide();
    			$(".guide.error.id-error-2").hide();
    			$(".guide.error.id-error-3").show();
    			$(".guide.error.id-error-4").hide();
    		}else if(memberId.length >= 5 && !regEx.test(memberId)){
    			$(".guide.ok.id").hide();
    			$(".guide.error.id-error-1").hide();
    			$(".guide.error.id-error-2").show();
    			$(".guide.error.id-error-3").hide();
    			$(".guide.error.id-error-4").hide();
    		}else{
    			$.ajax({
    				url: "/member/checkId.pb",
    				data: {"memberId" : memberId},
    				type: "get",
    				success: function(result){
    					if(result != 0){
    						$(".guide.ok.id").hide();
    						$(".guide.error.id-error-1").show();
    						$(".guide.error.id-error-2").hide();
    						$(".guide.error.id-error-3").hide();
    		    			$(".guide.error.id-error-4").hide();
    					}else{
    						$(".guide.ok.id").show();
    						$(".guide.error.id-error-1").hide();
    						$(".guide.error.id-error-2").hide();
    						$(".guide.error.id-error-3").hide();
    		    			$(".guide.error.id-error-4").hide();
    					}
    				}
    			})
    		}
    	});
    	// 비밀번호 유효성 검사
    	$("#join-pw").on("keyup", function(){
    		var memberPw = $("#join-pw").val();
    		var regEx = /(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]/;
    		if(memberPw == ""){
    			$(".guide.error.pw-error-1").hide();
    			$(".guide.error.pw-error-2").hide();
    			$(".guide.error.pw-error-3").show();
    		}else if(memberPw.length < 5){
    			$(".guide.error.pw-error-1").show();
    			$(".guide.error.pw-error-2").hide();
    			$(".guide.error.pw-error-3").hide();
    		}else if(memberPw.length >= 5 && !regEx.test(memberPw)){
    			$(".guide.error.pw-error-1").hide();
    			$(".guide.error.pw-error-2").show();
    			$(".guide.error.pw-error-3").hide();
    		}else if(memberPw.length >= 5 && regEx.test(memberPw)){
    			$(".guide.error.pw-error-1").hide();
    			$(".guide.error.pw-error-2").hide();
    			$(".guide.error.pw-error-3").hide();
    		}
    	});
    	// 비밀번호 재확인
    	$("#join-pw2").on("keyup", function(){
    		var originPw = $("#join-pw").val();
    		var checkPw = $("#join-pw2").val();
    		if(checkPw == ""){
    			$(".guide.error.pw2-error-1").hide();
    			$(".guide.error.pw2-error-2").show();
    		}else if(originPw != checkPw){
    			$(".guide.error.pw2-error-1").show();
    			$(".guide.error.pw2-error-2").hide();
    		}else if(originPw == checkPw){
    			$(".guide.error.pw2-error-1").hide();
    			$(".guide.error.pw2-error-2").hide();
    		}
    	});
    	// 이메일 유효성 검사
    	$("#join-email").on("keyup", function(){
    		var mEmail = $("#join-email").val();
    		var regEx = /^[a-zA-Z0-9]([.]?[a-zA-Z0-9])*@+[a-z]{1,}[.]+[a-z]{2,4}$/; // 이메일 형식
    		if(mEmail == ""){
    			$(".guide.ok.email").hide();
    			$(".guide.error.email-error-1").hide();
    			$(".guide.error.email-error-2").hide();
    			$(".guide.error.email-error-3").show();
    		}else if(!regEx.test(mEmail)){
    			$(".guide.ok.email").hide();
    			$(".guide.error.email-error-1").hide();
    			$(".guide.error.email-error-2").show();
    			$(".guide.error.email-error-3").hide();
    		}else{
    			$.ajax({
    				url: "/member/checkEmail.pb",
    				data: {"mEmail" : mEmail},
    				type: "get",
    				success: function(result){
    					if(result != 0){
    						$(".guide.ok.email").hide();
    						$(".guide.error.email-error-1").show();
    						$(".guide.error.email-error-2").hide();
    						$(".guide.error.email-error-3").hide();
    					}else{
    						$(".guide.ok.email").show();
    						$(".guide.error.email-error-1").hide();
    						$(".guide.error.email-error-2").hide();
    						$(".guide.error.email-error-3").hide();
    					}
    				}
    			})
    		}
    	});
    	
    	// submit check
    	$("form").on("submit", function(){
    	});

/*      	$("input").on("keyup", function(){
     		var mNickname = $("#join-nickname").val();
      		var memberId = $("#join-id").val();
     		var memberPw = $("#join-pw").val();
     		var memberPWChk = $("#join-pw2").val();
     		var mEmail = $("#join-email").val();
    		if($(".error").css("display") == "block"){
    			$("#join-btn").attr("disabled");
    		}else{
    			$("#join-btn").removeAttr("disabled");
    		}
    	}); */
    	
    	/* 이메일 인증 */
    </script>
</body>
</html>