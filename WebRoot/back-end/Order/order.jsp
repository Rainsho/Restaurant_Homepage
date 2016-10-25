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

<title>订单管理</title>
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

form select {
	width: 120px;
}

.load_more_div {
	min-width: 300px;
}

.load_more_div table * {
	border: none;
	margin: 0;
	padding: 0 5px;
	font-size: 14px;
}
</style>

</head>

<body>
	<input type="hidden" value="${sessionScope.ord_ocheck }"
		id="origin_ocheck" />
	<form class="form-inline definewidth m20" action="OrderLoadServlet"
		method="post">
		起始日期： <input type="text" name="date_s" id="date_s"
			class="abc input-default" value="${sessionScope.ord_date_s }"
			style="width: 120px">
		<div class="div_cal">
			<div id="cal1">
				<div id="dd1"></div>
				<a class="btn btn-success" id="btn_cal1">选择日期</a>&nbsp;&nbsp; <a
					class="btn btn-success btn_cal_cancel">关闭</a>
			</div>
		</div>
		&nbsp;&nbsp;截止日期： <input type="text" name="date_t" id="date_t"
			class="abc input-default" value="${sessionScope.ord_date_t }"
			style="width: 120px;">
		<div class="div_cal">
			<div id="cal2">
				<div id="dd2"></div>
				<a class="btn btn-success" id="btn_cal2">选择日期</a>&nbsp;&nbsp; <a
					class="btn btn-success btn_cal_cancel">关闭</a>
			</div>
		</div>
		&nbsp;&nbsp;<select name="ocheck">
			<option value="-1">全部</option>
			<option value="0">未结账</option>
			<option value="1">已结账</option>
		</select> &nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		<a href="OrderLoadServlet?date_s=&date_t=&ocheck=-1"
			class="btn btn-primary">显示所有</a>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th>订单编号</th>
			<th>下单时间</th>
			<th>单品数量</th>
			<th>商品总价</th>
			<th>显示详情</th>
			<th>订单状态</th>
		</tr>
		<c:forEach var="o" items="${sessionScope.ord_list }">
			<tr>
				<td>${o.oid }</td>
				<td>${o.fmtDate() }</td>
				<td>${o.oquant }</td>
				<td>${o.ofee }</td>
				<td><div class="load_more_div">
						<a href="javascript:load_detail(${o.oid });">加载详情</a>
					</div></td>
				<td><c:if test="${o.ocheck == 1 }">已结账</c:if> <c:if
						test="${o.ocheck != 1 }">
						<a href="javascript:check_out(${o.oid });">结账</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
	<div class="inline pull-right page">
		${sessionScope.ord_total } 条记录 ${sessionScope.ord_page
		}/${sessionScope.ord_total_page } 页
		<%!int page_cur, page_all;%>
		<%
			page_cur = (Integer) session.getAttribute("ord_page");
			page_all = (Integer) session.getAttribute("ord_total_page");
		%>


		<%
			if (page_cur > 1) {
		%>
		<a href='OrderLoadServlet?page=1'>首页</a>
		<%
			}
		%>

		<%
			if (page_cur > 5) {
		%>
		<a href='OrderLoadServlet?page=<%=page_cur - 5%>'>上5页</a>
		<%
			}
		%>

		<%
			if (page_cur > 1) {
		%>
		<a href='OrderLoadServlet?page=<%=page_cur - 1%>'>上一页</a>
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
		<a href='OrderLoadServlet?page=<%=i%>'><%=i%></a>
		<%
			}
			}
		%>

		<%
			if (page_cur < page_all) {
		%>
		<a href='OrderLoadServlet?page=<%=page_cur + 1%>'>下一页</a>
		<%
			}
		%>

		<%
			if (page_all - page_cur > 5) {
		%>
		<a href='OrderLoadServlet?page=<%=page_cur + 5%>'>下5页</a>
		<%
			}
		%>

		<%
			if (page_cur < page_all) {
		%>
		<a href='OrderLoadServlet?page=<%=page_all%>'>最后一页</a>
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
		// select
		if ($('#origin_ocheck').val() > -1) {
			$('select[name="ocheck"]').val($('#origin_ocheck').val());
		}
	});
	// ajax
	function load_detail(oid) {
		$.post('OrderCtrlServlet', {
			oid : oid,
			type : 'load_detail'
		}, function(data) {
			var div = $('a[href="javascript:load_detail(' + oid + ');"]')
					.parent();
			div.children().remove();
			div.append('<table></table>');
			var jsonobj = $.parseJSON(data);
			if (jsonobj.state == 0) {
				alert('改单存在异常');
				return;
			}
			// load detail
			$.each(jsonobj.obj, function(i, x) {
				div.children('table').append(
						'<tr><td>' + x.food.foodtype.ftname + '</td><td>'
								+ x.food.fname + '</td><td>'
								+ x.food.fprice.toFixed(2) + ' × ' + x.odquant
								+ '</td><td>'
								+ (x.food.fprice * x.odquant).toFixed(2)
								+ '</td></tr>');
			});
		});
	}

	function check_out(oid) {
		if (confirm('确认买单？')) {
			$.post('OrderCtrlServlet', {
				oid : oid,
				type : 'check_out'
			}, function() {
				$('a[href="javascript:check_out(' + oid + ');"]').parent()
						.html('已结账');
			});
		}
	}
</script>
</html>
