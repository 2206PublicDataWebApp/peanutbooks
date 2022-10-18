
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

