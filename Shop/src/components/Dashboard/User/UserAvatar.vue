<!--用户头像组件-->
<template>
  <div>
    <!--img v-if="this.isUploaded" :src="this.base64Data">
    <input
      v-if="!this.isUploaded"
      type="file"
      accept=".jpg,.png,.jpeg"
      ref="fileUpload"
      v-on:change="addFile($event)"
    -->
    <!--el-upload
      ref="upload"
      action=""
      :file-list="fileList"
      :on-change="handleChange"
      :auto-upload="false"
      list-type="picture-card"
      :on-preview="handlePreview"
    -->
    <!--i class="el-icon-plus upload-icon" v-if="this.fileList.length === 0" @click="$refs.upload.click()"></i-->
    <!--/el-upload-->
    <el-upload
      class="avatar-uploader"
      action="''"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
    >
      <img v-if="this.isUploaded" :src="this.base64Data">
      <el-icon v-else><Plus /></el-icon>
    </el-upload>


  </div>
</template>

<script>
import "../../../constant";
export default {
  data() {
    return {
      isUploaded: false,
      base64Data: ""
    };
  },
  created() {
    this.getUserAvatar();
  },
  methods: {
    beforeAvatarUpload(file) {
      //var files = file.target.files[0];
      //console.log(files.name);
      console.log(file.name);
      var avatar = new FormData();
      //avatar.append('image', file.target.files[0])
      avatar.append('image', file);
      avatar.append('username', localStorage.getItem("username"));
      this.$axios.post('/setUserAvatar', avatar, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if(res.data.state == Window.SUCCESS) {
          this.$message.success("上传成功");
          this.getUserAvatar();
        }
        else {
          this.$message.error(res.data.message);
        }
      })
      return false;
    },
    getUserAvatar: function() {
      var localStorage = window.localStorage;

      this.$axios.post('/getUserAvatar', {
        // 正式版本
        username: localStorage.getItem("username")
      })
      .then(res => {
        if(res.data.state==window.SUCCESS) {
          // 已有头像
          console.log("success");
          this.$message.success("收到反馈（根据是否显示图片判断文件传输是否成功）");
          //const base64Data = res.data.avatar;
          //const blob = formData.get('image');
          //const imgUrl = URL.createObjectURL(blob);
          // const img = document.createElement('img');
          // img.src = imgUrl;
          this.base64Data = "data:image/jpeg;base64," + res.data.avatar;
          this.isUploaded = true;
        }
        else {
          console.log("failure");
          this.isUploaded=false;
        }
      })
    },
    //getIcon: function (name) {
    //  return new URL(`../../../assets/${name}`, import.meta.url).href;
    //},
    addFile: function(file) {
      var files = file.target.files[0];
      console.log(files.name);
      var avatar = new FormData();
      avatar.append('image', file.target.files[0])
      avatar.append('username', localStorage.getItem("username"));
      // 测试版本： “username”改成指定用户名
      this.$axios.post('/setUserAvatar', avatar, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if(res.data.state == Window.SUCCESS) {
          this.$message.success("上传成功");
          this.getUserAvatar();
        }
        else {
          this.$message.error(res.data.message);
        }
      })
    }
  }
};
</script>

<style>
.avatar-uploader{
  display: flex;
  margin: 0 auto;
}

</style>
