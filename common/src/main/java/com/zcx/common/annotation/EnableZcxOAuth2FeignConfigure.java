package com.zcx.common.annotation;


import com.zcx.common.config.OAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * @author loafer
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(OAuth2FeignConfigure.class)
public @interface EnableZcxOAuth2FeignConfigure {
}
