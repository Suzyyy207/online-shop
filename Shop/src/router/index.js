import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Login',
      component: () => import('../views/WelcomeWeb/LoginWeb.vue')
    },
    {
      path: '/Register',
      name: 'Register',
      component: () => import('../views/WelcomeWeb/RegisterWeb.vue')
    },
    {
      path: '/UserWeb',
      name: 'UserWeb',
      component: () => import('../views/MainWeb/User/UserWeb.vue')
    },
    {
      path: '/ShopkeeperWeb',
      name: 'ShopkeeperWeb',
      component: () => import('../views/MainWeb/Shopkeeper/ShopkeeperWeb.vue')
    },
    {
      path: '/ManagerWeb',
      name: 'ManagerWeb',
      component: () => import('../views/MainWeb/Manager/ManagerWeb.vue')
    },
    {
      path: '/ManagerWebBlank',
      name: 'ManagerWebBlank',
      component: () => import('../views/MainWeb/Manager/ManagerWebBlank.vue')
    },
    {
      path: '/ShopDashboard',
      name: 'ShopDashboard',
      component: () => import('../views/MainWeb/Shopkeeper/ShopkeeperDashboard.vue')
    },
    {
      path: '/ShopDashboardBlank',
      name: 'ShopDashboardBlank',
      component: () => import('../views/MainWeb/Shopkeeper/ShopkeeperDashboardBlank.vue')
    },
    // 上述页面已添加拦截器

    // 下述页面是为了调试方便的组件路径，最终需要封装到views中，因此不需要额外添加拦截器
    // 提交项目前保证无组件路径
    {
      path: '/UserInfo',
      name: 'UserInfo',
      component: () => import('../components/MainWeb/Components/userInfo.vue')
    },
    {
      path: '/GoodsRegister',
      name: 'GoodsRegister',
      component: () => import('../components/Dashboard/Goods/GoodsRegister.vue')
    },
    {
      path: '/GoodsImg',
      name: 'GoodsImg',
      component: () => import('../components/Dashboard/Goods/GoodsImg.vue')
    },
    {
      path: '/UploadImg',
      name: 'UploadImg',
      component: () => import('../components/MainWeb/Components/UploadImg.vue')
    }
    ,
    {
      path: '/upload',
      name: 'upload',
      component: () => import('../components/MainWeb/Components/upload.vue')
    },
    {
      path: '/GoodsShow',
      name: 'GoodsShow',
      component: () => import('../components/Dashboard/Goods/GoodsShow.vue')
    },
    {
      path: '/WrapperGoods',
      name: 'WrapperGoods',
      component: () => import('../components/Dashboard/Wrapper/WrapperGoods.vue')
    }
  ]
})
router.beforeEach((to, from, next) => {
  console.log(`Navigating from ${from.path} to ${to.path}`);
  next();
});
export default router
