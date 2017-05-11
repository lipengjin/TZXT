<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT-->
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-anchor"></i>台账详情页
                </div>
                <div class="tools">
                    <a href="javascript:;" class="collapse">
                    </a>
                </div>
            </div>
            <div class="portlet-body">
                <div class="row">
                    <div class="col-md-12">
                    <#if ledgerDetail??>
                        <div role="form" class="form-horizontal">
                            <div class="form-body">
                                <h4>台账 名称和简介</h4>
                                <div class="form-group">
                                    <input class="hidden" type="number" id="ledgerId" value="<#if ledgerDetail.ledger??>${ledgerDetail.ledger.id}</#if>"/>
                                    <label class="col-md-3 control-label font-md margin-top-10">台账名称:</label>
                                    <div class="col-md-9">
                                        <p class="form-control-static">
                                            <a href="javascript:;" id="ledgerName" data-type="text" data-pk="1"
                                               data-original-title="Ledger Name" class="font-md">
                                                <#if ledgerDetail.ledger??><#if ledgerDetail.ledger.name??>${ledgerDetail.ledger.name}</#if></#if>
                                            </a>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 font-md margin-top-10">原始表关联:</label>
                                    <div class="col-md-9">
                                        <p class="form-control-static font-md margin-top-10">
                                            <#if ledgerDetail.ledger??><#if ledgerDetail.ledger.sourceTable??>${ledgerDetail.ledger.sourceTable}</#if></#if>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label font-md margin-top-10">台账简介:</label>
                                    <div class="col-md-9">
                                        <p class="form-control-static">
                                            <a href="javascript:;" id="ledgerComment" data-type="textarea" data-pk="1"
                                               data-original-title="Ledger Comment"
                                               class="font-md"><#if ledgerDetail.ledger??><#if ledgerDetail.ledger.comment??>${ledgerDetail.ledger.comment}</#if></#if></a>
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                <h4 class="margin-top-20">台账 字段信息</h4>
                                <table class="table table-bordered table-striped margin-top-20">
                                    <thead>
                                    <tr>
                                        <th>
                                            序号
                                        </th>
                                        <th>
                                            字段名称
                                        </th>
                                        <th>
                                            字段类型
                                        </th>
                                        <th>
                                            长度
                                        </th>
                                        <th>
                                            关联的原始表字段
                                        </th>
                                        <th>
                                            注释
                                        </th>
                                        <th>
                                            编辑
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#list ledgerDetail.ledgerDictionaries as dictionary>
                                        <tr>
                                            <td style="width:10%">
                                                <span class="text-muted">${dictionary.ldIndex} </span>
                                            </td>
                                            <td style="width:15%">
                                                <span class="text-muted">${dictionary.fieldName} </span>
                                            </td>
                                            <td style="width:15%">
                                                <span class="text-muted">${dictionary.fieldType}</span>
                                            </td>
                                            <td style="width:15%">
                                                <span class="text-muted">${dictionary.length} </span>
                                            </td>
                                            <td style="width:15%">
                                                <span class="text-muted">${dictionary.sourceField} </span>
                                            </td>
                                            <td style="width:30%">
                                                <span class="text-muted">${dictionary.ldComment} </span>
                                            </td>
                                            <td>
                                                <a href="${request.contextPath}/ledger/updateLedgerDictionary/${dictionary.id}"
                                                   class="btn default btn-xs green">
                                                    <i class="fa fa-edit"></i> 编辑 </a>
                                            </td>
                                        </tr>
                                        </#list>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </#if>
                    </div>
                </div>
            </div>
        </div>
        <!-- END PAGE CONTENT-->
    </div>
</div>
<!-- END CONTENT -->