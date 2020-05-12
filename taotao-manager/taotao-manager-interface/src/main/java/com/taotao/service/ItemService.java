package com.taotao.service;

import com.taotao.pojo.*;

import java.util.Date;
import java.util.List;

public interface ItemService {
    TbItem findTbItemById(Long itemId);

    LayuiResult findTbItemByPage(Integer page, Integer limit);

    TaotaoResult updataItemById(List<TbItem> items, Date date, int type);

    LayuiResult findTbItemBySearch(Integer page, Integer limit, String title, Integer priceMin, Integer priceMax, Long cid);

    ImgDateResult addPicture(String filename, byte[] bytes);

    TaotaoResult addItem(TbItem tbItem, String itemDesc, List<Integer> paramKeyIds, List<String> paramValue);

}
