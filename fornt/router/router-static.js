//1.创建组件
import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import news from '@/views/modules/news/list'
import shetuanchengyuan from '@/views/modules/shetuanchengyuan/list'
import xuesheng from '@/views/modules/xuesheng/list'
import jiarushetuan from '@/views/modules/jiarushetuan/list'
import shetuanhuodong from '@/views/modules/shetuanhuodong/list'
import huodongbaoming from '@/views/modules/huodongbaoming/list'
import discussshetuanxinxi from '@/views/modules/discussshetuanxinxi/list'
import shetuanxinxi from '@/views/modules/shetuanxinxi/list'
import shezhang from '@/views/modules/shezhang/list'
import storeup from '@/views/modules/storeup/list'
import config from '@/views/modules/config/list'
import shetuanfenlei from '@/views/modules/shetuanfenlei/list'
import shetuantongji from '@/views/modules/shetuantongji/list' // 新增引入
import comments from '@/views/modules/comments/list';
import studentInterest from '@/views/modules/studentInterest/list';

//2.配置路由   注意：名字
const routes = [
  {
    path: '/index',
    name: '首页',
    component: Index,
    children: [
      {
        path: '/',
        name: '首页',
        component: Home,
        meta: {icon:'', title:'center'}
      }, {
        path: '/updatePassword',
        name: '修改密码',
        component: UpdatePassword,
        meta: {icon:'', title:'updatePassword'}
      }, {
        path: '/pay',
        name: '支付',
        component: pay,
        meta: {icon:'', title:'pay'}
      }, {
        path: '/center',
        name: '个人信息',
        component: center,
        meta: {icon:'', title:'center'}
      },
      {
        path: '/news',
        name: '社团新闻',
        component: news
      },
      {
        path: '/shetuanchengyuan',
        name: '社团成员',
        component: shetuanchengyuan
      },
      {
        path: '/xuesheng',
        name: '学生',
        component: xuesheng
      },
      {
        path: '/jiarushetuan',
        name: '加入社团',
        component: jiarushetuan
      },
      {
        path: '/shetuanhuodong',
        name: '社团活动',
        component: shetuanhuodong
      },
      {
        path: '/huodongbaoming',
        name: '活动报名',
        component: huodongbaoming
      },
      {
        path: '/discussshetuanxinxi',
        name: '社团信息评论',
        component: discussshetuanxinxi
      },
      {
        path: '/shetuanxinxi',
        name: '社团信息',
        component: shetuanxinxi
      },
      {
        path: '/shezhang',
        name: '社长',
        component: shezhang
      },
      {
        path: '/storeup',
        name: '我的收藏管理',
        component: storeup
      },
      {
        path: '/config',
        name: '轮播图管理',
        component: config
      },
      {
        path: '/shetuanfenlei',
        name: '社团分类',
        component: shetuanfenlei
      },
      {
        path: '/shetuantongji',
        name: '社团统计',
        component: shetuantongji,
        meta: {icon:'', title:'社团统计'}
      },// 新增的“社团统计”路由
      {
        path: "/comments",
        name: "评论管理",
        component: comments,
        meta: { icon: "comment", title: "评论管理" },
      },
      {
        path: '/studentInterest',
        name: '学生兴趣分析',
        component: studentInterest,
        meta: { icon: '', title: '学生兴趣分析' }
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]

//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;

