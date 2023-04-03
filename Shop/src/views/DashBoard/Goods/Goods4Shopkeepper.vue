<script setup>
import Nav from '../../../components/Public/Nav/ShopkeeperNav.vue'
import WrapperGoods from '../../../components/Dashboard/Wrapper/WrapperGoods.vue'
import SearchTop from '../../../components/Dashboard/Goods/SearchG.vue'
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <Nav></Nav>

    <section>
        <SearchTop/>
    </section>
        
    <section class="show">
        <WrapperGoods/>
    </section>

</template>

<style scoped>
.show{
    margin: -10px 50px 0px 50px;
}
</style>
