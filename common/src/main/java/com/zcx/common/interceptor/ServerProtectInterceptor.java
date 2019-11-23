package com.zcx.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zcx.common.entity.MyResponse;
import com.zcx.common.entity.ZcxConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zcx
 * 添加全局拦截器 检查是否带有 zuul token
 * 如果不带有 zuul token 则拦截
 */
public class ServerProtectInterceptor implements HandlerInterceptor {

    /**
     * 拦截 所有请求
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(ZcxConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(ZcxConstant.ZUUL_TOKEN_VALUE.getBytes()));
        if (StringUtils.equals(zuulToken, token)) {
            return true;
        } else {
            MyResponse febsResponse = new MyResponse();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(febsResponse.message("请通过网关获取资源")));
            return false;
        }
    }


}
