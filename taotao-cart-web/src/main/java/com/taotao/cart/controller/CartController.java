package com.taotao.cart.controller;

import com.taotao.constant.RedisConstant;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ItemService itemService;


    /**
     * 将商品添加到购物车
     * @param itemId
     * @param num
     * @return
     */
    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response){
        //调用方法得到cookie中的值
        List<TbItem> cartList = getCartList(request);
        boolean b = false;
        for (TbItem tbitem: cartList) {
            if (tbitem.getId() == itemId.longValue()){
                ///进到这里也就意味着这个商品是存在的,修改商品的数量即可
                tbitem.setNum(tbitem.getNum()+num);
                b = true;
                break;
            }
        }
        //如果没有找到商品，则需要到数据库中查询商品
        if (!b){
            TbItem tbItem = itemService.getItemByItemId(itemId);
            String image = tbItem.getImage();
            if (StringUtils.isNotBlank(image)){
                String[] images = image.split("http");
                tbItem.setImage("http"+images[1]);
            }
            tbItem.setNum(num);
            cartList.add(tbItem);
        }
        //将购物车的商品信息存入cookie中
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartList),60*60*24*7, true);
        return "cartSuccess";
    }

    /**
     * 这是一个获取cookie中值得方法，在addCartItem方法中调用了此方法
     * @param request
     * @return
     */
    public List<TbItem> getCartList(HttpServletRequest request){
        String cookieValue = CookieUtils.getCookieValue(request,"TT_CART", true);
        if (cookieValue != null){
            List<TbItem> tbItems = JsonUtils.jsonToList(cookieValue, TbItem.class);
            return tbItems;
        }
        return new ArrayList<TbItem>();
    }

    /**
     * 跳转到结算页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/cart")
    public String showCartList(HttpServletRequest request, Model model){
        List<TbItem> list = getCartList(request);
        model.addAttribute("cartList",list);
        return "cart";
    }

    /**
     * 删除购物车中的商品信息（通过删除cookie来完成）
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId,HttpServletRequest request,HttpServletResponse response){
        List<TbItem> cartList = getCartList(request);
        for (int i = 0;i<cartList.size();i++) {
            TbItem tbItem = cartList.get(i);
            if(tbItem.getId()==itemId.longValue()){
                cartList.remove(tbItem);
                break;
            }
        }
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(cartList),60*60*24*7,true);
        return "redirect:/cart/cart.html";
    }

    /**
     * 修改购物车中的商品数量
     * @param request
     * @param response
     * @param itemId
     * @param num
     * @return
     */
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public TaotaoResult updateNum(HttpServletRequest request,HttpServletResponse response,@PathVariable Long itemId,@PathVariable Integer num){
        List<TbItem> cartList = getCartList(request);
        for (TbItem tbitem:cartList) {
            tbitem.setNum(num);
        }
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(cartList),60*60*24*7,true);
        return TaotaoResult.ok();
    }
}
