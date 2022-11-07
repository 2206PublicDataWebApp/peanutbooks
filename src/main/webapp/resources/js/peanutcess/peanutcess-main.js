$('.status-area').css('display','none');
$('#sche-area2').css('display','none');



function startMain(){
$('#sche-area1').css('display','block');

}

$('#viewStatus').on('click',function(){

		$('.status-area').css('display','block');


})

var scheduler = [];

$('#sche-plus').on('click',function(){



	$('#sche-area2').css('display','block');
	var str = '';
	str+= '<ul><li id="job">아르바이트를 한다</li>'
	str+='<li id="school">공부를 한다</li>'
	str+='<li id="rest">20살까지 아무것도 하지 않는다</li>'
	$('#sche-area2').html(str);
	$('#text').html('공주님의 스케쥴을 지정해주세요<br> 한달에 1개씩 총 4번의 스케쥴을 정할수 있어요')
	
	$('#job').on('click',function(){
		var str = '';
		str+= '<ul><li id="fram">농장알바</li>'
		str+='<li id="child">보모알바</li>'
		str+='<li id="cafe">식당알바</li>'
		$('#sche-area2').html(str);
		
		$('#fram').on('click',function(){
			if(scheduler.length<4){
				scheduler.push('farm');
				$('#text').html(scheduler.length+'번째 스케쥴은 농장알바를 해요');
				//스케쥴 꽉차면 실행하기
				if(scheduler.length==4){
					alert('스케쥴실행')
					}
		
			}else{
			$('#text').html('스케쥴이 꽉 찼어요');
			}	
		});
		
		$('#child').on('click',function(){
			if(scheduler.length<4){
				scheduler.push('child');
				$('#text').html(scheduler.length+'번째 스케쥴은 보모알바를 해요');
				//스케쥴 꽉차면 실행하기
				if(scheduler.length==4){
					alert('스케쥴실행')
					}
			}else{
			$('#text').html('스케쥴이 꽉 찼어요');
			}	
		});
		
		$('#cafe').on('click',function(){
			if(scheduler.length<4){
				scheduler.push('cafe');
				$('#text').html(scheduler.length+'번째 스케쥴은 카페알바를 해요');
				//스케쥴 꽉차면 실행하기
				if(scheduler.length==4){
					alert('스케쥴실행')
					}
			}else{
			$('#text').html('스케쥴이 꽉 찼어요');
			}	
		});
		
		
		
	});
	
	$('#school').on('click',function(){
		var str = '';
		str+= '<ul><li id="music">음악교실</li>'
		str+='<li id="art">미술교실</li>'
		str+='<li id="material">무술교실</li>'
		$('#sche-area2').html(str);
		
		$('#music').on('click',function(){
			if(scheduler.length<4){
				scheduler.push('music');
					$('#text').html(scheduler.length+'번째 스케쥴은 음악공부를 해요');
					//스케쥴 꽉차면 실행하기
				if(scheduler.length==4){
					alert('스케쥴실행')
					}
			
			}else{
			$('#text').html('스케쥴이 꽉 찼어요');
			}	
		});
		$('#art').on('click',function(){
			if(scheduler.length<4){
				scheduler.push('art');
				$('#text').html(scheduler.length+'번째 스케쥴은 미술공부를 해요');
				//스케쥴 꽉차면 실행하기
				if(scheduler.length==4){
					alert('스케쥴실행')
					}
			}else{
			$('#text').html('스케쥴이 꽉 찼어요');
			}	
		});
		$('#material').on('click',function(){
			if(scheduler.length<4){
				scheduler.push('material');
				$('#text').html(scheduler.length+'번째 스케쥴은 무술공부를 해요');
				//스케쥴 꽉차면 실행하기
				if(scheduler.length==4){
					alert('스케쥴실행')
					}
			}else{
			$('#text').html('스케쥴이 꽉 찼어요');
			}	
		});
		
		
		//스케쥴 꽉차면 실행하기
				if(scheduler.length==4){
					alert('스케쥴실행')
					}
	
	
	});
	
	$('#rest').on('click',function(){
	$.ajax({})//스테이터스 전달해서 엔딩보기
	
	});
	
	
	
		


})

$('#gotoShop').on('click',function(){

	$('#sche-area2').css('display','block');
	var str = '';
	str+= '<ul><li id="buy">물건을 산다</li>'
	$('#sche-area2').html(str);

	$('#text').html('상점에서 물건을 살수 있어요')

})

