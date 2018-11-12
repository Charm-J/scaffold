package com.dj.scaffold.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 本地开发访问地址:http://127.0.0.1:8888/scaffold/druid/index.html
 * 文档:https://github.com/alibaba/druid/wiki
 */
@Configuration
public class DruidConfig {

    @Value("${druid.account}")
    private String account;
    @Value("${druid.password}")
    private String password;


    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return dataSource;
    }

    /**
     * 配置要打印的sql
     */
    @Bean
    public Filter statFilter() {
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(1);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }


    /**
     * 注册一个DruidStatViewServlet
     */
    @Bean
    public ServletRegistrationBean setDruidStatViewServlet() {

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");

        // 添加初始化参数：initParams
        // 白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
        //servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        // 登录查看信息的账号密码
        servletRegistrationBean.addInitParameter("loginUsername", account);
        servletRegistrationBean.addInitParameter("loginPassword", password);
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个DruidStatFilter
     */
    @Bean
    public FilterRegistrationBean setDruidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
