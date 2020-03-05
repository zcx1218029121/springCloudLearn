package com.zcx.common.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 封装前端分页表格所需数据
     *
     * @param pageInfo pageInfo
     * @return Map<String, Object>
     */
    public static Map<String, Object> getDataTable(IPage<?> pageInfo) {
        Map<String, Object> data = new HashMap<>(2);
        data.put("rows", pageInfo.getRecords());
        data.put("total", pageInfo.getTotal());
        return data;
    }

}
