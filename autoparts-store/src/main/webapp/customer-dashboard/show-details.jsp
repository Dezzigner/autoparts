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

    <title>Autoparts Store Dashboard</title>

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
            <a class="navbar-brand" href="index.jsp">Dashboard</a>
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
                <li>
                    <a href="customer-dashboard.action"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                </li>
                <li class="active">
                    <a href="customer-dashboard-details.action"><i class="fa fa-fw fa-wrench"></i> Show details</a>
                </li>
                <li>
                    <a href="customer-dashboard-orders.action"><i class="fa fa-fw fa-usd"></i> Show orders</a>
                </li>
                <li>
                    <a href="customer-dashboard-makeorder.action"><i class="fa fa-fw fa-credit-card"></i> Make order</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <%@include file='show-details-add.jsp'%>

    <div id="page-wrapper" style="min-height: 600px;">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Details
                    </h1>
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-wrench"></i> Show details
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
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div style="color: red;">
                                <s:property value="message"></s:property>
                            </div>
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
                                    <th>actions</th>
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
                                        <td>
                                            <button class="btn btn-primary btn-sm"
                                                    id="<s:property value="id"/>"
                                                    name="<s:property value="name"/>"
                                                    carName="<s:property value="car.getName()"/>"
                                                    releaseYear="<s:property value="car.getReleaseYear()"/>"
                                                    warehouseName="<s:property value="warehouse.getName()"/>"
                                                    warehouseAddress="<s:property value="warehouse.getAddress()"/>"
                                                    companyName="<s:property value="supplier.getComapnyName()"/>"
                                                    onclick="showAddModal(this)">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                Add in order
                                            </button>
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

<script>
    function showAddModal(instance)
    {
        $('#details_add_id').val($(instance).attr('id'));
        $('#details_add_name').val($(instance).attr('name'));
        $('#details_add_car_name').val($(instance).attr('carName'));
        $('#details_add_car_release_year').val($(instance).attr('releaseYear'));
        $('#details_add_warehouse_name').val($(instance).attr('warehouseName'));
        $('#details_add_warehouse_address').val($(instance).attr('warehouseAddress'));
        $('#details_add_company_name').val($(instance).attr('companyName'));
        $('.details_add_modal').modal();
    }
</script>

</body>

</html>
