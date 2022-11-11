<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>땅콩북스: 개인정보수정</title>
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
			    <div>개인정보 수정</div>
			</div>
			<form action="/member/modify.pb" method="post">
			<input type="hidden" value="${sessionScope.loginMember.memberId}" name="memberId">
				<div class="modify-area">
					<div id="div-nick">
						<label for="modify-nick">별명</label>
						<input id="modify-nick" type="text" value="${sessionScope.loginMember.mNickname}" name="mNickname" maxlength="20">
						<p class="guide ok nick">사용 가능한 별명입니다.</p>
	                	<p class="guide error nick-error-1">이미 사용중인 별명입니다.</p>
	                	<p class="guide error nick-error-2">별명을 입력해 주세요.</p>
					</div>
					<div id="div-id">
						<label for="info-id">아이디</label><br>
						<input id="info-id" type="text" value="${sessionScope.loginMember.memberId}" readonly>
					</div>
					<div id="div-pw">
						<label for="modify-pw">비밀번호</label><br>
						<input id="modify-pw" type="password" value="${sessionScope.loginMember.memberPw}" name="originPw" readonly>
						<button class="modify-pw-btn" type="button" onclick="openInput()">비밀번호 변경</button><br>
					</div>
					<div id="div-newPw">
						<label for="modify-pw2">비밀번호</label><br>
						<input id="modify-pw2" type="password" placeholder="기존 비밀번호 입력"><br>
						<p class="guide error pw-error-4">비밀번호를 입력해 주세요.</p>
						<label for="modify-newPw">새 비밀번호</label><br>
						<input id="modify-newPw" type="password" placeholder="숫자, 영문 조합 최소 5자" name="memberPw"><br>
						<p class="guide ok pw">안전한 비밀번호입니다.</p>
						<p class="guide error pw-error-1">5~16자 이내로 입력해 주세요.</p>
	                	<p class="guide error pw-error-2">숫자, 영문 대소문자 조합으로 입력해 주세요.</p>
	                	<p class="guide error pw-error-3">비밀번호를 입력해 주세요.</p>
						<input id="modify-newPwChk" type="password" placeholder="비밀번호 재입력">
						<p class="guide error pw2-error-1">비밀번호가 일치하지 않습니다.</p>
	                	<p class="guide error pw2-error-2">비밀번호를 입력해 주세요.</p>
					</div>
					<div id="div-email">
						<label for="info-email">이메일</label><br>
						<input id="info-email" type="text" value="${sessionScope.loginMember.mEmail}" readonly>
					</div>
					<button class="modify-btn" type="submit">확인</button>
				</div>
			</form>
		</div>
	</main>
	<jsp:include page="../footer/footer.jsp" />
	<script>
		// 별명 유효성 검사
		$("#modify-nick").on("keyup", function(){
			var mNickname = $("#modify-nick").val(); // 입력된 닉네임 값
			var loginNickname = "${sessionScope.loginMember.mNickname}"; // 로그인한 회원 닉네임
			if(mNickname == ""){ // 닉네임 입력이 공백일 시
				$(".guide.ok.nick").hide();
				$(".guide.error.nick-error-1").hide();
				$(".guide.error.nick-error-2").show();
				$("#modify-nick").css("border", "solid 1px #FF577F");
			}else if(mNickname == loginNickname){ // 입력된 닉네임이 로그인한 회원의 기존 닉네임과 같을 시
				$(".guide.ok.nick").show();
				$(".guide.error.nick-error-1").hide();
				$(".guide.error.nick-error-2").hide();
				$("#modify-nick").css("border", "solid 1px #ccc");
			}else{
				$.ajax({
					url: "/member/checkNickname.pb",
					data: {"mNickname" : mNickname},
					type: "get",
					success: function(result){
						if(result != 0){ // 입력된 닉네임을 db에서 검색한 결과가 0이 아닐 때
							$(".guide.ok.nick").hide();
							$(".guide.error.nick-error-1").show();
							$(".guide.error.nick-error-2").hide();
							$("#modify-nick").css("border", "solid 1px #FF577F");
						}else{
							$(".guide.ok.nick").show();
							$(".guide.error.nick-error-1").hide();
							$(".guide.error.nick-error-2").hide();
							$("#modify-nick").css("border", "solid 1px #ccc");
						}
					}
				})
			}
		});
		// 새 비밀번호 유효성 검사
    	$("#modify-newPw").on("keyup", function(){
    		var newPw = $("#modify-newPw").val();
    		var regEx = /(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]/;
    		if(newPw == ""){
    			$(".guide.ok.pw").hide();
    			$(".guide.error.pw-error-1").hide();
    			$(".guide.error.pw-error-2").hide();
    			$(".guide.error.pw-error-3").show();
    			$("#modify-newPw").css("border", "solid 1px #FF577F");
    		}else if(newPw.length < 5){
    			$(".guide.ok.pw").hide();
    			$(".guide.error.pw-error-1").show();
    			$(".guide.error.pw-error-2").hide();
    			$(".guide.error.pw-error-3").hide();
    			$("#modify-newPw").css("border", "solid 1px #FF577F");
    		}else if(newPw.length >= 5 && !regEx.test(newPw)){
    			$(".guide.ok.pw").hide();
    			$(".guide.error.pw-error-1").hide();
    			$(".guide.error.pw-error-2").show();
    			$(".guide.error.pw-error-3").hide();
    			$("#modify-newPw").css("border", "solid 1px #FF577F");
    		}else if(newPw.length >= 5 && regEx.test(newPw)){
    			$(".guide.ok.pw").show();
    			$(".guide.error.pw-error-1").hide();
    			$(".guide.error.pw-error-2").hide();
    			$(".guide.error.pw-error-3").hide();
    			$("#modify-newPw").css("border", "solid 1px #ccc");
    		}
    	});
    	// 기존 비밀번호 검사
    	$("#modify-pw2").on("keyup", function(){
    		var memberPw = $("#modify-pw2").val();
    		if(memberPw == ""){
    			$(".guide.error.pw-error-4").show();
    			$("#modify-pw2").css("border", "solid 1px #FF577F");
    		}else{
    			$(".guide.error.pw-error-4").hide();
    			$("#modify-pw2").css("border", "solid 1px #ccc");
    		}
    	});
    	// 새 비밀번호 확인 검사
    	$("#modify-newPwChk").on("keyup", function(){
    		var newPw = $("#modify-newPw").val();
    		var chkPw = $("#modify-newPwChk").val();
    		if(chkPw == ""){
    			$(".guide.error.pw2-error-1").hide();
    			$(".guide.error.pw2-error-2").show();
    			$("#modify-newPwChk").css("border", "solid 1px #FF577F");
    		}else if(chkPw != newPw){
    			$(".guide.error.pw2-error-1").show();
    			$(".guide.error.pw2-error-2").hide();
    			$("#modify-newPwChk").css("border", "solid 1px #FF577F");
    		}else{
    			$(".guide.error.pw2-error-1").hide();
    			$(".guide.error.pw2-error-2").hide();
    			$("#modify-newPwChk").css("border", "solid 1px #ccc");
    		}
    	});
		// 새 비밀번호 입력 칸 열기
		function openInput(){
			$("#div-pw").hide();
			$("#div-newPw").show();
		}
		// 확인 버튼 클릭 시
		$(".modify-btn").on("click", function(){
			var mNickname = $("#modify-nick").val(); // 닉네임 값
			var memberPw = $("#modify-pw").val(); // 기존 비밀번호 값
			var pwCheck = $("#modify-pw2").val(); // 입력된 기존 비밀번호 값
			var newPw = $("#modify-newPw").val(); // 새 비밀번호 값
			var newPwChk = $("#modify-newPwChk").val(); // 새 비밀번호 확인 값
			var regEx = /(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]/; // 비밀번호 정규식
			if(mNickname == "" || $("#modify-nick").css("border")=="solid 1px #FF577F"){
				alert("별명을 다시 확인해 주세요.");
				$("#modify-nick").css("border", "solid 1px #FF577F");
				$("#modify-nick").focus();
				return false;
			}else if($("#div-newPw").css("display") != "none"){
				if(pwCheck == "" || pwCheck != memberPw){
					alert("기존 비밀번호를 다시 확인해 주세요.");
					$("#modify-pw2").css("border", "solid 1px #FF577F");
					$("#modify-pw2").focus();
					return false;
				}else if(newPw == "" || newPwChk == "" || newPw != newPwChk || !regEx.test(newPw)){
					alert("변경하실 비밀번호를 다시 확인해 주세요.");
					$("#modify-newPw").css("border", "solid 1px #FF577F");
					$("#modify-newPw").focus();
					return false;
				}else{
					alert("수정하신 정보가 반영되었습니다.");
					return true;
				}
			}else{
				alert("수정하신 정보가 반영되었습니다.");
				return true;
			}
		});
	</script>
</body>
</html>