package com.lb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.Role;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
public interface IRoleService extends IService<Role> {
    Role getByUserId(Long userId);
}
