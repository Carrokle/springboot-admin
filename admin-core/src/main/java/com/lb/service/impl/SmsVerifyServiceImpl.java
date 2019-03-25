package com.lb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.SmsVerify;
import com.lb.mapper.SmsVerifyMapper;
import com.lb.service.ISmsVerifyService;

import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<SmsVerify> getByMobileAndCaptchaAndType(String mobile, String captcha, int type) {
        LambdaQueryWrapper<SmsVerify> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SmsVerify::getMobile,mobile)
                .eq(SmsVerify::getSmsVerify,captcha)
                .eq(SmsVerify::getSmsType,type)
                .orderByDesc(SmsVerify::getCreateTime);

        return this.list(lqw);
    }

    @Override
    public SmsVerify getLatestByMobileAndCaptchaAndType(String mobile, String captcha, int type) {
        LambdaQueryWrapper<SmsVerify> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SmsVerify::getMobile, mobile)
                .eq(SmsVerify::getSmsVerify, captcha)
                .eq(SmsVerify::getSmsType, type)
        .orderByDesc(SmsVerify::getCreateTime);
        return this.getOne(lqw);
    }
}
