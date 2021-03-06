<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>修改商品信息</title>
<!-- Bootstrap Styles-->
<link href="../assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="../assets/css/font-awesome.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="../assets/css/custom-styles.css" rel="stylesheet" />
<link href="../assets/css/ShopList.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='https://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
	<style>

		.mm li{
			font-size: 17px;
		}

	</style>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html"><i class="fa fa-gear"></i>
				<strong>HYBRID</strong></a>
		</div>

		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-messages">
					<li><a href="#">
							<div>
								<strong>John Doe</strong> <span class="pull-right text-muted">
									<em>Today</em>
								</span>
							</div>
							<div>Lorem Ipsum has been the industry's standard dummy
								text ever since the 1500s...</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem Ipsum has been the industry's standard dummy
								text ever since an kwilnw...</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem Ipsum has been the industry's standard dummy
								text ever since the...</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>Read
								All Messages</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-messages --></li>
			<!-- /.dropdown -->

			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-alerts">
					<li><a href="#">
							<div>
								<i class="fa fa-comment fa-fw"></i> New Comment <span
									class="pull-right text-muted small">4 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
									class="pull-right text-muted small">12 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-envelope fa-fw"></i> Message Sent <span
									class="pull-right text-muted small">4 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-tasks fa-fw"></i> New Task <span
									class="pull-right text-muted small">4 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
									class="pull-right text-muted small">4 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>See
								All Alerts</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-alerts --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#"><i class="fa fa-user fa-fw"></i> User
							Profile</a></li>
					<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
					</li>
					<li class="divider"></li>
					<li><a href="#"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		</nav>
		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">

				<li><a class="active-menu"
					href="http://localhost:8080/FengSHOP/home.jsp"><i
						class="fa fa-dashboard"></i> 舟渡首页</a></li>
				<li>
					<a class=" sp" style="background-color: #18A0A9"   href="v#"><i class="fa fa-fw fa-file   "></i> 商品</a>

					<ul class=" mm"  style="list-style-type: none;">
						<li><a  style="text-decoration: none;" href="product?pageNo=1&operation=8"><i class="fa fa-fw fa-file"></i> 商品列表</a> </li>
						<li><a  style="text-decoration: none;"  href="AddShop.jsp"><i class="fa fa-fw fa-file"></i> 添加商品</a> </li>
						<li><a  style="text-decoration: none;" href="Category?pageNo=1&operation=7"><i class="fa fa-fw fa-file"></i> 商品类别</a> </li>
						<li><a style="text-decoration: none;"  href="AddShopType.jsp"><i class="fa fa-fw fa-file"></i> 添加类别</a> </li>
					</ul>
				</li>
			
				<li><a href="Cart?operation=8"><i class="fa fa-fw fa-file"></i>
						购物车</a></li>
					 <li>
                       <a  href="Order?operation=3" ><i
						class="fa fa-fw fa-file"></i>订单列表</a>
                    </li>
			</ul>

		</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h1 class="page-header">
							加 购<small> Insert Cart</small>
						</h1>
					</div>
				</div>
				<!-- /. ROW  -->
				<div>
					<form action="Cart" method="post">
						<input type="hidden" name="operation" value="1" /> <input
							type="hidden" name="pid" value="${product.id}" />
						<table style="margin-top: 20px" align="center" border="1"
							cellpadding="0" cellspacing="0">
							<tr>
								<td style="font-size: 15px">商品id</td>
								<td><input class="form-control"
									style="width: 162px; font-size: 20px" type="text" name="id"
									value="${product.id}"></td>
							</tr>
							<tr>
								<td style="font-size: 15px">商品名</td>
								<td><input class="form-control"
									style="width: 162px; font-size: 20px" type="text" name="id"
									value="${product.name}"></td>
							</tr>
							<tr>
								<td style="font-size: 15px">商品价格</td>
								<td><input class="form-control"
									style="width: 162px; font-size: 20px" type="text" name="id"
									value="${product.price}"></td>
							</tr>
							<tr>
								<td style="font-size: 15px">商品图片</td>
								<td><input class="form-control"
									style="width: 162px; font-size: 20px" type="text" name="id"
									value="${product.image}"></td>
							</tr>
							<tr>
								<td style="font-size: 15px">商品数量</td>
								<td><input class="form-control"
									style="width: 162px; font-size: 20px" type="text"
									name="productnum"></td>
							</tr>
							<tr>
								<td colspan="2"><input class="btn btn-default"
									style="width: 224px;" type="submit" value="加入购物车"></td>
							</tr>
						</table>
					</form>
				</div>










				<footer>
				<p>Copyright &copy; 2018.中国</p>
				</footer>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->
	<!-- JS Scripts-->
	<!-- jQuery Js -->
	<script src="../assets/js/jquery-1.10.2.js"></script>
	<!-- Bootstrap Js -->
	<script src="../assets/js/bootstrap.min.js"></script>
	<!-- Metis Menu Js -->
	<script src="../assets/js/jquery.metisMenu.js"></script>
	<!-- Custom Js -->
	<script src="../assets/js/custom-scripts.js"></script>


</body>
</html>
<<script !src="../assets/js/jquery-3.3.1.min.js"></script>
<<script>
$(function() {
$(".sp").click(function() {
$(".mm").toggle(1000);
})
})

</script>