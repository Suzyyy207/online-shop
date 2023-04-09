<script setup>
    import GoodsShow from '../Goods/GoodsShow.vue'
</script>

<template>
    <div class="wrap" >
        <el-tabs tab-position="left">
            <el-tab-pane label="在售商品">
                <div class="show">
                    <h1>在售商品表</h1>
                    <div class="goods">
                        <div v-for="goods in validGoodsList" :key="goods.goodsId">
                            <GoodsShow :goods="goods"/>
                        </div>
                    </div>
                    <div class="pages">
                        <el-button class="pageBtn">上一页</el-button>
                        <span>1/10</span>
                        <el-button class="pageBtn">下一页</el-button>
                    </div>
                </div>
            </el-tab-pane>

            <el-tab-pane label="已下架">
                <div class="show">
                    <h1>已下架商品表</h1>
                    <div class="goods">
                        <div v-for="goods in invalidGoodsList" :key="goods.goodsId">
                            <GoodsShow :goods="goods"/>
                        </div>
                    </div>
                    <div class="pages">
                        <el-button class="pageBtn">上一页</el-button>
                        <span>1/10</span>
                        <el-button class="pageBtn">下一页</el-button>
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
            invalidGoodsList: [],
            goods:{
                goodsname: "goodsname",
                goodsId: "id",
                status: 0,
                goodsPrice:100,
                goodsStock:20,
                introduction:"intro",
                goodsCategory: ['电脑数码', '家用电器']
            }
        }
    },
    created (){
        this.getValidGoods();
        this.getInvalidGoods();
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
        },
        getInvalidGoods() {
            this.invalidGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getInvalidGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.validGoodsList = res.data.data;
            })
        }
    }
}
</script>

<style scoped>

.show{
    margin:10px 30px 10px 30px;
    padding: 30px;
    background-color: #fff;
    border-radius: 15px;
    color: #303133;
    border: 3px solid #ebeef5;
    transition: .3s;

}
.show h1{
  text-align: left;
  font-family:"Lucida Console", "Courier New", monospace;
  font-size: 30px;
  margin-bottom:40px;
  font-weight: bolder;
  color:#81A18B;
}

/*翻页按钮*/
.pages{
    display: flex;
    margin:50px auto 0px;
}
.pages span{
    align-content:end;
    color:#81A18B;
    font-weight: bolder;
}
.pageBtn{
    display: flex;
    margin:0 auto;
}
</style>