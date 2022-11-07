var desa = 1;
var result = $('#result').val();
var pName;
var bMonth;
var bDay;

function nextText(){
	if(result>0){
		onGame();
		desa++;
	}
	if(result==0){
	console.log('hi')
		firstGame();
		desa++;
	}
};

function onGame(){
	if(desa == 1){
		$('#text').html('게임을 다시 접속하셨군요');
	}else if(desa == 2){
		$('#text').html('게임을 계속 할까요?');
	}else if(desa ==3){
	 	var str = '';
	 	str+='<div id="newgame" onclick="removeGame();">새 게임을 한다</div>'
	 	str+='<div id="gogame" onclick="goGame();">게임을 계속한다.</div>'
		$('#text').html(str);
	}
	

};

//게임지우기
function removeGame(){
	if(confirm('게임데이터를 지우고 새 게임을 할까요?')){
	$.ajax({
		url:"/book/peanutcessRemove.do",
		type:"get",
		success:function(){
			location.href='/book/Peanutcess.do'
		},
		error:function(){
		alert('오류! 오류가 계속되면 관리자에게 문의하세요')
		}
	
	})
	
	}else{
	desa=3;
	}
}


//게임계속하기
function goGame(){
location.href='/book/gameMain.do';
}


//처음 게임하기
function firstGame(){
	if(desa == 1){
		$('#text').html('피넛북스에서 준비한 특별게임!<br> 피넛세스 메이커에 오신걸 환영합니다!');
	}else if(desa == 2){
		$('#text').html('15살이된 피넛 공주님을 여왕으로 키워보세요!');
	}else if(desa ==3){
	 	$('#text').html('엔딩을 보면 특별한 선물이 기다릴지도...?');
	}else if(desa ==4){
		$('#peanutster').attr("src","/resources/img/peanutcess/animal_character_hamster_happy.png");
		$('#text').html('공주님은 '+mName+'님이 좋아하는 책으로 초기 능력치가 정해져요!');	
	}else if(desa ==5){
		$('#text').html('공주님의 이름을 정해주세요');
	}else if(desa ==6){
		selectPname();
	}else if(desa ==7){
		$('#text').html('공주님의 생일을 정해주세요');
	}else if(desa ==8){
		selectPbirth();
	}else if(desa ==9){
		$('#text').html(pName+'공주님은 '+bMonth+'월 '+bDay+'일이 생일이시군요!');		
	}else if(desa ==10){
	$('#peanutster').attr("src","/resources/img/peanutcess/animal_character_hamster.png");
		$('#text').html(mName+'님이 좋아하는 책을 불러옵니다...');
	}else if(desa==11){		
		chooseBookMark();	
	}else if(desa==12){
		$('#text').html('공주님이 책을 읽고 능력치를 올립니다');
	}else if(desa==13){
		
		startGame();
	}
	
	

}

//공주 이름 정하기
function selectPname(){
	pName = prompt('이름을 입력하세요');
	if(confirm(pName+'으로 하시겠습니까?')){
	alert('공주님의 이름은'+pName+'입니다');
	
	}else{
		pName ='';
		selectPname();
	
	}
}

//공주 생일 정하기
function selectPbirth(){
bMonth =  prompt('월을 입력하세요 (1~12숫자만가능)')
bDay =  prompt('일을 입력하세요 (1~31숫자만가능)')
	if(!(bMonth>=1 && bMonth<=12 && bDay>=1 && bDay<=31)){
		alert('잘못된 입력입니다!')
		selectPbirth();	
	}else{
		if(confirm('공주님의 생일은 '+bMonth+'월 '+bDay+'일 입니다')){
			alert('생일이 등록되었습니다.');
		}else{
			bMonth = '';
			bDay ='';
			selectPbirth();	
		}
	}
};

//내 서재에서 책 가져오기
function chooseBookMark(){
	$.ajax({
		url:"/peanutcess/checkBookMark.do",
		type:"get",
		success:function(result){
			if(result.length == 0){
			$('#text').html('서재에 책이 없으시네요...');
			}else{
				 var str = '';
				 str += '최근 서재에... '

				for(var i in result){
				console.log(result[i])
				 str += '"'+result[i].bookTitle+'" ';
				}
				str+='을 넣으셨네요!!'
				$('#text').html(str);
		
			}
		},
		error:function(){
		}
	
	})

};

function startGame(){

$.ajax({
		url:"/peanutcess/startGamesave.do",
		type:"get",
		data:{"pName":pName,"bMonth":bMonth,"bDay":bDay},
		success:function(){
		$('text').html('공주님의 정보가 저장되었습니다! 게임을 시작합니다!');
			location.href='/book/gameMain.do';
			
		},
		error:function(){
		}
	
	})

}