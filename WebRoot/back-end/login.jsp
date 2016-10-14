<%@ page language="java" pageEncoding="UTF-8"%>
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

<title>后台管理系统登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="back-end/style/style.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div class="login" id="login-box">
		<h2>
			管理登录<a href="front-end/index.jsp">返回网站主页</a>
		</h2>

		<form name="form1" method="post" action="LoginServlet"
			onsubmit="return checkform();">
			<!-- 登录失败提示 -->
			<p style="margin: 0;padding: 0 4px;height: 50px;color: white;">${sessionScope.loginmsg
				}</p>
			<input type="text" name="username" class="ipt" value="管理帐号"
				onfocus="if(this.value=='管理帐号'){this.value='';}"
				onblur="if(this.value==''){this.value='管理帐号';}" /> <input
				type="password" class="ipt" name="password" value="" /> <input
				id="vdcode" type="text" name="verifycode" class="ipt yzm"
				value="验证码" onfocus="if(this.value=='验证码'){this.value='';}"
				onblur="if(this.value==''){this.value='验证码';}" /> <a><img
				id="vdimgck" align="middle" onClick="cg_code()"
				src="VerifyCodeServlet" width="80" height="31" /></a> <a
				href="javascript:void(0);" onClick="cg_code()">&nbsp;&nbsp;&nbsp;&nbsp;看不清
			</a> <input type="submit" name="sm1" class="button" value="" onclick=""
				style="CURSOR: hand" />
		</form>

	</div>

</body>
<script type="text/javascript">
	function checkform() {
		form1.username.value = form1.username.value.trim();
		form1.password.value = form1.password.value.trim();
		form1.verifycode.value = form1.verifycode.value.trim();
		if (form1.username.value == "") {
			form1.username.focus();
			alert("账号不能为空");
			return false;
		}
		if (form1.password.value == "") {
			form1.password.focus();
			alert("密码不能为空");
			return false;
		}
		if (form1.verifycode.value == "") {
			form1.verifycode.focus();
			alert("验证码不能为空");
			return false;
		}
	}
	function cg_code() {
		document.getElementById('vdimgck').src = "VerifyCodeServlet?t="
				+ Math.random();
	}
</script>
</html>
