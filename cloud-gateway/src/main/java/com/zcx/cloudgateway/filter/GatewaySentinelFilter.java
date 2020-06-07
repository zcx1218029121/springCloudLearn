package com.zcx.cloudgateway.filter;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulErrorFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPostFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPreFilter;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.netflix.zuul.ZuulFilter;
import com.zcx.cloudgateway.fallback.GatewayBlockFallbackProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zcx
 * 流量限流
 */

@Slf4j
@Configuration
public class GatewaySentinelFilter {

    @Bean
    public ZuulFilter sentinelZuulPreFilter() {
        return new SentinelZuulPreFilter();
    }

    @Bean
    public ZuulFilter sentinelZuulPostFilter() {
        return new SentinelZuulPostFilter();
    }

    @Bean
    public ZuulFilter sentinelZuulErrorFilter() {
        return new SentinelZuulErrorFilter();
    }

    @PostConstruct
    public void doInit() {
        // config fallback GatewayBlockFallBack
        ZuulBlockFallbackManager.registerProvider(new GatewayBlockFallbackProvider());
        initGatewayRules();
    }

    /**
     * 初始化限流规则
     */
    @SentinelResource()
    private void  initGatewayRules(){

            Set<ApiDefinition> definitions = new HashSet<>();
            Set<ApiPredicateItem> predicateItems = new HashSet<>();
            // 设置访问的url
            predicateItems.add(new ApiPathPredicateItem().setPattern("/auth/captcha"));
            ApiDefinition definition = new ApiDefinition("captcha")
                    .setPredicateItems(predicateItems);
            definitions.add(definition);
            GatewayApiDefinitionManager.loadApiDefinitions(definitions);

            Set<GatewayFlowRule> rules = new HashSet<>();

            rules.add(new GatewayFlowRule("captcha")
                    .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
                    .setParamItem(
                            new GatewayParamFlowItem()
                                    .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
                                    .setFieldName("key")
                                    .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_EXACT)
                                    .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_CLIENT_IP)
                    )
                    // 设置限制数量
                    .setCount(10)
                    // 设置限制时间
                    .setIntervalSec(60)
            );
            GatewayRuleManager.loadRules(rules);
        }

    }
