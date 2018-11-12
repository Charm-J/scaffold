package com.dj.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@EnableTransactionManagement
@EnableScheduling
// 注意看源码
@SpringBootApplication
// 此处要特别注意-MapperScan引入的是tk.mybatis.spring.annotation.MapperScan而不是org.mybatis.spring.annotation.MapperScan
@MapperScan(basePackages = "com.dj.scaffold.dao")
public class ScaffoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaffoldApplication.class, args);
    }
}
