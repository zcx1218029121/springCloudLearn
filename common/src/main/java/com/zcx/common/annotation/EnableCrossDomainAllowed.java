package com.zcx.common.annotation;

import com.zcx.common.config.GateWayCorsConfigure;
import com.zcx.common.config.ServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 允许跨域 注解
 *
 * @author zcx
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(GateWayCorsConfigure.class)
public @interface EnableCrossDomainAllowed {
}
