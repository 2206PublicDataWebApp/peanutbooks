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
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
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
	                <div id="email-msg">이메일은 본인인증에 사용되며 추후 수정이 불가능합니다.</div>
	                <div class="guide ok email">사용 가능한 이메일입니다.</div>
	                <div class="guide error email-error-1">이미 사용중인 이메일입니다.</div>
	                <div class="guide error email-error-2">이메일 주소가 올바르지 않습니다.</div>
	                <div class="guide error email-error-3">이메일은 필수 정보입니다.</div>
               		<div class="join-btn"><button id="join-btn" type="submit">이메일 인증하고 가입하기</button></div>
               		<!-- <div class="inactive-btn"><button id="inactive-btn" type="button">이메일 인증하고 가입하기</button></div> -->
               		<div><hr id="join-hr"></div>
               		<div id="social-btns">
               			<div id="join-naver" onclick="location.href='${urlNaver}'"><img alt="네이버로 가입하기" src="../resources/img/member/naver-join-icon.png"></div>
               			<div id="join-kakao" onclick="location.href='${urlKakao}'"><img alt="카카오로 가입하기" src="../resources/img/member/kakao_login_medium_narrow.png"></div>
	            	</div>
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
    			$("#join-nickname").css("border", "solid 1px #FF577F");
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
    						$("#join-nickname").css("border", "solid 1px #FF577F");
    					}else{
    						$(".guide.ok.nick").show();
    						$(".guide.error.nick-error-1").hide();
    						$(".guide.error.nick-error-2").hide();
    						$("#join-nickname").css("border", "solid 1px #ccc");
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
    			$("#join-id").css("border", "solid 1px #FF577F");
    		}else if(memberId.length < 5){
    			$(".guide.ok.id").hide();
    			$(".guide.error.id-error-1").hide();
    			$(".guide.error.id-error-2").hide();
    			$(".guide.error.id-error-3").show();
    			$(".guide.error.id-error-4").hide();
    			$("#join-id").css("border", "solid 1px #FF577F");
    		}else if(memberId.length >= 5 && !regEx.test(memberId)){
    			$(".guide.ok.id").hide();
    			$(".guide.error.id-error-1").hide();
    			$(".guide.error.id-error-2").show();
    			$(".guide.error.id-error-3").hide();
    			$(".guide.error.id-error-4").hide();
    			$("#join-id").css("border", "solid 1px #FF577F");
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
    		    			$("#join-id").css("border", "solid 1px #FF577F");
    					}else{
    						$(".guide.ok.id").show();
    						$(".guide.error.id-error-1").hide();
    						$(".guide.error.id-error-2").hide();
    						$(".guide.error.id-error-3").hide();
    		    			$(".guide.error.id-error-4").hide();
    		    			$("#join-id").css("border", "solid 1px #ccc");
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
    			$("#join-pw").css("border", "solid 1px #FF577F");
    		}else if(memberPw.length < 5){
    			$(".guide.error.pw-error-1").show();
    			$(".guide.error.pw-error-2").hide();
    			$(".guide.error.pw-error-3").hide();
    			$("#join-pw").css("border", "solid 1px #FF577F");
    		}else if(memberPw.length >= 5 && !regEx.test(memberPw)){
    			$(".guide.error.pw-error-1").hide();
    			$(".guide.error.pw-error-2").show();
    			$(".guide.error.pw-error-3").hide();
    			$("#join-pw").css("border", "solid 1px #FF577F");
    		}else if(memberPw.length >= 5 && regEx.test(memberPw)){
    			$(".guide.error.pw-error-1").hide();
    			$(".guide.error.pw-error-2").hide();
    			$(".guide.error.pw-error-3").hide();
    			$("#join-pw").css("border", "solid 1px #ccc");
    		}
    	});
    	// 비밀번호 재확인
    	$("#join-pw2").on("keyup", function(){
    		var originPw = $("#join-pw").val();
    		var checkPw = $("#join-pw2").val();
    		if(checkPw == ""){
    			$(".guide.error.pw2-error-1").hide();
    			$(".guide.error.pw2-error-2").show();
    			$("#join-pw2").css("border", "solid 1px #FF577F");
    		}else if(originPw != checkPw){
    			$(".guide.error.pw2-error-1").show();
    			$(".guide.error.pw2-error-2").hide();
    			$("#join-pw2").css("border", "solid 1px #FF577F");
    		}else if(originPw == checkPw){
    			$(".guide.error.pw2-error-1").hide();
    			$(".guide.error.pw2-error-2").hide();
    			$("#join-pw2").css("border", "solid 1px #ccc");
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
    			$("#join-email").css("border", "solid 1px #FF577F");
    		}else if(!regEx.test(mEmail)){
    			$(".guide.ok.email").hide();
    			$(".guide.error.email-error-1").hide();
    			$(".guide.error.email-error-2").show();
    			$(".guide.error.email-error-3").hide();
    			$("#join-email").css("border", "solid 1px #FF577F");
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
    						$("#join-email").css("border", "solid 1px #FF577F");
    					}else{
    						$(".guide.ok.email").show();
    						$(".guide.error.email-error-1").hide();
    						$(".guide.error.email-error-2").hide();
    						$(".guide.error.email-error-3").hide();
    						$("#join-email").css("border", "solid 1px #ccc");
    					}
    				}
    			})
    		}
    	});

    	// 모든 데이터가 정상 입력 되기 전까지 가입 버튼 비활성화
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
    	
    </script>
</body>
</html>