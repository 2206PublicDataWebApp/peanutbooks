
//도서삭제
function removeOriginBook(bookNo){
	if(confirm('이 도서를 삭제하시겠습니까?')){
 location.href='/book/removeOribook.do?bookNo='+bookNo;
 }
}


function removeORiSeries(bookNo,seriesNo){
 if(confirm('시리즈를 삭제 하시겠습니까?')){
 location.href='/book/removeOriSeries.do?bookNo='+bookNo+'&seriesNo='+seriesNo;
 }
}


function replyRegist(bookNo, userId){
var reContents = $('#reply-text').val();
$.ajax({
	url:"/book/originBookReplyRegist",
	data:{"reContents":reContents, "bookNo":bookNo},
	type:"post",
	success:function(result){
		alert('등록성공');
		$('#reply-text').val('');
	
		printReply(bookNo,userId,1);
	
	
	},
	error:function(){
	alert('등록실패')
	}
})

};

function printReply(bookNo,userId,rPage){
var replyArea = $('.view-relply');
$.ajax({
	url:"/book/originBookAllReply",
	data:{"bookNo":bookNo, rPage:rPage},
	type:"post",
	success:function(result){
		if(result==0){
		$('#replyLength').html(0);
		replyArea[0].innerHTML = "";
		}else{
			replyArea[0].innerHTML = '';
			for(var i in result){
				$('#replyLength').html(result[0].totalCount);	
				var str ='<div class="row  d-flex justify-content-center repleOne mt-2" id="'+result[i].replyNo+'">';
				str+='<div class="card p-3">';	
				str+='<div class="d-flex row justify-content-between align-items-center">';
				str+='<div class="user col-12 d-flex flex-row align-items-center">';
				str+='<span class="col-md-1 col-3 d-inline-block text-truncate">';
				str+='<small class="font-weight-bold username">'+result[i].mNickName+'</small>';
				str+='</span>';
				str+='<span class="col-md-11 col-8">';
				str+='<small class="font-weight-bold">';
				str+=result[i].reContents;
				str+='</small>';
				str+='</span>';
				str+='</div></div>';
				str+='<div class="mt-2">';
				str+='<div class="reply row">';
				str+='<div class="col-6">';
					if(userId==result[i].memberId){
						str+='<span class="modify-del-button"> <small onclick="replyRemove('+result[i].replyNo+','+rPage+');">삭제</small> <small onclick="replymodifyView('+result[i].replyNo+','+rPage+');">수정</small>';
					}
					if(userId!=result[i].memberId && admin=='Y'){
							str+='<span class="modify-del-button"> <small onclick="replyRemove('+result[i].replyNo+','+rPage+');">삭제</small>';
						}
				str+='</span></div>';
				str+='<div class="text-truncate col-6 date">';
				
				if(result[i].reContents != '이 댓글은 삭제되었습니다'){
					str+='<small class="rereply" id="reply'+result[i].replyNo+'" onclick="Rereply('+result[i].replyNo+','+rPage+');">답글달기</small> ';
				}
				str+='<small>'+result[i].insertDate+'</small>';
				str+='</div></div></div>';
				
				replyArea[0].innerHTML += str;
				 
				 }
				 
				 var page = "";
				 if (rPage != 1){
				 	page +='<span onclick="printReply('+bookNo+',\''+userId+'\','+(rPage-1)+')"> < </span>';
				 }
				 for(var i =result[0].startNavi; i<=result[0].endNavi; i++){
				 	if( i == rPage){
				 		page+= '<small class="nowPage"> &nbsp; </small>';
				 	}else{
					 page+= '<small onclick="printReply('+bookNo+',\''+userId+'\','+i+')"> &nbsp; </small>';}
				 }
				 if(rPage != result[0].maxPage){
					 page +='<span onclick="printReply('+bookNo+',\''+userId+'\','+(rPage+1)+')"> > </span>';
				 }
				$('#page').html(page);
			} 
				if(result!=0){
				 	reReplyAll(rPage,result[0].totalCount)	
				 }			 
			},
	error:function(){}
	});
	
	
	
	
};


//대댓글 출력하기
function reReplyAll(rPage,count){
	$.ajax({
		url:"/book/ReReplyPrint",
		type:"post",
		data:{"bookNo":bookNo,"category":"origin"},
		success:function(result){
			for(var i in result){
				var str ="";
					str ='<div class="row  d-flex justify-content-center repleOne reReply mt-2 ps-5" id="rRe'+result[i].reReplyNo+'">';
					str+='<div class="card ps-3 pe-3 pt-2 pb-2">';	
					str+='<div class="d-flex row justify-content-between align-items-center">';
					str+='<div class="user col-12 d-flex flex-row align-items-center">';
					str+='<span class="col-md-1 col-3 d-inline-block text-truncate">';
					str+='<small class="font-weight-bold username">'+result[i].mNickName+'</small>';
					str+='</span>';
					str+='<span class="col-md-11 col-8">';
					str+='<small class="font-weight-bold">';
					str+=result[i].reContens;
					str+='</small>';
					str+='</span>';
					str+='</div></div>';
					str+='<div class="mt-2">';
					str+='<div class="reply row">';
					str+='<div class="col-6">';
						if(userId==result[i].memberId){
							str+='<span class="modify-del-button"> <small onclick="replyReRemove('+result[i].reReplyNo+','+rPage+');">삭제</small> <small onclick="rereplymodifyView('+result[i].reReplyNo+','+rPage+');">수정</small>';
						}
						if(userId!=result[i].memberId && admin=='Y'){
							str+='<span class="modify-del-button"> <small onclick="replyReRemove('+result[i].reReplyNo+','+rPage+');">삭제</small>';
						}
					str+='</span></div>';
					str+='<div class="text-truncate col-6 date">';
					str+='<small>'+result[i].insertDate+'</small>';
					str+='</div></div></div>';
				
				$('#'+result[i].replyNo).append(str);
				
				}
				totalCount =count+(result.length);
				$('#replyLength').html(totalCount);
					
		},
		error:function(){}
	})
	

}


function replyRemove(rNo, rPage){
	if(confirm('댓글을 삭제하시겠습니까?')){
		$.ajax({
			url:"/book/removeOriReply",
			data:{"rNo":rNo},
			type:"get",
			success:function(result){
				alert('삭제했습니다');
				if(result>0){
					printReply(bookNo,userId,rPage);
				}else{
					alert('작성자가 아닙니다');
				
				}
		
			},
			error:function(){}
		})
	};
	
}

//대댓글 삭제
function replyReRemove(rNo, rPage){


	if(confirm('댓글을 삭제하시겠습니까?')){
			$.ajax({
				url:"/book/removeReReply",
				data:{"replyNo":rNo},
				type:"get",
				success:function(result){
					alert('삭제했습니다');
					if(result>0){
						printReply(bookNo,userId,rPage);
					}else{
						alert('작성자가 아닙니다');
					
					}
			
				},
				error:function(){}
			})
		};

}


//댓글 수정창 만들기
function replymodifyView(rNo,rPage){
	$.ajax({
		url:"/book/oribookOneReply",
		data:{"rNo":rNo},
		type:"get",
		success:function(result){

		var str="";
		str+='<div class="row">'
		str+='<div class="col-md-11 col-9">'
		str+='<textarea name="reContents'+rNo+'" id="reply-text" rows="3">'+result+'</textarea></div>'							
		str+='<div class="col-md-1 col-3 reply-button-area">'
		str+='<button id="reply-button1" onclick="modifyReply('+rNo+','+rPage+')">수정</button> <button id="reply-button2" onclick="printReply('+bookNo+',\''+userId+'\','+rPage+')">취소</button></div></div>'
		
		$('#'+rNo).html(str);
		
		},
		error:function(){}
	})
}

//대댓글 수정창 만들기
function rereplymodifyView(rNo,rPage){
	$.ajax({
		url:"/book/getOneReReply",
		data:{"rNo":rNo},
		type:"get",
		success:function(result){
		console.log(result);
		var str="";
		str+='<div class="row">'
		str+='<div class="col-md-11 col-9">'
		str+='<textarea name="reRContents'+rNo+'" id="reply-text" rows="3">'+result+'</textarea></div>'							
		str+='<div class="col-md-1 col-3 reply-button-area">'
		str+='<button id="reply-button1" onclick="modifyReReply('+rNo+','+rPage+')">수정</button> <button id="reply-button2" onclick="printReply('+bookNo+',\''+userId+'\','+rPage+')">취소</button></div></div>'
		
		$('#rRe'+rNo).html(str);
	
		
		},
		error:function(){}
	})
}

//수정하기
function modifyReply(replyNo,rPage){
var reContents = $('[name=reContents'+replyNo+']').val();
$.ajax({
	url:"/book/modifyOriReply",
	data:{"replyNo":replyNo, "reContents":reContents},
	type:"post",
	success:function(result){
		if(result>0){
			alert('수정완료')
			printReply(bookNo,userId,rPage);
		}else{
			alert('작성자 아이디가 아닙니다')
		}
	},
	error:function(){}
});

}


//대댓글수정하기
function modifyReReply(replyNo,rPage){
var reContents = $('[name=reRContents'+replyNo+']').val();
$.ajax({
	url:"/book/modifyReReply",
	data:{"reReplyNo":replyNo, "reContens":reContents},
	type:"post",
	success:function(result){
		if(result>0){
			alert('수정완료')
			printReply(bookNo,userId,rPage);
		}else{
			alert('작성자 아이디가 아닙니다')
		}
	},
	error:function(){}
});

}



//별점주기 스크립트

var starCheck = false;

//별점주기
function StarScore(score){
	 $.ajax({
	 	url:"/book/StarScore.do",
	 	data:{"score":score,"bookNo":bookNo,"category":"origin"},
	 	type:"post",
	 	success:function(result){
	 	
	 	var score = result.score;
	 	var scoreCount = result.scoreCount;
	 	starAvg(score,scoreCount);
	 		
	 	}
	 })
}


//별점 평균 적용하기
function starAvg(score,scoreCount){
	$('.star-title').eq(0).html('사용자 평균('+scoreCount+'명)');
	if(score==0){
		$('#avr-score').html('☆ ☆ ☆ ☆ ☆')
	}else if(score==1){
		$('#avr-score').html('★ ☆ ☆ ☆ ☆')
	}else if(score==2){
		$('#avr-score').html('★ ★ ☆ ☆ ☆')
	}else if(score==3){
		$('#avr-score').html('★ ★ ★ ☆ ☆')
	}else if(score==4){
		$('#avr-score').html('★ ★ ★ ★ ☆')
	}else if(score==5){
		$('#avr-score').html('★ ★ ★ ★ ★')
	}
	
};

//별점 취소
function StarRemove(){
$.ajax({
	 	url:"/book/StarRemove.do",
	 	data:{"bookNo":bookNo,"category":"origin"},
	 	type:"post",
	 	success:function(result){
	 	
	 	var score = result.score;
	 	var scoreCount = result.scoreCount;
	 	starAvg(score,scoreCount);
	 		
	 	},
	 	error:function(){
	 	alert('오류');
	 	}
	 })
}

function StarRemoveUser(){
	StarRemove();
	$('#userStarScore').css('display','none');
	$('#star-score').css('display','block');

}

$('#star1').on("click",function(){
	if(!starCheck){
		$('#star1').css('display','none');
		$('#star1-fill').css('display','inline');
		StarScore(1);
		starCheck = true;
	}else{
		starfirst();
	}
})

$('#star2').on("click",function(){
	if(!starCheck){
		$('#star1').css('display','none');
		$('#star2').css('display','none');
		$('#star1-fill').css('display','inline');
		$('#star2-fill').css('display','inline');
		starCheck = true;
		StarScore(2)
	}else{
		StarRemove()
		starfirst();
	}
})

$('#star3').on("click",function(){
	if(!starCheck){
		$('#star1').css('display','none');
		$('#star2').css('display','none');
		$('#star3').css('display','none');
		$('#star1-fill').css('display','inline');
		$('#star2-fill').css('display','inline');
		$('#star3-fill').css('display','inline');
		StarScore(3);
		starCheck = true;
	}else{
		StarRemove()
		starfirst();
	}
})

$('#star4').on("click",function(){
	if(!starCheck){
		$('#star1').css('display','none');
		$('#star2').css('display','none');
		$('#star3').css('display','none');
		$('#star4').css('display','none');
		$('#star1-fill').css('display','inline');
		$('#star2-fill').css('display','inline');
		$('#star3-fill').css('display','inline');
		$('#star4-fill').css('display','inline');
		StarScore(4);
		starCheck = true;
	}else{
		StarRemove()
		starfirst();
	}
})

$('#star5').on("click",function(){
	if(!starCheck){
		$('#star1').css('display','none');
		$('#star2').css('display','none');
		$('#star3').css('display','none');
		$('#star4').css('display','none');
		$('#star5').css('display','none');
		$('#star1-fill').css('display','inline');
		$('#star2-fill').css('display','inline');
		$('#star3-fill').css('display','inline');
		$('#star4-fill').css('display','inline');
		$('#star5-fill').css('display','inline');
		StarScore(5);
		starCheck = true;
	}else{
		StarRemove()
		starfirst();
	}
})

function starfirst(){
		$('.star-fill').css('display','none');
		$('.star-none').css('display','inline');
		starCheck = false;		
}


	$('.star-fill').on("click",function(){
		if(starCheck){
			StarRemove()
			starfirst();
		}
	 })


//다음화 등록하기
function registOriNext(bookNo,seriesNo){
	location.href="/book/oriBookNextSeires.do?bookNo="+bookNo+"&seriesNo="+seriesNo;
}

//내 서재 등록하기
function addMybooks(category,bookNo){
	$.ajax({
		url:"/book/addMybooks.do",
		type:"get",
		data:{"category":category,"bookNo":bookNo},
		success: function(result){
			if(result=='ok'){
				alert('내 서재에 등록했습니다.');
				$('#mybooksButton').html('내 서재 취소');
			}else{
				alert('내 서재에서 삭제했습니다.');
				$('#mybooksButton').html('내 서재 등록');
			}
		},
		error : function(){}
	})
}


//대댓창 만들기
function Rereply(replylNo,rPage){
	if($('#'+replylNo).children('.reReply').length<10){

			var str="";
			str+='<div class="row mt-2">'
			str+='<div class="col-md-11 col-9">'
			str+='<textarea name="reRContents'+replylNo+'" id="reply-text" rows="3"></textarea></div>'							
			str+='<div class="col-md-1 col-3 reply-button-area">'
			str+='<button id="reply-button1" onclick="reReplyAdd('+replylNo+','+rPage+')">답글</button> <button id="reply-button2" onclick="printReply('+bookNo+',\''+userId+'\','+rPage+')">취소</button></div></div>'

		
		$('#'+replylNo).append(str)
	}else{
		alert('대댓글이 10개이상인경우 작성할수 없습니다.')
	}
}

//대댓 등록
function reReplyAdd(rNo,rPage){
var rContents = $('[name=reRContents'+rNo+']').val();
	 $.ajax({
	 url:"/book/bookReReplyRegist.do",
	 type:"post",
	 data:{"reContens":rContents,"replyNo":rNo,"bookNo":bookNo,"category":"origin"},
	 success:function(){
	 
		 printReply(bookNo,userId,rPage);
	 
	 },
	 error:function(){}
	 })

}

//작가일때 승인안됐으면 도서 삭제
function removeOriginBookMember(BookNo,SeriesNo){
	if(confirm('삭제 하시겠습니까?')){
		location.href='/book/removeOneSerieseMember?bookNo='+BookNo+'&seriesNo='+SeriesNo;
	}
}

function removeOriginBookOne(bookNo){
	if(confirm('삭제 하시겠습니까?')){
		location.href='/book/removeOneBookMember?bookNo='+bookNo;
	}
}





///sns공유 함수

document.querySelector('#popUp').style.display='none';
//팝업열기
function openPop(){
	var PopUp = document.querySelector('#popUp');
	PopUp.style.display="flex";
  	Kakao.init('851b05e5a04e6de56c5c90e2d2a0470c');
  	Kakao.Link.createDefaultButton({
	    container: '#btnKakao', // 카카오공유버튼ID
	    objectType: 'feed',
	    content: {
	      title: title, // 보여질 제목
	      description: "땅콩북스의 "+title, // 보여질 설명
	      imageUrl: "https://raw.githubusercontent.com/2206PublicDataWebApp/peanutbooks/master/src/main/webapp/resources/bookImg/"+img, // 콘텐츠 URL
	      link: {
	         mobileWebUrl: "http://127.0.0.1:9999/book/oriBookInfo?bookNo"+bookNo,
	         webUrl: "http://127.0.0.1:9999/book/oriBookInfo?bookNo"+bookNo
	      }
	    }
	  });
}


//해당 팝업닫기
function closePop(){
	var PopUp = document.querySelector('#popUp');
	PopUp.style.display="none";
}


//링크복사하기
function clip(){

        var url = "http://127.0.0.1:9999/book/oriBookInfo?bookNo"+bookNo;    // <a>태그에서 호출한 함수인 clip 생성
        var textarea = document.createElement("textarea");  
        //url 변수 생성 후, textarea라는 변수에 textarea의 요소를 생성
        
        document.body.appendChild(textarea); //</body> 바로 위에 textarea를 추가(임시 공간이라 위치는 상관 없음)
        url = window.document.location.href;  //url에는 현재 주소값을 넣어줌
        textarea.value = url;  // textarea 값에 url를 넣어줌
        textarea.select();  //textarea를 설정
        document.execCommand("copy");   // 복사
        document.body.removeChild(textarea); //extarea 요소를 없애줌
        
        alert("URL이 복사되었습니다.")  // 알림창
    }


//트위터
function shareTwitter() {
    var sendText = "땅콩북스의 "+title; // 전달할 텍스트
    var sendUrl = "http://127.0.0.1:9999/book/oriBookInfo?bookNo"+bookNo; // 전달할 URL
    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
}

//페이스북
function shareFacebook() {
    var sendUrl = "http://127.0.0.1:9999/book/oriBookInfo?bookNo"+bookNo; // 전달할 URL
    window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
}


