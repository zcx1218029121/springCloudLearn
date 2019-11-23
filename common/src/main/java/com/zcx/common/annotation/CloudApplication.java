package com.zcx.common.annotation;

import com.zcx.common.selector.CloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zcx
 * 自定义复合注解
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CloudApplicationSelector.class)
public @interface CloudApplication {
}
