package com.lb.service.impl;

import com.lb.entity.User;
import com.lb.mapper.UserMapper;
import com.lb.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
