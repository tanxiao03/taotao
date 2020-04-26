package com.taotao.controller;

import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
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


    @RequestMapping("/showItemPage")
    @ResponseBody
    public LayuiResult showItemPage(Integer page,Integer limit){
        LayuiResult layuiResult = itemService.findTbItemByPage(page,limit);
        return layuiResult;
    }

    @RequestMapping("/itemDelete")
    @ResponseBody
    public TaotaoResult itemDelete(@RequestBody List<TbItem> items){
        Date date = new Date();
        TaotaoResult taotaoResult = itemService.updataItemById(items,date,2);
        return taotaoResult;
    }

    @RequestMapping("/commodityUpperShelves")
    @ResponseBody
    public TaotaoResult commodityUpperShelves(@RequestBody List<TbItem> items){
        Date date = new Date();
        TaotaoResult taotaoResult = itemService.updataItemById(items,date,1);
        return taotaoResult;
    }

    @RequestMapping("/commodityLowerShelves")
    @ResponseBody
    public TaotaoResult commodityLowerShelves(@RequestBody List<TbItem> items){
        Date date = new Date();
        TaotaoResult taotaoResult = itemService.updataItemById(items,date,0);
        return taotaoResult;
    }

    @RequestMapping("/searchItem")
    @ResponseBody
    public LayuiResult searchItem(Integer page,Integer limit,String title,Integer priceMin,Integer priceMax,Long cid){
        LayuiResult layuiResult = itemService.findTbItemBySearch(page,limit,title,priceMin,priceMax,cid);
        return layuiResult;
    }


    /**
     * 多图片提交
     * @param file
     * @return
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public ImgDateResult fileUpload(MultipartFile file) {

        try {
            String filename = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            ImgDateResult imgDateResult = itemService.addPicture(filename,bytes);
            return imgDateResult;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商品信息添加
     * @param tbItem
     * @param itemDesc
     * @return
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public TaotaoResult addItem(TbItem tbItem,String itemDesc){
        TaotaoResult taotaoResult = itemService.addItem(tbItem,itemDesc);
        return taotaoResult;
    }
}
