<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Autoparts Store Adminpanel</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">Adminpanel</a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>
                    <c:set var="salary" scope="session" value='<%=session.getAttribute("login")%>'/>
                    <c:out value="${salary}"/>
                    <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="../.."><i class="fa fa-fw fa-home"></i>Go to main</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="../logout.action"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li >
                    <a href="adminpanel.action"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                </li>
                <li>
                    <a href="adminpanel-users.action"><i class="fa fa-fw fa-users"></i> Users</a>
                </li>
                <li>
                    <a href="adminpanel-add-admin.action"><i class="fa fa-fw fa-plus"></i> Add admin</a>
                </li>
                <li>
                    <a href="adminpanel-cars.action"><i class="fa fa-fw fa-car"></i> Cars</a>
                </li>
                <li class="active">
                    <a href="adminpanel-orders.action"><i class="fa fa-fw fa-usd"></i> Orders</a>
                </li>
                <li>
                    <a href="adminpanel-warehouses.action"><i class="fa fa-fw fa-building"></i> Warehouses</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper" style="min-height: 600px;">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Orders
                    </h1>
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-usd"></i> Orders
                        </li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->

        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <!-- Table -->
                    <div class="panel-heading">
                        <a href="/generateOrdersXLS.action">Generate XLS</a>
                        &middot;
                        <a href="/generateOrdersCSV.action">Generate CSV</a>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">

                            <p>Orders list:</p>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Customer</th>
                                    <th>Details</th>
                                    <th>Amount</th>
                                    <th>actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="orderReports" var="orderReport">
                                    <tr>
                                        <td><s:property value="id"></s:property></td>
                                        <td><s:property value="customer.getFullname()"></s:property></td>
                                        <td>
                                            <s:iterator value="getDetailsList()" var="detailReport">
                                                <s:property value="count"></s:property> x <s:property value="detail.name"></s:property> by <s:property value="detail.getSupplier().getCompanyName()"></s:property> &nbsp;
                                            </s:iterator>
                                        </td>
                                        <td><s:property value="amount"></s:property></td>
                                        <td>
                                            <a href="/generateOrderPDF.action?id=<s:property value="id"/>">PDF</a>

                                        </td>
                                    </tr>
                                </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="js/plugins/morris/raphael.min.js"></script>
<script src="js/plugins/morris/morris.min.js"></script>
<script src="js/plugins/morris/morris-data.js"></script>

</body>

</html>
