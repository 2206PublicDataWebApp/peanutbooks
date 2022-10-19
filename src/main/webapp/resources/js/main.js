document.querySelectorAll('header li')[0].style.color='#FFD384'


if (window.innerWidth < 500) {
    document.querySelector('#reco-text-2').className = 'text-truncate';
    document.querySelector('#ori-text-2').className = 'text-truncate';


} else {
    document.querySelector('#reco-text-2').className = '';
    document.querySelector('#ori-text-2').className = '';


}
window.onresize = function () {
    if (window.innerWidth < 500) {
        document.querySelector('#reco-text-2').className = 'text-truncate';
        document.querySelector('#ori-text-2').className = 'text-truncate';


    } else {
        document.querySelector('#reco-text-2').className = '';
        document.querySelector('#ori-text-2').className = '';
  

    }
}


document.querySelectorAll('.first-category')[0].style.animation = 'ontext 0.3s ease-out forwards';
document.querySelectorAll('.second-category')[0].style.opacity= 0;
document.querySelectorAll('.third-category')[0].style.opacity= 0;
document.querySelectorAll('.first-category')[1].style.opacity= 0;
document.querySelectorAll('.second-category')[1].style.opacity= 0;
document.querySelectorAll('.third-category')[1].style.opacity= 0;
document.querySelector('#article3').style.opacity= 0;
document.querySelector('#comment').style.opacity= 0;
window.addEventListener('scroll', function(){

	 if (matchMedia("screen and (max-width: 500px)").matches){
		 if(window.scrollY>100){
		 
		}
		if(window.scrollY>645){
		document.querySelectorAll('.second-category')[0].style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>1200){
		document.querySelectorAll('.third-category')[0].style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>1920){
		 document.querySelectorAll('.first-category')[1].style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>2500){
		document.querySelectorAll('.second-category')[1].style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>3085){
		document.querySelectorAll('.third-category')[1].style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>3680){
		document.querySelector('#article3').style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>3880){
		document.querySelector('#comment').style.animation = 'ontext 0.3s ease-out forwards';
		}
	 }else{
		
		if(window.scrollY>350){
		document.querySelectorAll('.second-category')[0].style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>750){
		document.querySelectorAll('.third-category')[0].style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>1400){
		 document.querySelectorAll('.first-category')[1].style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>1800){
		document.querySelectorAll('.second-category')[1].style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>2200){
		document.querySelectorAll('.third-category')[1].style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>2700){
		document.querySelector('#article3').style.animation = 'ontext 0.3s ease-out forwards';
		}
		if(window.scrollY>3000){
		document.querySelector('#comment').style.animation = 'ontext 0.3s ease-out forwards';
		}
	 
	 }
	
});

