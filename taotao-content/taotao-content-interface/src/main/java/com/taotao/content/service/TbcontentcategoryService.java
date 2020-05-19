package com.taotao.content.service;

import com.taotao.pojo.Ad1Node;
import com.taotao.pojo.ItemCatResult;
import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.TbContent;

import java.util.List;

public interface TbcontentcategoryService {
    List<ItemCatResult> findContentZtree(Long id);

    LayuiResult findContentTable(Long categoryId,Integer page,Integer limit);

    /**
     * 根据内容id删除指定内容信息
     * @param tbContents
     * @param page
     * @param limit
     * @return
     */
    LayuiResult deleteContentByCategoryId(List<TbContent> tbContents, Integer page, Integer limit);

    LayuiResult addContent(TbContent tbContent, Integer page, Integer limit);

    List<Ad1Node> showAdiNode();
}
