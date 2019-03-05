chrome.runtime.onMessage.addListener((msg, sender, callback) => {
    if (msg == 'hello') {
        callback('<h1>I got it!</h1>');
    }
});
