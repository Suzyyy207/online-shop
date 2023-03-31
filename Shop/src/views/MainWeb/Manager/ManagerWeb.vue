<!--管理员主页面，会显示所有店铺注册请求-->
<script setup>
import WrapperManager from '../../../components/MainWeb/Wrapper/WrapperManager.vue'
import SearchM from '../../../components/MainWeb/Components/SearchM.vue'
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <header>
        <p>Welcome!</p>
        <nav>
            <!--RouterLink to="#">首页</RouterLink-->
            <RouterLink to="#">购物车</RouterLink>
            <RouterLink to="#">收藏夹</RouterLink>
            <RouterLink to="/ManagerWeb">店铺管理</RouterLink>
            <RouterLink to="/">退出登录</RouterLink>
        </nav>
    </header>

    <section>
        <SearchM/>
    </section>
        
    <section class="show">
        <WrapperManager />
    </section>


</template>

<script>
import { interceptor } from "../../../interceptor";
export default {
    created(){
        interceptor(this);
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
    margin: 20px 50px 0px 50px;
}
</style>