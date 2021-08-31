## Mybatis-Plus 3.1.0 开发环境热加载实现

说明：要配合 Jrebel 使用

状态：修改mapper.xml文件会提示

    2020-09-10 09:23:35 JRebel: Reconfiguring bean 'testController' [com.prepared.controller.TestController]
    
这会已经重新加载了mapper.xml

说明2：打包部署后不能实现热部署；