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
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
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
	#colText{
		padding-top: 0.7rem;
	}
	 #page-area {
		font-size: 0.8rem;
		font-weight: bolder;
		text-align:center;
	}
	
	#page-area span{
		border-radius: 0.5rem;
		line-height:2rem;
		
	}
	
	.pageNow, .pages{
		display: inline-block;
		width: 3rem;
		height: 2rem;
		transition: 0.2s;
		
	}
	.pages{
		background-color:#FFD384;
		transition: 0.2s;	
	}
	.pageNow, .pages:hover{
		background-color:#FF884B;
		color:white;
		transition : 0.2s;
	}
	
	.prev, .next {
		display: inline-block;
		width: 3rem;
		height: 2rem;
		
	}
    .pages a {
		width:100%;
		height:100%;
		display:inline-block;
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
	<section class="container">
	<!-- 세부페이지 head 시작 -->
		<!-- 세부페이지 큰 제목 -->
		<div class="container text-center">
			<div class="row row-cols-1">
			   <div class="col" id="colText" style="background-color: #5e5e5e; color: white; height:45px" vertical-align: middle;>1:1문의 리스트</div>        
			</div>
		<!-- 세부페이지 큰 제목 끝 -->
		<br>
		<hr>
			<!-- 세부 메뉴 시작 -->
			<ul class="mainUl">
	    		<li class="mainLi"><div style="text-align:center">
			    		<a href="/admin/qnaList.kh">총문의<br>
			    		${totalQna }</a>
			    	</div>
			    </li>
			    <li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/admin/qnaList.kh?qnaStatus=Y">답변완료<br>
			    		${totalAnswer }</a>
			    	</div>
				</li>
				<li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/admin/qnaList.kh?qnaStatus=N">처리중<br>
			    		${totalNoAnswer }</a>
			    	</div>
				</li>
	 		</ul>
	 		<!-- 세부 메뉴 끝 -->
		<hr>
		<!-- 세부페이지 head 시작 -->
		
		<div class="row row-cols-1">
		<!-- 검색 -->
			<table align="center" class="table col-6" border="0px" style="width:80%;">
				<tr>
					<td class="col-6" style="border:none;">
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
			<table align="center" class="table col-7" border="0px" style="width:80%;">
				<!-- 카테고리별 리스트 시작 -->
				<tr>
					<td class="col-7" style="border:none;" td colspan="6" align="right">
						<a href="/admin/qnaList.kh" style="color: black"> 전체 | </a>
						<a href="/admin/categoryCount.kh?qnaCategory=member&page=${bPage.currentPage }" style="color: black"> 회원문의 |</a>
						<a href="/admin/categoryCount.kh?qnaCategory=point&page=${bPage.currentPage }" style="color: black"> 포인트문의 |</a>
						<a href="/admin/categoryCount.kh?qnaCategory=books&page=${bPage.currentPage }" style="color: black"> 도서문의 |</a>
						<a href="/admin/categoryCount.kh?qnaCategory=others&page=${bPage.currentPage }" style="color: black"> 기타 </a>
					</td>
				</tr>
				<!-- 카테고리별 리스트 끝 -->
				<c:if test="${!empty aList }">
						<tr>
							<th class="col-1">NO</th>
							<th class="col-1">문의유형</th>
							<th class="col-1">작성자</th>
							<th class="col-2">제목</th>
							<th class="col-1">상태</th>
							<th class="col-1">작성일</th>
						</tr>
					<c:forEach items="${aList }" var="qna" varStatus="i">
						<tr>
							<td>${i.count }</td>
							<td>
								<c:if test="${qna.qnaCategory == 'member' }">회원관련</c:if>
								<c:if test="${qna.qnaCategory == 'point' }">포인트관련</c:if>
								<c:if test="${qna.qnaCategory == 'books' }">도서관련</c:if>
								<c:if test="${qna.qnaCategory == 'others' }">기타</c:if>
							</td>
							<td>${qna.memberId }</td>
							<td align="left"><a href="/admin/aqnaDetailView.kh?qnaNo=${qna.qnaNo }&page=${bPage.currentPage }&searchCondition=${searchCondition}&searchValue=${searchValue}" style="color: black">${qna.qnaTitle }</a></td>
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
						<td colspan="6" align="center" >
							<b>게시물이 없습니다.</b>
					</tr>
				</c:if>
					<tr align="center" height="20">
						<td colspan="6" align="center" style="border:none;">
			            <!--  페이징 영역 -->
							<article id="page-area">
				
								<!-- 이전 페이지 출력 -->
								<c:if test="${bPage.startNavi != 1 && bPage.startNavi > 0  }">
									<span class="prev"> 
										<a href="/admin/categoryCount.kh?page=${bPage.startNavi-1 }&qnaCategory=${qna.qnaCategory}"> [이전] </a>
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
										<span class="pages"> <a href="/admin/categoryCount.kh?page=${p }&qnaCategory=${qna.qnaCategory}">${p }</a>
										</span>
									</c:if>
				
								</c:forEach>
								<!-- 다음 페이지 출력 -->
								<c:if test="${bPage.endNavi ne bPage.maxPage  }">
									<span class="next"> <a
										href="/admin/categoryCount.kh?page=${bPage.endNavi+1 }&qnaCategory=${qna.qnaCategory}"> [다음] </a>
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