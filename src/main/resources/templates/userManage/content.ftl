<div>
<#list userManageDtos as userManage>
<span>
    ${userManage.id}
</span>

    <span>
    ${userManage.userName}
</span>
    <span>
    ${userManage.realName}
</span>
    <span>
    ${userManage.unitName}
</span>
    <span>
    ${userManage.role}
</span>
    <span>
    ${userManage.auth}
</span>
    <span>
${userManage.locked}
</span>


</#list>


</div>