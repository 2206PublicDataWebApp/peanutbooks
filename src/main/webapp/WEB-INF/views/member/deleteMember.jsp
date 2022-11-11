<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
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
							<li>
								<span>저장된 도서</span>
								<span>${sessionScope.savedBooks}개</span>
							</li>
							<li>
								<span>등록한 작품</span>
								<span>${sessionScope.writtenBooks}개</span>
							</li>
							<li>
								<span>보유한 땅콩</span>
								<span>${sessionScope.loginMember.mPoint}땅콩</span>
							</li>
							<c:if test="${!empty sessionScope.lastDate}">
								<li>
									<span>사용중인 구독권</span>
									<span>${sessionScope.lastDate}까지</span>
								</li>
							</c:if>
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
						<label for="delete-pw1">비밀번호 확인</label><br>
						<input type="password" id="delete-pw1"><br>
						<p class="guide error pw-error-4">비밀번호를 입력해 주세요.</p>
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
		// 기존 비밀번호 값 검사
		$("#delete-pw1").on("keyup", function(){
			var memberPw = $("#delete-pw1").val();
			if(memberPw == ""){
				$(".guide.error.pw-error-4").show();
				$("#delete-pw1").css("border", "solid 1px #FF577F");
			}else{
				$(".guide.error.pw-error-4").hide();
				$("#delete-pw1").css("border", "solid 1px #ccc");
			}
		});
		// 회원 탈퇴 버튼 클릭 시
		$(".delete-btn").on("click", function(){
			var infoChk = $("#delete-check").is(":checked");
			var pwChk1 = $("#delete-pw1").val();
			var memberPw = $("#delete-memberPw").val();
			if(infoChk == false){
				alert("탈퇴 회원 유의 사항을 읽고 동의해 주세요.");
				return false;
			}else if(pwChk1 == "" || pwChk1 != memberPw){
				alert("비밀번호를 다시 확인해 주세요.");
				$("#delete-pw1").focus();
				$("#delete-pw1").css("border", "solid 1px #FF577F");
				return false;
			}else{
				if(confirm("정말 탈퇴하시겠습니까?\n회원 탈퇴는 되돌릴 수 없습니다.") == true){
					alert("회원 탈퇴가 완료되었습니다.\n감사합니다.");					
					return true;
				}else{
					window.location.href="/member/memberInfo.pb";
					return false;
				}
			}
		});
	</script>
</body>
</html>