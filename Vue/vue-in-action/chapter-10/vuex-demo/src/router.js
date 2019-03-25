import VueRouter from 'vue-router'
import Index from './router/views/index'
import About from './router/views/about'
import User from './router/views/user'

const RouterConfig = {
  // 使用 HTML5 的 History 路由模式
  mode: 'history',
  routes: [
    {
      path: '/index',
      // meta 为路由元信息
      meta: {
        title: '首页'
      },
      component: Index
    }, {
      path: '/about',
      meta: {
        title: '关于'
      },
      component: About
    }, {
      path: '/user/:id',
      meta: {
        title: '个人主页'
      },
      component: User
    }, {
      path: '*',
      redirect: '/index'
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

export default router;
