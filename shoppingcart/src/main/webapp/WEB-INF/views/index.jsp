<!doctype html>
<html lang="en" ng-app="ShoppingCart">
<head>
    <meta charset="utf-8">
    <title>Shopping Cart</title>
    <!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <!--<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700' rel='stylesheet' type='text/css'>-->
    <link href="resources/css/bootplus/1.0.3/bootplus.min.css" rel="stylesheet" media="screen">
    <link href="resources/css/bootplus/1.0.3/bootplus-responsive.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="resources/css/app.css" media="screen">
    <link rel="stylesheet" href="resources/css/docs.css" media="screen">
    <link href="resources/css/font-awesome/font-awesome.min.css" rel="stylesheet" media="screen">

</head>

<body>
<div id="wrap">

    <!-- Fixed navbar -->
   <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
	            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>
	            <a class="brand" href="#">Shopping Cart</a>
	            <div class="nav-collapse collapse">
	            </div><!--/.nav-collapse -->
	        </div>
	    </div>
	</div>


    <!-- Begin page content -->
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span2">
                <!-- Fixed left nav column -->
                <div ng-include src="'resources/partials/leftnav.html'"></div>
            </div>
            <div class="span10">
                <!-- Breadcrumbs
               ================================================== -->

                <!-- main content  -->
                <section id="mainSection" style="margin-top:0">
                <br/>
                    <ng-view>Loading...</ng-view>
                </section>
            </div>
        </div>
    </div>
    <!-- this div needed to keep footer pinned to bottom -->
    <div id="push"></div>
</div>


<script src="resources/lib/jquery/1.10.2/jquery.js"></script>
<script src="resources/lib/bootstrap/2.3.2/bootstrap.min.js"></script>
<script src="resources/lib/angular/1.1.5/angular.min.js"></script>

<script src="resources/js/app.js"></script>
<script src="resources/js/controllers.js"></script>

</body>
</html>
