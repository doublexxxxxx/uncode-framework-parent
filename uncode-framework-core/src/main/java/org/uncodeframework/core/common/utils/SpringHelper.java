package org.uncodeframework.core.common.utils;

import org.springframework.context.support.AbstractApplicationContext;


public class SpringHelper {

	/**
	 * 获取spring依赖注入的对象
	 * 
	 * @param name
	 * @return Object Bean
	 */
	public static Object getBean(String name) {
		AbstractApplicationContext ctx = AppContext.getInstance().getAppContext();
		return ctx.getBean(name);
	}
}