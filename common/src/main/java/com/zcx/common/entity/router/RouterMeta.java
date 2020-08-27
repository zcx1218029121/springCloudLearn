package com.zcx.common.entity.router;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: cloud-gateway
 * @Package: com.zcx.common.entity.router
 * @ClassName: RouterMeta
 * @Author: loafer
 * @Description: 路由
 * @Date: 2020/8/18 11:20
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {

    private static final long serialVersionUID = 5499925008927195914L;
    /**
     * 标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
}
