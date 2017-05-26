<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN EXAMPLE TABLE PORTLET-->
                <div class="portlet box blue">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-edit"></i>编辑角色
                        </div>
                        <div class="tools">
                            <a href="javascript:;" class="collapse">
                            </a>
                        </div>
                    </div>
                <#if role??>
                    <div class="portlet-body form">
                        <form role="form" class="form-horizontal form-row-seperated"
                              action="${request.contextPath}/role/saveEditRole"
                              method="post">
                            <div class="form-body">
                                <div class="form-group">
                                    <label for="roleNameInput" class="col-md-3 control-label">角色名称</label>
                                    <div class="col-md-7">
                                        <input type="text" value="${role.id}" class="hidden" name="roleId"/>
                                        <input type="text" class="form-control" name="roleName"
                                               placeholder="角色名称" value="${role.name}" id="roleNameInput" readonly>
                                    </div>
                                </div>
                                <#if ledgers??>
                                    <div class="form-group">
                                        <label for="authSelect" class="control-label col-md-3">选择角色权限</label>
                                        <div class="col-md-7">
                                            <select class="form-control select2_sample1" multiple id="authSelect"
                                                    name="ledgerIds">
                                                <optgroup label="台账形成系统">
                                                    <#list ledgers as ledger>
                                                        <option value="${ledger.id}"> ${ledger.name}</option>
                                                    </#list>
                                                </optgroup>
                                            </select>
                                        </div>
                                    </div>
                                </#if>
                            </div>
                            <div class="form-actions right">
                                <a type="button" class="btn default" href="${request.contextPath}/role">取消</a>
                                <button type="submit" class="btn blue">提交新建</button>
                            </div>
                        </form>
                    </div>
                </#if>
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
            </div>
        </div>
        <!-- END PAGE CONTENT-->
    </div>
</div>
<!-- END CONTENT -->