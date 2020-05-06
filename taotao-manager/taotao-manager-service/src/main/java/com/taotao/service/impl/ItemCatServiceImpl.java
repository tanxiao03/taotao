package com.taotao.service.impl;


import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemCatService;
import com.taotao.service.JedisClient;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("ITEMCAT")
    private String ITEMCAT;

    @Override
    public List<ItemCatResult> findZtree(Long id) {
        List<ItemCatResult> list = new ArrayList<ItemCatResult>();
        List<TbItemCat> tbItemCats = tbItemCatMapper.findZtree(id);
        for (TbItemCat tbItemCat:tbItemCats) {
            ItemCatResult itemCatResult = new ItemCatResult();
            itemCatResult.setId(tbItemCat.getId());
            itemCatResult.setName(tbItemCat.getName());

            itemCatResult.setIsParent(tbItemCat.getIsParent());
            list.add(itemCatResult);
        }
        //System.out.println(list);
        return list;
    }

    /**
     * 在前台页面展示商品分类信息
     * @return
     */
    @Override
    public QianDuan1 findItemCat() {
        QianDuan1 qianDuan1 = new QianDuan1();
        String json = jedisClient.get(ITEMCAT);
        if (StringUtils.isNotBlank(json)){
            List list = JsonUtils.jsonToPojo(json,List.class);
            qianDuan1.setData(list);
            return qianDuan1;
        }
        List<?> list = getItemCatList(0L);
        qianDuan1.setData(list);
        jedisClient.set(ITEMCAT,JsonUtils.objectToJson(list));
        return qianDuan1;
    }
    private List<?> getItemCatList(Long parentId){
        int count = 0;
        List list = new ArrayList();
        List<TbItemCat> tbItemCats = tbItemCatMapper.findZtree(parentId);
        for (TbItemCat itemCat: tbItemCats) {
            QianDuan2 qianDuan2 = new QianDuan2();
            //判断是父级目录还是三级目录
            if (itemCat.getIsParent()){
                //一定是一级或二级目录
                qianDuan2.setUrl("/products/"+itemCat.getId()+".html");
                if (itemCat.getParentId()==0){
                    //一定是一级目录
                    qianDuan2.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
                }else {
                    //一定是二级目录
                    qianDuan2.setName(itemCat.getName());
                }
                qianDuan2.setItem(getItemCatList(itemCat.getId()));
                list.add(qianDuan2);
                count++;
                if (parentId == 0 && count >=14){
                    break;
                }
            }else {
                //一定是三级目录
                list.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
            }
        }
        return list;
    }
}
