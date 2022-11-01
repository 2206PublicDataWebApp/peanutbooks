var header = document.querySelector('header');
header.style.backgroundColor='rgba( 255, 255, 255, 0.5 )'

window.addEventListener('scroll', function(){

	if(window.scrollY>140){
	header.style.backgroundColor='rgba( 255, 255, 255, 1 )'
		 
		}
		if(window.scrollY==0){
	header.style.transition='0.5s';
	header.style.backgroundColor='rgba( 255, 255, 255, 0.5 )'
		 
		}


	
});