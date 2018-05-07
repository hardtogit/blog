package com.thon.task.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author THON
 * @email thon.ju@meet-future.com
 * @date 2011-11-27 上午09:40:30
 * @description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface Task {
	String value() default "";
}
