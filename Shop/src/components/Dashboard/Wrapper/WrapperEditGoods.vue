<script setup>
    import GoodsShow from '../Goods/GoodsShow.vue'
</script>

<template>
    <div class="wrap" >
        <h1 v-if="this.editingGoodsList.length===0">您还没有注册任何商品</h1>
        <div class="goods">
            <div v-for="goods in editingGoodsList" :key="goods.goodsId">
                <GoodsShow :goods="goods"/>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            editingGoodsList: [],
            goods:{
                goodsname: "goodsname",
                goodsId: "id",
                registerStatus: 0,
                modifyStatus: 0,
                goodsPrice:100,
                goodsStock:20,
                introduction:"intro",
                goodsCategory: ['电脑数码', '家用电器']
            }
        }
    },
    created (){
        this.getEditingGoods() 
    },
    methods: {
        getEditingGoods() {
            this.editingGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getRegisteringGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.editingGoodsList = res.data.data;
            })
        }
    }
}
</script>