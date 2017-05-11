<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed page-sidebar-closed-hide-logo">
<#include "../components/header.ftl" />
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <#include "ordinary_user_profile_sider.ftl" />
    <#include "content.ftl" />
</div>
<!-- END CONTAINER -->
<#include "../components/footer.ftl" />
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${request.contextPath}/static/js/respond.min.js"></script>
<script src="${request.contextPath}/static/js/excanvas.min.js"></script>
<![endif]-->
<script src="${request.contextPath}/static/js/jquery.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${request.contextPath}/static/js/jquery-ui.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${request.contextPath}/static/js/bootstrap-fileinput.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${request.contextPath}/static/js/metronic.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/layout.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/demo.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/profile.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function () {
        // initiate layout and plugins
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
        Demo.init(); // init demo features\
        Profile.init(); // init page demo
    });
</script>
<!-- END JAVASCRIPTS -->
</body>