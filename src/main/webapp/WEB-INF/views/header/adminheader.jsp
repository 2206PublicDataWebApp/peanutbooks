<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>땅콩북스</title>
<link rel="stylesheet" href="/resources/css/admin-header-style.css">
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

</head>

<body>
	<header>

		<div class="container">
			<div class="row" id="header-area">
				<div class="col-md-1 col-3">
					<a href="/"><img src="/resources/img/logo/logo.png" id="logo" alt="" id="logo"></a>
				</div>
				<div class="col-md-7 d-md-block d-none">
					<ul>
						<li><a href="/admin/qnaList.kh">게시판</a></li>
						<li><a href="/admin/bookApprove/bookApproveView.kh">도서</a></li>
						<li>피넛</li>
						<li>회원</li>
						<li id="admintext">관리자홈</li>
					</ul>

				</div>
				<div class="col-md-4 col-7" id="header-menu-icon">
					<img src="/resources/img/header/icons8-search (2).png" alt=""
						id="search-icon"> 
					<img
						src="/resources/img/header/icons8-book (3).png" alt=""
						id="book-icon" onclick="location.href='/book/mybooks.do'"> 
					<img
						src="/resources/img/header/icons8-peanut-48 (6).png" alt=""
						id="p-icon"> 
					<img
						src="/resources/img/header/icons8-user.png" style="display: none;"alt="" id="user-icon">
					<!--관리자 체크해서 나타남-->
					<img src="/resources/img/header/icons8-monarch-48.png" alt=""
						id="admin-icon" style="display:;">

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
				<li>게시판</li>
				<li><a href="/admin/bookApprove/bookApproveView.kh">도서</a></li>
				<li>피넛</li>
				<li>회원</li>
			</ul>
		</div>

		<!--회원 정보 툴팁-->
		<div class="container" id="mypage-tooltip-area">
			<div class="row" id="icon-tooltip">
				<div class="col-md-2" id="mypage">
					<div id="name-space">이용자님 환영합니다!</div>
                  	<div id="subscribe">
					 	<c:if test="${!empty sessionScope.lastDate}">
                    		 구독<br> ${sessionScope.lastDate } 까지
              			 </c:if>
                 	 </div>

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

							<img src="/resources/img/header/icons8-write-48.png" alt="">
							<br>등록 도서
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
						<button id="logout" onclick="location.href='/member/logout.pb';">로그아웃</button>
					</div>

				</div>

			</div>
		</div>


		<!--관리자 툴팁-->
		<div class="container" id="admin-tooltip-area">
			<div class="row" id="admin-icon-tooltip">
				<div class="col-md-2" id="admin">
					<div id="name-space">${sessionScope.loginMember.mNickname} 관리자 입니다.</div>
                 	<div id="subscribe">
						 <c:if test="${!empty sessionScope.lastDate}">
	                     구독<br> ${sessionScope.lastDate } 까지
	                     </c:if>
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

							<img src="/resources/img/header/icons8-write-48.png" alt="">
							<br>등록 도서
						</div>

						<div class="col-4">
							<img src="/resources/img/header/icons8-chat-50.png" alt=""  onclick="chatManager();">
							<br>채팅상담
						</div>
						<div class="col-4">

							<a href="/notice/list.kh"><img src="/resources/img/header/icons8-noticeboard-50.png" alt=""></a>
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
					<div id="name-space">${sessionScope.loginMember.mNickname}님</div>
					<div id="now-point"></div>
					<div id="point-icon-area" class="row">
						<div class="col-4">
							<img src="/resources/img/header/icons8-page-52.png" alt="" onclick="peanutList('${sessionScope.loginMember.memberId}');">
							<br>이용내역
						</div>
						<div class="col-4">
							<img src="/resources/img/header/icons8-android-l-battery-48.png"
								alt=""  onclick="pay('${sessionScope.loginMember.memberId}');"> <br>땅콩충전
						</div>
						<div class="col-4">

							<img src="/resources/img/header/icons8-change-48.png" alt="" onclick="writerPay('${sessionScope.loginMember.memberId}');">
							<br>땅콩교환
						</div>

					</div>

				</div>

			</div>
		</div>

	</header>

	<script src="/resources/js/headerJs.js"></script>
	<script type="text/javascript">
	
	  //결제 관련   */
    function pay(memberId){                
       if(memberId==''){
          alert("로그인후 가능합니다");
       }else{
          location.href="/pay/start.kh";
       };
    }
    /*땅콩리스트  */
    function peanutList(memberId){                    
       if(memberId==''){
          alert("로그인후 가능합니다");
       }else{
          location.href="/peanut/listStart.kh?memberId="+memberId;
       };
    }
    
    
    //작가료 정산페이지 이동
   function writerPay(memberId){
       if(memberId==''){
          alert("로그인후 가능합니다");
       }else{
          location.href="/writer/writerStart.kh?memberId="+memberId;
       };
    }

      
   function chatbtnSuccess(memberId) {
      var windo = "status=no ,toolbar=no,scrollbars=no, menubar=no,resizable=no,titlebar=no,width=550,height=650";
      window.open("/consult/chatbefore.kh", "PopupWin",windo);
   }
   //채팅 상담리스트
   function chatManager(){ 
      location.href="/chat/move.kh";
   }

	
	</script>
</body>

</html>