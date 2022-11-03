<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>땅콩사용리스트</title>
<link rel="stylesheet" href="../resources/css/pay/peanetList.css" ></link>
<script scr="../resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="../header/header.jsp"></jsp:include>
	<main>
		<h1 id="h"></h1>
		<div id="spanDIV">
			<div class="totalpeanet"><img alt="" src="../resources/img/icons8-peanut-64.png"/></div>
			<div class="totalpeanet">${printID }님의 남은 땅콩은 ${ppSum}개</div>
		</div>
		<div id="table">
			<c:if test="${empty pList }">
				<table>
					<tr>
						<td id="emptyTD">땅콩이 없습니다.</td>
					</tr>
				</table>
			</c:if>

			<c:if test="${!empty pList  }">
				<c:forEach items="${pList }" var="PeanutPoint" varStatus="i">
					<table>						
						<tr>							
							<c:if test="${!empty PeanutPoint.bookName }">
								<td class="contexnts"><button class="numbtn">${PeanutPoint.peanutNo }</button>&nbsp;&nbsp;${PeanutPoint.bookName } </td>
							</c:if>
							<c:if test="${empty PeanutPoint.bookName }">
								<td class="contexnts"><button class="numbtn">${PeanutPoint.peanutNo }</button>&nbsp;&nbsp;땅콩충전</td>
							</c:if>
							<td rowspan="2" class="monitercontrol"></td>
							<td rowspan="2" class="peanet"><span>${PeanutPoint.peanutPoint}</span></td>
						</tr>
						<tr>
							<td class="date">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate
									value="${PeanutPoint.ppDate }"
									pattern="yyyy년MM월dd일 HH시 mm분 ss초" /></td>
						</tr>
						<tr class="line"></tr>
					</table>
				</c:forEach>
				<div class="page_wrap">
					<div class="pagination">

						<c:if test="${pm.startNavi !=1}">
							<a
								href="/peanut/listStart.kh?page=${1}&memberId=${loginMember.memberId}"
								title="first page"><svg fill="currentColor">
							<path
										d="M17.59 18L19 16.59 14.42 12 19 7.41 17.59 6l-6 6zM11 18l1.41-1.41L7.83 12l4.58-4.59L11 6l-6 6z" /></svg>
								First</a>
							<a
								href="/peanut/listStart.kh?page=${pm.startNavi -1}&memberId=${loginMember.memberId}"
								title="previous page"><svg fill="currentColor">
								<path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z" /></svg> </a>
						</c:if>
						<c:forEach begin="${pm.startNavi}" end="${pm.endNavi }" var="p">

							<a class="page-active"
								" href="/peanut/listStart.kh?page=${p }&memberId=${loginMember.memberId}">
								${p} </a>

						</c:forEach> 
						<c:if test="${pm.maxPage > pm.currentPage }">
							<a title="next page"
								href="/peanut/listStart.kh?page=${pm.endNavi+1}&memberId=${loginMember.memberId}"><svg
									fill="currentColor">
								<path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z" /></svg></a>
						</c:if>
						<c:if test="${(pm.endNavi %5)==0 && pm.maxPage != pm.endNavi }">
							<a
								href="/peanut/listStart.kh?page=${pm.maxPage}&memberId=${loginMember.memberId}"
								title="last page">Last<svg fill="currentColor">
							<path
										d="M6.41 6L5 7.41 9.58 12 5 16.59 6.41 18l6-6zM13 6l-1.41 1.41L16.17 12l-4.58 4.59L13 18l6-6z" /></svg></a>
						</c:if>
					</div>
				</div>
			</c:if>
		</div>
	</main>
	<jsp:include page="../footer/footer.jsp"></jsp:include>
</body>
</html>