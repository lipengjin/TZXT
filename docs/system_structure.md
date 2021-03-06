## 设计思路

    管理员动态创建台账 --> 保存 元数据 到 台账表和台账数据字典表
    管理员点击创建表 --> 在数据库服务器创建相应的台账表  （根据台账元数据 拼装 SQL 语句）
    管理员点击拉取数据 --> 后台启动一个线程拉取原始数据表中的数据到对应的台账表
    
    普通用户查看台账数据表 --> 根据存储的 台账的元数据 拼装 SQL 语句，展示查到的数据
    

## 应用技术

    后端：Spring + SpringBoot + SpringMVC（MVC框架） + MyBatis（数据持久层框架） 
    
    后端架构：MVC三层架构
    
    
    前端：freemarker（前端模板技术） + Bootstrap（前端CSS样式框架） + jQuery（前端JS框架）
    
    
## 文件下载的方式：

1. 利用 response

    ```java
        IOUtils.write(bao.toByteArray(), response.getOutputStream());
    ```
1. 返回 ResponseEntity<Resource>