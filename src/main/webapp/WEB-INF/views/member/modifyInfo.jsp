<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>땅콩북스: 개인정보수정</title>
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
			    <div>개인정보수정</div>
			</div>
			<div class="modify-area">
				<label for="">별명</label>
				<input id="modify-nick" type="text">
				<label for="">비밀번호</label><br>
				<input id="modify-pw" type="password">
				<button class="modify-pw-btn" type="button">비밀번호 변경</button><br>
				<button class="modify-btn" type="button">확인</button>
			</div>
		</div>
	</main>
	<jsp:include page="../footer/footer.jsp" />
</body>
</html>