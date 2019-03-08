package com.lb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenuByRoleCode(String roleCode);

    /**
     * 获取菜单树形结构
     * @param pId
     * @param menus
     * @return
     */
    List<Menu> treeMenuList(String pId, List<Menu> menus);
}
