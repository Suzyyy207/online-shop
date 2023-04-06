<!-- 添加商品的组件页面-->

<template>
    <div>
        <el-form :model="addForm" :rules="addFormRules" ref="addForm" label-width="100px" label-position="top">
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

            <el-form-item label="初始库存" prop="goodsStock"> 
                <el-input 
                    v-model="addForm.goodsStock" 
                ></el-input>
                <span class="input-requirement">初始库存为不小于0的整数 </span>
            </el-form-item>

            <!--TODO：重新分配类型，做成级联选择器-->

            <el-form-item label="商品类别" prop="goodsCategory">
                <el-checkbox-group v-model="addForm.goodsCategory">
                    <el-checkbox label="电脑数码" />
                    <el-checkbox label="家用电器" />
                    <el-checkbox label="运动户外" />
                    <el-checkbox label="服饰鞋包" />
                    <el-checkbox label="个护化妆" />
                    <el-checkbox label="母婴用品" />
                    <el-checkbox label="日用百货" />
                    <el-checkbox label="食品生鲜" />
                    <el-checkbox label="配饰腕表" />
                    <el-checkbox label="图书音像" />
                    <el-checkbox label="玩模乐器" />
                    <el-checkbox label="办公设备" />
                    <el-checkbox label="家居家装" />
                    <el-checkbox label="汽车消费" />
                    <el-checkbox label="艺术收藏" />
                    <el-checkbox label="其他" />
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
                v-if="isModified"
                type="primary" 
                @click="setGoodsInfo('addForm')" 
            >提交
            </el-button>

            <el-button 
                v-else
                type="primary" 
                @click="handleUpload('addForm')" 
            >注&nbsp;&nbsp;册
            </el-button>

        </el-form>
    </div>
</template>
 
<script>
import "../../../constant";
export default {
    props: {
        goods: {
            type: Object,
            required: false,
            default: ""
        }
    },
    data(){
        return {
            isModified: 0,
            addForm: {
                goodsname:'',
                goodsPrice: 0,
                goodsStock: 0,
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
                goodsStock:[
                    { required:true, validator: this.goodsStockValidator, trigger: 'blur' }
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
    created(){
        this.getGoodsInfo();
    },
    methods:{
        getGoodsInfo() {
            if(this.goods!="") {
                console.log("商品信息修改");
                this.isModified = 1;
                this.addForm.goodsname = this.goods.goodsname;
                this.addForm.goodsPrice = this.goods.goodsPrice;
                this.addForm.goodsStock = this.goods.goodsStock;
                this.addForm.goodsCategory = this.goods.goodsCategory;
                this.addForm.introduction = this.goods.introduction;
                this.addForm.fileList = this.goods.goodsAvatar;
                // TODO：fileList类型转换

                //goodsId = this.goods.goodsId
                console.log(this.goods);
            }
        },
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
        goodsStockValidator(rule, value, callback) {
            if (isNaN(Number(value))) {
                this.addForm.goodsStock = 0;
                return callback(new Error("请输入正确的库存数"));
            }
            else {
                this.addForm.goodsStock = parseInt(value);
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
                    shopname: localStorage.getItem("shopname"),
                    goodsCategory: this.addForm.goodsCategory,
                    introduction: this.addForm.introduction,
                    goodsname:this.addForm.goodsname,
                    goodsPrice: this.addForm.goodsPrice,
                    goodsStock: this.addForm.goodsStock
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
        setGoodsInfo: function (addForm) {
            this.$refs[addForm].validate((valid) => {
            if (valid) {
                // 先传输普通数据
                var localStorage = window.localStorage;
                this.$axios.post('/setGoodsInfo', {
                    goodsId: this.goods.goodsId,
                    goodsCategory: this.addForm.goodsCategory,
                    introduction: this.addForm.introduction,
                    goodsname:this.addForm.goodsname,
                    goodsPrice: this.addForm.goodsPrice,
                    goodsStock: this.addForm.goodsStock
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