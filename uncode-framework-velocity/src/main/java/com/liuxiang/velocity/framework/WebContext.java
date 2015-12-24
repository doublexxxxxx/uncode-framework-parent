package com.liuxiang.velocity.framework;

import com.liuxiang.velocity.util.ClassFilter;

public interface WebContext extends Context {
/*	void init();
	void init(String[] actionPackages);*/
	void init(String[] beanPackages,String[] actionPackages);
	void init(String[] beanPackages,String[] actionPackages,ClassFilter filter);
	Object getActionInstance(String actionName);
}
