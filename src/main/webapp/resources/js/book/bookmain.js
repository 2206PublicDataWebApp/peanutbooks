
function replyRegist(bookNo){
var reContents = $('#reply-text').val();
$.ajax({
	url:"/book/originBookReplyRegist",
	data:{"reContents":reContents, "bookNo":bookNo},
	type:"post",
	success:function(result){
	alert("등록성공");
	$('#reply-text').val('');
	
	},
	error:function(){
	alert('등록실패')
	}
})

};

function printReply(bookNo){
var replyArea = $('.view-relply');
$.ajax({
	url:"/book/originBookAllReply",
	date:{"bookNo":bookNo},
	type:post,
	success:function(result){
			for(var i in result){
				$('#replyLength').html(result.length);	
				var str ='<div class="row  d-flex justify-content-center repleOne mt-2">';
				str+='<div class="card p-3">';	
				str+='<div class="d-flex justify-content-between align-items-center">';
				str+='<div class="user d-flex flex-row align-items-center">';
				str+='<span class="col-3 d-inline-block text-truncate">';
				str+='<small class="font-weight-bold username">사용자이름 길면 잘림</small>';
				str+='</span>';
				str+='<span class="col-11">';
				str+='<small class="font-weight-bold">';
				str+='리플내용';
				str+='</small>';
				str+='</span>';
				str+='</div></div>';
				str+='<div class="mt-2">';
				str+='<div class="reply row">';
				str+='<div class="col-6">';
				str+='<span class="modify-del-button"> <small>삭제</small> <small>수정</small>';
				str+='</span></div>';
				str+='<div class="text-truncate col-6 date">';
				str+='<small>날짜</small>';
				str+='</div></div></div>';
				
				replyArea[replyArea.length-1].innerHTML += str;
				 
				 }
			 		 
			},
	error:function(){}
	});
};