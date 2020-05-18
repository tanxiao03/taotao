package com.taotao.order.handler;

import com.taotao.constant.RedisConstant;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import com.taotao.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中取出token
        String token = CookieUtils.getCookieValue(request, RedisConstant.TT_TOKEN);
        if (StringUtils.isBlank(token)){
            //没有在cookie中找到token，则获取当前的url地址已用做登录后的页面跳转
            String url = request.getRequestURL().toString();
            response.sendRedirect(RedisConstant.SSO_LOGIN_URL + "?redirectUrl=" + url);
            return false;
        }
        //代码运行到这里，代表在cookie中找到了token，这时，需要根据token来查用户信息，来判断用户登录是否已经过期
        TaotaoResult userByToken = userService.getUserByToken(token);
        if (userByToken.getStatus() != 200){
            //代码运行到这里，代表着用户登录过期
            String url = request.getRequestURL().toString();
            response.sendRedirect(RedisConstant.SSO_LOGIN_URL + "?redirectUrl=" + url);
            return false;
        }
        //这两行代码是做提交订单功能的时候进行补全用户信息的(将用户信息存入request中)
        TbUser user = (TbUser) userByToken.getData();
        request.setAttribute("user",user);

        //代码运行到这里，代表着查询到了用户的信息，拦截器可以直接放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
