package com.taotao.mapper;


import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface TbItemMapper {

    @Select("SELECT * FROM tbitem WHERE id = #{id}")
    TbItem findTbItemById(Long itemId);


    /**
     * 获取数据总数的方法(操作数据库)
     * @return
     */
    @Select("SELECT COUNT(id) FROM tbitem")
    int findTbItemByCount();


    /**
     * 分页查询数据的方法(操作数据库)
     */
    @Select("SELECT * FROM tbitem LIMIT #{index},#{pagesize}")
    List<TbItem> findTbItemByPage(@Param("index") int index, @Param("pagesize") int pagesize);

    /**
     * 商品修改
     * @param ids
     * @param type
     * @param date
     * @return
     */
    int updateItemByIds(@Param("ids") List<Long> ids, @Param("type")int type, @Param("date")Date date);
}