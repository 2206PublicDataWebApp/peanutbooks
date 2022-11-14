<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 홈</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/admin/css.css" ></link>
</head>

<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
	<div class="board_wrap">
	<!-- 세부페이지 body 시작 -->
		<div class="container text-center">
		 	<div class="row row-cols-1">
		 	<br><br>
		 	  <!-- 도서 현황 -->
		 		<p style="color: #5F6F94; font-size:28px; font-family:malgun gothic; text-align: center;">[ 도서 현황 ]</p>
				<hr>
					<!-- <div id="title">전체 시리즈 목록</div> -->
					<ul class="mainUl">
			    		<li class="mainLi"><div style="text-align:center">
					    		<a href="/admin/approveYN.kh">전체도서<br>
					    		${allBooks }</a>
					    	</div>
					    </li>
					    <li class="mainLi">
					    	<div class="col"><img src="/resources/img/sidebar.png"></div>
					    </li>
			    		<li class="mainLi">
			    			<div style="text-align:center">
					    		<a href="/admin/approveYN.kh?checkPermission=Y&step=date">승인도서<br>
					    		${approveYes }</a>
					    	</div>
						</li>
						<li class="mainLi">
					    	<div class="col"><img src="/resources/img/sidebar.png"></div>
					    </li>
			    		<li class="mainLi">
			    			<div style="text-align:center">
					    		<a href="/admin/approveYN.kh?checkPermission=N&step=number">보류도서<br>
					    		${approveNo }</a>
					    	</div>
						</li>
						<li class="mainLi">
					    	<div class="col"><img src="/resources/img/sidebar.png"></div>
					    </li>
			    		<li class="mainLi">
			    			<div style="text-align:center">
					    		<a href="/admin/reApproveList.kh">재승인도서<br>
					    		${reApproveBooks }</a>
					    	</div>
			    		</li>
			 		</ul>
				<hr>
				<br><br>
		 		</div>
			  
			  <!-- 문의 게시판  -->
			<div class="row row-cols-1">
		      	<p style="color: #8ba525; font-size:28px; font-family:malgun gothic; text-align: center;">[ 문의 게시판 ]</p>
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
					<table class="table col-7 table-striped table-hover">
						<tr>
							<th class="col-1">번호</th>
							<th class="col-1">작성자</th>
							<th class="col-1">문의유형</th>
							<th class="col-2">제목</th>
							<th class="col-1">상태</th>
							<th class="col-1">작성일</th>
						</tr>
					<c:if test="${!empty qList }">
						<c:forEach items="${qList }" var="qna" varStatus="j">
						<tr>
							<td>${qna.qnaNo }${j.count }</td>
							<td>${qna.memberId }</td>
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
							<td><a href="/admin/aqnaDetailView.kh?qnaNo=${qna.qnaNo }" style="color: black">${qna.qnaTitle }</a></td>
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
				</table>
			</div>
			<br><br>
			<!-- 문의 게시판  -->
			
			<!-- 회원관리 게시판  -->
			<div class="row row-cols-1">
			<br><br>
	      	<p style="color: #43b2ea; font-size:28px; font-family:malgun gothic; text-align: center;">[ 회원관리 ]</p>
		      	<!-- 세부 메뉴 시작 -->
		      	<hr>
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
				<table class="table col-6 table-striped table-hover">
					<tr>
						<th class="col-1">번호</th>
						<th class="col-1">닉네임</th>
						<th class="col-1">아이디</th>
						<th class="col-1">땅콩</th>
						<th class="col-1">상태</th>
						<th class="col-1">가입일</th>
					</tr>
				<c:if test="${!empty mList }">
					<c:forEach items="${mList }" var="member" varStatus="k">
					<tr>
						<td>${k.count }</td>
						<td>${member.mNickname }</td>
						<td><a href="/admin/memberDetailView.kh?memberId=${member.memberId }">${member.memberId }</a></td>
						<td>${member.mPoint }</td>
						<td>
							<c:if test="${member.deleteYN eq 'N' }">
								<b style="color: #2d532c;">회원</b>
							</c:if>
							<c:if test="${member.deleteYN eq 'Y' }">
								<b style="color: #d21853;">탈퇴</b>
							</c:if>
						</td>
						<td>${member.joinDate }</td>
					</tr>
					</c:forEach>
				</c:if>
				</table>
			</div>       
			<br><br>
	 <!-- 회원관리 게시판  --> 
			<!-- 도서 게시판  -->
			   <%--  <div class="col-lg-6 col-md-12">
			      <div class="p-4 border bg-light">
			      	<p style="color: #d21853; font-size:28px; font-family:malgun gothic; text-align: center;">[ 레시피 게시판 ]</p>
						<table class="table table-striped table-hover">
							<tr>
								<th>번호</th>
								<th>작성자</th>
								<th>책제목</th>
								<th>시리즈</th>
								<th>승인여부</th>
								<th>작성일</th>
							</tr>
						<c:if test="${!empty osList }">
							<c:forEach items="${osList }" var="os" varStatus="i">
								<td>${os.approveNo }</td>
								<td>${os.memberId }</td>
								<td>${os.bookTitle }</td>
								<td>${os.title }</td>
								<td>
									<c:if test="${os.checkPermission eq 'Y' }">
										<b style="color: #2d532c;">승인</b>
									</c:if>
									<c:if test="${os.checkPermission eq 'N' }">
										<b style="color: #d21853;">보류</b>
									</c:if>
								</td>
							</tr>
							</c:forEach>
						</c:if>
					</table>
			      </div>
			    </div> --%>
			 <!-- 문의 게시판  -->   
		
			<div class="row row-cols-1">  
			 <!-- 작가 정산 게시판  -->
			 <br><br><br>
		      	<p style="color: #ea61bc; font-size:28px; font-family:malgun gothic; text-align: center;">[ 작가 정산 리스트 ]</p>
					<table class="table col-6 table-striped table-hover">
						<tr>
							<th class="col-1">접수번호</th>
							<th class="col-1">아이디</th>
							<th class="col-1">책번호</th>
							<th class="col-1">정산금액</th>
							<th class="col-1">지급은행</th>
							<th class="col-1">승인여부</th>
						</tr>
					<c:if test="${!empty wrList }">
						<c:forEach items="${wrList }" var="pay" varStatus="l">
						<tr>
							<td>${pay.wrpayNo }${l.count }</td>
							<td>${pay.memberId }</td>
							<td>${pay.payment }</td>
							<td>${pay.bankName }</td>
							<td>${pay.bankStatus }</td>
							<td>
								<c:if test="${pay.bankStatus eq 'N' }">
									<b style="color: #2d532c;">승인</b>
								</c:if>
								<c:if test="${pay.bankStatus eq 'Y' }">
									<b style="color: #d21853;">승인완료</b>
								</c:if>
							</td>
						</tr>
						</c:forEach>
					</c:if>
				</table>
				<br><br>
			</div>
			<!-- 커뮤니티 게시판  -->
			
		</div>
	</div>
</main>
<!-- main contents End -->


<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>