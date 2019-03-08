package com.lb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lb.entity.Menu;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getMenuByRoleCode(@Param("roleCode") String roleCode);
}
