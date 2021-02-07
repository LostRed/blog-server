package info.lostred.blog.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>启用用户日志的注解</p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableUserLog {
    @AliasFor("event")
    String value() default "";

    @AliasFor("value")
    String event() default "";
}
