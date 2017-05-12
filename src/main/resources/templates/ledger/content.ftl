<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN EXAMPLE TABLE PORTLET-->
                <div class="portlet box blue-hoki">
                <#if ledger??>
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-book"></i>${ledger.name}
                            &nbsp;
                            <small>---${ledger.comment}</small>
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
                                    <form role="form" class="form-horizontal"
                                          action="${request.contextPath}/ledger/check/${ledger.id}" method="post">
                                        <div class="form-body">
                                            <div class="form-group">
                                                <label class="control-label col-md-3" for="unit_select">选择单位</label>
                                                <div class="col-md-4">
                                                    <select class="form-control select2_category" id="unit_select"
                                                            name="unitId">
                                                        <option value="0">请选择单位</option>
                                                        <#if units??>
                                                            <#list units as unit>
                                                                <#if queryParam??>
                                                                    <#if queryParam.unitId == unit.id>
                                                                        <option selected value="${unit.id}">
                                                                        ${unit.name}
                                                                        </option>
                                                                    <#else>
                                                                        <option value="${unit.id}">
                                                                        ${unit.name}
                                                                        </option>
                                                                    </#if>
                                                                <#else>
                                                                    <option value="${unit.id}">
                                                                    ${unit.name}
                                                                    </option>
                                                                </#if>
                                                            </#list>
                                                        </#if>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3" for="mouth_select">选择月份</label>
                                                <div class="col-md-4">
                                                    <div class="input-group input-medium date date-picker"
                                                         data-date-format="yyyy-mm"
                                                         data-date-viewmode="years" data-date-minviewmode="months">
                                                        <input type="text" class="form-control" id="mouth_select"
                                                               name="mouth"
                                                               value="<#if queryParam??>${queryParam.mouth}</#if>"
                                                               readonly>
                                                        <span class="input-group-btn">
												            <button class="btn default" type="button"><i
                                                                    class="fa fa-calendar"></i></button>
												        </span>
                                                    </div>
                                                </div>
                                                <div class="col-md-5">
                                                    <button type="submit" class="btn blue-madison">查询 <i
                                                            class="m-icon-swapright m-icon-white"></i></button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
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
                        <#if ledgerDictionaries??>
                            <table class="table table-striped table-bordered table-advance table-hover">
                                <thead>
                                <tr>
                                    <#list ledgerDictionaries as ld>
                                        <th>
                                        ${ld.fieldName}
                                        </th>
                                    </#list>
                                    <th>
                                        <i class="fa fa-newspaper-o"> 创建时间</i>
                                    </th>
                                    <th>
                                        <i class="fa fa-refresh"> 更新时间</i>
                                    </th>
                                    <th>
                                        <i class="fa fa-edit"> 编辑</i>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if ledgerDataSets??>
                                        <#list ledgerDataSets as lds>
                                        <tr>
                                            <#list lds.ledgerData as ld>
                                                <td>
                                                ${ld.value}
                                                </td>
                                            </#list>

                                            <td>
                                                <a href="${request.contextPath}/ledger/detail/${lds.id}"
                                                   class="btn default btn-xs purple">
                                                    <i class="fa fa-edit"></i> 编辑 </a>
                                            </td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>
                        <#--<nav aria-label="Page navigation">-->
                        <#--<ul class="pagination">-->
                        <#--<#if ledgerPage.hasPreviousPage>-->
                        <#--<li>-->
                        <#--<a href="${request.contextPath}/ledger?pageNo=1&pageSize=${ledgerPage.pageSize}">首页</a>-->
                        <#--</li>-->
                        <#--<li>-->
                        <#--<a href="${request.contextPath}/ledger?pageNo=${ledgerPage.prePage}&pageSize=${ledgerPage.pageSize}">前一页</a>-->
                        <#--</li>-->
                        <#--</#if>-->
                        <#--<#if !ledgerPage.hasPreviousPage>-->
                        <#--<li class="disabled">-->
                        <#--<a href="javascript:void(0);">首页</a>-->
                        <#--</li>-->
                        <#--<li class="disabled">-->
                        <#--<a href="javascript:void(0);">前一页</a>-->
                        <#--</li>-->
                        <#--</#if>-->
                        <#--<#list ledgerPage.navigatepageNums as nav>-->
                        <#--<#if nav == ledgerPage.pageNum>-->
                        <#--<li class="active"><a href="#">${nav} <span class="sr-only">(current)</span></a></li>-->
                        <#--</#if>-->
                        <#--<#if nav != ledgerPage.pageNum>-->
                        <#--<li>-->
                        <#--<a href="${request.contextPath}/ledger?pageNo=${nav}&pageSize=${ledgerPage.pageSize}">${nav}</a>-->
                        <#--</li>-->
                        <#--</#if>-->
                        <#--</#list>-->
                        <#--<#if ledgerPage.hasNextPage>-->
                        <#--<li>-->
                        <#--<a href="${request.contextPath}/ledger?pageNo=${ledgerPage.nextPage}&pageSize=${ledgerPage.pageSize}">下一页</a>-->
                        <#--</li>-->
                        <#--<li>-->
                        <#--<a href="${request.contextPath}/ledger?pageNo=${ledgerPage.pages}&pageSize=${ledgerPage.pageSize}">尾页</a>-->
                        <#--</li>-->
                        <#--</#if>-->
                        <#--<#if !ledgerPage.hasNextPage>-->
                        <#--<li class="disabled">-->
                        <#--<a href="javascript:void(0);">下一页</a>-->
                        <#--</li>-->
                        <#--<li class="disabled">-->
                        <#--<a href="javascript:void(0);">尾页</a>-->
                        <#--</li>-->
                        <#--</#if>-->
                        <#--</ul>-->
                        <#--</nav>-->
                        </#if>
                    </div>
                </#if>
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
            </div>
        </div>
        <!-- END PAGE CONTENT -->
    </div>
</div>
<!-- END CONTENT -->