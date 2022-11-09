
$('.status-area').css('display','none');
$('#status-area').css('display','none');
$('#sche-area2').css('display','none');


// firstText( function() { 
//    secontTest();
// })

//function secontTest(){
//alert('끝')}



firstText();

var desa = 1;

let showFlag = 0;
function firstText(callback){
	$('#sche-area1').css('display','none');
	let bookTitle = new Array();
	let bookTag = new Array();

	for(var i=0; i<$('.booktitle').length;i++){
		var value = $('.booktitle').eq(i).val();
		var value2 = $('.booktag').eq(i).val();
		bookTitle.push(value);
		bookTag.push(value2);
	}

	$('#princessImg').attr('src','/resources/img/peanutcess/book_woman.png')
	
	let str = new Array();
		
	for(var i=0; i<bookTitle.length;i++){
		str[i] = name+' 공주님이 '+bookTitle[i]+' 도서를 읽고있습니다. <br>'
		if(bookTag[i] == 'horror'){
			str[i] += '공주님의 힘이 5올랐습니다 <br>'		
		}else if(bookTag[i] == 'gag'){
			str[i] += '공주님의 학력이 5올랐습니다 <br>'
		}else if(bookTag[i] == 'move'){
			str[i] += '공주님의 예절이 5올랐습니다 <br>'
		}else if(bookTag[i] == 'heart'){
			str[i] += '공주님의 힘이 5올랐습니다 <br>'
		}else if(bookTag[i] == 'tear'){
			str[i] += '공주님의 미술이 5올랐습니다 <br>'
		}else if(bookTag[i] == 'popcorn'){
			str[i] += '공주님의 요리가 5올랐습니다 <br>'
		}else if(bookTag[i] == 'cider'){
			str[i] += '공주님의 음악이 5올랐습니다 <br>'
		}else if(bookTag[i] == 'none'){
			str[i] += '공주님의 돈이 5피넛 올랐습니다 <br>'
		}
		
	}


	if(str.length==0){
		location.href="/book/gameStarting";
	}

	$('#text').html(str[0]);
	if(str.length==1){
		showInSchedule();
	}

	if(str.length>1){
		$('#down-area').on('click',function(){
			$('#text').html(str[1]);
			$('#down-area').attr('class','down-area1');
			if(str.length==2){
				showInSchedule();
			}
			if(str.length>2){		
				$('.down-area1').on('click',function(){
					$('#text').html(str[2]);
					showInSchedule();
				})
			}
		})			
	}
	
}

function showInSchedule() {
	$('#sche-area1').css('display','block');
	$('#sche-area1').html('<div class="hover">스케쥴을 입력한다</div>');
	$('#sche-area1').on('click',function(){location.href="/book/gameStarting"});
}