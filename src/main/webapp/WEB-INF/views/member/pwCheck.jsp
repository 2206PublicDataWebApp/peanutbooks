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
    <link rel="stylesheet" href="../resources/css/member2.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
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
        		<p class="guide error pw-error-4">비밀번호를 입력해 주세요.</p>
        		<button class="pwChk-btn" type="button" onclick="checkPw()">확인</button>
        	</div>
	    </div>
	</main>
	<jsp:include page="../footer/footer.jsp" />
	<script>
		// 비밀번호 값 검사
		$("#pwChk-pw").on("keyup", function(){
			var inputPw = $("#pwChk-pw").val();
			if(inputPw == ""){
				$(".guide.error.pw-error-4").show();
				$("#pwChk-pw").css("border", "solid 1px #FF577F");
			}else{
				$(".guide.error.pw-error-4").hide();
				$("#pwChk-pw").css("border", "solid 1px #ccc");
			}
		});
		// 확인 버튼 클릭 시
		function checkPw(){
			var memberPw = $("#pwChk-memberPw").val();
			var inputPw = $("#pwChk-pw").val();
			if(inputPw == ""){
				alert("비밀번호를 입력해 주세요.");
				$("#pwChk-pw").focus().css("border", "solid 1px #FF577F");
			}else if(inputPw != memberPw){
				alert("비밀번호가 일치하지 않습니다.");
				$("#pwChk-pw").focus().css("border", "solid 1px #FF577F");
			}else{
				window.location.href="/member/modifyView.pb";
			}
		}
	</script>
</body>
</html>