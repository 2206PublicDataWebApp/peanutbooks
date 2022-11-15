

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
            imgid.style.background='white';
        }

    }
    imgCheck(imgid)

};
function imgView1(obj) {

    var imgid = obj.parentElement.parentElement.previousElementSibling.previousElementSibling;

    
    imgid.src = URL.createObjectURL(event.target.files[0]);
    imgid.onload = function() {
        URL.revokeObjectURL(imgid.src) // free memory
        if(imgid.src!=''){
            imgid.style.background='white';
        }

    }
    imgCheck(imgid)

};


var imgFile = document.querySelectorAll('.isFile');
var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/i;
var fileSize =2 * 1024 * 1024;
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


//사진 수정메뉴 띄우기


function picModifyMenu(){
	if(document.querySelector('#profilePicMenu').style.display=='none'){
		document.querySelector('#profilePicMenu').style.display='block';
		document.querySelector('#profilePicMenu').style.animation = 'ontext 0.3s ease-out forwards';
	 }else{
	 	document.querySelector('#profilePicMenu').style.animation = 'Rontext 0.3s ease-out forwards';
	 	setTimeout(function(){	
			document.querySelector('#profilePicMenu').style.display='none';			
		}, 300);
	 	
	 }
}

//사진삭제
function removeProPic(){
document.querySelector('#proPic').src='/resources/bookImg/defaultImg.jpg'
document.querySelector('[name=removeImg]').value="default";

}


