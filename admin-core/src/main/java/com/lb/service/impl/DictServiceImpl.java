package com.lb.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.Dict;
import com.lb.mapper.DictMapper;
import com.lb.service.IDictService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

}
