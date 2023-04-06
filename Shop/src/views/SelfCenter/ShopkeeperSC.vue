<!--商家个人中心主页面-->
<script setup>
import InfoShow from '../../components/SelfCenter/InfoShow.vue'
import Nav from '../../components/Public/Nav/ShopkeeperNav.vue'
import { RouterLink, RouterView } from 'vue-router'
import SetUserInfo from '../../components/Dashboard/User/SetUserInfo.vue'
</script>

<template>
        
    <Nav></Nav>
    <section>
        <div v-if="state==0">
            <InfoShow></InfoShow>
            <el-button @click="this.state=1">修改个人信息</el-button>
        </div>
        <div v-if="state==1">
            <SetUserInfo/>
        </div>
        
    </section>


</template>

<script>
export default{
    data() {
        return{
            state:0
        }
    }
}
</script>