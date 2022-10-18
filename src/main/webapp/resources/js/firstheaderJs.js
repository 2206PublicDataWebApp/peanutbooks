var loginicon = document.querySelector('#menubar');
var check = false;
loginicon.onclick = function () {
    if (!check) {
        document.querySelector('#toggle-menu').style.display = 'flex';
        check = true
    } else {
        document.querySelector('#toggle-menu').style.display = 'none';
        check = false;
    }
}
window.addEventListener('scroll', function () {

    if (matchMedia("screen and (max-width: 500px)").matches) {
        if (window.scrollY > 150) {
            document.querySelector('#main-text').style.animation = 'Rontext 0.3s ease-out forwards';
        }

        if (window.scrollY < 150) {
            document.querySelector('#main-text').style.animation = 'ontext 0.3s ease-out forwards';
        }


        if (window.scrollY < 300 || window.scrollY > 700) {
            document.querySelector('#section2-title').style.animation = 'Rontext 0.3s ease-out forwards';
        }

        if (window.scrollY > 300 && window.scrollY < 700) {
            document.querySelector('#section2-title').style.animation = 'ontext 0.3s ease-out forwards';
        }

        if (window.scrollY < 900) {
            document.querySelector('#faq-title').style.animation = 'Rontext 0.3s ease-out forwards';
        }

        if (window.scrollY > 900) {
            document.querySelector('#faq-title').style.animation = 'ontext 0.3s ease-out forwards';
        }



    } else {

        if (window.scrollY > 200) {
            document.querySelector('#main-text').style.animation = 'Rontext 0.3s ease-out forwards';
        }

        if (window.scrollY < 200) {
            document.querySelector('#main-text').style.animation = 'ontext 0.3s ease-out forwards';
        }


        if (window.scrollY < 300 || window.scrollY > 600) {
            document.querySelector('#section2-title').style.animation = 'Rontext 0.3s ease-out forwards';
        }

        if (window.scrollY > 300 && window.scrollY < 600) {
            document.querySelector('#section2-title').style.animation = 'ontext 0.3s ease-out forwards';
        }

        if (window.scrollY < 1250) {
            document.querySelector('#faq-title').style.animation = 'Rontext 0.3s ease-out forwards';
        }

        if (window.scrollY > 1250) {
            document.querySelector('#faq-title').style.animation = 'ontext 0.3s ease-out forwards';
        }

    }

})

