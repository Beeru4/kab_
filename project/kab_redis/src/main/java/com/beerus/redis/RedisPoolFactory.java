package com.beerus.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author Beerus
 * @Description RedisPoolFactory
 * @Date 2019-06-18
 **/
@Service
@Configuration
@RefreshScope
public class RedisPoolFactory {
    @Value("${redis.host}")
    private String host;  //主机
    @Value("${redis.port}")
    private int port;  //端口
    @Value("${redis.timeout}")
    private int timeout;  //超时时间
    @Value("${redis.maxactive}")
    private int maxActive;  //连接池最大线程数
    @Value("${redis.maxwait}")
    private long maxWait;  //等待时间
    @Value("${redis.maxidle}")
    private int maxIdle;//最大空闲连接

    /**
     * redis连接池的一些配置
     *
     * @return
     */
    @Bean
    public JedisPool JedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxWaitMillis(maxWait);
        JedisPool jedisPool = new JedisPool(poolConfig, host, port, timeout);
        return jedisPool;
    }
}
