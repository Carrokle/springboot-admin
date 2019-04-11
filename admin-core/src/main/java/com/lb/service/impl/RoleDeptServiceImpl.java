package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.mapper.RoleDeptMapper;
import com.lb.entity.RoleDept;
import com.lb.service.IRoleDeptService;
import org.springframework.stereotype.Service;

/**
 * 角色与部门对应关系(sys_role_dept)表服务实现类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Service("roleDeptService")
public class RoleDeptServiceImpl extends ServiceImpl<RoleDeptMapper, RoleDept> implements IRoleDeptService {

}