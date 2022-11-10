<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>땅콩북스: 비밀번호 재설정</title>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</head>
<body>
	<form action="/member/resetPw.pb" method="post" Id="resetPw">
		<input type="hidden" name="memberId" value="${memberId}">
	    <div class="row">
	        <div id="login-img" class="col-lg-8"></div>
	        <div id="newpw-area" class="col-lg-4">
	            <div><h3 id="findId-hl">비밀번호 재설정</h3></div>
	            <div id="newpw-input">
	                <label for="join-pw">비밀번호</label>
	                <div><input class="newpw-inputs" id="join-pw" type="password" placeholder="숫자, 영문 조합 최소 5자" name="memberPw" maxlength="16"></div>
    	            <p class="guide error pw-error-1">5~16자 이내로 입력해 주십시오.</p>
	                <p class="guide error pw-error-2">숫자, 영문 대소문자 조합으로 입력해 주십시오.</p>
	                <p class="guide error pw-error-3">비밀번호를 입력해 주세요.</p>
	                <div><input class="newpw-inputs" id="join-pw2" type="password" placeholder="비밀번호 재입력" maxlength="16"></div>
	                <p class="guide error pw2-error-1">비밀번호가 일치하지 않습니다.</p>
	                <p class="guide error pw2-error-2">비밀번호를 입력해 주세요.</p>
	                <div><button id="submit-btn" type="button">확인</button></div>
	            </div>
	        </div>
	    </div>
    </form>
    <script>
	 	// 비밀번호 유효성 검사
		$("#join-pw").on("keyup", function(){
			var memberPw = $("#join-pw").val();	// 새로운 비밀번호 값
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
    		var memberPw = $("#join-pw").val();	// 새로운 비밀번호 값
    		var checkPw = $("#join-pw2").val(); // 확인용 비밀번호 값
    		if(checkPw == ""){
    			$(".guide.error.pw2-error-1").hide();
    			$(".guide.error.pw2-error-2").show();
    			$("#join-pw2").css("border", "solid 1px #FF577F");
    		}else if(memberPw != checkPw){
    			$(".guide.error.pw2-error-1").show();
    			$(".guide.error.pw2-error-2").hide();
    			$("#join-pw2").css("border", "solid 1px #FF577F");
    		}else if(memberPw == checkPw){
    			$(".guide.error.pw2-error-1").hide();
    			$(".guide.error.pw2-error-2").hide();
    			$("#join-pw2").css("border", "solid 1px #ccc");
    		}
    	});
    	// 비밀번호 재설정
    	$("#submit-btn").on("click", function(){
    		var memberPw = $("#join-pw").val();	// 새로운 비밀번호 값
    		var checkPw = $("#join-pw2").val(); // 확인용 비밀번호 값
    		var regEx = /(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]/;
    		if(memberPw == "" || checkPw == "" || checkPw != memberPw || memberPw.length < 5 || !regEx.test(memberPw)){
    			alert("비밀번호를 다시 확인해 주세요.");
    			return false;
    		}else {
    			alert("비밀번호 재설정이 완료되었습니다.");
    			$("#resetPw").submit();
    		}
    	});
    </script>
</body>
</html>