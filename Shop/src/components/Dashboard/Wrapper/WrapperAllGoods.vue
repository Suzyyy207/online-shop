<script setup>
    import GoodsShow from '../Goods/GoodsShow.vue'
</script>

<template>
    <div class="wrap" >
        <el-tabs tab-position="left">
            <el-tab-pane label="在售商品">
                <div class="goods">
                    <div v-for="goods in validGoodsList" :key="goods.goodsId">
                        <GoodsShow :goods="goods"/>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="已下架">
                <div class="goods">
                    <div v-for="goods in registerationDeniedGoodsList" :key="goods.goodsId">
                        <GoodsShow :goods="goods"/>
                    </div>
                </div>
            </el-tab-pane>
        </el-tabs>
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