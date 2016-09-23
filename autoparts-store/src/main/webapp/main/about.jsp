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
                            <li class="act"><a href="main.action" data-hover="Home">Home</a></li>
                            <li class="active act"><a href="about.action" data-hover="About">About</a></li>
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
<!-- about -->
	<div class="about">
		<div class="container">
			<h3>About Us</h3>
			<ul>
				<li><a href="index.jsp">Home</a><i>|</i></li>
				<li>About Us</li>
			</ul>
		</div>
	</div>
<!-- //about -->
<!-- about-bottom -->
	<div class="about-bottom">
		<div class="container">
			<div class="agileinfo_about_bottom_grids">
				<div class="col-md-6 agileinfo_about_bottom_grid">
					<h3>About <span>Us</span></h3>
					<p>Service of auto parts Autoparts Store is a modern service
                        in which you can find the latest novelties of auto parts for any
                        make of the car and year of manufacture. Our website creates for you
                        the best conditions to make a purchase. Choose the car or you enter
                        the necessary article, press to order,
                        and we will bring to you the spare part to the specified place.
                        Service allows to carry out selection of details for make of the car.
                        To start search it is necessary to choose the Producer of the car, its brand,
                        year of release and the corresponding modification.</p>
				</div>
				<div class="col-md-6 agileinfo_about_bottom_grid">
					<img src="images/7.jpg" alt=" " class="img-responsive" />
				</div>
			</div>
		</div>
	</div>
<!-- //about-bottom -->
<!-- team -->
	<div class="team">
		<div class="container">
			<h3>Meet Out <span>Team</span></h3>
			<div class="w3l_team_grids">
				<div class="col-md-3 w3l_team_grid">
					<div class="view view-second">
						<img src="images/8.jpg" alt=" " class="img-responsive" />
						<div class="mask"></div>
						<div class="content">
							<div class="w3l_social_icons w3l_social_icons1">
								<ul>
									<li><a href="#" class="facebook"></a></li>
									<li><a href="#" class="twitter"></a></li>
									<li><a href="#" class="google_plus"></a></li>
									<li><a href="#" class="pinterest"></a></li>
									<li><a href="#" class="instagram"></a></li>
								</ul>
							</div>
							<p>The main thing in time to understand what people do you wrong.</p>
						</div>
					</div>
					<h4>Pavel Poplavskis</h4>
					<p>Main developer</p>
				</div>
				<div class="col-md-3 w3l_team_grid">
					<div class="view view-second">
						<img src="images/9.jpg" alt=" " class="img-responsive" />
						<div class="mask"></div>
						<div class="content">
							<div class="w3l_social_icons w3l_social_icons1">
								<ul>
									<li><a href="#" class="facebook"></a></li>
									<li><a href="#" class="twitter"></a></li>
									<li><a href="#" class="google_plus"></a></li>
									<li><a href="#" class="pinterest"></a></li>
									<li><a href="#" class="instagram"></a></li>
								</ul>
							</div>
							<p>Life lessons will not skip.</p>
						</div>
					</div>
					<h4>Alexey Gorishnyi</h4>
					<p>The main developer</p>
				</div>
				<div class="col-md-3 w3l_team_grid">
					<div class="view view-second">
						<img src="images/10.jpg" alt=" " class="img-responsive" />
						<div class="mask"></div>
						<div class="content">
							<div class="w3l_social_icons w3l_social_icons1">
								<ul>
									<li><a href="#" class="facebook"></a></li>
									<li><a href="#" class="twitter"></a></li>
									<li><a href="#" class="google_plus"></a></li>
									<li><a href="#" class="pinterest"></a></li>
									<li><a href="#" class="instagram"></a></li>
								</ul>
							</div>
							<p>Some girls are like sparrows, jumping from stick to stick.</p>
						</div>
					</div>
					<h4>Alexey Palto</h4>
					<p>Android developer</p>
				</div>
				<div class="col-md-3 w3l_team_grid">
					<div class="view view-second">
						<img src="images/6.jpg" alt=" " class="img-responsive" />
						<div class="mask"></div>
						<div class="content">
							<div class="w3l_social_icons w3l_social_icons1">
								<ul>
									<li><a href="#" class="facebook"></a></li>
									<li><a href="#" class="twitter"></a></li>
									<li><a href="#" class="google_plus"></a></li>
									<li><a href="#" class="pinterest"></a></li>
									<li><a href="#" class="instagram"></a></li>
								</ul>
							</div>
							<p>Everyone is jealous, if a sincere love.</p>
						</div>
					</div>
					<h4>Mark Zuckerberg</h4>
					<p>Junior developer</p>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //team -->
<!-- updates -->
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
<!-- //updates -->
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