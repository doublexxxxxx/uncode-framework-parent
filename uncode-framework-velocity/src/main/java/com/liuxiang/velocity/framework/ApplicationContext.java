package com.liuxiang.velocity.framework;

import java.lang.reflect.Modifier;
import java.util.Map;

import com.liuxiang.velocity.annotation.Component;
import com.liuxiang.velocity.util.ClassFilter;
import com.liuxiang.velocity.util.ClassScanner;
import com.liuxiang.velocity.util.InstanceUtil;

public class ApplicationContext implements Context{
	
	protected static Map<String,Class> classMap;
	protected static Map<String,Object> instanceMap;
	protected static ClassFilter filter = new ClassFilter() {
		public boolean accept(Class clazz) {
			return !Modifier.isAbstract(clazz.getModifiers())
					  && !Modifier.isInterface(clazz.getModifiers())
					  && Modifier.isPublic(clazz.getModifiers())
					  && !Modifier.isStatic(clazz.getModifiers());
		}
	};
	protected static String[] defaultPackages = {"com.liuxiang.velocity"};
	public void init() {
		
		init(defaultPackages);
	}
	public void init(String[] packages) {
		init(packages,filter);
	}
	public void init(String[] packages,ClassFilter filter) {
		classMap = ClassScanner.packageScan(packages, filter,Component.class);
		instanceMap = InstanceUtil.createInstance(classMap);
	}
	
	public Object getInstance(String className) {
		return instanceMap.get(className);
	}
}
