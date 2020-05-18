package com.taotao.order.controller;

import com.taotao.constant.RedisConstant;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.*;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order-cart")
    public String showOrder(HttpServletRequest request){
        List<TbItem> cartList = getCartList(request);
        request.setAttribute("cartList",cartList);
        return "order-cart";
    }

    /**
     * 这是一个获取cookie中值得方法
     * @return
     */
    public List<TbItem> getCartList(HttpServletRequest request){
        String cookieValue = CookieUtils.getCookieValue(request, RedisConstant.TT_CART, true);
        if (cookieValue != null){
            List<TbItem> tbItems = JsonUtils.jsonToList(cookieValue, TbItem.class);
            return tbItems;
        }
        return new ArrayList<TbItem>();
    }

    /**
     * 提交订单功能（将订单写入数据库）
     * @param orderInfo
     * @param request
     * @return
     */
    @RequestMapping("/create")
    public String createOrder(OrderInfo orderInfo, HttpServletRequest request, Model model, String payment, Integer paymentType){
        TbUser tbUser = (TbUser) request.getAttribute("user");
        TaotaoResult taotaoResult = orderService.addOrder(tbUser,payment,paymentType,orderInfo.getOrderItems(),orderInfo.getOrderShipping());
        String orderId = taotaoResult.getData().toString();
        // a)需要Service返回订单号
        model.addAttribute("orderId", orderId);
        model.addAttribute("payment", payment);
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(3);
        model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));
        return "success";
    }
}
