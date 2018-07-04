package com.cherbini.faceattendance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisPoolFactory {
    //自动注入redis配置文件
    @Autowired
    private RedisProperties properties;

    @Bean
    public JedisPool getJedisPool() {
        RedisProperties.Pool pool = properties.getJedis().getPool();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(pool.getMaxIdle());
        config.setMaxTotal(pool.getMaxActive());
        config.setMaxWaitMillis(pool.getMaxWait().toMillis());
        JedisPool jedisPool = new JedisPool(config, properties.getHost(), properties.getPort(), 100);//, properties.getPassword()  set password
        return jedisPool;
    }
}
