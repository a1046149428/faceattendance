package com.cherbini.faceattendance.commonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;

@Component
public class JedisUtils {
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private static JedisPool object;

    @PostConstruct
    public void init() {
        object = jedisPool;
    }

    public static Jedis getJedisInstance() {

        return object.getResource();
    }
}
