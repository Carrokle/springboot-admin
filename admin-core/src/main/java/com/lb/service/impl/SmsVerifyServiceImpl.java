package com.lb.service.impl;

import com.lb.entity.SmsVerify;
import com.lb.mapper.SmsVerifyMapper;
import com.lb.service.ISmsVerifyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 验证码发送记录 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Service
public class SmsVerifyServiceImpl extends ServiceImpl<SmsVerifyMapper, SmsVerify> implements ISmsVerifyService {

}
