package com.lb.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lb.entity.Menu;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getByRoleId(Long roleId);

    Set<Menu> getByRoleIds(@Param("ids") Long [] ids);
}
