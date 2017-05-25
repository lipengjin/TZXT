<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN EXAMPLE TABLE PORTLET-->
                <div class="portlet box blue">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-plus"></i>录入角色管理
                        </div>
                        <div class="tools">
                            <a href="javascript:;" class="collapse">
                            </a>
                        </div>
                    </div>
                    <div class="portlet-body form">
                        <form role="form" class="form-horizontal" action="${request.contextPath}/users/add-role"
                              method="post" id="new_ledger_form">
                            <div class="form-body">
                                <div class="form-group">
                                    <label for="inputEmail1" class="col-md-2 control-label">角色名称</label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" name="userName"
                                               placeholder="角色名称" value="">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <#list ledgers as ledger>
                                       <div class="col-md-10 col-md-offset-2">
                                        <input type="checkbox" class="form-control" name="ids" value="${ledger.id}">
                                        ${ledger.name}
                                       </div>
                                    </#list>
                                </div>
                            </div>
                            <div class="form-actions right">
                                <a type="button" class="btn default" href="${request.contextPath}/users/manage">取消</a>
                                <button type="submit" class="btn blue">提交新建</button>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
            </div>
        </div>
        <!-- END PAGE CONTENT-->
    </div>
</div>
<!-- END CONTENT -->
<script>

</script>