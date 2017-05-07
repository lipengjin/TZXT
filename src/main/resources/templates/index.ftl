<html>
<head>
    <title>台账系统</title>
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- Bootstrap Core CSS -->
    <link href="${request.contextPath}/static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
    <!-- Custom CSS -->
    <link href="${request.contextPath}/static/css/style.css" rel='stylesheet' type='text/css' />
    <!-- Graph CSS -->
    <link href="${request.contextPath}/static/css/font-awesome.css" rel="stylesheet">
    <!-- jQuery -->
    <!-- lined-icons -->
    <link rel="stylesheet" href="${request.contextPath}/static/css/icon-font.min.css" type='text/css' />
    <!-- //lined-icons -->
    <!-- chart -->
    <script src="${request.contextPath}/static/js/Chart.js"></script>
    <!-- //chart -->
    <!--animate-->
    <link href="${request.contextPath}/static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="${request.contextPath}/static/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!----webfonts--->
    <link href='http://fonts.useso.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
    <!---//webfonts--->
    <!-- Meters graphs -->
    <script src="${request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <!-- Placed js at the end of the document so the pages load faster -->
    <title>Home</title>
</head>
<body class="sticky-header left-side-collapsed"  onload="initMap()">
<section>
    <!-- left side start-->
    <div class="left-side sticky-left-side">

        <!--logo and iconic logo start-->
        <div class="logo">
            <h1><a href="index.jsp">采油工程<span></span></a></h1>
        </div>
        <div class="logo-icon text-center">
            <a href="index.jsp"><i class="lnr lnr-home"></i> </a>
        </div>

        <!--logo and iconic logo end-->
        <div class="left-side-inner">

            <!--sidebar nav start-->
            <ul class="nav nav-pills nav-stacked custom-nav">
                <li class="active"><a href="index.jsp"><i class="lnr lnr-power-switch"></i><span>首页</span></a></li>
                <li class="menu-list">
                    <a href="#"><i class="lnr lnr-cog"></i>
                        <span>设置</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="insertuser.jsp">用户管理</a></li>

                        <li><a href="role.jsp">角色管理</a> </li>
                        <li><a href="menu.jsp">菜单管理</a></li>
                    </ul>

                </li>

                <li><a href="tables.jsp"><i class="lnr lnr-menu"></i> <span>台账报表</span></a></li>

                <!--sidebar nav end-->
        </div>
    </div>
    <!-- left side end-->

    <!-- main content start-->
    <div class="main-content">
        <!-- header-starts -->
        <div class="header-section">

            <!--toggle button start-->
            <a class="toggle-btn  menu-collapsed"><i class="fa fa-bars"></i></a>
            <!--toggle button end-->

            <!--notification menu start -->
            <div class="menu-right">
                <div class="user-panel-top">
                    <div class="profile_details_left">
                        <ul class="nofitications-dropdown">
                            <li class="login_box" id="loginContainer">
                                <div class="search-box"></div>
                                <!-- search-scripts -->
                                <script src="js/classie.js"></script>
                                <script src="js/uisearch.js"></script>
                                <script>
                                    new UISearch( document.getElementById( 'sb-search' ) );
                                </script>
                                <!-- //search-scripts -->
                            </li>
                            <div class="clearfix"></div>
                        </ul>
                    </div>
                    <div class="profile_details">
                        <ul>
                            <li class="dropdown profile_details_drop">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <div class="profile_img">
                                        <span style="background:url(images/1.jpg) no-repeat center"> </span>
                                        <div class="user-name">
                                            <p>用户名<span>管理员</span></p>
                                        </div>
                                        <i class="lnr lnr-chevron-down"></i>
                                        <i class="lnr lnr-chevron-up"></i>
                                        <div class="clearfix"></div>
                                    </div>
                                </a>
                                <ul class="dropdown-menu drp-mnu">
                                    <li> <a href="login.jsp"><i class="fa fa-sign-out"></i> 注销</a> </li>
                                </ul>
                            </li>
                            <div class="clearfix"> </div>
                        </ul>
                    </div>
                    <div class="social_icons">
                        <div class="clearfix"> </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <!--notification menu end -->
            <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >网站模板</a></div>
        </div>
        <!-- //header-ends -->

        <div id="page-wrapper">
            <h1><center><span class="label btn_6 label-danger">欢迎来到采油工程生产报表系统</span></center></h1>
            <div class="graphs">
                <div class="col_3">
                    <div class="col-md-3 widget widget1"></div>
                    <div class="col-md-3 widget widget1"></div>
                    <div class="col-md-3 widget widget1"></div>
                    <div class="col-md-3 widget"></div>
                    <div class="clearfix"> </div>
                </div>

                <!-- switches -->
                <div class="switches">
                    <div class="col-4">
                        <div class="clearfix"></div>
                    </div>
                </div>
                <!-- //switches -->
                <div class="col_1">
                    <div class="clearfix"> </div>

                </div>
            </div>
            <!--body wrapper start-->
        </div>
        <!--body wrapper end-->
    </div>
    <!-- main content end-->
</section>

<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>