package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.mapper.RoleMenuMapper;
import com.lb.entity.RoleMenu;
import com.lb.service.IRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色与菜单对应关系(sys_role_menu)表服务实现类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}