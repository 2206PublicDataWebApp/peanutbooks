

var title= document.querySelector('[name="title"]');
var titleValue = document.querySelector('#titleValue');
title.value = titleValue.value;



var content = document.querySelector('[name="contents"]'); //현재 로드한 시리즈의 내용값

function contentsInSumNote(){
 document.querySelector('.note-placeholder').style.display='none';//플레이스 홀더 안보이게
 document.querySelector('.note-editable').innerHTML= content.value;//로드한 내용값을 섬머노트로 넣는다
	
}

function loadFile(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
    output.onload = function() {
        URL.revokeObjectURL(output.src) // free memory
    };
    imgCheck()
};

function imgView(obj) {

    var imgid = obj.parentNode.previousElementSibling;

    imgid.src = URL.createObjectURL(event.target.files[0]);
    imgid.onload = function() {
        URL.revokeObjectURL(imgid.src) // free memory
        if(imgid.src!=''){
            imgid.style.background='gray';
        }

    }
    imgCheck(imgid)

};


var imgFile = document.querySelectorAll('.isFile');
var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/i;
var fileSize = 2 * 1024 * 1024;
function imgCheck(obj) {
    for (var i = 0; i < imgFile.length; i++) {
        if (imgFile[i].value != "") {

            if (!imgFile[i].value.match(fileForm)) {
                alert("이미지 파일만 업로드 가능");
                imgFile[i].value = "";
                obj.src='/resources/bookImg/error.png';

            }

            if(imgFile[i].files[0].size>fileSize){
                alert("2메가 이상은 등록할수 없습니다.");
                imgFile[i].value = "";
                obj.src='/resources/bookImg/error.png';

            }
        }
    }

};

$('#summernote').summernote({
    placeholder: '내용을 입력하세요',
    tabsize: 2,
    height: 500,
    toolbar: [
      ['style', ['style']],
      ['font', ['bold', 'underline', 'clear']],
      ['color', ['color']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['table', ['table']],
      ['insert', ['link']],
      ['view', ['fullscreen', 'codeview', 'help']],
      ['insert', ['link', 'picture']]
    ],
    
    callbacks : { //여기 부분이 이미지를 첨부하는 부분
            onImageUpload : function(files, editor,
            welEditable) {
            for (var i = files.length - 1; i >= 0; i--) {
            uploadSummernoteImageFile(files[i],
            this);
            		}
            	}
            }
         
 
     
  });
  
  
  
  //섬머노트 파일저장
    function uploadSummernoteImageFile(file, el) {
			data = new FormData();
			data.append("file", file);
			
			$.ajax({
				data : data,
				type : "POST",
				url : "/uploadSummernoteImageFile",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(data) {
					$(el).summernote('editor.insertImage', data.url);
				}
			});
		}
  
  

  document.querySelector('.note-editable').onblur = function(){
    var textarea = document.querySelector('[ name="contents"]');
    var textvalue = document.querySelector('.note-editable');
        textarea.value = textvalue .innerHTML;
        }
        
        
  function fnChkByte(obj){
  
   var str = obj.value;
    var str_len = str.length;

	var maxByte = 500;
    var rbyte = 0;
    var rlen = 0;
    var one_char = "";
    var str2 = "";


    for(var i=0; i<str_len; i++)
    {
        one_char = str.charAt(i);
        if(escape(one_char).length > 4) {
            rbyte += 2;                                         //한글2Byte
        }else{
            rbyte++;                                            //영문 등 나머지 1Byte
        }
        if(rbyte <= maxByte){
            rlen = i+1;                                          //return할 문자열 갯수
        }
     }
     if(rbyte > maxByte)
     {
        // alert("한글 "+(maxByte/2)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
        alert("메세지는 최대 " + maxByte + "byte를 초과할 수 없습니다.")
        str2 = str.substr(0,rlen);                                  //문자열 자르기
        obj.value = str2;
        fnChkByte(obj, maxByte);
     }
     else
     {
        document.getElementById('byteInfo').innerText = rbyte;
     }
   
   
  }
  