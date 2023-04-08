# lab_3

## 个人账户系统 userAccount
### 个人账户展示
#### 测试：个人中心可以正确显示账户余额（具体参数传递方法见/getUserInfo）
### 个人账户充值
| 接口名   | 地址          | 方法 | 功能     |
| -------- | ------------- | ---- | -------- |
| recharge | /api/recharge | POST | 账户充值 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| username |  | String |
| accountType | 账户类型 | INT 普通买家：1(privateAccount个人账户)；商家：1(个人账户) 2（shopAccount商店账户）管理员：3(profitAccount商城利润账户) 4(intermediaryAccount商城购物中间账户) |
| credit | 充值金额 |  **String**（转换不会丢失精度）  |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 是否充值成功 | YES（0）和NO（1） |

测试：个人中心页面有充值按钮

### 个人流水显示 getUserTransactions
userTransaction类：表示用户的交易信息
PS.这里只涉及前端需要的数据，后端/数据库具体怎么设计你们自己决定就可以
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| date   | 交易日期       |  |
| counterpart  | 交易的另一方 | 某一个商店<br>商城<br>-（表示充值） |
| amount   | 交易额       | 可能正可能负，正表示收入，负表示支出 |
| balance   | 余额       |  |

| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| getUserTransactions | POST | 获取用户账户流水信息，每条信息包括（交易的另一方+交易额+账户余额） |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| username | 用户名 |           |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 传输是否成功 | YES（0）和NO（1） |
| data   | 交易记录userTransaction组成的列表       | |

## 店铺信息管理
### 店铺信息显示shopInfo
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| getShopInfoByUsername | POST | 获取指定用户商店信息（不包括头像） |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| username |  |           |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 用户是否注册过 | YES（0）和NO（1） |
| data   | shop类       | goodstype:字符串，用;拆分<br>introduction<br>address<br>idnum<br>capital<br>date<br>shopid<br>is_admitted：0为正在批复中，1为注册成功，2为管理员拒绝注册请求<br> |

测试：登录->我的店铺，已提交注册请求的商户可以显示商店信息，未注册的商户显示注册页面<br><br>
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| 店铺信息显示getShopAvatarByShopname | POST | 获取指定商店头像 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| shopname |  |           |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 是否具有头像 | YES（0）和NO（1） |
| data.avatar   | 头像数据       | base64 |

## 商品信息管理
### 商品状态goods.status
| 值 | 含义         | 类型&备注         |
| ------ | ------------ | ------------ | 
| 0  | 注册中 | 管理员尚未批复注册请求 <br>管理员拒绝注册请求        |
| 1 | 正常有效商品       | 其他所有情况 |
| 2 | 修改中       |管理员尚未批复修改请求 <br>管理员拒绝修改请求 |
| 3 | 已下架       | 下架 |
| 4  | 尚未提交注册请求 | 用户提交注册请求，在管理员批准前撤销        |

### 商品注册状态goods.registerStatus
| 值 | 含义         | 
| ------ | ------------ | 
| 0  | 提交注册信息，未批复 | 
| 1 | 管理员同意注册    | 
| 2  | 提交注册信息，管理员拒绝 |  

### 商品修改状态goods.modifyStatus
| 值 | 含义         | 
| ------ | ------------ | 
| 0   | 未修改      | 
| 1   | 提交修改信息，未批复     | 
| 2  | 提交修改信息，管理员拒绝  | 
| 3   | 提交修改信息，修改成功    | 

### 商品信息修改 goodsInfo
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| setGoodsInfo | POST | 修改商品信息 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| goodsId | 商品id |           |
| goodsCategory |  |  字符串列表         |
| introduction |  |           |
| goodsname |  |           |
| goodsPrice |  |           |
| goodsStock |  |           |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 是否修改成功 | YES（0）和NO（1） |

### 商品信息显示 goodsInfo
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| getRegisteringGoodsByShopname | POST | 获得regiserStatus=0的所有商品信息 |
| getRegisteredGoodsByShopname | POST | 获得registerStatus=1的所有商品信息 |
| getRegisterationDeniedGoodsByShopname | POST | 获得registerStatus=2的所有商品信息 |
| getEditingGoodsByShopname | POST | 获得modifyStatus=1的所有商品信息 |
| getEditedGoodsByShopname | POST | 获得modifyStatus=3的所有商品信息 |
| getEditDeniedGoodsByShopname | POST | 获得modifyStatus=2的所有商品信息 |
| getInvalidGoodsByShopname | POST | 获得status=3的所有商品信息 |
| getValidGoodsByShopname | POST | 获得所有正在售卖的商品信息status=1 |

*以上函数共享以下参数*

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| shopname | 店铺名 |           |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 是否存在这种类型的商品 | YES（0）和NO（1） |
| data   | goods构成的列表       | 每个goods包含：<br>普通文本信息<br>商品状态<br>goodsCategory字符串列表<br>goodsAvatar图片列表 |

<br><br>


| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| getEditingGoodsInfo | POST | 对于处于“注册中”和“修改中”的商品，获得用户提交申请时填写的商品信息<br>注意对于修改申请而言，此处获得的信息和商品原信息不同！！！ |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| goodsId |  |           |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 商品是否处于“注册中”或“修改中” | YES（0）和NO（1） |
| data   | goods类       | 如果商品处于两种状态之一，则goods中需要包含：<br>普通文本信息<br>商品状态<br>goodsCategory字符串列表<br>goodsAvatar图片列表 |


### 商品下架 goodsOffShelve
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| goodsOffShelve | POST | 指定商品下架 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| goodsId |  |           |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 下架是否成功 | YES（0）和NO（1） |

### 商品上架 goodsOnShelve
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| goodsOnShelve | POST | 指定商品上架（恢复原来的status） |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| goodsId |  |           |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 上架是否成功 | YES（0）和NO（1） |


### 商品注册
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| goodsRegister | POST | 商品注册——文本信息传输 |
| setGoodsPicture | POST | 商品注册——图片传输 |
#### goodsRegister
参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| shopname | 店铺名 |           |
| goodsCategory | 商品类型 | 字符串列表          |
| introduction |  |           |
| goodsname | 店铺名 |           |
| goodsPrice | 价格 |           |
| goodsStock | 库存 |           |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 传输是否成功 | YES（0）和NO（1） |
| goodsId   | 商品对应的商品id（后端生成，唯一）       |  |

#### setGoodsPicture
参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| goodsId | 商品Id | 储存在formData中的goodsId中         |
| file | 商品图片（可能有多张） |  储存在formData中的file（具体可见GoodsRegister.vue:214-216）       |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 传输是否成功 | YES（0）和NO（1） |

### 撤销商品注册/修改申请 cancelApplication
无论撤销的是注册还是修改，都统一用一个接口，后端需要根据当前商品的状态确定具体的撤销类型。
- 如果当前商品处于“注册中”状态，则撤销后商品状态变更为“尚未提交注册请求”，即status=4
- 如果当前商品处于“修改中”状态，则商品恢复原信息，并且状态为“正常有效”，即status=1
- 注意此处我只写出了status的变化，registerStatus和modifyStatus请根据具体情况进行相应变更～

| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| cancelApplication | POST | 商户撤销商品申请 |

#### goodsRegister
参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| goodsId |  |           |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 撤销是否成功 | YES（0）和NO（1） |
| message  |  | 注册/修改申请已撤销 |

### 商品注册/修改申请审批 approveGoods
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| getApprovingGoods | GET | 待管理员批复的所有商品信息（包括注册/修改） |
| getApprovedGoods | GET | 管理员已经批复的所有商品信息（包括注册/修改） |

参数列表 back->front
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| data | 商品列表 | 其中每个商品除了本身的信息和状态，还需要有所属的店铺shopname          |

| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| approveGoods | POST | 管理员批准申请 |
| rejectGoods | POST | 管理员拒绝申请 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| goodsId |  |           |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 批准/拒绝是否成功 | YES（0）和NO（1） |
| message  |  | 已批准/已拒绝 |

## 个人中心
### 个人信息显示

commit示例: feat(front):userInfo add API /getUserInfo to get user info

| 接口名      | 地址             | 方法 | 功能         |
| ----------- | ---------------- | ---- | ------------ |
| getUserInfo | /api/getuserInfo | POST | 获取用户信息 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| username | 用户名 |           |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 传输是否成功 | YES（0）和NO（1） |
| data   | user类       | phone;idnum;email;account（个人账户余额） |
测试：登录->个人中心，可以显示包括头像在内的用户信息

### 个人信息修改


| 接口名      | 地址             | 方法 | 功能         |
| ----------- | ---------------- | ---- | ------------ |
| setUserInfo | /api/setUserInfo | POST | 修改用户信息 |

参数列表 front->back
| 参数名      | 含义       | 类型&备注                      |
| ----------- | ---------- | ------------------------------ |
| oldusername | 旧用户名   |                                |
| newusername | 新用户名   |                                |
| newpassword | 新密码     |                                |
| newphone    | 新手机号   |                                |
| newemail    | 新邮箱     |                                |
| ~~idnum~~   | ~~身份证~~ | 文档提示：身份证号默认无法修改 |

参数列表 back->front

| 参数名  | 含义         | 类型&备注                                                    |
| ------- | ------------ | ------------------------------------------------------------ |
| state   | 传输是否成功 | YES（0）和NO（1）                                            |
| message | 提示信息     | 修改失败：用户名/手机号/邮箱/身份证已存在；如果有多种错误，则拼接成同一个字符串，以“；”分割<br>修改成功！ |

测试：登录->个人中心，点击修改后可以成功更新后台信息（包括用户头像在內）

### 头像上传

用户上传头像1.0: 图片传输(uploadImg)

commit示例: feat(front):uploadImg add API /setUserAvatar to post img only   

| 接口名        | 地址               | 方法 | 功能         |
| ------------- | ------------------ | ---- | ------------ |
| setUserAvatar | /api/setUserAvatar | POST | 设置用户头像 |

参数列表 front->back

| 参数名   | 含义             | 类型&备注                                                    |
| -------- | ---------------- | ------------------------------------------------------------ |
| avatar   | 头像的二进制数据 | formData（图像以二进制的形式储存在avatar的'image'中，username储存在avatar的'username'），传输的content-type设置为'multipart/form-data' |

参数列表 back->front


|  参数名  |  含义  |  类型&备注  |
| ---------- | ---------- | -------- |
|  state  |  传输是否成功  | 依旧用NO（1）和OK（3）代表失败和成功 |
|  message  |  提示信息  | 传输成功时：“用户头像上传成功”<br> 传输失败时："上传失败，请重试"   |

 测试:在用户信息显示处统一测试

<br>

### 头像显示
用户头像显示1.0: 图片传输(downloadImg)

commit示例: feat(front):downloadImg add API /getUserAvatar to get img only  

| 接口名        | 地址               | 方法 | 功能         |
| ------------- | ------------------ | ---- | ------------ |
| getUserAvatar | /api/getUserAvatar | POST | 返回用户头像 |

参数列表 front->back

|  参数名  |  含义  |  类型&备注  |
| ---------- | ---------- | -------- |
|  username  |  用户名  |    |

参数列表 back->front

| 参数名 | 含义                   | 类型&备注                                       |
| ------ | ---------------------- | ----------------------------------------------- |
| state  | 是否有头像             |                                                 |
| avatar | base64形式的图像数据 | 
 |


 测试：在用户信息修改处统一测试



## 版本管理

|   功能名称   |  备注  |  
| ---------- | ---------- | 
|  uploadImg  | 图片从前端传输至后端 |  
|  downloadImg  | 图片从后端传输至前端 |  





## commit 规范
基本格式 类型(frontend/backend)：具体功能 “注释”

#### （1）类型
- feat：新增功能
- fix：修复问题
- docs：修改文档
- style：修改代码风格
- test：新增或修改测试代码
- perf：优化性能
<br>在括号中指明前后端
#### （2）具体功能
以接口为基本单位，在接口文档中除了确定接口函数，还需要指定commit时对应的功能名称，比如“上传图片”对应的是uploadImg<br>
具体的commit单位前后端分别确定，但考虑到我们是以接口为基本单位确定具体功能的名称，所以至少在完成一个接口功能后commit一次<br>
因此希望大家完成一个功能就commit一次！！！
#### （3）注释
**基本功能的完成需要使用统一的注释**，比如

- 前端完成与后端交互的函数，注释模版为“add API to post/get”。具体的使用例子为"add API '/login' to post user info"
- README添加接口信息，注释模版为“add API info for XXX”。具体的使用例子为"add API info for getUserAvatar"


【【【【【【自己写一下自己的模版自己写一下自己的模版！！！！】】】】】】



后端（施鸿翔）：

- 某一部分功能正在开发中，注释：“xxx + 日期 + indevelopment”

  如：“feat(back): getUserInfo 4.2 in development”

- 完成某一部分功能，注释为“ xxx done”

  如“feat(back) : getUserInfo done”
  
  前端（于可欣）：

- 完成某一新页面的框架创建，注释：“WebName built”

  如：“feat(front): GoodsManage bulit”

- 完成某一页面的组件添加或修改，注释为“ WebName ComponetFName changed/added”

  如“feat(front) : GoodsManage jumpToRegister_btn added”
  
- 完成某一页面的格式调整，注释：“WebName layout changed ”

  如：“feat(front): GoodsManage layout changed”
