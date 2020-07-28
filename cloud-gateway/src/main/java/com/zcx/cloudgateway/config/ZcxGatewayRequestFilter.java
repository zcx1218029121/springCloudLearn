package com.zcx.cloudgateway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zcx.cloudgateway.properties.GatewayProperties;
import com.zcx.common.entity.MyResponse;
import com.zcx.common.entity.ZcxConstant;
import com.zcx.common.util.ZcxUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zcx
 */
@Slf4j
@Component
public class ZcxGatewayRequestFilter extends ZuulFilter {
    /**
     * GatewayProperties 拦截  ZuulFilter网关
     */
    @Autowired
    private GatewayProperties properties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();


    /**
     * 配置拦截器的类型
     *
     * @return 拦截器的类型
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * PreDecorationFilter 的order 是5 在 PreDecorationFilter之后获取请求上下文
     */
    @Override
    public int filterOrder() {
        return 6;
    }

    /**
     * 拦截所有的请求
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 为通过网关的请求添加 token
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = ctx.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}，ServerId：{}", uri, method, host, serviceId);
        /**
         * 拦截 URI
         */
        boolean shouldForward = true;
        String forbidRequestUri = properties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            HttpServletResponse response = ctx.getResponse();
            MyResponse febsResponse = new MyResponse().message("该URI不允许外部访问");
            try {

                ZcxUtil.makeResponse(
                        response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                        HttpServletResponse.SC_FORBIDDEN, febsResponse
                );
                ctx.setSendZuulResponse(false);
                ctx.setResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        byte[] token = Base64Utils.encode((ZcxConstant.ZUUL_TOKEN_VALUE).getBytes());
        ctx.addZuulRequestHeader(ZcxConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}
