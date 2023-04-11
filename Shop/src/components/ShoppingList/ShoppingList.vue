<!--此组件打包所有购物车内信息，向用户展示-->
<script setup>
import GoodsInSL from '../ShoppingList/GoodsInSL.vue'
import InvalidGoodsInSL from '../ShoppingList/InvalidGoodsInSL.vue'
</script>

<template>
    <div class="wrap">
        <div class="title">
            <p>购物车(全部 {{ totalCount }})</p>
            <div class="settlement">
                <p>已选择{{ selectedCount }}件商品</p>
                <p>共计：{{ totalPrice }}元</p>
                <el-button class="tBtn">结算</el-button>
            </div>
        </div>
        <div class="bar"></div>

        <div class="cart">
            <div class="cartTable">
                <el-checkbox v-model="allChecked" @change="changeAllStatus">
                    <span>全选</span>
                </el-checkbox>
                <span>商品信息</span>
                <span>单价</span>
                <span>数量</span>
                <span>金额</span>
                <span>操作</span>
            </div>
            
            <div class="allGoods">
                <div class="singleShop" v-for="shop in validCart" :key="shop.shopname">
                    <el-checkbox v-model="shop.isChecked" @change="changeShopStatus(shop)">
                        {{ shop.shopname }}
                    </el-checkbox>
                        
                    <div class="goods">
                        <div class="goodShow" v-for="goods in shop.goodsList" :key="goods.goodsId">
                            <el-checkbox class="check" v-model="goods.isChecked" />
                            <GoodsInSL :goods="goods" @reloadPage="handleReloadPage"/>
                        </div>
                    </div>

                </div> 
            </div>

            <!--失效商品-->
            <div class="allGoods">
                <div class="singleShop" v-for="shop in invalidCart" :key="shop.shopname">
                    <div class="shopInfo">
                        <span>店铺：{{ shop.shopname }}</span>
                    </div>
                    <div class="invalidGoods">
                        <div class="invalidShow" v-for="goods in shop.goodsList" :key="goods.goodsId">
                            <InvalidGoodsInSL class="invalidGood" :goods="goods" @reloadPage="handleReloadPage"/>
                        </div>
                    </div>                       
                </div> 
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            validCart: [],
            invalidCart: [],
            allChecked: false,
            totalCount: 0,
            goods:{
                goodsname: "goodsname",
                goodsId: "id",
                status: 0,
                goodsPrice:100,
                goodsStock:20,
                introduction:"intro",
                goodsCategory: ['电脑数码', '家用电器'],
                favorites:0,
                goodsAvatar: [],
                num: 4
            },
            goods2:{
                goodsname: "goodsname",
                goodsId: "id",
                status: 0,
                goodsPrice:100,
                goodsStock:20,
                introduction:"intro",
                goodsCategory: ['电脑数码', '家用电器'],
                favorites:0,
                goodsAvatar: [],
                num: 1
            },
            shop:{
                shopname: "shopname1",
                goodsList: []
            }
        }
    },
    created (){
        this.getValidCart();
        this.getInvalidCart();
    },
    computed: {
        totalPrice() {
            let total = 0;
            for (let shop of this.validCart) {
                for (let goods of shop.goodsList) {
                    if (goods.isChecked) {
                        total += Number(goods.goodsPrice) * Number(goods.num);
                    }
                }
            }
            return total
        },
        selectedCount() {
            let total = 0;
            for (let shop of this.validCart) {
                for (let goods of shop.goodsList) {
                    if (goods.isChecked) {
                        total += Number(goods.num);
                    }
                }
            }
            return total
        }
    },
    methods: {
        getValidCart() {
            this.shop.goodsList = [this.goods, this.goods2]
            this.validCart = [this.shop];
            var localStorage = window.localStorage;
            this.$axios.post("/getValidCart", {
                username: localStorage.getItem("username")
            }).then(res => {
                this.validCart = res.data.data;
            })

            this.validCart = this.validCart.map(shop => {
                shop.isChecked = false;
                shop.goodsList = shop.goodsList.map(goods => {
                    goods.isChecked = false;
                    this.totalCount += 1;
                    return goods;
                });
                return shop;
            });
        },
        getInvalidCart() {
            this.shop.goodsList = [this.goods, this.goods2]
            this.invalidCart = [this.shop];
            var localStorage = window.localStorage;
            this.$axios.post("/getInvalidCart", {
                username: localStorage.getItem("username")
            }).then(res => {
                this.invalidCart = res.data.data;
            })
        },
        changeShopStatus(shop) {
            shop.goodsList.forEach((goods) => {
                goods.isChecked = shop.isChecked;
            });
        },
        handleReloadPage() {
            this.$router.go(0)
        },
        changeAllStatus() {
            this.validCart.forEach((shop) => {
                shop.isChecked = this.allChecked;
                this.changeShopStatus(shop);
            });
        }
    }
}
</script>

<style scoped>
.wrap{
    width: 85%;
    height: 100%;
    margin:20px auto;
    padding: 10px 0 30px;
    background-color: #fff;
    border-radius: 15px;
    color: #303133;
    border: 2px solid #ebeef5;
    box-shadow: 2px 2px 12px 2px rgb(0 0 0 / 10%);
}

/*购物车表头 */
.title{
    display: grid;
    grid-template-columns: 60% 40%;
    padding: 0px 20px;
    margin: 20px 10px;
}
.title > p{
    grid-column: 1/2;
    text-align: left;
    font-size: 30px;
    font-weight: bolder;
    color:#81A18B;
    font-family: "Brush Script MT", cursive;
    padding-left: 20px;
}
.title .settlement{
    grid-column: 2/3;
    display: flex;
    align-items: baseline;
    gap:20px
}
.title .settlement p {
    font-size: 20px;
    font-weight: bolder;
    color:#81A18B;
    font-family: "Brush Script MT", cursive;
}
.title .tBtn{
    width:100px;
    background-color:#81A18B;
    border-color:#81A18B;
    justify-items: center;
    color:white;
} 
/*分割线*/
.bar{
    padding: 0;
    width: 100%;
    height:1px;
    border-top:2px solid #ccc;
}

/*购物车表单*/
    /*表单开头*/
.cartTable{
    padding:10px 20px 0;
    height: 50px;
    line-height: 50px;
    font-size:15px;
    color: #3c3c3c;
    display: grid;
    grid-template-columns: 10% 40% 10% 15% 10% 15%;
    margin: 20px auto;
    text-align: center;
}
.cartTable  span{
    margin-top: -7px;
    color:#3c3c3c;
    justify-content: top;
    font-size: 20px;
}

/*按商家展示排版*/

.singleShop{
    padding:0px 20px;
    margin:0px auto 40px;
}
.singleShop .shopInfo{
    margin:30px 10px;
}
.singleShop .goods{
    margin-top:-20px;
    margin-left: 30px;
    padding:10px 0px 0 30px;
    width:95%;
    height:auto;
    align-items: center;
    background-color: rgba(128,128,128,0.1);
    border-radius: 15px;
}
.singleShop .invalidGoods{
    margin-top:-20px;
    margin-left: 30px;
    padding:10px 0px 0 30px;
    width:95%;
    height:auto;
    align-items: center;
    background-color: rgba(128,128,128,0.4);
    border-radius: 15px;
}

.singleShop .goods .goodShow{
    background-color: transparent;
    border:none;
    box-shadow: none;
    width: 100%;
    height:auto;
    display: grid;
    grid-template-columns: 10% 90%;
}
.singleShop .invalidGoods .invalidShow{
    background-color: transparent;
    border:none;
    box-shadow: none;
    width: 100%;
    height:auto;
    display: grid;
    grid-template-columns: 10% 90%;
}
.check{
    margin-top:30px;
}
.singleShop .goods .good{
    background-color: transparent;
    border:none;
    box-shadow: none;
    width: 100%;
    height:auto;
    padding-top:10px;
}
.singleShop .invalidGoods .invalidGood{
    background-color: transparent;
    border:none;
    box-shadow: none;
    width: 100%;
    height:auto;
    padding-top:10px;
}
</style>