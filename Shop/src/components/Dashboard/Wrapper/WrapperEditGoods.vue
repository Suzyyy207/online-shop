<script setup>
    import GoodsShow from '../Goods/GoodsShow.vue'
</script>

<template>
    <div class="wrap" >
        <h2>待审核</h2>
        <div class="goods">
            <div v-for="goods in editingGoodsList" :key="goods.goodsId">
                <GoodsShow :goods="goods"/>
            </div>
        </div>

        <h2>修改失败</h2>
        <div class="goods">
            <div v-for="goods in editDeniedGoodsList" :key="goods.goodsId">
                <GoodsShow :goods="goods"/>
            </div>
        </div>

        <h2>修改成功</h2>
        <div class="goods">
            <div v-for="goods in editedGoodsList" :key="goods.goodsId">
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
            editedGoodsList: [],
            editDeniedGoodsList: [],
            goods:{
                goodsname: "goodsname",
                goodsId: "id",
                registerStatus: 0,
                modifyStatus: 0,
                status: 0,
                goodsPrice:100,
                goodsStock:20,
                introduction:"intro",
                goodsCategory: ['电脑数码', '家用电器'],
                favorites:0
            }
        }
    },
    created (){
        this.getEditingGoods();
        this.getEditedGoods();
        this.getEditDeniedGoods();
    },
    methods: {
        getEditingGoods() {
            this.editingGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getEditingGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.editingGoodsList = res.data.data;
            })
        },
        getEditedGoods() {
            this.editedGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getEditedGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.registeredGoodsList = res.data.data;
            })
        },
        getEditDeniedGoods() {
            this.editDeniedGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getEditDeniedGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.registerationDeniedGoodsList = res.data.data;
            })
        }
    }
}
</script>