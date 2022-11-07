<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>땅콩북스: 알림</title>
	<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../resources/css/news.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="../header/header.jsp" />
	<main>
		<div>
			<div class="news-header-top">
			    <div>알림센터</div>
			</div>
			<div class="news-header-bottom">
			    <div>
					<button type="button">
						<span>내 알림</span>
					</button>
<!-- 					<button type="button">
						<span>공지</span>
					</button> -->
					<button type="button">
						<span>이벤트/프로모션</span>
					</button>
				</div>
			</div>
			<div class="news-area">
				<!-- 반복 영역 시작 -->
				<%-- <c:if test="${!empty nList}"> --%>
					<%-- <c:forEach items="${nList}" var="notice"> --%>
						<div class="news-contents-area">
							<span class="news-contents">
								<%-- <a>${notice.noticeTitle}</a> --%>
							</span>
							<span class="delete-icon">
								<a><img alt="닫기" src="../resources/img/news/icons8-close-48.png"></a>
							</span>
						</div>
					<%-- </c:forEach> --%>
				<%-- </c:if> --%>
				<!-- 반복 영역 끝-->
			</div>
		</div>
	</main>
	<jsp:include page="../footer/footer.jsp" />
</body>
</html>