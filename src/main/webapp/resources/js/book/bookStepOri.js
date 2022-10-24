
//유료여부 체크

function checkPay(seriesNo, bookNo){
	$.ajax({
		url:"/book/checkPaidbookSeries.do",
		type:"get",
		data:{"seriesNo":seriesNo,"bookNo":bookNo},
		success:function(result){
			if(result=='N '){
				 readDetailSeries(seriesNo,bookNo);
			}
			else{
				alert('유료도서'+ result)
			}
		
		},
		error:function(){}
	
	})


}

//도서 읽기
function readDetailSeries(seriesNo,bookNo){
	location.href="/book/OridetailSeries.do?seriesNo="+seriesNo+"&bookNo="+bookNo;
}