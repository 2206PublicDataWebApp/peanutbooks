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
	<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
</head>
<body>
	<form action="/member/authEmail.pb" method="post">
	    <div class="row">
	        <div id="login-img" class="col-lg-8"></div>
	        <input id="email-memberId" name="memberId" type="hidden" value="${memberId}">
	        <div id="login-area" class="col-lg-4">	
	            <div><h3 id="login-hl">이메일 인증</h3></div>
	            <div id="msg-area">
	                <input id="email-authKey" name="authKey" type="text" placeholder="인증번호">
	                <div><button id="join-btn" type="submit">확인</button></div>
	            </div>
	        </div>
	    </div>
    </form>
    <script>
    	$("#join-btn").on("click", function(){
    		var authKey = $("#email-authKey").val();
    		var memberId = $("#email-memberId").val();
    		if(authKey == ""){
    			alert("인증번호를 입력해 주세요.");
    			return false;
    		}else{
    			$.ajax({
    				url: "/member/checkAuthKey.pb",
    				data: {"authKey": authKey, "memberId": memberId},
    				type: "get",
    				success: function(result){
    					if(result != 0){
    						alert("회원가입이 완료되었습니다. 로그인해 주세요.");
    						return true;
    					}else{
    						alert("인증번호가 올바르지 않습니다. 다시 확인해 주세요.");
    						return false;
    					}
    				}
    			})
    		}
    	});
    </script>
</body>
</html>