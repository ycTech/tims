package com.tims.facade.hessian;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * hessian service annotation
 *
 *  Created by peterc on 2017/7/14.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HessianService {
    String description() default "";

    boolean overloadEnabled() default false; // 是否支持方法重载

    String uri(); // 用于服务端bean名称，也是客户端访问链接的后半部分 配置。如: /talentService
}
