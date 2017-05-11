<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<#include "../components/header.ftl" />
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <#include "../components/sider.ftl" />
    <#include "ledger_detail_content.ftl" />
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
<!-- BEGIN PLUGINS USED BY X-EDITABLE -->
<script type="text/javascript" src="${request.contextPath}/static/js/select2.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/wysihtml5-0.3.0.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/bootstrap-wysihtml5.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/moment.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/bootstrap-editable.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/address.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/wysihtml5.js"></script>
<!-- END X-EDITABLE PLUGIN -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${request.contextPath}/static/js/metronic.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/layout.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/demo.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/form-editable.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/ledger-data-manage.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function() {
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
        Demo.init(); // init demo features
        FormEditable.init();
        LedgerDataManage.init();
    });
</script>
</body>
<!-- END BODY -->