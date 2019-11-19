package com.zcx.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.zcx.common.entity.MyResponse;
import com.zcx.common.util.ZcxUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author loafer 自定义 权限验证错误
 */
public class ZcxAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ZcxUtil.makeResponse(httpServletResponse,
                MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED,
                MyResponse.buildResult("Token无效"));
    }
}
