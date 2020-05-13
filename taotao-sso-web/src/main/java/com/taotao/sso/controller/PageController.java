package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * 用户注册侧界面跳转
     * @return
     */
    @RequestMapping("/register")
    public String showRegister(){
        return "register";
    }

    /**
     * 用户登录界面跳转
     * @return
     */
    @RequestMapping("/login")
    public String showLogin(){
        return "login";
    }
}
