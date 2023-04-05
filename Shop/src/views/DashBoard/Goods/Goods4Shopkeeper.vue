<script setup>
import Nav from '../../../components/Public/Nav/ShopkeeperNav.vue'
import WrapperGoods from '../../../components/Dashboard/Wrapper/WrapperGoods.vue'
import SearchTop from '../../../components/Dashboard/Goods/SearchG.vue'
import { RouterLink, RouterView } from 'vue-router'
import GoodsRegister from '../../../components/Dashboard/Goods/GoodsRegister.vue'
import WrapperRegisterGoods from '../../../components/Dashboard/Wrapper/WrapperRegisterGoods.vue'
import WrapperEditGoods from '../../../components/Dashboard/Wrapper/WrapperEditGoods.vue'
import WrapperAllGoods from '../../../components/Dashboard/Wrapper/WrapperAllGoods.vue'
</script>

<template>
    <!--Nav></Nav>

    <section>
        <SearchTop/>
    </section>
        
    <section class="show">
        <WrapperGoods/>
    </section-->
    <el-tabs tab-position="left">
        <el-tab-pane label="商品注册">
            <GoodsRegister/>
        </el-tab-pane>
        <el-tab-pane label="上架申请记录">
            <WrapperRegisterGoods/>
        </el-tab-pane>
        <el-tab-pane label="修改申请记录">
            <WrapperEditGoods/>
        </el-tab-pane>
        <el-tab-pane label="所有商品">
            <WrapperAllGoods/>
        </el-tab-pane>
    </el-tabs>
</template>

<style scoped>
.show{
    margin: -10px 50px 0px 50px;
}
</style>
