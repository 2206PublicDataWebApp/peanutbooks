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


    } else {
        mypage.style.animation = 'fade-out 1s'
        
        mypage.style.display = 'none';
        document.querySelector('#user-icon').src = '/resources/img/header/icons8-user.png';

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


    } else {
        point.style.animation = 'fade-out 0.2s ease-out forwards'
        document.querySelector('#p-icon').src = '/resources/img/header/icons8-peanut-48 (6).png';
       
        point.style.display = 'none';
    }

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


    } else {
        adminPage.style.animation = 'fade-out 0.2s ease-out forwards'
        
        adminPage.style.display = 'none';
        document.querySelector('#admin-icon').src = '/resources/img/header/icons8-monarch-48.png';
    }

}