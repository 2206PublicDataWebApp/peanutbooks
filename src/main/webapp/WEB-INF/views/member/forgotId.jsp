<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>땅콩북스: 아이디 찾기</title>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
   	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</head>
<body>
<!-- 	<form action=""> -->
		<input id="findId-memberId" type="text" name="memberId">
		<input id="forgotId-authKey" type="text" name="authKey">
	    <div class="row">
	        <div id="login-img" class="col-lg-8"></div>
	        <div id="findId-area" class="col-lg-4">
	            <div><h3 id="findId-hl">아이디 찾기</h3></div>
	            <div id="input-area">
	                <label for="findId-email">이메일</label>
	                <div id="findId-email"><input id="email-input" type="text"><button type="button" id="email-btn">인증요청</button></div>
	                <div id="findId-authKey"><input id="authKey-input" type="text" placeholder="인증번호"></div>
	                <div><button id="findId-btn" type="button">아이디 찾기</button></div>
	            </div>
	        </div>
	    </div>
<!--     </form> -->
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
				    		$.ajax({
				    			url: "/member/getIdByEmail.pb",
				    			data: {"mEmail": mEmail},
				    			type: "get",
				    			success: function(result){
				    				console.log(result);
				    				$("#authKey-input").focus();
				    				if(authData != null){
					    				$("#findId-memberId").val();
					    				$("#forgotId-authKey").val();
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
    		var authKey = $("#authKey-input").val();
    		var memberId = $("#findId-memberId").val();
    		if($("#findId-authKey").css("display") == "none"){
    			alert("인증요청을 해 주세요.");
    			return false;
    		}else if(authKey == ""){
    			alert("인증번호를 입력해 주세요.");
    			return false;
    		}else{
    			$.ajax({
    				url: "/member/checkAuthKey.pb",
    				data: {"authKey": authKey, "memberId": memberId},
    				type: "get",
    				success: function(result){
    					if(result != 0){
    						location.href = "/member/idResult.pb?memberId="+memberId;
    						return true;
    					}else{
    						alert("인증번호가 일치하지 않습니다. 다시 확인해 주세요.");
    						return false;
    					}
    				}
    			})
    		}
    	});
    </script>
</body>
</html>