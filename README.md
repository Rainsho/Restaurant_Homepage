# Restaurant_Homepage

An Restaurant Homepage with easy back-end management function written with JSP.

For personal practice only.

效果测试：http://rest.rainsho.cc

AWS EC2服务器，位于海外。可能速度比较慢，请耐心等待（异步上传过程中可能无明显提示）。  
需要后台测试账号的请私信联系。

## 前台模板基于H5编写，包含主页、菜单、动态、厨师、预订、画廊界面。

## 后台页面基于JSP编写
主要包含以下功能
### 主页管理
设置轮播banner内容，主页slogan及前端6大页面展示图片。  
更新使用AJAX异步提交，图片可上传或在已有图片库（每次增加10张异步加载服务器内图片）选择。
### 菜单管理
菜品的CRUD，类别及图片设置为外键，只能在已有项目中选择或新增。
### 动态管理
动态（新闻）的CRUD，支持markdown语法，前台默认显示最新动态，目录采用AJAX异步加载模式。
### 员工管理
员工的CRUD，其中员工type分为（1=管理员，2=员工、3=客户，默认值为3）。  
不同type员工对应不同信息或字段录入，其中员工可设置图片，并设置是否在前端页面显示。
### 预订管理
预订功能由客户在前台提交，后台主要是查询功能，基于easyUI的calendar选择起止日期。
### 画廊管理
上传新图片，设置图片是否在前台画廊界面展示。设置按钮由图片的hover事件触发，在IE下可能有部分异常。

## 扩展新功能
后台菜单管理加入菜品类别的增改功能。  
前台菜单页面加入类似购物车点餐功能，提交后可在后台备餐及结付操作。  
后台订单管理增加小计功能，与筛选功能联动。  
前台增加部分动画效果（菜单页面点餐、画廊图片放大显示等）。  
后台页面增加登陆验证，后登陆用户将使已登陆相同用户登陆信息丢失。  
修改登陆验证码规则，仅在两次登陆失败后要求验证码验证。
