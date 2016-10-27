<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<!-- mune_chart css -->
<link rel="stylesheet" href="css/menu_chart.css">

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
						<li><a href="../MenuServlet">菜单</a></li>
					</ul>
					<div id="menu_chart">
						<div id="chart_title">
							<ul>
								<li><a>已选菜品</a></li>
							</ul>
							<i class="ci-count" id="shopping-amount">${sessionScope.menu_chart_total_quant
								+ 0 }</i>
						</div>
						<div id="chart_layer">
							<div id="ordered">
								<table style="width: 100%;">
									<c:forEach var="mi" items="${sessionScope.menu_item }">
										<tr>
											<td><img src="${mi.food.picture.ppath }"></td>
											<td>${mi.food.fname }</td>
											<td>$${mi.food.fprice } × ${mi.quant }<br /> <a
												href="javascript:del_food(${mi.food.fid });">删除</a></td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<div id="total">
								<c:if test="${sessionScope.menu_chart_total_quant > 0 }">
									<div>共${sessionScope.menu_chart_total_quant }份菜品
										总计$${sessionScope.menu_chart_total_price }元</div>
									<div>
										<a id="btn_menu_chart" href="javascript:void(0);"
											onclick="order_food()">下单</a>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<div id="main-content">
					<div class="wrap-content">
						<c:forEach var="ft0" items="${sessionScope.menu_list1 }"
							varStatus="status">
							<c:if test="${status.index % 3 ==0 }">
								<div class="row">
									<c:forEach var="ft" items="${sessionScope.menu_list1 }"
										begin="${status.index }" end="${status.index + 2 }">
										<div class="col-1-3">
											<div class="wrap-col">
												<h3>${ft.ftname }</h3>
												<c:forEach var="f" items="${sessionScope.menu_list2 }">
													<c:if test="${ft.ftid == f.foodtype.ftid }">
														<div class="post">
															<a><img src="${f.picture.ppath }"
																alt="${f.picture.ppath }" /></a>
															<div class="wrapper">
																<h5 style="position: relative;">
																	<a title="你不来一个么">${f.fname }</a> <span
																		class="food_detial" style="font-size: 10px;">份数：<input
																		type="number" value="1" min="1" max="20"
																		name="n_fid_${f.fid }"
																		style="width: 40px; border: 1px dotted gainsboro;margin-right: 4px;" /><span
																		style="font-size: 10px;"></span><br /> <a
																		href="javascript:add_food(${f.fid });">点餐</a><br />${f.fdetial
																		}
																	</span>
																</h5>
																<span>$&nbsp;${f.fprice } - ${f.fprice }</span>
															</div>
														</div>
													</c:if>
												</c:forEach>
											</div>
										</div>
									</c:forEach>
								</div>
							</c:if>
						</c:forEach>
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

		<script src="js/jquery-1.11.3.min.js"></script>
		<script src="js/menu.js"></script>

	</div>
</body>
</html>