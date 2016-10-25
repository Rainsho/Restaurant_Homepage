<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/back-end/login_check.jsp" %>
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

<title>菜单管理</title>
<link rel="stylesheet" type="text/css" href="back-end/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="back-end/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="back-end/Css/style.css" />
<script type="text/javascript" src="back-end/Js/jquery.js"></script>
<script type="text/javascript" src="back-end/Js/bootstrap.js"></script>
<script type="text/javascript" src="back-end/Js/ckform.js"></script>
<script type="text/javascript" src="back-end/Js/common.js"></script>

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
</style>

</head>

<body>
	<form class="form-inline definewidth m20" action="FoodPageServlet"
		method="post">
		菜品名称： <input type="text" name="fname" id="fname"
			class="abc input-default" value="${sessionScope.food_filter }">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		<a href="FoodPageServlet?fname=" class="btn btn-primary">显示所有</a>
		<button type="button" class="btn btn-success" id="addnew">新增菜品</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th>编号</th>
			<th>菜品类别</th>
			<th>菜品名称</th>
			<th width="40%">菜品描述</th>
			<th>菜品单价</th>
			<th>菜品图片</th>
			<th>管理操作</th>
		</tr>
		<c:forEach var="fd" items="${sessionScope.food_list }">
			<tr>
				<td>${fd.fid }</td>
				<td>${fd.foodtype.ftname }</td>
				<td>${fd.fname }</td>
				<td>${fd.fdetial }</td>
				<td>${fd.fprice }</td>
				<td><img width="80" alt="${fd.fname }"
					src="front-end/${fd.picture.ppath }"></td>
				<td><a href="FoodInfoServlet?fid=${fd.fid }">编辑</a>&nbsp;|&nbsp;<a
					href="javascript:del(${fd.fid });">删除</a></td>
			</tr>
		</c:forEach>


	</table>
	<div class="inline pull-right page">
		${sessionScope.food_total } 条记录 ${sessionScope.food_page
		}/${sessionScope.food_total_page } 页
		<%!int page_cur, page_all;%>
		<%
			page_cur = (Integer) session.getAttribute("food_page");
			page_all = (Integer) session.getAttribute("food_total_page");
		%>


		<%
			if (page_cur > 1) {
		%>
		<a href='FoodPageServlet?page=1'>首页</a>
		<%
			}
		%>

		<%
			if (page_cur > 5) {
		%>
		<a href='FoodPageServlet?page=<%=page_cur - 5%>'>上5页</a>
		<%
			}
		%>

		<%
			if (page_cur > 1) {
		%>
		<a href='FoodPageServlet?page=<%=page_cur - 1%>'>上一页</a>
		<%
			}
		%>

		<%
			for (int i = (int) Math.ceil(page_cur / 5.0) * 5 - 4; i <= Math
					.min(page_all, Math.ceil(page_cur / 5.0) * 5); i++) {
				if (i == page_cur) {
		%>
		<span class='current'><%=i%></span>
		<%
			} else {
		%>
		<a href='FoodPageServlet?page=<%=i%>'><%=i%></a>
		<%
			}
			}
		%>

		<%
			if (page_cur < page_all) {
		%>
		<a href='FoodPageServlet?page=<%=page_cur + 1%>'>下一页</a>
		<%
			}
		%>

		<%
			if (page_all - page_cur > 5) {
		%>
		<a href='FoodPageServlet?page=<%=page_cur + 5%>'>下5页</a>
		<%
			}
		%>

		<%
			if (page_cur < page_all) {
		%>
		<a href='FoodPageServlet?page=<%=page_all%>'>最后一页</a>
		<%
			}
		%>
	</div>

	<script>
		$(function() {
			$('#addnew').click(function() {
				window.location.href = "FoodAddServlet";
			});
		});

		function del(id) {
			if (confirm("确定要删除吗？")) {
				var url = "FoodDeleteServlet?fid=" + id;
				window.location.href = url;
			}
		};
	</script>
</body>
</html>
