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

<title>编辑用户</title>
<link rel="stylesheet" type="text/css" href="back-end/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="back-end/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="back-end/Css/style.css" />
<script type="text/javascript" src="back-end/Js/jquery.js"></script>
<script type="text/javascript" src="back-end/Js/bootstrap.js"></script>
<script type="text/javascript" src="back-end/Js/ckform.js"></script>
<script type="text/javascript" src="back-end/Js/common.js"></script>

<script type="text/javascript" src="back-end/js_me/user_add.js"></script>

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
	<form action="UserUpdateServlet" method="post"
		onsubmit="return checkform();">
		<input type="hidden" name="uid" value="${sessionScope.user_edit.uid }" />
		<input type="hidden" name="origin_utype"
			value="${sessionScope.user_edit.utype }" /> <input type="hidden"
			name="origin_ustaffdisplay"
			value="${sessionScope.user_edit.ustaffdisplay }" />
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">用户类别</td>
				<td><select name="utype" style="width: 206px" disabled="disabled" >
						<option value="1">管理员</option>
						<option value="2">员工</option>
						<option value="3">用户</option>
				</select></td>
			</tr>
			<tr>
				<td class="tableleft">用户名称</td>
				<td><input type="text" name="uname"
					value="${sessionScope.user_edit.uname }" /></td>
			</tr>
			<tr class="utype1">
				<td class="tableleft">修改密码</td>
				<td><input type="password" name="upassword" id="psw1"
					value="${sessionScope.user_edit.upassword }" /></td>
			</tr>
			<tr class="utype1">
				<td class="tableleft">重复密码</td>
				<td><input type="password" id="psw2"
					value="${sessionScope.user_edit.upassword }" /><span id="psw_msg"></span></td>
			</tr>
			<tr>
				<td class="tableleft">联系电话</td>
				<td><input type="text" name="utelphone"
					value="${sessionScope.user_edit.utelphone }" /></td>
			</tr>
			<tr class="utype2">
				<td class="tableleft">员工介绍</td>
				<td><textarea name="ustaffinfo" rows=5 />${sessionScope.user_edit.ustaffinfo }</textarea></td>
			</tr>
			<tr class="utype2">
				<td class="tableleft">主页显示</td>
				<td><input type="radio" name="ustaffdisplay" value="0"
					checked="checked"><span>不显示</span> <input type="radio"
					name="ustaffdisplay" value="1"><span>显示</span></td>
			</tr>
			<tr class="utype2">
				<td class="tableleft">员工照片</td>
				<td><img width="206"
					src="front-end/${sessionScope.user_edit.upic }" id="pic_show">
					<span><a id="show_pic_lib" style="cursor: hand;">选取图片&gt;&gt;</a>
				</span> <input type="hidden" name="upic"
					value="${sessionScope.user_edit.upic }" /></td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" type="button">修改用户</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid"
						id="backid">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
	<div id="pic_lib" class="pic_lib">
		<form name="upload_pic" method="post" enctype="multipart/form-data"
			onsubmit="return uploadpic();">
			<input type="file" name="file" id="file" /><input type="submit"
				value="上传" /><span id="back_msg"></span>
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
<script type="text/javascript">
	$(function() {
		//
		var odp = $('input[name="origin_ustaffdisplay"]').val();
		$('input[name="ustaffdisplay"]').each(function() {
			if ($(this).val() == odp) {
				$(this).attr('checked', 'checked');
			} else {
				$(this).removeAttr('checked');
			}
		});
	});
</script>
</html>
