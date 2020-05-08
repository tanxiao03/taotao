package com.taotao.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class GlobalExceptionReslover implements HandlerExceptionResolver {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionReslover.class);

    /**
     * 全局异常处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("系统发生异常",ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("massage","系统发生异常，请稍后再试");
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }
}
