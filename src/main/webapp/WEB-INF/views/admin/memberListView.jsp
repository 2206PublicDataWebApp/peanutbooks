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
</style> 
<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->


<!-- main contents start -->
<main>
	<section class="container">
	<!-- 세부페이지 head 시작 -->
		<!-- 세부페이지 큰 제목 -->
		<div class="container text-center">
			<div class="row row-cols-1">
				<div class="col" id="colText" style="background-color: #5e5e5e; color: white; height:45px; vertical-align: middle;">회원리스트</div>
			</div>
		<!-- 세부페이지 큰 제목 끝 -->
		<br>
		<hr>
			<!-- 세부 메뉴 시작 -->
			<ul class="mainUl">
	    		<li class="mainLi"><div style="text-align:center">
			    		<a href="/admin/adminListView.kh?code=all">전체회원<br>${totalCount }</a>
			    	</div>
			    </li>
			    <li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/admin/adminListView.kh?code=today">오늘가입<br>${todayCount }</a>
			    	</div>
				</li>
				<li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/admin/adminListView.kh?code=del">탈퇴회원<br>${deleteCount }</a>
			    	</div>
				</li>
	 		</ul>
	 		<!-- 세부 메뉴 끝 -->
		<hr>
		<br><br>
		<!-- 세부페이지 head 시작 -->
		
		<!-- 검색 -->
			<div class="row row-cols-1">
				<table align="center" class="table col-6" style="width:80%";>
					<tr>
						<td style="border:none;">
							<div style="display: inline-block; margin: 0 5px;  float: left;">
							<h4 align="center">회원리스트</h4>
							</div>
							<!-- div 오른쪽 정렬 -->
							<div style="display: inline-block; margin: 0 5px;  float: right;">
							<form action="/admin/MemberSearch.kh" method="get" >
								<div style= "display: inline-block">
									<select class="form-select" name="searchCondition" >
										<option value="all" <c:if test="${searchCondition eq 'all'}">selected</c:if>>전체</option>
										<option value="name" <c:if test="${searchCondition eq 'name'}">selected</c:if>>닉네임</option>
										<option value="id" <c:if test="${searchCondition eq 'id'}">selected</c:if>>아이디</option>
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
				<table align="center" class="table col-6" style="width:80%";>
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
			            </td>
			        </tr>
				</table>
			</div>
		</div>
	<br>
	<hr>
	</section>
</main>
<!-- main contents End -->



<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>