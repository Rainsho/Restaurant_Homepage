# Restaurant_Homepage
An Restaurant Homepage with easy back-end management function written with JSP.

For personal practice only.

前台模板基于H5编写，包含主页、菜单、动态、厨师、预订、画廊界面。

## 后台页面基于JSP编写。
包含以下功能
### 主页管理
设置轮播banner内容，主页slogan及前端6大页面展示图片。  
更新使用AJAX异步提交，图片可上传或在已有图片库内选择。
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