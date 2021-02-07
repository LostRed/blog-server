package info.lostred.blog.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>启用管理员日志的注解</p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableAdminLog {
    @AliasFor("event")
    String value() default "";

    @AliasFor("value")
    String event() default "";
}
