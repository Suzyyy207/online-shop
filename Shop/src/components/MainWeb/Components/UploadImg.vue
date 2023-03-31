<!--用户头像组件-->
<template>
  <div>
    <img v-if="this.isUploaded" :src="getIcon(this.imageUrl)">
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
    //this.getUserInfo();
  },
  methods: {
    getUserAvatar: function() {
      var localStorage = window.localStorage;
      this.$axios.post('/getUserAvatar', {
        username: localStorage.getItem("username")
      })
      .then(res => {
        if(res.data.state==3) {
          this.imageUrl = URL.createObjectURL(response.data);
          this.isUploaded=true;
        }
        else {
          this.isUploaded=false;
        }
      })
    },
    getIcon: function (name) {
      return new URL(`../../../assets/${name}`, import.meta.url).href;
    },
    addFile: function(file) {
      var files = file.target.files[0];
      console.log(files.name)

      var formData = new FormData();
      formData.append('/setUserAvatar', file.target.files[0])
      this.$axios.post('/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if(res.data.state == 3) {
          this.$message.success("上传成功");
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
