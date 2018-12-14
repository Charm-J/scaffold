## scaffold

#### 项目介绍
springboot 脚手架

#### 软件架构
1. Swagger
	http://127.0.0.1:8888/scaffold/swagger-ui.html
2. Druid
	http://127.0.0.1:8888/scaffold/druid/index.html
3. 使用tk.mybatis操作数据库-Mybatis3支持mapper接口、注解、xml几种方式 
     
    A：ANNOTATEDMAPPER：Mapper接口+Annotation的方式创建（SQL生成在annotation中），不会生成对应的XML 
    
    B：MIXEDMAPPER：使用混合配置 Mapper接口 + Annotation + XML 
    
    C：XMLMAPPER：Mapper接口 + 接口完全依赖的XML 
   本项目使用的是方式 **C**
   
4. db命名-建议
	t_sys_user
	
5. 自定义注解
    A. @Auth验证类或者方法是否有权限
    B. @Log通过切面记录日志
  
6. 支持数据库表逆向工程 框架：mybatis-generator

7. 支持数据库表同步，协同管理。 框架：flywaydb

8. 支持自动生成MK接口文档。 框架：smart-doc

9. 支持图片压缩。框架：谷歌 thumbnailator

10. 支持一级缓存JDK8-Caffeine，和二级缓存redis

11. 支持全局异常和自定义异常

12. 支持thymeleaf

13. 支持HTTP所有请求方式：get,post,put,delete,options(需配置)

14. 支持腾讯云对象存储

15. 提供了几个批处理文件，方便快捷git提交， 按部署环境打包...


	
#### 优化

1. Actuator
    所有的监控都需要调用固定的接口来查看-不方便，UI也不宜于查看和理解
    可以改为spring-boot-admin
2. devtools
    热启动：看个人吧
    https://blog.csdn.net/u014792352/article/details/65631577
3. Undertow Jetty Tomcat 在负载和并发不大的情况下 差异不大
    https://www.cnblogs.com/maybo/p/7784687.html


#### 参考文档
1. https://blog.tengshe789.tech/2018/08/04/springboot/



