package com.taotao.controller;

import com.taotao.service.ItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itemGroup")
public class ItemGroupController {
    @Autowired
    private ItemGroupService itemGroupService;



}
