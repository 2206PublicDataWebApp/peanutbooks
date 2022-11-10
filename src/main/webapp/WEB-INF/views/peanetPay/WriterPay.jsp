<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작가 정산 요청</title>
<link rel="stylesheet" href="../resources/css/pay/writerPay.css">
<script src="../resources/js/jquery-3.6.0.min.js" ></script>
</head>
<body>
<jsp:include page="../header/header.jsp"></jsp:include>

<section>
<h3 id="h">작가 정산 요청</h3>
<div>
	 <table>
            <tr>
                <td>도서 번호 :</td>
                <td>
	                <select name="bookTitle" id="bTitle" onchange="bookNo();">
	                	<option value="">도서 선택</option>
	                	<c:forEach items="${o_bookList}" var="originBook" varStatus="i" >
	                		<option value="${originBook.bookNo }">${originBook.bookTitle }</option>
	                	</c:forEach>
	                </select>
                </td>                
            </tr>
            <tr>
                <td>시리즈 번호 :</td>
                <td>
                	<select name="seriesNo" id="seriesNum" onchange="howPeanet();">
	                	<option value="">시리즈 선택</option>	                	
	                </select>                
                </td>
            </tr>
            <tr>
                <td>누적 된 땅콩 :</td>
                <td><input type="text"name="paidCount" id="paidCount"></td>
            </tr>
            <tr>
                <td>차감 할 땅콩 :</td>
                <td><input type="text" name="changeP" id="changeP"></td>
            </tr>
            <tr>
                <td>지급금액 :</td>
                <td><input type="text" name="payment" id="payment" readonly></td>
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
    <div class="btn"><button id="paybtn" onclick="writerPay();">지급요청</button></div>   
    
</section>

<jsp:include page="../footer/footer.jsp"></jsp:include>

<script>
	//책번호로 시리즈 정보 조회
	function bookNo(){
		var bookNo=$('#bTitle option:selected').val();
		$.ajax({
			url : '/writer/bookNo.kh',
			type : 'post',
			dataType : 'json',
			data : {
				'bookNo'   : bookNo				 
			},
			success : function(result){
				$('#seriesNum').children('option:not(:first)').remove();
				result.forEach(function (OriginBookSeries,i) {
				$('#seriesNum').append('<option value="'+OriginBookSeries.seriesNo+'" name="'+OriginBookSeries.paidCount+'">'						
						+OriginBookSeries.title+'</option>');
				});
			},
			error : function(e){
				alert('error : '+e.statusText);
			}		
		});		
	}
	function howPeanet(){
		var peanut = $('#seriesNum option:selected').attr('name');
		$('#paidCount').val(peanut);
	}
	
	$('#changeP').on('keyup',function(){
		var changeP=$('#changeP').val();
		
		if(changeP.length>=4){
			$("#payment").val(changeP*100);
		};
		var peanut = $('#seriesNum option:selected').attr('name');
		if(parseInt(changeP)>parseInt(peanut)){
			alert("땅콩을 초과 입력하셨습니다. 다시 입력하세요");
			$('#changeP').val("");
			$('#payment').val("");
			$('#changeP').focus();
		}
		
	})
	

	function writerPay(){
		
		if(confirm("계좌번호를 확인하셨습니까?")){

			var memberId   = '${sessionScope.loginMember.memberId}';
			var seriesNo   = $('#seriesNum option:selected').val();
			var ori_bookNo = $('#bTitle option:selected').val();
			var changeP    = $('#changeP').val();
			var payment    = $('#payment').val();
			var bankName   = $('#bankName').val();
			var bankNo     = $('#bankNo').val();		
			$.ajax({
				url : '/writer/receipt.kh',
				type : 'post',
				data : {
					'memberId'   : memberId,
					'seriesNo'   : seriesNo,
					'ori_bookNo' : ori_bookNo,
					'changeP'    : changeP,
					'payment'    : payment,
					'bankName'   : bankName,			
					'bankNo'     : bankNo				
				},
				success : function(result){
					if(result=='success'){
						if(confirm('정상접수되었습니다.')){
							location.href="/writer/writerStart.kh?memberId="+memberId;
						};
						
					}else{
						alert('미접수되었습니다. 다시한번 부탁드립니다.')
					}
				},
				error : function(e){
					alert('error : '+e.statusText);
				}				
			});
		};
		
	}
</script>
</body>
</html>