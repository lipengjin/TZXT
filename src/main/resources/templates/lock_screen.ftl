<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>台账系统锁屏</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
          type="text/css"/>
    <link href="${request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${request.contextPath}/static/css/lock2.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="${request.contextPath}/static/css/components-rounded.css" id="style_components" rel="stylesheet"
          type="text/css"/>
    <link href="${request.contextPath}/static/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${request.contextPath}/static/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="${request.contextPath}/static/images/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
<div class="page-lock">
    <div class="page-logo">
        <a class="brand" href="javascript:void(0);">
            <img src="${request.contextPath}/static/images/logo-big.png" alt="logo"/>
        </a>
    </div>
    <div class="page-body">
        <img class="page-lock-img" src="${request.contextPath}/static/media/profile/profile.jpg" alt="">
        <div class="page-lock-info">
            <h1>Bob Nilson</h1>
            <span class="email">
			bob@keenthemes.com </span>
            <span class="locked">
			Locked </span>
            <form class="form-inline" action="/reValidate" method="post">
                <div class="input-group input-medium">
                    <input type="password" class="form-control" placeholder="Password" name="password">
                    <span class="input-group-btn">
					<button type="submit" class="btn blue icn-only"><i
                            class="m-icon-swapright m-icon-white"></i></button>
					</span>
                </div>
                <!-- /input-group -->
                <div class="relogin">
                    <a href="login">
                        Not Bob Nilson ? </a>
                </div>
            </form>
        </div>
    </div>
    <div class="page-footer-custom">
        2014 &copy; 台账系统. 采油工程信息管理系统.
    </div>
</div>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${request.contextPath}/static/js/respond.min.js"></script>
<script src="${request.contextPath}/static/js/excanvas.min.js"></script>
<![endif]-->
<script src="${request.contextPath}/static/js/jquery.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${request.contextPath}/static/js/jquery.backstretch.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script src="${request.contextPath}/static/js/metronic.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/layout.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/demo.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/lock.js"></script>
<script>
    jQuery(document).ready(function () {
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
        Lock.init();
        Demo.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>