package com.gg.eb.api.config;

import com.gg.eb.common.config.SystemConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //获取请求头消息头
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //获取请求对象
        HttpServletRequest request = attributes.getRequest();
        //设置 消息头传递
        requestTemplate.header(SystemConfig.HEARDTOKEN,request.getHeader(SystemConfig.HEARDTOKEN));
    }
}
