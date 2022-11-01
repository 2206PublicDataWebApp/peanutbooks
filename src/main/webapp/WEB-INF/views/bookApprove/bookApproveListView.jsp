<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서심의리스트</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
 <style>
	.container {
		width: 70%;
		max-width: 1000px;
		margin: 0 auto;
	}
	a {
		text-decoration:none !important
	}
	a:hover { 
		text-decoration:none !important
	}
</style>  
<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
<br><br>	
<div class="container">
	<div class="container text-center">
	<!-- 검색 -->
		<div class="row row-cols-1">
			<table align="center" class="table col-10" border="0px">
				<tr>
					<td style="border:none;">
						<div style="display: inline-block; margin: 0 5px;  float: left;">
						<h4 align="center">도서심의 리스트</h4>
						</div>
						<!-- div 오른쪽 정렬 -->
						<div style="display: inline-block; margin: 0 5px;  float: right;">
						<form action="/admin/bookApprove/search.kh" method="get" >
							<div style= "display: inline-block">
								<select class="form-select" name="searchCondition" >
									<option <c:if test="${searchCondition eq 'all'}">selected</c:if> value="all">전체</option>
									<option <c:if test="${searchCondition eq 'name'}">selected</c:if> value="Id">작가</option>
									<option <c:if test="${searchCondition eq 'title'}">selected</c:if> value="title">제목</option>
									<option <c:if test="${searchCondition eq 'contents'}">selected</c:if> value="contents">내용</option>
								</select>
							</div>
							<div style= "display: inline-block">
							<input type="text" name="searchValue" placeholder="검색"  value="${searchValue }">
							</div>
							<div style= "display: inline-block">
							<input type="submit" value="검색" class="btn btn-dark">
							</div>
						</form>
						</div>
					</td>
				</tr>
				
			</table>
		</div>
	<!-- 검색 -->


	<!-- 리스트 출력 -->
		<div class="row row-cols-1">
			<table align="center" class="table col-10">
				<tr>
					<td style="border:none;" td colspan="5" align="right">
						<a href="/admin/approveBookList.kh" style="color: black"> 전체 | </a>
						<a href="/admin/categoryCount.kh?category=novel&page=${currentPage }" style="color: black"> 소설 |</a>
						<a href="/admin/categoryCount.kh?category=essay&page=${currentPage }" style="color: black"> 에세이 |</a>
						<a href="/admin/categoryCount.kh?category=tale&page=${currentPage }" style="color: black"> 동화 |</a>
						<a href="/admin/ategoryCount.kh?category=poem&page=${currentPage }" style="color: black"> 시 |</a>
						<a href="/admin/categoryCount.kh?category=other&page=${currentPage }" style="color: black"> 기타 </a>
					</td>
				</tr>
				<tr>
					<td>번호</td>
					<td>종류</td>
					<td>제목</td>
					<td>작성일</td>
					<td>심의</td>
				</tr>
			<c:if test="${!empty baList }">
				<c:forEach items="${baList }" var="originbook" varStatus="i">
					<tr>
						<td>${i.count }</td>
						<td>
							<c:if test="${originbook.category == 'novel'}">소설</c:if>
							<c:if test="${originbook.category == 'essay'}">에세이</c:if>
							<c:if test="${originbook.category == 'tale'}">동화</c:if>
							<c:if test="${originbook.category == 'poem'}">시</c:if>
							<c:if test="${originbook.category == 'other'}">기타</c:if>
						</td>
						<td align="left">
						<c:set var = "string1" value = "${originbook.bookTitle}"/>
					    <c:set var = "length" value = "${fn:length(string1)}"/>
					    <c:set var = "title" value = "${fn:substring(string1, 0, 25)}" />
						<div id="img"
								onclick="window.open('/book/OridetailSeries.do?bookNo=${oSeries.bookNo }&seriesNo=${oSeries.seriesNo }')">${title }</div></td>
						<td>${originbook.insertDate }</td>
						<td>
							<c:if test="${originbook.checkPermission == 'Y' }">승인</c:if>
							<c:if test="${originbook.checkPermission == 'N' }">보류</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty baList }">
				<tr>
					<td colspan="5" align="center"><b>데이터가 존재하지 않습니다.</b></td>
				</tr>
			</c:if>
				<tr align="center" height="20"> ${bPage} <-페이지
		            <td colspan="5" style="border:none;">
		                <c:if test="${bPage.currentPage != 1}">
		                    <a href="/admin/${urlVal }.kh?page=${bPage.currentPage - 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}">[이전]</a>
		                </c:if>
		                <c:forEach var="p" begin = "${bPage.startNavi }" end="${bPage.endNavi }">
		                    <c:if test="${bPage.currentPage eq p }">
		                        <b>${p}</b> 
		                    </c:if>
		                    <c:if test="${bPage.currentPage ne p }">
		                        <a href = "/admin/${urlVal }.kh?page=${p }&searchCondition=${searchCondition }&searchValue=${searchValue }">${p}</a>
		                    </c:if>
		                </c:forEach>
		            <c:if test = "${bPage.currentPage < bPage.maxPage }">
		                <a href = "/admin/${urlVal}.kh?page=${bPage.currentPage + 1}&searchCondition=${searchCondition}&searchValue=${searchValue}">[다음]</a>
		            </c:if>
		            </td>
		        </tr>
			</table>
		</div>
	</div>
</div>
<br><br>
</main>
<!-- main contents End -->



<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>