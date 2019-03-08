package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.entity.Order;
import com.lb.mapper.OrderMapper;
import com.lb.service.IOrderService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
