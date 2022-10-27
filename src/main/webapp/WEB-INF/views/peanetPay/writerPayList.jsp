<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작가정산요청서</title>
<link rel="stylesheet" href="../resources/css/pay/writerpayList.css">
	<script src="../resources/js/jquery-3.6.0.min.js" ></script>
</head>
<body>
<h1>정산 접수 내역</h1>
<main id="putMain">
    <div id="table">
        <table>
            <thead>
                <tr>
                    <th>접수번호</th>
                    <th>고객ID</th>
                    <th class="768moni">시리즈번호</th>
                    <th>책번호</th>
                    <th>정산땅콩</th>
                    <th>정산금액</th>                
                    <th>접수일</th>
                    <th>지급은행</th>
                    <th>지급계좌</th>
                    <th>승인여부</th>
                </tr>
            </thead>
            <tbody id="tb">
               
            </tbody>
        </table>     
     
    
    </div>
</main>
    <div class="page_wrap">
        <div class="page_nation">
           <a class="arrow pprev" href="#">처음</a>
           <a class="arrow prev" href="#">이전</a>
           <a href="#" class="active">1</a>
           <a href="#">2</a>
           <a href="#">3</a>
           <a href="#">4</a>
           <a href="#">5</a>           
           <a class="arrow next" href="#">다음</a>
           <a class="arrow nnext" href="#">끝</a>
        </div>
     </div>
</body>
<script>
	$(document).ready(function(){
		$.ajax({
			url:"/writer/listprint.kh",
			type:"get",
			dataType:"json",
			success:function(result){
				console.log(result);
				if(!(result=="error")){
					for(var i=0; i<result.length; i++){
						var a="<tr>"+
							  "<td>"+result[i].wrpayNo+"</td>"+
							  "<td>"+result[i].memberId+"</td>"+
							  "<td>"+result[i].seriesNo+"</td>"+
							  "<td>"+result[i].ori_bookNo+"</td>"+
							  "<td>"+result[i].changeP+"</td>"+
							  "<td>"+result[i].payment+"</td>"+
							  "<td>"+result[i].putDate+"</td>"+
							  "<td>"+result[i].bankName+"</td>"+
							  "<td>"+result[i].bankNo+"</td>";
						if(result[i].bankStatus=='N'){
							a+='<td id="wrStratus'+i+'"><input class="okbtn" type="button" onclick="putWRpay('+result[i].wrpayNo+');" value="승인"></td>';
						}else{
							  a+='<td id="wrStratus'+i+'">'+승인완료+'</td></tr>';					
						};							  
							  
						$("#tb").append(a);
					}			
				}else{
					alert("실패 ");	
				};
			},
			error:function(e){
				alert("에러"+e);
			}
		});
	})
	
	function putWRpay(wrpayNo){
		$.ajax({
			url:"",
			type:"post",
			data:{"wrpayNo":wrpayNo},
			success:function(result){
				if(result=="success"){
					alert("접수 완료");
				}else{
					alert("실패");
				}
			}
		});
	}
	


	
</script>
</html>