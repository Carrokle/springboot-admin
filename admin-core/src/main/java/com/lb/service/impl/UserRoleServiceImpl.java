package com.lb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.mapper.UserRoleMapper;
import com.lb.entity.UserRole;
import com.lb.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户与角色对应关系(sys_user_role)表服务实现类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public UserRole getByUserId(Integer userId) {
        LambdaQueryWrapper<UserRole> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserRole::getUserId,userId);
        return this.getOne(lqw,false);
    }
}