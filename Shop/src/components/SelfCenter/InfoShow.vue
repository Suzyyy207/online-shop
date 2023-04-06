<template>
    <section>
      <h1 class="title">Self Center</h1>
      <div class="main">

        <div class="profile">
            <div class="infoShow">
                <img src="../../assets/shop.png" alt="头像">
                <div class="info">
                    <p>姓名: xxxx</p>
                    <p>用户类型: xxxxx</p>
                    <p>电话: xxx</p>
                    
                </div>
            </div>
            <p>身份证号: 610102200202193129</p>
            <p>邮箱: suzyyy207@gmail.com</p>
            <!--这里需要根据用户类型决定展示信息-->
            <p>个人账户余额: <num class="account">0</num></p>
            <p>商户账户余额: <num class="account">0</num></p>
            <div class="charge">
                <el-input class="chargeInput"></el-input>
                <el-button>充值</el-button>
            </div>
            <el-button class="">修改个人信息</el-button>
        </div>

        <!--为后续扩展已购买记录预留-->
        <div class="goodsInfo">
          <div class="goodsList">
            <h3 class="goodsTitle">已购买</h3>
            <!-- 购物记录将在此处通过 JavaScript 动态加载 -->
          </div>
        </div>

      </div>
    </section> 
</template>

<style scoped>
section{
    margin: 0;
}
/*标题*/
.title{
    font-family: 'Courier New', Courier, monospace;
    margin: 30px 0 30px 50px;
    color:#81A18B;
    text-shadow: 2px 2px 2px #195844;
    font-size: 45px;
    text-align: center;
    font-weight: bolder;
}
.main{
    display: grid;
    grid-template-columns: 35% 55%;
    grid-template-rows: 100%;
    grid-column-gap: 100px;
    margin-top: 20px;
    margin-left:20px
}

/*个人信息展示与导航*/
.profile {
    display: flex;
    flex-direction: column;
    grid-column: 1/2;
    background-color: #fff;
    border-radius: 15px;
    color: #303133;
    border: 2px solid #ebeef5;
    box-shadow: 2px 2px 12px 2px (0 0 0 / 10%);
    padding: 20px;
    margin-left: 30px;
    margin-bottom: 20px;
    width: 100%;
    height: auto;
    gap:20px
}

.infoShow{
    display: flex;
}
.profile img {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin-right: 20px;
}
.profile .info {
    font-size: 16px;
}
.profile .info p {
    margin: 10px 0;
}

.profile > p{
    letter-spacing:0;
    line-height: 10px;
    margin-left: 10px;
}
.profile .chargeInput{
    width:60%;
    margin-right:20px
}

/*相关记录*/
.goodsInfo{
    grid-column: 2/3;
    border-radius: 5px;
    padding: 10px;
    display: flex;
    flex-direction: column;
}
  
.goodsList {
    background-color:#81A18B;
    display: flex;
    flex-direction: column;
    padding: 15px;
    max-height: 300px;
    margin-bottom: 20px;
}
.goodsTitle {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 10px;
    color:white;
}
  

  
</style>