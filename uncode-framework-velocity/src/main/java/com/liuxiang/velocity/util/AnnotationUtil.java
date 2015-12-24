package com.liuxiang.velocity.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.liuxiang.velocity.annotation.Component;

public class AnnotationUtil {

	public static boolean isComponent(Class clazz) {
		return clazz.isAnnotationPresent(Component.class);
	}

	public static boolean mathchType(Class clazz, Class annotationType) {
		return clazz.isAnnotationPresent(annotationType);
	}

	public static Object getValue(Class clazz, Class annotationType,String name) {
		Object value = null;
		try {
			Annotation annotation = clazz.getAnnotation(annotationType);
			if(annotation != null) {
				Method m = annotation.getClass().getDeclaredMethod(name, null);
				if(m != null) {
					value = m.invoke(annotation);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return value;
	}

}
