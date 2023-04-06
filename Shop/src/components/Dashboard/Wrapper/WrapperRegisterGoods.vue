<!--已申请未批复+已申请被拒绝-->
<script setup>
    import GoodsShow from '../Goods/GoodsShow.vue'
</script>

<template>
    <div class="wrap" >
        <el-tabs tab-position="left">
            <el-tab-pane label="待审核">
                <div class="goods">
                    <div v-for="goods in registeringGoodsList" :key="goods.goodsId">
                        <GoodsShow :goods="goods"/>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="申请失败">
                <div class="goods">
                    <div v-for="goods in registerationDeniedGoodsList" :key="goods.goodsId">
                        <GoodsShow :goods="goods"/>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="申请成功">
                <div class="goods">
                    <div v-for="goods in registeredGoodsList" :key="goods.goodsId">
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
            registeringGoodsList: [],
            registerationDeniedGoodsList: [],
            registeredGoodsList:[],
            goods:{
                goodsname: "goodsname",
                goodsId: "id",
                registerStatus: 0,
                modifyStatus: 0,
                status: 0,
                goodsPrice:100,
                goodsStock:20,
                introduction:"intro",
                goodsCategory: ['电脑数码', '家用电器']
            }
        }
    },
    created (){
        this.getRegisteringGoods();
        this.getRegisteredGoods();
        this.getRregisterationDeniedGoods();
    },
    methods: {
        getRegisteringGoods() {
            this.registeringGoodsList = [this.goods];
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
        getRregisterationDeniedGoods() {
            this.registerationDeniedGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getRegisterationDeniedGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.registerationDeniedGoodsList = res.data.data;
            })
        }
    }
}
</script>