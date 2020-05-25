package com.taotao.content.service.impl;

import com.taotao.content.service.JedisClient;
import com.taotao.content.service.TbcontentcategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.*;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TbcontentcategoryServiceImpl implements TbcontentcategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;
    @Value("AD1")
    private String AD1;

    /**
     * 后台内容管理树形结构
     * @param id
     * @return
     */
    @Override
    public List<ItemCatResult> findContentZtree(Long id) {
        List<ItemCatResult> list = new ArrayList<ItemCatResult>();
        List<TbContentCategory>  tbContentCategories = tbContentCategoryMapper.findContentZtree(id);
        for (TbContentCategory tbContentCategory:tbContentCategories) {
            ItemCatResult itemCatResult = new ItemCatResult();
            itemCatResult.setId(tbContentCategory.getId());
            itemCatResult.setName(tbContentCategory.getName());
            itemCatResult.setIsParent(tbContentCategory.getIsParent());
            list.add(itemCatResult);
        }
        return list;
    }

    /**
     * 后台分页展示大广告信息
     * @param categoryId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public LayuiResult findContentTable(Long categoryId,Integer page,Integer limit) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setMsg("");
        List<TbContent> data = tbContentMapper.findContentTable(categoryId,(page-1)*limit,limit);
        layuiResult.setData(data);
        int count = tbContentMapper.findContentTableCount(categoryId);
        layuiResult.setCount(count);
        return layuiResult;
    }

    /**
     * 后台删除大广告信息
     * @param tbContents
     * @param page
     * @param limit
     * @return
     */
    @Override
    public LayuiResult deleteContentByCategoryId(List<TbContent> tbContents, Integer page, Integer limit) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setMsg("");
        layuiResult.setCount(0);
        layuiResult.setData(null);

        if (tbContents.size()<=0){
            return layuiResult;
        }

        int i = tbContentMapper.deleteContentByCategoryId(tbContents);
        if (i<=0){
            return layuiResult;
        }

        Long categoryId = tbContents.get(0).getCategoryId();
        int count = tbContentMapper.findContentTableCount(categoryId);
        if (count<=0){
            return layuiResult;
        }
        layuiResult.setCount(count);

        List<TbContent> data = tbContentMapper.findContentTable(categoryId,(page-1)*limit,limit);
        layuiResult.setData(data);

        jedisClient.del(AD1);
        return layuiResult;
    }

    /**
     * 后台添加大广告信息
     * @param tbContent
     * @param page
     * @param limit
     * @return
     */
    @Override
    public LayuiResult addContent(TbContent tbContent, Integer page, Integer limit) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setMsg("");


        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.addContent(tbContent);

        Long categoryId = tbContent.getCategoryId();

        int count = tbContentMapper.findContentTableCount(categoryId);
        layuiResult.setCount(count);

        List<TbContent> data = tbContentMapper.findContentTable(categoryId,(page-1)*limit,limit);
        layuiResult.setData(data);

        jedisClient.del(AD1);
        return layuiResult;
    }

    /**
     * 在前台页面展示大广告的方法
     * @return
     */
    @Override
    public List<Ad1Node> showAdiNode() {
        //添加缓存
        String json = jedisClient.get(AD1);
        if (StringUtils.isNotBlank(json)){
            List<Ad1Node> nodes = JsonUtils.jsonToPojo(json,List.class);
            return nodes;
        }
        //因为前台的大广告有多个，所以用集合来装
        List<Ad1Node> nodes = new ArrayList<Ad1Node>();
        //查询数据库，得到所有的大广告
        List<TbContent> contents = tbContentMapper.findContentTable(89L, 0, 10);
        for (TbContent content:contents) {
            //封装实体类
            Ad1Node node = new Ad1Node();
            node.setSrcB(content.getPic2());
            node.setHeight(240);
            node.setAlt("");
            node.setWidth(670);
            node.setSrc(content.getPic());
            node.setWidthB(550);
            node.setHref(content.getUrl());
            node.setHeightB(240);
            //添加到集合中
            nodes.add(node);
        }
        jedisClient.set(AD1,JsonUtils.objectToJson(nodes));
        return nodes;
    }
}
