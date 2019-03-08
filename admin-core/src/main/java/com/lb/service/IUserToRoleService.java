package com.lb.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.UserToRole;

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
public interface IUserToRoleService extends IService<UserToRole> {
    /**
     * 根据用户id查询角色
     * @param userNo
     * @return
     */
    UserToRole getByUserNo(String userNo);
}
