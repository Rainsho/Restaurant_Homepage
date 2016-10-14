<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>新增菜品</title>
<link rel="stylesheet" type="text/css" href="back-end/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="back-end/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="back-end/Css/style.css" />
<script type="text/javascript" src="back-end/Js/jquery.js"></script>
<script type="text/javascript" src="back-end/Js/bootstrap.js"></script>
<script type="text/javascript" src="back-end/Js/ckform.js"></script>
<script type="text/javascript" src="back-end/Js/common.js"></script>
<!-- 这个页面用的js -->
<script type="text/javascript" src="back-end/js_me/food_add.js"></script>
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) { /* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}

span {
	margin-left: 5px;
}

a:hover {
	text-decoration: none;
}

#pic_lib {
	background-color: white;
	width: 50%;
	height: 450px;
	position: absolute;
	top: 70px;
	left: 12%;
	width: 50%;
	padding: 5px;
	border: 1px solid #dddddd;
}

#pic_display {
	height: 380px;
	overflow-y: scroll;
	border: 1px solid #dddddd;
	margin-bottom: 5px;
}

.pic_in_lib {
	float: left;
	width: 100px;
	margin: 1px;
	border: 1px solid white;
}

.red_border {
	border: 1px solid red;
}

.pic_select {
	opacity: 0.5;
}
</style>

</head>

<body>
	<form action="FoodAddPostServlet" method="post"
		onsubmit="return checkform();">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">菜品类别</td>
				<td><select name="ftid" style="width: 206px">
						<c:forEach var="ft" items="${sessionScope.type_list }">
							<option value="${ft.ftid }">${ft.ftname }</option>
						</c:forEach>
				</select>&nbsp;&nbsp;<a id="show_add_type" style="cursor: hand;">添加类别&gt;&gt;</a><span
					id="span_add_type"><input type="text" id="txt_add_type"
						style="width: 120px;" />&nbsp;&nbsp;<a class="btn btn-primary"
						id="add_type">新增类别</a></span></td>
			</tr>
			<tr>
				<td class="tableleft">菜品名称</td>
				<td><input type="text" name="fname" /></td>
			</tr>
			<tr>
				<td class="tableleft">菜品描述</td>
				<td><textarea name="fdetial" rows=5 /></textarea></td>
			</tr>
			<tr>
				<td class="tableleft">菜品单价</td>
				<td><input type="text" name="fprice" /></td>
			</tr>
			<tr>
				<td class="tableleft">菜品图片</td>
				<td><img width="206" src="" id="pic_show"> <span><a
						id="show_pic_lib" style="cursor: hand;">选取图片&gt;&gt;</a> </span> <input
					type="hidden" name="pid" value="" /></td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" type="button">新增菜品</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid"
						id="backid">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
	<div id="pic_lib" class="pic_lib">
		<form name="upload_pic" method="post" enctype="multipart/form-data" onsubmit="return uploadpic();">
			<input type="file" name="file" id="file" /><input type="submit" value="上传" /><span id="back_msg"></span>
		</form>
		<div id="pic_display">
			<a id="more_pic"
				style="cursor: hand;width: 100%;display: inline-block;text-align: center;font-size: 16px">加载更多</a>
		</div>
		<a type="button" class="btn btn-primary" id="pic_lib_confirm"
			style="width: 50px;">确定</a> <a type="button" class="btn btn-success"
			id="pic_lib_cannel" style="width: 50px;">返回</a>
	</div>
</body>

</html>
