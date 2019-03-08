package com.lb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.Order;

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
