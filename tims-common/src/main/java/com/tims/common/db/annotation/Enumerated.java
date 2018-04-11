package com.tims.common.db.annotation;

import com.tims.common.db.enums.EnumType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举类型对象注解，其支持ORDINAL(数字) 和 STRING(字符串) 两种枚举类型
 * 其中字符串枚举类型 需继承com.klxx.common.enums.StringEnum
 * Created by chenp on 2017/9/5.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Enumerated {
    EnumType value() default EnumType.ORDINAL;
}