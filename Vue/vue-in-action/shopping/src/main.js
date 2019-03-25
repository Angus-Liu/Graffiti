import Vue from 'vue'
import router from './router'
import store from './store'
import App from './App.vue'
// 导入全局样式
import './style/style.css'

Vue.config.productionTip = true;

new Vue({
  // 使用 vue-router
  router,
  // 使用 vuex
  store,
  render: h => h(App),
}).$mount('#app');
