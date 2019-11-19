package com.zcx.common.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author loafer
 */
public class ZcxUtil {
    /**
     * 组装 http 响应体
     * @param response 要组装的http响应体
     * @param contentType contentType
     * @param status 响应的状态
     * @param value 写入响应流的操作
     * @return 包装好的响应体
     * @throws IOException io异常
     */
    public static HttpServletResponse makeResponse(HttpServletResponse response, String contentType, int status, Object value) throws IOException {
        response.setStatus(status);
        response.setContentType(contentType);
        response.getOutputStream().write(JSONObject.toJSONString(value).getBytes());
        return response;
    }
}
