package com.lb.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lb.entity.Menu;

import java.util.List;

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
}
