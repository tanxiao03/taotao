package com.taotao.mapper;

import com.taotao.pojo.GroupResult;
import com.taotao.pojo.KeyResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbitemparamgroupMapper {
    /**
     * 商品添加时规格参数模板展示
     * @param cId
     * @return
     */
    List<GroupResult> findItemGroup(@Param("cId") Long cId);

    /**
     * 规格参数模板子模板
     * @param id
     * @return
     */
    List<KeyResult> findItemKey(@Param("id") int id);

    /**
     * 添加规格参数的主列
     * @param split
     * @param cId
     */
    void addItemGroup(@Param("split") String split,@Param("cId") Long cId);

    /**
     * 通过groupName和itemCatId查询主列的id
     * @param split
     * @param cId
     * @return
     */
    int findItemGroupId(@Param("split") String split,@Param("cId") Long cId);

    /**
     * 添加规格参数的子列
     * @param split
     * @param id
     */
    void addKeyGroup(@Param("split") String split, @Param("id") int id);
}
