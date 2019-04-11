package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.mapper.MenuMapper;
import com.lb.entity.Menu;
import com.lb.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单管理(sys_menu)表服务实现类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getByRoleId(Integer roleId) {
        return null;
    }
}