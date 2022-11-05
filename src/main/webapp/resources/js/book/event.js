document.querySelector('#peanut-area').style.display='none';
if(today==false){
	document.querySelector('#peanut-area').style.display='block';
	document.querySelector('#peanut-area').style.animation='scaledown 2s forwards';
	setTimeout(function(){	
			document.querySelector('#peanut-area').style.display='none';									
			alert('출석체크를 하셨습니다!');
			if(check=='fine'){
			alert('출석을 완료하셨습니다! 5피넛을 선물로 드립니다!');
			document.querySelector('#evenImg').src='/resources/img/book/eventImg5.png';
			}
		}, 2000);
}
window.onload=changeImg();

function changeImg(){

	if(check == 'one'){
		document.querySelector('#evenImg').src='/resources/img/book/eventImg1.png';
		}else if(check == 'two'){
	
		document.querySelector('#evenImg').src='/resources/img/book/eventImg2.png';
		}else if(check == 'three'){
	
		document.querySelector('#evenImg').src='/resources/img/book/eventImg3.png';
		}else if(check == 'four'){
	
		document.querySelector('#evenImg').src='/resources/img/book/eventImg4.png';
		}else if(check =='five'){
	
		document.querySelector('#evenImg').src='/resources/img/book/eventImg5.png';
		}
}