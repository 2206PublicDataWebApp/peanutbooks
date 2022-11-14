<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>1:1문의내역</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel="stylesheet" href="/resources/css/admin/qna/css.css" ></link>
</head>

<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
	<!-- 세부페이지 head 시작 -->
	<!-- 세부페이지 큰 제목 -->
	<div class="board_wrap">
		<div class="main_title">
		   <h6>1:1문의 리스트</h6>        
		</div>
	<!-- 세부페이지 큰 제목 끝 -->

		<!-- 세부 메뉴 시작 -->
		<div class="sub_menu">
		     <ul class="amount">
		        <li>
		          <div>
		            <div class="contents1"><a href="/admin/qnaList.kh">총문의</a></div>
		            <div class="result"><a href="/admin/qnaList.kh">${totalQna }</a></div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1"><a href="/admin/qnaList.kh?qnaStatus=Y">답변완료</a></div>
		            <div class="result"><a href="/admin/qnaList.kh?qnaStatus=Y">${totalAnswer }</a></div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1"><a href="/admin/qnaList.kh?qnaStatus=N">처리중</a></div>
		            <div class="result"><a href="/admin/qnaList.kh?qnaStatus=N">${totalNoAnswer }</a></div>
		          </div>
		        </li>
		     </ul>
	     </div>
	<hr>
	<!-- amount end -->
		
		
	<!-- 리스트 출력 -->
		<div class="board_title">
		<!-- 제목 & 검색 폼 -->
			<div class="b_title">
            	<strong>문의 게시판</strong>
            </div>
            <div class="b_search">
            	<!-- 검색 -->
	    		<form action="/admin/search.kh" method="get" >
			        <ul class="search_area">
			        	<li>
			         		<select class="form-select" name="searchCondition" >
								<option <c:if test="${searchCondition eq 'all'}">selected</c:if> value="all">전체</option>
								<option <c:if test="${searchCondition eq 'name'}">selected</c:if> value="name">작성자</option>
								<option <c:if test="${searchCondition eq 'title'}">selected</c:if> value="title">제목</option>
								<option <c:if test="${searchCondition eq 'contents'}">selected</c:if> value="contents">내용</option>
							</select>
			          	</li>
						<li>
							<input type="text" name="searchValue" placeholder="검색"  value="${searchValue }">
						</li>
			          	<li>
			          		<input type="submit" value="검색" class="btn btn-dark">
						</li>
			        </ul>
		        </form>
			<!-- 검색 -->
            </div>
        </div>
        <!-- 제목 & 검색 폼 -->
        <div class="category_list">
        	<!-- 카테고리별 리스트 -->
        	<a href="/admin/qnaList.kh" style="color: black"> 전체 | </a>
			<a href="/admin/categoryCount.kh?qnaCategory=member&page=${page}" style="color: black;"> 회원문의 |</a>
			<a href="/admin/categoryCount.kh?qnaCategory=point&page=${page}" style="color: black;"> 포인트문의 |</a>
			<a href="/admin/categoryCount.kh?qnaCategory=books&page=${page}" style="color: black;"> 도서문의 |</a>
			<a href="/admin/categoryCount.kh?qnaCategory=others&page=${page}" style="color: black;"> 기타 </a>
        </div>
        <div class="board_list_wrap">
            <div class="board_list">
            <c:if test="${!empty aList }">
                <div class="top">
                    <div class="num">번호</div>
                    <div class="category">문의유형</div>
                    <div class="writer">글쓴이</div>
                    <div class="title">제목</div>
                    <div class="status">상태</div>
                    <div class="date">작성일</div>
                </div>
                <c:forEach items="${aList }" var="qna" varStatus="i">
                <div>
                    <div class="num">${i.count }</div>
                    <div class="category">
                    	<c:if test="${qna.qnaCategory == 'member' }">회원관련</c:if>
						<c:if test="${qna.qnaCategory == 'point' }">포인트관련</c:if>
						<c:if test="${qna.qnaCategory == 'books' }">도서관련</c:if>
						<c:if test="${qna.qnaCategory == 'others' }">기타</c:if>
					</div>
                    <div class="writer">${qna.memberId }</div>
                    <div class="title"><a href="/admin/aqnaDetailView.kh?qnaNo=${qna.qnaNo }&page=${bPage.currentPage }&searchCondition=${searchCondition}&searchValue=${searchValue}" style="color: black">${qna.qnaTitle }</a></div>
                    <div class="status">
                    	<c:if test="${qna.qnaStatus eq 'Y' }">
							<b style="color: #2d532c;">답변완료</b>
						</c:if>
						<c:if test="${qna.qnaStatus eq 'N' }">
							<b style="color: #d21853;">답변대기</b>
						</c:if>
                    </div>
                    <div class="date">${qna.qCreateDate }</div>
                </div>
	        	</c:forEach>
			</c:if>
			<c:if test="${empty aList }">
				<br>
	        	<b style="color: darkgray; font-size: 16px">게시물이 없습니다.</b>
	        </c:if>
	        <!-- 페이징 -->
			<article id="page-area">

				<!-- 이전 페이지 출력 -->
				<c:if test="${bPage.startNavi != 1 && bPage.startNavi > 0  }">
					<span class="prev"> 
						<a href="/admin/qnaList.kh?page=${bPage.startNavi-1 }&qnaStatus=${qna.qnaStatus}"> [이전] </a>
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
						<span class="pages"> <a href="/admin/qnaList.kh?page=${p }&qnaStatus=${qna.qnaStatus}">${p }</a>
						</span>
					</c:if>

				</c:forEach>
				<!-- 다음 페이지 출력 -->
				<c:if test="${bPage.endNavi ne bPage.maxPage  }">
					<span class="next"> <a
						href="/admin/qnaList?page=${bPage.endNavi+1 }&qnaStatus=${qna.qnaStatus}"> [다음] </a>
					</span>
				</c:if>
			</article>
			<!-- 페이징 영역 종료 -->
			</div>
		</div>
	</div>
	<br>
	<hr>
</main>
<!-- main contents End -->

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->

</body>
</html>