package org.uncodeframework.core.common.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * type必填 ，若type,name两个都填，name有效
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@org.springframework.stereotype.Service
public @interface Service {

	/**
	 * service在spring容器中的名称
	 */
	String value() default "";

	/**
	 * Repository的实例名称
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * Repository的实例类型
	 * 
	 * @return
	 */
	Class<?> repositoryType();
}
