//  插件安装时执行
chrome.runtime.onInstalled.addListener(() => {
    chrome.contextMenus.create({
        'id': 'save-all-images',
        'type': 'normal',
        'title': '保存所有图片'
    });
});

chrome.contextMenus.onClicked.addListener(downloadAllImages);

function downloadAllImages(info, tab) {
    if (info.menuItemId == 'save-all-images') {
        chrome.tabs.executeScript(tab.id, {
            file: 'main.js'
        }, res => {
            if (res && res[0] && res[0].length) {
                res[0].forEach(url => {
                    chrome.downloads.download({
                        url: url,
                        conflictAction: 'uniquify',
                        saveAs: false
                    });
                });
            }
        });
    }
};
