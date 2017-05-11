<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT INNER -->
        <div class="row">
            <div class="col-md-12 col-sm-12">
                <!-- BEGIN PORTLET-->
                <div class="portlet light portlet-index">
                    <div class="portlet-title">
                        <div class="caption caption-md">
                            <i class="icon-bar-chart theme-font-color hide"></i>
                            <span class="caption-subject theme-font-color bold uppercase">欢迎使用</span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="row list-separated">
                            <div class="col-md-3 col-sm-3 col-xs-3 col-md-offset-3">
                                <div class="font-grey-mint font-lg margin-bottom-20">
                                <#if currUser??>
                                    <span class="username username-hide-on-mobile"><#if currUser.userName??>${currUser.userName},</#if> </span>
                                </#if>
                                    欢迎您！
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END PORTLET-->
            </div>
        </div>
        <!-- END PAGE CONTENT INNER -->
    </div>
</div>
<!-- END CONTENT -->