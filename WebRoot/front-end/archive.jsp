<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%-- 这个页面与后台base一致，否则后台编辑图片很麻烦 --%>
<base href="<%=basePath%>">

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
<link rel="stylesheet" href="front-end/css/zerogrid.css">
<link rel="stylesheet" href="front-end/css/style.css">
<link rel="stylesheet" href="front-end/css/slide.css">
<link rel="stylesheet" href="front-end/css/menu.css">
<!-- Custom Fonts -->
<link href="front-end/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
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
							<a href="back-end/login.jsp" style="color:white;">登录后台管理</a>
						</p></li>
				</ul>
			</div>
		</div>
		<!--////////////////////////////////////Header-->
		<header>
			<div class="zerogrid">
				<center>
					<div class="logo">
						<img src="front-end/images/logo.png">
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
				<li class="colour-1"><a href="IndexServlet">主页</a></li>
				<li class="colour-2"><a href="MenuServlet">菜单</a></li>
				<li class="colour-4"><a href="ArchiveServlet">动态</a></li>
				<li class="colour-5"><a href="StaffServlet">员工</a></li>
				<li class="colour-7"><a href="ReservationServlet">预订</a></li>
				<li class="colour-8"><a href="GalleryServlet">画廊</a></li>
			</ul>
		</nav>


		<!--////////////////////////////////////Container-->
		<section id="container" class="sub-page">
			<div class="wrap-container zerogrid">
				<div class="crumbs">
					<ul>
						<li><a href="IndexServlet">主页</a></li>
						<li><a href="ArchiveServlet">动态</a></li>
					</ul>
				</div>
				<div id="main-content" class="col-2-3">
					<div class="wrap-content">
						<article>
							<div class="art-header">
								<div class="entry-title">
									<h2>${sessionScope.archive_news.ntag }</h2>
								</div>
								<div class="info">
									By <a>${sessionScope.archive_news.nauthor }</a> on
									${sessionScope.archive_news.fmtDate() }
								</div>
							</div>
							<div class="art-content" id="ncontent" style="margin-top: 10px;">${sessionScope.archive_news.ncontent
								}</div>
						</article>
						<div class="widget wid-related">
							<div class="wrap-col">
								<div class="wid-header">
									<h5>最近动态</h5>
								</div>
								<div class="wid-content">
									<div class="row" id="more_archive" style="padding: 0 10px;"></div>
									<div class="row">
										<h4 style="text-align: center;">
											<a href="javascript:getmore();" id="a_more">加载更多</a>
										</h4>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="sidebar" class="col-1-3">
					<div class="wrap-sidebar">
						<!---- Start Widget ---->
						<div class="widget wid-about">
							<div class="wid-header">
								<h5>关于我们</h5>
							</div>
							<div class="wid-content">
								<img src="front-end/images/indoor1.jpeg" />
								<p>新派粤菜食府龙景轩位于酒店四楼，可饱览海港绝美景观，加上选用本地最新鲜的高级食材入馔，配合在当地享负盛名的精英厨师团队，实为天作之合。</p>
							</div>
						</div>
						<!---- Start Widget ---->
						<div class="widget wid-post">
							<div class="wid-header">
								<h5>新品推荐</h5>
							</div>
							<div class="wid-content">
								<c:forEach var="f" items="${sessionScope.archive_flist }">
									<div class="post">
										<a href="MenuServlet"><img
											src="front-end/${f.picture.ppath }" /></a>
										<div class="wrapper">
											<h5>
												<a href="MenuServlet">${f.fname }</a>
											</h5>
											<span>$&nbsp;${f.fprice } - ${f.fprice }</span>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<!---- Start Widget ---->
						<!-- 未启用tag功能 -->
						<!-- <div class="widget wid-tag">
							<div class="wid-header">
								<h5>Tags</h5>
							</div>
							<div class="wid-content">
								<a href="#">animals ,</a> <a href="#">cooking ,</a> <a href="#">countries
									,</a> <a href="#">home ,</a> <a href="#">likes ,</a> <a href="#">photo
									,</a> <a href="#">travel ,</a> <a href="#">video </a>
							</div>
						</div> -->
						<!---- Start Widget ---->
						<div class="widget wid-gallery">
							<div class="wid-header">
								<h5>画廊</h5>
							</div>
							<div class="wid-content">
								<c:forEach var="p" items="${sessionScope.archive_plist }">
									<a href="GalleryServlet"><img src="front-end/${p.ppath }"></a>
								</c:forEach>
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
								<img src="front-end/images/a-1.jpg">
								<h5>思聪 王</h5>
								<p>大闸蟹是最让人垂涎的食物，这样的湖鲜，用传统的清蒸就好，餐厅还用上了荷叶，添一丝清香。一只肉肥膏黄是大闸蟹，一杯黄酒，真是人间美事啊！(推荐)</p>
							</div>
						</div>
					</div>
					<div class="col-1-3">
						<div class="wrap-col">
							<h4>Location</h4>
							<div class="wrap-map">
								<iframe src="front-end/api/bdmap.html" width="100%" height="200"
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
		<script src="front-end/js/classie.js"></script>
		<script src="front-end/js/demo.js"></script>

		<script src="front-end/js/jquery-1.11.3.min.js"></script>
		<script src="back-end/markdown/marked.min.js"></script>
		<script type="text/javascript">
			var page = 1;
			function getmore() {
				$.post('ArchiveMoreServlet', {
					page : page
				}, function(data) {
					var obj = $.parseJSON(data);
					$.each(obj.obj, function(i, x) {
						var d = new Date(x.ndate.timeInMillis);
						var yy = d.getFullYear();
						var MM = d.getMonth() < 9 ? ('0' + (d.getMonth() + 1))
								: (d.getMonth() + 1);
						var dd = d.getDate() < 10 ? ('0' + d.getDate()) : d
								.getDate();
						var hh = d.getHours() < 10 ? ('0' + d.getHours()) : d
								.getHours();
						var mm = d.getMinutes() < 10 ? ('0' + d.getMinutes())
								: d.getMinutes();
						var dt = yy + '年' + MM + '月' + dd + '日 ' + hh + '时'
								+ mm + '分';
						$('#more_archive').append(
								'<h4><a href="ArchiveServlet?nid=' + x.nid
										+ '">' + dt + '\t' + x.ntag
										+ '</a></h4>');
					});
					if (obj.state < 10) {
						$('#a_more').text('没有更多了...');
						$('#a_more').attr('href', 'javascript:void(0);');
					}
					page++;
				});
			}
			$(function() {
				$('#ncontent').html(marked($('#ncontent').text()));
				getmore();
			});
		</script>
	</div>
</body>
</html>