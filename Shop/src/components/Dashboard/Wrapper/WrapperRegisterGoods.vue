<script setup>
    import GoodsShow from '../Goods/GoodsShow.vue'
</script>

<template>
    <div class="wrap" >
        <h2>待审核</h2>
            <div class="goods">
                <div v-for="goods in registeringGoodsList" :key="goods.goodsId">
                    <GoodsShow :goods="goods"/>
                </div>
            </div>
        <h2>注册失败</h2>
            <div class="goods">
                <div v-for="goods in registrationDeniedGoodsList" :key="goods.goodsId">
                    <GoodsShow :goods="goods"/>
                </div>
            </div>
        <h2>注册成功</h2>
            <div class="goods">
                <div v-for="goods in registeredGoodsList" :key="goods.goodsId">
                    <GoodsShow :goods="goods"/>
                </div>
            </div>
    </div>
</template>
<script>
export default {
    components: {
        GoodsShow
    },
    data() {
        return {
            registeringGoodsList: [],
            registrationDeniedGoodsList: [],
            registeredGoodsList:[],
            goods:{
                goodsname: "goodsname",
                goodsId: "id",
                registerStatus: 0,
                modifyStatus: 2,
                status: 2,
                goodsPrice:100,
                goodsStock:20,
                introduction:"intro",
                goodsCategory: ['电脑数码', '家用电器'],
                favorites:0,
                goodsAvatar: []
            }
        }
    },
    created (){
        this.getRegisteringGoods();
        this.getRegisteredGoods();
        this.getRegistrationDeniedGoods();
    },
    methods: {
        getRegisteringGoods() {
            console.log("getinfo")
            this.registeringGoodsList = [this.goods];
            console.log(this.registeringGoodsList);
            var localStorage = window.localStorage;
            this.$axios.post("/getRegisteringGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.registeringGoodsList = res.data.data;
            })
        },
        getRegisteredGoods() {
            this.registeredGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getRegisteredGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.registeredGoodsList = res.data.data;
            })
        },
        getRegistrationDeniedGoods() {
            this.registrationDeniedGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getRegistrationDeniedGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.registerationDeniedGoodsList = res.data.data;
            })
        }
    }
}
</script>