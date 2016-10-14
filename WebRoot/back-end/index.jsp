<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>网站后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="back-end/assets/css/dpl-min.css" rel="stylesheet"
	type="text/css" />
<link href="back-end/assets/css/bui-min.css" rel="stylesheet"
	type="text/css" />
<link href="back-end/assets/css/main-min.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<%
		String ck_usr = null;
		Cookie[] ck_list = request.getCookies();
		
		if(ck_list!=null){
		 	for (Cookie x : ck_list) {
				if (x.getName().equals("usr")) {
					ck_usr = x.getValue();
				}
			}
		}

		if (ck_usr == null && request.getAttribute("usr") == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	<div class="header">

		<div class="dl-title">
			<!--<img src="/chinapost/Public/assets/img/top.png">-->
		</div>

		<div class="dl-log">
			欢迎您，<span class="dl-log-user">${requestScope.usr.getUname()}</span><a
				href="back-end/login.jsp" title="退出系统" class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">系统管理</div></li>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">

		</ul>
	</div>
	<script type="text/javascript"
		src="back-end/assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="back-end/assets/js/bui-min.js"></script>
	<script type="text/javascript"
		src="back-end/assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="back-end/assets/js/config-min.js"></script>
	<script>
		BUI.use('common/main', function() {
			var config = [ {
				id : '1',
				menu : [ {
					text : '系统管理',
					items : [ {
						id : '21',
						text : '主页管理',
						href : 'back-end/Node/index.html'
					}, {
						id : '22',
						text : '菜单管理',
						href : 'FoodPageServlet'
					}, {
						id : '23',
						text : '动态管理',
						href : 'AllNewsServlet'
					}, {
						id : '24',
						text : '员工管理',
						href : 'back-end/Node/index.html'
					}, {
						id : '25',
						text : '预订管理',
						href : 'back-end/Node/index.html'
					}, {
						id : '26',
						text : '画廊管理',
						href : 'PicAllServlet'
					} ]
				} ]
			} ];
			new PageUtil.MainPage({
				modulesConfig : config
			});
		});
	</script>
</body>
</html>