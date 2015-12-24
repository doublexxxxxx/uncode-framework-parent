package com.uncodeframework.core.common.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@org.springframework.stereotype.Repository
public @interface Repository {
	/**
	 * Repository在spring容器中的实例名称
	 */
	String value() default "";

	/**
	 * Repository处理的实体类
	 * 
	 * @return
	 */
	Class<?> entity();
}
