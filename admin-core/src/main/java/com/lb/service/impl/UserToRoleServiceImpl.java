package com.lb.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.UserToRole;
import com.lb.mapper.UserToRoleMapper;
import com.lb.service.IUserToRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Service

public class UserToRoleServiceImpl extends ServiceImpl<UserToRoleMapper, UserToRole> implements IUserToRoleService {

    @Autowired
    private UserToRoleMapper userToRoleMapper;
    @Override
    public UserToRole getByUserNo(String userNo) {
        QueryWrapper<UserToRole> qw = new QueryWrapper<>();
        qw.eq("user_no",userNo);
        /*List<UserToRole> userToRoleList = this.list(qw);
        return ComUtil.isEmpty(userToRoleList) ? null : userToRoleList.get(0);*/
        return this.getOne(qw, false);

    }
}
