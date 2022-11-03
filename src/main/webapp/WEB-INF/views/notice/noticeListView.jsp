<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
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
<jsp:include page="../header/header.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
	<section class="container">
	<!-- 세부페이지 head 시작 -->
		<!-- 세부페이지 큰 제목 -->
		<br>
		<div class="container text-center">
			<hr>
		<!-- 세부페이지 큰 제목 끝 -->
		<br>
		
		<!-- 세부페이지 body 시작 -->
		<!-- 검색 -->
			<div class="row row-cols-1">
				<table align="center" class="table col-7" border="0px">
					<tr>
						<td class="col-7" style="border:none;">
							<div style="display: inline-block; margin: 5px;  float: left;">
							<h4 align="center">공지사항</h4>
							</div>
							<!-- div 오른쪽 정렬 -->
							<div style="display: inline-block; margin: 5px;  float: right;">
							<form action="/notice/search.kh" method="get" >
								<div style= "display: inline-block">
									<select class="form-select" name="searchCondition" >
										<option <c:if test="${searchCondition eq 'all'}">selected</c:if> value="all">전체</option>
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
				<table align="center" class="table col-7">
					<!-- 카테고리별 리스트 시작 -->
					<tr>
						<td class="col-7" style="border:none;" td colspan="4" align="right">
							<a href="/notice/list.kh" style="color: black"> 전체 | </a>
							<a href="/notice/categoryCount.kh?noticeCategory=notice&page=${bPage.currentPage }" style="color: black"> 공지 |</a>
							<a href="/notice/categoryCount.kh?noticeCategory=update&page=${bPage.currentPage }" style="color: black"> 업데이트 |</a>
							<a href="/notice/categoryCount.kh?noticeCategory=event&page=${bPage.currentPage }" style="color: black"> 이벤트 |</a>
							<a href="/notice/categoryCount.kh?noticeCategory=info&page=${bPage.currentPage }" style="color: black"> 안내 </a>
						</td>
					</tr>
					<!-- 카테고리별 리스트 끝 -->
				<c:if test="${!empty nList }">
					<c:forEach items="${nList }" var="notice" varStatus="i">
						<tr>
							<td class="col-1">${i.count }</td>
							<td class="col-1">
								<c:if test="${notice.noticeCategory == 'notice'}">공지</c:if>
								<c:if test="${notice.noticeCategory == 'update'}">업데이트</c:if>
								<c:if test="${notice.noticeCategory == 'event'}">이벤트</c:if>
								<c:if test="${notice.noticeCategory == 'info'}">안내</c:if>
							</td>
							<td class="col-4" align="left"><a href="/notice/noticeDetailView.kh?noticeNo=${notice.noticeNo }&page=${bPage.currentPage }" style="color: black">${notice.noticeTitle }</a></td>
							<td class="col-1">${notice.nCreateDate }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty nList }">
					<tr>
						<td colspan="4" align="center"><b>데이터가 존재하지 않습니다.</b></td>
					</tr>
				</c:if>
					<tr align="center" height="20">
			            <td colspan="6" style="border:none;">
						    <!--  페이징 영역 -->
							<article id="page-area">
				
								<!-- 이전 페이지 출력 -->
								<c:if test="${bPage.startNavi != 1 && bPage.startNavi > 0  }">
									<span class="prev"> <a
										href="/notice/list.kh?page=${bPage.startNavi-1 }"> [이전] </a>
									</span>
								</c:if>
				
								<!-- 페이지 번호 출력 -->
								<c:forEach var="p" begin="${bPage.startNavi}"
									end="${bPage.endNavi}">
				
									
									<c:if test="${p == bPage.currentPage  }">
										<span class="pageNow"> 
											${p }
										</span>	
									</c:if> 
									<c:if test="${p == 0  }">
										<span class="pageNow"> 
											${p+1 }	
										</span>	
									</c:if>
									
				
									<c:if test="${p != bPage.currentPage && p !=0}">
										<span class="pages"> <a href="/notice/list.kh?page=${p }">${p }</a>
										</span>
									</c:if>
				
								</c:forEach>
								<!-- 다음 페이지 출력 -->
								<c:if test="${bPage.endNavi ne bPage.maxPage  }">
									<span class="next"> <a
										href="/notice/list.kh?page=${bPage.endNavi+1 }"> [다음] </a>
									</span>
								</c:if>
				
				
							</article>
							<!-- 페이징 영역 종료 -->
			            </td>
			        </tr>
				</table>
			</div>
		</div>
	<br>
		<hr>
		</section>
<br>
</main>
<!-- main contents End -->



<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>