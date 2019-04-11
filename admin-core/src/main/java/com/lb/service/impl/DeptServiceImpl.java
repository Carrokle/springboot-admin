package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.mapper.DeptMapper;
import com.lb.entity.Dept;
import com.lb.service.IDeptService;
import org.springframework.stereotype.Service;

/**
 * 部门管理(sys_dept)表服务实现类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}