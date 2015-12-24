package com.liuxiang.velocity.framework;

import java.util.Map;

import com.liuxiang.velocity.annotation.Action;
import com.liuxiang.velocity.util.ClassFilter;
import com.liuxiang.velocity.util.ClassScanner;
import com.liuxiang.velocity.util.InstanceUtil;

public class WebApplicationContext extends ApplicationContext implements WebContext{
	private static Map<String,Class> actionClassMap;
	private static String[] defaultActionPackages = {"com.liuxiang.velocity.action"};
	
	public void init() {
		init(defaultActionPackages);
	}
	
	public void init(String[] actionPackages) {
		init(ApplicationContext.defaultPackages,actionPackages);
	}
	public void init(String[] beanPackages, String[] actionPackages) {
		init(beanPackages,actionPackages,ApplicationContext.filter);
	}
	
	public void init(String[] beanPackages,String[] actionPackages,ClassFilter filter) {
		super.init(beanPackages, filter);
		actionClassMap = ClassScanner.packageScan(actionPackages, filter, Action.class);
	}
	
	public Object getActionInstance(String actionName) {
		Object action = null;
		try {
			Class actionClass = actionClassMap.get(actionName);
			if(actionClass == null) {
				return null;
			} else {
				action = actionClass.newInstance();
				InstanceUtil.referenceInject(action, ApplicationContext.instanceMap);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return action;
	}

}
