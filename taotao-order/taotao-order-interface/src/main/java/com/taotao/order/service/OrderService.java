package com.taotao.order.service;


import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import com.taotao.pojo.TbUser;

import java.util.List;

public interface OrderService {
    /**
     * 提交订单功能（将订单写入数据库）
     * @param tbUser
     * @param payment
     * @param paymentType
     * @param orderItems
     * @param orderShipping
     * @return
     */
    TaotaoResult addOrder(TbUser tbUser, String payment, Integer paymentType, List<TbOrderItem> orderItems, TbOrderShipping orderShipping);
}
