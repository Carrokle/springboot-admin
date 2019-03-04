package com.lb.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lb.entity.Menu;
import com.lb.mapper.MenuMapper;
import com.lb.service.IMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Menu> selectMenuByRoleCode(String roleCode) {
        return null;
    }

}
