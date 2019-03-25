import Vue from 'vue';
import App from './App.vue';
import './style.css';

// 生产提示
Vue.config.productionTip = true;

new Vue({
  render: h => h(App),
}).$mount('#app');
