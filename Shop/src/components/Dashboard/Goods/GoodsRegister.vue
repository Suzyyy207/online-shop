<!--此组件用于为商家提供商品注册UI-->
<script setup>
import PicUpload from '../../Public/Upload/PicUpload.vue'
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <div class="register">
        <h1>商 品 注 册</h1>
        <el-form  class="form">
            <el-form-item>
                <el-input 
                    placeholder="商品名（不超过x个字）" 
                    type="shopname" 
                    autocomplete="off"
                    :prefix-icon=" 1 ? 'House' : ''"
                    Clearable
                >
                </el-input>
            </el-form-item>

            <el-form-item label="商品类别" prop="goodstype">
                <el-checkbox-group>
                    <el-checkbox label="电脑数码" name="digitalproduct" />
                    <el-checkbox label="家用电器" name="householdappliances" />
                    <el-checkbox label="运动户外" name="sports" />
                    <el-checkbox label="服饰鞋包" name="clothing" />
                    <el-checkbox label="个护化妆" name="makeups" />
                    <el-checkbox label="母婴用品" name="babyproduct" />
                    <el-checkbox label="日用百货" name="dailynecessities" />
                    <el-checkbox label="食品生鲜" name="food" />
                    <el-checkbox label="配饰腕表" name="accessory" />
                    <el-checkbox label="图书音像" name="book" />
                    <el-checkbox label="玩模乐器" name="instrument" />
                    <el-checkbox label="办公设备" name="officeequipment" />
                    <el-checkbox label="家居家装" name="furniture" />
                    <el-checkbox label="汽车消费" name="car" />
                    <el-checkbox label="艺术收藏" name="art" />
                    <el-checkbox label="其他" name="others" />
                </el-checkbox-group>
            </el-form-item>

        
            <el-form-item>
                <el-input 
                    placeholder="商品描述" 
                    type="introduction" 
                    autocomplete="off"
                    :prefix-icon=" 1 ? 'House' : ''"
                    Clearable
                ></el-input>
            </el-form-item>

            <el-row>
              <el-col :span="12">
                <el-form-item label="商品价格" prop="capital">
                    <el-input-number
                        label="商品价格"
                        :controls="false"
                        :precision="2"
                        :prefix-icon=" 1 ? 'Money' : ''"
                        Clearable
                    >
                    </el-input-number>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="初始库存" prop="capital">
                    <el-input-number
                        label="初始库存"
                        :controls="false"
                        Clearable
                    >
                    </el-input-number>
                </el-form-item>
              </el-col>
            </el-row>

            <el-form-item label="点击上传商品照片" style="align-items: center;">

              <PicUpload></PicUpload>

            </el-form-item>
            


        <!--
            <el-row>
              <el-form-item prop="validCode" >
                <el-input 
                  v-model="form.validCode" 
                  placeholder="验证码"
                  :prefix-icon=" 1 ? 'MessageBox' : ''"
                ></el-input>
              </el-form-item>
                <ValidCode class="validcode" @input="createValidCode" />
            </el-row>
        -->


            <el-row>
              <el-col :span="8">
                <el-form-item class="btn">
                  <el-button type="primary">注&nbsp;&nbsp;册</el-button>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item class="btn">
                  <el-button type="primary">重&nbsp;&nbsp;置</el-button>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item class="btn">
                  <el-button type="primary">返&nbsp;&nbsp;回</el-button>
                </el-form-item>
              </el-col>
            </el-row>
  
        </el-form>
    </div>
  
  </template>





<style scoped>

.register h1{
  text-align: center;
  font-family:"Lucida Console", "Courier New", monospace;
  font-size: 45px;
  margin-bottom:20px;
  font-weight: bolder;
  color:#81A18B;
}

.register .el-input {
  font-family: "Brush Script MT", cursive;
  height: 50px;
  padding-top: 5px;
  padding-bottom: 5px;
}

.register .el-form-item{
  margin: 25px 10px 10px 10px;
  text-emphasis:initial;
}


.register .btn button{
  line-height: 100px;
  width: 80%;
  height: 38px;
  background-color:#81A18B;
  border-color:#81A18B;
  justify-items: center;

}


</style>