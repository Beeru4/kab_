package com.beerus.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author Beerus
 * @Description 数据源配置
 * @Date 2019-07-03
 **/
@Configuration
public class DruidSource {

    @Resource
    private ConnectionParam connectionParam;

    @Bean   //声明其为Bean实例
    @Primary //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(connectionParam.getDbUrl());
        datasource.setUsername(connectionParam.getUsername());
        datasource.setPassword(connectionParam.getPassword());
        datasource.setDriverClassName(connectionParam.getDriverClassName());

        //configuration
        datasource.setInitialSize(connectionParam.getInitialSize());
        datasource.setMinIdle(connectionParam.getMinIdle());
        datasource.setMaxActive(connectionParam.getMaxActive());
        datasource.setMaxWait(connectionParam.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(connectionParam.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(connectionParam.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(connectionParam.getValidationQuery());
        datasource.setTestWhileIdle(connectionParam.isTestWhileIdle());
        datasource.setTestOnBorrow(connectionParam.isTestOnBorrow());
        datasource.setPoolPreparedStatements(connectionParam.isPoolPreparedStatements());
        return datasource;
    }
}
