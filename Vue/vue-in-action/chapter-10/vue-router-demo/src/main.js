import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import App from './App.vue'

Vue.config.productionTip = false;

// 加载插件
Vue.use(VueRouter);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
