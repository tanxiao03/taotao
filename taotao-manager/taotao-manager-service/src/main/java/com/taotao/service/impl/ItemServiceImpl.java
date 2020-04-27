package com.taotao.service.impl;

import com.taotao.constant.FTPConstant;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import com.taotao.utils.FtpUtil;
import com.taotao.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Override
    public TbItem findTbItemById(Long itemId) {
        TbItem tbItem = tbItemMapper.findTbItemById(itemId);
        return tbItem;
    }

    @Override
    public LayuiResult findTbItemByPage(Integer page, Integer limit) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setMsg("");
        List<TbItem> data = tbItemMapper.findTbItemByPage((page-1)*limit,limit);
        layuiResult.setData(data);
        int count = tbItemMapper.findTbItemByCount();
        layuiResult.setCount(count);
        return layuiResult;
    }

    @Override
    public TaotaoResult updataItemById(List<TbItem> items, Date date, int type) {
        TaotaoResult taotaoResult = new TaotaoResult();
        if (items.size()<=0){
            return taotaoResult.build(500,"请先勾选商品",null);
        }
        List<Long> ids = new ArrayList<Long>();
        for (TbItem tbitem:items) {
            ids.add(tbitem.getId());
        }
        int count = tbItemMapper.updataItemById(ids,date,type);

        if (count>0&&type==0){
            return taotaoResult.build(200,"商品下架成功",null);
        }else if (count>0&&type==1){
            return taotaoResult.build(200,"商品上架成功",null);
        }else if (count>0&&type==2){
            return taotaoResult.build(200,"商品删除成功",null);
        }
        return taotaoResult.build(500,"商品修改失败");
    }



    @Override
    public LayuiResult findTbItemBySearch(Integer page, Integer limit, String title, Integer priceMin, Integer priceMax, Long cid) {
        if (priceMin == null){
            priceMin = 0;
        }
        if (priceMax == null){
            priceMax = 1000000000;
        }
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setMsg("");
        List<TbItem> tbItems = tbItemMapper.findTbItemBySearch((page-1)*limit,limit,title,priceMin,priceMax,cid);
        layuiResult.setData(tbItems);
        int count = tbItemMapper.findTbItemCountBySearch(title,priceMin,priceMax,cid);
        layuiResult.setCount(count);
        return layuiResult;
    }

    /**
     * 图片上传
     * @param filename
     * @param bytes
     * @return
     */
    @Override
    public ImgDateResult addPicture(String filename, byte[] bytes) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String filePath = simpleDateFormat.format(date);
        String fileName = IDUtils.genImageName()+filename.substring(filename.lastIndexOf("."));
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        boolean b = FtpUtil.uploadFile(FTPConstant.FTP_ADDRESS,FTPConstant.FTP_PORT,FTPConstant.FTP_USERNAME,FTPConstant.FTP_PASSWORD,FTPConstant.FILI_UPLOAD_PATH,filePath,fileName,bis);
        if (b){
            ImgData imgData = new ImgData();
            imgData.setSrc(FTPConstant.IMAGE_BASE_URL+"/"+filePath+"/"+fileName);
            ImgDateResult imgDateResult = new ImgDateResult();
            imgDateResult.setCode(0);
            imgDateResult.setMsg("");
            imgDateResult.setData(imgData);
            System.out.println(imgData.getSrc());
            return imgDateResult;
        }
        return null;
    }

    /**
     * 添加商品信息
     * @param tbItem
     * @return
     */
    @Override
    public TaotaoResult addItem(TbItem tbItem,String itemDesc) {
        //生成商品id
        Long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        //生成当前时间作为创建和修改的时间
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        //设置状态码
        tbItem.setStatus((byte)1);
        //将商品基本信息添加到数据库中
        int i = tbItemMapper.addItem(tbItem);
        if (i<=0){
            return TaotaoResult.build(500,"商品基本信息添加失败");
        }
        //设置商品描述的参数
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(itemDesc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        //将商品描述信息添加到数据库中
        int j = tbItemDescMapper.addItemDesc(tbItemDesc);
        if (j<=0){
            return TaotaoResult.build(500,"商品描述信息添加失败");
        }
        return TaotaoResult.build(200,"商品信息添加成功");
    }
}