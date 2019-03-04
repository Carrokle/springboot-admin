package com.lb.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lb.entity.UserToRole;
import com.lb.mapper.UserToRoleMapper;
import com.lb.service.IUserToRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lb.util.ComUtil;

import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public UserToRole selectByUserNo(String userNo) {
        EntityWrapper<UserToRole> ew = new EntityWrapper<>();
        ew.where("user_no={0}",userNo);
        List<UserToRole> userToRoleList = this.selectList(ew);
        return ComUtil.isEmpty(userToRoleList) ? null : userToRoleList.get(0);
    }
}
