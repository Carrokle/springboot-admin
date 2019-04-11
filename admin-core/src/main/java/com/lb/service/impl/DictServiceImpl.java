package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.mapper.DictMapper;
import com.lb.entity.Dict;
import com.lb.service.IDictService;
import org.springframework.stereotype.Service;

/**
 * 数据字典表(sys_dict)表服务实现类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

}