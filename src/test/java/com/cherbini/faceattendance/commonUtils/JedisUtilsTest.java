package com.cherbini.faceattendance.commonUtils;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

public class JedisUtilsTest {

    @Test
    public void getJedisInstance() {
      Jedis jedis= JedisUtils.getJedisInstance();
      jedis.set("shi","wenqing");




        System.out.println(jedis.get("shi"));
    }
}