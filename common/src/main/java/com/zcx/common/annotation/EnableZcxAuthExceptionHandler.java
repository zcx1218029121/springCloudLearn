package com.zcx.common.annotation;

import com.zcx.common.config.ZcxAuthExceptionConfigure;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author loafer
 *
 * 自定义注解 导入ZcxAuthExceptionConfigure
 *  SpringBootApplication 继承 @Configuration
 *  bean 在应用开启的时候就自动继承了
 * 因为 @springboot
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(ZcxAuthExceptionConfigure.class)
public @interface EnableZcxAuthExceptionHandler {
}
