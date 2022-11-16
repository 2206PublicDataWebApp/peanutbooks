<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel="stylesheet" href="/resources/css/admin/notice/css.css" ></link>
</head>
 
<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
	<div class="board_wrap">
	<!-- 세부페이지 head 시작 -->
		<!-- 세부페이지 큰 제목 -->
		<div class="main_title">
			<div class="main_t1">
				<a href="/notice/list.kh"><p style="color:white";>공지사항 리스트</p></a>
			</div>
			<div class="main_t2">
				<a href="/notice/writeView.kh">공지사항 작성</a>
			</div>        
		</div>
		<!-- 세부페이지 큰 제목 끝 -->
	
		<!-- 세부 메뉴 시작 -->
		<div class="sub_menu">
		     <ul class="amount">
		        <li>
		          <div>
		            <div class="contents1">
		            	<a href="/notice/list.kh">전체</a>
		            </div>
		            <div class="result">
		            	<a href="/notice/list.kh">${totalBoard }</a>
		            </div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1">
		            	<a href="/notice/list.kh?nStatus=Y">게시</a>
			    	</div>
		            <div class="result">
		            	<a href="/notice/list.kh?nStatus=Y">${showBoard }</a>
		            </div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1">
		            	<a href="/notice/list.kh?nStatus=N">보류</a>
		            </div>
		            <div class="result">
		            	<a href="/notice/list.kh?nStatus=N">${hideBoard }</a>
		            </div>
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
	            	<strong>공지사항</strong>
	            </div>
	            <div class="b_search">
	            	<!-- 검색 -->
		    		<form action="/notice/search.kh?nStatus=${notice.nStatus }" method="get" >
				        <ul class="search_area">
				        	<li>
				         		<select class="form-select" name="searchCondition" >
									<option <c:if test="${searchCondition eq 'all'}">selected</c:if> value="all">전체</option>
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
	        
			<!-- 리스트 출력 제목 & 검색 폼 -->
	        <div class="category_list">
	        	<!-- 카테고리별 리스트 -->
				<a href="/notice/list.kh" style="color: black"> 전체 | </a>
				<a href="/notice/categoryCount.kh?noticeCategory=notice&nStatus=Y" style="color: black"> 공지 |</a>
				<a href="/notice/categoryCount.kh?noticeCategory=update&nStatus=Y" style="color: black"> 업데이트 |</a>
				<a href="/notice/categoryCount.kh?noticeCategory=event&nStatus=Y" style="color: black"> 이벤트 |</a>
				<a href="/notice/categoryCount.kh?noticeCategory=info&nStatus=Y" style="color: black"> 안내 </a>
			</div>
	        <div class="board_list_wrap">
	            <div class="board_list">
	                <div class="top">
	                    <div class="num">번호</div>
	                    <div class="category">문의유형</div>
	                    <div class="title">제목</div>
	                    <div class="status">상태</div>
	                    <div class="date">작성일</div>
	                </div>
					<!-- 카테고리별 리스트 끝 -->
				<c:if test="${!empty nList }">
					<c:forEach items="${nList }" var="notice" varStatus="i">
						<div>
							<div class="num">${i.count }</div>
							<div class="category">
								<c:if test="${notice.noticeCategory == 'notice'}">공지</c:if>
								<c:if test="${notice.noticeCategory == 'update'}">업데이트</c:if>
								<c:if test="${notice.noticeCategory == 'event'}">이벤트</c:if>
								<c:if test="${notice.noticeCategory == 'info'}">안내</c:if>
							</div>
							<div class="title">
								<div style= "display: inline-block"><a href="/notice/noticeDetailView.kh?noticeNo=${notice.noticeNo }&page=${bPage.currentPage }" style="color: black">${notice.noticeTitle }</a></div>
								<c:if test="${notice.nStatus eq 'Y'}"><div style= "display: inline-block"><p class="blink" style="color: #ed436d; font-size:0.8em; font-weight: bold;">게시중</p></div></c:if>
							</div>
							<div class="status">
							<c:if test="${notice.nStatus eq 'Y'}">
								<button type="button" onclick="location.href='/notice/chooseNotice.do?noticeNo=${notice.noticeNo}&nStatus=N&page=${bPage.currentPage}'" class="btn btn-danger btn-sm" style="width:40pt;height:20pt;">보류</button>
							</c:if>
							<c:if test="${notice.nStatus eq 'N'}">
								<button type="button" onclick="location.href='/notice/chooseNotice.do?noticeNo=${notice.noticeNo}&nStatus=Y&page=${bPage.currentPage}'" class="btn btn-success btn-sm" style="width:40pt;height:20pt;">게시</button>
							</c:if>
							</div>
							<div class="date">${notice.nCreateDate }</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${empty nList }">
					<br>
		        	<b style="color: #616161; font-size: 16px; text-align: center;">게시물이 없습니다.</b>
		        </c:if>

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
							<span class="pages"> <a href="/notice/list.kh?page=${p }&noticeCategory=${noticeCategory}">${p }</a>
							</span>
						</c:if>
	
					</c:forEach>
					<!-- 다음 페이지 출력 -->
					<c:if test="${bPage.endNavi ne bPage.maxPage  }">
						<span class="next"> <a
							href="/notice/list.kh?page=${bPage.endNavi+1 }&noticeCategory=${noticeCategory}"> [다음] </a>
						</span>
					</c:if>
				</article>
				<!-- 페이징 영역 종료 -->
			</div>
		</div>
			
	<br>
		<hr>
	<br>
	</div>
</main>
<!-- main contents End -->


<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>