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
			
		<!-- 세부페이지 큰 제목 끝 -->
		<br>
		<hr>
			<!-- 세부 메뉴 시작 -->
			<ul class="mainUl">
	    		<li class="mainLi"><div style="text-align:right">
			    		<a href="/admin/adminListView.kh">전체회원<br>1&nbsp;&nbsp;&nbsp;&nbsp;</a>
			    	</div>
			    </li>
			    <li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/admin/adminListView.kh">오늘가입<br>1</a>
			    	</div>
				</li>
				<li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:center">
			    		<a href="/admin/adminListView.kh">탈퇴회원<br>1</a>
			    	</div>
				</li>
				<li class="mainLi">
			    	<div class="col"><img src="/resources/img/sidebar.png"></div>
			    </li>
	    		<li class="mainLi">
	    			<div style="text-align:left">
			    		<a href="/admin/adminListView.kh">휴면회원<br>&nbsp;&nbsp;&nbsp;&nbsp;1</a>
			    	</div>
	    		</li>
	 		</ul>
	 		<!-- 세부 메뉴 끝 -->
		<hr>
		<!-- 세부페이지 head 끝 -->
	
			<!--  세부페이지 body -->
			<div class="row row-cols-1">
				<table align="center" class="table col-6" style="width:80%";>
					<tr>
						<th class="col-6" colspan="2" align="left" style="border:none";>${member.memberId }회원의 상세정보</th>
					</tr>
					<tr>
						<th class="col-2" scope="col" align="center">닉네임</th>
						<td class="col-4"><input type="text" name="mNickname" class="form-control" value="${member.mNickname }" readonly></td>
					</tr>
					<tr>
						<th class="col-2" scope="col" align="center">아이디</th>
						<td class="col-4"><input type="text" name="memberId" class="form-control" value="${member.memberId }" readonly></td>
					</tr>
					<tr>
						<th class="col-2" scope="col" align="center">비밀번호</th>
						<td class="col-4"><input type="text" name="memberPw" class="form-control" value="${member.memberPw }" readonly></td>
					</tr>
					<tr>
						<th class="col-2" scope="col" align="center">이메일</th>
						<td class="col-4"><input type="text" name="mEmail" class="form-control" value="${member.mEmail }" readonly></td>
					</tr>
					<tr>
						<th class="col-2" scope="col" align="center">가입일</th>
						<td class="col-4"><input type="text" name="joinDate" class="form-control" value="${member.joinDate }" readonly></td>
					</tr>
					<tr>
						<th class="col-2">상태</th>
						<td class="col-4">
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
						<td class="col-4"><input type="text" name="mPoint" class="form-control" value="${member.mPoint }" readonly></td>
					</tr>
					<tr>
						<th class="col-2" scope="col" align="center">월구독여부</th>
						<td class="col-4">
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
						<td class="col-4"><input type="text" name="naverId" class="form-control" value="${member.naverId }" readonly></td>
					</tr>
					<tr>
						<th  class="col-2" scope="col" align="center">카카오 아이디</th>
						<td class="col-4"><input type="text" name="kakaoId" class="form-control" value="${member.kakaoId }" readonly></td>
					</tr>
					<tr>
						<th class="col-2" scope="col" align="center">계정 종류</th>
						<td><input type="text" name="accType" class="form-control" value="${member.accType }" readonly></td>
					</tr>
					<tr>
						<th class="col-2" scope="col" align="center">회원 종류</th>
						<td class="col-4">
							<c:if test="${member.adminYN eq 'Y' }">
								<input type="text" name="adminYN" class="form-control" value="관리자" readonly>
							</c:if>
							<c:if test="${member.adminYN eq 'N' }">
								<input type="text" name="adminYN" class="form-control" value="일반" readonly>
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="col-6" colspan="2" align="center" style="border:none";>
							<input onclick="adminModify('${member.memberId }', '${page}');" type="button" value="수정" class="btn btn-warning btn-sm">
							<input onclick="adminRemove('${member.memberId }', '${page}');" type="button" value="삭제" class="btn btn-warning btn-sm">
							<button type="button" onclick="backBtn()" class="btn btn-warning btn-sm">목록</button>
							<%-- <button type="button" onclick="location.href='/admin/adminListView.kh?page=${page}'" class="btn btn-warning btn-sm">목록</button> --%> 
						</td>
					</tr>
				</table>
			</div>
		</div>	
	</section>
<br><br>
</main>
<!-- main contents End -->


<script>
	function backBtn() {
	    history.back();
	}
	
	function adminModify(memberId, page) {
		location.href="/admin/memberModifyView.kh?memberId="+memberId+"&page="+page;
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