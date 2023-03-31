<!--此组件为商家看到的本店铺的注册信息展示-->

<template>
  <div class="wrap">
    <div :shop="shop" class="shop">
        <img class="logo" src="@/assets/shop.png"/>

        <div class="info1">
          <p>店名：{{shop.shopname}}</p>
          <p>类别：{{shop.goodstype}}</p>
          <p>简介：{{shop.introduction}}</p>
          <p>注册资金：{{shop.capital}}</p>
        </div>

        <div class="info2">
          <el-row>
            <el-col :span="12">
              <p>身份证号：{{shop.idnum}}</p>
            </el-col>
            <el-col :span="12">
              <p>备案地址：{{shop.address}}</p>
            </el-col>
          </el-row>
        </div>

        <div class="info3">
          <el-row>
            <el-col :span="12">
              <p>注册日期：{{shop.date}}</p>
            </el-col>
            <el-col :span="12">
              <p v-if="shop.is_admitted == 1">注册状态：已注册</p>
              <p v-if="shop.is_admitted == 0">注册状态：审核中</p>
              <p v-if="shop.is_admitted == 2">注册状态：已拒绝。请修改店铺信息后重新提交申请</p>
            </el-col>
          </el-row>
        </div>

        <div class="info4">
          <el-form-item class="re_btn">
            <el-button type="submit" @click="modifyShopInfo">修改注册信息</el-button>
          </el-form-item>
        </div>
    </div>
  </div>
</template>
  
  <script>
  export default {
    props: {
      shop: {
        type: Object,
        required: true
      }
    },
    methods: {
      modifyShopInfo() {
        var localStorage = window.localStorage;
        localStorage.setItem("shopname",this.shop.shopname);
        localStorage.setItem("goodstype",this.shop.goodstype);
        localStorage.setItem("introduction",this.shop.introduction);
        localStorage.setItem("address",this.shop.address);
        localStorage.setItem("idnum",this.shop.idnum);
        localStorage.setItem("capital",this.shop.capital);
        this.$router.push({name:'ShopDashboardBlank'});
      }
    }
}
</script>


<style scoped>
.wrap{
  padding: 20px 30px 10px 30px;
  margin: 10px 20px 20px 20px;
  line-height: 100px;
  align-items: center;
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  overflow: auto;
  background-color: #fff;
  border-radius: 15px;
  color: #303133;
  border: 3px solid #ebeef5;
  transition: .3s;
}

.shop{
  width: 100%;
  height: 100%;
  margin: 50px 40px 40px 60px;

  display: grid;
  grid-template-columns: repeat(50px, 6);
  grid-template-rows: repeat(10%, 10);
  grid-column-gap:0px;
  font-size: 20px;
/*
    background-color: #fff;
    border-radius: 15px;
    color: #303133;
    border: 2px solid #ebeef5;
    transition: .3s;
  */
}

.shop .logo{
    grid-row: 1/3;
    grid-column: 1/4;
    width: 70%;
    height: 90%;
    background-color: #303133;
}


.shop .info1{
  grid-row: 1/3;
  grid-column: 4/11;
  margin-top: -15px;
}
.shop .info1 p{
  margin-bottom: -10px;
}



.shop .info2{
  padding-left: 25px;
  grid-row: 4/5;
  grid-column: 1/11;
  line-height: 30px;
}


.shop .info3{
  padding-left: 25px;
  grid-row: 5/6;
  grid-column: 1/11;
  margin-bottom: 50px;
}

.shop .info4{
  grid-row: 6/7;
  grid-column: 1/11;
}

.re_btn{
  margin-bottom: 25px;
  margin-right: 20px;
  margin-top: 0px;
  text-align: center;
  border-color:#81A18B;
}
.re_btn button{
  line-height: 4px;
  width: 200%;
  height: 38px;
  border-color:#81A18B;
  font-size: 20px;
  padding: 30px;
}


</style>