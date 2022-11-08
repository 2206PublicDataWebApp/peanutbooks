<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세페이지</title>
<!-- jQuery, bootstrap  -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
		<!-- 세부페이지 head 끝 -->
	
			<!--  세부페이지 body -->
			<div class="row row-cols-1">
				<!-- 회원정보 수정 폼 -->
				<form action="/admin/memberModify.kh" method="post">
					<table align="center" class="table col-7" style="width:100%;">
						<input type="hidden" name="page" value="${page }">
						<tr>
							<th class="col-7" colspan="2" align="left" style="border:none";>${member.memberId }회원의 정보 수정</th>
						</tr>
						<tr>
							<th class="col-2" scope="col" align="center">닉네임</th>
							<td class="col-5"><input type="text" name="mNickname" class="form-control" value="${member.mNickname }"></td>
						</tr>
						<tr>
							<th class="col-1" scope="col" align="center">아이디</th>
							<td class="col-1"><input type="text" name="memberId" class="form-control" value="${member.memberId }" readonly></td>
						</tr>
						<tr>
							<th class="col-2" scope="col" align="center">비밀번호</th>
							<td class="col-5"><input type="text" name="memberPw" class="form-control" value="${member.memberPw }"></td>
						</tr>
						<tr>
							<th class="col-2" scope="col" align="center">이메일</th>
							<td class="col-5"><input type="text" name="mEmail" class="form-control" value="${member.mEmail }" readonly></td>
						</tr>
						<tr>
							<th class="col-2" scope="col" align="center">가입일</th>
							<td class="col-5"><input type="text" name="joinDate" class="form-control" value="${member.joinDate }" readonly></td>
						</tr>
						<tr>
							<th class="col-2">상태</th>
							<td class="col-5">
								<select name="deleteYN" class="form-select">
									<option value="N" <c:if test="${member.deleteYN eq 'N' }">selected</c:if>>회원</option>
									<option value="Y" <c:if test="${member.deleteYN eq 'Y' }">selected</c:if>>탈퇴</option>
								</select>
							</td>
						</tr>
						<tr>
							<th class="col-2" scope="col" align="center">회원포인트</th>
							<td class="col-5"><input type="text" name="mPoint" class="form-control" value="${member.mPoint }"></td>
						</tr>
						<tr>
							<th class="col-2" scope="col" align="center">월구독여부</th>
							<td class="col-5">
								<select name="subYN" class="form-select">
									<option value="N" <c:if test="${member.subYN eq 'N' }">selected</c:if>>X</option>
									<option value="Y" <c:if test="${member.subYN eq 'Y' }">selected</c:if>>O</option>
								</select>
							</td>
						</tr>
						<tr>
							<th class="col-2" scope="col" align="center">회원 종류</th>
							<td class="col-5">
								<select name="adminYN" class="form-select">
									<option value="N" <c:if test="${member.adminYN eq 'N' }">selected</c:if>>일반회원</option>
									<option value="Y" <c:if test="${member.adminYN eq 'Y' }">selected</c:if>>관리자</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="col-7" colspan="2" align="center" style="border:none;">
								<input type="submit" value="수정" class="btn btn-warning btn-sm">
								<input onclick="adminRemove('${member.memberId }', '${page}');" type="button" value="삭제" class="btn btn-warning btn-sm">
								<button type="button" onclick="backBtn()" class="btn btn-warning btn-sm">목록</button>
								<%-- <button type="button" onclick="location.href='/admin/adminListView.kh?page=${page}'" class="btn btn-warning btn-sm">목록</button> --%> 
							</td>
						</tr>
					</table>
				</form>
				<!-- 회원정보 수정 폼 끝 -->
			</div>
			<!--  세부페이지 body -->
		</div>	
	<hr>
	</section>
</main>
<!-- main contents End -->


<script>
	function backBtn() {
	    history.back();
	}

	function adminRemove(memberId, page) {
		event.preventDefault(); // 하이퍼링크 이동 방지
		if(confirm("게시물을 삭제하시겠습니까?")) {
			location.href="/admin/memberRemove.kh?memberId="+memberId+"&page="+page;
		}
	}

	function fnImgPop(url){
		var img = new Image();
		img.src = url;
		var img_width = img.width;
		var win_width = img.width+25;
		var img_height = img.height;
		var win = img.height+30;
		var OpenWindow = window.open('','_blank', 'width = '+img_width+', height = '+img_height+', menubars = no, scrollbars = auto');
		OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
	 }
</script>
<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>