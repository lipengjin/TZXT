<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
    <div class="page-content">
        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN EXAMPLE TABLE PORTLET-->
                <div class="portlet box blue">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-plus"></i>创建一个台账
                        </div>
                        <div class="tools">
                            <a href="javascript:;" class="collapse">
                            </a>
                        </div>
                    </div>
                    <div class="portlet-body form">
                        <form role="form" class="form-horizontal" action="${request.contextPath}/ledger/save"
                              method="post">
                            <div class="form-body">
                                <h4>台账 名称和简介</h4>
                                <div class="form-group">
                                    <label for="inputEmail1" class="col-md-2 control-label">台账名称</label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" name="ledger.name"
                                               placeholder="Ledger name" value="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword12" class="col-md-2 control-label">台账简介</label>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" name="ledger.comment"
                                               placeholder="edger comment" value="">
                                    </div>
                                </div>
                                <hr>
                                <div class="table-toolbar">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="btn-group">
                                                <button id="sample_editable_1_new" class="btn green">
                                                    添加一条字段 <i class="fa fa-plus"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <table class="table table-striped table-hover table-bordered" id="sample_editable_1">
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
                                            注释
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
                                    </tbody>
                                </table>
                            </div>
                            <div class="form-actions right">
                                <button type="button" class="btn default">取消</button>
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