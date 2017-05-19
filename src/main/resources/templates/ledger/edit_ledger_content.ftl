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
                            <i class="fa fa-edit"></i>
                            编辑 &nbsp;
                            <strong>
                            ${ledger.name}
                            </strong>
                            &nbsp;
                            <small>---${ledger.comment}</small>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <#if ledgerDictionaries??>
                            <form role="form" action="${request.contextPath}/ledger/saveLedgerData/${ledger.id}" method="post">
                                <input class="hidden" value="${ledgerDataSet.id}" name="id"/>
                                <table class="table table-striped table-bordered table-advance table-hover">
                                    <thead>
                                    <tr>
                                        <#list ledgerDictionaries as ld>
                                            <th>
                                            ${ld.fieldName}
                                            </th>
                                        </#list>
                                        <th>
                                            <i class="fa fa-save"> 保存</i>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if ledgerDataSet??>
                                        <tr>
                                            <#list ledgerDataSet.ledgerData as ld>
                                                <td>
                                                    <input name="ledgerData[${ld.index}].name" type="text" class="hidden"
                                                           value="${ld.name}"/>
                                                    <input type="text" class="form-control input-small"
                                                           value="${ld.value?string}" name="ledgerData[${ld.index}].value"/>
                                                </td>
                                            </#list>
                                            <td>
                                                <button type="submit" class="btn default btn-xs purple">
                                                    <i class="fa fa-save"></i> 保存
                                                </button>
                                            </td>
                                        </tr>
                                        </#if>
                                    </tbody>
                                </table>
                            </form>
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