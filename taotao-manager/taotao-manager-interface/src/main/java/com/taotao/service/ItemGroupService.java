package com.taotao.service;

import com.taotao.pojo.TaotaoResult;

public interface ItemGroupService {
    /**
     * 商品添加时规格参数模板展示
     * @param cId
     * @return
     */
    TaotaoResult addItemGroup(Long cId);

    /**
     *自定义规格参数模板
     * @param cId
     * @param params
     * @return
     */
    TaotaoResult addGroup(Long cId, String params);
}
