package com.taotao.order.service.impl;

import com.taotao.constant.RedisConstant;
import com.taotao.mapper.TbOrderMapper;

import com.taotao.order.service.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private JedisClient jedisClient;

    /**
     * 提交订单功能（将订单写入数据库）
     * @param tbUser
     * @param payment
     * @param paymentType
     * @param orderItems
     * @param orderShipping
     * @return
     */
    @Override
    public TaotaoResult addOrder(TbUser tbUser, String payment, Integer paymentType, List<TbOrderItem> orderItems, TbOrderShipping orderShipping) {
        TbOrder tbOrder = new TbOrder();
        if (!jedisClient.exists(RedisConstant.ORDER_GEN_KEY)){
            jedisClient.set(RedisConstant.ORDER_GEN_KEY,RedisConstant.ORDER_ID_BEGIN);
        }
        String orderId = jedisClient.incr(RedisConstant.ORDER_GEN_KEY).toString();
        tbOrder.setOrderId(orderId);
        tbOrder.setPostFee("0");
        tbOrder.setStatus(1);
        Date date = new Date();
        tbOrder.setCreateTime(date);
        tbOrder.setUpdateTime(date);
        tbOrder.setUserId(tbUser.getId());
        tbOrder.setBuyerNick(tbUser.getUserName());
        int i = tbOrderMapper.addOrder(tbOrder);
        if(i<=0){
            return TaotaoResult.build(500,"生成订单失败");
        }
        for (TbOrderItem orderItem:orderItems) {
            String orderItemId = jedisClient.incr(RedisConstant.ORDER_ITEM_ID_GEN_KEY).toString();
            orderItem.setOrderId(orderId);
            orderItem.setId(orderItemId);
            int j = tbOrderMapper.orderItems(orderItem);
            if(j<=0){
                return TaotaoResult.build(500,"生成订单失败");
            }
        }
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        int x = tbOrderMapper.addOrderShipping(orderShipping);
        if(x<=0){
            return TaotaoResult.build(500,"生成订单失败");
        }
        return TaotaoResult.ok(orderId);
    }
}
