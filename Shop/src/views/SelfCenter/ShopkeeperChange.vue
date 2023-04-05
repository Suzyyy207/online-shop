<!--商家修改信息主页面-->
<script setup>
import Nav from '../../components/Public/Nav/ShopkeeperNav.vue'
import UserInfo  from '../../components/MainWeb/Components/UserInfo.vue';
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
        
    <Nav></Nav>

        
    <section>
        <UserInfo></UserInfo>
    </section>


</template>