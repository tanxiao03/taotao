package com.taotao.mapper;


import com.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.Param;

public interface TbItemDescMapper {

    int addItemDesc(TbItemDesc tbItemDesc);

    /**
     * 查询商品描述信息展示到页面
     * @param itemId
     * @return
     */
    TbItemDesc findItemDescById(@Param("itemId") Long itemId);
}