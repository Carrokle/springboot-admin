package com.lb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lb.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getByUserId(Long userId);
}
