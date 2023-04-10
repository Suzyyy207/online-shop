<script setup>
import Nav from '../../components/Public/Nav/UserNav.vue'
import ShoppingList from '../../components/ShoppingList/ShoppingList.vue'
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <Nav></Nav>
    <section class="back">
        <ShoppingList />
    </section>
</template>

<style scoped>
.back{
    margin-top: 20px;
    display: flex;
}
</style>