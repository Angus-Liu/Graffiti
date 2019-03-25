// l 分钟以前，显示“刚刚”。
// 1 分钟～ 1 小时之间，显示“xx 分钟前”．
// 1 小时～ l 天之间，显示“xx 小时前”．
// l 天～ 1 个月 01 天）之间，显示“xx 天前” ．
// 大于 1 个月，显示“xx 年 xx 月 xx 曰”

let Time = {
  // 获取当前时间戳
  getUnix() {
    let date = new Date();
    return date.getTime();
  },
  // 获取今天 0 点 0 分 0 秒的时间戳
  getTodayUnix() {
    let date = new Date();
    date.setHours(0);
    date.setMinutes(0);
    date.setSeconds(0);
    date.setMilliseconds(0);
    return date.getTime();
  },
  // 获取今年 1 月 1 日 0 点 0 分 0 秒的时间戳
  getYearUnix() {
    let date = new Date();
    date.setMonth(0);
    date.setDate(1);
    date.setHours(0);
    date.setMinutes(0);
    date.setSeconds(0);
    date.setMilliseconds(0);
    return date.getTime();
  },
  // 获取标准年月日
  getLastDate(time) {
    let date = new Date(item);
    let month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
    let day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    return date.getFullYear() + '-' + month + '-' + day;
  },
  // 转换时间
  getFormatTime(timestamp) {
    let now = this.getUnix();
    let today = this.getTodayUnix();
    let year = this.getYearUnix();
    let timer = (now - timestamp) / 1000; // 转换为秒级时间戳
    let tip = '';

    if (timer <= 0 || Math.floor(timer / 60) <= 0) {
      tip = "刚刚"
    } else if (timer < 3600) {
      tip = Math.floor(timer / 60) + '分钟前';
    } else if (timer >= 3600 && (timestamp - today) >= 0) {
      tip = Math.floor(timer / 3600) + '小时前';
    } else if (timer / 86400 <= 31) {
      tip = Math.ceil(timer / 86400) + '天前';
    } else {
      tip = this.getLastDate(timestamp);
    }

    return tip;
  }
};

export default {
  bind: function (el, binding) {
    el.innerHTML = Time.getFormatTime(binding.value * 1000);
    el.__timeout__ = setInterval(() =>
      el.innerHTML = Time.getFormatTime(binding.value  * 1000), 60000);
  },

  unbind: function (el) {
    clearInterval(el.__timeout__);
    delete el.__timeout__;
  }
};
