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

<title>主页管理</title>
<link rel="stylesheet" type="text/css" href="back-end/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="back-end/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="back-end/Css/style.css" />
<script type="text/javascript" src="back-end/Js/jquery.js"></script>
<script type="text/javascript" src="back-end/Js/bootstrap.js"></script>
<script type="text/javascript" src="back-end/Js/ckform.js"></script>
<script type="text/javascript" src="back-end/Js/common.js"></script>
<!-- 这个页面用的js -->
<script type="text/javascript" src="back-end/js_me/home.js"></script>
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

.td_contents {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	width: 500px;
}

.td_title {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	width: 200px;
}

#hp_update {
	margin: 10px 20px;
	text-align: center;
}

#update_left_div {
	display: inline-block;
	width: 40%;
	text-align: center;
}

#update_left_div a {
	font-size: 14px;
	display: block;
	margin-top: 5px;
	cursor: hand;
}

#update_left_div a:hover {
	text-decoration: none;
}

#update_right_div {
	display: inline-block;
	width: 55%;
	padding-left: 20px;
	text-align: center;
}

#update_right_div textarea {
	width: 96%;
}

/* pic lib 相关 */
#pic_lib {
	background-color: white;
	width: 50%;
	height: 450px;
	position: absolute;
	top: 170px;
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

	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th>选择设置</th>
			<th>元素标题</th>
			<th>元素正文</th>
		</tr>
		<c:forEach var="hp" items="${sessionScope.homepage_list }">
			<tr>
				<td align="center"><input type="radio" name="hid"
					value="${hp.hid }" />&nbsp;&nbsp;<span>${hp.type }</span></td>
				<td><div class="td_title">${hp.title }</div></td>
				<td><div class="td_contents">${hp.contents }</div> <input
					type="hidden" value="${hp.pic }" /></td>
			</tr>
		</c:forEach>
	</table>
	<div id="hp_update">
		<div id="update_left_div">
			<input id="hp_pic" type="hidden" value="" /><img id="hp_pic_show"
				src=""><a id="show_pic_lib">选择图片&gt;&gt;</a>
		</div>
		<div id="update_right_div">
			<input id="hp_hid" type="hidden" value="hid" />
			<textarea id="hp_type" rows="1" cols="20" readOnly="readonly">Type</textarea>
			<textarea id="hp_title" rows="2" cols="20"></textarea>
			<textarea id="hp_contents" rows="10" cols="20"></textarea>
			<input id="btn_update" type="submit" class="btn btn-primary"
				value="更新" style="width: 100px;" />
		</div>
	</div>
	<!-- pic_lib -->
	<div id="pic_lib" class="pic_lib">
		<form name="upload_pic" method="post" enctype="multipart/form-data"
			onsubmit="return uploadpic();">
			<input type="file" name="file" id="file" /><input type="submit"
				value="上传" /><span id="back_msg" style="margin-left: 5px;"></span>
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
