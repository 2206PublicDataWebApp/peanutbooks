<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>땅콩북스</title>
<link rel="stylesheet" href="/resources/css/header-style.css">
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
  <script src="../resources/js/jquery-3.6.0.min.js" ></script>


</head>

<body>
	<header>

		<div class="container">
			<div class="row" id="header-area">
				<div class="col-md-1 col-3">
					<img src="/resources/img/logo/logo.png" id="logo" alt="" id="logo">
				</div>
				<div class="col-md-7 d-md-block d-none">
					<ul>
						<li>이달의 추천</li>
						<li>도서</li>
						<li>피넛 오리지널</li>
					</ul>


                </div>
                <div class="col-md-4 col-7" id="header-menu-icon">
                    <img src="/resources/img/header/icons8-search (2).png" alt="" id="search-icon">
                    <img src="/resources/img/header/icons8-book (3).png" alt="" id="book-icon">
                    <img src="/resources/img/header/icons8-peanut-48 (6).png" alt="" id="p-icon">
                    <img src="/resources/img/header/icons8-user.png" alt="" id="user-icon"
                    <c:if test="${loginMember.adminYN=='Y' }">style="display:none;"</c:if>
                    >
                    <!--관리자 체크해서 나타남-->
                    <img src="/resources/img/header/icons8-monarch-48.png" alt="" id="admin-icon"        
                     <c:if test="${loginMember.adminYN=='N' }">style="display:none;"</c:if>
                    >
                </div>
                <div class="d-md-none d-block col-1" id="togglemenu">
                    <div>
                        <img src="/resources/img/header/icons8-menu-60.png">    
                    </div>

				</div>


			</div>



		</div>

		<div id="menu-bar">
			<ul>
				<li>이달의 추천</li>
				<li>도서</li>
				<li>피넛 오리지널</li>
			</ul>
		</div>

		<!--회원 정보 툴팁-->
		<div class="container" id="mypage-tooltip-area">
			<div class="row" id="icon-tooltip">
				<div class="col-md-2" id="mypage">
					<div id="name-space">
                    ${sessionScope.loginMember.mNickname}님 환영합니다!
                </div>
                <c:if test="${!empty sessionScope.lastDate}" >
	                <div id="subscribe">
	                	    구독<br>
	                    ${sessionScope.lastDate } 까지
	                </div>
                </c:if>   
					<div id="mypage-icon-area" class="row">
						<div class="col-4">
							<img src="/resources/img/header/icons8-notification-64.png"
								alt=""> <br>알림
						</div>
						<div class="col-4">
							<img src="/resources/img/header/icons8-gear-50.png" alt="">
							<br>정보수정
						</div>
						<div class="col-4">
							<a href="/book/writerMenu.do"> <img
								src="/resources/img/header/icons8-write-48.png" alt=""> <br>등록
								도서
							</a>
						</div>

						<div class="col-4">
							<img src="/resources/img/header/icons8-headphone-64.png" alt="">
							<br>1:1문의
						</div>
						<div class="col-4">
							<img src="/resources/img/header/icons8-chat-50.png" alt="">
							<br>채팅상담
						</div>
						<div class="col-4">

							<img src="/resources/img/header/icons8-noticeboard-50.png" alt="">
							<br>공지사항
						</div>

					</div>
					<div id="logout-area">
						<button id="logout">로그아웃</button>
					</div>

				</div>

			</div>
		</div>


		<!--관리자 툴팁-->
		<div class="container" id="admin-tooltip-area">
			<div class="row" id="admin-icon-tooltip">
				<div class="col-md-2" id="admin">
					<div id="name-space">관리자 입니다.</div>
					<div id="subscribe">
						구독<br> 2022년 11월 30일까지
					</div>
					<div id="admin-icon-area" class="row">
						<div class="col-4">
							<img src="/resources/img/header/icons8-notification-64.png"
								alt=""> <br>알림
						</div>
						<div class="col-4">
							<img src="/resources/img/header/icons8-gear-50.png" alt="">
							<br>정보수정
						</div>
						<div class="col-4">
							<a href="/book/writerMenu.do"> <img
								src="/resources/img/header/icons8-write-48.png" alt=""> <br>등록
								도서
							</a>
						</div>

						<div class="col-4">
							<img src="/resources/img/header/icons8-chat-50.png" alt="">
							<br>채팅상담
						</div>
						<div class="col-4">

							<img src="/resources/img/header/icons8-noticeboard-50.png" alt="">
							<br>공지사항
						</div>

						<div class="col-4">

							<a href="/adminMain.kh"><img src="/resources/img/header/icons8-laptop-60.png" alt=""></a>
                        <br>관리자
                    </div>

					</div>
					<div id="logout-area">
						<button id="logout">로그아웃</button>
					</div>


				</div>

			</div>
		</div>





      <div class="container" id="point-tooltip-area">
        <div class="row" id="point-icon-tooltip">
            <div class="col-md-2" id="point">
                <div id="name-space">
                    ${sessionScope.loginMember.mNickname}님
                </div>
                <div id="now-point">
                    ${sessionScope.loginMember.mPoint}땅콩
                </div>
                <div id="point-icon-area" class="row">
                    <div class="col-4">
                        <img src="/resources/img/header/icons8-page-52.png" alt="">
                        <br>이용내역
                    </div>
                    <div class="col-4" >                    	
                        <img src="/resources/img/header/icons8-android-l-battery-48.png" alt="" id="peanutIn">
                        <br>땅콩충전
                    </div>
                    <div class="col-4">                    	
                        <img src="/resources/img/header/icons8-change-48.png" alt="" onclick="peanutList('${sessionScope.loginMember.memberId}');">
                        <br>땅콩교환
                    </div>


							

					</div>


				</div>

			</div>
		</div>

	</header>

    <script src="/resources/js/headerJs.js"></script>
    <script type="text/javascript">
    
	 	/*결제 관련   */
		 $("#peanutIn").on("click",function(){
			 var mid="${sessionScope.loginMember.memberId}";
			 if(mid==''){
				 alert("로그인후 가능합니다");
			 }else{
				 location.href="/pay/start.kh";
			 };
		 })
   
    </script>
    

</body>

</html>