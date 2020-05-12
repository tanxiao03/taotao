package com.taotao.mapper;


import com.taotao.pojo.GroupResult;
import com.taotao.pojo.TbItemParamValue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemParamMapper {
    /**
     * 添加规格参数的值
     * @param tbItemParamValue
     * @return
     */
    @Insert("INSERT INTO tbitemparamvalue (itemId,paramId,paramValue) VALUES (#{itemId},#{paramId},#{paramValue})")
    int addGroupValue(TbItemParamValue tbItemParamValue);

    /**
     * 根据id查询规格参数的 组信息 项信息 值信息
     * @param itemId
     * @return
     */
    List<GroupResult> findTbItemGroupByItemId(@Param("itemId") Long itemId);
}