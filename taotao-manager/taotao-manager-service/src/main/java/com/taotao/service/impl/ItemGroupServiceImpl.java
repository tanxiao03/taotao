package com.taotao.service.impl;

import com.taotao.mapper.TbitemparamgroupMapper;
import com.taotao.service.ItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemGroupServiceImpl implements ItemGroupService{
    @Autowired
    private TbitemparamgroupMapper tbitemparamgroupMapper;

}
