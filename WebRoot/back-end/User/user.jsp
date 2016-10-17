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

<title>用户管理</title>
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
	<input type="hidden" value="${sessionScope.user_utype }"
		id="origin_utype" />
	<form class="form-inline definewidth m20" action="UserLoadServlet"
		method="post">
		员工姓名： <input type="text" name="uname" id="uname"
			class="abc input-default" value="${sessionScope.user_uname }"
			style="width: 100px;">&nbsp;&nbsp;用户类别：<select name="utype"
			style="width: 100px;"><option value="0">全部</option>
			<option value="1">管理员</option>
			<option value="2">员工</option>
			<option value="3">客户</option></select>&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		<a href="UserLoadServlet?uname=&utype=0" class="btn btn-primary">显示所有</a>
		<button type="button" class="btn btn-success" id="addnew">新增用户</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th>编号</th>
			<th>用户名称</th>
			<th>用户类型</th>
			<th>联系电话</th>
			<th>操作</th>
		</tr>
		<c:forEach var="ur" items="${sessionScope.user_list }">
			<tr>
				<td>${ur.uid }</td>
				<td>${ur.uname }</td>
				<td>${ur.utype==1?"管理员":ur.utype==2?"员工":"客户" }</td>
				<td>${ur.utelphone }</td>
				<td><a href="UserUpdateServlet?uid=${ur.uid }">详情</a>&nbsp;|&nbsp;<a
					href="javascript:del(${ur.uid });">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="inline pull-right page">
		${sessionScope.user_total } 条记录 ${sessionScope.user_page
		}/${sessionScope.user_total_page } 页
		<%!int page_cur, page_all;%>
		<%
			page_cur = (Integer) session.getAttribute("user_page");
			page_all = (Integer) session.getAttribute("user_total_page");
		%>


		<%
			if (page_cur > 1) {
		%>
		<a href='UserLoadServlet?page=1'>首页</a>
		<%
			}
		%>

		<%
			if (page_cur > 5) {
		%>
		<a href='UserLoadServlet?page=<%=page_cur - 5%>'>上5页</a>
		<%
			}
		%>

		<%
			if (page_cur > 1) {
		%>
		<a href='UserLoadServlet?page=<%=page_cur - 1%>'>上一页</a>
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
		<a href='UserLoadServlet?page=<%=i%>'><%=i%></a>
		<%
			}
			}
		%>

		<%
			if (page_cur < page_all) {
		%>
		<a href='UserLoadServlet?page=<%=page_cur + 1%>'>下一页</a>
		<%
			}
		%>

		<%
			if (page_all - page_cur > 5) {
		%>
		<a href='UserLoadServlet?page=<%=page_cur + 5%>'>下5页</a>
		<%
			}
		%>

		<%
			if (page_cur < page_all) {
		%>
		<a href='UserLoadServlet?page=<%=page_all%>'>最后一页</a>
		<%
			}
		%>
	</div>

	<script>
		$(function() {
			$('#addnew').click(function() {
				window.location.href = "back-end/User/add.jsp";
			});
			// select
			if ($('#origin_utype').val() > 0) {
				$('select[name="utype"]').val($('#origin_utype').val());
			}
		});

		function del(id) {
			if (confirm("确定要删除吗？")) {
				url = "UserDeleteServlet?uid=" + id;
				window.location.href = url;
			}
		};
	</script>
</body>
</html>
