<!--商店余额显示+充值-->
<template>
    <p>中间账户余额:{{ this.intermediaryAccount }}</p>
    <div class="charge">
        <el-input v-model='credit'></el-input>
        <el-button @click="recharge">充值</el-button>
    </div>
</template>

<script>
export default{
    data() {
        return {
            intermediaryAccount: 0
        }
    },
    created (){
        this.getIntermediaryAccount();
    },
    methods: {
        getIntermediaryAccount() {
            this.$axios.get('/getIntermediaryAccount')
            .then(res => {
                this.intermediaryAccount = res.data.data.intermediaryAccount;
            })
        },
        recharge: function() {
            var localStorage = window.localStorage;
            console.log(this.credit);
            if (isNaN(Number(this.credit))|this.credit<0){
                this.$message.error("充值失败，请输入符合要求的正浮点数");
            } else {
                const credit = String(this.credit);
                const regex = /^\d+(\.\d{0,2})?/;
                const match = credit.match(regex);
                const formattedValue = match ? match[0] : '0';
                this.credit = parseFloat(formattedValue);
                this.$axios.post('/recharge', {
                    username: localStorage.getItem("username"),
                    // accountType为2表示为商店账户充值
                    accountType: 2,
                    credit: String(this.credit)
                })
                .then(res => {
                    if(res.data.state==window.SUCCESS) {
                        this.$message.success("充值金额：" + this.credit + "（系统默认保留两位小数）！");
                        this.intermediaryAccount = res.data.data.account;
                        this.credit = 0;
                    }
                    else {
                        console.log("failure");
                    }
                })
            }
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
