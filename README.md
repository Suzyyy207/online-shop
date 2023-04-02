# lab_3

## 个人中心
### 个人信息显示

commit示例: feat(front):userInfo add API /getUserInfo to get user info

| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| getUserInfo | POST | 获取用户信息 |

参数列表 front->back
| 参数名   | 含义   | 类型&备注 |
| -------- | ------ | --------- |
| username | 用户名 |           |

参数列表 back->front

| 参数名 | 含义         | 类型&备注         |
| ------ | ------------ | ----------------- |
| state  | 传输是否成功 | YES（0）和NO（1） |
| data   | user类       | phone;idnum;email |

测试

| 前端测试路径            | 测试方法                                                     |
| ----------------------- | ------------------------------------------------------------ |
| localhost:端口/UserInfo | 修改UserInfo:93的username为指定用户后，可以在改页面上看到用户个人信息 |



### 个人信息修改


| 接口名      | 方法 | 功能         |
| ----------- | ---- | ------------ |
| setUserInfo | POST | 修改用户信息 |

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

测试


| 前端测试路径            | 测试方法                                                     |
| ----------------------- | ------------------------------------------------------------ |
| localhost:端口/UserInfo | 修改UserInfo:93的username为指定用户后，可以在改页面上看到用户个人信息；点击“修改”按钮并修改后，可以正确修改 |



### 头像上传

用户上传头像1.0: 图片传输(uploadImg)

commit示例: feat(front):uploadImg add API /setUserAvatar to post img only   

| 接口名        | 方法 | 功能         |
| ------------- | ---- | ------------ |
| setUserAvatar | POST | 设置用户头像 |

参数列表 front->back

| 参数名   | 含义             | 类型&备注                                                    |
| -------- | ---------------- | ------------------------------------------------------------ |
| avatar   | 头像的二进制数据 | formData（图像以二进制的形式储存在avatar的'image'中，username储存在avatar的'username'），传输的content-type设置为'multipart/form-data' |

参数列表 back->front


|  参数名  |  含义  |  类型&备注  |
| ---------- | ---------- | -------- |
|  state  |  传输是否成功  | 依旧用NO（1）和OK（3）代表失败和成功 |
|  message  |  提示信息  | 传输成功时：“用户头像上传成功”<br> 传输失败时："上传失败，请重试"   |

 测试 :确定图像传输成功,这一阶段只需要测试图片能否传到后端，不涉及具体的用户名等

|    |                                                    |
| ---------- | ---------- |
|  前端测试路径  |  localhost:端口/UploadImg  |
|  测试方法  |  点击上传文件后，确定后端数据库是否成功收到图片数据  |

<br>

### 头像显示
用户头像显示1.0: 图片传输(downloadImg)

commit示例: feat(front):downloadImg add API /getUserAvatar to get img only  

| 接口名        | 方法 | 功能         |
| ------------- | ---- | ------------ |
| getUserAvatar | POST | 返回用户头像 |

参数列表 front->back

|  参数名  |  含义  |  类型&备注  |
| ---------- | ---------- | -------- |
|  username  |  用户名  |    |

参数列表 back->front

| 参数名 | 含义                   | 类型&备注                                       |
| ------ | ---------------------- | ----------------------------------------------- |
| state  | 是否有头像             |                                                 |
| avatar | dataform形式的图像数据 | 具体来说，二进制数据应该储存在avatar的'image'中 |


 测试 确定图像传输成功

|    |    |  
| ---------- | ---------- | 
|  前端测试路径  |  localhost:端口/UploadImg  |    
|  测试方法  |  将UploadImg.vue:34 的username赋值成指定的username，如果页面显示该用户的头像，则传输成功  | 



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
