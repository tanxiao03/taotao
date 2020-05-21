package com.taotao.controller;

import com.taotao.service.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired
    private JedisClient jedisClient;

    @RequestMapping("/")
    public String showIndex(Model model){
        String userStatus = jedisClient.get("USERSTATUS");
        model.addAttribute("userStatus",userStatus);
        return "index";
    }
}
