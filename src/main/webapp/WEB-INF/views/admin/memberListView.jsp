<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel="stylesheet" href="/resources/css/admin/member/css.css" ></link>
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
		<!-- 세부페이지 큰 제목 -->
		<div class="main_title">
		   <h6>회원리스트</h6>        
		</div>
		<!-- 세부페이지 큰 제목 끝 -->
			<!-- 세부 메뉴 시작 -->
			<div class="sub_menu">
			     <ul class="amount">
			        <li>
			          <div>
			            <div class="contents1"><a href="/admin/adminListView.kh?code=all">전체회원</a></div>
			            <div class="result"><a href="/admin/adminListView.kh?code=all">${totalCount }</a></div>
			          </div>
			        </li>
			        <li>
			          <div>
			            <div class="contents1"><a href="/admin/adminListView.kh?code=today">오늘가입</a></div>
			            <div class="result"><a href="/admin/adminListView.kh?code=today">${todayCount }</a></div>
			          </div>
			        </li>
			        <li>
			          <div>
			            <div class="contents1"><a href="/admin/adminListView.kh?code=del">탈퇴회원</a></div>
			            <div class="result"><a href="/admin/adminListView.kh?code=del">${deleteCount }</a></div>
			          </div>
			        </li>
			     </ul>
		     </div>
		<hr>
		<!-- amount end -->


		<br>
		<!-- 세부페이지 head 시작 -->
		
		<!-- 리스트 출력, 제목, 검색 -->
		<div class="board_title">
		<!-- 제목 & 검색 폼 -->
			<div class="b_title">
            	<strong>가입회원</strong>
            </div>
            <div class="b_search">
            	<!-- 검색 -->
	    		<form action="/admin/MemberSearch.kh" method="get" >
			        <ul class="search_area">
			        	<li>
			         		<select class="form-select" name="searchCondition" >
								<option value="all" <c:if test="${searchCondition eq 'all'}">selected</c:if>>전체</option>
								<option value="name" <c:if test="${searchCondition eq 'name'}">selected</c:if>>닉네임</option>
								<option value="id" <c:if test="${searchCondition eq 'id'}">selected</c:if>>아이디</option>
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
	<br><br>
	
		<!-- 리스트 출력 제목 & 검색 폼 -->
		<div class="board_list_wrap">
            <div class="board_list">
                <div class="top">
                    <div class="num">번호</div>
                    <div class="writer">닉네임</div>
                    <div class="title">아이디</div>
                    <div class="mpoint">땅콩</div>
                    <div class="status">상태</div>
                    <div class="date">가입일</div>
                </div>
	            <c:if test="${!empty mList }">
	               	<c:forEach items="${mList }" var="member" varStatus="i">
	                <div>
	                    <div class="num">${i.count }</div>
	                    <div class="writer"><a href="/admin/memberDetailView.kh?memberId=${member.memberId }&page=${bPage.currentPage }" style="color: black">${member.mNickname}</a></div>
	                    <div class="title"><a href="/admin/memberDetailView.kh?memberId=${member.memberId }&page=${bPage.currentPage }" style="color: black">${member.memberId }</a></div>
	                    <div class=status>
	                    	<c:if test="${member.deleteYN eq 'N' }">회원</c:if>
							<c:if test="${member.deleteYN eq 'Y' }">탈퇴</c:if>
						</div>
	                    <div class="mpoint">
	                    	${member.mPoint }
	                    </div>
	                    <div class="date">${member.joinDate }</div>
	                </div>
		        	</c:forEach> 
	            </c:if>    
				<c:if test="${empty mList }">
					<br>
	        		<b style="color: darkgray; font-size: 16px">조회한 내역이 없습니다.</b>
				</c:if>
				<!-- 페이징 -->
			<article id="page-area">

				<!-- 이전 페이지 출력 -->
				<c:if test="${bPage.startNavi != 1 && bPage.startNavi > 0  }">
					<span class="prev"> 
						<a href="/admin/adminListView.kh?page=${bPage.startNavi-1 }"> < </a>
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
						<span class="pages"> <a href="/admin/adminListView.kh?page=${p }&code=${code}">${p }</a>
						</span>
					</c:if>

				</c:forEach>
				<!-- 다음 페이지 출력 -->
				<c:if test="${bPage.endNavi ne bPage.maxPage  }">
					<span class="next"> <a href="/admin/adminListView.kh?page=${bPage.endNavi+1 }&code=${code}"> > </a>
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