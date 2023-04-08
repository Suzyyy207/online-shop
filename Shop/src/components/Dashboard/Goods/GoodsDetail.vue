<!--公共组件，展示商品详细信息-->
<template>
   <div v-for="avatar in goods.goodsAvatar">
        <img :src="'data:image/jpeg;base64,' + avatar">
    </div>
                
    <p>商品类型：{{ goods.goodsname }}</p>
    <p>商品类别：{{ goods.goodsCategory }}</p>
    <p>商品简介：{{ goods.introduction }}</p>
    <p>商品库存：{{ goods.goodsStocks }}</p>
    <p>商品价格：{{ goods.goodsPrice }}</p>
    
</template>
 
<script>
import "../../../constant";
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
 .el-steps{
  margin: 15px 0;
}
.el-step__title{
  font-size: 13px;
}

</style>