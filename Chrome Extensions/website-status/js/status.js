function httpRequest(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            callback(true);
        }
    }

    xhr.onerror = () => {
        callback(false);
    }

    xhr.send();
}

setInterval(() => {
    httpRequest('http://www.google.cn', (status) => {
        chrome.browserAction.setIcon({
            path: 'images/' + (status ? 'online.png' : 'offline.png')
        });
    });
}, 5000);
