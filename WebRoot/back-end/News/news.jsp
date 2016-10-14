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

<title>动态管理</title>
<link rel="stylesheet" type="text/css" href="back-end/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="back-end/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="back-end/Css/style.css" />
<script type="text/javascript" src="back-end/Js/jquery.js"></script>
<script type="text/javascript" src="back-end/Js/bootstrap.js"></script>
<script type="text/javascript" src="back-end/Js/ckform.js"></script>
<script type="text/javascript" src="back-end/Js/common.js"></script>

<!-- markdown function -->
<script type="text/javascript" src="back-end/markdown/marked.min.js"></script>

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
	<form class="form-inline definewidth m20" action="NewsFilterServlet"
		method="post">
		作者： <input type="text" name="nauthor" id="nauthor"
			class="abc input-default">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增动态</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th>编号</th>
			<th>作者</th>
			<th>发表时间</th>
			<th width="60%">正文</th>
			<th>管理操作</th>
		</tr>
		<c:forEach var="news" items="${sessionScope.news_list }">
			<tr>
				<td>${news.nid }</td>
				<td>${news.nauthor }</td>
				<td>${news.fmtDate() }</td>
				<td class="ncontent">${news.ncontent }</td>
				<td><a href="EditNewsServlet?nid=${news.nid }">编辑</a>&nbsp;|&nbsp;<a
					href="javascript:del(${news.nid });">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="inline pull-right page">
		${sessionScope.news_list_all.size() } 条记录 ${sessionScope.news_page
		}/${sessionScope.news_page_all } 页
		<%!int page_cur, page_all;%>
		<%
			page_cur = (Integer) session.getAttribute("news_page");
			page_all = (Integer) session.getAttribute("news_page_all");
		%>


		<%
			if (page_cur > 1) {
		%>
		<a href='NewsPageServlet?page=1'>首页</a>
		<%
			}
		%>

		<%
			if (page_cur > 5) {
		%>
		<a href='NewsPageServlet?page=<%=page_cur - 5%>'>上5页</a>
		<%
			}
		%>

		<%
			if (page_cur > 1) {
		%>
		<a href='NewsPageServlet?page=<%=page_cur - 1%>'>上一页</a>
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
		<a href='NewsPageServlet?page=<%=i%>'><%=i%></a>
		<%
			}
			}
		%>

		<%
			if (page_cur < page_all) {
		%>
		<a href='NewsPageServlet?page=<%=page_cur + 1%>'>下一页</a>
		<%
			}
		%>

		<%
			if (page_all - page_cur > 5) {
		%>
		<a href='NewsPageServlet?page=<%=page_cur + 5%>'>下5页</a>
		<%
			}
		%>

		<%
			if (page_cur < page_all) {
		%>
		<a href='NewsPageServlet?page=<%=page_all%>'>最后一页</a>
		<%
			}
		%>


	</div>

	<script>
		$(function() {
			$('#addnew').click(function() {
				window.location.href = "back-end/News/add.jsp";
			});
		});

		function del(id) {
			if (confirm("确定要删除吗？")) {
				var url = "DeleteNewsServlet?nid=" + id;
				window.location.href = url;
			}
		};

		//markdown support
		$('.ncontent').each(function() {
			$(this).html(marked($(this).text()));
		});
	</script>
</body>
</html>
