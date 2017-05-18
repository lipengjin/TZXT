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
                                <div class="profile-content">
                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="portlet grey-salsa box">
                                                <div class="portlet-title">
                                                    <div class="caption caption-md">
                                                        <h4>台账 名称和简介</h4>
                                                    </div>
                                                </div>
                                                <div class="portlet-body">
                                                    <div class="form-group">
                                                        <input class="hidden" type="number" id="ledgerId"
                                                               value="<#if ledgerDetail.ledger??>${ledgerDetail.ledger.id}</#if>"/>
                                                        <label class="col-md-5 control-label font-md margin-top-10">台账名称:</label>
                                                        <div class="col-md-7">
                                                            <p class="form-control-static">
                                                                <a href="javascript:;" id="ledgerName" data-type="text"
                                                                   data-pk="1"
                                                                   data-original-title="Ledger Name" class="font-md">
                                                                    <#if ledgerDetail.ledger??><#if ledgerDetail.ledger.name??>${ledgerDetail.ledger.name}</#if></#if>
                                                                </a>
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label col-md-5 font-md margin-top-10">原始表关联:</label>
                                                        <div class="col-md-7">
                                                            <p class="form-control-static font-md margin-top-10">
                                                                <#if ledgerDetail.ledger??><#if ledgerDetail.ledger.sourceTable??>${ledgerDetail.ledger.sourceTable}</#if></#if>
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-md-5 control-label font-md margin-top-10">台账简介:</label>
                                                        <div class="col-md-7">
                                                            <p class="form-control-static">
                                                                <a href="javascript:;" id="ledgerComment"
                                                                   data-type="textarea"
                                                                   data-pk="1"
                                                                   data-original-title="Ledger Comment"
                                                                   class="font-md"><#if ledgerDetail.ledger??><#if ledgerDetail.ledger.comment??>${ledgerDetail.ledger.comment}</#if></#if></a>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="portlet grey-salsa box">
                                                <div class="portlet-title">
                                                    <div class="caption caption-md">
                                                        <h4>操作区</h4>
                                                    </div>
                                                </div>
                                                <div class="portlet-body">
                                                    <div class="btn-group">
                                                        <button class="btn btn-lg green"
                                                                id="createLedgerBtn" <#if exist??>${exist}</#if>> 建表
                                                        </button>
                                                        <button class="btn btn-lg popovers" data-container="body"
                                                                data-trigger="hover" data-placement="right"
                                                                data-content="请在下面选择拉取数据的时间区间（注意时间区间是闭区间），不选择则默认全量拉取！"
                                                                data-original-title="提示" id="pullDataBtn"> 拉取数据
                                                        </button>
                                                    </div>
                                                    <div class="margin-top-10">
                                                        <div class="form-group">
                                                            <div class="col-md-6">
                                                                <label class="control-label">请选择数据拉取时间区间：</label>
                                                            </div>
                                                            <div class="col-md-12">
                                                                <div class="input-group input-large date-picker input-daterange"
                                                                     data-date-format="yyyy-mm"
                                                                     data-date-viewmode="years"
                                                                     data-date-minviewmode="months">
                                                                    <input type="text" class="form-control" name="from" id="start">
                                                                    <span class="input-group-addon">
												                        to </span>
                                                                    <input type="text" class="form-control" name="to" id="end">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="note note-success margin-top-10"
                                                         style="<#if style??>${style}<#else>display: none;</#if>">
                                                        <h4 class="block"> 此表已成功创建了</h4>
                                                    </div>
                                                    <div class="note note-success margin-top-10" style="display: none;"
                                                         id="createLedgerSuccessNote">
                                                        <h4 class="block"> 建表成功</h4>
                                                    </div>
                                                    <div class="note note-danger margin-top-10" style="display: none;"
                                                         id="createLedgerErrorNote">
                                                        <h4 class="block"> 建表失败，请稍后再试</h4>
                                                    </div>
                                                    <div class="note note-info margin-top-10" style="display: none;"
                                                         id="pullDataInfoNote">
                                                        <h4 class="block"> 后台正在拉取数据...</h4>
                                                    </div>
                                                    <div class="note note-danger margin-top-10" style="display: none;"
                                                         id="pullDataErrorNote">
                                                        <h4 class="block"> 后台启动拉取数据失败，请稍后再试</h4>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="portlet grey-salsa box">
                                                <div class="portlet-title">
                                                    <div class="caption caption-md">
                                                        <h4>台账 字段信息</h4>
                                                    </div>
                                                </div>
                                                <div class="portlet-body">
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
                                        </div>
                                    </div>
                                </div>
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