package com.taotao.sso.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户校验
     * @param param
     * @param type
     * @return
     */
    @RequestMapping(value = "/check/{param}/{type}",method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult userCheck(@PathVariable String param,@PathVariable Integer type){
        TaotaoResult taotaoResult = userService.findUser(param,type);
        return taotaoResult;
    }

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userRegister(TbUser tbUser){
        TaotaoResult taotaoResult = userService.addUser(tbUser);
        return taotaoResult;
    }

    /**
     * 用户登录
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userLogin(String userName,String passWord){
        TaotaoResult taotaoResult = userService.findUserByNameAndPass(userName,passWord);
        return taotaoResult;
    }

    /**
     * 通过token获取用户
     * @param token
     * @return
     */
    @RequestMapping(value = "/token/{token}",method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult getUserByToken(@PathVariable String token){
        TaotaoResult taotaoResult = userService.getUserByToken(token);
        return taotaoResult;
    }

    /**
     * 用户退出
     * @param token
     * @return
     */
    @RequestMapping(value = "logout/{token}",method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult userLogout(@PathVariable String token){
        TaotaoResult taotaoResult = userService.deleteUserByToken(token);
        return taotaoResult;
    }
}
