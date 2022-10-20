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
			<input type="hidden" name="memberEmail" value="${memberEmail}">
			<input type="hidden" name="titleNo" id="titleNo" />			
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
					<td><button id="centerbtn" onclick="(beforeChat('${memberId}','${memberEmail}')) ;" value="접수">접수</button></td>
					
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
		//상담접수
		function beforeChat(memberId, memberEmail) {

			var afterMsg = {
				cMemberId : memberId,
				cEmail : memberEmail,
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
					printer=setInterval(collList,4000);

				},
				error: function(e) {
					alert(1);
				}
			})

		}

		// 고객이 관리자와 채팅 시작
		$('#getResult').click(function() { /* 전송버튼을 클릭하면 */
			$("#before").css("display","none");
			var msg = { //json형식으로 데이터set 	
				cMemberId : $('#nickName').html(),
				cContexts : $('#usertext').val(),
				cEmail : $('[name=memberEmail]').val(),
				titleNo : $('#titleNo').val()
			};
			$('#usertext').val('');
			console.log("채팅전송내역:" + msg.cMemberId + ", " + msg.cContexts + ", " + msg.cEmail + ", " + msg.titleNo);
			$.ajax({								/*{} 객체를 의미함 key: value값을 ,로 구분하여 객체의 속성이 만들어짐 */
				url:"/client/start.kh",	/* url파일로 접근, 컨트롤러에서 대기중인 url주소 */
				dataType:'json',					/* 검사/net/응답을 보면{"result",true:,"msg":"보낸 메세지...input의 text임"}) 받은걸 자바스크립트가 알아서 변환해준다. */
				type:'post',						/* 폼에서 메소드형식을 생각하면됨 */
				data:msg,							/* 서버로 부터 받은 msg의 val를 메세지변수에 넣음 */
				success:function(result){  			/* 이벤트 핸들러 result에 서버가 보낸준 값이 리턴됨. */
					console.log("채팅전송성공:"+result);
					
				},
				error: function(e) {
					alert('error : ' + e);
				},
			});
		})
		

		//DB에서 데이터 가져와서 화면에 출력해주기

		//var time = setInterval(collList, 3000) //2000했더니 바쁘다..
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
					console.log("리스트 수신성공: " + result);
					//var data = JSON.parse(result);  //배열로 온것을 파싱한다.
					//console.log(data); 			
				
					for ( var i in result) {
						addChat(result[i].cMemberId, result[i].cContexts,result[i].cDate);						
					}
				
				},
				error: function(e) {
					alert('error : ' + e);
				}
			});
		}


		function addChat(cMemberId, cContext, cDate) {
			console.log("데이터 올림 확인 : " + cMemberId);
			if(cMemberId!='manager'){
				 $('#after').append(
					 '<div class="right">'
                 	   +'<h5 >'+cMemberId+'</h5>'
                       +'<div class="middleBox"><span class="contextBox">'+ cContext +'</span>'
                       +'<span class="dateBox">'+cDate+'</span></div></div>');

			}else{
				 $('#after').append(
						 '<div class="left" >'
	                 	   +'<h5 >'+cMemberId+'</h5>'
	                       +'<div class="middleBox"><span class="contextBox">'+ cContext +'</span>'
	                       +'<span class="dateBox">'+cDate+'</span></div></div>');				
			}
			// $('#after').scrollTop($('#after')[0].scrollHeight);
			$('#after').scrollTop=$('#after').scrollHeight
		}
		//종료 버튼 누를경우
		function chatfinish() {

			if (confirm("정말로 종료하시겠습니까?")) {
				clearInterval(printer);
				self.close();
				//location.href = "/";
			}
		}
	</script>
</body>
</html>






