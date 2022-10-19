<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작가 정산 요청</title>
<link rel="stylesheet" href="../resources/css/pay/writerPay.css">
<script
  src="https://code.jquery.com/jquery-3.6.1.js"
  integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
  crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../header/header.jsp"></jsp:include>

<section>
<h1>작가 정산 요청</h1>
<div>
	 <table>
            <tr>
                <td>시리즈 번호 :</td>
                <td><input type="text" name="seriesNo" id="seriesNo"></td>
            </tr>
            <tr>
                <td>도서번호 :</td>
                <td><input type="text" name="ori_bookNo" id="ori_bookNo"></td>
            </tr>
            <tr>
                <td>정산땅콩 :</td>
                <td><input type="text"name="paidCount" id="paidCount"></td>
            </tr>
            <tr>
                <td>차감될 땅콩 :</td>
                <td><input type="text" name="changeP" id="changeP"></td>
            </tr>
            <tr>
                <td>지급금액 :</td>
                <td><input type="text" name="payment" id="payment"></td>
            </tr>
            <tr>
                <td>지급요청은행 :</td>
                <td><input type="text" name="bankName" id="bankName"></td>
            </tr>
            <tr>
                <td>계좌번호 :</td>
                <td><input type="text" name="bankNo" id="bankNo"></td>
            </tr>
        </table>
    </div>
    <div class="btn"><button onclick="writerPay()">지급요청</button></div>   
    
</section>

<jsp:include page="../footer/footer.jsp"></jsp:include>

<script>
	function writerPay(){
		var memberId="${sessionScope.loginUser.memberId}";
		var seriesNo=$("#seriesNo").val();
		var ori_bookNo=$("#ori_bookNo").val();
		var changeP=$("#changeP").val();
		var payment=$("#payment").val();
		var bankName=$("#bankName").val();
		var bankNo=$("#bankNo").val();
		$.ajax({
			url:"/writer/receipt.kh",
			type:"post",
			data:{
				"memberId" : memberId,
				"seriesNo" : seriesNo,
				"ori_bookNo" : ori_bookNo,
				"changeP" : changeP,
				"payment" : payment,
				"bankName" : bankName,			
				"bankNo" : bankNo				
			},
			success : function(result){
				if(result=="success"){
					alert("정상접수되었습니다.");
				}else{
					alert("미접수되었습니다. 다시한번 부탁드립니다.")
				}
			}		
			
		});
		
	}
</script>
</body>
</html>