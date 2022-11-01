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
</style> 
<body>
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
<br><br>	
<div class="container">

	<h3 align="center">회원 상세 페이지</h3>
	<br>
	<div class="container text-center">
		<div class="row row-cols-1">
			<div class="col" style="background-color: #5e5e5e; color: white; height:45px; vertical-align: middle;"><a href="admin/adminListView.kh">회원리스트</a></div>
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

	<br>
		<div class="row row-cols-1">
			<table align="center" class="table col-10">
				<tr>
					<th colspan="2" align="left">${member.memberId }회원의 상세정보</th>
				</tr>
				<tr>
					<th class="col-2" scope="col" align="center">이름</th>
					<td><input type="text" name="mNickname" class="form-control" value="${member.mNickname }" readonly></td>
				</tr>
				<tr>
					<th class="col-2" scope="col" align="center">아이디</th>
					<td><input type="text" name="memberId" class="form-control" value="${member.memberId }" readonly></td>
				</tr>
				<tr>
					<th class="col-2" scope="col" align="center">비밀번호</th>
					<td><input type="text" name="memberPw" class="form-control" value="${member.memberPw }" readonly></td>
				</tr>
				<tr>
					<th class="col-2" scope="col" align="center">이메일</th>
					<td><input type="text" name="mEmail" class="form-control" value="${member.mEmail }" readonly></td>
				</tr>
				<tr>
					<th class="col-2" scope="col" align="center">가입일</th>
					<td><input type="text" name="joinDate" class="form-control" value="${member.joinDate }" readonly></td>
				</tr>
				<tr>
					<th>상태</th>
					<td>
						<c:if test="${member.deleteYN eq 'N' }">
							<input type="text" name="deleteYN" class="form-control" value="회원" readonly>
						</c:if>
						<c:if test="${member.deleteYN eq 'Y' }">
							<input type="text" name="deleteYN" class="form-control" value="탈퇴" readonly>
						</c:if>
					</td>
				</tr>
				<tr>
					<th class="col-2" scope="col" align="center">회원포인트</th>
					<td><input type="text" name="mPoint" class="form-control" value="${member.mPoint }" readonly></td>
				</tr>
				<tr>
					<th class="col-2" scope="col" align="center">월구독여부</th>
					<td>
						<c:if test="${member.subYN eq 'Y' }">
							<input type="text" name="subYN" class="form-control" value="O" readonly>
						</c:if>
						<c:if test="${member.subYN eq 'N' }">
							<input type="text" name="subYN" class="form-control" value="X" readonly>
						</c:if>
					</td>
				</tr>
				<tr>
					<th class="col-2" scope="col" align="center">네이버 아이디</th>
					<td><input type="text" name="naverId" class="form-control" value="${member.naverId }" readonly></td>
				</tr>
				<tr>
					<th  class="col-2" scope="col" align="center">카카오 아이디</th>
					<td><input type="text" name="kakaoId" class="form-control" value="${member.kakaoId }" readonly></td>
				</tr>
				<tr>
					<th class="col-2" scope="col" align="center">계정 종류</th>
					<td><input type="text" name="accType" class="form-control" value="${member.accType }" readonly></td>
				</tr>
				<tr>
					<th class="col-2" scope="col" align="center">회원 종류</th>
					<td>
						<c:if test="${member.adminYN eq 'Y' }">
							<input type="text" name="adminYN" class="form-control" value="관리자" readonly>
						</c:if>
						<c:if test="${member.adminYN eq 'N' }">
							<input type="text" name="adminYN" class="form-control" value="일반" readonly>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" style="border:none;">
						<input onclick="adminModify(${member.memberId }, ${bPage.currentPage});" type="button" value="수정" class="btn btn-warning btn-sm">
						<input onclick="adminRemove(${member.memberId }, ${bPage.currentPage});" type="button" value="삭제" class="btn btn-warning btn-sm">
						<button type="button" onclick="backBtn()" class="btn btn-warning btn-sm">목록</button> 
						 
					</td>
				</tr>
			</table>
		</div>
	</div>	
</div>
<br><br>
</main>
<!-- main contents End -->


<script>
	function backBtn() {
	    history.back();
	}
	
	function adminModify(memberId, currentPage) {
		location.href="/admin/membermodifyView.kh?memberId="+memberId+"&page="+currentPage;
	}
	function adminRemove(memberId, currentPage) {
		event.preventDefault(); // 하이퍼링크 이동 방지
		if(confirm("게시물을 삭제하시겠습니까?")) {
			location.href="/admin/memberRemove.kh?memberId="+memberId+"&page="+currentPage;
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