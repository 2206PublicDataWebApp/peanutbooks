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
</head>
<body>
	<jsp:include page="../header/header.jsp" />
	<main>
		<div class="info-wrapper">
			<div class="info-header">
			    <div>개인정보수정</div>
			</div>
			<form action="/member/modify.pb" method="post">
			<input type="hidden" value="${sessionScope.loginMember.memberId}" name="memberId">
				<div class="modify-area">
					<div id="div-nick">
						<label for="modify-nick">별명</label>
						<input id="modify-nick" type="text" value="${sessionScope.loginMember.mNickname}" name="mNickname">
					</div>
					<div id="div-pw">
						<label for="modify-pw">비밀번호</label><br>
						<input id="modify-pw" type="password" value="${sessionScope.loginMember.memberPw}" readonly>
						<button class="modify-pw-btn" type="button" onclick="openInput()">비밀번호 변경</button><br>
					</div>
					<div id="div-newPw">
						<label for="modify-pw2">비밀번호</label><br>
						<input id="modify-pw2" type="password" placeholder="기존 비밀번호 입력"><br>
						<label for="">새 비밀번호</label><br>
						<input id="modify-newPw" type="password" placeholder="숫자, 영문 조합 최소 5자" name="memberPw"><br>
						<input id="modify-newPwChk" type="password" placeholder="비밀번호 재입력">
					</div>
					<button class="modify-btn" type="submit">확인</button>
				</div>
			</form>
		</div>
	</main>
	<jsp:include page="../footer/footer.jsp" />
	<script>
		// 새 비밀번호 입력 칸 열기
		function openInput(){
			$("#div-pw").hide();
			$("#div-newPw").show();
		}
		// 확인 버튼 클릭 시
		$(".modify-btn").on("click", function(){
			var memberPw = $("#modify-pw").val();
			var pwCheck = $("#modify-pw2").val();
			var newPw = $("#modify-newPw").val();
			var newPwChk = $("#modify-newPwChk").val();
			var regEx = /(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]/;
			if(pwCheck == ""){
				alert("기존 비밀번호를 입력해주세요.");
				$("#modify-pw2").focus();
				return false;
			}else if(newPw == "" || newPwChk == "" || newPw != newPwChk || !regEx.test(newPw)){
				alert("변경하실 비밀번호를 다시 확인해 주세요.");
				return false;
			}else if(memberPw != pwCheck){
				alert("기존 비밀번호를 다시 확인해주세요.");
				$("#modify-pw2").focus();
				return false;
			}else{
				alert("수정하신 정보가 반영되었습니다.");
				return true;
			}
		});
	</script>
</body>
</html>