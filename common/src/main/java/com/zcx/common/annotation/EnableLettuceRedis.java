package com.zcx.common.annotation;

import com.zcx.common.config.RedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RedisConfigure.class)
public @interface EnableLettuceRedis {
}
