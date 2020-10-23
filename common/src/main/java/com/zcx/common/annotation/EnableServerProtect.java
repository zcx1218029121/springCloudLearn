package com.zcx.common.annotation;

import com.zcx.common.config.ServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * spring
 * Import  注解 引入 配资 类 因为@SpringBootApplication 注解本身就 有[配置注解的功能
 * @author zcx
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ServerProtectConfigure.class)
public @interface
EnableServerProtect {
}
