package com.taotao.portal.controller;

import com.taotao.pojo.Ad1Node;
import com.taotao.pojo.QianDuan1;
import com.taotao.service.ItemCatService;
import com.taotao.service.TbcontentcategoryService;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ItemCatService itemCatService;

    @Autowired
    private TbcontentcategoryService tbcontentcategoryService;

    /**
     * 前台页面大广告展示
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(Model model){
        List<Ad1Node> nodes = tbcontentcategoryService.showAdiNode();
        String ad1 = JsonUtils.objectToJson(nodes);
        model.addAttribute("ad1",ad1);
        return "index";
    }

    @RequestMapping("/itemCat/all")
    @ResponseBody
    public String showItemCat(){
        QianDuan1 qianDuan1 = itemCatService.findItemCat();
        String s = JsonUtils.objectToJson(qianDuan1);
        return s;
    }
}
