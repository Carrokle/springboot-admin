package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.mapper.UserMapper;
import com.lb.entity.User;
import com.lb.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户(sys_user)表服务实现类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}