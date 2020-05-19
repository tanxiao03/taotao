package com.taotao.service;

import com.taotao.pojo.ItemCatResult;
import com.taotao.pojo.QianDuan1;
import com.taotao.pojo.StatisticsItem;

import java.util.List;

public interface ItemCatService {


    List<ItemCatResult> findZtree(Long id);

    QianDuan1 findItemCat();

    /**
     * 统计图（后台）
     * @return
     */
    List<StatisticsItem> findStatisticsItem();
}
