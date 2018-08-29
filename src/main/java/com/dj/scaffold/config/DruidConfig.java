package com.dj.scaffold.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Primary  // 在同样的DataSource中，首先使用被标注的DataSource
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidConfig extends DataSourceProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidConfig.class);

    /**
     * 因为Spring boot不会主动读取druid的数据源配置，故需要手动实例化数据源
     * 以下初始值为druid的默认值，当配置文件没有配置时，默认使用的值
     */
    private Integer initialSize = 0;
    private Integer minIdle = 0;
    private Integer maxActive = 8;
    private Integer maxPoolPreparedStatementPerConnectionSize = 10;
    private Long maxWait = -1L;
    private Long timeBetweenEvictionRunsMillis = 60000L;
    private Long minEvictableIdleTimeMillis = 1800000L;
    private String validationQuery;
    private Boolean testWhileIdle = true;
    private Boolean testOnBorrow = false;
    private Boolean testOnReturn = false;
    private Boolean poolPreparedStatements = false;
    private String filters;
    private String connectionProperties;

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxWait(Long maxWait) {
        this.maxWait = maxWait;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(super.getUrl());
        datasource.setUsername(super.getUsername());
        datasource.setPassword(super.getPassword());
        datasource.setDriverClassName(super.getDriverClassName());

        // configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setConnectionProperties(connectionProperties);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            LOGGER.error("Set druid filters error!", e);
        }
        return datasource;
    }
}
