package com.lb;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2019-3-4
 * Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
@WebAppConfiguration
@Slf4j
public class ApplicationTests {

    @Before
    public void init(){
        log.info("开始测试---------");
    }

    @After
    public void after(){
        log.info("结束测试----------");
    }
}
