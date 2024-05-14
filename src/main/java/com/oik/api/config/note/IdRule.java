package com.oik.api.config.note;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface IdRule {
    /**
     * @return 主键头部
     */
    String code() default "DEFAULT";
}
