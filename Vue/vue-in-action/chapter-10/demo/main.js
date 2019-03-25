// 导入 Vue 框架
import Vue from 'vue';
// 导入 app.vue组件
import App from './app.vue'

// 创建 Vue 根实例

new Vue({
  el: '#app',
  render: h => h(App)
});
