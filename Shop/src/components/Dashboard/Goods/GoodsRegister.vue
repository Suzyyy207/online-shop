<!-- 添加商品的组件页面-->

<template>
    <div>
        <!-- 面包栏 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>商品管理</el-breadcrumb-item>
        <el-breadcrumb-item>添加商品</el-breadcrumb-item>
        </el-breadcrumb>

        <!-- 进度条 -->
        <el-card>
            <el-steps :space="200" :active="activeIndex" finish-status="success" align-center>
                <el-step title="基本信息"></el-step>
                <el-step title="管理员审核"></el-step>
                <el-step title="修改申请信息"></el-step>
                <el-step title="管理员审核修改信息"></el-step>
                <el-step title="申请成功"></el-step>
            </el-steps>

        </el-card>

        <el-form :model="addForm" :rules="addFormRules" ref="addForm" label-width="100px" label-position="top">
            <div v-if="this.activeIndex==1">
                <!-- TODO: icon添加 -->
                <el-form-item label="商品名称" prop="goodsname"> 
                    <el-input 
                        v-model="addForm.goodsname"
                    ></el-input>
                </el-form-item>

                <el-form-item label="商品价格" prop="goodsPrice"> 
                    <el-input 
                        v-model="addForm.goodsPrice" 
                    ></el-input>
                    <span class="input-requirement">商品价格不能小于0，且保留2位小数 </span>
                </el-form-item>

                <el-form-item label="初始库存" prop="goodsNumber"> 
                    <el-input 
                        v-model="addForm.goodsNumber" 
                    ></el-input>
                    <span class="input-requirement">初始库存为不小于0的整数 </span>
                </el-form-item>

                <!--TODO：重新分配类型，做成级联选择器-->

                <el-form-item label="商品类别" prop="goodsCategory">
                    <el-checkbox-group v-model="addForm.goodsCategory">
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

                <!--TODO: 简介框需要比其他框height更大-->
                <el-form-item label="店铺简介" prop="introduction">
                    <el-input 
                        placeholder="店铺简介（不超过128个字）" 
                        v-model="addForm.introduction" 
                        autocomplete="off"
                        Clearable
                    ></el-input>
                </el-form-item>


                <el-upload
                    v-model:file-list="addForm.fileList"
                    auto-upload:false
                    list-type="picture-card"
                    :on-remove="handleChange"
                    ref="upload"
                    :action="''"
                    :auto-upload="false"
                    :on-change="handleChange"
                >
                <el-icon><Plus /></el-icon>
                </el-upload>
                
                <el-button 
                    type="primary" 
                    @click="handleUpload('addForm')" 
                >注&nbsp;&nbsp;册
                </el-button>
            </div>

            <div v-if="this.activeIndex==2">
                管理员正在审核，请耐心等待...
            </div>
        </el-form>


    </div>
</template>
 
<script>
import "../../../constant";
export default {
    data(){
        return {
            activeIndex: 1,
            addForm: {
                goodsname:'',
                goodsPrice: 0,
                goodsNumber: 0,
                goodsCategory: [],
                fileList: [],
                introduction: "",
                goodsId: ""
            },
            addFormRules: {
                goodsname:[
                    {required:true, message:'请输入商品名称', trigger: 'blur'},
                    {required:true, message:'请输入商品名称', trigger: 'change'}
                ],
                goodsPrice:[
                    { required:true, validator: this.goodsPriceValidator, trigger: 'blur' }
                ],
                goodsNumber:[
                    { required:true, validator: this.goodsNumberValidator, trigger: 'blur' }
                ],
                introduction:[
                    { required: true, message: '店铺简介不能为空', trigger: 'blur' },
                    { required: true, message: '店铺简介不能为空', trigger: 'change' },
                    { required: true, max: 128, message: '店铺简介不能超过128个字', trigger: 'blur' },
                    { required: true, max: 128, message: '店铺简介不能超过128个字', trigger: 'change' }
                ],
                goodsCategory:[
                    { required:true, validator: this.goodsCategoryValidator, trigger: 'blur' },
                    { required:true, validator: this.goodsCategoryValidator, trigger: 'change' }
                ]
            } 
        }
    },
    created(){},
    methods:{
        goodsPriceValidator(rule, value, callback) {
            if (isNaN(Number(value))) {
                this.addForm.goodsPrice = 0;
                return callback(new Error("请输入正确的浮点数"));
            }
            else {
                const input = String(value);
                // 使用正则表达式将其转换为保留两位小数的字符串
                const regex = /^\d+(\.\d{0,2})?/;
                const match = input.match(regex);
                const formattedValue = match ? match[0] : '0';
                // 将转换后的值赋给 this.addForm.goodsPrice
                this.addForm.goodsPrice = parseFloat(formattedValue);
            }
            callback();
        },
        goodsNumberValidator(rule, value, callback) {
            if (isNaN(Number(value))) {
                this.addForm.goodsNumber = 0;
                return callback(new Error("请输入正确的浮点数"));
            }
            else {
                this.addForm.goodsNumber = parseInt(value);
            }
            callback();
        },
        goodsCategoryValidator(rule, value, callback) {
            if(value.length==0) {
                return callback(new Error("请至少选择一项"));
            }
            callback();
        },
        fileListValidator(rule, value, callback) {
            if(value.length==0) {
                return callback(new Error("至少上传一张图片"));
            }
            callback();
        },
        handleChange(file, fileList) {
            this.addForm.fileList = fileList;
            console.log(this.addForm.fileList.length);
        },
        handleUpload: function (addForm)  {
            console.log("here");
            this.$refs[addForm].validate((valid) => {
            if (valid) {
                // 先传输普通数据
                var localStorage = window.localStorage;
                this.$axios.post('/goodsRegister', {
                    // 测试版本——shopname: "目标店铺名"
                    // 正式版本——shopname: localStorage.getItem("shopname"),
                    shopname: "SHOPNAME",
                    goodsCategory: this.addForm.goodsCategory.join(';'),
                    introduction: this.addForm.introduction,
                    goodsname:this.addForm.goodsname,
                    goodsPrice: this.addForm.goodsPrice,
                    goodsNumber: this.addForm.goodsNumber
                })
                .then(res => {
                    if(res.data.state == window.SUCCESS){
                        // 然后传输图片
                        let formData = new FormData();
                        formData.append("goodsId", res.data.data.goodsId);
                        for (let i = 0; i < this.addForm.fileList.length; i++) {
                            formData.append("file", this.addForm.fileList[i].raw);
                        }
                        this.$axios.post("/setGoodsPicture", formData, {
                            headers: { "Content-Type": "multipart/form-data" },
                        })
                        .then((res) => {
                            console.log(res.data);
                            this.$message.success("提交成功！");
                            this.next();
                        })
                        .catch((err) => {
                            console.log(err);
                            this.$message.error("提交失败，请重试");
                        });
                    }
                    else {
                        this.$message.error("提交失败，请重试");
                    }
                })
                .catch(err => {
                console.log(err);
                })
            } else {
                console.log("校验失败");
                return false;
            }
          });
        },
        next() {
            this.activeIndex++;
        }

    }
}

</script>

 
<style scoped>
 .el-steps{
  margin: 15px 0;
}
.el-step__title{
  font-size: 13px;
}

</style>