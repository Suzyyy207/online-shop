<!--商家个人中心主页面-->
<script setup>
import InfoShow from '../../components/SelfCenter/InfoShow.vue'
import Nav from '../../components/Public/Nav/ShopkeeperNav.vue'
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
        
    <Nav></Nav>

        
    <section>
        <InfoShow></InfoShow>
    </section>


</template>