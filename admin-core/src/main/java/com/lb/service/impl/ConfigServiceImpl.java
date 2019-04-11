package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.mapper.ConfigMapper;
import com.lb.entity.Config;
import com.lb.service.IConfigService;
import org.springframework.stereotype.Service;

/**
 * 系统配置信息表(sys_config)表服务实现类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}