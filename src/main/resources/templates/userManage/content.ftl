<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <div class="portlet box blue-hoki">
                    <div class="portlet-title">
                        <div class="caption">
                            用户信息
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-toolbar">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="btn-group">
                                        <a class="btn green" href="${request.contextPath}/users/add-user">
                                            创建新的用户 <i class="fa fa-plus"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <table class="table table-striped table-bordered table-advance table-hover" id="ledgerManage">
                                                <thead>
                                                <tr>
                                                    <th class="hidden">id</th>
                                                    <th>
                                                        用户名
                                                    </th>
                                                    <th>
                                                        真实姓名
                                                    </th>
                                                    <th>
                                                        单位代码
                                                    </th>
                                                    <th>
                                                        用户角色
                                                    </th>
                                                    <th>
                                                        权限代码
                                                    </th>
                                                    <th>
                                                        是否锁定
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <#list userManageDtos as userManage>
                                                <tr>
                                                    <td class="hidden">${userManage.id}</td>
                                                    <td>
                                                    ${userManage.userName}
                                                    </td>
                                                    <td>
                                                    ${userManage.realName}
                                                    </td>
                                                    <td>
                                                    ${userManage.unitName}
                                                    </td>
                                                    <td>
                                                    ${userManage.role}
                                                    </td>
                                                    <td>
                                                    ${userManage.auth}
                                                    </td>
                                                    <#if userManage.locked == 'false'>
                                                        <td>
                                                            <div style="width: 20px;height:20px;border-radius:50%;background:#00ff66;"></div>
                                                        </td>
                                                    </#if>
                                                    <#if userManage.locked == 'true'>
                                                        <td>
                                                            <div style="width: 20px;height:20px;border-radius:50%;background: red;"></div>
                                                        </td>
                                                    </#if>

                                                </tr>
                                                </#list>
                                                </tbody>
                                            </table>
                                    </div>
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </div>
     </div>
</div>

