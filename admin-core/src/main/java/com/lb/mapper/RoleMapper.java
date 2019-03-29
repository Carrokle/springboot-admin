package com.lb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lb.entity.Role;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
public interface RoleMapper extends BaseMapper<Role> {

    Role getByUserNo(@Param("userNo") String userNo);
}
