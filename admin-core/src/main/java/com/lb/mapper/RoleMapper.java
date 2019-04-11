package com.lb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lb.entity.Role;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
public interface RoleMapper extends BaseMapper<Role> {
    Role getByUserId(Long userId);
}
