<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>땅콩북스</title>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
</head>
<body>
	<script>
		var msg = "${msg}"; // alert 창 메시지
		var url = "${url}"; // alert 창에서 확인 클릭 시 이동할 페이지
		alert(msg);
		document.location.href = url;
	</script>
</body>
</html>