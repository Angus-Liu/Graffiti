function httpRequest(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            callback(xhr.responseText);
        }
    }

    xhr.send();
}

function showWeather(result) {
    result = JSON.parse(result);
    if (result.error_code != 0) {
        alert("查询失败！");
        return;
    }
    result = result.result;
    var content = ` 
            <div id='city'>当前城市: ${city}</div>
            <table>
            <tr>
                <th>日期</th>
                <th>天气</th>
                <th>温度</th>
                <th>风向</th>
            </tr>`;
    result.future.forEach(item => {
        content += `
            <tr>
                <td>${item.date}</td>
                <td>${item.weather}</td>
                <td>${item.temperature}</td>
                <td>${item.direct}</td>
            </tr>`;
    });
    content += `</table>`;
    document.getElementById('weather').innerHTML = content;
}

var city = localStorage.city;
city = city ? city : '成都';
var url = `http://apis.juhe.cn/simpleWeather/query?city=${city}&key=1644080cd996c120a065abe3d10ee0b1`;
httpRequest(url, showWeather);
