package com.lb.service;

import com.lb.ApplicationTests;
import com.lb.entity.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2019-3-6
 * Description:
 */
@Slf4j
public class UserServiceTest extends ApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    public void getByUserTest(){
        User user = new User();
       /* user.setUserNo("user-006efece76c8433d8974c1a2f98422b6");
        user.setPassWord("$2a$10$VwPL.rHo4PETgCcLDTN2LOwE.ksgCA0jLHbVX5yXEoisHWihX7S/i");*/
        user.setStatus(1);
        List<User> byUser = userService.getByUser(user);
        log.info(byUser.toString());
    }
}
