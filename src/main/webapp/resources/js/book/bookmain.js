



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
			replyArea[0].innerHTML = '';
			for(var i in result){
				$('#replyLength').html(result.length);	
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

function replyRemove(rNo){
	confirm(rNo+'을 삭제하시겠습니까?');
	
}

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
		str+='<button id="reply-button" onclick="modifyReply('+rNo+','+rPage+')">수정</button></div></div>'
		
		$('#'+rNo).html(str);
		
		},
		error:function(){}
	})
}

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


