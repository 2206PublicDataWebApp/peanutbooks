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
      ['view', ['fullscreen', 'codeview', 'help']]
    ]
  });

  document.querySelector('.note-editable').onblur = function(){
    var textarea = document.querySelector('[ name="Contents"]');
    var textvalue = document.querySelector('.note-editable');
        textarea.value = textvalue .innerHTML;
        }