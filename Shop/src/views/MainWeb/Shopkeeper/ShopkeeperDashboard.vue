<!--商家后台界面-->
<script setup>
import ShopRegister from '../../../components/MainWeb/Components/ShopRegister.vue'
import MyShopInfo from '../../../components/MainWeb/Components/MyShopInfo.vue'
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <header>
        <p>Welcome!</p>
        <nav>
            <RouterLink to="/ShopkeeperWeb">首页</RouterLink>
            <RouterLink to="#">购物车</RouterLink>
            <RouterLink to="#">收藏夹</RouterLink>
            <RouterLink to="/ShopDashboard">我的店铺</RouterLink>
            <RouterLink to="/">退出登录</RouterLink>
        </nav>
    </header>
        
    <section class="register_show">
        <!--如果尚未注册过，则进入注册页面；否则进入商店信息展示页面-->
        <MyShopInfo v-if="isregistered && (!isToModify)" :shop="shop"/>
        <ShopRegister v-else :shop="shop"/>
    </section>

</template>


<script>
import "../../../constant.js";
import { interceptor, shopkeeperInterceptor } from "../../../interceptor";
export default {
    props: ['shop_id'],
    data() {
        return {
            isregistered: "",
            isToModify: "",
            shop: {
                goodstype: [],
                shopname: "",
                introduction: "",
                address: "",
                idnum: "",
                capital: "",
                date: "",
                shopid: "",
                is_admitted: ""
            }
        }
    },
    created (){
        interceptor(this);
        shopkeeperInterceptor(this);
        this.getMyShopInfo();
        this.toModify();
    },
    methods: {
        // 根据当前username取店铺信息
        getMyShopInfo() {
            var localStorage = window.localStorage;

            this.$axios.post('/getShopInfoByUsername', {
                username: localStorage.getItem("username")
            })
            .then(res => {
                // 如果用户已经注册过，则在localStorage中储存此商户的shopname，便于后续在数据库中的操作
                if(res.data.state == window.SUCCESS) {
                    this.isregistered = 1;
                    this.shop = res.data.data;
                    var localStorage = window.localStorage;
                    localStorage.setItem("shopname", shop.shopname);
                }
                else {
                    this.isregistered = 0;
                }
            })
        },
        // 如果用户需要修改注册信息重新提交注册，也需要导入ShopRegister页面   
        toModify() {
            var localStorage = window.localStorage;
            if(localStorage.getItem('address')) {
                this.isToModify = 1;
            } else {
                this.isToModify = 0;
            }
        }
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


/*商家注册信息栏*/
.register_show{
    margin:40px 100px 0px 100px;
}
</style>