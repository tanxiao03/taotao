package com.taotao.service;

import com.taotao.pojo.ItemCatResult;
import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.QianDuan1;

import java.util.List;

public interface ItemCatService {


    List<ItemCatResult> findZtree(Long id);

    QianDuan1 findItemCat();
}
