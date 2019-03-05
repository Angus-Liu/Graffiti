var currentWindow = chrome.app.window.current();

document.getElementById('minisize').onclick = () => {
    currentWindow.minisize();
}

document.getElementById('close').onclick = () => {
    currentWindow.close();
}
