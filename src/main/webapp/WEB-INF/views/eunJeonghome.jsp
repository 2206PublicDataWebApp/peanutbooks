<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

 <c:if test="${sessionScope.loginUser ne null }">
 		<table align="right">
 			<tr>
 				 <td colspan="2">${loginUser.memberId}님 환영합니다</td> 
 					
 			</tr>
 			<tr>
 				<td><a href="#">정보수정</a></td>
 				<td><a href="#">로그아웃</a></td>
 			</tr>
 		</table>
 </c:if>

<c:if test="${empty loginUser }">
		<form action="/member/login.kh" method="post">
		 	<table align="right">
			 	<tr>
			 		<td>이메일 : </td>
			 		<td><input type="text" name="mEmail"></td>
			 		<td rowspan="2">
			 			<input type="submit" value="로그인">
			 		</td>
			 	</tr>
			 	
			 	<tr>
			 		<td> 비밀번호 : </td>
			 		<td><input type="password" name="memberPw" ></td>
			 	</tr>
			 	
			 	<tr>
			 	 	<td colspan="3"><a href="/member/joinView.kh">회원가입</a></td>
			 	</tr>
			 </table>
	 	</form> 

  </c:if>

	<button onclick="startChat('${loginUser.memberId}');">채팅상담</button>
	<button onclick="location.href='/manager.kh';">관리자 페이지</button>
	<button onclick="payStart('${loginUser.memberId}');">결제 페이지</button>
	<button onclick="peanutList('${loginUser.memberId}');">땅콩사용페이지</button>
	<button onclick="writerPay('${loginUser.memberId}')">작가 정산요청</button>
<script>
	//고객채팅창가기
	function startChat(memberId) {
		 if(memberId==''){
			 alert("로그인후 가능합니다");
		 }else{
			  $.ajax({						
					url:"/client/chatCheck.kh",
					dataType:"json",
					type:'get',	 			           			                         	
					success:function(result){  			/* 이벤트 핸들러 result에 서버가 보낸준 값이 리턴됨. */
						console.log("버튼확인성공:"+result);
						 if(result.switchbtn=='N'){
							 alert("관리자가 준비되지 않았습니다. 잠시후 부탁드립니다.");  //버튼값이 n이면 그냥 종료
						 }else{
							 chatbtnSuccess(memberId)                                //y이면 로그인 확인
						 };				
					},
					error: function(e) {
						alert('error');
					},
				});
		 }
	}
	 
	 
	 function chatbtnSuccess(memberId){	        
			var windo="status=no ,toolbar=no,scrollbars=no, menubar=no,resizable=no,titlebar=no,width=550,height=650";
			window.open("/consult/chatbefore.kh","PopupWin",windo);			
	 } 	 
		
	 
	 /*결제 관련   */
	 function payStart(memberId) {
		 if(memberId==''){
			 alert("로그인후 가능합니다");
		 }else{
			 location.href="/pay/start.kh";
		 }
	 }
	 
	 /*작가 정산 요청  */
	 	 function writerPay(memberId) {
		 if(memberId==''){
			 alert("로그인후 가능합니다");
		 }else{
			 location.href="/writer/writerStart.kh?memberId="+memberId;
		 }
	 }
	 
	 //땅콩사용리스트
		 function peanutList(memberId) {
			 if(memberId==''){
				 alert("로그인후 가능합니다");
			 }else{
				 location.href="/peanut/listStart.kh";    				
			 }	
	 }

</script>
</body>
</html>
