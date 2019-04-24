package com.lb.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.Menu;
import com.lb.mapper.MenuMapper;
import com.lb.service.IMenuService;
import com.lb.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return menuMapper.getByRoleId(roleId);
    }

    @Override
    public Set<Menu> getByRoleIds(Long[] ids) {
        return menuMapper.getByRoleIds(ids);
    }

    @Override
    public List<Menu> treeMenuList(Long pid, Set<Menu> menuList) {
        List<Menu> result = new ArrayList<>();
        menuList.forEach(menu ->{
            if(pid.equals(menu.getParentId())){
                List<Menu> childMenuList = treeMenuList(menu.getMenuId(),menuList);
                menu.setChildren(childMenuList);
                result.add(menu);
            }
        });
        return result;
    }

    @Override
    public Set<String> getPermissions(List<Menu> menuList) {
        Set<String> permissions = new HashSet<>();
        menuList.forEach(menu -> {
            if(StringUtils.isNotBlank(menu.getPerms())){
                permissions.addAll(Arrays.asList(menu.getPerms().split(",")));
            }
        });
        return permissions;
    }
}
