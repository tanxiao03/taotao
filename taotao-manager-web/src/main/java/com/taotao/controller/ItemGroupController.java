package com.taotao.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.service.ItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/itemGroup")
public class ItemGroupController {
    @Autowired
    private ItemGroupService itemGroupService;

    /**
     * 商品添加时规格参数模板展示
     * @param cId
     * @return
     */
    @RequestMapping("/showItemGroup")
    @ResponseBody
    public TaotaoResult showItemGroup(Long cId){
        TaotaoResult taotaoResult = itemGroupService.addItemGroup(cId);
        return taotaoResult;
    }

    /**
     * 自定义规格参数模板
     * @return
     */
    @RequestMapping("/addGroup")
    @ResponseBody
    public TaotaoResult addGroup(Long cId,String params){
        TaotaoResult taotaoResult = itemGroupService.addGroup(cId,params);
        return taotaoResult;
    }

}
