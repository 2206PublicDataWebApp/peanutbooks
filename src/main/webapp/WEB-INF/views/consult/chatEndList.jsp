<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 채팅 검색화면</title>
<link rel="stylesheet" href="../resources/css/chat/chatEndList.css" ></link>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
</head>
<body>
<div id="header">
<jsp:include page="../header/adminheader.jsp"></jsp:include>
</div>

<br>
<main>
	<div>
		<div id="searchbtn">
			<select name="chatEndList" id="endList">
				<option value="all" checked>전체</option>
				<option value="id">아이디</option>
				<option value="date">날짜</option>
			</select> 
			<c:if test="$('#endList option:selected').val()=='id'">
			<input type="text">
			</c:if>
			<c:if test="$('#endList option:selected').val()=='date'">
			<input type="date">
			</c:if>

		</div>
		<div>
			<div id="pagename" align="center">채팅상담리스트</div>
			<div class="table-responsive">
				<table class="table table-striped table-hover" border="1"
					id="togglePart">
					<thead class="table-light">
						<tr>
							<th scope="col">번호</th>
							<th scope="col">고객ID</th>
							<th scope="col">문의주제</th>
							<th scope="col">신청시간</th>
							<th scope="col">상담결과</th>
							<th scope="col">상세보기</th>
						</tr>
					</thead>
					<c:if test="${empty chatList }">
					<tbody>
						<tr>
							<td>채팅상담종료된 건이 없습니다.</td>
						</tr>
					</tbody>
					</c:if>

					<c:if test="${!empty chatList  }">
					<tbody>
						<c:forEach items="${chatList }" var="ConsultServer" varStatus="i">
							<tr>
								<td>${ConsultServer.titleNo }</td>
								<td>${ConsultServer.csMemberId }</td>
								<td>${ConsultServer.csTitle }</td>
								<td><fmt:formatDate value="${ConsultServer.csDate }"
										pattern="yy.MM.dd HH:mm:ss" /></td>
								<td>${ConsultServer.csResult }</td>
								<td><input type="button"
									onclick="popupOpen(${ConsultServer.csMemberId },${ConsultServer.titleNo });"
									value="상세보기">
									</button></td>
							</tr>

						</c:forEach>
						</tdody>
						</table>
						
						
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
										" href="/peanut/listStart.kh?page=${p }&memberId=${loginMember.memberId}&csDate">
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
	</div>

</main>

<jsp:include page="../footer/footer.jsp"></jsp:include>

<script>
	function poputOpen(csMemberId,titleNo){
		var windo = 'status=no ,toolbar=no,scrollbars=no, menubar=no,resizable=no,titlebar=no, width=800,height=800';
		window.open("/consult/chatDetail.kh?csMemberId="+ csMemberId+"&titleNo="+titleNo","PopupWin", windo);
	}
	
/* 	$('input[type="date"]').val();
	'2022-10-24' */

	
</script>
</body>
</html>
