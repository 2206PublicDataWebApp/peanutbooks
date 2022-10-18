<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 부트스트랩,jQuery -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- 썸머 노트 -->
  <script src="/resources/js/summernote-lite.js"></script>
  <script src="/resources/js/summernote/lang/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="/resources/css/summernote-lite.css">
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
</head>
<!-- 스크립트태그-썸머노트설정 -->
<script>
	$(document).ready(function(){
		$('#summernote').summernote({
			height : 300,			//에디터 높이
			width : 700,	
			lang : "ko-KR",			//한글 설정
			//콜백 함수
			callbacks:{				//이미지를 첨부하는 부분
				onImageUpload : function(files, editor, welEditable){
					uploadSummernoteImageFile(files[0],this);
				},
				onPaste: function(e){
					var clipboardData = e.originalEvent.clipboardData;
					if(clipboardData && clipboardData.items && clipboardData.items.length){
						var item = clipboardData.items[0];
						if(item.kind === 'file' && item.type.indexOf('image/') !== -1){
							e.preventDefault();
						}
					}
				},
			}
		});
		
		// 이미지 파일 업로드			
		function uploadSummernoteImageFile(file,editor){
			data = new FormData();
			data.append("file",file);
			$.ajax({
				data:data,
				type:"POST",
				url:"/notice/uploadSummernoteImageFile",
				dataType:"JSON",
				contentType:false,
				processData:false,
				success:function(data){
					//항상 업로드된 파일의 url이 있어야 한다.
					$(editor).summernote("insertImage",data.url);
				}
			});
		}
	});
</script>
<meta charset="UTF-8">
<title>공지사항 입력</title>
</head>
<body>
<jsp:include page="../header/header.jsp"></jsp:include>
<main>
<br><br><br><br><br><br><br>
	<div class="container" >
		<div class="row">
			<div class="col-sm-12 text-center" >
			<h2 style="color: #8ba525;"> 공지사항 입력</h2>
				<form action="/notice/register.kh" method="post" enctype="multipart/form-data">
					<table class="table table-boardered" border="1" style="width: 800px;">
					<tr>
						<td width="200px">선택</td>
						<td width="600px">
							<select id="selectCategory" name="noticeCatagory"  class="form-select" aria-label="Default select example" >
								<option selected>Open this select menu</option>
								<option value="0" label="공지"></option>
								<option value="1" label="업데이트"></option>
								<option value="2" label="이벤트"></option>
								<option value="3" label="안내"></option>
							</select>	
						</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>
							<input type="text" id="inputWriter" name="noticeWriter"  class="form-control" value="admin">
						</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>
							<input type="text" id="inputTitle" onkeyup="titleLengthCheck(this);" name="noticeWriter"  class="form-control" placeholder="제목을 입력하세요" required>
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<div class="form-floating">
								<textarea id="summernote" name="noticeContents"></textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td>
							<div class="form-floating">
								<input type="file" name="uploadFile">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="등록">
							<input type="reset" value="취소">
						</td>
					</tr>
					</table>
				</form>
			</div>
		</div>               
	</div>
</main>
<script>
//제목 길이 유효성 검사 / 30글자 넘으면 지워진다.
 function titleLengthCheck(thisInput){
 	console.log(thisInput.value.length);
 	if(thisInput.value.length>30){
 		thisInput.value = thisInput.value.substr(0,30);
 	}	
}

</script>
<jsp:include page="../footer/footer.jsp"></jsp:include>
</body>
</html>