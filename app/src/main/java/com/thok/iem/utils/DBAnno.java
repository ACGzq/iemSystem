package com.thok.iem.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DBAnno {
    boolean isKey() default false;
    boolean isCanNull() default true;
    boolean isCanEmpty() default true;
    boolean hasDefaultVault() default false;
    int defaultIntgerVaule()default 0;
    String defaultStringVaule()default "";
}
