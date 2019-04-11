package com.lb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getByRoleId(Long roleId);

    /**
     * 将菜单构造为树形结构
     * @param pid
     * @param menuList
     * @return
     */
    List<Menu> treeMenuList(Long pid, List<Menu> menuList);

}
