
//유료여부 체크

function checkPay(seriesNo, bookNo){
	$.ajax({
		url:"/book/checkPaidbookSeries.do",
		type:"get",
		data:{"seriesNo":seriesNo,"bookNo":bookNo},
		success:function(result){
			if(result=='N '){
				 readDetailSeries(seriesNo,bookNo); //시리즈 상세페이지 연결
			}
			else{
				checkPurchase(seriesNo,bookNo); //구입여부 체크
				
			}
		
		},
		error:function(){}
	
	})


}

//도서 구입여부 체크
function checkPurchase(seriesNo,bookNo){
	$.ajax({
			url:"/book/checkPurchase.do",
			type:"get",
			data:{"seriesNo":seriesNo,"bookNo":bookNo},
			success:function(result){
				if(result>0){
					 readDetailSeries(seriesNo,bookNo);//시리즈 상세 페이지 연결
				}
				else{
					if(confirm('해당 시리즈를 땅콩 1개로 구입하시겠습니까?')){
						buyThisSeries(seriesNo,bookNo); //구매 함수로
						
					}
					
				}
			
			},
			error:function(){}
	})

}

//도서 구매하기
function buyThisSeries(seriesNo,bookNo){
	$.ajax({
				url:"/book/buyThisSeries.do",
				type:"get",
				data:{"seriesNo":seriesNo,"bookNo":bookNo},
				success:function(result){
					if(result>=1){
						 readDetailSeries(seriesNo,bookNo);//시리즈 상세 페이지 연결
					}
					else{
						if(confirm('땅콩이 부족합니다, 구입하시겠습니까?')){
							location.href="/pay/start.kh";  //구매 함수로
						}
						
					}
				
				},
				error:function(){}
		})

}

//도서 읽기
function readDetailSeries(seriesNo,bookNo){
	location.href="/book/OridetailSeries.do?seriesNo="+seriesNo+"&bookNo="+bookNo;
}