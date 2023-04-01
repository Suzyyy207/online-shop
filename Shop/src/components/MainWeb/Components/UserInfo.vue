<!--个人信息界面-->
<template>
    <el-form :model="form" :rules="rules" ref="form" class="form">
        <el-form-item label="用户名" prop="username">
            <el-input 
                placeholder="请输入用户名"
                type="username" 
                v-model="form.username" 
                autocomplete="off"
                clearable
                :prefix-icon=" 1 ? 'User' : ''"
                :disabled="usernameDisabled"
            >
            </el-input>
        </el-form-item>
  
        <el-form-item label="手机号" prop="phone">
            <el-input 
                placeholder="请输入手机号码"
                type="phone" 
                v-model="form.phone" 
                autocomplete="off"
                clearable
                :prefix-icon=" 1 ? 'Iphone' : ''"
                :disabled="phoneDisabled"
            ></el-input>
        </el-form-item>
  
        <el-form-item label="身份证号" prop="idnum">
            <el-input 
                placeholder="请输入电话号码"
                type="idnum" 
                v-model="form.idnum" 
                autocomplete="off"
                clearable
                :prefix-icon=" 1 ? 'Postcard' : ''"
                :disabled="idnumDisabled"
            ></el-input>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
            <el-input 
                placeholder="请输入邮箱"
                type="email" 
                v-model="form.email" 
                autocomplete="off"
                clearable
                :prefix-icon=" 1 ? 'Message' : ''"
                :disabled="emailDisabled"
            ></el-input>
        </el-form-item>
  
        <el-row v-if="isModified">
            <el-form-item prop="validCode" >
                <el-input 
                v-model="form.validCode" 
                placeholder="验证码"
                :prefix-icon=" 1 ? 'MessageBox' : ''"
                ></el-input>
            </el-form-item>
            <ValidCode @input="createValidCode" />
        </el-row>
  
        <el-form-item>
            <el-button v-if="isModified" type="primary" @click="setUserInfo('form')">提交</el-button>
            <el-button v-else type="primary" @click="toModify">修改</el-button>
        </el-form-item>
  
    </el-form>
  </template>
  
  <script>
  import "../../../constant.js";
  import { ElMessage, ElMessageBox } from 'element-plus';
  import { validatePhone, validateUsername, validateIdnum, validateEmail } from "../../../validate";
  import ValidCode from "./ValidCode.vue";
  
  const phoneValidator = (rule, value, callback) => {
    if (!value) {
      return callback(new Error("电话号码不能为空"));
    } else {
      if (validatePhone(value)) {
        callback();
      } else {
        return callback(new Error('电话号码格式不正确'))
      }
    }
  };
  const usernameValidator = (rule, value, callback) => {
    if (!value) {
      return callback(new Error("用户名不能为空"));
    } else {
      if (validateUsername(value)) {
        callback();
      } else {
        return callback(new Error('用户名格式不正确：仅能出现英⽂字符、数字与下划线；用户名长度为3-10'))
      }
    }
  };
  const idnumValidator = (rule, value, callback) => {
    if (!value) {
      return callback(new Error("身份证号不能为空"));
    } else {
      if (validateIdnum(value)) {
        callback();
      } else {
        return callback(new Error('身份证号格式不正确'))
      }
    }
  };
  const emailValidator = (rule, value, callback) => {
    if (!value) {
      return callback(new Error("邮箱不能为空"));
    } else {
      if (validateEmail(value)) {
        callback();
      } else {
        return callback(new Error('邮箱格式不正确'))
      }
    }
  };
  
  export default {
    name: "userInfo",
    components:{
      ValidCode
    },
    data() {
      var validCodeValidator = (rule, value, callback) => {
        if(!value) {
          callback(new Error("验证码不能为空"));
        }
        if (this.form.validCode.toLowerCase() === this.validCode.toLowerCase()) {
          callback()
        } else {
          callback(new Error('验证码错误!'));
        }
      };
      return {
        form: {
          username: "username",
          phone: "phone",
          idnum: "idnum",
          email: "email",
          validCode: ""
        },
        validCode: "",
        isModified: false,
        usernameDisabled: true,
        phoneDisabled: true,
        idnumDisabled: true,
        emailDisabled: true,
        rules: {
          username: [
            { required: true, validator: usernameValidator, trigger: 'change' },
            { required: true, validator: usernameValidator, trigger: 'blur' }
          ],
          phone: [
            { required: true, validator: phoneValidator, trigger: "change" },
            { required: true, validator: phoneValidator, trigger: "blur" }
          ],
          idnum: [
            { required: true, validator: idnumValidator, trigger: 'change' },
            { required: true, validator: idnumValidator, trigger: 'blur' }
          ],
          email: [
            { required: true, validator: emailValidator, trigger: 'change' },
            { required: true, validator: emailValidator, trigger: 'blur' }
          ],
          validCode: [
            { required: true, validator: validCodeValidator, trigger: 'change' },
            { required: true, validator: validCodeValidator, trigger: 'blur' }
          ]
        }
      }
    },
    created() {
        //this.getUserInfo
    },
    methods: {
      getUserInfo() {
        var localStorage = window.localStorage;
        this.$axios.post('/getUserInfo', {
            username: localStorage.getItem("username")
        })
        .then(res => {
            const user = res.data.data;
            /*this.form.gender = user.gender;*/
            this.form.phone = user.phone;
            this.form.idnum = user.idnum;
            this.form.email = user.email;
        })
      },
      reset: function () {
        ElMessageBox.confirm(
          '你确定要重置输入信息吗',
          '', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          })
          .then(() => {
            ElMessage({
              type: 'success',
              message: '已成功重置',
            })
            this.confirmReset();
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '已取消',
            })
          })
      },
      setUserInfo: function(form) {
        this.$refs[form].validate((valid=>{
            if(valid) {
                var localStorage = window.localStorage;
                this.$axios.post('/setUserInfo', {
                    username: localStorage.getItem("username"),
                    newusername: this.form.username,
                    /*goodstype: this.form.goodstype.join(';'),
                    gender: this.form.gender,*/
                    phone: this.form.phone,
                    idnum: this.form.idnum,
                    email: this.form.email
                    /*birthday: this.form.birthday*/
                })
                .then(res => {
                    if(res.data.state == window.SUCCESS){
                        this.$message.sucess(res.data.message);
                        var localStorage = window.localStorage;
                        localStorage.setItem("username", this.form.username);
                        // TODO：跳转到一个空白页面后跳转回来
                        this.$router.push({name:'UserInfoBlank'});
                    } else {
                        const error_msg = res.data.message.replace(/&$/, '');
                        const msg = error_msg.split("&");
                        for(let item of msg) {
                            this.$message.error(item);
                        }
                    }
                })
            } else {
                this.$message.error("修改失败，请按照要求填写所有信息");
            }
        }))
      },
      onSubmit() {
        var localStorage = window.localStorage;
        this.$axios.post('/setUserInfo', {
            username: localStorage.getItem("username"),
            newusername: this.form.username,
            /*goodstype: this.form.goodstype.join(';'),
            gender: this.form.gender,*/
            phone: this.form.phone,
            idnum: this.form.idnum,
            email: this.form.email
            /*birthday: this.form.birthday*/
        })
        .then(res => {
            if(res.data.state == window.SUCCESS){
            this.$message.sucess(res.data.message);
            var localStorage = window.localStorage;
            localStorage.setItem("username", this.form.username);
            // TODO：跳转到一个空白页面后跳转回来
            this.$router.push({name:'UserInfoBlank'});
            } else {
            this.$message.error(res.data.message);
            }
        })
        },
     toModify() {
        this.usernameDisabled = false,
        this.phoneDisabled = false,
        this.idnumDisabled = false,
        this.emailDisabled = false,
        this.isModified = true
     },
     createValidCode(data){
        this.validCode = data;
    },
     confirmReset() {
        this.$refs.form.resetFields(); // 重置表单
        this.form.usertype="";
        this.form.agreement="";
     },
    }
  }
  </script>
  