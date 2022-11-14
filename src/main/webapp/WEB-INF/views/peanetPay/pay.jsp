<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제창</title>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link  rel="stylesheet" href="../resources/css/pay/pay.css">
<script
  src="https://code.jquery.com/jquery-3.6.1.js"
  integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
  crossorigin="anonymous"></script>

<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
</head>

<body>
<jsp:include page="../header/header.jsp"></jsp:include>


    <main>        
        <section>
        <input type="hidden" value="${sessionScope.loginMember}">
			<div id="ssTicket">
				<table>
					<tr>
						<td colspan="3" class="payName">구독권</td>
					</tr>
					<tr>
						<td class="payOne"><label for="seasonticket"><input
								type="radio" id="seasonticket" name="payName" value="10000"></label></td>
						<td class="payTwo" name="seasonticket"><label
							for="seasonticket">한달이용권</label></td>
						<td class="payTwo" value="10000"><label
							for="seasonticket">10,000원</label></td>
					</tr>
				</table>
			</div>

			<div id="peanetCharge">
				<table>			
					<tr>
						<td colspan="3" class="payName">땅콩충전</td>
					</tr>
					<tr>

						<td class="payOne"><label for="peanutpoint100"><input
								type="radio" id="peanutpoint100" name="payName" value="10000"></label></td>
						<td class="payTwo" name="peanetpoint"><label
							for="peanutpoint100">100 땅콩</label></td>
						<td class="payTwo"><label for="peanutpoint100">10,000원</label></td>

					</tr>
					<tr>

						<td class="payOne"><label for="peanutpoint200"><input
								type="radio" id="peanutpoint200" name="payName" value="20000"></label></td>
						<td class="payTwo" name="peanetpoint"><label
							for="peanutpoint200">200 땅콩</label></td>
						<td class="payTwo"><label for="peanutpoint200">20,000원</label></td>

					</tr>
					<tr>
						<td class="payOne"><label for="peanutpoint300"><input
								type="radio" id="peanutpoint300" name="payName" value="30000"></label></td>
						<td class="payTwo" name="peanetpoint"><label
							for="peanutpoint300">300 땅콩</label></td>
						<td class="payTwo"><label for="peanutpoint300">30,000원</label></td>
					</tr>

				</table>

			</div>

			<div id="Confirmation"><button id="ordercheck">주 문 확 정</button></div>
            <div id="history">
                <table>                  
                    <tr>
                        <td colspan="2" class="payName">주문내역</td>
                    </tr>                    
                    <tr>
                        <td class="history1"> 주문번호 :</td>
                        <td class="history2" id="oderno"></td>
                    </tr>
                    <tr>
                        <td class="history1">구매내역 : </td>
                        <td class="history2" id="contents"></td>
                    </tr>
                    <tr>
                        <td class="history1">구매금액 : </td>
                        <td class="history2" id="paymoney" value=""></td>
                    </tr>
              
                </table>     
            
            
                <div id="listCheck">
                    <p>주문내용을 확인하셨습니까? </p>
                    <p>구독권은 구매후 취소불가합니다.</p>
                    <p>땅콩은 구매후 15일 안에 사용하지 않을 경우만 </p>
                    <p>취소가능하며 사용 후 잔여땅콩은 환불되지 않습니다.</p>
                    <p>위의 내용에 동의하십니까?<input type="checkbox" value="" id="payCheck"></p> 
                </div>
            </div>
	        <div id="pay"><button onclick="requestPay()">결 제 진 행</button></div>
        </section>
    </main>
    
<jsp:include page="../footer/footer.jsp"></jsp:include>

<script type="text/javascript">
	    var orderName = '';//결제 분류명
	    var payMoney = '';  // 결제할 값  
	    var d = new Date();
	    var dm = '';         // 년월일 합칠 변수
	    var orderNo = '';   //주문번호 분류명과 날짜 합친것
	    var orderContents = '';
	    var merchant_uid = ''; //결제 주문번호 넣을 곳
	    var memberId = '${sessionScope.loginMember.memberId}';
	    var mEmail = '${sessionScope.loginMember.mEmail}'; 
	  
	  
	    $('#seasonticket').on('click', function(){
	    	if(!(${empty sessionScope.lastDate})){
				alert("구독권 만료후 구매 부탁드립니다. 이용해 주셔서 감사합니다.");
				$('#seasonticket').prop("checked",false);
			};
	    })
	    
	    
	    
	    $('#ordercheck').on('click', function(){
	    	orderName= $('input[type="radio"]:checked').parent().parent().next().attr('name');//결제 분류명
	    	payMoney= $('input[type="radio"]:checked').val();  // 결제할 값 
	    	orderContents=$('input[type="radio"]:checked').parent().parent().next().text()+"구매"; // 주문내용
	    	
	    	if(orderName==null){
	    		alert("구매내용을 선택 해주세요.");
	    	} else{
	
			    if ($('#history').css('display')=='none'){
				    $('#ordercheck').css('background-color','red');
				    $('#ordercheck').css('color','#fff');
				    $('#ordercheck').html('주 문 취 소');    
				    $('#history').css('display','block');
				    $('#pay').css('display','block');
				    orderList(); 
				    
				  } else{           
				    location.href='/pay/start.kh';
				  };	
	    	}				
	})
	
	function orderList(){	    
	   	    
	    dm=d.getFullYear();
	    dm+=('0'+(d.getMonth()+1)).slice(-2);
	    dm+=('0'+d.getDate()).slice(-2);
	    orderNo=orderName+"-"+dm;                        //주문번호 분류명과 날짜 합친것
	    
	    
	    $.ajax({
	    	url:'/pay/orderIN.kh',
	    	type:'post',
	    	dataType:'json',
	    	data:{
	    		'orderNo':orderNo,
	    		'memberId':memberId,
	    		'orderContents':orderContents,	    		
	    		'payMoney':payMoney
	    	},
	    	success:function(pay){          //주문번호 받아오기
	    		$('#oderno').html(pay.orderNo);
	    		$('#contents').html(pay.orderContents);
	    		$('#paymoney').html(pay.pay+'원');
	    		$('#paymoney').val(pay.pay);
	    	
	    	},
	    	error : function(data){
	    		console.log(data);
	    		alert('주문실패: 잠시후 다시 부탁드립니다!'+data.statusText);
	    	}
	    })
	    
	}
	
	// -------------------------------------*********주문 확정 후 번호 받아 오기
	var IMP = window.IMP; 
	IMP.init('imp01477151');
	
	function requestPay() {
		
	if ($('#payCheck').is(':checked')) {
			IMP.request_pay({
				pg : 'html5_inicis',
				pay_method : 'card',
				merchant_uid : merchant_uid, //주문번호 
				name : orderContents, //주문내용
				amount : 1000, //주문금액
				buyer_email : mEmail, // 고객이메일
				buyer_name : memberId, // 고객id
				buyer_tel : '01011112222', //고객연락처
				buyer_addr : '서울특별시 강남구 삼성동', //고객주소
				buyer_postcode : '123-456'
			}, function(rsp) { // callback
				if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
					// jQuery로 HTTP 요청
					$.ajax({
						url : '/pay/success.kh', // 예: https://www.myservice.com/payments/complete
						type : 'POST',
						// headers: { 'Content-Type': 'application/json' },
						data : {
							'imp_uid' : rsp.imp_uid,//결제번호
							'orderNo' : $('#oderno').html(),
							'memberId' : memberId,
							'pay' : $('#paymoney').val()
						},
						success : function(result) {
							alert("결제성공");
							//결제성공시 팝업							
							if(orderName=='seasonticket'){
								popUp();							
							}
							location.href = '/';
						},
						error : function(e) {
							console.log(e)
							alert('에러: 관리자에게 문의하세요');
						}
					}).done(function(data) {
						//가맹점 서버 결제 aip성공시 로직
						switch (data.status) {
						case 'vbankIssued':
							// 가상계좌 발급 시 로직
							break;
						case 'success':
							// 결제 성공 시 로직
							break;
						}
					});
				} else {
					alert('결제에 실패하였습니다. 에러 내용: ' + rsp.error_msg);
					location.href = '/pay/start.kh';
				}
			});
			
		} else {
			alert("동의 체크 후 결제 가능합니다.");
		}

	}
	var popupObj;
	function popUp(){
		var windo = "status=no ,toolbar=no,scrollbars=no, menubar=no,resizable=no,titlebar=no, width=380,height=320";

		popupObj=window.open("/pay/paySuccessPop.kh","PopupWin", windo);
		
		var stoptime=setTimeout(closePopup,5000);		
	}
	
	function closePopup(){
		popupObj.close();
	}
	
</script>
</body>
</html>