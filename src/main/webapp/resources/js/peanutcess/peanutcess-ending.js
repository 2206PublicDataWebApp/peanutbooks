

$('.status-area').css('display','none');
$('#sche-area2').css('display','none');


var desa =1;

//대사창 누르면 실행
function nextText(){
	endigText();
	desa++;
};



function endigText(){	
	$('#sche-farm').html('<img src="/resources/img/peanutcess/animal_character_hamster.png">');
	if(desa==1){
		$('#text').html(name+'공주님은 어떻게 성장했을까요?')
	}
	
	if(ending=='neet'){
		if(desa==2){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/animal_character_hamster_kurushii.png">');
			$('#text').html('이럴수가... 공주님은 백수가 되었어요')
		}else if(desa == 3){
			$('#text').html('실망이예요...')
		}else if(desa == 4){
			$('#sche-farm').html('<img src="/resources/img/peanutcess/neet.png">');
			$('#name-area').html(name)
			$('#text').html('뭐하고 놀지.. 이제 놀것도 없네')
		}else if(desa == 5){
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href='/book/Peanutcess.do';
		}
		
	
	}else if(ending=='farmEnd'){
	
		if(desa==2){
			$('#text').html('공주님은 농부가 되었어요!')
			}else if(desa == 3){
			$('#text').html('축하합니다!')
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href='/book/Peanutcess.do';
			}
		
	}else if(ending=='cafeEnd'){
	
			if(desa==2){
			$('#text').html('공주님은 식당 직원이 되었어요!')
			}else if(desa == 3){
				$('#text').html('축하합니다!')
				alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
				location.href='/book/Peanutcess.do';
			}
	}else if(ending=='childEnd'){
		if(desa==2){
			$('#text').html('공주님은 어린이집 선생님이 되었어요!')
			}else if(desa == 3){
				$('#text').html('축하합니다!')
				alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
				location.href='/book/Peanutcess.do';
			}
	
	}else if(ending=='freetor'){
		if(desa==2){
			$('#text').html('아르바이트생이 되었어요!')
			}else if(desa == 3){
				$('#text').html('축하합니다!')
				alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
				location.href='/book/Peanutcess.do';
			}
	
	}else if(ending=='genal'){
		if(desa==2){
			$('#text').html('공주님은 장군이 되었어요!')
			}else if(desa == 3){
				$('#text').html('축하합니다!')
				alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
				location.href='/book/Peanutcess.do';
			}
	
	}else if(ending=='musician'){
		if(desa==2){
			$('#text').html('공주님은 음악가가 되었어요!')
			}else if(desa == 3){
				$('#text').html('축하합니다!')
				alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
				location.href='/book/Peanutcess.do';
			}
	
	}else if(ending=='artist'){
		if(desa==2){
			$('#text').html('공주님은 화가가 되었어요!')
			}else if(desa == 3){
				$('#text').html('축하합니다!')
				alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
				location.href='/book/Peanutcess.do';
			}
	
	}else if(ending=='servent'){
		if(desa==2){
			$('#text').html('공주님은 공무원이 되었어요!')
			}else if(desa == 3){
				$('#text').html('축하합니다!')
				alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
				location.href='/book/Peanutcess.do';
			}
	
	}else if(ending=='qeen'){
		if(desa==2){
		$('#sche-farm').html('<img src="/resources/img/peanutcess/animal_character_hamster_happy.png">');
			$('#text').html('공주님은 여왕님이 되었어요!');
		}else if(desa == 3){
			alert('축하합니다! 피넛 5개가 지급되었습니다!');
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
				location.href='/book/Peanutcess.do';
			}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
