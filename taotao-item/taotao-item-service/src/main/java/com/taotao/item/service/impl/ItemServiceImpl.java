package com.taotao.item.service.impl;

import com.taotao.item.service.ItemService;
import com.taotao.item.service.JedisClient;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.GroupResult;
import com.taotao.pojo.KeyResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private TbItemParamMapper tbItemParamMapper;



    /**
     * 查询商品信息展示到详情页面
     * @param itemId
     * @return
     */
    @Override
    public TbItem findTbItem(Long itemId) {
        TbItem tbItem = tbItemMapper.findTbItem(itemId);
        return tbItem;
    }

    /**
     * 查询商品描述信息展示到页面
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc findItemDescById(Long itemId) {
        TbItemDesc tbItemDesc = tbItemDescMapper.findItemDescById(itemId);
        return tbItemDesc;
    }

    /**
     *根据id查询规格参数的 组信息 项信息 值信息
     * @param itemId
     * @return
     */
    @Override
    public String findTbItemGroupByItemId(Long itemId) {
        List<GroupResult> itemGroup = tbItemParamMapper.findTbItemGroupByItemId(itemId);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        stringBuffer.append("    <tbody>\n");
        for (GroupResult group:itemGroup) {
            stringBuffer.append("        <tr>\n");
            stringBuffer.append("            <th class=\"tdTitle\" colspan=\"2\">"+group.getGroupName()+"</th>\n");
            stringBuffer.append("        </tr>\n");
            List<KeyResult> paramKeys = group.getParamKeys();
            for (KeyResult paramKey:paramKeys) {
                stringBuffer.append("        <tr>\n");
                stringBuffer.append("            <td class=\"tdTitle\">"+paramKey.getParamName()+"</td>\n");
                stringBuffer.append("            <td>"+paramKey.getTbItemParamValue().getParamValue()+"</td>\n");
                stringBuffer.append("        </tr>\n");
            }
        }
        stringBuffer.append("    </tbody>\n");
        stringBuffer.append("</table>");
        String result = stringBuffer.toString();
        return result;
    }
}
