<!--用户头像组件-->
<template>
  <div>
    <!--img v-if="this.isUploaded" :src="getIcon(this.imageUrl)"-->
    <img v-if="this.isUploaded" :src="this.imageUrl">
    <input
      v-if="!this.isUploaded"
      type="file"
      accept=".jpg,.png,.jpeg"
      ref="fileUpload"
      v-on:change="addFile($event)"
    >
  </div>
  
</template>

<script>
export default {
  data() {
    return {
      isUploaded: false,
      imageUrl: ""
    };
  },
  created() {
    this.getUserAvatar();
  },
  methods: {
    getUserAvatar: function() {
      var localStorage = window.localStorage;

      this.$axios.post('/getUserAvatar', {
        // 测试版本：
        username: "数据库存储的username名称"
        // 正式版本：username: localStorage.getItem("username")
      })
      .then(res => {
        if(res.data.state==3) {
          // this.imageUrl = URL.createObjectURL(res.data.data);
          // this.isUploaded=true;
          this.$message.success("收到反馈（根据是否显示图片判断文件传输是否成功）");
          const formData = res.data.avatar;
          const blob = formData.get('image');
          const imgUrl = URL.createObjectURL(blob);
          // const img = document.createElement('img');
          // img.src = imgUrl;
          this.imageUrl = imgUrl;
          this.isUploaded = true;
        }
        else {
          this.isUploaded=false;
        }
      })
    },
    //getIcon: function (name) {
    //  return new URL(`../../../assets/${name}`, import.meta.url).href;
    //},
    addFile: function(file) {
      var files = file.target.files[0];
      console.log(files.name)
      console.log(files)
      var avatar = new FormData();
      avatar.append('image', file.target.files[0])
      avatar.append('username', "username")
      // 测试版本： “myUsername”改成指定用户名
      this.$axios.post('/setUserAvatar', avatar, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if(res.data.state == 3) {
          this.$message.success("上传成功");
          // this.getUserAvatar();
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
</style>
