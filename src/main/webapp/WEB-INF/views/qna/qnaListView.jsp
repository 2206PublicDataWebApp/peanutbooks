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
	.btn {
	  appearance: none;
	    -webkit-appearance: none;
	  font-family: sans-serif;
	  cursor: pointer;
	  padding: 12px;
	  min-width: 150px;
	  border: none;
	    -webkit-transition: background-color 100ms linear;
	    -ms-transition: background-color 100ms linear;
	     transition: background-color 100ms linear;
	}
	.mainUl {
		list-style-type: none;
		padding: 0px;
	}
	
	.mainLi {
		display: inline-block;
		margin-left: 18px;
		margin-right: 18px;
		font-size: 18px;
		/* font-weight: bold; */
	}
</style> 
<body>
<!-- header start -->
<jsp:include page="../header/header.jsp"></jsp:include>
<!-- header End -->

<!-- sub menu bar -->
<div class="subMenu">
    <div>공지사항 | 문의게시판</div>
</div>

<!-- sub menu bar -->

<!-- main contents start -->
<main>
	<section class="container">
	<!-- 세부페이지 head 시작 -->
		<!-- 세부페이지 큰 제목 -->
		<div class="container text-center">
			<div class="row row-cols-2">
				<div class="col" style="background-color: #5e5e5e; color: white; height:45px; vertical-align: middle;"><a href="/qna/list.kh">문의내역</a></div>
				<div class="col" style="background-color: #c9c9c9; color: white; height:45px; vertical-align: middle;"><a href="/qna/writeView">문의작성</a></div>
			</div>
		<!-- 세부페이지 큰 제목 끝 -->
		<br>
		<hr>
			<!-- 세부 메뉴 시작 -->
			<ul class="mainUl">
	    		<li class="mainLi"><div style="text-align:right">
			    		<a href="/qna/list.kh">총문의<br>1&nbsp;&nbsp;&nbsp;&nbsp;</a>
			    	</div>
			    </li>
			    <li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/qna/list.kh">답변완료<br>1</a>
			    	</div>
				</li>
				<li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/qna/list.kh">처리중<br>1</a>
			    	</div>
				</li>
				<li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:left">
			    		<a href="/qna/list.kh">접수<br>&nbsp;&nbsp;&nbsp;&nbsp;1</a>
			    	</div>
	    		</li>
	 		</ul>
	 		<!-- 세부 메뉴 끝 -->
		<hr>
		<br>
		<!-- 세부페이지 body 시작 -->

		<!-- 검색 -->
		<div class="row row-cols-1">
			<table align="center" class="table col-7" border="0" style="width:80%;">
				<tr>
					<td class="col-7" style="border:none;">
						<div style="display: inline-block; margin: 5px;  float: left;">
						<h4 align="center">문의 리스트</h4>
						</div>
						<!-- div 오른쪽 정렬 -->
						<div style="display: inline-block; margin: 5px;  float: right;">
						<form action="/qna/search.kh" method="get" >
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
			<table align="center" class="table col-6">
				<c:if test="${!empty qList }">
						<tr>
							<th class="col-1">NO</th>
							<th class="col-1">문의유형</th>
							<th class="col-2">제목</th>
							<th class="col-1">상태</th>
							<th class="col-1">작성일</th>
						</tr>
					<c:forEach items="${qList }" var="qna" varStatus="i">
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
							<td align="left"><a href="/qna/detailView.kh?qnaNo=${qna.qnaNo }&page=${bPage.currentPage }&searchCondition=${searchCondition}&searchValue=${searchValue}" style="color: black">${qna.qnaTitle }</a></td>
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
				<c:if test="${empty qList }">
					<tr>
						<td colspan="5" align="center"><b>작성된 게시물이 없습니다.</b></td>
					</tr>
				</c:if>
					<tr align="center" height="20">
						<td colspan="6" align="center" style="border:none;">
			            <!--  페이징 영역 -->
							<article id="page-area">
				
								<!-- 이전 페이지 출력 -->
								<c:if test="${bPage.startNavi != 1 && bPage.startNavi > 0  }">
									<span class="prev"> 
										<a href="/qna/list.kh?page=${bPage.startNavi-1 }"> [이전] </a>
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
										<span class="pages"> <a href="/qna/list.kh?page=${p }">${p }</a>
										</span>
									</c:if>
				
								</c:forEach>
								<!-- 다음 페이지 출력 -->
								<c:if test="${bPage.endNavi ne bPage.maxPage  }">
									<span class="next"> <a
										href="/qna/list?page=${bPage.endNavi+1 }"> [다음] </a>
									</span>
								</c:if>
							</article>
							<!-- 페이징 영역 종료 -->
			
			            </td>
			        </tr>
				</table>
			</div>
			<!-- 리스트 출력 -->
		</div>
	</section>
<br><br>
</main>
<!-- main contents End -->




<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>