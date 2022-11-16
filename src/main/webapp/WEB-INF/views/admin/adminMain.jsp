<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 홈</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel="stylesheet" href="/resources/css/admin/main/css.css" ></link>
</head>

<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
	<div class="board_wrap">
	<!-- 세부페이지 body 시작 -->
		<!-- 도서 현황 -->
		<div class="main_title">
		   <h6>도서 리스트</h6>        
		</div>
		<!-- 세부 메뉴 시작 -->
		<div class="sub_menu">
		     <ul class="amount">
		        <li>
		          <div>
		            <div class="contents1">
		            	<a href="/admin/approveYN.kh">전체도서</a>
		            </div>
		            <div class="result"><a href="/admin/approveYN.kh">${allBooks }</a></div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1">
		            	<a href="/admin/approveYN.kh?checkPermission=Y&step=date">승인도서</a>
				    	</div>
		            <div class="result">
		            	<a href="/admin/approveYN.kh?checkPermission=Y&step=date">${approveYes }</a>
				    </div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1">
		            	<a href="/admin/approveYN.kh?checkPermission=N&step=number">보류도서</a>
				    </div>
		            <div class="result">
		            	<a href="/admin/approveYN.kh?checkPermission=N&step=number">${approveNo }</a>
		            </div>
		          </div>
		        </li>
		        <li>
		          <div>
		            <div class="contents1">
		            	<a href="/admin/reApproveList.kh">재승인도서</a>
		            </div>
		            <div class="result">
		            	<a href="/admin/reApproveList.kh">${reApproveBooks }</a>
		            </div>
		          </div>
		        </li>
		     </ul>
	     </div>
		<hr>
		<!-- 도서 현황 End-->
	<!-- amount end -->
	<br>
			  
	<!-- 문의 게시판  -->
		<div class="main_title">
		   <h6>1:1문의 게시판</h6>        
		</div>
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
	<!-- amount end -->
	
	<!-- 리스트 출력 제목 & 검색 폼 -->
	      <div class="board_list_wrap">
	          <div class="board_list">
		 		<!-- 세부 메뉴 끝 -->
				<table class="t1">
					<thead>
						<tr>
							<th>작성자</th>
							<th>문의유형</th>
							<th>제목</th>
							<th>상태</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty qList }">
							<c:forEach items="${qList }" var="qna" varStatus="j">
								<tr>
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
					</tbody>
				</table>
		   </div>
		</div>
	<br><br>
	<!-- 문의 게시판 End -->
			
	<!-- 회원관리 게시판  -->
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
	<!-- amount end -->
		<div class="board_list_wrap">
	    	<div class="board_list">
				<table class="t2">
					<thead>
						<tr>
							<th>닉네임</th>
							<th>아이디</th>
							<th>땅콩</th>
							<th>상태</th>
							<th>가입일</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty mList }">
							<c:forEach items="${mList }" var="member" varStatus="k">
								<tr>
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
					</tbody>
				</table>
			</div>
		</div>       
	<br><br>
	<!-- 회원관리 게시판 End -->

	<!-- 작가 정산 게시판  -->
		 <div class="main_title">
			 <h6>작가 정산 리스트</h6>        
		</div>
		<div class="board_list_wrap">
	    	<div class="board_list">
				<table class="t3">
					<thead>
						<tr>
							<th>접수번호</th>
							<th>아이디</th>
							<th>정산금액</th>
							<th>지급은행</th>
							<th>승인여부</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty wrList }">
							<c:forEach items="${wrList }" var="pay" varStatus="l">
							<tr>
								<td>${pay.wrpayNo }</td>
								<td>${pay.memberId }</td>
								<td>${pay.payment }</td>
								<td>${pay.bankName }</td>
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
					</tbody>
				</table>
				<br>
			</div>
		</div>
		<!-- 작가 정산 게시판 End -->
		
	</div>
</main>
<!-- main contents End -->


<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>