<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <div class="portlet box blue-hoki">
                    <div class="portlet-title">
                        <div class="caption">
                            角色管理
                        </div>
                        <div class="tools">
                            <a href="javascript:;" class="collapse">
                            </a>
                            <a href="javascript:;" class="reload">
                            </a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-toolbar">
                            <div class="btn-group">
                                <a class="btn green" href="${request.contextPath}/role/addRole">
                                    创建角色 <i class="fa fa-plus"></i>
                                </a>
                            </div>
                        </div>
                    <#if roleModels??>
                        <table class="table table-striped table-bordered table-advance table-hover"
                               id="ledgerManage">
                            <thead>
                            <tr>
                                <th class="hidden">id</th>
                                <th>
                                    角色名称
                                </th>
                                <th>
                                    角色权限
                                </th>
                                <th>
                                    编辑
                                </th>
                                <th>
                                    删除
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                                <#list roleModels as roleModel>
                                <tr>
                                    <td class="hidden">${roleModel.role.id}</td>
                                    <td>
                                    ${roleModel.role.name}
                                    </td>
                                    <td>
                                    ${roleModel.auths}
                                    </td>
                                    <td>
                                        <#if roleModel.role.name == '系统管理员' || roleModel.role.name == 'Guest'>
                                            <a href="${request.contextPath}/role/edit/${roleModel.role.id}"
                                               class="btn default btn-xs purple" disabled>
                                                <i class="fa fa-edit"></i> 编辑 </a>
                                        <#else >
                                            <a href="${request.contextPath}/role/edit/${roleModel.role.id}"
                                               class="btn default btn-xs purple">
                                                <i class="fa fa-edit"></i> 编辑 </a>
                                        </#if>
                                    </td>
                                    <td>
                                        <#if roleModel.role.name == '系统管理员' || roleModel.role.name == 'Guest'>
                                            <a href="javascript:;" class="btn default btn-xs black delete" disabled>
                                                <i class="fa fa-trash-o"></i> 删除 </a>
                                        <#else >
                                            <a href="${request.contextPath}/role/delete/${roleModel.role.id}" class="btn default btn-xs black delete">
                                                <i class="fa fa-trash-o"></i> 删除 </a>
                                        </#if>
                                    </td>
                                </tr>
                                </#list>
                            </tbody>
                        </table>
                    </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

