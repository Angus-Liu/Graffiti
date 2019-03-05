var cpuHistory, memHistory,
    pointsNum = 20,
    cpuChartPie, cpuChartLine, memChartPie, memChartLine;

function init() {
    cpuHistory = {
        user: [],
        kernel: [],
        total: [],
        lastUser: [],
        lastKernel: [],
        lastTotal: []
    };

    memHistory = {
        used: []
    };

    initCpuHistory();
}

function initCpuHistory() {
    for (let i = 0; i < pointsNum; i++) {
        cpuHistory.user.push(0);
        cpuHistory.kernel.push(0);
        cpuHistory.total.push(0);
    }

    chrome.system.cpu.getInfo(info => {
        for (let i = 0; i < info.processors.length; i++) {
            cpuHistory.lastTotal.push(info.processors[i].usage.total);
            cpuHistory.lastUser.push(info.processors[i].usage.user);
            cpuHistory.lastKernel.push(info.processors[i].usage.kernel);
        }

        initMemHistory();
    });
}

function initMemHistory() {
    for (let i = 0; i < pointsNum; i++) {
        memHistory.used.push(0);
    }
    updateData();
    setInterval(updateData, 1000);
}

function updateData() {
    updateCpuHistory();
    updateMemHistory();
}

function updateCpuHistory() {
    getCpuUsage(usage => {
        cpuHistory.user.shift();
        cpuHistory.user.push(usage.user);
        cpuHistory.kernel.shift();
        cpuHistory.kernel.push(usage.kernel);
        cpuHistory.total.shift();
        cpuHistory.total.push(usage.total);
        showCpu();
    });
}

function updateMemHistory() {
    getMemUsage(usage => {
        memHistory.used.shift();
        memHistory.used.push(Math.round((usage.capacity - usage.availableCapacity) / usage.capacity * 100));
        showMem();
    });
}

function getCpuUsage(callback) {
    chrome.system.cpu.getInfo(info => {
        var total = 0;
        var user = 0;
        var kernel = 0;
        for (let i = 0; i < info.processors.length; i++) {
            total += info.processors[i].usage.total - cpuHistory.lastTotal[i];
            cpuHistory.lastTotal[i] = info.processors[i].usage.total;
            user += info.processors[i].usage.user - cpuHistory.lastUser[i];
            cpuHistory.lastUser[i] = info.processors[i].usage.user;
            kernel += info.processors[i].kernel - cpuHistory.lastKernel[i];
            cpuHistory.lastKernel[i] = info.processors[i].usage.kernel;
        }
        user = Math.round(user / total * 100);
        kernel = Math.round(kernel / total * 100);
        callback({
            user: user,
            kernel: kernel,
            total: user + total
        });
    });
}

function getMemUsage(callback) {
    chrome.system.memory.getInfo(info => {
        callback(info);
    });
}

function showCpu() {
    var history = {
        labels: (function () {
            for (var i = 0, labels = []; i < pointsNum; labels.push(''), i++);
            return labels;
        })(),
        datasets: [{
                fillColor: "rgba(220,220,220,0.5)",
                data: cpuHistory.total
            },
            {
                fillColor: "rgba(90,140,255,0.5)",
                data: cpuHistory.kernel
            },
            {
                fillColor: "rgba(255,90,90,0.5)",
                data: cpuHistory.user
            }
        ]
    };

    var now = [{
            value: cpuHistory.total[pointsNum - 1],
            color: "rgba(220,220,220,0.7)"
        },
        {
            value: 100 - cpuHistory.total[pointsNum - 1],
            color: "rgba(220,220,220,0.3)"
        }
    ];
    var hisCtx = document.getElementById('cpu-history').getContext("2d");
    var nowCtx = document.getElementById("cpu-total").getContext("2d");
    if (!cpuChartLine || !cpuChartPie) {
        cpuChartLine = new Chart(hisCtx);
        cpuChartPie = new Chart(nowCtx);
    }
    cpuChartLine.Line(history, {
        scaleFontSize: 4,
        pointDot: false,
        animation: false
    });
    cpuChartPie.Pie(now, {
        segmentShowStroke: false,
        animation: false
    });
}

function showMem() {
    var history = {
        labels: (function () {
            for (var i = 0, labels = []; i < pointsNum; labels.push(''), i++);
            return labels;
        })(),
        datasets: [{
            fillColor: "rgba(220,220,220,0.5)",
            data: memHistory.used
        }]
    };

    var now = [{
            value: memHistory.used[pointsNum - 1],
            color: "rgba(220,220,220,0.7)"
        },
        {
            value: 100 - memHistory.used[pointsNum - 1],
            color: "rgba(220,220,220,0.3)"
        }
    ];
    var hisCtx = document.getElementById('mem-history').getContext("2d");
    var nowCtx = document.getElementById("mem-total").getContext("2d");
    if (!memChartLine || !memChartPie) {
        memChartLine = new Chart(hisCtx);
        memChartPie = new Chart(nowCtx);
    }
    memChartLine.Line(history, {
        scaleFontSize: 4,
        pointDot: false,
        animation: false
    });
    memChartPie.Pie(now, {
        segmentShowStroke: false,
        animation: false
    });
}

init();
