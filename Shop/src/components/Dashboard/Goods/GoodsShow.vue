<!--商家上架申请记录中的商品展示组件-->
<script setup>
import { reactive, ref } from 'vue'
import GoodsInfo from "./GoodsInfo.vue"
const dialogTableVisible = ref(false)
</script>


<template>
  <div class="goods">
    <a href="#" class="goodsLink">

      <img v-if="!goods.avatar" class="logo" src="@/assets/shop.png" alt="点击进入店铺详情页面"/>
      <img v-else :src="'data:image/jpeg;base64,'+goods.avatar[0]">

      <p class="goodsName">商品名：{{ goods.goodsname }}</p>
      <el-button class="btnReg"  @click="dialogTableVisible = true">
        <p>点击查看详细信息</p>
      </el-button>

      <el-dialog  class="information" v-model="dialogTableVisible" title="商品信息">
        <GoodsInfo :goods="this.goods"/>
        <!--img v-if="!goods.avatar" class="logo" src="@/assets/shop.png" alt="点击进入店铺详情页面"/>
        <img v-else :src="'data:image/jpeg;base64,'+goods.avatar">
        <p>商品名：{{ goods.goodsname }}</p>
        <p>商品类型：{{ goods.goodsCategory }}</p>
        <p>商品价格：{{ goods.goodsPrice }}</p>
        <p>商品库存：{{ goods.goodsStocks }}</p>
        <p>商品简介：{{ goods.introduction }}</p>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="primary">点击修改注册信息</el-button>
          </span>
        </template-->
      </el-dialog>
          
    </a>
  </div>
</template>

<script>
export default {
  props: {
    goods: {
      type: Object,
      required: true
    }
  }
}
</script>

<style>
.goodsLink{
    width: 320px;
    height: 250px;
    padding: 20px 30px 10px 30px;
    margin: 10px 20px 20px 20px;
    line-height: 100px;
    align-items: center;

    display: grid;
    grid-template-columns: 60% 40%;
    grid-template-rows: 20% 50% 30%;
    grid-column-gap:20px;

    background-color: #fff;
    border-radius: 15px;
    color: #303133;
    border: 2px solid #ebeef5;
    transition: .3s;
}
.goodsLink .logo{
    grid-row: 1/3;
    grid-column: 1/2;
    width: 80%;
    height: 80%;
}
.goodsLink p{
  align-items: left;
  padding-top: 5px;
  margin-left: -20px;
  line-height: 30px;
}


.goodsLink .btnReg{
    grid-row: 3/4;
    grid-column: 1/3;
}



.goodsLink .information p{
  font-family:"Lucida Console", "Courier New", monospace;
  font-size:20px;
  margin:0px 0px 0px 50px;
  line-height: 40px;
}
.goodsLink .information .bottom{
  font-family:"Lucida Console", "Courier New", monospace;
  font-size:20px;
  margin:0px 0px -70px 50px;
}

.dialog-footer{
  margin-right: 10px;
}


</style>