<!-- 用户可见的商品简要信息展示 -->
<template>
    <div class="all">
        <div class="image">
            <img src="@/assets/shop.png">
        </div>
        <!--div class="image">
            <img v-if="goods.goodsAvatar.length == 0" class="logo" src="@/assets/shop.png" alt="点击进入店铺详情页面"/>
            <img v-else :src="'data:image/jpeg;base64,'+goods.goodsAvatar[0]">
        </div-->
        
        <div class="info">
            <p>商品名: 吃吃吃</p>
            <p>收藏量：100</p>
            <p>单价：￥100</p>
        </div>
        <!--div class="info">
            <p>商品名：{{ goods.goodsname }}</p>
            <p>收藏量： {{ goods.favorites }}</p>
            <p>单价：</p>
        </div-->
        
        <div class="buy">
            <el-input-number class="num"></el-input-number>
        </div>
        <div class="btn">
            <el-button class="b">加入购物车</el-button>
            <el-button class="b">
                <router-link :to="{ name: 'Goods4User'}" class="rt">
                点击查看详细信息
                </router-link>
            </el-button>
        </div>
        <!--div class="btn">
            <el-button class="b">加入购物车</el-button>
            <el-button class="b">
                <router-link :to="{ name: 'Good4User'}">
                点击查看详细信息
                </router-link>
            </el-button>
        </div-->
    </div>
  </template>
  
  <script>
  import "../../constant"
  export default {
      props: {
          goods: {
              type: Object,
              required: true
          }
      }
  }
  </script>

<style scoped>
.image{
    grid-column: 1/2;
}
.image img{
    width:100%;
    height: 100%;
}
.info{
    line-height: 50px;
}
.buy .num{
    margin-left: -100px;
}
.btn{
    display: flex;
    flex-direction: column;
    gap:20px;
}
.b{
    width:100%;
    margin-left: 0;
}
.rt{
    text-decoration: none;
}
</style>