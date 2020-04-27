package com.taotao.mapper;


import com.taotao.pojo.QianDuan1;
import com.taotao.pojo.TbItemCat;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemCatMapper {
    @Select("SELECT * FROM tbitemcat WHERE parentId = #{id}")
    List<TbItemCat> findZtree(@Param("id") Long id);


}