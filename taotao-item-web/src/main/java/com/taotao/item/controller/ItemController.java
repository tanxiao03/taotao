package com.taotao.item.controller;

import com.taotao.item.pojo.Item;
import com.taotao.item.service.ItemService;
import com.taotao.pojo.GroupResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;


    /**
     * 查询商品信息展示到详情页面
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping("/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model){
        TbItem tbItem = itemService.findTbItem(itemId);
        Item item = new Item(tbItem);
        model.addAttribute("item",item);
        return "item";
    }

    /**
     * 查询商品描述信息展示到页面
     * @param itemId
     * @return
     */
    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public String showItemDesc(@PathVariable Long itemId){
        TbItemDesc tbItemDesc = itemService.findItemDescById(itemId);
        return tbItemDesc.getItemDesc();
    }

    /**
     * 根据id查询规格参数的 组信息 项信息 值信息
     * @param itemId
     * @return
     */
    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public String showItemParam(@PathVariable Long itemId){
        String result = itemService.findTbItemGroupByItemId(itemId);
        return result;
    }
}
