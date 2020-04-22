package com.taotao.controller;

import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem findTbItem(@PathVariable Long itemId){
        TbItem tbItemById = itemService.findTbItemById(itemId);
        return tbItemById;
    }


    //指定路径的注解
    @RequestMapping("/showItemPage")

    //数据回传到前端的注解
    @ResponseBody

    /**
     * 展示所有数据，并分页的方法
     */
    public LayuiResult showAll(Integer page,Integer limit){
        LayuiResult result = itemService.findTbItemByPage(page, limit);
        return result;
    }

    /**
     * 商品的删除
     * @param tbItems
     * @return
     */
    @RequestMapping("/itemDelete")
    @ResponseBody
    public TaotaoResult itemDelete(@RequestBody List<TbItem> tbItems){
        Date date = new Date();

        TaotaoResult result = itemService.updateItem(tbItems,2,date);
        return result;
    }

    @RequestMapping("/commodityUpperShelves")
    @ResponseBody
    public TaotaoResult commodityUpperShelves(@RequestBody List<TbItem> tbItems){
        Date date = new Date();

        TaotaoResult result = itemService.updateItem(tbItems,1,date);
        return result;
    }

    @RequestMapping("/commodityLowerShelves")
    @ResponseBody
    public TaotaoResult commodityLowerShelves(@RequestBody List<TbItem> tbItems){
        Date date = new Date();

        TaotaoResult result = itemService.updateItem(tbItems,0,date);
        return result;
    }
}
