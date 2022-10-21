<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>땅콩북스: 비밀번호 재설정</title>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
    <div class="row">
        <div id="login-img" class="col-lg-8"></div>
        <div id="newpw-area" class="col-lg-4">
            <h3 id="findid-hl">비밀번호 재설정</h3>
            <div id="newpw-input">
                <label for="join-pw">비밀번호</label>
                <div><input class="newpw-inputs" id="join-pw" type="password" placeholder="숫자, 영문 조합 최소 5자"></div>
                <div><input class="newpw-inputs" id="join-pw2" type="password" placeholder="비밀번호 재입력"></div>
                <div><button id="check-btn" type="button">확인</button></div>
            </div>
        </div>
    </div>
</body>
</html>