<!--商店余额显示+充值-->
<template>
    <p>利润:{{ this.profitAccount }}</p>
</template>

<script>
export default{
    data() {
        return {
            profitAccount: 0
        }
    },
    created (){
        this.getProfitAccount();
    },
    methods: {
        getProfitAccount() {
            this.$axios.get('/getProfitAccount')
            .then(res => {
                this.profitAccount = res.data.data.profitAccount;
            }).catch((err) => {
                console.log(err);
                this.$message.error("获取利润失败，请重试");
            });
        }
    }
}
</script>

<style scoped>

.cont{
    width: 90%;
    margin:10px auto 50px;
}

</style>
