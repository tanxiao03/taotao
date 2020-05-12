package com.taotao.item.listener;

import com.taotao.constant.FreeMakerConstant;
import com.taotao.item.pojo.Item;
import com.taotao.item.service.ItemService;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MyMessageListener implements MessageListener {
    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    private Writer writer;
    @Override
    public void onMessage(Message message) {
        /*TextMessage textMessage = (TextMessage) message;
        try {
            String itemId = textMessage.getText();
            Long id = Long.valueOf(itemId);
            TbItem tbItem = itemService.findTbItem(id);
            TbItemDesc itemDesc = itemService.findItemDescById(id);
            String tbItemGroupByItemId = itemService.findTbItemGroupByItemId(id);
            //指定路径在/WEB-INF/ftl之下
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            //指定模板是item.ftl
            Template template = configuration.getTemplate("item.ftl");

            Map map = new HashMap();
            map.put("item", new Item(tbItem));

            System.out.println(tbItem);

            map.put("itemDesc", itemDesc);

            System.out.println(itemDesc);

            map.put("itemParam", tbItemGroupByItemId);

            System.out.println(tbItemGroupByItemId);

           // writer = new FileWriter(new File(FreeMakerConstant.HTML_OUT_PATH + id + ".html"));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(FreeMakerConstant.HTML_OUT_PATH + id + ".html")),"UTF-8"));
            template.process(map, writer);


        } catch (JMSException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
}
