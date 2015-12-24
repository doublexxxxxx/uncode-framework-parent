package com.liuxiang.velocity.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.liuxiang.velocity.annotation.Autowired;

public class InstanceUtil {

	public static Map<String, Object> createInstance(Map<String, Class> classMap) {
		
		Map<String,Object> instanceMap = new HashMap<String,Object>();
			try {
				for(Entry<String,Class> entry : classMap.entrySet()) {
					String name = entry.getKey();
					Class clazz = entry.getValue();
					instanceMap.put(name,clazz.newInstance());
				}
				referenceInjectWithinMap(instanceMap);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		return instanceMap;
	}

	private static void referenceInjectWithinMap(Map<String, Object> instanceMap) {
		try {
			for(Entry<String,Object> entry : instanceMap.entrySet()) {
				String name = entry.getKey();
				Object instance = entry.getValue();
				Field[] fields = instance.getClass().getDeclaredFields();
				for(Field f : fields) {
					f.setAccessible(true);
					Object referencedInstance = instanceMap.get(f.getType().getName());
					if(f.isAnnotationPresent(Autowired.class) && referencedInstance != null) {
						f.set(instance, referencedInstance);
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public static void referenceInject(Object toInjected,Map<String,Object> instanceMap) {
		try {
			Field[] fields = toInjected.getClass().getDeclaredFields();
			for(Field f : fields) {
				f.setAccessible(true);
				Object referencedInstance = instanceMap.get(f.getType().getName());
				if(f.isAnnotationPresent(Autowired.class) && referencedInstance != null) {
					f.set(toInjected, referencedInstance);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
