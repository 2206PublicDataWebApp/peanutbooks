<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>땅콩북스: 내 정보</title>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/member2.css">
	<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
</head>
<body>
	<jsp:include page="../header/header.jsp" />
	<main>
	    <div class="info-wrapper">
			<div class="info-header">
			    <div>내 정보 관리</div>
			</div>
			<div class="menu-area">
			<c:if test="${sessionScope.loginMember.accType ne 'naver'}">
            	<div class="info-menu" onclick="location.href='/member/pwCheck.pb'">
	                <div class="menu-item txt">개인정보 수정</div>
	                <div class="menu-item arrow">></div>
            	</div>
            </c:if>
            <c:if test="${sessionScope.loginMember.accType eq 'naver'}">
            	<div class="info-menu" onclick="location.href='/member/modifyView.pb'">
	                <div class="menu-item txt">개인정보 수정</div>
	                <div class="menu-item arrow">></div>
            	</div>
            </c:if>	
	            <div class="info-menu" onclick="location.href='/member/deleteView.pb'">
                	<div class="menu-item txt">회원탈퇴</div>
                	<div class="menu-item arrow">></div>
	            </div>
	        </div>
	    </div>
	</main>
	<jsp:include page="../footer/footer.jsp" />
</body>
</html>