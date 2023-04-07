<!--个人信息界面-->

<template>
    <el-form :model="form" :rules="rules" ref="form" class="form">
        <UserAvatar class="imgU"></UserAvatar>
        <el-row>
          <el-col :span="12">
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
          </el-col>
          <el-col :span="12">
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
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
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
          </el-col>

          <el-col :span="12">
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
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="密码" prop="password">
              <el-input 
                placeholder="密码"
                show-password 
                type="password" 
                v-model="form.password" 
                autocomplete="off"
                clearable
                :prefix-icon=" 1 ? 'Key' : ''"
                :disabled="passwordDisabled"
              ></el-input>
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="确认密码" prop="password2" v-if="isModified">
              <el-input
                  placeholder="确认密码"
                  show-password
                  type="password"
                  v-model="form.password2"
                  autocomplete="off"
                  clearable
                  :prefix-icon=" 1 ? 'Key' : ''"
                  @keyup.enter.native="register"
                ></el-input>
              </el-form-item>
          </el-col>
        </el-row>


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
            <el-button class="btn" v-if="isModified" type="primary" @click="setUserInfo('form')">提交</el-button>
            <el-button class="btn" v-else type="primary" @click="toModify">修改</el-button>
        </el-form-item>
    </el-form>
  </template>
  
  <script>
  import "../../../constant.js";
  import { ElMessage, ElMessageBox } from 'element-plus';
<<<<<<< HEAD:Shop/src/components/MainWeb/Components/UserInfo.vue
  import ValidCode from "./ValidCode.vue";
  import { validatePhone, validateUsername, validateIdnum, validateEmail, validatePassword } from "../../../validate";
  import { objectExpression } from "@babel/types";
=======
  import { validatePhone, validateUsername, validateIdnum, validateEmail, validatePassword } from "../../../validate";
  import ValidCode from "../../MainWeb/Components/ValidCode.vue";
  import UserAvatar from "./UserAvatar.vue";
>>>>>>> d4be2944a3ae92aa72288a643d356aa24580d997:Shop/src/components/Dashboard/User/UserInfo.vue
  
  const passwordValidator = (rule, value, callback) => {
  if (!value) {
    return callback(new Error("密码不能为空"));
  } else {
    if (validatePassword(value)) {
      callback();
    } else {
      return callback(new Error('格式不正确:密码⻓度为6-32个字符; 字⺟，数字或者特殊字符（-_）⾄少包含两种'))
    }
  }
};
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
      ValidCode,
      UserAvatar
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
      var password2Validator = (rule, value, callback) => {
        if(!value) {
          callback(new Error('确认密码不能为空!'));
        }
        if (value !== this.form.password) {
            callback(new Error('两次输入密码不一致!'));
        } else {
            callback();
        }
      };
      return {
        form: {
<<<<<<< HEAD:Shop/src/components/MainWeb/Components/UserInfo.vue
          username: "",
          phone: "",
          idnum: "",
          email: "",
          validCode: "",
          password: "",
          password2: ""
        },
        user: {
          oldusername: "",
          oldphone: "",
          oldemail: "",
          oldpassword: ""
=======
          username: "username",
          phone: "phone",
          idnum: "440404200404040404",
          email: "email",
          validCode: "",
          password: "",
          password2: ""
>>>>>>> d4be2944a3ae92aa72288a643d356aa24580d997:Shop/src/components/Dashboard/User/UserInfo.vue
        },
        validCode: "",
        isModified: false,
        usernameDisabled: true,
        phoneDisabled: true,
        idnumDisabled: true,
        emailDisabled: true,
        passwordDisabled: true,
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
          ],
          password: [
            { required: true, validator: passwordValidator, trigger: 'change' },
            { required: true, validator: passwordValidator, trigger: 'blur' }
          ],
          password2: [
            { required: true, validator: password2Validator, trigger: 'change' },
            { required: true, validator: password2Validator, trigger: 'blur' }
          ]
        }
      }
    },
    created() {
        this.getUserInfo();
    },
    methods: {
      getUserInfo() {
        var localStorage = window.localStorage;
        this.$axios.post('/getUserInfo', {
            // 测试版本：自定义username
            // username: "username"
            // 正式版本
            username: localStorage.getItem("username")
        })
        .then(res => {
            const user = res.data.data;
            /*this.form.gender = user.gender;*/
            this.form.phone = user.phone;
            this.form.idnum = user.idnum;
            this.form.email = user.email;
            this.form.password = user.password;
<<<<<<< HEAD:Shop/src/components/MainWeb/Components/UserInfo.vue
            this.user.oldusername = user.username;
            this.user.oldphone= user.phone;
            this.user.oldemail= user.email;
            this.user.oldpassword= user.password;
=======
>>>>>>> d4be2944a3ae92aa72288a643d356aa24580d997:Shop/src/components/Dashboard/User/UserInfo.vue
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
                if(this.user.oldusername == this.form.username && this.user.oldphone == this.form.phone && this.user.newemail == this.form.email && this.user.password == this.user.newpassword) {
                  this.$message.error("您没有修改任何信息，请修改信息后重新提交");
                  return;
                }
                this.$axios.post('/setUserInfo', {
                    username: localStorage.getItem("username"),
                    newusername: this.form.username,
                    newphone: this.form.phone,
                    idnum: this.form.idnum,
                    newemail: this.form.email
                })
                .then(res => {
                    if(res.data.state == window.SUCCESS){
                        this.$message.sucess(res.data.message);
                        var localStorage = window.localStorage;
                        localStorage.setItem("username", this.form.username);
                        this.$router.go(0);
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
     toModify() {
        this.usernameDisabled = false;
        this.phoneDisabled = false;
        this.emailDisabled = false;
        this.passwordDisabled = false;
        this.isModified = true;
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
  
<style scoped>
.form{
  margin:50px;
  gap:20px;
}

.el-form-item{
  margin:20px;
}

.imgU{
  width:100px;
  height: 100px;
  margin:20px auto;
  display: flex;
  border: 3px dashed gray; /* 3像素宽的黑色虚线边框 */
  background-color: rgba(128, 128, 128, 0.2); /* 透明灰色背景 */
}

.form .btn {
  line-height: 100px;
  width: 100%;
  height: 38px;
  background-color:#81A18B;
  border-color:#81A18B;
  justify-items: center;

}
</style>