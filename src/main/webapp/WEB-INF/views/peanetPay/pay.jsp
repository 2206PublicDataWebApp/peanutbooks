<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제창</title>

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
<button onclick="location.href='/peanet/list.kh';">땅콩사용 리스트</button>

    <main>        
        <section>
        <input type="hidden" value="${sessionScope.loginUser}">
            <div id="ssTicket">
                <table>
                    <tr>
                        <td colspan="3" class="payName">구독권</td>
                    </tr> 
                    <tr>
                        <td class="payOne"><input type="radio" name="seasonticket" value="10000"></td>
                        <td class="payTwo" >한달이용권</td>
                        <td class="payTwo" name="pay" value="10000">10,000원</td>
                    </tr>
              
                </table>
            </div>
            
            <div id="peanetCharge">
                <table>
                    <tr>
                        <td colspan="3" class="payName">땅콩충전</td>
                    </tr>
                    <tr>
                        <td class="payOne"><input type="radio" name="peanetpoint" value="10000"></td>
                        <td class="payTwo" >100 땅콩</td>
                        <td class="payTwo" >10,000원</td>
                    </tr>
                    <tr>
                        <td class="payOne"><input type="radio" name="peanetpoint" value="20000"></td>
                        <td class="payTwo" >200 땅콩</td>
                        <td class="payTwo"> 20,000원</td>
                    </tr>
                    <tr>
                        <td class="payOne"><input type="radio" name="peanetpoint" value="30000"></td>
                        <td class="payTwo" >300 땅콩</td>
                        <td class="payTwo" >30,000원</td>
                    </tr>
              
                </table>             
                
            </div>

            <div id="Confirmation"><button id="ordercheck">주문확정</button></div>
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
                        <td class="history2" id="paymoney"></td>
                    </tr>
              
                </table>     
            
            
                <div id="orderCheck">
                    <p>주문내용을 확인하셨습니까? </p>
                    <p>구독권은 구매후 취소불가합니다.</p>
                    <p>땅콩은 구매후 15일 안에 사용하지 않을 경우만 </p>
                    <p>취소가능하며 잔여땅콩은 환불되지 않습니다.</p>
                    <p>위의 내용에 동의하십니까?<input type="checkbox" value="" id="payCheck"></p> 
                </div>
            </div>
            <div id="pay"><button onclick="requestPay()">결제진행</button></div>
        </section>
    </main>

<script type="text/javascript">

	    var orderName="";  //결제 분류명
	    var payPoint="";  // 결제할 값
	    var d=new Date();
	    var dm="";         // 년월일 합칠 변수
	    var orderNo="";   //주문번호 분류명과 날짜 합친것
	    var orderContents="" // 주문내용
	    var merchant_uid=""; //결제 주문번호 넣을 곳
	    var memberId= "${sessionScope.loginUser.memberId}";
	    var memberEmail= "${sessionScope.loginUser.memberEmail}";  
	    
	$("#ordercheck").on("click", function(){
	    if ($("#history").css("display")=="none"){
		    $("#ordercheck").css("background-color","red");
		    $("#ordercheck").css("color","#fff");
		    $("#ordercheck").html("주문취소");    
		    $("#history").show();
		   }else{           
		    $("#ordercheck").css("background-color","#fff9b0");
		    $("#ordercheck").css("color","black");
		    $("#ordercheck").html("주문확정"); 
		    $("#history").hide();   
		    
		   }
	    
	    orderName=$('input[type="radio"]').attr('name');  //결제 분류명
	    payPoint=$('input[name="peanetpoint"]').val();  // 결제할 값    
	    dm=String(d.getFullYear());
	    dm+=String(d.getMonth()+1);
	    dm+=String(d.getDate());
	    orderNo=orderName+"-"+dm;                        //주문번호 분류명과 날짜 합친것
	    orderContents=$('input[type="radio"]:checked').parent().next().text()+"구매"; // 주문내용
	    
	    $.ajax({
	    	url:"/pay/orderIN.kh",
	    	type:"post",
	    	dataType:"json",
	    	data:{
	    		"orderNo":orderNo,
	    		"memberId":memberId,
	    		"orderContents":orderContents,
	    		"memberEmail":memberEmail,
	    		"payPoint":payPoint
	    	},
	    	success:function(pay){          //주문번호 받아오기
	    		$("#oderno").html(pay.orderNo);
	    		$("#contents").html(pay.orderContents);
	    		$("#paymoney").html(pay.payPoint+"원");
	    	
	    	},
	    	error : function(){
	    		alert("주문실패: 잠시후 다시 부탁드립니다!");
	    	}
	    })
	    
	})
	// -------------------------------------*********주문 확정 후 번호 받아 오기
	var IMP = window.IMP; 
	IMP.init("imp01477151"); 	
	function requestPay() {
	    IMP.request_pay({
	        pg : 'html5_inicis',
	        pay_method : 'card',
	        merchant_uid: merchant_uid,   //주문번호 
	        name : orderContents,             //주문내용
	        amount : payPoint,                 //주문금액
	        buyer_email :memberEmail,       // 고객이메일
	        buyer_name : memberId,         // 고객id
	        buyer_tel : "01011112222",        //고객연락처
	        buyer_addr : '서울특별시 강남구 삼성동', //고객주소
	        buyer_postcode : '123-456'
	    }, function (rsp) { // callback
	        if (rsp.success) {                // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
	        	// jQuery로 HTTP 요청
	            $.ajax({
	                url: "{서버의 결제 정보를 받는 endpoint}", // 예: https://www.myservice.com/payments/complete
	                method: "POST",
	                headers: { "Content-Type": "application/json" },
	                data: {
	                    imp_uid: rsp.imp_uid,
	                    merchant_uid: rsp.merchant_uid
	                }
	            }).done(function (data) {  //////////////////******결제 응답처리
	            	 switch(data.status) {
	                 case "vbankIssued":
	                   // 가상계좌 발급 시 로직
	                   break;
	                 case "success":
	                	 alert("결제성공");
	                  $.ajax({
	                	  url:"/pay/success.kh",
	                	  type:"get",
	                	  data:{"orderNo":merchant_uid },
	                	  success:function(result){
	                		  alert("결제저장성공");
	                		  
	                	  },
	                	  error:function(){
	                		  alert("결제저장 실패: 관리자에게 문의하세요");
	                	  }
	                  });
	                   break;
	               }
	             });
	        } else {
	        	alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg); 
	        	location.href="/pay/start.kh";
	        	}
	    });
                          
	}

</script>
</body>
</html>