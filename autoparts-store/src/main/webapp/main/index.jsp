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
							<li class="active act"><a href="main.action" data-hover="Home">Home</a></li>
							<li><a href="about.action" data-hover="About">About</a></li>
							<li><a href="find-details.action" data-hover="Find auto parts">Find auto parts</a></li>
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
<!-- banner -->
	<div class="banner">
		<div class="container">
			<div class="wthree_banner_info">
				<section class="slider">
					<div class="flexslider">
						<ul class="slides">
							<li>
								<div class="wthree_banner_info_grid">
									<p>Need auto parts? Find it here</p>
									<h3>We offer a convenient service to search and order the necessary parts for cars.</h3>
								</div>
							</li>
							<li>
								<div class="wthree_banner_info_grid">
									<p>You are company producing auto parts?</p>
									<h3>You can place your products on our website and sell it.</h3>
								</div>
							</li>
							<li>
								<div class="wthree_banner_info_grid">
									<p>Simple and reliable</p>
									<h3>Every day we make our service better and better.</h3>
								</div>
							</li>
						</ul>
					</div>
				</section>
						<!-- flexSlider -->
							<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" property="" />
							<script defer src="js/jquery.flexslider.js"></script>
							<script type="text/javascript">
							$(window).load(function(){
							  $('.flexslider').flexslider({
								animation: "slide",
								start: function(slider){
								  $('body').removeClass('loading');
								}
							  });
							});
						  </script>
						<!-- //flexSlider -->
			</div>
		</div>
	</div>
<!-- //banner -->
<!-- banner-bottom -->
	<div class="banner-bottom">
		<div class="container">
			<div class="agile_banner_bottom_grids">
				<div class="col-md-3 agile_banner_bottom_grid">
					<div class="agile_banner_bottom_grid1 hvr-rectangle-out">
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
					</div>
					<h3>Convenient presentation of products.</h3>
				</div>
				<div class="col-md-3 agile_banner_bottom_grid">
					<div class="agile_banner_bottom_grid1 hvr-rectangle-out">
						<span class="glyphicon glyphicon-signal" aria-hidden="true"></span>
					</div>
					<h3>Increases sales effectiveness.</h3>
				</div>
				<div class="col-md-3 agile_banner_bottom_grid">
					<div class="agile_banner_bottom_grid1 hvr-rectangle-out">
						<span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
					</div>
					<h3>Track your orders.</h3>
				</div>
				<div class="col-md-3 agile_banner_bottom_grid">
					<div class="agile_banner_bottom_grid1 hvr-rectangle-out">
						<span class="glyphicon glyphicon-export" aria-hidden="true"></span>
					</div>
					<h3>Something really cool written here.</h3>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //banner-bottom -->
<!-- welcome -->
	<div class="welcome">
		<div class="container">
			<div class="col-md-6 agileits_welcome_grid_left">
				<h2>Welcome To Autoparts Store !</h2>
				<p>Service of auto parts Autoparts Store is a modern service
					in which you can find the latest novelties of auto parts for any
					make of the car and year of manufacture. Our website creates for you
					the best conditions to make a purchase. Choose the car or you enter
					the necessary article, press to order,
					and we will bring to you the spare part to the specified place.
					<i>Service allows to carry out selection of details for make of the car.
						To start search it is necessary to choose the Producer of the car, its brand,
						year of release and the corresponding modification.</i></p>
			</div>
			<div class="col-md-6 agileits_welcome_grid_right">
				<img src="images/1.jpg" alt=" " class="img-responsive" />
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
<!-- //welcome -->
<!-- welcome-bottom -->
	<div class="welcome-bottom">
		<div class="container">
			<h3>We do regular things even easier</h3>
			<p>We carefully worked on our base of spare parts and crosses.
				Furthermore, it is continuously supplemented and improved.</p>
			<div class="more">
				<a href="about.jsp" class="hvr-bounce-to-top">Learn More</a>
			</div>
		</div>
	</div>
<!-- //welcome-bottom -->
<!-- welcome -->
<div class="welcome">
	<div class="container">
		<div class="col-md-6 agileits_welcome_grid_left">
			<img src="images/114.jpg" alt=" " class="img-responsive" />
		</div>
		<div class="col-md-6 agileits_welcome_grid_right">
			<h2>Always current price and availability</h2>&nbsp;
			<p>We daily update remaining balance and the prices of all
				suppliers provided in our system that allows you to
				possess up-to-date information always. Besides, if you
				install our system to yourself on the website
				– you won't need to worry about updating of availability
				and the prices – it will become automatically.</p>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!-- //welcome -->
<!-- updates-bottom -->
	<div class="updates-bottom">
		<div class="container">
			<div class="col-md-3 w3_updates_bottom_grid">
				<div class="w3_updates_bottom_grid_left">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
				</div>
				<div class="w3_updates_bottom_grid_right">
					<p class="counter">886</p> 
					<h3>Happy Clients</h3>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="col-md-3 w3_updates_bottom_grid">
				<div class="w3_updates_bottom_grid_left">
					<span class="glyphicon glyphicon-signal" aria-hidden="true"></span>
				</div>
				<div class="w3_updates_bottom_grid_right">
					<p class="counter">652</p> 
					<h3>Products Level</h3>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="col-md-3 w3_updates_bottom_grid">
				<div class="w3_updates_bottom_grid_left">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
				</div>
				<div class="w3_updates_bottom_grid_right">
					<p class="counter">886</p> 
					<h3>Suppliers</h3>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="col-md-3 w3_updates_bottom_grid">
				<div class="w3_updates_bottom_grid_left">
					<span class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span>
				</div>
				<div class="w3_updates_bottom_grid_right">
					<p class="counter">457</p> 
					<h3>Popularity</h3>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="clearfix"> </div>
			<!-- Starts-Number-Scroller-Animation-JavaScript -->
				<script src="js/waypoints.min.js"></script> 
				<script src="js/counterup.min.js"></script> 
				<script>
					jQuery(document).ready(function( $ ) {
						$('.counter').counterUp({
							delay: 20,
							time: 1000
						});
					});
				</script>

			<!-- //Starts-Number-Scroller-Animation-JavaScript -->
		</div>
	</div>
<!-- //updates-bottom -->
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