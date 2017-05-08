<!-- BEGIN BODY -->
<body class="login">
<#include "content.ftl" />
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
<script src="${request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/jquery.backstretch.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${request.contextPath}/static/js/metronic.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/layout.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/demo.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/login-soft.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
        Login.init();
        Demo.init();
        // init background slide images
        $.backstretch([
                    "${request.contextPath}/static/media/bg/1.jpg",
                    "${request.contextPath}/static/media/bg/2.jpg",
                    "${request.contextPath}/static/media/bg/3.jpg",
                    "${request.contextPath}/static/media/bg/4.jpg"
                ], {
                    fade: 1000,
                    duration: 8000
                }
        );
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->