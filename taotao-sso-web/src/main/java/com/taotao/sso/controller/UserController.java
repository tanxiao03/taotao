package com.taotao.sso.controller;

import com.taotao.constant.RedisConstant;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
@Api(value = "登录", tags = "UserController", description = "登录")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户校验
     * @param param
     * @param type
     * @return 用jsonp的形式解决跨域问题
     */
    @RequestMapping(value = "/check/{param}/{type}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "用户校验", notes = "用户校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param",value = "用户信息",required = true,dataType = "String"),
            @ApiImplicitParam(name = "type",value = "校验类型",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "callback",value = "jsonp形式",required = true,dataType = "String")

    })
    public Object userCheck(@PathVariable String param,@PathVariable Integer type,String callback){
        TaotaoResult taotaoResult = userService.findUser(param,type);
        if (StringUtils.isNotBlank(callback)){
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(taotaoResult);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return JsonUtils.objectToJson(taotaoResult);
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
     * @return 完成登录之后需要跨域传输数据
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userLogin(String userName, String passWord, HttpServletRequest request, HttpServletResponse response){
        TaotaoResult taotaoResult = userService.findUserByNameAndPass(userName,passWord);
        CookieUtils.setCookie(request,response, RedisConstant.TT_TOKEN,taotaoResult.getData().toString());
        return taotaoResult;
    }

    /**
     * 通过token获取用户
     * @param token
     * @return 以jsonp的形式获取
     */
    @RequestMapping(value = "/token/{token}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getUserByToken(@PathVariable String token,String callback){

        TaotaoResult taotaoResult = userService.getUserByToken(token);
        if (StringUtils.isNotBlank(callback)){
            return callback+"("+JsonUtils.objectToJson(taotaoResult)+");";
        }
        return JsonUtils.objectToJson(taotaoResult);
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
