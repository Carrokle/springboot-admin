package com.lb.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.OperationLog;
import com.lb.mapper.OperationLogMapper;
import com.lb.service.IOperationLogService;

import org.springframework.stereotype.Service;

/**
 * 操作日志表(tb_operation_log)表服务实现类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:42:32
 */
@Service("operationLogService")
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}