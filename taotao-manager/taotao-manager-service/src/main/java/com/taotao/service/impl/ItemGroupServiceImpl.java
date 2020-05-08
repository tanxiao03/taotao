package com.taotao.service.impl;

import com.taotao.mapper.TbitemparamgroupMapper;
import com.taotao.pojo.GroupResult;
import com.taotao.pojo.KeyResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.service.ItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemGroupServiceImpl implements ItemGroupService{
    @Autowired
    private TbitemparamgroupMapper tbitemparamgroupMapper;

    /**
     * 商品添加时规格参数模板展示
     * @param cId
     * @return
     */
    @Override
    public TaotaoResult addItemGroup(Long cId) {
        List<GroupResult> groupResults = tbitemparamgroupMapper.findItemGroup(cId);
        if (groupResults.size()<=0){
            return TaotaoResult.build(500,"模板添加失败");
        }
        for (GroupResult groupResult:groupResults) {
            List<KeyResult> keyResults = tbitemparamgroupMapper.findItemKey(groupResult.getId());
            groupResult.setParamKeys(keyResults);
        }
        return TaotaoResult.build(200,"模板添加成功",groupResults);
    }

    /**
     * 自定义规格参数模板
     * @param cId
     * @param params
     * @return
     */
    @Override
    public TaotaoResult addGroup(Long cId, String params) {
        String[] clives = params.split("clive");
        int a = 0;
        for (String string:clives) {
            String[] splits = string.split(",");
            tbitemparamgroupMapper.addItemGroup(splits[0],cId);
            int id = tbitemparamgroupMapper.findItemGroupId(splits[0],cId);
            for (int i = 1;i<splits.length;i++){
                tbitemparamgroupMapper.addKeyGroup(splits[i],id);
                a = 1;
            }
        }
        if (a == 1){
            return TaotaoResult.build(200,"参数模板添加成功");
        }
        return TaotaoResult.build(500,"参数模板添加失败");
    }
}
