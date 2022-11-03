var menu = document.querySelector('#togglemenu');

var user = document.querySelector('#user-icon');
var mypage = document.querySelector('#mypage-tooltip-area');
var menubar = document.querySelector('#menu-bar');
var point = document.querySelector('#point-tooltip-area');
var picon = document.querySelector('#p-icon');
var admin =  document.querySelector('#admin-icon');
var adminPage = document.querySelector('#admin-tooltip-area');
mypage.style.display = 'none';
point.style.display='none';
adminPage.style.display='none';
 menubar.style.display = 'none';
var userOn = false;
var pointOn = false;
var adminOn= false;




user.onclick = function () {
    if (mypage.style.display == 'none') {
        mypage.style.animation = 'fade-in 0.3s ease-out forwards'
        mypage.style.display = 'block';
        menubar.style.display = 'none';
        point.style.display = 'none';
        adminPage.style.display = 'none';
        document.querySelector('#user-icon').src = '/resources/img/header/icons8-user-yellow.png';
        document.querySelector('#p-icon').src = '/resources/img/header/icons8-peanut-48 (6).png';
         document.querySelector('#admin-icon').src = '/resources/img/header/icons8-monarch-48.png';
	userOn = true;

    } else {
        mypage.style.animation = 'fade-out 1s'
        
        mypage.style.display = 'none';
        document.querySelector('#user-icon').src = '/resources/img/header/icons8-user.png';
        userOn = false;

    }

}

function getPeanutPoint() {
	var xhttp = new XMLHttpRequest();
	var memberId = document.querySelector("#member-id").value;
	xhttp.open('get', '/ppoint/pointsum.kh?memberId='+memberId, true);
	xhttp.onreadystatechange = function() {
		if (this.status >= 200 && this.status < 400) {
			var resp = this.response;
		    document.querySelector('#now-point').innerHTML= resp+"땅콩";
	  	} else {

		    var e = this.response;
		    alert("error :"+e);
		}
	}
	xhttp.send();
}



//고객 채팅 연결
	function chatStart() {
	var memberId = document.querySelector("#member-id").value;
		if (memberId == '') {
			alert("로그인후 가능합니다");
		} else {
			
			var xhttp = new XMLHttpRequest();
			xhttp.open('get', '/client/chatCheck.kh', true);
			xhttp.onreadystatechange = function() {
				if (this.status >= 200 && this.status < 400) {
					var result = this.response;	
					
				    if (JSON.parse(result).switchbtn.trim() == 'N') {
							alert("관리자가 준비되지 않았습니다. 잠시후 부탁드립니다."); //버튼값이 n이면 그냥 종료
						} else {
							chatbtnSuccess(memberId) //y이면 로그인 확인
						}			  	
  	
			  	} else {
				    var e = this.response;
				    alert("error :"+e);
				}
	}
	xhttp.send();	
				
				
				
			}
		}


picon.onclick = function () {
    if (point.style.display == 'none') {
        point.style.animation = 'fade-in 0.3s ease-out forwards'
        point.style.display = 'block';
        menubar.style.display = 'none';
        mypage.style.display = 'none';
        adminPage.style.display = 'none';
        document.querySelector('#p-icon').src = '/resources/img/header/icons8-peanut-48 (6)-yellow.png';
        document.querySelector('#user-icon').src = '/resources/img/header/icons8-user.png';
        document.querySelector('#admin-icon').src = '/resources/img/header/icons8-monarch-48.png';
        pointOn = true;
        
    } else {
        point.style.animation = 'fade-out 0.2s ease-out forwards'
        document.querySelector('#p-icon').src = '/resources/img/header/icons8-peanut-48 (6).png';
       
        point.style.display = 'none';
        pointOn = false;
        
    }
	getPeanutPoint();
}
menu.onclick = function () {
    if (menubar.style.display == 'none') {
        menubar.style.animation = 'fade-in 0.3s ease-out forwards'
        menubar.style.display = 'block'
        point.style.display = 'none';
        mypage.style.display = 'none';
        adminPage.style.display = 'none';
         document.querySelector('#p-icon').src = '/resources/img/header/icons8-peanut-48 (6).png';
        document.querySelector('#user-icon').src = '/resources/img/header/icons8-user.png';
        document.querySelector('#admin-icon').src = '/resources/img/header/icons8-monarch-48.png';
        
      

    } else {
        menubar.style.animation = 'fade-out 0.2s ease-out forwards'
       
        menubar.style.display = 'none'
        

    }
}

admin.onclick = function () {
    if (adminPage.style.display == 'none') {
        adminPage.style.animation = 'fade-in 0.3s ease-out forwards'
        adminPage.style.display = 'block';
        mypage.style.display = 'none';
        menubar.style.display = 'none';
        point.style.display = 'none';
        document.querySelector('#admin-icon').src = '/resources/img/header/icons8-monarch-48-yellow.png';
        document.querySelector('#p-icon').src = '/resources/img/header/icons8-peanut-48 (6).png';
        document.querySelector('#user-icon').src = '/resources/img/header/icons8-user.png';
        adminOn= true;


    } else {
        adminPage.style.animation = 'fade-out 0.2s ease-out forwards'
        
        adminPage.style.display = 'none';
        document.querySelector('#admin-icon').src = '/resources/img/header/icons8-monarch-48.png';
        adminOn= true;
    }

}



//221103 은정님 추가

   document.getElementById('peanutMenu').style.display='none';
	function mainPN(){
		document.getElementById('peanutMenu').style.display='block';
	}