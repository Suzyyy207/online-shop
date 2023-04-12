<script setup>
import Nav from '../../components/Public/Nav/ShopkeeperNav.vue'
import ShopDetail from '../../components/ShopGoodsPage/ShopDetail.vue'
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <body>
        <Nav></Nav>
        <section class="back">
            <ShopDetail/>
        </section>
    </body>
</template>

<style scoped>

.back{
    margin-top: 20px;
    padding-bottom: 30px;
    display: flex;
}
</style>