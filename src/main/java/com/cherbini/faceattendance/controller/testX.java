package com.cherbini.faceattendance.controller;

import com.cherbini.faceattendance.commonUtils.JedisUtils;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController

public class testX {
  @RequestMapping("/sss")
    public String test() {
        Jedis jedis = JedisUtils.getJedisInstance();
        jedis.set("shi", "wenqing");


        return jedis.get("shi");
    }
}
