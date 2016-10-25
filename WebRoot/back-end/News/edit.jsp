<%@ page language="java" pageEncoding="UTF-8"%>
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

<title>修改动态</title>
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

input,textarea {
	width: 50%;
}
</style>

</head>

<body>
	<form action="EditNewsServlet2" method="post">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td class="tableleft">主题</td>
				<td><input type="text" name="ntag"
					value="${sessionScope.news.ntag }" /></td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">作者</td>
				<td><input type="text" name="nauthor"
					value="${sessionScope.news.nauthor }" /></td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">发表时间</td>
				<td><input type="text" name="ndate"
					value="${sessionScope.news.fmtDate() }" /></td>
			</tr>
			<tr>
				<td class="tableleft">正文</td>
				<td><textarea name="ncontent" rows=10 />${sessionScope.news.ncontent }</textarea></td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" type="button">修改动态</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid"
						id="backid">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
	<script>
		$(function() {
			$('#backid').click(function() {
				window.location.href = "back-end/News/news.jsp";
			});
		});
	</script>
</body>
</html>
