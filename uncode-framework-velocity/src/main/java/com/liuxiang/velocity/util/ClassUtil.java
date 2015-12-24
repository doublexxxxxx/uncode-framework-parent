package com.liuxiang.velocity.util;

public class ClassUtil {
	public static String convertMethodToName(String methodName) {
		String paramName = methodName.substring(3);
		paramName = paramName.substring(0,1).toLowerCase() + paramName.substring(1);
		return paramName;
	}
}
