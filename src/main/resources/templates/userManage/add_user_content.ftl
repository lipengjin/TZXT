<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN EXAMPLE TABLE PORTLET-->
                <div class="portlet box blue">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-plus"></i>创建一个用户
                        </div>
                        <div class="tools">
                            <a href="javascript:;" class="collapse">
                            </a>
                        </div>
                    </div>
                    <div class="portlet-body form">
                        <form role="form" class="form-horizontal" action="${request.contextPath}/users/create"
                              method="post" id="new_ledger_form">
                            <div class="form-body">
                                <div class="form-group">
                                    <label for="inputEmail1" class="col-md-2 control-label">用户名称</label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" name="userName"
                                               placeholder="userName" value="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail1" class="col-md-2 control-label">密码</label>
                                    <div class="col-md-4">
                                        <input type="password" class="form-control" name="password"
                                               placeholder="password" value="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail1" class="col-md-2 control-label">真实姓名</label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" name="realName"
                                               placeholder="realName" value="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="unit_select" class="col-md-2 control-label">单位代码</label>
                                    <div class="col-md-4">
                                        <select class="form-control select2_category" id="unit_select"
                                                name="unitId">
                                            <option value="0">请选择单位代码</option>
                                        <#if unitAndRoleAndAuthDto.units??>
                                            <#list unitAndRoleAndAuthDto.units as unit>
                                                <option value="${unit.id}">
                                                ${unit.name}
                                                </option>
                                            </#list>
                                        </#if>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="role_select" class="col-md-2 control-label">用户角色</label>
                                    <div class="col-md-4">
                                        <select class="form-control select2_category" id="role_select"
                                                name="roleId">
                                            <option value="0">请选择用户角色</option>
                                        <#if unitAndRoleAndAuthDto.roles??>
                                            <#list unitAndRoleAndAuthDto.roles as role>
                                                <option value="${role.id}">
                                                ${role.name}
                                                </option>
                                            </#list>
                                        </#if>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="auth_select" class="col-md-2 control-label">权限</label>
                                    <div class="col-md-4">
                                        <select class="form-control select2_category" id="auth_select"
                                                name="auth">
                                            <option value="0">请选择权限</option>
                                        <#if unitAndRoleAndAuthDto.roleAuths??>
                                            <#list unitAndRoleAndAuthDto.roleAuths as roleAuth>
                                                <option value="${roleAuth.id}">
                                                ${roleAuth.auth}
                                                </option>
                                            </#list>
                                        </#if>
                                        </select>
                                    </div>
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