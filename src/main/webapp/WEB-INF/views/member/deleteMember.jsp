<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>땅콩북스: 회원탈퇴</title>
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
			    <div>회원탈퇴</div>
			</div>
			<form action="/member/delete.pb" method="post">
			<input type="hidden" value="${sessionScope.loginMember.memberId}" name="memberId">
			<input type="hidden" value="${sessionScope.loginMember.memberPw}" id="delete-memberPw">
				<div class="delete-div-bottom">
					<div class="div-square">
						<p>${sessionScope.loginMember.mNickname}님의 이용현황</p>
						<ul>
							<li>저장된 도서 개</li>
							<li>등록한 작품 ${sessionScope.writtenBooks}개</li>
							<li>보유한 땅콩 ${sessionScope.loginMember.mPoint}땅콩</li>
						</ul>
					</div>
					<div class="div-check">
						<p>탈퇴 회원 유의 사항</p>
						<ul>
							<li>탈퇴를 하실 경우, 기존 서재에 대한 복구는 불가능하며 로그인이 필요한 모든 서비스를 이용하실 수 없습니다.</li>
							<li>탈퇴를 하실 경우, 계정과 함께 땅콩이 소멸 됩니다. 원치 않으실 경우 땅콩 이용 후 탈퇴해 주시기 바랍니다.</li>
							<li>탈퇴를 하실 경우 결제하신 서비스 이용 권한을 포기하는 것으로 간주됩니다. 원치 않으실 경우, 탈퇴를 보류해주세요.</li>
						</ul>
						<input type="checkbox" id="delete-check"><label for="delete-check">안내사항을 모두 확인하였으며, 이에 동의합니다.</label>
					</div>
					<div class="div-pw">
						<label>비밀번호</label><br>
						<input type="password" id="delete-pw1"><br>
						<label>비밀번호 확인</label><br>
						<input type="password" id="delete-pw2">
					</div>
					<div class="div-btn">
						<button type="submit" class="delete-btn">회원 탈퇴</button>
					</div>
				</div>
			</form>
		</div>
	</main>
	<jsp:include page="../footer/footer.jsp" />
	<script>
		$(".delete-btn").on("click", function(){
			var infoChk = $("#delete-check").is(":checked");
			var pwChk1 = $("#delete-pw1").val();
			var pwChk2 = $("#delete-pw2").val();
			var memberPw = $("#delete-memberPw").val();
			if(infoChk == false){
				alert("탈퇴 회원 유의 사항을 읽고 동의해 주세요.");
				return false;
			}else if(pwChk1 == "" || pwChk2 == "" || pwChk1 != pwChk2 || pwChk1 != memberPw){
				alert("비밀번호를 다시 확인해 주세요.");
				return false;
			}else{
				if(confirm("정말 탈퇴하시겠습니까?\n회원 탈퇴는 되돌릴 수 없습니다.") == true){
					alert("회원 탈퇴가 완료되었습니다.\n감사합니다.");					
					return true;
				}else{
					window.location.href="/member/deleteView.pb";
					return false;
				}
			}
		});
	</script>
</body>
</html>