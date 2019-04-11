package com.lb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.Menu;

import java.util.List;

/**
 * 菜单管理(sys_menu)表服务接口
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 根据角色ID获取菜单
     * @param roleId
     * @return
     */
    List<Menu> getByRoleId(Integer roleId);
}