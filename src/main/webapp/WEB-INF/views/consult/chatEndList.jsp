<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String csDate = request.getParameter("csDate") == null ? "" : request.getParameter("csDate");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 채팅 검색화면</title>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel="stylesheet" href="/resources/css/chat/chatEndList.css"></link>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

</head>
<body>
	<div id="header">
		<jsp:include page="../header/adminheader.jsp"></jsp:include>
	</div>

	<br>
	<main id="c_end_main">
		<div id="pagename" align="center">채팅상담리스트</div>
		<div>
			<div id="searchbtn">
				<label for="select"></label> <select name="chatEndList" id="select">
					<option value="all" checked>전체</option>
					<option value="id">아이디</option>
					<option value="date">날짜</option>
				</select> <input type="text" style="display: none"> <input
					type="date" style="display: none">
				<button id="csBtn" onclick="chatsearch();">검 색</button>

			</div>

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
									<td><input type="button" class="detailBtn"
										onclick="popUpOpen('${ConsultServer.csMemberId }', '${ConsultServer.titleNo }' );"
										value="상세보기" /></td>
								</tr>

							</c:forEach>
							</tdody>
					</c:if>
				</table>

				<c:if test="${!empty chatList  }">
					<div class="page_wrap">
						<div class="pagination">

							<c:if test="${pm.startNavi !=1}">
								<a href="javascript:void(0);" onclick="pageChatSearch(${1});"
									title="firstpage"><svg fill="currentColor">
						<path
											d="M17.59 18L19 16.59 14.42 12 19 7.41 17.59 6l-6 6zM11 18l1.41-1.41L7.83 12l4.58-4.59L11 6l-6 6z" /></svg>
									First</a>
								<a href="javascript:void(0);"
									onclick="pageChatSearch(${pm.startNavi -1});"
									title="previouspage"><svg fill="currentColor">
					<path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z" /></svg> </a>
							</c:if>

							<c:forEach begin="${pm.startNavi}" end="${pm.endNavi }" var="p">
								<c:if test="${pm.currentPage eq p}">
									<a class="page-active" href="javascript:void(0);"
										onclick="pageChatSearch(${p});"
										style="background-color: grey;"> ${p} </a>
								</c:if>
								<c:if test="${pm.currentPage ne p}">
									<a class="page-active" href="javascript:void(0);"
										onclick="pageChatSearch(${p});"> ${p} </a>
								</c:if>
							</c:forEach>

							<c:if test="${pm.maxPage > pm.currentPage }">
								<a title="next page" href="javascript:void(0);"
									onclick="pageChatSearch(${pm.endNavi+1});"><svg
										fill="currentColor">
				<path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z" /></svg></a>
							</c:if>
							<c:if test="${(pm.endNavi %5)==0 && pm.maxPage != pm.endNavi }">
								<a href="javascript:void(0);"
									onclick="pageChatSearch(${pm.maxPage});" title="last page">Last<svg
										fill="currentColor">
			<path
											d="M6.41 6L5 7.41 9.58 12 5 16.59 6.41 18l6-6zM13 6l-1.41 1.41L16.17 12l-4.58 4.59L13 18l6-6z" /></svg></a>
							</c:if>
						</div>
					</div>
				</c:if>

			</div>
		</div>

	</main>

	<div class="footer">
		<jsp:include page="../footer/footer.jsp"></jsp:include>
	</div>


	<script>
	/* chat내용 상세보기 */
	function popUpOpen(csMemberId,titleNo){
		var windo = 'status=no ,toolbar=no,scrollbars=no, menubar=no,resizable=no,titlebar=no, width=780,height=750';
		window.open("/consult/chatDetail.kh?cMemberId="+ csMemberId+"&titleNo="+titleNo,"PopupWin", windo);
	}
	
	/* 검색창 display  */
	$('#select').on('change',function(){
		if($('#select option').eq(1).is(':checked')){
			$('input[type="text"]').show();
			$('input[type="date"]').hide();
		}else if($('#select option').eq(2).is(':checked')){
			$('input[type="date"]').show();
			$('input[type="text"]').hide();
		}else{
			$('input[type="text"]').hide();
			$('input[type="date"]').hide();
		}
	});
	
		
	
	/* 페이징  */
	function pageChatSearch(page){  
		
		var csDate="${csDate}";			
		var memberId="${csMemberId}";		
		location.href="/consult/endList.kh?page="+page+"&csMemberId="+memberId+"&searchDate="+csDate;
	}
	/* 검색버튼  */
	function chatsearch(){
		var csDate=$('input[type=date]').val();
		var memberId=$('input[type=text]').val();
		if($('#select option').eq(1).is(':checked')){	
			location.href="/consult/endList.kh?csMemberId="+memberId;
		}else if($('#select option').eq(2).is(':checked')){
			location.href="/consult/endList.kh?searchDate="+csDate;
		}else{
			location.href="/consult/endList.kh";
		};
			
	}

	
</script>
</body>
</html>
