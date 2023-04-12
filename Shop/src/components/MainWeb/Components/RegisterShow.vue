<!--此组件将商家注册信息打包，并用于给管理员展示注册信息-->
<script setup>
import { reactive, ref } from 'vue'
const dialogTableVisible = ref(false)
</script>


<template>
  <div class="shop" :shop="shop">
    <a href="#" class="shop_link">
      <img v-if="shop.avatar" :src="'data:image/jpeg;base64,' + shop.avatar">
      <img v-else class="logo" src="@/assets/shop.png"/>
      
      <p class="shopname">{{shop.shopname}}</p>
      <p class="introdunction">{{shop.introduction}}</p>

      <el-button class="btn_approval" v-if="isregistered=1" @click="dialogTableVisible = true">
        <p>点击查看详细信息</p>
      </el-button>
      <p v-else>注册状态：已注册成功</p>
<!--这里不加标题无法正常关闭-->
      <el-dialog  class="information" v-model="dialogTableVisible" title="商家信息">
        <p>店名：{{shop.shopname}}</p>
        <p>店铺id：{{shop.shopid}}</p>
        <p>类别：{{shop.goodstype}}</p>
        <p>启动资金：{{shop.capital}}</p>
        <p>注册日期：{{shop.date}}</p>
        <p>备份地址：{{shop.address}}</p>
        <p>负责人用户id：{{shop.uid}}</p>
        <p>负责人身份证号码：{{shop.idnum}}</p>
        <p class="bottom">简介：{{shop.introduction}}</p>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="primary" @click="registrationApproved">批准</el-button>
            <el-button  @click="registrationRejected">拒绝</el-button>
          </span>
        </template>
      </el-dialog>
          
          
    </a>
  </div>
</template>

<script>
import { ElMessage, ElMessageBox } from 'element-plus';
import "../../../constant.js";
export default {
  props: {
    shop: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      shopid: this.shop.shopid
    }
  },
  methods: {
    registrationApproved: function() {
      this.$axios.post('/registrationApproved', {
          shopname: this.shop.shopname
        })
      .then(res=>{
        if(res.data.state == window.SUCCESS){
          this.$message.success("已同意注册！");
          setTimeout(() => {
            // 修改成功后需要刷新页面：跳转到一个空白页面再跳转回来
            this.$router.push({name:'ManagerWebBlank'});
          }, 1000);
        }
        else {
          this.$message.error("请重试");
        }
      })
    },
    registrationRejected: function() {
      this.$axios.post('/registrationRejected', {
          shopname: this.shop.shopname
        })
      .then(res=>{
        if(res.data.state == window.SUCCESS){
          this.$message.success("已拒绝注册！");
          setTimeout(() => {
            // 修改成功后需要刷新页面：跳转到一个空白页面再跳转回来
            this.$router.push({name:'ManagerWebBlank'});
          }, 1000);
        }
        else {
          this.$message.error("请重试");
        }
      })
    }
  }
}
</script>

<style scoped>
.shop_link{
    width: 320px;
    height: 250px;
    padding: 20px 30px 10px 30px;
    margin: 10px 20px 20px 20px;
    line-height: 100px;
    align-items: center;

    display: grid;
    grid-template-columns: 60% 40%;
    grid-template-rows: 20% 50% 30%;
    grid-column-gap:20px;

    background-color: #fff;
    border-radius: 15px;
    color: #303133;
    border: 2px solid #ebeef5;
    transition: .3s;
}
.shop_link .logo{
    grid-row: 1/3;
    grid-column: 1/2;
    width: 80%;
    height: 80%;
}
.shop_link p{
  align-items: left;
  padding-top: 5px;
  margin-left: -20px;
  line-height: 30px;
}


.shop_link .btn_approval{
    grid-row: 3/4;
    grid-column: 1/3;
}



.shop_link .information p{
  font-family:"Lucida Console", "Courier New", monospace;
  font-size:20px;
  margin:0px 0px 0px 50px;
  line-height: 40px;
}
.shop_link .information .bottom{
  font-family:"Lucida Console", "Courier New", monospace;
  font-size:20px;
  margin:0px 0px -70px 50px;
}

.dialog-footer{
  margin-right: 10px;
}


</style>