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
	@keyframes blink-effect {
		50% {
		    opacity: 0;
		}
	}
	.blink {
	 	animation: blink-effect 1s step-end infinite;
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
	#colText a {
		width:100%;
		height:100%;
		color: white; 
		display:inline-block;
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
			<div class="row row-cols-2">
			   <div class="col" id="colText" style="background-color: #5e5e5e; height:45px; vertical-align: middle;"><a href="/notice/list.kh"><p style="color:white";>공지사항 리스트</p></a></div>
			   <div class="col" id="colText" style="background-color: #c9c9c9; height:45px; vertical-align: middle;"><a href="/notice/writeView.kh">공지사항 작성</a></div>        
			</div>
		<!-- 세부페이지 큰 제목 끝 -->
		<br>
		<hr>
			<!-- <div id="title">전체 시리즈 목록</div> -->
			<ul class="mainUl">
	    		<li class="mainLi"><div style="text-align:center">
			    		<a href="/notice/list.kh">전체공지사항<br>
			    		${totalBoard }
			    		</a>
			    	</div>
			    </li>
			    <li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/notice/list.kh?nStatus=Y">게시<br>
			    		${showBoard }</a>
			    	</div>
				</li>
				<li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/notice/list.kh?nStatus=N">보류<br>
			    		${hideBoard }</a>
			    	</div>
				</li>
	 		</ul>
		<hr>
		</div>
		
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
						<td class="col-8" style="border:none;" colspan="5" align="right">
							<a href="/notice/list.kh" style="color: black"> 전체 | </a>
							<a href="/notice/categoryCount.kh?noticeCategory=notice" style="color: black"> 공지 |</a>
							<a href="/notice/categoryCount.kh?noticeCategory=update" style="color: black"> 업데이트 |</a>
							<a href="/notice/categoryCount.kh?noticeCategory=event" style="color: black"> 이벤트 |</a>
							<a href="/notice/categoryCount.kh?noticeCategory=info" style="color: black"> 안내 </a>
						</td>
					</tr>
					<tr>
						<td>번호</td>
						<td>유형</td>
						<td>제목</td>
						<td>작성일</td>
						<td>노출</td>
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
							<td class="col-4" align="left">
								<div style= "display: inline-block"><a href="/notice/noticeDetailView.kh?noticeNo=${notice.noticeNo }&page=${bPage.currentPage }" style="color: black">${notice.noticeTitle }</a></div>
								<c:if test="${notice.nStatus eq 'Y'}"><div style= "display: inline-block"><p class="blink" style="color: #ed436d; font-size:0.8em; font-weight: bold;">게시중</p></div></c:if>
								
							</td>
							<td class="col-1">${notice.nCreateDate }</td>
							<td class="col-1">
							<c:if test="${notice.nStatus eq 'Y'}">
								<button type="button" onclick="location.href='/notice/chooseNotice.do?noticeNo=${notice.noticeNo}&nStatus=N&page=${bPage.currentPage}'" class="btn btn-danger btn-sm" style="width:40pt;height:20pt;">보류</button>
							</c:if>
							<c:if test="${notice.nStatus eq 'N'}">
								<button type="button" onclick="location.href='/notice/chooseNotice.do?noticeNo=${notice.noticeNo}&nStatus=Y&page=${bPage.currentPage}'" class="btn btn-success btn-sm" style="width:40pt;height:20pt;">게시</button>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty nList }">
					<tr>
						<td colspan="5" align="center"><b>데이터가 존재하지 않습니다.</b></td>
					</tr>
				</c:if>
					<tr align="center" height="20">
			            <td colspan="5" style="border:none;">
			       
			       <c:if test="notice.noticeCategory"></c:if>
						    <!--  페이징 영역 -->
							<article id="page-area">
				
								<!-- 이전 페이지 출력 -->
								<c:if test="${bPage.startNavi != 1 && bPage.startNavi > 0  }">
									<span class="prev"> <a
										href="/notice/categoryCount.kh?noticeCategory=${notice.noticeCategory }&page=${bPage.startNavi-1 }"> [이전] </a>
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
										<span class="pages"> <a href="/notice/categoryCount.kh?page=${p }&noticeCategory=${noticeCategory}">${p }</a>
										</span>
									</c:if>
				
								</c:forEach>
								<!-- 다음 페이지 출력 -->
								<c:if test="${bPage.endNavi ne bPage.maxPage  }">
									<span class="next"> <a
										href="/notice/categoryCount.kh?page=${bPage.endNavi+1 }&noticeCategory=${noticeCategory}"> [다음] </a>
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