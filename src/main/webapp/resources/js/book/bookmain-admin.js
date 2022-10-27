



//일반도서 리플 등록하기
function replyRegist(bookNo, userId){
var contents = $('#reply-text').val();
$.ajax({
	url:"/book/norBookReplyRegist",
	data:{"contents":contents, "bookNo":bookNo},
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

//일반도서 리플 출력하기
function printReply(bookNo,userId,rPage){
var replyArea = $('.view-relply');
$.ajax({
	url:"/book/norBookAllReply",
	data:{"bookNo":bookNo, rPage:rPage},
	type:"post",
	success:function(result){
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
				str+=result[i].contents;
				str+='</small>';
				str+='</span>';
				str+='</div></div>';
				str+='<div class="mt-2">';
				str+='<div class="reply row">';
				str+='<div class="col-6">';
					if(userId==result[i].memberId){
						str+='<span class="modify-del-button"> <small onclick="replyRemove('+result[i].replyNo+','+rPage+');">삭제</small> <small onclick="replymodifyView('+result[i].replyNo+','+rPage+');">수정</small>';
					}
				str+='</span></div>';
				str+='<div class="text-truncate col-6 date">';
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
			 		 
			},
	error:function(){}
	});
};

//일반도서 댓글 삭제하기
function replyRemove(rNo, rPage){
	if(confirm('댓글을 삭제하시겠습니까?')){
		$.ajax({
			url:"/book/removeNorReply",
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

//일반도서 수정 댓글열기
function replymodifyView(rNo,rPage){
	$.ajax({
		url:"/book/norbookOneReply",
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

//일반도서 댓글 수정하기

function modifyReply(replyNo,rPage){
var contents = $('[name=reContents'+replyNo+']').val();
$.ajax({
	url:"/book/modifyNorReply",
	data:{"replyNo":replyNo, "contents":contents},
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
	 	data:{"score":score,"bookNo":bookNo,"category":"normal"},
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
	 	data:{"bookNo":bookNo,"category":"normal"},
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

