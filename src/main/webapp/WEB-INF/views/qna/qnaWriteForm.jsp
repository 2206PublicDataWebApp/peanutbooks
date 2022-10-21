<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
</head>
 <style>
	.container {
		width: 70%;
		max-width: 1000px;
		margin: 0 auto;
	}

	.btn {
	  appearance: none;
	    -webkit-appearance: none;
	  font-family: sans-serif;
	  cursor: pointer;
	  padding: 12px;
	  min-width: 150px;
	  border: none;
	    -webkit-transition: background-color 100ms linear;
	    -ms-transition: background-color 100ms linear;
	     transition: background-color 100ms linear;
	}
</style> 


<body>
<!-- header start -->
<jsp:include page="../header/header.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
<br><br>	
<div class="container">

	<br><br>
	<table align="center" class="table col-10" style="border:none;">
		<tr>
			<td colspan="2" align="center" style="border:none;" height="30px">
				<div class="col-6" style="display: inline-block; margin: 0px;  float: left;">
					<input type="submit" value="문의내역" style="width:100%; height:30pt; background-color: #5e5e5e; border:0; color:white; border-radius : 2px;">
				</div>
				<div class="col-6" style="display: inline-block; margin: 0px;  float: left;">
					<input type="submit" value="문의작성" style="width:100%; height:30pt; background-color: #c9c9c9; border:0; color:white; border-radius : 2px;">
				</div>
			</td>
		</tr>
	</table>
	<table align="center" class="table col-10" bgcolor="#e0e0e0">
		<tr>
			<td class="col-2" align="center">총문의</td>
			<td rowspan="2" width="1px" style="vertical-align:middle;"><img src="/resources/img/sidebar.png"></td>
			<td class="col-2" align="center">답변완료</td>
			<td rowspan="2" width="1px" style="vertical-align:middle;"><img src="/resources/img/sidebar.png"></td>
			<td class="col-2" align="center">처리중</td>
			<td rowspan="2" width="1px" style="vertical-align:middle;"><img src="/resources/img/sidebar.png"></td>
			<td class="col-2" align="center">접수</td>
		</tr>
		<tr>
			<td class="col-2" align="center">1</td>
			<td class="col-2" align="center">1</td>
			<td class="col-2" align="center">1</td>
			<td class="col-2" align="center">1</td>
	</table>
		
	<form action="/qna/register.kh" method="post" enctype="multipart/form-data" name="qnaForm" id="qnaForm" onsubmit="return registerAction()">
		<table align="center" class="table col-10">
			<tr>
				<td class="col-2" scope="col" align="center">선택</td>
				<td>
					<select name="qnaCategory"  class="form-select" aria-label="Default select example" >
						<option selected>문의유형 선택</option>
						<option value="0" label="회원"></option>
						<option value="1" label="포인트"></option>
						<option value="2" label="도서"></option>
						<option value="3" label="홈페이지 이용"></option>
					</select>	
				</td>
			<tr>
				<td  class="col-2" scope="col" align="center">작성자</td>
				<td><input type="text" name="qnaWriter"  class="form-control" value="${sessionScope.loginMember.memberId}" readonly></td>
			</tr>
			<tr>
				<td  class="col-2" scope="col" align="center">제목</td>
				<td><input type="text" name="qnaTitle" onkeyup="titleLengthCk(this);" class="form-control"></td>
			</tr>
			<tr>
				<td  class="col-2" scope="col" align="center">내용</td>
				<td> <textarea class="form-control" id="exampleFormControlTextarea1" cols="50" rows="20" name="qnaContents"></textarea>  </td>
			</tr>
			<tr>
				<td class="col-2" scope="col" align="center">첨부파일(선택)</td>
				<td>
					<div class="container">
					  <button id="btn-upload" type="button" style="border: 1px solid #ddd; outline: none;">파일 추가</button>
					  	<input id="input_file" multiple="multiple" type="file" style="display:none;">
					  	<span style="font-size:10px; color: gray;">※첨부파일은 최대 3개까지 등록이 가능합니다.</span>
					  	<div class="data_file_txt" id="data_file_txt" style="margin:40px;">
							<span>첨부 파일</span>
							<br />
							<div id="articlefileChange">
							</div>
						</div>
					  	<button type="submit" style="border: 1px solid #ddd; outline: none;">전송</button>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center" style="border:none;">
					<input type="submit" value="등록" class="btn btn-warning btn-sm">
					<input type="reset" value="취소" class="btn btn-warning btn-sm">
					<button type="button" onclick="location.href='/qna/list.kh'" class="btn btn-warning btn-sm">목록</button> 
					 
				</td>
			</tr>
		</table>
	</form>
</div>
<br><br>
</main>
<!-- main contents End -->


<!-- 파일 업로드 스크립트 -->
<script>
$(document).ready(function()
		// input file 파일 첨부시 fileCheck 함수 실행
		{
			$("#input_file").on("change", fileCheck);
		});

/**
 * 첨부파일로직
 */
$(function () {
    $('#btn-upload').click(function (e) {
        e.preventDefault();
        $('#input_file').click();
    });
});

// 파일 현재 필드 숫자 totalCount랑 비교값
var fileCount = 0;
// 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
var totalCount = 10;
// 파일 고유넘버
var fileNum = 0;
// 첨부파일 배열
var content_files = new Array();

function fileCheck(e) {
    var files = e.target.files;
    
    // 파일 배열 담기
    var filesArr = Array.prototype.slice.call(files);
    
    // 파일 개수 확인 및 제한
    if (fileCount + filesArr.length > totalCount) {
      $.alert('파일은 최대 '+totalCount+'개까지 업로드 할 수 있습니다.');
      return;
    } else {
    	 fileCount = fileCount + filesArr.length;
    }
    
    // 각각의 파일 배열담기 및 기타
    filesArr.forEach(function (f) {
      var reader = new FileReader();
      reader.onload = function (e) {
        content_files.push(f);
        $('#articlefileChange').append(
       		'<div id="file' + fileNum + '" onclick="fileDelete(\'file' + fileNum + '\')">'
       		+ '<font style="font-size:12px">' + f.name + '</font>'  
       		+ '<img src="/img/icon_minus.png" style="width:20px; height:auto; vertical-align: middle; cursor: pointer;"/>' 
       		+ '<div/>'
		);
        fileNum ++;
      };
      reader.readAsDataURL(f);
    });
    console.log(content_files);
    //초기화 한다.
    $("#input_file").val("");
  }

// 파일 부분 삭제 함수
function fileDelete(fileNum){
    var no = fileNum.replace(/[^0-9]/g, "");
    content_files[no].is_delete = true;
	$('#' + fileNum).remove();
	fileCount --;
    console.log(content_files);
}

/*
 * 폼 submit 로직
 */
	function registerAction(){
		
	var form = $("form")[0];        
 	var formData = new FormData(form);
		for (var x = 0; x < content_files.length; x++) {
			// 삭제 안한것만 담아 준다. 
			if(!content_files[x].is_delete){
				 formData.append("article_file", content_files[x]);
			}
		}
   /*
   * 파일업로드 multiple ajax처리
   */    
	$.ajax({
   	      type: "POST",
   	   	  enctype: "multipart/form-data",
   	      url: "/qna/register.kh",
       	  data : formData,
       	  processData: false,
   	      contentType: false,
   	      success: function (data) {
   	    	if(JSON.parse(data)['result'] == "OK"){
   	    		alert("파일업로드 성공");
			} else
				alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
   	      },
   	      error: function (xhr, status, error) {
   	    	alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
   	     return false;
   	      }
   	    });
   	    return false;
	}
</script>

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>