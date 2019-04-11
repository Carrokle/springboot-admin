package com.lb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.UserRole;
import com.lb.mapper.UserRoleMapper;
import com.lb.service.IUserRoleService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public UserRole getByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserRole::getUserId,userId);
        return this.getOne(lqw,false);
    }
}
