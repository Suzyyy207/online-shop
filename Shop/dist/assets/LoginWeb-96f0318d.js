import{H as L,_ as k,a as S}from"./HelloWorld-187a67c0.js";import{_ as x}from"./logo-39106f3d.js";import"./constant-1e5ebc02.js";import{_ as f,r as m,o as g,c as h,a as s,b as o,w as r,d as $,e as d,p as w,f as b,F as I}from"./index-e172b211.js";const V={name:"Login",data(){return{form:{username:"",password:""}}},created(){this.clearLocalStorage()},methods:{login:function(){this.form.username==""?this.$message.error("登录失败：请输入用户名"):this.form.password==""?this.$message.error("登录失败：请输入密码"):this.$axios.post("/userlogin",{username:this.form.username,password:this.form.password}).then(e=>{const t=e.data.data;if(e.data.state==window.SUCCESS){var l=window.localStorage;l.setItem("username",this.form.username),l.setItem("usertype",t.usertype),this.$message.success("登录成功！欢迎您："+l.getItem("username")),setTimeout(()=>{switch(t.usertype){case window.USER:this.$router.push({name:"UserWeb"});break;case window.SHOPKEEPER:this.$router.push({name:"ShopkeeperWeb"});break;case window.MANAGER:this.$router.push({name:"ManagerWeb"});break;default:this.$message.error("连接错误，请重新登录！"),this.$router.push({name:"Login"})}},1e3)}else this.$message.error(e.data.message)}).catch(e=>{console.log(e)})},register:function(){this.$router.push({name:"Register"})},clearLocalStorage(){localStorage.clear()}}},C=e=>(w("data-v-7794a582"),e=e(),b(),e),E={id:"login",class:"login"},R={class:"wrap"},W=C(()=>s("h1",null,"登 录",-1)),U={class:"goto"};function K(e,t,l,_,i,n){const p=m("el-input"),c=m("el-form-item"),v=m("el-button"),y=m("el-form");return g(),h("div",E,[s("div",R,[W,o(y,{model:i.form,ref:"form",class:"form"},{default:r(()=>[o(c,{prop:"username"},{default:r(()=>[o(p,{placeholder:"用户名",type:"username",modelValue:i.form.username,"onUpdate:modelValue":t[0]||(t[0]=a=>i.form.username=a),"prefix-icon":"User",clearable:"",autocomplete:"off"},null,8,["modelValue"])]),_:1}),o(c,{id:"password",prop:"password"},{default:r(()=>[o(p,{placeholder:"密码","show-password":"",type:"password",modelValue:i.form.password,"onUpdate:modelValue":t[1]||(t[1]=a=>i.form.password=a),autocomplete:"off","prefix-icon":"Key",clearable:"",onKeyup:$(n.login,["enter","native"])},null,8,["modelValue","onKeyup"])]),_:1}),o(c,{class:"btn_login"},{default:r(()=>[o(v,{type:"primary",onClick:n.login},{default:r(()=>[d("登  录")]),_:1},8,["onClick"])]),_:1}),s("div",U,[o(c,null,{default:r(()=>[s("a",{class:"gotochild",onClick:t[2]||(t[2]=(...a)=>n.register&&n.register(...a))},"忘记密码")]),_:1}),o(c,null,{default:r(()=>[s("a",{class:"gotochild",onClick:t[3]||(t[3]=(...a)=>n.register&&n.register(...a))},"没有账号？去注册")]),_:1})])]),_:1},8,["model"])])])}const N=f(V,[["render",K],["__scopeId","data-v-7794a582"]]);const u=e=>(w("data-v-0a9f94b8"),e=e(),b(),e),B=u(()=>s("header",null,[s("img",{src:k,class:"top"})],-1)),H={class:"wrapper"},A=u(()=>s("img",{class:"logo",src:x},null,-1)),F={class:"welcome"},M=u(()=>s("footer",null,[s("img",{src:S,class:"bottom"})],-1)),P={__name:"LoginWeb",setup(e){return(t,l)=>{const _=m("RouterLink");return g(),h(I,null,[B,s("body",null,[s("div",H,[A,s("div",F,[o(L,{class:"hello",msg:"Lysw"}),s("nav",null,[o(_,{to:"/"},{default:r(()=>[d("Login")]),_:1}),o(_,{to:"/register"},{default:r(()=>[d("Register")]),_:1})])]),o(N,{class:"viewr"})])]),M],64)}}},q=f(P,[["__scopeId","data-v-0a9f94b8"]]);export{q as default};
