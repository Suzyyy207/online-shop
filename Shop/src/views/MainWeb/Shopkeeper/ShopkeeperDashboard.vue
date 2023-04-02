<!--商家后台界面-->
<script setup>
import ShopRegister from '../../../components/MainWeb/Components/ShopRegister.vue'
import MyShopInfo from '../../../components/MainWeb/Components/MyShopInfo.vue'
import Nav from '../../../components/Public/Nav/ShopkeeperNav.vue'
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>

    <Nav></Nav>
    <section class="register_show">
        <!--如果尚未注册过，则进入注册页面；否则进入商店信息展示页面-->
        <MyShopInfo v-if="isregistered && (!isToModify)" :shop="shop"/>
        <ShopRegister v-else :shop="shop"/>
    </section>

</template>


<script>
import "../../../constant.js";
import { interceptor } from "../../../interceptor";
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
/*商家注册信息栏*/
.register_show{
    margin:40px 100px 0px 100px;
}
</style>