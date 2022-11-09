
if(mon==13){
	mon = 1;
	age = age+1
}

$('#age-area').html(age+'살 '+mon+'월');



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
	$('#sche-area2').html('');
	$('#sche-area2').css('display','block');
	var str = '';
	str+= '<ul><li id="job" class="hover">아르바이트를 한다</li>'
	str+='<li id="school"  class="hover">공부를 한다</li>'
	str+='<li  class="hover" onclick="restEnd();" >20살까지 논다</li>'
	$('#sche-area1').html(str);
	$('#text').html('공주님의 스케쥴을 지정해주세요<br> 한달에 1개씩 총 4번의 스케쥴을 정할수 있어요')
	
	$('#job').on('click',function(){
		var str = '';
		str+= '<ul><li id="fram"  class="hover">농장알바(10피넛↑)</li>'
		str+='<li id="child"  class="hover">보모알바(10피넛↑)</li>'
		str+='<li id="cafe"  class="hover">식당알바(10피넛↑)</li>'
		str+='<li id="Onerest"  class="hover">휴식한다</li>'
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
		
		$('#Onerest').on('click',function(){
			if(scheduler.length<4){
				scheduler.push('rest');
				$('#text').html(scheduler.length+'번째 스케쥴은 휴식을 해요');
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
		str+= '<ul><li id="music"  class="hover">음악교실(20피넛↓)</li>'
		str+='<li id="art"  class="hover">미술교실(20피넛↓)</li>'
		str+='<li id="material"  class="hover">무술교실(20피넛↓)</li>'
		str+='<li id="Onerest"  class="hover">휴식한다</li>'
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
		
		$('#Onerest').on('click',function(){
			if(scheduler.length<4){
				scheduler.push('rest');
				$('#text').html(scheduler.length+'번째 스케쥴은 휴식을 해요');
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
	
	
	
	
	
		


}



//스케쥴실행
function startSche(){
	var str = '';
	str += '<ul><li  class="hover" id="goSche">스케쥴을 실행한다</li>'
	str += '<li  class="hover" id="stopSche"> 스케쥴을 다시짠다</li></ul>'
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
			KrSche[i] = '미술교실'
		}else if(scheduler[i] == 'rest'){
			KrSche[i] = '휴식한다'
		}
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
	$('#goSche').on('click',function(){
		location.href="/peanutcess/scheDo1.do?sche1="+scheduler[0]+"&sche2="+scheduler[1]+"&sche3="+scheduler[2]+"&sche4="+scheduler[3]
	});

}


//상점가기
$('#gotoShop').on('click',function(){
	$('#sche-area2').html('');
	$('#sche-area2').css('display','block');
	var str = '';
	str+= '<ul><li id="buy"  class="hover" onclick="buyStore();">물건을 산다</li>'
	$('#sche-area2').html(str);

	$('#text').html('상점에서 물건을 살수 있어요');

})


//20살까지 놀기

function restEnd() {
if(confirm('바로 엔딩을 볼까요?')){
	location.href='/book/restEnd.do'
}

}

//물건사기
function buyStore(){
	 var str = '';
	 str+= '<ul><li  class="hover" onclick="rotto();">복권을 산다.(50피넛)</li>';
	 str+= '<li  class="hover" onclick="item()">아이템을 산다.</li></ul>';

	$('#sche-area2').html(str);

}

//로또하기

 function rotto(){
 	if(money<50){
 		$('#text').html('돈이 없어요');
 	}else{
 	 if(confirm('복권을 구매합니다')){
 	 
 	 	$.ajax({
 	 		url:"/peanutcess/rotto.do",
 	 		type:"get",
 	 		success:function(result){
 	 		console.log(result);
 	 			if(result=='true'){
 	 				alert('당첨되었습니다!');
 	 				alert('200피넛을 받습니다!');
 	 				money = money+150;
 	 				$('#money-area').html('소지금 : '+money+' 피넛');
 	 			}else{
 	 				alert('꽝...');
 	 				alert('50피넛을 잃었습니다....');
 	 				money = money-50;
 	 				$('#money-area').html('소지금 : '+money+' 피넛');
 	 			
 	 			}
 	 		}
 	 	
 	 	
 	 	})
 	 
 	 }
 	}
 
 }
 
 //아이템사기 
 function item(){
 	var str = '';
 	str += '<ul><li  class="hover" onclick="buyItem(\'artBook\')">그림책 100피넛</li>';
 	str += '<li  class="hover" onclick="buyItem(\'musicBook\')">악보 100피넛</li>';
 	str += '<li class="hover" onclick="buyItem(\'knife\')">목검 100피넛</li></ul>';
 	$('#sche-area2').html(str);
 
 }

//아이템별 구입
function buyItem(item){
	if(money<100){
		
		$('#text').html('돈이 없어요');
	
	}else{
		$.ajax({
			url:"/peanutcess/buyitem.do",
			type:"get",
			data:{"item":item},
			success:function(result){
				alert('아이템을 구입하셨습니다.');
				if(item=='artBook'){
					alert('미술이 10, 공부가 10 증가합니다.');
				}else if(item=='musicBook'){
					alert('음악이 10, 예절이 10 증가합니다.');
				}else{
					alert('힘이 10, 근성이 10 증가합니다.');
				}
				
				$('#statusPower').html(result.power);
				$('#statuscook').html(result.cook);
				$('#statusmanner').html(result.manner);
				$('#statusmusic').html(result.music);
				$('#statusPower').html(result.power);
				$('#statusart').html(result.art);
				$('#statusstudy').html(result.study);
				$('#statusstrong').html(result.strong);
				$('#money-area').html('소지금 :'+result.money+ '피넛');
				money = result.money;

				
				
			}
		
		})
}
}
