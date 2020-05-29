package com.zcx.common.util;

import com.alibaba.fastjson.JSONObject;
import com.zcx.common.entity.MyResponse;
import com.zcx.common.entity.ZcxConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletResponse;

public class TokenC {
    public static boolean validZuulToken(String token) {
        return StringUtils.equals(token, new String(Base64Utils.encode(ZcxConstant.ZUUL_TOKEN_VALUE.getBytes())));
    }

    public static String base64Encode(String token) {
        byte[] tokenBytes = Base64Utils.encode((token).getBytes());

        return new String(tokenBytes);
    }

    public static String getZuulToken() {
        return base64Encode(ZcxConstant.ZUUL_TOKEN_VALUE);
    }

}
