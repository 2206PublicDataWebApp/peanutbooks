<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>땅콩북스</title>
<link rel="stylesheet" href="/resources/css/book/bookmain-style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

</head>

<body onload="printReply(${oBook.bookNo}, '${loginMember.memberId}',1);">
	<jsp:include page="../header/header.jsp" />

	<main>
		<section>
			<article class="container" id="fisrt-article">
				<div id="info-article" class="row">
					<div class="img-area col-md-6">
						<div class="inner">
						</div>
						<img src="/resources/bookImg/${oBook.coverRename}" alt="표지그림">
					</div>
					<div class="info-area col-md-6">
						<div id="title">${oBook.bookTitle }</div>
						<c:if test="${oBook.status == 'N '}">이 책은 관리자에 의해 삭제되었습니다.</c:if>
						<div id="wirter-text">작가 : ${oBook.memberNickName }</div>


						<div class="tag">
							<c:if test="${hTag.hashTag1 != 'none' }">
							#${hTag.hashTag1 }<br>
							</c:if>
							<c:if test="${hTag.hashTag2 != 'none'}">
							#${hTag.hashTag2 }<br>
							</c:if>
							<c:if test="${hTag.hashTag3 != 'none'}">
							#${hTag.hashTag3 }</c:if>
						</div>


						<div class="openbutton">
						
						<!-- 상세 열람 버튼 -->
						
						
						
						
							<button id="bookopenbutton" onclick="window.open('/book/bookStep.do?bookNo=${oBook.bookNo}&category=origin')">읽어보기</button>
						
						
						
						
						
						</div>
						<div class="userbutton container">
							<div class="row">
								<div class="col-4" onclick="addMybooks('origin',${oBook.bookNo})">
									<img src="/resources/img/book/icons8-bookmark-52.png" alt="">
									
									<br> 
									<span id="mybooksButton">
									<c:if test="${library=='in'}">내 서재 취소</c:if>
									<c:if test="${library!='in'}">내 서재 등록</c:if>
									</span>
								</div>
								<div class="col-4">
									<a href="#reply-text">
										<img src="/resources/img/book/icons8-reply-48.png" alt="">
										<br> 감상쓰기
									</a>
								</div>
								<div class="col-4">
									<a href="#star-area">
										<img src="/resources/img/book/icons8-star-52.png" alt="">
										<br> 별점주기
									</a>
								</div>
							</div>
						</div>
						<div id="plusbutton">
						
						<!-- 작가일때만 보이는 버튼 -->
						<c:if test="${loginMember.memberId == oBook.memberId}">
							<button onclick="registOriNext(${oBook.bookNo},${nextSeriesNo});">다음편 쓰기</button>
						</c:if>
						
						<!-- 관리자일때만 보이는 버튼 -->
						<c:if test="${loginMember.adminYN == 'Y' }" >
							<button  onclick="removeOriginBook(${oBook.bookNo});">삭제하기</button>
							<button  onclick="location.href='/book/OriBookModifyView.do?bookNo=${oBook.bookNo}';">수정하기</button>
						</c:if>
							
						</div>
					</div>
				</div>

			</article>
			<hr>
			<article class="container">
				<div class="info-title">
					<div class="sub-title">작품소개</div>
					<div class="sub-info">${oBook.bookInfo }</div>
				</div>
				<div class="step-title">
					<div class="sub-title">목차</div>
					<div class="sub-info">

						<!-- 목차 반복문 시작 없으면 출력되지 않음-->
						<c:if test="${osList != null }">
							<ol>

								<c:forEach items="${osList }" var="oSeries" varStatus="i">
	
								<c:if test ="${oSeries.checkPermission == 'Y'}"> <!-- 책이 승인되었을때만 보임 -->
									<li><!-- 삭제 여부 체크 -->
									<c:if test="${oSeries.status == 'N'}"> 관리자에 의해 삭제되었습니다.</c:if>
									<c:if test="${oSeries.status == 'Y'}"> ${oSeries.title }</c:if>
									<!-- 작성자 일때만 수정버튼 보임 -->
									<c:if test="${loginMember.memberId == oBook.memberId}">
										<button onclick="location.href='/book/oriSeriesModifyView.do?bookNo=${oBook.bookNo}&seriesNo=${i.index +1 }'">수정</button>
									</c:if>
										<!--  관리자 일때만 삭제 버튼 보임 -->
									<c:if test="${loginMember.adminYN == 'Y'}">
										<button onclick="removeORiSeries(${oBook.bookNo},${i.index +1 })">삭제</button>
									</c:if>
									
									
									
									</li>
								</c:if>
								<c:if test ="${oSeries.checkPermission == 'N'}"><!-- 책이 승인되지 않았을땐 관리자에게만 보임 -->
									<c:if test="${loginMember.memberId == oBook.memberId||loginMember.adminYN == 'Y'}">
											<li><!-- 삭제 여부 체크 -->
										<c:if test="${oSeries.status == 'N'}"> 관리자에 의해 삭제되었습니다.</c:if>
										<c:if test="${oSeries.status == 'Y'}"> ${oSeries.title } : 이 시리즈는 승인되지 않았습니다</c:if>
										<!-- 작성자 일때만 수정버튼 보임 -->
										<c:if test="${loginMember.memberId == oBook.memberId}">
											<button onclick="location.href='/book/oriSeriesModifyView.do?bookNo=${oBook.bookNo}&seriesNo=${i.index +1 }'">수정</button>
										</c:if>
										<!--  관리자 일때만 삭제 버튼 보임 -->
										<c:if test="${loginMember.adminYN == 'Y'}">
											<button onclick="removeORiSeries(${oBook.bookNo},${i.index +1 })">삭제</button>
										</c:if>
										
									</li>
									
									
									</c:if>
								</c:if>
									
									
									
									
									
								</c:forEach>

							</ol>
						</c:if>

						<!-- 목차 반복문 종료 -->

					</div>
				</div>

			</article>
			<article class="container" id="reply-area">
				<div id="star-area">
					<div class="sub-title">리뷰현황</div>
					<div class="sub-info row">
						<div class="star-area-1 col-md-6">
							<div class="star-title">사용자 평균(${oBook.scoreCount }명)</div>
							
							<div class="star-score" id="avr-score">
							 <c:if test="${oBook.score==0}">☆ ☆ ☆ ☆ ☆</c:if>
							 <c:if test="${oBook.score==1}">★ ☆ ☆ ☆ ☆</c:if>
							 <c:if test="${oBook.score==2}">★ ★ ☆ ☆ ☆</c:if>
							 <c:if test="${oBook.score==3}">★ ★ ★ ☆ ☆</c:if>
							 <c:if test="${oBook.score==4}">★ ★ ★ ★ ☆</c:if>
							 <c:if test="${oBook.score==5}">★ ★ ★ ★ ★</c:if>
							
							</div>
						</div>
						<div class="star-area-2 col-md-6">
							<div class="star-title">별점주기</div>
							<c:if test ="${star != null}">
							
							<div class="star-score" id="userStarScore" onclick="StarRemoveUser();">
								<c:if test="${star.score==0}">☆ ☆ ☆ ☆ ☆</c:if>
								<c:if test="${star.score==1}">★ ☆ ☆ ☆ ☆</c:if>
							 	<c:if test="${star.score==2}">★ ★ ☆ ☆ ☆</c:if>
								<c:if test="${star.score==3}">★ ★ ★ ☆ ☆</c:if>
								<c:if test="${star.score==4}">★ ★ ★ ★ ☆</c:if>
							 	<c:if test="${star.score==5}">★ ★ ★ ★ ★</c:if>
							 </div>
							
							</c:if>
						
							
							<div class="star-score" id="star-score" <c:if test ="${star != null}"> style="display:none;" </c:if> >
								<span class="star-none" id="star1">☆</span>  <span class="star-fill"  id="star1-fill">★</span>
								<span class="star-none"  id="star2">☆</span>  <span class="star-fill"  id="star2-fill">★</span>
								<span class="star-none"  id="star3">☆</span>  <span class="star-fill"  id="star3-fill">★</span>
								<span class="star-none"  id="star4">☆</span>  <span class="star-fill"  id="star4-fill">★</span>
								<span class="star-none"  id="star5">☆</span>  <span class="star-fill"  id="star5-fill">★</span>
		
							</div>
							
							
						</div>

					</div>
				</div>
				<div id="reply">
					<div class="sub-title">독자 감상(<span id="replyLength">0</span>)</div>
					<div class="reply">

						<div class="container mt-1 view-relply">
							<!--리플 한게 보이는 영역-->

<!-- 							<div class="row  d-flex justify-content-center repleOne">

								<div class="card p-3">

									<div class="d-flex justify-content-between align-items-center">

										<div class="user d-flex flex-row align-items-center">
											<span class="col-3 d-inline-block text-truncate">
												<small class="font-weight-bold username">사용자이름 길면 잘림</small>
											</span> 
											<span class="col-11">
												<small class="font-weight-bold">
													저는 이 책이 매우 마음에 들어요! cool
												</small>
											</span>

										</div>
									</div>
									<div class="mt-2">

										<div class="reply row">
											<div class="col-6">
												<span class="modify-del-button"> <small>삭제</small> <small>수정</small>
												</span>
											</div>
											<div class="text-truncate col-6 date">
												<small>2 days ago</small>
											</div>
										</div>

									</div>


	-->							</div>
 

							</div>

						</div>
						<!--리플 한게 보이는 영역 끝-->
						<!-- 페이징영역 -->
						<div id="page" class =mt-1></div>

						<div class="container mt-3 view-relply">

							<div class="row">
								<div class="col-md-11 col-9">
									<textarea name="reContents" id="reply-text" rows="3"></textarea>
								</div>
								<div class="col-md-1 col-3 reply-button-area">
									<button id="reply-button" onclick="replyRegist(${oBook.bookNo },'${loginMember.memberId}')">등록</button>
								</div>

							</div>
						
						</div>
					</div>
				</div>
			</article>
			<div id="listbutton">
			<button onclick="history.go(-1);">이전으로</button>
			</div>
		</section>
	</main>

	<jsp:include page="../footer/footer.jsp" />
</body>
<script type="text/javascript">

var bookNo = ${oBook.bookNo};
var userId = '${loginMember.memberId}';
var admin ='${loginMember.adminYN}'
</script>
<script src="/resources/js/book/bookmain.js"></script>

</html>