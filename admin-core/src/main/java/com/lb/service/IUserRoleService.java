package com.lb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.UserRole;

/**
 * 用户与角色对应关系(sys_user_role)表服务接口
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
public interface IUserRoleService extends IService<UserRole> {
    UserRole getByUserId(Integer userId);
}