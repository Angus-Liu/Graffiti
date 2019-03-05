chrome.app.runtime.onLaunched.addListener(() => {

    var mainWindow = chrome.app.window.get('main');
    if (mainWindow) {
        mainWindow.show();
    } else {
        chrome.app.window.create('main.html', {
            'id': 'main',
            'innerBounds': {
                'width': 542,
                'height': 360
            },
            'resizable': false,
            'frame': 'none'
        });
    }
});
