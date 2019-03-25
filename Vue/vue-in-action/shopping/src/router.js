import VueRouter from 'vue-router'
import Vue from 'vue';
import List from './views/list';
import Product from './views/product';
import Cart from './views/cart';

// 加载插件
Vue.use(VueRouter);

const RouterConfig = {
  // 使用 HTML5 的 History 路由模式
  mode: 'history',
  routes: [
    {
      path: '/list',
      // meta 为路由元信息
      meta: {
        title: '商品列表'
      },
      component: List
    }, {
      path: '/product/:id',
      meta: {
        title: '商品详情'
      },
      component: Product,
      props: true
    }, {
      path: '/cart',
      // meta 为路由元信息
      meta: {
        title: '购物车'
      },
      component: Cart
    }, {
      path: '*',
      // 未匹配的 url 重定向到 list
      redirect: '/list'
    }
  ]
};

const router = new VueRouter(RouterConfig);

// vue-router 提供了导航钩子 beforeEach 和 afterEach，它们会在路由即将改变前和改变后触发
router.beforeEach((to, from, next) => {
  // 设置标题可以在 beforeEach 钩子完成
  window.document.title = to.meta.title;
  next();
});

router.afterEach((to, from, next) => {
  window.scrollTo(0, 0);
});

export default router;
