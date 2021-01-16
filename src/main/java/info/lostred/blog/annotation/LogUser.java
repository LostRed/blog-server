package info.lostred.blog.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>开启用户日志的注解</p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogUser {
    @AliasFor("event")
    String value() default "";

    @AliasFor("value")
    String event() default "";
}
