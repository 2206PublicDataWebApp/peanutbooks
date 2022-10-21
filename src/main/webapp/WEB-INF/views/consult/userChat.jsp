<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 채팅화면</title>

<link rel="stylesheet" href="../resources/css/chat/userChat.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<style>
</style>
</head>
<body>
	<!--header 항시 출력 부분 -->
	<div class="header">
		<div>
			<P id="nickName">${memberId}</p>
			<p>의 채팅상담</P>
			<input type="hidden" name="mEmail" value="${mEmail}">
			<input type="hidden" name="titleNo" id="titleNo" value="${titleNo}"/>			
		</div>

		<div class="btn">
			<button onclick="chatfinish();" id="endChat">종료</button>
			
		</div>
	</div>
	
	<div id="before" >
			사용자님 반갑습니다. 상담원하시는 주제를 10자 이내로 기록해주시면 관리자가 접속시 참고하여 진행해드립니다. 다른 상담으로
			인해 대기시간이 소요될수 있는 점은 양해부탁드립니다.
			<table width="100%">
				<tr>
					<td><input type="text" name="cTitle" id="inTitle" /></td>
					<td><button id="centerbtn" onclick="beforeChat('${memberId}','${mEmail}') ;" value="접수">접수</button></td>
					
				</tr>
			</table>
	
		</div>
		<!--context 내용 입력부분 -->
	
	<div class="context">
		<div id="after"></div>	
	</div>
	
	
	<!--foot 입력부분  -->
	<div class="footer">
		<div class="textIn">
			<textarea id="usertext" cols="50" placeholder="50자이내로 입력하세요"></textarea>
		</div>
		<div id="footbtn">
			<input type="button" value="전송" id="getResult">
		</div>
	</div>
	<script>
	$('.context').scrollTop=$('.context').scrollHeight;
	var printer;
	//상담접수
		function beforeChat(memberId, mEmail) {

			var afterMsg = {
				cMemberId : memberId,
				cEmail : mEmail,
				csTitle : $('#inTitle').val()
			};
			//console.log("화면 접수성공: " + afterMsg);
			$.ajax({
				url : "/client/afterChat.kh",
				dataType : 'json',
				type : 'post',
				data : afterMsg,
				success : function(result) {
					alert("접수성공");
					//성공시 대기 맨트 예정
					$('<div>').appendTo('#after').text("접수완료되었습니다.");
					$('<div>').appendTo('#after').text("잠시만 기다려주시기 바랍니다..");
					$('<div>').appendTo('#after').text(
							"다른 상담으로 인해 대시시간이 요소될수 있는 점 양해 부탁드립니다..")

					$('#titleNo').val(result.titleNo);
					printer = setInterval(collList, 500);

				},
				error : function(e) {
					alert(1);
				}
			});

		}

		// 고객이 관리자와 채팅 시작

		$('#getResult').click(
				function() {
					$("#before").css("display", "none");
					var msg = { //json형식으로 데이터set 	
						cMemberId : $('#nickName').html(),
						cContexts : $('#usertext').val(),
						cEmail : $('[name=mEmail]').val(),
						titleNo : $('#titleNo').val()
					};
					$('#usertext').val('');
					console.log("채팅전송내역:" + msg.cMemberId + ", "
							+ msg.cContexts + ", " + msg.cEmail + ", "
							+ msg.titleNo);
					$.ajax({
						url : "/client/start.kh",
						dataType : 'json',
						type : 'post',
						data : msg,
						success : function(result) {
							console.log("채팅전송성공:" + result);

						},
						error : function(e) {
							alert('error : ' + e);
						}
					});
				})

		//DB에서 데이터 가져와서 화면에 출력해주기		
		function collList() {
			console.log("출력준비");
			$('#after').html('');

			$.ajax({
				url : "/client/listprint.kh",
				type : 'post',
				data : {
					titleNo : $("#titleNo").val()
				},
				success : function(result) {
					for ( var i in result) {
						var $chat = $('#after > div[data-consultNo="' + result[i].consultNo + '"]');
						if ($chat.length < 1) {						
							addChat(result[i].cousultNo, result[i].cMemberId,
									result[i].cContexts, result[i].cDate);	
						};
					};
				},
				error : function(e) {
					alert('error : ' + e);
				}
			});
		}

		function addChat(consultNo,cMemberId, cContext, cDate) {
			console.log("데이터 올림 확인 : " + cMemberId);
			if (cMemberId != 'admin') {
				$('#after').append(
								'<div class="right" data-consultNo="' + consultNo + '">'
										+ '<h5 >' + cMemberId + '</h5>'
										+ '<div class="middleBox"><span class="contextBox">'
										+ cContext + '</span>'
										+ '<span class="dateBox">' + cDate
										+ '</span></div></div>');

			} else {
				$('#after').append(
						'<div class="left" data-consultNo="' + consultNo + '">'
								+ '<h5 >' + cMemberId + '</h5>'
								+ '<div class="middleBox"><span class="contextBox">'
								+ cContext + '</span>'
								+ '<span class="dateBox">' + cDate
								+ '</span></div></div>');
			}
		}
		//종료 버튼 누를경우
		function chatfinish() {

			if (confirm("정말로 종료하시겠습니까?")) {
				clearInterval(printer);
				setTimeout(function(){
					printer=false;
					self.close();
				},50);
			} else {
				alert("종료 되지 않았습니다. 다시 부탁드립니다.");
			};
		}
	</script>
</body>
</html>






