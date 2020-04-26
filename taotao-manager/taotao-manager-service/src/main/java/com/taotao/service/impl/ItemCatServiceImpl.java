package com.taotao.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.ItemCatResult;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;


    @Override
    public List<ItemCatResult> findZtree(Long id) {
        List<ItemCatResult> list = new ArrayList<ItemCatResult>();
        List<TbItemCat> tbItemCats = tbItemCatMapper.findZtree(id);
        for (TbItemCat tbItemCat:tbItemCats) {
            ItemCatResult itemCatResult = new ItemCatResult();
            itemCatResult.setId(tbItemCat.getId());
            itemCatResult.setName(tbItemCat.getName());

            itemCatResult.setIsParent(tbItemCat.getIsParent());
            list.add(itemCatResult);
        }
        //System.out.println(list);
        return list;
    }
}
