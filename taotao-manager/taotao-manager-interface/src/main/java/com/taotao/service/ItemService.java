package com.taotao.service;

import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

import java.util.Date;
import java.util.List;

public interface ItemService {
    TbItem findTbItemById(Long itemId);

    /**
     * 分页查询数据的方法
     * @param
     * @param
     * @return
     */
    LayuiResult findTbItemByPage(int page, int limit);

    /**
     * 批量修改商品信息
     * @param tbItems
     * @param type
     * @param date
     * @return
     */
    TaotaoResult updateItem(List<TbItem> tbItems, int type, Date date);
}
