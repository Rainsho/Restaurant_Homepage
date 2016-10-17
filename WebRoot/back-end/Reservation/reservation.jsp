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

<title>预订管理</title>
<link rel="stylesheet" type="text/css" href="back-end/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="back-end/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="back-end/Css/style.css" />
<script type="text/javascript" src="back-end/Js/jquery.js"></script>
<script type="text/javascript" src="back-end/Js/bootstrap.js"></script>
<script type="text/javascript" src="back-end/Js/ckform.js"></script>
<script type="text/javascript" src="back-end/Js/common.js"></script>

<!-- easy_ui -->
<script type="text/javascript" src="back-end/easyUI/jquery.min.js"></script>
<!-- easyui的js文件，建议使用版本自带jquery文件 -->
<script type="text/javascript"
	src="back-end/easyUI/jquery.easyui.min.js"></script>
<!-- easyui的js文件 -->
<script type="text/javascript"
	src="back-end/easyUI/locale/easyui-lang-zh_CN.js"></script>
<!-- 本地中文字符js文件 -->
<link rel="stylesheet" type="text/css"
	href="back-end/easyUI/themes/default/easyui.css">
<!-- easyUI默认的样式 -->
<link rel="stylesheet" type="text/css"
	href="back-end/easyUI/themes/icon.css">
<!-- easyUI内置图标样式 -->

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

.div_cal {
	position: relative;
	display: inline;
	text-align: center;
}

#cal1,#cal2 {
	background-color: white;
	width: 180px;
	height: 220px;
	display: inline-block;
	position: absolute;
	left: -120px;
	top: 25px;
}

#dd1,#dd2 {
	height: 160px;
	display: inline-block;
	margin-bottom: 5px;
}
</style>

</head>

<body>
	<form class="form-inline definewidth m20" action="ResLoadServlet"
		method="post">
		起始日期： <input type="text" name="date_s" id="date_s"
			class="abc input-default" value="${sessionScope.res_date_s }"
			style="width: 120px">
		<div class="div_cal">
			<div id="cal1">
				<div id="dd1"></div>
				<a class="btn btn-success" id="btn_cal1">选择日期</a>&nbsp;&nbsp; <a
					class="btn btn-success btn_cal_cancel">关闭</a>
			</div>
		</div>
		&nbsp;&nbsp;截止日期： <input type="text" name="date_t" id="date_t"
			class="abc input-default" value="${sessionScope.res_date_t }"
			style="width: 120px;">
		<div class="div_cal">
			<div id="cal2">
				<div id="dd2"></div>
				<a class="btn btn-success" id="btn_cal2">选择日期</a>&nbsp;&nbsp; <a
					class="btn btn-success btn_cal_cancel">关闭</a>
			</div>
		</div>
		&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		<a href="ResLoadServlet?date_s=&date_t=" class="btn btn-primary">显示所有</a>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th>预订编号</th>
			<th>预订时间</th>
			<th>预订位数</th>
			<th>预订标题</th>
			<th>预订信息</th>
			<th>用户姓名</th>
			<th>联系电话</th>
		</tr>
		<c:forEach var="res" items="${sessionScope.res_list }">
			<tr>
				<td>${res.resid }</td>
				<td>${res.fmtDate() }</td>
				<td>${res.resseat }</td>
				<td>${res.restitle }</td>
				<td>${res.resinfo }</td>
				<td>${res.user.uname }</td>
				<td>${res.user.utelphone }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="inline pull-right page">
		${sessionScope.res_total } 条记录 ${sessionScope.res_page
		}/${sessionScope.res_total_page } 页
		<%!int page_cur, page_all;%>
		<%
			page_cur = (Integer) session.getAttribute("res_page");
			page_all = (Integer) session.getAttribute("res_total_page");
		%>


		<%
			if (page_cur > 1) {
		%>
		<a href='ResLoadServlet?page=1'>首页</a>
		<%
			}
		%>

		<%
			if (page_cur > 5) {
		%>
		<a href='ResLoadServlet?page=<%=page_cur - 5%>'>上5页</a>
		<%
			}
		%>

		<%
			if (page_cur > 1) {
		%>
		<a href='ResLoadServlet?page=<%=page_cur - 1%>'>上一页</a>
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
		<a href='ResLoadServlet?page=<%=i%>'><%=i%></a>
		<%
			}
			}
		%>

		<%
			if (page_cur < page_all) {
		%>
		<a href='ResLoadServlet?page=<%=page_cur + 1%>'>下一页</a>
		<%
			}
		%>

		<%
			if (page_all - page_cur > 5) {
		%>
		<a href='ResLoadServlet?page=<%=page_cur + 5%>'>下5页</a>
		<%
			}
		%>

		<%
			if (page_cur < page_all) {
		%>
		<a href='ResLoadServlet?page=<%=page_all%>'>最后一页</a>
		<%
			}
		%>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#dd1,#dd2').calendar();
		$('#cal1,#cal2').hide();
		$('#date_s').focus(function() {
			$('#cal1').show();
			$('#cal2').hide();
		});
		$('#date_t').focus(function() {
			$('#cal2').show();
			$('#cal1').hide();
		});
		$('.btn_cal_cancel').click(function() {
			$(this).parent().hide();
		});
		$('#btn_cal1').click(function() {
			var s = $('#dd1 .calendar-selected')[0].abbr.replace(/,/g, '-');
			$('#date_s').val(s);
			$('#cal1').hide();
		});
		$('#btn_cal2').click(function() {
			var s = $('#dd2 .calendar-selected')[0].abbr.replace(/,/g, '-');
			$('#date_t').val(s);
			$('#cal2').hide();
		});
		$('#date_s,#date_t').blur(
				function() {
					var s = $(this).val();
					if (s == '') {
						return;
					}
					if (s.match(/^\d{3,4}-\d{1,2}-\d{1,2}$/)) {
						s = s.split('-');
						var d = new Date(s[0], s[1] - 1, s[2]);
						s = d.getFullYear() + '-' + (d.getMonth() + 1) + '-'
								+ d.getDate();
						$(this).val(s);
					} else {
						$(this).focus().select();
					}
				});
	});
</script>
</html>
