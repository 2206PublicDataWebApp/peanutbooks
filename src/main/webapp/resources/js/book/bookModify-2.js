
function loadTitle(){
var title = document.querySelector('#hidden-title');
var writer = document.querySelector('#hidden-writer');
document.querySelector('#title-text').value = title.value
document.querySelector('#writer-name').value = writer.value
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
    imgCheck()

};


var imgFile = document.querySelectorAll('.isFile');
var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/i;
var fileSize;
function imgCheck() {
    for (var i = 0; i < imgFile.length; i++) {
        if (imgFile[i].value != "") {

            if (!imgFile[i].value.match(fileForm)) {
                alert("이미지 파일만 업로드 가능");
                imgFile[i].value = "";

            }
        }
    }

};
        
  function fnChkByte(obj){
  
   var str = obj.value;
    var str_len = str.length;

	var maxByte = 600;
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
  