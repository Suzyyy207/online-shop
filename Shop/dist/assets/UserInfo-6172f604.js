import{_ as x,r as a,o as p,i as b,w as n,b as e,c as C,h as D,F as w,e as h,t as I}from"./index-e172b211.js";new FormData;const S={data(){return{form:{gender:"",birthday:"",preference:[],username:"username",phone:"phone",idnum:"",email:"",image:"../../../assets/logo.png"},usernameDisabled:!0,phoneDisabled:!0,idnumDisabled:!0,emailDisabled:!0,goodstypes:["电脑数码","家用电器","运动户外","服饰鞋包","个护化妆","母婴用品","日用百货","食品生鲜","配饰腕表","图书音像","玩模乐器","办公设备","家居家装","汽车消费","艺术收藏","其他"]}},created(){},methods:{getUserInfo(){var i=window.localStorage;this.$axios.post("/getUserInfo",{username:i.getItem("username")}).then(l=>{const r=l.data.data;this.form.gender=r.gender,this.form.goodstypes=r.goodstypes,this.form.birthday=r.birthday,this.form.phone=r.phone,this.form.idnum=r.idnum,this.form.email=r.email})},setUsername(){this.usernameDisabled=!1},setPhone(){this.phoneDisabled=!1},setIdnum(){this.idnumDisabled=!1},setEmail(){this.emailDisabled=!1},onSubmit(){var i=window.localStorage;this.$axios.post("/setUserInfo",{username:i.getItem("username"),newusername:this.form.username,goodstype:this.form.goodstype.join(";"),gender:this.form.gender,phone:this.form.phone,idnum:this.form.idnum,email:this.form.email,birthday:this.form.birthday}).then(l=>{if(l.data.state==window.SUCCESS){this.$message.success("修改成功!");var r=window.localStorage;r.setItem("username",this.form.username),this.$router.push({name:"UserInfoBlank"})}else this.$message.error(l.data.msg)})}}};function E(i,l,r,v,o,m){const d=a("el-input"),s=a("el-form-item"),u=a("Edit"),f=a("el-icon"),c=a("el-radio"),_=a("el-radio-group"),g=a("el-date-picker"),V=a("el-checkbox-button"),k=a("el-checkbox-group"),y=a("el-button"),U=a("el-form");return p(),b(U,{model:o.form},{default:n(()=>[e(s,{label:"用户名"},{default:n(()=>[e(d,{placeholder:"this.form.username",type:"username",modelValue:o.form.username,"onUpdate:modelValue":l[0]||(l[0]=t=>o.form.username=t),autocomplete:"off",clearable:"","prefix-icon":"User",disabled:o.usernameDisabled},null,8,["modelValue","disabled"])]),_:1}),e(f,null,{default:n(()=>[e(u,{onClick:m.setUsername},null,8,["onClick"])]),_:1}),e(s,{label:"手机号"},{default:n(()=>[e(d,{placeholder:"this.form.phone",modelValue:o.form.phone,"onUpdate:modelValue":l[1]||(l[1]=t=>o.form.phone=t),autocomplete:"off",clearable:"","prefix-icon":"Iphone",disabled:o.phoneDisabled},null,8,["modelValue","disabled"])]),_:1}),e(f,null,{default:n(()=>[e(u,{onClick:m.setPhone},null,8,["onClick"])]),_:1}),e(s,{label:"性别"},{default:n(()=>[e(_,{modelValue:o.form.gender,"onUpdate:modelValue":l[2]||(l[2]=t=>o.form.gender=t)},{default:n(()=>[e(c,{label:"男"}),e(c,{label:"女"})]),_:1},8,["modelValue"])]),_:1}),e(s,{label:"生日"},{default:n(()=>[e(g,{modelValue:o.form.birthday,"onUpdate:modelValue":l[3]||(l[3]=t=>o.form.birthday=t),type:"date",placeholder:"Pick a date",style:{width:"100%"}},null,8,["modelValue"])]),_:1}),e(s,{label:"身份证号"},{default:n(()=>[e(d,{placeholder:"this.form.idnum",type:"idnum",modelValue:o.form.idnum,"onUpdate:modelValue":l[4]||(l[4]=t=>o.form.idnum=t),autocomplete:"off",clearable:"","prefix-icon":"Postcard",disabled:o.idnumDisabled},null,8,["modelValue","disabled"])]),_:1}),e(f,null,{default:n(()=>[e(u,{onClick:m.setIdnum},null,8,["onClick"])]),_:1}),e(s,{label:"邮箱"},{default:n(()=>[e(d,{placeholder:"this.form.email",type:"email",modelValue:o.form.email,"onUpdate:modelValue":l[5]||(l[5]=t=>o.form.email=t),autocomplete:"off",clearable:"","prefix-icon":"Message",disabled:o.emailDisabled},null,8,["modelValue","disabled"])]),_:1}),e(f,null,{default:n(()=>[e(u,{onClick:m.setEmail},null,8,["onClick"])]),_:1}),e(s,{label:"选择感兴趣的内容"},{default:n(()=>[e(k,{modelValue:o.form.preference,"onUpdate:modelValue":l[6]||(l[6]=t=>o.form.preference=t),size:"small"},{default:n(()=>[(p(!0),C(w,null,D(this.goodstypes,t=>(p(),b(V,{key:t,label:t},{default:n(()=>[h(I(t),1)]),_:2},1032,["label"]))),128))]),_:1},8,["modelValue"])]),_:1}),e(s,null,{default:n(()=>[e(y,{type:"primary",onClick:m.onSubmit},{default:n(()=>[h("提交")]),_:1},8,["onClick"])]),_:1})]),_:1},8,["model"])}const P=x(S,[["render",E]]);export{P as default};
