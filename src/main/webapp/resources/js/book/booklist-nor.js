var header = document.querySelector('header');
header.style.backgroundColor='rgba( 255, 255, 255, 0.5 )';
var li = document.querySelectorAll('header li')[1];
li.style='color: #FFD384; text-shadow: 2px 2px 2px  #fff';



window.addEventListener('scroll', function(){

	if(window.scrollY>140){
	header.style.backgroundColor='rgba( 255, 255, 255, 1 )'
		 
		}


	
});