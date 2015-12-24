package com.liuxiang.velocity.framework;

import com.liuxiang.velocity.util.ClassFilter;

public interface Context {
	void init();
	void init(String[] packages);
	void init(String[] packages,ClassFilter filter);
	
	Object getInstance(String beanName);
}
