<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-6 col-lg-offset-3 col-md-offset-3 col-sm-offset-3">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet light">
                    <div class="portlet-title">
                        <div class="caption font-green">
                            <i class="icon-pin font-green"></i>
                            <span class="caption-subject bold"> 编辑 台账数据字典</span>
                        </div>
                    </div>
                    <div class="portlet-body form">
                    <#if ledgerDictionary??>
                        <form role="form" action="${request.contextPath}/ledger/saveUpdateLedgerDictionary/"
                              method="post">
                            <div class="form-body">
                                <input class="hidden" value="${ledgerDictionary.id}" name="id"/>
                                <input class="hidden" value="${ledgerDictionary.ledgerId}" name="ledgerId"/>
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="ldIndex"
                                           value="<#if ledgerDictionary.ldIndex??>${ledgerDictionary.ldIndex}</#if>"
                                           readonly>
                                    <label for="ldIndex">序号</label>
                                </div>
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="fieldName" name="fieldName" readonly
                                           placeholder="FieldName"
                                           value="<#if ledgerDictionary.fieldName??>${ledgerDictionary.fieldName}</#if>">
                                    <label for="fieldName">字段名称</label>
                                </div>
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" readonly
                                           value="<#if ledgerDictionary.sourceField??>${ledgerDictionary.sourceField}</#if>"
                                           id="sourceField">
                                    <label for="sourceField">原始数据表字段名</label>
                                </div>
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" readonly
                                           value="<#if ledgerDictionary.fieldType??>${ledgerDictionary.fieldType}</#if>"
                                           id="fieldType">
                                    <label for="fieldType">字段类型</label>
                                </div>
                                <div class="form-group form-md-line-input">
                                    <input type="number" class="form-control" readonly
                                           value="<#if ledgerDictionary.length??>${ledgerDictionary.length}</#if>"
                                           id="length">
                                    <label for="length">字段类型长度</label>
                                </div>
                                <div class="form-group form-md-line-input">
                                    <textarea class="form-control" rows="3" placeholder="Ledger Comment" id="ldComment"
                                              name="ldComment"><#if ledgerDictionary.ldComment??>${ledgerDictionary.ldComment}</#if></textarea>
                                    <label for="ldComment">字段注释</label>
                                </div>
                            </div>
                            <div class="form-actions noborder">
                                <button type="submit" class="btn blue">提交更新</button>
                                <button type="button" class="btn default">取消</button>
                            </div>
                        </form>
                    </#if>
                    </div>
                </div>
                <!-- END SAMPLE FORM PORTLET-->
            </div>
        </div>
        <!-- END PAGE CONTENT-->
    </div>
</div>
<!-- END CONTENT -->