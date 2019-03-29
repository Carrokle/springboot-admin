package com.lb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.Role;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户的编号查询角色
     * @param userNo
     * @return
     */
    Role getByUserNo(String userNo);
}
