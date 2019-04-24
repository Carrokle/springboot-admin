package com.lb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.UserRole;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
public interface IUserRoleService extends IService<UserRole> {

    List<UserRole> getByUserId(Long userId);
}
