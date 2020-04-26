package com.taotao.controller;

import com.taotao.pojo.ItemCatResult;
import com.taotao.pojo.LayuiResult;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/itemCat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/showZtree")
    @ResponseBody
    public List<ItemCatResult> showZtree(@RequestParam(value="id",defaultValue="0")Long id){
        List<ItemCatResult> itemCatResults = itemCatService.findZtree(id);
        //System.out.println(itemCatResults);
        return itemCatResults;
    }
}
