<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Autoparts Store</title>
    <link href="main/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="main/js/jquery-2.1.4.min.js"></script>
    <style>
        .vertical-offset-100{
            padding-top:100px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row vertical-offset-100">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Error</h3>
                </div>
                <div class="panel-body">
                    Something went wrong :(
                </div>
            </div>
        </div>
    </div>
</div>
<!-- for bootstrap working -->
<script src="main/js/bootstrap.js"></script>
<!-- //for bootstrap working -->
</body>
</html>