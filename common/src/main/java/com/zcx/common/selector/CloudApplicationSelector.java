package com.zcx.common.selector;

import com.zcx.common.config.OAuth2FeignConfigure;
import com.zcx.common.config.ServerProtectConfigure;
import com.zcx.common.config.ZcxAuthExceptionConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zcx
 * 批量导入配置
 */
public class CloudApplicationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                ServerProtectConfigure.class.getName(),
                ZcxAuthExceptionConfigure.class.getName(),
                OAuth2FeignConfigure.class.getName()
        };
    }
}
