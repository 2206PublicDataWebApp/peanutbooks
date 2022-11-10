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
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</head>
<body>
	<form action="/member/sendMemberId" method="post" id="sendId">
		<input id="findId-memberId" type="hidden" name="memberId">
		<input id="forgotId-authKey" type="hidden" name="authKey">
	    <div class="row">
	        <div id="login-img" class="col-lg-8"></div>
	        <div id="findId-area" class="col-lg-4">
	            <div><h3 id="findId-hl">비밀번호 재설정</h3></div>
	            <div id="input-area">
	                <label for="findId-email">이메일</label>
	                <div id="findId-email"><input id="email-input" type="text"><button type="button" id="email-btn">인증요청</button></div>
	                <div id="findId-authKey"><input id="authKey-input" type="text" placeholder="인증번호"></div>
	                <div><button id="findId-btn" type="button">비밀번호 재설정</button></div>
	            </div>
	        </div>
	    </div>
    </form>
    <script>
    	$("#email-btn").on("click", function(){
    		var mEmail = $("#email-input").val();
    		if(mEmail == ""){
    			alert("이메일을 입력해 주세요.");
    			$("#email-input").focus();
    			return false;
    		}else{
	    		$.ajax({
	    			url: "/member/checkMemberByEmail",
	    			data: {"mEmail": mEmail},
	    			type: "get",
	    			success: function(result){
	    				if(result != 0){
				    		alert("입력하신 이메일 주소로 인증번호를 발송했습니다.");
				    		$("#findId-authKey").show();
		    				$("#authKey-input").focus();
				    		$.ajax({
				    			url: "/member/getIdByEmail.pb",
				    			data: {"mEmail": mEmail},
				    			type: "get",
				    			success: function(result){
				    				if(result != null){
				    					var memberId = result[0].memberId;
				    					var authKey = result[0].authKey;
					    				$("#findId-memberId").val(memberId);
					    				$("#forgotId-authKey").val(authKey);
				    				}
				    			}
				    		});
	    				}else{
	    					alert("회원정보를 다시 확인해 주세요.");
	    					$("#email-input").focus();
	    					$("#findId-authKey").hide();
	    				}
	    			}
	    		});
    		}
    	});
    	
    	$("#findId-btn").on("click", function(){
    		var inputAuthKey = $("#authKey-input").val(); // 입력된 인증 키
    		var memberId = $("#findId-memberId").val(); // 해당 회원 아이디
    		var authKey = $("#forgotId-authKey").val(); // db에 저장된 인증 키
    		if($("#findId-authKey").css("display") == "none"){
    			alert("인증요청을 해 주세요.");
    		}else if(inputAuthKey == ""){
    			alert("인증번호를 입력해 주세요.");
    			$("#authKey-input").focus();
    		}else if(authKey == ""){
    			alert("오류입니다. 잠시 후 다시 시도해 주세요.");
    		}else if(inputAuthKey == authKey){
    			alert("인증이 완료되었습니다.");
    			$("#sendId").submit();
    		}
    	});
    </script>
</body>
</html>