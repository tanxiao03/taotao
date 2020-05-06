package com.taotao.mapper;


import com.taotao.pojo.TbContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbContentMapper {
    @Select("SELECT * FROM tbcontent WHERE categoryId = #{categoryId} LIMIT #{i},#{limit}")
    List<TbContent> findContentTable(@Param("categoryId") Long categoryId, @Param("i")int i, @Param("limit")Integer limit);

    @Select("SELECT COUNT(*) FROM tbcontent WHERE categoryId = #{categoryId}")
    int findContentTableCount(@Param("categoryId")Long categoryId);

    int deleteContentByCategoryId(@Param("tbContents")List<TbContent> tbContents);

    @Insert("INSERT INTO tbcontent(categoryId,title,subTitle,titleDesc,url,pic,pic2,content,created,updated) VALUES (#{categoryId},#{title},#{subTitle},#{titleDesc},#{url},#{pic},#{pic2},#{content},#{created},#{updated})")
    void addContent(TbContent tbContent);
}