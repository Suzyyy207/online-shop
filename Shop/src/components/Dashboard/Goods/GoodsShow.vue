<!--此组件将商家注册信息打包，并用于给管理员展示注册信息-->
<script setup>
import { reactive, ref } from 'vue'
const dialogTableVisible = ref(false)
</script>


<template>
  <div class="goods" :shop="shop">
    <a href="#" class="goodsLink">
      <img class="logo" src="@/assets/shop.png" alt="点击进入店铺详情页面"/>
      <p class="goodsName">商品名</p>
      <p class="status">注册状态</p>
      <el-button class="btnReg"  @click="dialogTableVisible = true">
        <p>点击查看详细信息</p>
      </el-button>
      <el-dialog  class="information" v-model="dialogTableVisible" title="商品信息">
        <p>店名：</p>
        <p>店铺id：</p>
        <p class="bottom">简介：</p>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="primary">点击修改注册信息</el-button>
          </span>
        </template>
      </el-dialog>
          
          
    </a>
  </div>
</template>

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