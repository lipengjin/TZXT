<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<#include "../components/header.ftl" />
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <#include "../components/ordinary_sider.ftl" />
    <#include "content.ftl" />
</div>
<!-- END CONTAINER -->
<#include "../components/footer.ftl" />
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${request.contextPath}/static/js/respond.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/excanvas.min.js" type="text/javascript"></script>
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
<script src="${request.contextPath}/static/js/jquery.vmap.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.vmap.world.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.vmap.sampledata.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag & drop support -->
<script src="${request.contextPath}/static/js/morris.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/raphael-min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${request.contextPath}/static/js/metronic.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/layout.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/quick-sidebar.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/demo.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/index3.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/tasks.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/time.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function () {
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        Demo.init(); // init demo features
        QuickSidebar.init(); // init quick sidebar
        Index.init(); // init index page
        Tasks.initDashboardWidget(); // init tash dashboard widget
        ShowTime.initTime();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->