# lab_3

## 面向用户的店铺&商品展示

### 获取店铺信息 getShopInfoByShopname
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| getShopInfoByShopname | POST | 获取指定店铺信息 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| shopname |  |           |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  |  |   |
| data  | shop类 | 包括店铺头像avatar和其他店铺相关信息  |

### 商品展示 getGoodsInfoByGoodsId

| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| getGoodsInfoByGoodsId | POST | 获取指定商品 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| goodsId |  |           |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  |  |   |
| data  | 商品类 | 包括goodsAvatar列表，favorites等  |

### 添加商品至购物车 addToCart
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| addToCart | POST | 添加指定商品至购物车 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| username | 用户名 |           |
| goodsId | 商品id |           |
| num | 添加的商品个数 |           |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 是否添加成功 |   |

### 收藏商品 favorites
*新增商品属性：收藏量favorites，需要在返回商品类相关的函数中同时返回该属性！！！*
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| addToFavorites | POST | 收藏商品 |
| Unfavorite | POST | 取消收藏商品 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| username | 用户名 |           |
| goodsId | 商品id |           |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 是否收藏成功 |   |

| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| isFavorited | POST | 是否已经收藏商品 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| username | 用户名 |           |
| goodsId | 商品id |           |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 是否已经收藏 |   |


## 管理员批复 adminApproval
### 店铺申请批复 
店铺注册申请/店铺修改申请（待完善）/店铺删除申请批复都用同一接口，就是原来的registerApproved和registerRejected，在此处更名为shopApplicationApproved和shopApplicationRejected

#### 同意申请 shopApplicationApproved
在店铺is_admitted=0时，店铺变更为“正常店铺”<br>
在店铺is_admitted=3时，店铺信息删除，对应商户不与任何店铺关联<br>
由于此处可以直接通过

| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| shopApplicationApproved | POST | 同意店铺申请 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| shopname | 店名 |           |
| approveType | 类型 |  与is_admitted同步<br>0:同意注册申请<br>3：同意删除申请         |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 批复是否提交成功 | YES（0）和NO（1） |
| message   | 提示信息       | 批复成功<br>批复失败，请重试 |

#### 拒绝申请 shopApplicationRejected
在店铺is_admitted=0时，店铺状态变为1<br>
在店铺is_admitted=3时，店铺状态变为4<br>
由于此处可以直接通过
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| shopApplicationRejected | POST | 拒绝店铺申请 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| shopname | 店名 |           |
| rejectType | 类型 |  与is_admitted同步<br>0:拒绝注册申请<br>3：拒绝删除申请         |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 批复是否提交成功 | YES（0）和NO（1） |
| message   | 提示信息       | 批复成功<br>批复失败，请重试 |

 ### 商品申请批复 goodsApproval
 #### 获得商品信息
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| getApprovingGoods | GET | 待管理员批复的所有商品信息（包括注册/修改） |

参数列表 back->front
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| data | 商品列表 | 其中每个商品除了本身的信息和状态，还需要有所属的店铺shopname          |

#### 管理员同意商品申请 goodsApplicationApproved
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| goodsApplicationApproved | POST | 管理员批准申请 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| goodsId |  |           |
| approveType | 批复类型 | 与goods.status值相同，后端需自行判断申请类型并做出相应的状态变更          |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 批准是否成功 | YES（0）和NO（1） |
| message  |  | 已批准<br>批准失败，请重试 |

#### 管理员拒绝商品申请 goodsApplicationRejected
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| goodsApplicationRejected | POST | 管理员拒绝申请 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| goodsId |  |           |
| approveType | 批复类型 | 与goods.status值相同，后端需自行判断申请类型并做出相应的状态变更          |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 批准是否成功 | YES（0）和NO（1） |
| message  |  | 已拒绝<br>拒绝失败，请重试 |


## 店铺管理
### 商户店铺删除 
功能概述：商户可以删除本商店，在“我的店铺”的店铺信息板块提供删除按钮。
- 提交删除申请时：
  -- 前端利用shopUnregister将shopname提交至后端
  -- 后端检查商店是否有订单未完成，如果有则不允许提交申请，并且在message中提示
  -- 如果没有未完成的订单，则申请提交成功，管理员页面会出现该店铺的删除申请
- 商户提交删除申请后：
  -- 商户无法再对商品进行操作（包括商品注册、商品下架等等）
  -- 在“我的店铺”的店铺信息板块，显示商店当前处于“删除审核”状态
  -- 原来的删除按钮失效
  -- 提供“撤销删除申请”的按钮
  -- 主页无法看到店铺信息
  -- 用户购物车中该店铺的申请失效，显示“商店处于异常状态”
- 商户撤销申请
  -- 商店状态恢复，商户可以进行商品管理
  -- 管理员处的商店删除申请消失
- 管理员同意删除申请
  -- 商户点击“我的店铺”，将出现商店注册页面
- 管理员拒绝删除申请
  -- 商店状态恢复
  -- TODO：后续需要类似“收件箱”功能使得用户收到管理员的批复
  商户提交删除信息后，需要管理员进行审核

#### 店铺删除shopUnregister
| 接口名      | 地址 | 方法 | 功能         |
| ----------- | ---- | ------------ | ------------ |
| shopUnregister | /api/shopUnregister | POST | 申请删除店铺 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| shopname | 店名 |           |

参数列表 back->front
| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 申请是否提交成功 | YES（0）和NO（1）<br> 后端需要根据当前商店的状态判断是否可以提交申请，比如是否有未完成的订单等 |
| message   | 提示信息       |提交成功，请等待管理员审核<br>提交失败，当前仍有未完成订单<br>... |

由于店铺提交删除申请后，处于异常状态，因此需要对getMyShopInfoByUsername接口的back->front参数返回的shop类中，重新设计"is_admitted"参数，具体值如下：
| 参数值 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| 0   | 注册审核中       |  |
| 1  | 正常店铺 | |
| 2   | 管理员拒绝注册请求       |  |
| 3   | 删除审核中       |  |
| 4   | 管理员拒绝删除请求       | 无论是3还是4，店铺都属于“删除状态”，商家无法修改本店铺的信息、商品等，用户购物车中该商店的商品也处于异常状态 |
*需要确定一下getRegisteredShop这个函数只返回is_admitted=1的店铺！！！*

#### 撤销店铺注册/删除申请cancelRegister
在店铺is_admitted=0/2时，可以“撤销注册申请”，不需要管理员审批；提交后商店注册信息清空，该商户不与任何店铺关联<br>
在店铺is_admitted=3/4时，都可以“撤销删除申请”，不需要管理员审批；提交后店铺状态变为1<br>
由于此处可以直接通过
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| cancelRegister | POST | 撤销删除/注册店铺申请 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| shopname | 店名 |           |
| cancelType | 类型 |  与is_admitted同步<br>0:撤销注册申请<br>3/4：撤销删除申请         |


参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 撤销是否提交成功 | YES（0）和NO（1） |
| message   | 提示信息       | 撤销成功<br>撤销失败 |

## 个人账户系统 
### 个人账户展示
测试：个人中心可以正确显示账户余额（具体参数传递方法见/getUserInfo）

### 个人账户充值
| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| recharge | POST | 个人账户充值 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| username |  | String |
| accountType | 账户类型 | INT 普通买家：1(只有一个账户)；商家：1(privateAccount个人账户) 2（shopAccount商店账户）管理员：1(profitAccount商城利润账户) 2(intermediaryAccount商城购物中间账户) |
| credit | 充值金额 | String(转换不会丢失精度)                                     |

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

#### 获取指定注册中/修改中商品信息
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
| data   | user类       | phone;idnum;email; privateAccount（个人账户余额） |
|测试：登录->个人中心，可以显示包括头像在内的用户信息|||

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
