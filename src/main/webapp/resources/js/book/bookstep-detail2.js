//다음화 연결
document.querySelector('#next-button').onclick=function(){

	 $.ajax({
	 url:"/book/OrinextSeires.do",
	 type:"get",
	 data:{"bookNo":bookNo,"seriesNo":seriesNo},
	 success:function(result){
	 console.log(result);
		 if(result=="no:end"){
		 	 alert('마지막 화 입니다.');
		 }else if(result==""){
			 alert('로그인하세요'); 
		 }else if(result.paid=='no'){
		 	if(confirm('해당도서를 땅콩 하나로 구입하시겠습니까?')){
		 	
		 	buyThisSeries(result.nextSeriesNo,bookNo);
		 	
		 	};
		 }else{
			 window.scrollTo(0,0);
			document.querySelector('.header-area2').style.backgroundImage="linear-gradient(to bottom,rgba(232, 232, 232, 0),rgba(255, 255, 255, 1)),url('/resources/bookImg/"+result.subPicRename+"')"
		 	var bookTitle =result.bookTitle;
		 	var subTitle = result.seriesNo+'. '+result.title;
		 	var contents = result.contents;
		 	$('.title-area2').html(bookTitle);
		 	$('.step-title2').html(subTitle);
		 	$('.contents-area2').html(contents);
		 	document.querySelector('#two').style.display='block';
			
			if(window.scrollY>0){ //스크롤이 있다면 스크롤실행후 다음화 넘김
			window.addEventListener('scroll', function(){
				 if(window.scrollY == 0){//스크롤이 전부 올라왔을때
				nextAnimation(result.seriesNo)
				}
				})
			}else{ //없으면 바로 넘김
			nextAnimation(result.seriesNo);
			}
			
		
		 	
		 }
	 },
	 error:function(){
	 	alert('시스템오류, 오류가 반복되면 관리자에게 신고하세요');
	 }
	 
	 })


}



	//다음화or이전화 애니메션 후 넘기기		
	function nextAnimation(seriesNo){
		
		document.querySelector('#One').style.transformOrigin="0% 0%";
				
		document.querySelector('#One').style.animation="rotateFall 0.8s both ease-in";
		document.querySelector('#two').style.animation="scaleUp .6s ease both";
				
		setTimeout(function(){	
			document.querySelector('#One').style.display='none';			
			location.href="/book/OridetailSeries.do?bookNo="+bookNo+"&seriesNo="+seriesNo;
		}, 800);

		 			
		 	}
		 	
		 	
		 	
		 	//이전화 연결
document.querySelector('#prev-button').onclick=function(){

	 $.ajax({
	 url:"/book/OriprevSeires.do",
	 type:"get",
	 data:{"bookNo":bookNo,"seriesNo":seriesNo},
	 success:function(result){
	
		 if(result=="no:one"){
		 	 alert('첫 화 입니다.');
		 }else if(result==""){
			 alert('로그인하세요');
		}else if(result.paid=='no'){
		 	if(confirm('해당도서를 땅콩 하나로 구입하시겠습니까?')){
		 	
		 	buyThisSeries(result.prevSeriesNo,bookNo);
		 	
		 	};	  
		 }else{
			 window.scrollTo(0,0);
			document.querySelector('.header-area2').style.backgroundImage="linear-gradient(to bottom,rgba(232, 232, 232, 0),rgba(255, 255, 255, 1)),url('/resources/bookImg/"+result.subPicRename+"')"
		 	var bookTitle =result.bookTitle;
		 	var subTitle = result.seriesNo+'. '+result.title;
		 	var contents = result.contents;
		 	$('.title-area2').html(bookTitle);
		 	$('.step-title2').html(subTitle);
		 	$('.contents-area2').html(contents);
		 	document.querySelector('#two').style.display='block';
			
			if(window.scrollY>0){ //스크롤이 있다면 스크롤실행후 다음화 넘김
			window.addEventListener('scroll', function(){
				 if(window.scrollY == 0){//스크롤이 전부 올라왔을때
				nextAnimation(result.seriesNo)
				}
				})
			}else{ //없으면 바로 넘김
			nextAnimation(result.seriesNo);
			}
			
		
		 	
		 }
	 },
	 error:function(result){
	  console.log(result);
	 	alert('시스템오류, 오류가 반복되면 관리자에게 신고하세요');
	 }
	 
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
						 nextAnimation(seriesNo);//시리즈 상세 페이지 연결
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



