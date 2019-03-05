chrome.contextMenus.create({
    'type': 'normal',
    'title': `使用Google翻译%s`,
    'contexts': ['selection'],
    'id': 'cn',
    'onclick': translate
});

function translate(info, tab) {
    var url = `https://translate.google.com/?source=gtx_m#zh-CN/zh-CN/${info.selectionText}`;
    window.open(url, '_blank');
};
