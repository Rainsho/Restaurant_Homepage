<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>

<!-- Basic Page Needs
  ================================================== -->
<meta charset="utf-8">
<title>牛逼西餐官网</title>
<meta name="description" content="">
<meta name="author" content="">

<!-- Mobile Specific Metas
  ================================================== -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
  ================================================== -->
<link rel="stylesheet" href="css/zerogrid.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/slide.css">
<link rel="stylesheet" href="css/menu.css">
<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<!--[if lt IE 8]>
       <div style=' clear: both; text-align:center; position: relative;'>
         <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
           <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
      </div>
    <![endif]-->
<!--[if lt IE 9]>
		<script src="js/html5.js"></script>
		<script src="js/css3-mediaqueries.js"></script>
	<![endif]-->

</head>
<body>
	<div class="wrap-body">
		<!--///////////////////////////////////////Top-->
		<div class="top">
			<div class="zerogrid">
				<ul class="number f-left">
					<li class="mail"><p>rainsho@126.com</p></li>
					<li class="phone"><p>134 3719 0052</p></li>
				</ul>
				<ul class=" f-right">
					<li><p>
							<a href="../back-end/index.jsp" style="color:white;">登录后台管理</a>
						</p></li>
				</ul>
			</div>
		</div>
		<!--////////////////////////////////////Header-->
		<header>
			<div class="zerogrid">
				<center>
					<div class="logo">
						<img src="images/logo.png">
					</div>
				</center>
			</div>
		</header>
		<div class="site-title">
			<div class="zerogrid">
				<div class="row">
					<h2 class="t-center">我们这里最牛逼的西餐厅 - 纽约时报</h2>
				</div>
			</div>
		</div>
		<!--//////////////////////////////////////Menu-->
		<a href="#" class="nav-toggle">选择频道</a>
		<nav class="cmn-tile-nav">
			<ul class="clearfix">
				<li class="colour-1"><a href="../IndexServlet">主页</a></li>
				<li class="colour-2"><a href="../MenuServlet">菜单</a></li>
				<li class="colour-4"><a href="../ArchiveServlet">动态</a></li>
				<li class="colour-5"><a href="../StaffServlet">员工</a></li>
				<li class="colour-7"><a href="../ReservationServlet">预订</a></li>
				<li class="colour-8"><a href="../GalleryServlet">画廊</a></li>
			</ul>
		</nav>

		<!--////////////////////////////////////Container-->
		<section id="container" class="sub-page">
			<div class="wrap-container zerogrid">
				<div class="crumbs">
					<ul>
						<li><a href="../IndexServlet">主页</a></li>
						<li><a href="../ReservationServlet">预订</a></li>
					</ul>
				</div>
				<div id="main-content">
					<div class="wrap-content">
						<div class="row">
							<div class="col-1-3">
								<div class="wrap-col">
									<h3>请填写右侧预订信息</h3>
									<p>预订不收取任何费用。我们的工作人员会尽快与您核实具体就餐时间或其他需求。如需取消预订，请在预订时间前2小时电话告知。</p>
									<br />
									<h3>或直接致电</h3>
									<p>
										+86 27 5931 8888 <br> +86 27 5931 7777
									</p>
									<p>info@newbeerestaurant.com</p>
								</div>
							</div>
							<div class="col-2-3">
								<div class="wrap-col">
									<div class="contact">
										<div id="contact_form">
											<form name="contact" id="contact" method="post"
												onsubmit="return ajax_res();">
												<div class="row">
													<div class="col-1-3">
														<div class="wrap-col">
															<input type="text" name="uname" id="uname"
																placeholder="您的姓名" required="required" />
														</div>
													</div>
													<div class="col-1-3">
														<div class="wrap-col">
															<input type="text" name="utelphone" id="utelphone"
																placeholder="您的电话" required="required" />
														</div>
													</div>
													<div class="col-1-3">
														<div class="wrap-col">
															<input type="number" name="resseat" id="resseat"
																placeholder="预订位数" required="required" min="1" max="100" />
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-1-3">
														<div class="wrap-col">
															<input type="text" name="restitle" id="restitle"
																placeholder="预订标题" required="required" />
														</div>
													</div>
													<div class="col-1-3">
														<div class="wrap-col">
															<input type="date" name="date" id="date"
																placeholder="Date" required="required" />
														</div>
													</div>
													<div class="col-1-3">
														<div class="wrap-col">
															<input type="time" name="time" id="time"
																placeholder="Time" required="required" />
														</div>
													</div>
												</div>
												<div class="row">
													<div class="wrap-col">
														<textarea name="resinfo" id="resinfo" class="form-control"
															rows="4" cols="25" placeholder="预订信息（选填）"></textarea>
													</div>
												</div>
												<center>
													<input class="sendButton" type="submit" name="Submit"
														value="预订">
												</center>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>


		<!--////////////////////////////////////Footer-->
		<footer class="zerogrid">
			<div class="wrap-footer">
				<div class="row">
					<div class="col-1-3">
						<div class="wrap-col">
							<h4>客户反馈</h4>
							<div class="row">
								<img src="images/a-1.jpg">
								<h5>思聪 王</h5>
								<p>大闸蟹是最让人垂涎的食物，这样的湖鲜，用传统的清蒸就好，餐厅还用上了荷叶，添一丝清香。一只肉肥膏黄是大闸蟹，一杯黄酒，真是人间美事啊！(推荐)</p>
							</div>
						</div>
					</div>
					<div class="col-1-3">
						<div class="wrap-col">
							<h4>Location</h4>
							<div class="wrap-map">
								<iframe src="api/bdmap.html" width="100%" height="200"
									frameborder="0" style="border:0"></iframe>
							</div>
						</div>
					</div>
					<div class="col-1-3">
						<div class="wrap-col">
							<h4>营业时间</h4>
							<p>
								<span>mon.</span> 17:00 - 21:00
							</p>
							<p>
								<span>tue.-wed.</span> 16:30 - 21:00
							</p>
							<p>
								<span>thu.-sat.</span> 16:30 - 21:00
							</p>
							<p>
								<span>sun.</span> 11:00 - 21:00
							</p>
							<p>
								<span>喝醉了回不去？</span><br /> 我们提供代驾服务！
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="copyright">
				<div class="wrapper">
					Copyright &copy; 2016. NewBee Restaurant All rights reserved.
					<ul class="quick-link f-right">
						<li><a href="javascript:void(0);">Privacy Policy</a></li>
						<li><a href="javascript:void(0);">Terms of Use</a></li>
					</ul>
				</div>
			</div>
		</footer>


		<!-- js -->
		<script src="js/classie.js"></script>
		<script src="js/demo.js"></script>

	</div>
</body>
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		// 默认第二天19点
		var d = new Date();
		d.setDate(d.getDate() + 1);
		var yy = d.getFullYear();
		var mm = d.getMonth() < 9 ? ('0' + (d.getMonth() + 1))
				: (d.getMonth() + 1);
		var dd = d.getDate() < 10 ? ('0' + d.getDate()) : d.getDate();
		$('#date').val(yy + '-' + mm + '-' + dd);
		$('#time').val('19:00');
	});

	function ajax_res() {
		var uname = $('#uname').val().trim();
		var utelphone = $('#utelphone').val().trim();
		// resseat 交给H5验证
		var resseat = $('#resseat').val().trim();
		var restitle = $('#restitle').val().trim();
		var resinfo = $('#resinfo').val().trim();
		var resdate = $('#date').val().trim() + ' ' + $('#time').val().trim();

		// check utelphone/resdate
		var t = resdate.split(/\D/);
		var d = new Date(t[0], t[1] - 1, t[2], t[3] - 2, t[4]);
		if (d < new Date()) {
			alert('距您预订的时间已不足两小时，臣妾做不到啊！');
			return false;
		}
		if (!utelphone.match(/^1[3578]\d{9}$/)) {
			alert('暂时只支持中国大陆1开头11位手机号');
			$('#utelphone').focus().select();
			return false;
		}

		var data = {
			uname : uname,
			utelphone : utelphone,
			resseat : resseat,
			restitle : restitle,
			resinfo : resinfo,
			resdate : resdate
		};

		$.post('../ReservationServlet', data, function() {
			alert('预订成功，稍后会有我们的客服人员为您确定。');
			contact.reset();
		});

		return false;
	}
</script>
</html>