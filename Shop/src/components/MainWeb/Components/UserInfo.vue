<!-- 用户信息修改&补充 -->


<!--现在是用disabled来区分个人信息查看/修改，本质上还是input框-->
<!--如果不好修改样式可以改成用v-if=ismodified区分的<p>和<input>-->

<template>
  <!--TODO: 用户头像上传-->

  <el-form :model="form">
    <el-form-item label="用户名">
      <el-input 
        placeholder=this.form.username
        type="username" 
        v-model="form.username" 
        autocomplete="off"
        clearable
        :prefix-icon=" 1 ? 'User' : ''"
        :disabled="usernameDisabled"
      >
      </el-input>
    </el-form-item>

    <el-form-item label="手机号">
      <el-input 
        placeholder=this.form.phone
        v-model="form.phone" 
        autocomplete="off"
        clearable
        :prefix-icon=" 1 ? 'Iphone' : ''"
        :disabled="phoneDisabled"
      ></el-input>
    </el-form-item>

    <el-form-item label="性别">
      <el-radio-group v-model="form.gender" :disabled="genderDisabled">
        <el-radio label="男" />
        <el-radio label="女" />
      </el-radio-group>
    </el-form-item>

    <el-form-item label="身份证号">
      <el-input 
        placeholder=this.form.idnum
        type="idnum" 
        v-model="form.idnum" 
        autocomplete="off"
        clearable
        :prefix-icon=" 1 ? 'Postcard' : ''"
        :disabled="idnumDisabled"
       ></el-input>
    </el-form-item>

    <el-form-item label="邮箱">
      <el-input 
        placeholder=this.form.email
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
      <el-button v-if="isModified" type="primary" @click="onSubmit">提交</el-button>
      <el-button v-else type="primary" @click="toModify">修改</el-button>
    </el-form-item>

  </el-form>
</template>
  
<script>
import ValidCode from "./ValidCode.vue";
import "../../../constant.js";
const formData = new FormData();
export default {
  data() {
    return {
      form: {
        gender: '男',
        username: "username",
        phone: 'phone',
        idnum: 'a',
        email: 'a',
        image: '../../../assets/logo.png'
      },
      isModified: false,
      usernameDisabled: true,
      phoneDisabled: true,
      idnumDisabled: true,
      emailDisabled: true,
      genderDisabled: true
    }
  },
  components:{
      ValidCode
    },
  created() {
    //this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      var localStorage = window.localStorage;
      this.$axios.post('/getUserInfo', {
        username: localStorage.getItem("username")
      })
      .then(res => {
        const user = res.data.data;
        this.form.gender = user.gender;
        this.form.birthday = user.birthday;
        this.form.phone = user.phone;
        this.form.idnum = user.idnum;
        this.form.email = user.email;
      })
    },
    onSubmit() {
      var localStorage = window.localStorage;
      this.$axios.post('/setUserInfo', {
        username: localStorage.getItem("username"),
        newusername: this.form.username,
        goodstype: this.form.goodstype.join(';'),
        gender: this.form.gender,
        phone: this.form.phone,
        idnum: this.form.idnum,
        email: this.form.email,
        birthday: this.form.birthday
      })
      .then(res => {
        if(res.data.state == window.SUCCESS){
          this.$message.success("修改成功!");
          var localStorage = window.localStorage;
          localStorage.setItem("username", this.form.username);
          // TODO：跳转到一个空白页面后跳转回来
          this.$router.push({name:'UserInfoBlank'});
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    created() {
        this.getUserInfo();
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
            console.log(user);
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
}

</script>

  