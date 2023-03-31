export function interceptor(Vue) {
    var localStorage = window.localStorage;

    if(!localStorage.getItem('username')) {
        Vue.$message.error("访问失败：请先登录！");
        Vue.$router.push({name:'Login'});
    }
}