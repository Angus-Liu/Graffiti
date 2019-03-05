function gotoGithub(text, disposition) {
    window.open(`https://github.com/search?q=${text}`);
}

function updateSuggest(text, suggest) {
    suggest([{
        'content': text,
        'description': `Search ${text} in GitHub`
    }]);
}

chrome.omnibox.onInputChanged.addListener(updateSuggest);
chrome.omnibox.onInputEntered.addListener(gotoGithub);
