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
      component: () => import('../views/MainWeb/Shopkeeper/ShopDashboard.vue')
    },
    {
      path: '/ShopDashboardBlank',
      name: 'ShopDashboardBlank',
      component: () => import('../views/MainWeb/Shopkeeper/ShopkeeperDashboardBlank.vue')
    },
    // 上述页面已添加拦截器


    //新添加的个人中心页面，未添加拦截器
    {
      path: '/userSelfCenter',
      name: 'userSelfCenter',
      component: () => import('../views/SelfCenter/UserSC.vue')
    },
    {
      path: '/shopkeeperSelfCenter',
      name: 'shopkeeperCenter',
      component: () => import('../views/SelfCenter/ShopkeeperSC.vue')
    },

    //新添加的购物车界面，未加拦截器
    {
      path: '/userSL',
      name: 'userShoppingList',
      component: () => import('../views/ShoppingList/UserSL.vue')
    },
    {
      path: '/shopkeeperSL',
      name: 'shopkeeperShoppingList',
      component: () => import('../views/ShoppingList/ShopkeeperSL.vue')
    },

    // 下述页面是为了调试方便的组件路径，最终需要封装到views中，因此不需要额外添加拦截器
    // 提交项目前保证无组件路径
    {
      path: '/GoodsDetail4User/:goodsId',
      name: 'GoodsDetail4User',
      component: () => import('../components/MainWeb/Components/GoodsDetail4User.vue')
    },
    {
      path: '/ShopDetail4User/:shopname',
      name: 'ShopDetail4User',
      component: () => import('../components/MainWeb/Components/ShopDetail4User.vue')
    },
    {
      path: '/GoodsRegister',
      name: 'GoodsRegister',
      component: () => import('../components/Dashboard/Goods/GoodsRegister.vue')
    },
    {
      path: '/Avatar',
      name: 'Avatar',
      component: () => import('../components/Dashboard/User/UserAvatar.vue')
    },
    {
      path: '/GoodsImg',
      name: 'GoodsImg',
      component: () => import('../components/Dashboard/Goods/GoodsImg.vue')
    }
    ,
    {
      path: '/upload',
      name: 'upload',     
       component: () => import('../components/Dashboard/User/upload.vue')
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
    },
    {
      path: '/SetUserInfo',
      name: 'SetUserInfo',
      component: () => import('../components/Dashboard/User/SetUserInfo.vue')
    }
    
  ]
})
router.beforeEach((to, from, next) => {
  console.log(`Navigating from ${from.path} to ${to.path}`);
  next();
});
export default router
