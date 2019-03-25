import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import store from './store'
import App from './App.vue'

Vue.config.productionTip = false;

// 加载插件
Vue.use(VueRouter);

new Vue({
  // 使用 vue-router
  router,
  // 使用 vuex
  store,
  render: h => h(App),
}).$mount('#app');
