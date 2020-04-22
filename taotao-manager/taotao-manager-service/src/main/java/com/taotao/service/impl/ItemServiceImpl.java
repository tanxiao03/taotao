package com.taotao.service.impl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Override
    public TbItem findTbItemById(Long itemId) {
        TbItem tbItem = tbItemMapper.findTbItemById(itemId);
        return tbItem;
    }


    /**
     * 分页查询数据的方法
     */
    @Override
    public LayuiResult findTbItemByPage(int page, int limit) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg("");
        int count = tbItemMapper.findTbItemByCount();
        result.setCount(count);
        List<TbItem> data = tbItemMapper.findTbItemByPage((page-1)*limit,limit);
        result.setData(data);
        return result;
    }

    @Override
    public TaotaoResult updateItem(List<TbItem> tbItems, int type, Date date) {
        if (tbItems.size()<=0){
            return TaotaoResult.build(500,"请先勾选要操作的内容",null);
        }
        List<Long> ids = new ArrayList<Long>();
        for (TbItem tbitem:tbItems) {
            ids.add(tbitem.getId());
        }
        int count = tbItemMapper.updateItemByIds(ids,type,date);

        if (count>0&&type==0){
            return TaotaoResult.build(200,"商品下架成功",null);
        }else if (count>0&&type==1){
            return TaotaoResult.build(200,"商品上架成功",null);
        }else if (count>0&&type==2){
            return TaotaoResult.build(200,"商品删除成功",null);
        }
        return TaotaoResult.build(500,"商品修改失败",null);
    }


}
