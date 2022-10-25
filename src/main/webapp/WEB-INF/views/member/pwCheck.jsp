<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>땅콩북스: 비밀번호 확인</title>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../header/header.jsp" />
	<main>
		<div class="info-wrapper">
			<div class="info-header">
			    <div>비밀번호 확인</div>
			</div>
			<div class="msg-area">
				<div class="circle-area">
					<img alt="자물쇠" src="../resources/img/member/icons8-lock-48.png">
				</div>
				<p>
					안전한 개인정보 변경을 위해<br>
					비밀번호를 다시 입력해주세요.
				</p>
	        </div>
        	<div class="pw-input-area">
        		<label for="pwChk-pw">비밀번호</label>
        		<input id="pwChk-memberPw" type="hidden" value="${loginMember.memberPw}">
        		<input id="pwChk-pw" type="password" required>
        		<button class="pwChk-btn" type="submit">확인</button>
        	</div>
	    </div>
	</main>
	<jsp:include page="../footer/footer.jsp" />
	<script>
		$(".pwChk-btn").on("submit", function(){
			var memberPw = $("#pwChk-memberPw").val();
			var inputPw = $("#pwChk-pw").val();
			if(memberPw == inputPw){
				
			}
		});
	</script>
</body>
</html>