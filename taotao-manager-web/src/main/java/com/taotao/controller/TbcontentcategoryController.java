package com.taotao.controller;

import com.taotao.content.service.TbcontentcategoryService;
import com.taotao.pojo.ItemCatResult;
import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.TbContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class TbcontentcategoryController {
    @Autowired
    private TbcontentcategoryService tbcontentcategoryService;

    /**
     * 后台内容管理树形结构
     * @param id
     * @return
     */
    @RequestMapping("/showContentZtree")
    @ResponseBody
    public List<ItemCatResult> showContentZtree(@RequestParam(value="id",defaultValue="0")Long id){
        List<ItemCatResult> list = tbcontentcategoryService.findContentZtree(id);
        return list;
    }

    /**
     * 后台分页展示大广告信息
     * @param categoryId
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/showContentTable")
    @ResponseBody
    public LayuiResult showContentTable(Long categoryId,Integer page,Integer limit){
        LayuiResult layuiResult = tbcontentcategoryService.findContentTable(categoryId,page,limit);
        return layuiResult;
    }

    /**
     * 后台删除大广告信息
     * @param tbContents
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/deleteContentByCategoryId")
    @ResponseBody
    public LayuiResult deleteContentByCategoryId(@RequestBody List<TbContent> tbContents,@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="limit",defaultValue="10")Integer limit){
        LayuiResult layuiResult = tbcontentcategoryService.deleteContentByCategoryId(tbContents,page,limit);
        return layuiResult;
    }

    /**
     * 后台添加大广告信息
     * @param tbContent
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/addContent")
    @ResponseBody
    public LayuiResult addContent(TbContent tbContent,@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="limit",defaultValue="10")Integer limit){
        LayuiResult layuiResult = tbcontentcategoryService.addContent(tbContent,page,limit);
        return layuiResult;
    }
}
