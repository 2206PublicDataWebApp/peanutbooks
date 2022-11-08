$('.status-area').css('display','none');
$('#sche-area2').css('display','none');



function startMain(){
$('#sche-area1').css('display','block');

}


//스테이터스 창 보기
$('#viewStatus').on('click',function(){

	$('.status-area').css('display','block');

})


//스케쥴 배열
var scheduler = [];

//스케쥴 입력하기 누르면
$('#sche-plus').on('click',function(){makeSche()});

//스케쥴 입력하기
function makeSche(){
	$('#sche-area2').css('display','block');
	var str = '';
	str+= '<ul><li id="job">아르바이트를 한다</li>'
	str+='<li id="school">공부를 한다</li>'
	str+='<li id="rest">20살까지 논다</li>'
	$('#sche-area1').html(str);
	$('#text').html('공주님의 스케쥴을 지정해주세요<br> 한달에 1개씩 총 4번의 스케쥴을 정할수 있어요')
	
	$('#job').on('click',function(){
		var str = '';
		str+= '<ul><li id="fram">농장알바(10피넛↑)</li>'
		str+='<li id="child">보모알바(10피넛↑)</li>'
		str+='<li id="cafe">식당알바(10피넛↑)</li>'
		$('#sche-area2').html(str);
		
		$('#fram').on('click',function(){
			if(scheduler.length<4){
				scheduler.push('farm');
				$('#text').html(scheduler.length+'번째 스케쥴은 농장알바를 해요');
				//스케쥴 꽉차면 실행하기
				if(scheduler.length==4){
					startSche();
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
					startSche();
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
					startSche();
					}
			}else{
			$('#text').html('스케쥴이 꽉 찼어요');
			}	
		});
		
		
		
	});
	
	$('#school').on('click',function(){
		var str = '';
		str+= '<ul><li id="music">음악교실(20피넛↓)</li>'
		str+='<li id="art">미술교실(20피넛↓)</li>'
		str+='<li id="material">무술교실(20피넛↓)</li>'
		$('#sche-area2').html(str);
		
		$('#music').on('click',function(){
			if(scheduler.length<4){
				scheduler.push('music');
					$('#text').html(scheduler.length+'번째 스케쥴은 음악공부를 해요');
					//스케쥴 꽉차면 실행하기
				if(scheduler.length==4){
					startSche();
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
					startSche();
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
					startSche();
					}
			}else{
			$('#text').html('스케쥴이 꽉 찼어요');
			}	
		});
		
		
		//스케쥴 꽉차면 실행하기
				if(scheduler.length==4){
					startSche();
					}
	
	
	});
	
	$('#rest').on('click',function(){
	$.ajax({})//스테이터스 전달해서 엔딩보기
	
	});
	
	
	
		


}



//스케쥴실행
function startSche(){
	var str = '';
	str += '<ul><li id="goSche">스케쥴을 실행한다</li>'
	str += '<li id="stopSche"> 스케쥴을 다시짠다</li></ul>'
	$('#sche-area1').html(str);//스케쥴 실행여부 붇기
	
	var str1 = '<ol>';
	
	var KrSche = [];
	for(var i =0; i<4;i++){
		if(scheduler[i] == 'farm'){
			KrSche[i] = '농장알바'
		}else if(scheduler[i] == 'child'){
			KrSche[i] = '보모알바'
		}else if(scheduler[i] == 'cafe'){
			KrSche[i] = '식당알바'
		}else if(scheduler[i] == 'music'){
			KrSche[i] = '음악교실'
		}else if(scheduler[i] == 'material'){
			KrSche[i] = '무술교실'
		}else if(scheduler[i] == 'art'){
			KrSche[i] = '미술교실'}
	}
	
	
	str1 +='<li>'  +KrSche[0]+ '</li>';
	str1 +='<li>'  +KrSche[1]+ '</li>';
	str1 +='<li>'  +KrSche[2]+ '</li>';
	str1 +='<li>'  +KrSche[3]+ '</li>';
	$('#sche-area2').html(str1);//스케쥴 보여주기
	
	//스케쥴을 다시 짰을때
	$('#stopSche').on('click',function(){
		scheduler = []; //배열비우기
		$('#sche-area2').html('');//내용비우기
		makeSche();
	})
	
	//스케쥴 실행
//	$('#goSche').on('click',function(){
//		$.ajax({
//			url:"",
//			type:"post",
//			data:{"sche1":},
//			success:function(){}
//			
		
//		})
//	})


}


//상점가기
$('#gotoShop').on('click',function(){

	$('#sche-area2').css('display','block');
	var str = '';
	str+= '<ul><li id="buy">물건을 산다</li>'
	$('#sche-area2').html(str);

	$('#text').html('상점에서 물건을 살수 있어요');

})

