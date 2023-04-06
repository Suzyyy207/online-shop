<!--此组件为将所有向管理员发送注册请求的店铺打包，并排版为管理员友好的UI界面-->
<script setup>
import RegisterShow from '../Components/RegisterShow.vue'
import ShopShow from '../Components/ShopShow.vue'
</script>

<template>
    <div class="wrap" >
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
    </div>
</template>

<script>
export default {
    props: ['username'],
    data() {
        return {
            registeredShopList: [],
            registeringShopList: []
        }
    },
    created (){
        this.getRegisteredShop()
        this.getRegisteringShop() 
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