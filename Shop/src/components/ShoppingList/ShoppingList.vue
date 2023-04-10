<!--此组件打包所有购物车内信息，向用户展示-->
<template>
    <div class="wrap">
        <div class="title">
            <p>购物车(全部 72)</p>
            <div class="settlement">
                <p>已选择商品共计：3333元</p>
                <el-button class="tBtn">结算</el-button>
            </div>
        </div>
        <div class="bar"></div>
        <div class="cart">
            <div class="cartTable">
                <el-checkbox><span>全选</span></el-checkbox>
                <span>商品信息</span>
                <span>单价</span>
                <span>数量</span>
                <span>金额</span>
                <span>操作</span>
            </div>
            <div class="allGoods">
                <div class="singleShop">
                    <div class="shopInfo">
                        <el-checkbox>店铺：店铺名</el-checkbox>
                    </div>
                    <div class="goods">

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.wrap{
    width: 80%;
    height: 100%;
    margin:20px auto;
    padding: 10px 0 30px;
    line-height: 100px;
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
    margin: 10px 10px 0 10px;
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
    margin-bottom: 0;
}
.cartTable > span{
    margin-top: -7px;
    font-size:14px;
    color:#3c3c3c;
    justify-content: top;
}

/*按商家展示排版*/
.singleShop{
    padding:0px 20px;
    margin:0 auto;
}
.singleShop .goods{
    margin-top:-20px;
    padding:0px 40px;
    width:100%;
    height:100px;
    align-items: center;
    background-color: rgba(128,128,128,0.3);
    border-radius: 15px;
}
</style>