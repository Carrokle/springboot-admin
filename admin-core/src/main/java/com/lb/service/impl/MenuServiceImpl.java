package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.base.Constant;
import com.lb.entity.Menu;
import com.lb.mapper.MenuMapper;
import com.lb.service.IMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    //redis方法级别的缓存，需要做缓存打开改注解即可
   // @Cacheable(value = "UserToRole",keyGenerator="wiselyKeyGenerator")
    public List<Menu> getMenuByRoleCode(String roleCode) {
        return menuMapper.getMenuByRoleCode(roleCode);
    }

    @Override
    public List<Menu> treeMenuList(String pId, List<Menu> menuList) {
        List<Menu> result = new ArrayList<>();
        menuList.forEach(m -> {
            if(String.valueOf(m.getParentId()).equals(pId)){
                List<Menu> childMenuList = treeMenuList(String.valueOf(m.getMenuId()),menuList);
                m.setChildMenu(childMenuList);
                if(m.getMenuType() == Constant.TYPE_MENU){
                    result.add(m);
                }
            }
        });
        return result;
    }
}
