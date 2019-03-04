package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.Notice;
import com.lb.mapper.NoticeMapper;
import com.lb.service.INoticeService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息通知表 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
