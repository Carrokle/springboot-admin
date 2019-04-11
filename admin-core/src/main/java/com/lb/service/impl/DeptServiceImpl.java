package com.lb.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.Dept;
import com.lb.mapper.DeptMapper;
import com.lb.service.IDeptService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
