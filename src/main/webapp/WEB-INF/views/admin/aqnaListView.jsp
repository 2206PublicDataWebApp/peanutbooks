<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의내역</title>
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
	.subMenu{
	    border-top: solid 1px lightgray;
	    border-bottom: solid 1px lightgray;
	    text-align: center;
	    font-weight: bold;
	    height: 44px;
	    line-height: 44px;
	    position: fixed;
	    width: 100%;
	    background-color: white;
	}
</style>  
<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->

<!-- sub menu bar -->
<div class="subMenu">
    <div>공지사항 | 문의게시판</div>
</div>

<!-- sub menu bar -->

<!-- main contents start -->
<main>
<br><br>	
<div class="container">

	<div class="container text-center">
		<div class="row row-cols-1">
			<div class="col" style="background-color: #5e5e5e; color: white; height:45px; vertical-align: middle;"><a href="/admin/qnaList.kh"">문의내역</a></div>
		</div>
		<br>
		<div class="row row-cols-7" style="background-color:#e0e0e0; padding:20px">
		    <div class="col-2" style="text-align:right;">
		    	총문의<br>1&nbsp;&nbsp;&nbsp;
		    </div>
		    <div class="col"><img src="/resources/img/sidebar.png"></div>
		    <div class="col">답변완료<br>1</div>
		    <div class="col"><img src="/resources/img/sidebar.png"></div>
		    <div class="col">처리중<br>1</div>
		    <div class="col"><img src="/resources/img/sidebar.png"></div>
		    <div class="col-2" style="text-align:left;">&nbsp;&nbsp;접수<br>&nbsp;&nbsp;&nbsp;&nbsp;1</div>
		</div>

	<br><br>

	<div class="row row-cols-1">
	<!-- 검색 -->
		<table align="center" class="table col-10" border="0px">
			<tr>
				<td style="border:none;">
					<div style="display: inline-block; margin: 0 5px;  float: left;">
					<h4 align="center">문의 리스트</h4>
					</div>
					<!-- div 오른쪽 정렬 -->
					<div style="display: inline-block; margin: 0 5px;  float: right;">
					<form action="/admin/search.kh" method="get" >
						<div style= "display: inline-block">
							<select class="form-select" name="searchCondition" >
								<option <c:if test="${searchCondition eq 'all'}">selected</c:if> value="all">전체</option>
								<option <c:if test="${searchCondition eq 'name'}">selected</c:if> value="name">작성자</option>
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
				<td style="border:none;" td colspan="6" align="right">
					<a href="/admin/qnaList.kh" style="color: black"> 전체 | </a>
					<a href="/admin/categoryCount.kh?qnaCategory=member&page=${currentPage }" style="color: black"> 회원문의 |</a>
					<a href="/admin/categoryCount.kh?qnaCategory=point&page=${currentPage }" style="color: black"> 포인트문의 |</a>
					<a href="/admin/categoryCount.kh?qnaCategory=books&page=${currentPage }" style="color: black"> 도서문의 |</a>
					<a href="/admin/categoryCount.kh?qnaCategory=others&page=${currentPage }" style="color: black"> 기타 </a>
				</td>
			</tr>
			<c:if test="${!empty aList }">
					<tr>
						<th>NO</th>
						<th>문의유형</th>
						<th>작성자</th>
						<th>제목</th>
						<th>상태</th>
						<th>작성일</th>
					</tr>
				<c:forEach items="${aList }" var="qna" varStatus="i">
					<tr>
						<td>${i.count }</td>
						<td>
							<c:if test="${qna.qnaCategory == 'member' }">
								회원관련
							</c:if>
							<c:if test="${qna.qnaCategory == 'point' }">
								포인트관련
							</c:if>
							<c:if test="${qna.qnaCategory == 'books' }">
								도서관련
							</c:if>
							<c:if test="${qna.qnaCategory == 'others' }">
								기타
							</c:if>
						</td>
						<td>${qna.memberId }</td>
						<td align="left"><a href="/admin/aqnaDetailView.kh?qnaNo=${qna.qnaNo }&page=${currentPage }&searchCondition=${searchCondition}&searchValue=${searchValue}" style="color: black">${qna.qnaTitle }</a></td>
						<td>
							<c:if test="${qna.qnaStatus eq 'Y' }">
								<b style="color: #2d532c;">답변완료</b>
							</c:if>
							<c:if test="${qna.qnaStatus eq 'N' }">
								<b style="color: #d21853;">답변대기</b>
							</c:if>
						</td>
						<td>${qna.qCreateDate }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty aList }">
				<tr>
					<td colspan="5" align="center"><b>작성된 게시물이 없습니다.</b></td>
				</tr>
			</c:if>
				<tr align="center" height="20">
		            <td colspan="5" style="border:none;">
		                <c:if test="${currentPage != 1}">
		                    <a href="/admin/${urlVal }.kh?page=${currentPage - 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}">[이전]</a>
		                </c:if>
		                <c:forEach var="p" begin = "${startNavi }" end="${endNavi }">
		                    <c:if test="${currentPage eq p }">
		                        <b>${p}</b> 
		                    </c:if>
		                    <c:if test="${currentPage ne p }">
		                        <a href = "/admin/${urlVal }.kh?page=${p }&searchCondition=${searchCondition }&searchValue=${searchValue }">${p}</a>
		                    </c:if>
		                </c:forEach>
		            <c:if test = "${currentPage < maxPage }">
		                <a href = "/admin/${urlVal}.kh?page=${currentPage + 1}&searchCondition=${searchCondition}&searchValue=${searchValue}">[다음]</a>
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