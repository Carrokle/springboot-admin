package com.lb.service;

import com.lb.entity.Order;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 服务类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
public interface IOrderService extends IService<Order> {

}
