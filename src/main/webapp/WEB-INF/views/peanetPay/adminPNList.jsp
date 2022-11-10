<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>땅콩관리자리스트</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/pay/adminPNList.css" ></link>
</head>

<body>
<jsp:include page="../header/adminheader.jsp"></jsp:include>
	<main>
		<h1 id="h">땅콩 내역 조회</h1>
		<div id="searchbtn">
			<label for="selectPN" ></label>
			<select id="selectPN" >
				<option value="all" checked>전체</option>
				<option value="id">아이디</option>
				<option value="date">날짜</option>
			</select> 		
			
			<input class="inputzone" type="text" style="display:none" >						
			<input class="inputzone" type="date" style="display:none">				
			
			<button id="csBtn" class="btnbox" onclick="pNutSearch();">검 색</button>

		</div>
		<div id="markZone">
			<div id="markpeanut">
				<c:if
					test="${!empty searchppDate and (empty printId || printId =='' ) }">
					<button class="result">${searchppDate }</button>
				</c:if>
				<c:if
					test="${(empty printId  || printId=='admin' || printId=='') and empty searchppDate  }">
					<button class="result">전체 </button>
				</c:if>
				<c:if
					test="${!empty printId and printId !='' and printId !='admin' and empty searchppDate }">
					<button class="result">${printId }</button>
				</c:if>
			</div>

		</div>
		<div id="table">
			<c:if test="${empty pList }">
				<table>
					<tr>
						<td>땅콩이 없습니다.</td>
					</tr>
				</table>
			</c:if>

			<c:if test="${!empty pList  }">
				<c:forEach items="${pList }" var="PeanutPoint" varStatus="i">
					<table>
						<tr>
							<td class="tableNo" rowspan="2"><button class="numbtn">${i.count +((pm.currentPage-1)*10) }</button></td>
							<c:if test="${!empty PeanutPoint.bookName }">
								<td class="contexnts">${PeanutPoint.bookName } 열람</td>
								<td rowspan="2" class="monitercontrol"></td>
								<td rowspan="2" class="peanet"><span>${PeanutPoint.peanutPoint} 땅콩</span></td>
							</c:if>
							<c:if test="${empty PeanutPoint.bookName }">
								<td class="contexnts">&nbsp;땅콩충전</td>
								<td rowspan="2" class="monitercontrol"></td>
								<td rowspan="2" class="peanet"><span>+ ${PeanutPoint.peanutPoint} 땅콩</span></td>
							</c:if>
						</tr>
						<tr>
							<td class="date" colspan="3">&nbsp;<fmt:formatDate
									value="${PeanutPoint.ppDate }"
									pattern="yyyy년MM월dd일 HH시 mm분 ss초" /></td>
						</tr>
						<tr class="line"></tr>
					</table>
				</c:forEach>
				</div>
				<div class="page_wrap">
					<div class="pagination">
						<c:if test="${pm.startNavi !=1}">
							<a href="javascript:void(0);" 
								onclick="pagePNSearch(${1});"
								title="firstpage"><svg
									fill="currentColor">
				<path
										d="M17.59 18L19 16.59 14.42 12 19 7.41 17.59 6l-6 6zM11 18l1.41-1.41L7.83 12l4.58-4.59L11 6l-6 6z" /></svg>
								First</a>
							<a href="javascript:void(0);"
								onclick="pagePNSearch(${pm.startNavi -1});"										
								title="previouspage"><svg
									fill="currentColor">
					<path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z" /></svg> </a>
						</c:if>
						<c:forEach begin="${pm.startNavi}" end="${pm.endNavi }" var="p">
							<c:if test="${pm.currentPage eq p}"  >					
								<a class="page-active" href="javascript:void(0);"
								   onclick="pagePNSearch(${p });" style="background-color:grey;">
									${p} </a>
							</c:if>
							<c:if test="${pm.currentPage ne p}"  >					
								<a class="page-active" href="javascript:void(0);"
								   onclick="pagePNSearch(${p });" >
									${p} </a>
							</c:if>

						</c:forEach>


						<c:if test="${pm.maxPage > pm.currentPage && pm.maxPage != pm.endNavi}">
							<a title="next page" href="javascript:void(0);"
								onclick="pagePNSearch(${pm.endNavi+1});"><svg
									fill="currentColor">
					<path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z" /></svg></a>
						</c:if>
						<c:if test="${(pm.endNavi %5)==0 && pm.maxPage != pm.endNavi }">
							<a href="javascript:void(0);"
								onclick="pagePNSearch(${pm.maxPage});" title="last page">Last<svg
									fill="currentColor">
				<path
										d="M6.41 6L5 7.41 9.58 12 5 16.59 6.41 18l6-6zM13 6l-1.41 1.41L16.17 12l-4.58 4.59L13 18l6-6z" /></svg></a>
						</c:if>
					</div>
				</div>
			</c:if>
		
	</main>
	<jsp:include page="../footer/footer.jsp"></jsp:include>
	
<script>
	/* 페이징  */
	function pagePNSearch(page){ 
		
		var ppDate="${searchppDate }";			
		var memberId="${printId }";		
		location.href="/peanut/admin_list.kh?page="+page+"&memberId="+memberId+"&searchDate="+ppDate;
	}
	
	/* 검색창 display  */
	$('#selectPN').change(function(){
		if($('#selectPN option').eq(1).is(':checked')){
			$('input[type="text"]').show();
			$('input[type="date"]').hide();
		}else if($('#selectPN option').eq(2).is(':checked')){
			$('input[type="date"]').show();
			$('input[type="text"]').hide();
		}else{
			$('input[type="text"]').hide();
			$('input[type="date"]').hide();
		}
	});
	
	/* 검색버튼  */
	function pNutSearch(){
		var ppDate=$('input[type=date]').val();
		var memberId=$('input[type=text]').val();
		if($('#selectPN option').eq(1).is(':checked')){	
			location.href="/peanut/admin_list.kh?memberId="+memberId;
		}else if($('#selectPN option').eq(2).is(':checked')){
			location.href="/peanut/admin_list.kh?searchDate="+ppDate;
		}else{
			location.href="/peanut/admin_list.kh";
		};
			
	}
</script>
</body>
</html>