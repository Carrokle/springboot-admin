package com.lb.service;

import com.lb.ApplicationTests;
import com.lb.service.impl.RedisServiceImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2019-3-29
 * Description:
 */
@Slf4j
public class RedisServiceTest extends ApplicationTests {
    @Autowired
    private RedisServiceImpl redisService;

    @Test
    public void test() throws Exception {
        redisService.set("mykey","123");
        String mykey = redisService.get("mykey");
        log.info("redis中的mykey="+mykey);
    }
}
