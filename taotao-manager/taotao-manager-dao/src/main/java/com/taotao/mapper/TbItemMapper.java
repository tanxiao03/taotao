package com.taotao.mapper;


import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface TbItemMapper {

    @Select("SELECT * FROM tbitem WHERE id = #{id}")
    TbItem findTbItemById(Long itemId);

    @Select("SELECT * FROM tbitem LIMIT #{i},#{limit}")
    List<TbItem> findTbItemByPage(@Param("i") int i, @Param("limit") Integer limit);

    @Select("SELECT COUNT(*) FROM tbitem")
    int findTbItemByCount();

    int updataItemById(@Param("ids") List<Long> ids, @Param("date")Date date, @Param("type")int type);

    List<TbItem> findTbItemBySearch(@Param("page")Integer page, @Param("limit")Integer limit, @Param("title")String title, @Param("priceMin")Integer priceMin, @Param("priceMax")Integer priceMax, @Param("cid")Long cid);


    int findTbItemCountBySearch(@Param("title")String title, @Param("priceMin")Integer priceMin, @Param("priceMax")Integer priceMax, @Param("cid")Long cid);

    int addItem(TbItem tbItem);
}