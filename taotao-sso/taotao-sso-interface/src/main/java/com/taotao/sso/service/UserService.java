package com.taotao.sso.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
    /**
     * 用户校验
     * @param param
     * @param type
     * @return
     */
    TaotaoResult findUser(String param, Integer type);

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    TaotaoResult addUser(TbUser tbUser);

    /**
     * 用户登录
     * @param userName
     * @param passWord
     * @return
     */
    TaotaoResult findUserByNameAndPass(String userName, String passWord);

    /**
     * 通过token获取用户
     * @param token
     * @return
     */
    TaotaoResult getUserByToken(String token);

    /**
     * 用户退出
     * @param token
     * @return
     */
    TaotaoResult deleteUserByToken(String token);
}
