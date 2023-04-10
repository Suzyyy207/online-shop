<script setup>
import Nav from '../../components/Public/Nav/ShopkeeperNav.vue'
import ShoppingList from '../../components/ShoppingList/ShoppingList.vue'
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <body>
        <Nav></Nav>
        <section class="back">
            <ShoppingList />
        </section>
    </body>
</template>

<style scoped>
body{
    background-color: rgba(128,128,128,0.1);
}
.back{
    margin-top: 20px;
    padding-bottom: 30px;
    display: flex;
}
</style>