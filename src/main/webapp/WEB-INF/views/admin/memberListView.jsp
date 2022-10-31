<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
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
	subHeader {
		position: sticky;
		top: 88px;
		
		background: white;
		color: black;
/* 		padding-left: 0px; 
		padding-right: 0px;   */
		padding: 5px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		vertical-align: middle;
		z-index: 100;
	}

	
	ul {
		list-style-type: none;
		padding: 0px;
	}
	
	li {
		display: inline-block;
		margin-left: 16px;
		font-size: 16px;
		/* font-weight: bold; */
	}
</style>  
<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->
<subHeader>
	<hr>
	<nav>
	 	<ul>
	    	<li>전체회원</li>
	    	<li>메뉴2</li>
	    	<li>메뉴3</li>
	  	</ul>
	</nav>
	<hr>
</subHeader>
<!-- main contents start -->
<main>


<div class="container">
	<div class="container text-center">
		<div class="row row-cols-1">
			<div class="col" style="background-color: #5e5e5e; color: white; height:45px; vertical-align: middle;">회원리스트</div>
		</div>
		<br>
		<div class="row row-cols-7" style="background-color:#e0e0e0; padding:20px">
		    <div class="col-2" style="text-align:right;">
		    	<a href="/admin/adminListView.kh">전체회원<br>1</a>&nbsp;&nbsp;&nbsp;
		    </div>
		    <div class="col"><img src="/resources/img/sidebar.png"></div>
		    <div class="col">오늘가입<br>1</div>
		    <div class="col"><img src="/resources/img/sidebar.png"></div>
		    <div class="col">탈퇴회원<br>1</div>
		    <div class="col"><img src="/resources/img/sidebar.png"></div>
		    <div class="col-2" style="text-align:left;">&nbsp;&nbsp;휴면회원<br>&nbsp;&nbsp;&nbsp;&nbsp;1</div>
		</div>

	<br><br>
	
	<!-- 검색 -->
		<div class="row row-cols-1">
			<table align="center" class="table col-10" border="0px">
				<tr>
					<td style="border:none;">
						<div style="display: inline-block; margin: 0 5px;  float: left;">
						<h4 align="center">회원리스트</h4>
						</div>
						<!-- div 오른쪽 정렬 -->
						<div style="display: inline-block; margin: 0 5px;  float: right;">
						<form action="/admin/search.kh" method="get" >
							<div style= "display: inline-block">
								<select class="form-select" name="searchCondition" >
									<option <c:if test="${searchCondition eq 'all'}">selected</c:if> value="all">전체</option>
									<option <c:if test="${searchCondition eq 'name'}">selected</c:if> value="name">이름</option>
									<option <c:if test="${searchCondition eq 'id'}">selected</c:if> value="id">아이디</option>
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
						<th>No</th>
						<th>이름</th>
						<th>아이디</th>
						<th>땅콩</th>
						<th>상태</th>
						<th>가입일</th>
					</tr>
			<c:if test="${!empty mList }">
				<c:forEach items="${mList }" var="member" varStatus="i">
					<tr>
						<td>${i.count }</td>
						<td>
							<a href="/admin/memberDetailView.kh?memberId=${member.memberId }&page=${bPage.currentPage }" style="color: black">${member.mNickname}</a>
						</td>
						<td>
							<a href="/admin/memberDetailView.kh?memberId=${member.memberId }&page=${bPage.currentPage }" style="color: black">${member.memberId }</a>
						</td>
						<td>${member.mPoint }</td>
						<td>
							<c:if test="${member.deleteYN eq 'N' }">회원</c:if>
							<c:if test="${member.deleteYN eq 'Y' }">탈퇴</c:if>
						</td>
						<td>
							${member.joinDate }
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty mList }">
				<tr>
					<td colspan="6" align="center"><b>데이터가 존재하지 않습니다.</b></td>
				</tr>
			</c:if>
				<tr align="center" height="20">
		            <td colspan="6" style="border:none;">
					    <!--  페이징 영역 -->
						<article id="page-area">
			
							<!-- 이전 페이지 출력 -->
							<c:if test="${bPage.startNavi != 1 && bPage.startNavi > 0  }">
								<span class="prev"> <a
									href="/admin/adminListView.kh?page=${bPage.startNavi-1 }"> < </a>
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
									<span class="pages"> <a href="/admin/adminListView.kh?page=${p }">${p }</a>
									</span>
								</c:if>
			
							</c:forEach>
							<!-- 다음 페이지 출력 -->
							<c:if test="${bPage.endNavi ne bPage.maxPage  }">
								<span class="next"> <a
									href="/admin/adminListView.kh?page=${bPage.endNavi+1 }"> > </a>
								</span>
							</c:if>
			
			
						</article>
						<!-- 페이징 영역 종료 -->
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