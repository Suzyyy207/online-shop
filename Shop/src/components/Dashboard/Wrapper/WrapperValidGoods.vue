<script setup>
    import GoodsShow from '../Goods/GoodsShow.vue'
</script>

<template>
    <div class="wrap" >
        <h1 v-if="this.validGoodsList.length===0">您还没有注册任何商品</h1>
        <div class="goods">
            <div v-for="goods in validGoodsList" :key="goods.goodsId">
                <GoodsShow :goods="goods"/>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            validGoodsList: [],
            goods:{
                goodsname: "goodsname",
                goodsId: "id",
                status: 1,
                goodsPrice:100,
                goodsStock:20,
                introduction:"intro",
                goodsCategory: ['电脑数码', '家用电器']
            }
        }
    },
    created (){
        this.getValidGoods() 
    },
    methods: {
        getValidGoods() {
            this.validGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getValidGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.validGoodsList = res.data.data;
            })
        }
    }
}
</script>