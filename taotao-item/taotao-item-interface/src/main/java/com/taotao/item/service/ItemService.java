package com.taotao.item.service;

import com.taotao.pojo.GroupResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

import java.util.List;

public interface ItemService {
    /**
     * 查询商品信息展示到详情页面
     * @param itemId
     * @return
     */
    TbItem findTbItem(Long itemId);

    /**
     *查询商品描述信息展示到页面
     * @param itemId
     * @return
     */
    TbItemDesc findItemDescById(Long itemId);

    /**
     * 根据id查询规格参数的 组信息 项信息 值信息
     * @param itemId
     * @return
     */
    String findTbItemGroupByItemId(Long itemId);
}
