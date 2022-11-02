<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String csDate= request.getParameter("csDate")==null?"":request.getParameter("csDate");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 채팅 검색화면</title>
<link rel="stylesheet" href="../resources/css/chat/chatDetail.css" ></link>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
</head>
<body>
		<div id="searchbtn">						
			<button id="csBtn" onclick="exit();">종 료</button>
		</div>

<br>
<main>
	<div>
		<div>
			<div id="pagename" align="center">${param.cMemberId }님의 상담내용</div>
			<div class="table-responsive">
				<table class="table table-striped table-hover" 
					id="togglePart">
					<thead class="table-light">
						<tr>
							<th scope="col">번호</th>
							<th scope="col">말한사람</th>							
							<th scope="col">대화시간</th>
							<th scope="col">대화내용</th>							
						</tr>
					</thead>					
					
					<tbody>
						<c:forEach items="${cList }" var="Consult" varStatus="i">
							<tr style="height : 40px;">
								<td>${i.count +((pm.currentPage-1)*10)}</td>
								<td>${Consult.cMemberId }</td>
								<td><fmt:formatDate value="${Consult.cDate }"
										pattern="yy.MM.dd HH:mm:ss" /></td>
								<td>${Consult.cContexts }</td>				
								
							</tr>
						</c:forEach>
					</tdody>
				</table>


			<div class="page_wrap">
				<div class="pagination">

					<c:if test="${pm.startNavi !=1}">
						<a href="javascript:void(0);"
							onclick="pageChatSearch(${1});
								title=" firstpage"><svg
								fill="currentColor">
				<path
									d="M17.59 18L19 16.59 14.42 12 19 7.41 17.59 6l-6 6zM11 18l1.41-1.41L7.83 12l4.58-4.59L11 6l-6 6z" /></svg>
							First</a>
						<a href="javascript:void(0);"
							onclick="pageChatSearch(${pm.startNavi -1});										
								title="
							previouspage"><svg fill="currentColor">
					<path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z" /></svg> </a>
					</c:if>
					<c:forEach begin="${pm.startNavi}" end="${pm.endNavi }" var="p">

						<a class="page-active" href="javascript:void(0);"
							onclick="pageChatSearch(${p});"> <%-- 	href="/consult/endList.kh?page=${p }&memberId=${memberId}&csDate=$('input[type=date]').prop('defaultValue') "> --%>
							${p}
						</a>

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
	</div>
	</div>

</main>
<script>	
		
	
	/* 페이징  */
	function pageChatSearch(page){
		
		var titleNo="${titleNo }";			
		var memberId="${param.cMemberId}";		
		location.href="/consult/chatDetail.kh?page="+page+"&cMemberId="+memberId+"&titleNo="+titleNo;
		
	}
	/* 종료  */
	function exit(){
		self.close();			
	}

	
</script>
</body>
</html>
