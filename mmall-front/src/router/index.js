import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/pages/login';
import Regist from '@/pages/register';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: '登录',
      component: Login
    },
    {
      path: '/register',
      name: '注册',
      component: Regist
    }
  ]
});
