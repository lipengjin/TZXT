<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN EXAMPLE TABLE PORTLET-->
                <div class="portlet box grey-cascade">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-list"></i>台账 列表
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
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="btn-group">
                                        <a class="btn green" href="${request.contextPath}/ledger/create">
                                            创建新的台账 <i class="fa fa-plus"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="btn-group pull-right">
                                        <button class="btn dropdown-toggle" data-toggle="dropdown">Tools <i
                                                class="fa fa-angle-down"></i>
                                        </button>
                                        <ul class="dropdown-menu pull-right">
                                            <li>
                                                <a href="javascript:;">
                                                    保存为PDF </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    导出到Excel </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <#if ledgerPage??>
                        <table class="table table-striped table-bordered table-advance table-hover">
                            <thead>
                            <tr>
                                <th>
                                    <i class="fa fa-list"></i> 台账名
                                </th>
                                <th class="hidden-xs">
                                    <i class="fa fa-comment"></i> 注释
                                </th>
                                <th>
                                    <i class="fa fa-times"></i> 创建时间
                                </th>
                                <th>
                                    <i class="icon-magnifier"> 查看</i>
                                </th>
                                <th>
                                    <i class="fa fa-close"> 删除</i>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                                <#if ledgerPage.size == 0>
                                <tr>
                                    <td colspan="6" align="middle">
                                        <a class="btn green" href="${request.contextPath}/ledger/create">
                                            <i class="fa fa-plus"></i> 创建新的台账
                                        </a>
                                    </td>
                                </tr>
                                </#if>
                                <#list ledgerPage.list as ledger>
                                <tr>
                                    <td>
                                    ${ledger.name}
                                    </td>
                                    <td class="hidden-xs">
                                    ${ledger.comment}
                                    </td>
                                    <td>
                                    ${ledger.createAt?string('yyyy-MM-dd HH:mm:ss')}
                                    </td>
                                    <td>
                                        <a href="${request.contextPath}/ledger/detail/${ledger.id}" class="btn default btn-xs purple">
                                            <i class="icon-magnifier"></i> 查看 </a>
                                    </td>
                                    <td>
                                        <a href="javascript:;" class="btn default btn-xs black">
                                            <i class="fa fa-trash-o"></i> 删除 </a>
                                    </td>
                                </tr>
                                </#list>
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <#if ledgerPage.hasPreviousPage>
                                    <li>
                                        <a href="${request.contextPath}/ledger?pageNo=1&pageSize=${ledgerPage.pageSize}">首页</a>
                                    </li>
                                    <li>
                                        <a href="${request.contextPath}/ledger?pageNo=${ledgerPage.prePage}&pageSize=${ledgerPage.pageSize}">前一页</a>
                                    </li>
                                </#if>
                                <#if !ledgerPage.hasPreviousPage>
                                    <li class="disabled">
                                        <a href="javascript:void(0);">首页</a>
                                    </li>
                                    <li class="disabled">
                                        <a href="javascript:void(0);">前一页</a>
                                    </li>
                                </#if>
                                <#list ledgerPage.navigatepageNums as nav>
                                    <#if nav == ledgerPage.pageNum>
                                        <li class="active"><a href="#">${nav} <span class="sr-only">(current)</span></a></li>
                                    </#if>
                                    <#if nav != ledgerPage.pageNum>
                                        <li>
                                            <a href="${request.contextPath}/ledger?pageNo=${nav}&pageSize=${ledgerPage.pageSize}">${nav}</a>
                                        </li>
                                    </#if>
                                </#list>
                                <#if ledgerPage.hasNextPage>
                                    <li>
                                        <a href="${request.contextPath}/ledger?pageNo=${ledgerPage.nextPage}&pageSize=${ledgerPage.pageSize}">下一页</a>
                                    </li>
                                    <li>
                                        <a href="${request.contextPath}/ledger?pageNo=${ledgerPage.pages}&pageSize=${ledgerPage.pageSize}">尾页</a>
                                    </li>
                                </#if>
                                <#if !ledgerPage.hasNextPage>
                                    <li class="disabled">
                                        <a href="javascript:void(0);">下一页</a>
                                    </li>
                                    <li class="disabled">
                                        <a href="javascript:void(0);">尾页</a>
                                    </li>
                                </#if>
                            </ul>
                        </nav>
                    </#if>
                    </div>
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
            </div>
        </div>
        <!-- END PAGE CONTENT -->
    </div>
</div>
<!-- END CONTENT -->