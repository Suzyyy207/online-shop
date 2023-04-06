<!--已申请未批复+已申请被拒绝-->
<script setup>
    import WrapperRegisterGoods from './WrapperRegisterGoods.vue';
    import WrapperEditGoods from './WrapperEditGoods.vue';
</script>

<template>
    <el-tabs tab-position="left">
        <el-tab-pane label="注册申请">
            <WrapperRegisterGoods/>
        </el-tab-pane>
        <el-tab-pane label="修改申请">
            <WrapperEditGoods/>
        </el-tab-pane>
    </el-tabs>
    
</template>
<script>
export default {
    
}
</script>