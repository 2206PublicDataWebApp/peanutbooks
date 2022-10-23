



function replyRegist(bookNo, userId){
var reContents = $('#reply-text').val();
$.ajax({
	url:"/book/originBookReplyRegist",
	data:{"reContents":reContents, "bookNo":bookNo},
	type:"post",
	success:function(result){
		alert('등록성공');
		$('#reply-text').val('');
	
		printReply(bookNo,userId);
	
	
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
				var str ='<div class="row  d-flex justify-content-center repleOne mt-2">';
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
						str+='<span class="modify-del-button"> <small>삭제</small> <small>수정</small>';
					}
				str+='</span></div>';
				str+='<div class="text-truncate col-6 date">';
				str+='<small>'+result[i].insertDate+'</small>';
				str+='</div></div></div>';
				
				replyArea[0].innerHTML += str;
				 
				 }
				 
				 var page = "";
				 if (rPage != 1){
				 page +='<span onclick="printReply('+bookNo+',\''+userId+'\','+(rPage-1)+')">이전 </span>';
				 }
				 if(rPage != result[0].maxPage){
				 page +='<span onclick="printReply('+bookNo+',\''+userId+'\','+(rPage+1)+')">다음</span>';
				 }
				$('#page').html(page);
			 		 
			},
	error:function(){}
	});
};