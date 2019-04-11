package com.lb.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.Menu;
import com.lb.mapper.MenuMapper;
import com.lb.service.IMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<Menu> treeMenuList(Long pid, List<Menu> menuList) {
        List<Menu> result = new ArrayList<>();
        menuList.forEach(menu ->{
            if(pid.equals(menu.getParentId())){
                List<Menu> childMenuList = treeMenuList(menu.getMenuId(),menuList);
                menu.setChildMenu(childMenuList);
                result.add(menu);
            }
        });
        return result;
    }
}
