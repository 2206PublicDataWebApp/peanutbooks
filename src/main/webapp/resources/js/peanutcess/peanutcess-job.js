

$('.status-area').css('display','none');
$('#sche-area2').css('display','none');

$('.age-area').html(age+'살'+monThis+'월');
var desa =1;

//대사창 누르면 실행
function nextText(){
	jobText();
	desa++;
};



function jobText(){
	
	if(thisTurn == 1){
		
		if(sche1=='farm'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/farm.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_farm.jpg)');		
			if(desa==1){
				$('#text').html('농장 알바를 합니다.');
			}else if(desa == 2){
				console.log(desa);
				$('#text').html('농장알바<br>힘(+5), 근성(+2),<br> 용돈(+10), 스트레스(+5)');
			}else if(sche1Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche1Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다');
				next();
				
			}
		
		}
		else if(sche1=='cafe'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/cafe.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_cafe.png)');
			if(desa==1){
				$('#text').html('식당 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('식당알바<br>요리(+5), 근성(+2),<br> 용돈(+10), 스트레스(+5)');
			}else if(sche1Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche1Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다');
				next();
			}
		}
		else if(sche1=='child'){
				$('#sche-farm').html('<img src="/resources/img/peanutcess/child.gif">');
				$('#center-area').css('background','url(/resources/img/peanutcess/bg_child.png)');
			if(desa==1){
	
				$('#text').html('보모 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('보모알바<br>공부(+5), 예절(+2),<br> 용돈(+10), 스트레스(+10)');
			}else if(sche1Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche1Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다');
				next();
			}
		
		}
		else if(sche1=='art'){
				$('#sche-farm').html('<img src="/resources/img/peanutcess/art.gif">');
				$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
			if(desa==1){
				
				$('#text').html('미술공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('미술공부<br>공부(+5), 미술(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche1Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
			}else{
				alert('다음스케쥴을 진행합니다');
				next();
			}
		
		}
		else if(sche1=='material'){
				$('#sche-farm').html('<img src="/resources/img/peanutcess/kendo.gif">');
				$('#center-area').css("background","url(/resources/img/peanutcess/bg_school.jpg)");
			if(desa==1){
				
				$('#text').html('무술공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('무술공부<br>힘(+5), 근성(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche1Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche1Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다');
				next();
			}
			
		}
		else if(sche1=='music'){
		$('#sche-farm').html('<img src="/resources/img/peanutcess/music.gif">');
		$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
			
			if(desa==1){
				
				$('#text').html('음악공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('음악공부<br>음악(+5), 예절(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche1Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche1Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다');
				next();
			}
		
		}
		else if(sche1=='rest'){
		$('#sche-farm').html('');
		$('#center-area').css('background','url()');
		
			if(desa==1){
				
				
				$('#text').html('휴식을 합니다.');
			}else if(desa == 2){
				if(sche1Result != 'ok'){
				$('#text').html('스케쥴을 진행할수 없습니다 <br>휴식을 합니다. 스트레스(-10)');
				}else{
				$('#text').html('휴식 스트레스(-10)');}
			}else if(sche1Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche1Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다');
				next();
			}
		
		}

	}
	
	
	
	if(thisTurn == 2){
		$('.age-area').html(age+'살'+(monThis+1)+'월');
	
			$('#sche-farm').html('<img src="/resources/img/peanutcess/farm.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_farm.jpg)');
		if(sche2=='farm'){
			if(desa==1){
			
				$('#text').html('농장 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('농장알바<br>힘(+5), 근성(+2),<br> 용돈(+10), 스트레스(+5)');
			}else if(sche2Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche2Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche2=='cafe'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/cafe.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_cafe.png)');
		
			if(desa==1){
				$('#text').html('식당 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('식당알바<br>요리(+5), 근성(+2),<br> 용돈(+10), 스트레스(+5)');
			}else if(sche2Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche2Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		}
		else if(sche2=='child'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/child.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_child.png)');
			if(desa==1){
				$('#text').html('보모 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('보모알바<br>공부(+5), 예절(+2),<br> 용돈(+10), 스트레스(+10)');
			}else if(sche2Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche2Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche2=='art'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/art.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
		
			if(desa==1){
				$('#text').html('미술공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('미술공부<br>공부(+5), 미술(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche2Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche2Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche2=='material'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/kendo.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
			
			if(desa==1){
				$('#text').html('무술공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('무술공부<br>힘(+5), 근성(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche2Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche2Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
			
		}
		else if(sche2=='music'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/music.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
		
			if(desa==1){
				$('#text').html('음악공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('음악공부<br>음악(+5), 예절(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche2Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche2Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche2=='rest'){
			$('#sche-farm').html('');
			$('#center-area').css('background','url()');
		
			if(desa==1){
				$('#text').html('휴식을 합니다.');
			}else if(desa == 2){
				if(sche2Result != 'ok'){
				$('#text').html('스케쥴을 진행할수 없습니다 <br>휴식을 합니다. 스트레스(-10)');
				}else{
				$('#text').html('휴식 스트레스(-10)');}
			}else if(sche2Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche2Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}

	}
	
	if(thisTurn == 3){
	$('.age-area').html(age+'살'+(monThis+2)+'월');
		if(sche3=='farm'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/farm.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_farm.jpg)');
			if(desa==1){
				$('#text').html('농장 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('농장알바<br>힘(+5), 근성(+2),<br> 용돈(+10), 스트레스(+5)');
			}else if(sche3Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche3Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche3=='cafe'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/cafe.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_cafe.png)');
		
			if(desa==1){
				$('#text').html('식당 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('식당알바<br>요리(+5), 근성(+2),<br> 용돈(+10), 스트레스(+5)');
			}else if(sche3Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche3Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		}
		else if(sche3=='child'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/child.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_child.png)');
			if(desa==1){
				$('#text').html('보모 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('보모알바<br>공부(+5), 예절(+2),<br> 용돈(+10), 스트레스(+10)');
			}else if(sche1Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			} 
		
		}
		else if(sche3=='art'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/art.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
		
			if(desa==1){
				$('#text').html('미술공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('미술공부<br>공부(+5), 미술(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche3Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche3Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche3=='material'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/kendo.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
		
			if(desa==1){
				$('#text').html('무술공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('무술공부<br>힘(+5), 근성(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche3Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche3Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
			
		}
		else if(sche3=='music'){
		$('#sche-farm').html('<img src="/resources/img/peanutcess/music.gif">');
		$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
			if(desa==1){
				$('#text').html('음악공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('음악공부<br>음악(+5), 예절(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche3Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche3Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche3=='rest'){
		$('#sche-farm').html('');
		$('#center-area').css('background','url()');
		
			if(desa==1){
				$('#text').html('휴식을 합니다.');
			}else if(desa == 2){
				if(sche3Result != 'ok'){
				$('#text').html('스케쥴을 진행할수 없습니다 <br>휴식을 합니다. 스트레스(-10)');
				}else{
				$('#text').html('휴식 스트레스(-10)');}
			}else if(sche3Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche3Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}

	}
	
	if(thisTurn == 4){
	$('.age-area').html(age+'살'+(monThis+3)+'월');
		if(sche4=='farm'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/farm.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_farm.jpg)');
			if(desa==1){
				$('#text').html('농장 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('농장알바<br>힘(+5), 근성(+2),<br> 용돈(+10), 스트레스(+5)');
			}else if(sche4Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche4Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche4=='cafe'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/cafe.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_cafe.png)');
			if(desa==1){
				$('#text').html('식당 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('식당알바<br>요리(+5), 근성(+2),<br> 용돈(+10), 스트레스(+5)');
			}else if(sche4Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche4Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		}
		else if(sche4=='child'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/child.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_child.png)');
				
			if(desa==1){
				$('#text').html('보모 알바를 합니다.');
			}else if(desa == 2){
				$('#text').html('보모알바<br>공부(+5), 예절(+2),<br> 용돈(+10), 스트레스(+10)');
			}else if(sche4Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche4Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche4=='art'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/art.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
		
			if(desa==1){
				$('#text').html('미술공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('미술공부<br>공부(+5), 미술(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche4Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche4Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche4=='material'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/kendo.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
			
			if(desa==1){
				$('#text').html('무술공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('무술공부<br>힘(+5), 근성(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche4Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche4Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
			
		}
		else if(sche4=='music'){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/music.gif">');
			$('#center-area').css('background','url(/resources/img/peanutcess/bg_school.jpg)');
			if(desa==1){
				$('#text').html('음악공부를 합니다.');
			}else if(desa == 2){
				$('#text').html('음악공부<br>음악(+5), 예절(+5),<br> 용돈(-20), 스트레스(+10)');
			}else if(sche4Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche4Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
		else if(sche4=='rest'){
			$('#sche-farm').html('');
			$('#center-area').css('background','url()');
		
			if(desa==1){
				$('#text').html('휴식을 합니다.');
			}else if(desa == 2){
				if(sche4Result != 'ok'){
				$('#text').html('스케쥴을 진행할수 없습니다 <br>휴식을 합니다. 스트레스(-10)');
				}else{
				$('#text').html('휴식 스트레스(-10)');}
			}else if(sche4Birth == true){
				$('#text').html('공주님의 생일이네요!<br> 용돈 20피넛을 받았습니다');
				sche4Birth=false;
			}else{
				alert('다음스케쥴을 진행합니다'); next();
			}
		
		}
	}


}

function next(){
	if(thisTurn != 4){
		thisTurn++;
		desa=1;
		nextText();
	}else if(thisTurn == 4){
		location.href='/book/gameStarting';
	}
}
