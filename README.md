# scaffold

#### 项目介绍
springboot 脚手架

#### 软件架构
1. Swagger
	http://127.0.0.1:8888/scaffold/swagger-ui.html
2. Druid
	http://127.0.0.1:8888/scaffold/druid/index.html
3. Actuator 
	http://127.0.0.1:9999/monitor
4. 使用tk.mybatis操作数据库-Mybatis3支持mapper接口，注解，xml几种方式 
     
    A，ANNOTATEDMAPPER：Mapper接口+Annotation的方式创建（SQL生成在annotation中），不会生成对应的XML 
    
    B，MIXEDMAPPER：使用混合配置 Mapper接口 + Annotation + XML 
    
    C，XMLMAPPER：Mapper接口 + 接口完全依赖的XML 
    
   本项目使用的是方式 **C**
   
   
5. db命名-建议
	t_sys_user
	
#### 优化

1. Actuator所有的监控都需要调用固定的接口来查看-不方便;UI也不宜于查看和理解
   可以改为spring-boot-admin
2. devtools--看个人-如果启动时间长-反而不合适
   https://blog.csdn.net/u014792352/article/details/65631577
3. Undertow Jetty Tomcat 在负载和并发不大的情况下 差异不大
   https://www.cnblogs.com/maybo/p/7784687.html

#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 参考文档

1. https://blog.tengshe789.tech/2018/08/04/springboot/



