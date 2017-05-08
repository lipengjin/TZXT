<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<#include "../components/header.ftl" />
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <#include "../components/sider.ftl" />
    <#include "new_ledger_content.ftl" />
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
<script type="text/javascript" src="${request.contextPath}/static/js/select2.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/dataTables.bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${request.contextPath}/static/js/metronic.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/layout.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/demo.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/table-editable.js"></script>
<script>
    jQuery(document).ready(function() {
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
        Demo.init(); // init demo features
        TableEditable.init();
    });
</script>
</body>
<!-- END BODY -->