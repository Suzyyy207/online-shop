<template>
    <div class="wrap">
        <div class="info">
            <img src="../../assets/logo.png"/>
            <div class="intro">
                <span>商品名称</span>
                <span>商品简介</span>
            </div>
            <div class="category">
                <p>分类1</p>
                <p>分类2</p>
            </div>
        </div>
        <div class="price">
            <p>￥123.00</p>
        </div>
        <div class="amount">
            <el-input-number size="small" :min="1" />
        </div>
        <div class="count">
            <p>￥246.00</p>
        </div>
        <div class="btn">
            <el-button>删除</el-button>
        </div>

    </div>
    
</template>

<style scoped>
.wrap{
    display:grid;
    grid-template-columns: 45% 13% 17% 11% 15%;
    background-color: transparent;
    line-height: 30px;
}
.info{
    grid-column: 1/2;
    display: grid;
    grid-template-columns: 30% 25% 25%;
    gap:20px;
}
.intro{
    display: flex;
    flex-direction: column;
}
.category{
    display: flex;
    flex-direction: column;
}
.btn{
    margin:0 auto;
}
img{
    margin-left:-40px;
    width: 100%;
    height: 100%;
    align-items: end;
    background-color: white;
}
</style>