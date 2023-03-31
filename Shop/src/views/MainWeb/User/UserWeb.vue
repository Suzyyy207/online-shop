<!--普通用户首页-->
<script setup>
import WrapperUser from '../../../components/MainWeb/Wrapper/WrapperUser.vue'
import SearchTop from '../../../components/MainWeb/Components/SearchU.vue'
import { RouterLink, RouterView } from 'vue-router'
import { ref } from 'vue'
import { Search } from '@element-plus/icons-vue'
const input = ref('')
const select = ref('')
</script>

<template>
    <header>
            <p>Welcome!</p>
            <nav>
                <RouterLink to="/UserWeb">首页</RouterLink>
                <RouterLink to="#">购物车</RouterLink>
                <RouterLink to="#">收藏夹</RouterLink>
                <RouterLink to="/">退出登录</RouterLink>
            </nav>
    </header>

    <section>
        <SearchTop />
    </section>
        
    <section class="show">
        <h1 class="title">Let's have fun!</h1>
        <br>
        <WrapperUser/>
    </section>
</template>

<script>
import { interceptor, userInterceptor } from "../../../interceptor";
export default {
    created(){
        interceptor(this);
        userInterceptor(this);
    }
}
</script>


<style scoped>

/*页眉格式*/ 
header {
    background-color:#81A18B;
    width: 100%;
    padding:10px;
    text-align: center;
    display: grid;
    grid-template-columns: 50%, 50%;
    grid-row: 100%;
}
header p{
    grid-column: 1/2;
    text-align: left; 
    margin-left: 20px;
    padding: 0 1rem;
    font-size: 20px;
    color:white;
    transition: 0.4s;

}
header nav{
    grid-column: 2/3;
    text-align: right; 
}
header nav a {
    display: inline-block;
    padding: 0 1rem;
    font-size: 20px;
    border-left: 1px solid var(--color-border);
    color:white;
    text-decoration: underline;
    transition: 0.4s;
    font-family: "Brush Script MT", cursive;
}

/*搜索栏及装饰*/

/*展示栏*/
.show{
    margin: -10px 50px 0px 50px;
}
.show .title{
    margin-top:10px;
    margin-bottom: 10px;
    text-align: center;
    color:#81A18B;
    text-shadow: 2px 2px 2px #195844;
    font-size: 45px;
    font-family: "Lucida Console", "Courier New", monospace;
}
</style>