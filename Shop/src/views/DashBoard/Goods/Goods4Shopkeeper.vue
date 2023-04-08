<script setup>
import GoodsRegister from '../../../components/Dashboard/Goods/GoodsRegister.vue'
import WrapperAllGoods from '../../../components/Dashboard/Wrapper/WrapperAllGoods.vue'
import WrapperGoodsRecord from '../../../components/Dashboard/Wrapper/WrapperGoodsRecord.vue'
</script>

<template>
    <el-tabs tab-position="left">
        <el-tab-pane label="商品注册">
            <GoodsRegister/>
        </el-tab-pane>
        <el-tab-pane label="申请记录">
            <WrapperGoodsRecord/>
        </el-tab-pane>
        <el-tab-pane label="所有商品">
            <WrapperAllGoods/>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
export default{
    components:{
        GoodsRegister
    }
}
</script>

<style scoped>
.show{
    margin: -10px 50px 0px 50px;
}
</style>
