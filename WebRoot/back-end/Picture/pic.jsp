<%@ page language="java" pageEncoding="GBK"%>
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

<title>画廊管理</title>
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
	<form class="form-inline definewidth m20" action="PicUploadServlet"
		method="post" enctype="multipart/form-data">
		<input type="file" name="file" id="file" class="abc input-default" />
		<input type="submit" class="btn btn-primary" value="上传" /> <span>${sessionScope.pic_upload_msg
			}</span>
	</form>
	<div style="padding: 20px">
		<!-- show pic -->
		<c:forEach var="p" items="${sessionScope.pic_list }">
			<div class="pic_div">
				<img alt="${p.pname }" src="front-end/${p.ppath }"
					class="display${p.pdisplay }">
				<div class="pic_btn">
					<%-- 在event里两次使用${p.pid }会在第一行显示x，看的烦，放入span处理 --%>
					<span style="display: none;">${p.pid }</span>
					<button type="button" class="btn btn-success pic_display">设置${p.pdisplay==0?
						"显示":"不显示" }</button>
					<button type="button" class="btn btn-success pic_del">删除</button>
				</div>
			</div>
		</c:forEach>
	</div>


</body>

<style type="text/css">
.pic_div {
	display: inline-block;
	width: 19.5%;
	margin-bottom: 3px;
	position: relative;
}

.pic_div:hover .pic_btn {
	display: block;
}

.pic_btn {
	width: 100%;
	position: absolute;
	bottom: 5px;
	text-align: center;
	opacity: 0.8;
	display: none;
}

.display0 {
	opacity: 0.6;
}

.display1 {
	opacity: 1;
}
</style>
<script type="text/javascript">
	$(function() {
		$('.pic_display').click(function() {
			// 回调函数会操作当前dom，拿that暂存
			var that = $(this);
			var pid = $(this).prev().text();
			var pdisplay = $(this).text() == '设置显示' ? 1 : 0;
			//console.log(pid + '  ' + pdisplay);
			$.post('PicChangeDisplayServlet', {
				pid : pid,
				pdisplay : pdisplay
			}, function() {
				//change text
				if (pdisplay == 1) {
					that.text('设置不显示');
				} else {
					that.text('设置显示');
				}
				//change class
				that.parent().prev().toggleClass('display0 display1');
			});
		});
	});
	$(function() {
		$('.pic_del').click(function() {
			if (confirm("确定要删除吗？")) {
				var pid = $(this).prev().prev().text();
				var url = "PicDeleteServlet?pid=" + pid;
				window.location.href = url;
			}
		});
	});
</script>
</html>
