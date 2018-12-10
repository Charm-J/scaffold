package com.dj.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@EnableTransactionManagement
@EnableScheduling
//支持应用内缓存
@EnableCaching
// 注意看源码
@SpringBootApplication
// 此处要特别注意-MapperScan引入的是tk.mybatis.spring.annotation.MapperScan而不是org.mybatis.spring.annotation.MapperScan
@MapperScan(basePackages = "com.dj.scaffold.dao")
public class ScaffoldApplication extends SpringBootServletInitializer {
    /**
     * 通过继承SpringBootServletInitializer并覆盖configure方式
     * 解决springboot打成war包，部署tomcat后访问404问题
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ScaffoldApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ScaffoldApplication.class, args);
    }
}
