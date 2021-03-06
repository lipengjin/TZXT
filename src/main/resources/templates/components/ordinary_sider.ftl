<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
            <li class="start active ">
                <a href="javascript:void(0);">
                    <i class="icon-home"></i>
                    <span class="title">采油工程信息查询系统</span>
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="icon-basket"></i>
                    <span class="title">台账查询</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                <#if ledgers??>
                    <#list ledgers as ledger>
                        <li>
                            <a href="${request.contextPath}/ledger/check/${ledger.id}">
                                <i class="fa fa-book"></i>
                            ${ledger.name}</a>
                            </a>
                        </li>
                    </#list>
                </#if>
                </ul>
            </li>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>
<!-- END SIDEBAR -->