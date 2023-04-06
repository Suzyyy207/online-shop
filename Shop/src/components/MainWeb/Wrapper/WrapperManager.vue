<!--此组件为将所有向管理员发送注册请求的店铺打包，并排版为管理员友好的UI界面-->
<script setup>
import RegisterShow from '../Components/RegisterShow.vue'
import ShopShow from '../Components/ShopShow.vue'
import GoodsShow from '../../Dashboard/Goods/GoodsShow.vue';
</script>

<template>
    <div class="wrap" >
        <el-tabs tab-position="left">
            <el-tab-pane label="店铺管理">
                <h1>待处理</h1>
                    <div class="shops">
                        <div class="shops_ing" v-for="shop in registeringShopList" :key="shop.id">
                            <RegisterShow :shop="shop"/>
                        </div>
                    </div>
                <div class="ge"></div>
                <h1>已处理</h1>
                <div class="shops">
                    <div class="shops_ed" v-for="shop in registeredShopList" :key="shop.id">
                        <ShopShow :shop="shop"/>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="商品管理">
                <h1>待处理</h1>
                    <div>
                        <div v-for="goods in approvingGoodsList" :key="goods.goodsId">
                            <GoodsShow :goods="goods"/>
                        </div>
                    </div>

            </el-tab-pane>
        </el-tabs>
        
    </div>
</template>

<script>
export default {
    props: ['username'],
    data() {
        return {
            registeredShopList: [],
            registeringShopList: [],
            approvingGoodsList: [],
            approvedGoodsList: [],
            goods:{
                goodsname: "goodsname",
                goodsId: "id",
                shopname: "shopname",
                status: 0,
                goodsPrice:100,
                goodsStock:20,
                introduction:"intro",
                goodsCategory: ['电脑数码', '家用电器']
            }
        }
    },
    created (){
        this.getRegisteredShop();
        this.getRegisteringShop() ;
        this.getApprovingGoods();
        this.getApprovedGoods();
    },
    methods: {
        getRegisteredShop() {
            this.$axios.get('/getRegisteredShop')
            .then(res => {
                this.registeredShopList = this.registeredShopList.concat(res.data.data);
            })
        },
        getRegisteringShop() {
            this.$axios.get('/getRegisteringShop')
            .then(res => {
                this.registeringShopList = this.registeringShopList.concat(res.data.data);
            })
        },
        getApprovedGoods() {
            this.approvedGoodsList = [this.goods];
            this.$axios.get('/getApprovedGoods')
            .then(res => {
                this.approvedGoodsList = this.approvedGoodsList.concat(res.data.data);
            })
        },
        getApprovingGoods() {
            this.approvingGoodsList = [this.goods];
            this.$axios.get('/getApprovingGoods')
            .then(res => {
                this.approvingGoodsList = this.approvingGoodsList.concat(res.data.data);
            })
        }
    }
}
</script>

<style scoped>
.wrap {
    width: 100%;
    height: 100%;
    padding: 25px 25px 25px 40px;

    line-height: 100px;
    align-items: center;
    justify-items: center;

    background-color: #fff;
    border-radius: 15px;
    color: #303133;
    border: 2px solid #ebeef5;
    box-shadow: 2px 2px 12px 2px rgb(0 0 0 / 10%);
    transition: .3s;
}

h1{
    text-align: center;
    font-weight: bolder;
    font-size: 30px;
}
.shops{
    display:flex;
    overflow: auto;
    flex-wrap: wrap;
}

.ge{
    width: 100%;
    height:1px;
    margin-top: 30px;
    margin-bottom: 10px;
    border-top:2px solid #ccc;
}
</style>