package com.lb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lb.entity.Order;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 Mapper 接口
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
public interface OrderMapper extends BaseMapper<Order> {

}
