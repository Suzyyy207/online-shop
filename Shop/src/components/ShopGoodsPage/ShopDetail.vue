<!-- 用户可见的店铺详情 -->
<script setup>
import GoodsShow from './GoodsShow.vue'
</script>
<template>
    <div class="wrap">
        <div class="sideInfo">
            <div class="image">
                <img v-if="!shop.avatar" class="logo" src="@/assets/shop.png" alt="点击进入店铺详情页面"/>
                <img v-else :src="'data:image/jpeg;base64,'+shop.avatar">
            </div>
            <div class="info">
                <p> 店铺名: {{ this.shop.shopname }} </p>
                <p> 类别：</p>
                <p> 店铺简介: {{ this.shop.shopname }} </p> 
                <!--评价预留-->
                
            </div>
        </div>
        <!-- 商品展示 -->
        <div class="goods">
            <GoodsShow class="good"/>
            <GoodsShow class="good"/>
        </div>
        <!--div class="goods" v-for="goods in shop.goodslist" :key="goods.goodsname">
            <GoodsShow4User :goods="goods" class="good"/>
        </div-->
        
    </div>
    
</template>
  
<script>
import GoodsShow4User from "./GoodsDetail.vue"
export default {
    data(){
        return {
            shop: {
                shopname: "",
                avatar: "",
                goodslist: []
            }
        }
    },
    components: {
        GoodsShow4User
    },
    created() {
        this.getShopInfo();
    },
    methods: {
        getValidGoods() {
            this.$axios.post("/getValidGoodsByShopname", {
                shopname: this.shop.shopname
            }).then(res => {
                this.shop.goodslist = res.data.data;
            })
        },
        getShopInfo() {
            // this.goods.goodsAvatar = []
            // this.shop.goodslist = [this.goods]
            // console.log(this.shop.goodslist)
            this.$axios.post("/getShopInfoByShopname", {
                shopname: this.$route.params.shopname
            })
            .then((res) => {
                if(res.data.state == window.SUCCESS){
                    const shop = res.data.data;
                    this.shop.shopname = shop.shopname;
                    this.shop.avatar = shop.avatar;
                    this.getValidGoods();
                }
                else {
                    this.$message.error("加载失败，请重试");
                }
            })
            .catch((err) => {
                console.log(err);
                this.$message.error("加载失败，请重试");
            });
        }
    }
}
</script>
 
<style scoped>
.wrap{
    display: grid;
    grid-template-columns: 20% 80%;
    margin:0 auto;
    padding: 20px;
    width:95%;
    background-color: rgba(128,128,128,0.1);
    border-radius: 15px;
}
.sideInfo{
    display: flex;
    flex-direction: column;
    margin:10px;
    padding: 20px;
    gap:10px;
    background-color: rgba(256,256,256,0.7);
    height: fit-content;
}
.image{
    grid-column: 1/2;
}
.image img{
    width: 100%;
    height: 100%;
}
.info p{
    line-height: 40px;
    font-family: "Brush Script MT", cursive;
}
.goods{
    grid-column: 2/3;
    margin: 10px 20px 10px 20px;
    padding: 10px 20px 10px 10px;
    background-color: transparent;
    display: flex;
    flex-direction: column;
    width:100%;
    height:auto;
    gap:20px;
}
.good{
    width:100%;
    height: auto;
    background-color: rgba(256,256,256);
    border-radius: 10px;
    display: grid;
    grid-template-columns: 20% 30% 30% 10%;
    padding:20px;
    align-items: center;
    justify-items: center;
}
</style>