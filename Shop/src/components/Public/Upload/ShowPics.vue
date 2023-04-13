<!-- 用户可见的商品详情页面 -->
<template>

<div class="image">
    <div v-for="(avatar, index) in goodsAvatar" :key="index">
        <img :src="'data:image/jpeg;base64,' + avatar">
    </div>
    <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="totalAvatars"
    />

</div>

</template>
 
<script>
export default {
    props: {
        goodsAvatar: []
    },
    data() {
        return {
            currentPage: 1, // 当前页码
            pageSize: 1, // 每页显示数量
            totalAvatars: 0 // 总图片数量
        };
    },
    created() {
        this.totalAvatars = this.goodsAvatar.length
    },
    computed: {
        currentAvatars() {
            // 计算当前页需要显示的图片
            const start = (this.currentPage - 1) * this.pageSize
            const end = start + this.pageSize
            return this.goods.goodsAvatar.slice(start, end)
        }
    },
    methods: {
        handleCurrentChange(newPage) {
            this.currentPage = newPage
        }
    }
}

</script>

 
<style scoped>

.wrap{
    display: grid;
    gap:30px;
    height:auto;
    margin:30px auto 50px;
}
.wrap .title{
    width:40%;
    margin: 10px auto;
    padding: 10px 0;
    text-align: center;
    color:#81A18B;
    font-size: 45px;
    font-family: "Brush Script MT", cursive;
    border: 2px solid #ebeef5;
    border-radius: 10px;
}

.wrap .infoBuy{
    padding: 40px 40px;
    margin: 0px auto;
    border: 2px solid #ebeef5;
    border-radius: 5px;
    width: 90%;
    display: grid;
    grid-template-columns: 40% 60%;
    column-gap: 30px;
}

.wrap .detail{
    margin:0 auto;
    width: 90%;
}

.image img{
    width:90%;
    height: 90%;
    background-color:rgba(128,128,128,0.3);
    margin-bottom: 10px;
}

.info{
    display: flex;
    flex-direction: column;
    gap:10px;
    font-family: "Brush Script MT", cursive;
    font-size: 20px;
}
.info .basicInfo{
    line-height: 30px;
    background-color: rgba(129,161,139,0.1);
    padding: 20px;
    width:fit-content;
}

.info .left{
    display: flex;
    gap:20px;
}

.info .buyInfo{
    margin:20px 0;
    padding: 20px;
    width:fit-content;
    display: flex;
    gap:20px;
    background:#FFF2E8;
    
}
.buy{
    display: flex;
    flex-direction: column;
    gap:20px;
}

.buy .add{
    display: flex;
    gap:20px;
}
.buy .buyBtn{
    margin-top:20px;
    display: flex;
    gap:10px; 
}

.btn{
    width:30%
}
</style>