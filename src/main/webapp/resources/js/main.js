document.querySelectorAll('header li')[0].style.color='#FFD384'


if (window.innerWidth < 500) {
    document.querySelector('#reco-text-2').className = 'text-truncate';
    document.querySelector('#ori-text-2').className = 'text-truncate';
    document.querySelector('#slide-img').classList.remove('container');

} else {
    document.querySelector('#reco-text-2').className = '';
    document.querySelector('#ori-text-2').className = '';
    document.querySelector('#slide-img').className = 'container';

}
window.onresize = function () {
    if (window.innerWidth < 500) {
        document.querySelector('#reco-text-2').className = 'text-truncate';
        document.querySelector('#ori-text-2').className = 'text-truncate';
        document.querySelector('#slide-img').classList.remove('container');

    } else {
        document.querySelector('#reco-text-2').className = '';
        document.querySelector('#ori-text-2').className = '';
         document.querySelector('#slide-img').className = 'container';

    }
}

