package com.lb.service;

import com.lb.ApplicationTests;
import com.lb.entity.UserToRole;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2019-3-5
 * Description:
 */
@Slf4j
public class UserToRoleServiceTest extends ApplicationTests {

    @Autowired
    private IUserToRoleService userToRoleService;


    @Test
    public void getByUserNo(){
        UserToRole userRole = userToRoleService.getByUserNo("13");
        Assert.notNull(userRole,"查询结果为空");
        log.info(userRole.toString());
    }
}
