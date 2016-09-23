<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Autoparts Store</title>
	<!-- for-mobile-apps -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="css/smoothbox.css" type='text/css' media="all" />
<!-- js -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<!-- //js -->
<link href='//fonts.googleapis.com/css?family=Maven+Pro:400,500,700,900' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<!-- start-smoth-scrolling -->
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
</head>
	
<body>
<!-- header -->
	<div class="header" id="ban">
		<div class="container">
			<div class="w3l_social_icons">
				<ul>
					<li><a href="#" class="facebook"></a></li>
					<li><a href="#" class="twitter"></a></li>
					<li><a href="#" class="google_plus"></a></li>
				</ul>
			</div>
			<div class="w3ls_logo">
				<h1><a href="index.jsp">Autoparts Store</a></h1>
			</div>
			<div class="header_right">
			<nav class="navbar navbar-default">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
					<nav class="link-effect-7" id="link-effect-7">
						<ul class="nav navbar-nav">
							<li class="act"><a href="main.action" data-hover="Home">Home</a></li>
							<li><a href="about.action" data-hover="About">About</a></li>
							<li class="active act"><a href="find-details.action" data-hover="Find auto parts">Find auto parts</a></li>
							<c:set var="salary" scope="session" value='<%=session.getAttribute("role")%>'/>
							<c:if test="${salary eq 'Customer'}">
								<li><a href="customer-dashboard.action" data-hover="Dashboard">Dashboard</a></li>
								<li><a href="logout.action" data-hover="Logout">Logout</a></li>
							</c:if>
							<c:if test="${salary eq 'Supplier'}">
								<li><a href="supplier-dashboard.action" data-hover="Dashboard">Dashboard</a></li>
								<li><a href="logout.action" data-hover="Logout">Logout</a></li>
							</c:if>
							<c:if test="${salary eq 'Admin'}">
								<li><a href="adminpanel.action" data-hover="Dashboard">Dashboard</a></li>
								<li><a href="logout.action" data-hover="Logout">Logout</a></li>
							</c:if>
							<c:choose>
								<c:when test="${salary eq null}">
									<li><a href="registration.action" data-hover="Sign Up">Sign Up</a></li>
									<li><a href="authorisation.action" data-hover="Sign In">Sign In</a></li>
								</c:when>
							</c:choose>
						</ul>
					</nav>
				</div>
				<!-- /.navbar-collapse -->
			</nav>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
<!-- //header -->
<!-- about -->
	<div class="about">
		<div class="container">
			<h3>Find details</h3>
			<ul>
				<li><a href="index.jsp">Home</a><i>|</i></li>
				<li>Find Details</li>
			</ul>
		</div>
	</div>

<div id="page-inner">
	<div class="row">
		<div class="col-md-12">
			<!-- Table -->
			<div class="panel-body">
				<div class="table-responsive">
					<p>Details list:</p>
					<table class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
							<th>#</th>
							<th>name</th>
							<th>price</th>
							<th>count in warehouse</th>
							<th>car name</th>
							<th>car release year</th>
							<th>warehouse name</th>
							<th>warehouse address</th>
							<th>supplier</th>
						</tr>
						</thead>
						<tbody>
						<s:iterator value="detailsList" var="detail">
							<tr>
								<td><s:property value="id"></s:property></td>
								<td><s:property value="name"></s:property></td>
								<td><s:property value="price"></s:property></td>
								<td><s:property value="countInWarehouse"></s:property></td>
								<td><s:property value="car.getName()"></s:property></td>
								<td><s:property value="car.getReleaseYear()"></s:property></td>
								<td><s:property value="warehouse.getName()"></s:property></td>
								<td><s:property value="warehouse.getAddress()"></s:property></td>
								<td><s:property value="supplier.getCompanyName()"></s:property></td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- //about -->

<!-- footer -->
	<div class="footer">
	</div>
<div class="footer-copy">
	<div class="container">
		<div class="w3agile_footer_grids">
			<div class="col-md-4 w3agile_footer_grid">
				<h3>About Us</h3>
				<p>We are Pasha, Alex and Alex.<span>We love to program.</span></p>
			</div>
			<div class="col-md-4 w3agile_footer_grid">
				<h3>Contact Info</h3>
				<ul>
					<li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>L.Bedy Avenue, 4th block, <span>Minsk.</span></li>
					<li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a href="mailto:autoparts.store@gmail.com">autoparts.store@gmail.com</a></li>
					<li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+375298765467</li>
				</ul>
			</div>
			<div class="col-md-4 w3agile_footer_grid w3agile_footer_grid1">
				<h3>Navigation</h3>
				<ul>
					<li><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span><a href="main.action">Home</a></li>
					<li><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span><a href="about.action">About Us</a></li>
					<li><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span><a href="find-details.action">Find Details</a></li>
				</ul>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
</div>
<div class="copy-right-social">
	<div class="container">
		<div class="footer-pos">
			<a href="#ban" class="scroll"><img src="images/arrow.png" alt=" " class="img-responsive" /></a>
		</div>
		<div class="copy-right">
			<p>&copy; 2016 Autoparts Store. All rights reserved | Developed by Pavel Poplavskis, Alexey Palto & Alexey Gorishnyi</p>
		</div>
		<div class="copy-right-social1">
			<div class="w3l_social_icons w3l_social_icons1">
				<ul>
					<li><a href="#" class="facebook"></a></li>
					<li><a href="#" class="twitter"></a></li>
					<li><a href="#" class="google_plus"></a></li>
				</ul>
			</div>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!-- //footer -->
<!-- for bootstrap working -->
	<script src="js/bootstrap.js"></script>
<!-- //for bootstrap working -->
</body>
</html>