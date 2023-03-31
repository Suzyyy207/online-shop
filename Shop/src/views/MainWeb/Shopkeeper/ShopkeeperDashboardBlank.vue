<template>

</template>

<script>
import { interceptor } from "../../../interceptor";
export default {
    data (){
        this.$router.push({name:'ShopDashboard'});
    },
    created(){
        interceptor(this);
    }
}
</script>