package com.javamentor.qa.platform.models.util.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface Valid {

    public String message() default "{Hello there}";

    String regexp() default ".*";

//    public boolean isString(String name) {
//        return name.matches("[а-яА-ЯёЁa-zA-Z]+.*$");
//    }
}

