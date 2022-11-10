

$('.status-area').css('display', 'none');
$('#sche-area2').css('display', 'none');


var desa = 1;

//대사창 누르면 실행
function nextText() {
	endigText();
	desa++;
};



function endigText() {
	$('#sche-farm').html('<img src="/resources/img/peanutcess/animal_character_hamster.png">');
	if (desa == 1) {
		$('#text').html(name + '공주님은 어떻게 성장했을까요?')
	}

	if (ending == 'neet') {
		if (desa == 2) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/animal_character_hamster_kurushii.png">');
			$('#text').html('이럴수가... 공주님은 백수가 되었어요')
		} else if (desa == 3) {
			$('#text').html('실망이예요...')
		} else if (desa == 4) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/neet.png">');
			$('#name-area').html(name)
			$('#text').html('뭐하고 놀지.. 이제 놀것도 없네')
		} else if (desa == 5) {
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href = '/book/Peanutcess.do';
		}


	} else if (ending == 'farmEnd') {

		if (desa == 2) {
			$('#text').html('농사를 좋아하시던 공주님')
		} else if (desa == 3) {
			$('#text').html('공주님은 농부가 되었어요!')
		} else if (desa == 4) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/fammer.png">');
			$('#name-area').html(name);
			$('#text').html('올해는 풍년이야!')	;
		} else if (desa == 5) {
			$('#name-area').html('');
			$('#text').html('축하합니다!');
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href = '/book/Peanutcess.do';
		}

	} else if (ending == 'cafeEnd') {

		if (desa == 2) {
			$('#text').html('식당알바를 열심히 하시던 공주님')
		} else if (desa == 3) {
			$('#text').html('공주님은 식당 직원이 되었어요!')
		} else if (desa == 4) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/job_waitress.png">');
			$('#name-area').html(name);
			$('#text').html('어서오세요~~~')	;
		} else if (desa == 5) {
			$('#name-area').html('');
			$('#text').html('축하합니다!')
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href = '/book/Peanutcess.do';
		}
	} else if (ending == 'childEnd') {
		if (desa == 2) {
			$('#text').html('보모 알바를 열심히 하시던 공주님')
		} else if (desa == 3) {
			$('#text').html('공주님은 어린이집 선생님이 되었어요!')
		} else if (desa == 4) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/job_youchien.png">');
			$('#name-area').html(name);
			$('#text').html('호랑이반 모여라~~~~')	;
		} else if (desa == 5) {
			$('#text').html('축하합니다!')
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href = '/book/Peanutcess.do';
		}
		
	} else if (ending == 'freetor') {
		if (desa == 2) {
			$('#text').html('아르바이트를 열심히 하시던 공주님')
		} else if (desa == 3) {
			$('#text').html('공주님은 아르바이트생이 되었어요!')
		} else if (desa == 4) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/job_one.png">');
			$('#name-area').html(name);
			$('#text').html('네 다음분 오세요!')	;
		} else if (desa == 5) {
			$('#text').html('축하합니다!')
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href = '/book/Peanutcess.do';
		}

	} else if (ending == 'genal') {
		if (desa == 2) {
			$('#text').html('무술공부를 열심히 하시던 공주님')
		} else if (desa == 3) {
			$('#text').html('공주님은 장군이 되었어요!')
		} else if (desa == 4) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/war_ryuukihei.png">');
			$('#name-area').html(name);
			$('#text').html('나를 따르라!!!')	;
		} else if (desa == 5) {
			$('#text').html('축하합니다!')
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href = '/book/Peanutcess.do';
		}

	} else if (ending == 'musician') {
		if (desa == 2) {
			$('#text').html('언제나 음악공부에 열중하시던 공주님')
		} else if (desa == 3) {
			$('#text').html('공주님은 음악가가 되었어요!')
		} else if (desa == 4) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/piano_solo.png">');
			$('#name-area').html(name);
			$('#text').html('오늘 제 음악회에 와주셔서 감사해요')	;
		} else if (desa == 5) {
			$('#text').html('축하합니다!')
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href = '/book/Peanutcess.do';
		}

	} else if (ending == 'artist') {
		if (desa == 2) {
			$('#text').html('그림을 좋아하시던 공주님')
		} else if (desa == 3) {
			$('#text').html('공주님은 화가가 되었어요!')
		} else if (desa == 4) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/bijutsu_paint.png">');
			$('#name-area').html(name);
			$('#text').html('멋진 그림을 그려드릴게요!')	;
		} else if (desa == 5) {
			$('#text').html('축하합니다!')
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href = '/book/Peanutcess.do';
		}
		
	} else if (ending == 'servent') {
		if (desa == 2) {
			$('#text').html('언제나 뭐든 열심히 하시던 공주님')
		} else if (desa == 3) {
			$('#text').html('공주님은 공무원이 되었어요!')
		} else if (desa == 4) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/kokusai_woman.png">');
			$('#name-area').html(name);
			$('#text').html('이 나라를 훌륭하게 만들게요!')	;
		} else if (desa == 5) {
			$('#text').html('축하합니다!')
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href = '/book/Peanutcess.do';
		}

	} else if (ending == 'qeen') {
		if (desa == 2) {
			
			$('#text').html('공주님은 이후로도 공부를 게을리 하지 않으셨고');
		} else if (desa == 3) {
			$('#text').html('꾸준히 노력하셔서...');
		} else if (desa == 4) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/animal_character_hamster_happy.png">');
			$('#text').html('공주님은 여왕이 되셨어요!');
		} else if (desa == 5) {
			$('#sche-farm').html('<img src="/resources/img/peanutcess/royal_queen.png">');
			$('#name-area').html(name);
			$('#text').html('오늘부터 땅콩북스의 여왕이 되어 최선을 다하겠습니다')	;
		} else if (desa == 6) {
			alert('축하합니다! 피넛 5개가 지급되었습니다!');
			alert('게임을 완료했습니다, 초기화면으로 돌아갑니다.');
			location.href = '/book/Peanutcess.do';
		}

	}













}
