package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.mapper.RoleMapper;
import com.lb.entity.Role;
import com.lb.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色(sys_role)表服务实现类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}