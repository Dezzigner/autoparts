<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Autoparts Store</title>
	<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="span12">
			<div class="" id="loginModal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3>Register new account here</h3>
				</div>
				<div class="modal-body">
					<div class="well">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#ascustomer" data-toggle="tab">Sign up as customer</a></li>
							<li><a href="#assupplier" data-toggle="tab">Sign up as supplier</a></li>
						</ul>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane active in" id="ascustomer">
								<form class="form-horizontal" action='signup-as-customer.action' method="POST">
									<fieldset>
										&nbsp;
										<div id="legend">
											<legend class="">New customer</legend>
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Login" name="login" type="text">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Password" name="password" type="password" value="">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Repeat password" name="repeatpass" type="password">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Firstname" name="firstname" type="text" value="">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Lastname" name="lastname" type="text">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Middlename" name="middlename" type="text" value="">
										</div>
										&nbsp;
										<div class="form-group">
											<div style="color: red;">
												<s:property value="message"></s:property>
											</div>
											<div class="controls">
												<button class="btn btn-success" type="submit">Create account</button>
											</div>
										</div>
									</fieldset>
								</form>
							</div>
							<div class="tab-pane fade" id="assupplier">
								<form class="form-horizontal" action='signup-as-supplier.action' method="POST">
									<fieldset>
										&nbsp;
										<div id="legend">
											<legend class="">New supplier</legend>
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Login" name="login" type="text">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Password" name="password" type="password" value="">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Repeat password" name="repeatpass" type="password">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Company name" name="companyName" type="text" value="">
										</div>
										&nbsp;
										<div class="form-group">
											<div style="color: red;">
												<s:property value="message"></s:property>
											</div>
											<div class="controls">
												<button class="btn btn-success" type="submit">Create account</button>
											</div>
										</div>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- for bootstrap working -->
<script src="js/bootstrap.js"></script>
<!-- //for bootstrap working -->
</body>
</html>