<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 채팅화면</title>
<link rel="stylesheet" href="../resources/css/chat/managerChat.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
<!--header 항시 출력 부분 -->
<div class="header">
	<div class="InputText">
		<P id="nickName">${cMemberId} 의 문의입니다</P>
		<p >주제 : ${csTitle }</p>
		<input type="hidden" name="titleNo" id="titleNo" />	
	</div>
	<div class="btn"><input type="button" onClick="chatfinish();" value="종료" id="chatfinish"></div>
	
</div>
<!--context 내용 입력부분 -->
	<div class="context">
		<div id="after">
			<div>상담을 시작합니다.</div>
			
		</div>




	</div>
<!--foot 입력부분  -->
	<div class="footer">
		<div class="InputText">
			<textarea id="magText" cols="50" placeholder="50자이내로 입력하세요"></textarea>
		</div>
		<div id="footbtn">
			<input type="button" value="전송" id="getResult">
		</div>
	</div>

<script >
	var printer;

	$('.context').scrollTop=$('.context').scrollHeight;	
	
	$('#getResult').on('click',function(){
		sendChatMesage();
	});
	
	$('#magText').on('keyup',function(e){
		
		if(e.keyCode==13){
			sendChatMesage();
		};
	});
	function sendChatMesage(){	
		var msg=$('#magText').val();
		if(msg==''){
			alert("입력되지 않았습니다");
		}else{
			var titleNo = ${param.titleNo};
			var msg = { 
				cMemberId : 'admin',
				cContexts : $('#magText').val(),
				cEmail : 'test_admin',
				titleNo : titleNo
			};
			$('#magText').val('');
			$.ajax({ 
				url : '/client/start.kh', 
				dataType : 'json', 
				type : 'POST', 
				data : msg, 
				success : function(result) { 
	
 					if(!printer){
 						printer = setInterval(collList, 500);
 					}
				},
				error : function(e) {
					alert('error:' + e);
				},
			});
			
		}
	};

	
	function collList() {
		console.log("출력준비");
		var titleNo = ${param.titleNo};		

		$.ajax({
			url : "/client/listprint.kh",
			type : 'post',
			data : {titleNo : titleNo},
			success : function(result) {
				for ( var i in result) {
					var $chat = $('.chat[data-consult-no="' + result[i].consultNo + '"]');					
 			 		if ($chat.length < 1) {						
 						addChat(result[i].consultNo, result[i].cMemberId,
 								result[i].cContexts, result[i].cDate);	 					
					}
				}
			},
			error : function(e) {
				alert('error : ' + e);
			}
		});
	}
	//넘겨진 채팅 내용을 받아서 화면에 출력해줌
	function addChat(consultNo, cMemberId, cContext, cDate) {			
		countData++;
		if (cMemberId === 'admin') {
			$('#after').append(
					'<div class="chat right" data-consult-no="' + consultNo +'" >'
							+ '<div class="icon"><img src="../resources/img/live-chat.png"></div>'
							+ '<div class="middleBox"><span class="dateBox">'
							+ cDate + '</span>' + '<span class="contextBox">'
							+ cContext + '</span></div></div>');
		} else {
			$('#after').append(
					'<div class="chat left" data-consult-no="' + consultNo + '">'
							+ '<div class="icon"><img src="../resources/img/programmer.png"></div>'
							+ '<div class="middleBox"><span class="contextBox">'
							+ cContext + '</span><span class="dateBox">'
							+ cDate + '</span>' + '</div></div>');
		}
	}

	//종료 버튼 누를경우
	function chatfinish() {
		var titleNo = ${param.titleNo};
		if (confirm("정말로 종료하시겠습니까?")) {
			var csResult = prompt('상담결과를 입력하세요.');
			if(csResult != null && csResult != '' && csResult != 'undefined'){
				$.ajax({
					url : "/consult/finish.kh",
					type : "post",
					dataType : "json",
					data : {
						titleNo : titleNo,
						csResult : csResult
					},
					success : function(data) {
						if (data.result > 0) {
							clearInterval(printer);
							setTimeout(function() {
								printer = false;
								self.close();
							}, 50)
						} else {
							alert("종료 되지 않았습니다. 다시 해주세요");
	
						};
					},
					error : function(e) {
						alert('error : ' + e.statusText);
					}
				});
			} else{
				alert("결과를 입력해주세요.");
			}
		};
	}
</script>
<a href="https://www.flaticon.com/free-icons/user" title="user icons">flaticon</a>
</body>
</html>