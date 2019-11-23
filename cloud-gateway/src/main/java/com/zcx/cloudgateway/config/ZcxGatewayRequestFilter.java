package com.zcx.cloudgateway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zcx.common.entity.ZcxConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zcx
 */
@Slf4j
@Component
public class ZcxGatewayRequestFilter extends ZuulFilter {
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
     *
     */
    @Override
    public int filterOrder() {
        return 6;
    }

    /**
     * 拦截所以请求
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 为通过网关的请求添加 token
     *
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

        byte[] token = Base64Utils.encode((ZcxConstant.ZUUL_TOKEN_VALUE).getBytes());
        ctx.addZuulRequestHeader(ZcxConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}
