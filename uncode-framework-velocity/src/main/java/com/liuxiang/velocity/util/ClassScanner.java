package com.liuxiang.velocity.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import com.liuxiang.velocity.annotation.Action;

public class ClassScanner {
	
	private static String NON_PACKAGE_ERROR = "package is null";
	private static String CLASS_EXT = ".class";
	private static String JAR_EXT = ".jar";
	private static String ZIP_SLASH = "/";
	private static FileFilter fileFilter = new FileFilter(){
		public boolean accept(File pathname) {
			return pathname.getName().endsWith(CLASS_EXT)
				|| pathname.getName().endsWith(JAR_EXT)
				|| pathname.isDirectory();
		}
	};
	
	public static Map<String,Class> packageScan(String[] packages,ClassFilter filter,Class annotationType) {
		checkPackages(packages);
		Map<String,Class> map = new HashMap<String,Class>();
		for(String pkg : packages) {
			map.putAll(packageScan(pkg,filter,annotationType));
		}
		return map;
	}
	private static void checkPackages(String[] packages) {
		
		if(packages == null || packages.length == 0 ) {
			throw new NullPointerException(NON_PACKAGE_ERROR);
		}
		for(String pkg : packages) {
			if(pkg == null || pkg.trim().length() == 0) {
				throw new NullPointerException(NON_PACKAGE_ERROR);
			}
		}
	}
	private static Map<String,Class> packageScan(String pkg,ClassFilter filter,Class annotationType) {
		Map<String,Class> map = new HashMap<String,Class>();
		for(String classPath : getClasspathArray()) {
			fillinClassMap(new File(classPath),getWellFormatedPackageName(pkg),filter,map,annotationType);
		}
		return map;
	}
	private static void fillinClassMap(
			File file, String packageName, ClassFilter filter,Map<String,Class> map,Class annotationType) {
		if(file.isDirectory()) {
			processDirectory(file,packageName,filter,map,annotationType);
		} else if(file.getName().endsWith(CLASS_EXT)) {
			processClassFile(file,packageName,filter,map,annotationType);
		} else if(file.getName().endsWith(JAR_EXT)) {
			processJarFile(file,packageName,filter,map,annotationType);
		}
	}
	private static void processJarFile(File file, String packageName,
			ClassFilter filter, Map<String, Class> map,Class annotationType) {
		try {
			for(ZipEntry entry : Collections.list(new ZipFile(file).entries())) {
				if(entry.getName().endsWith(CLASS_EXT)) {
					final String className = entry.getName().replace(ZIP_SLASH, ".").replace(CLASS_EXT, "");
					fillClass(className,packageName,filter,map,annotationType);
				}
			}
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void processClassFile(File file, String packageName,
			ClassFilter filter, Map<String, Class> map,Class annotationType) {
		final String fileNameWithDot = file.getAbsolutePath().replace(File.separator,".");
		int subIndex = -1;
		if((subIndex = fileNameWithDot.indexOf(packageName)) != -1) {
			final String className = fileNameWithDot.substring(subIndex).replace(CLASS_EXT, "");
			fillClass(className,packageName,filter,map,annotationType);
		}
	}
	private static void fillClass(String className, String packageName,
			ClassFilter filter, Map<String, Class> map,Class annotationType) {
		if(className.startsWith(packageName)) {
			try {
				Class clazz = Class.forName(className, false, ClassScanner.class.getClassLoader());
				if(checkClassFilter(clazz,filter) && AnnotationUtil.mathchType(clazz,annotationType)) {
					if(annotationType.equals(Action.class)) {
						className = (String) AnnotationUtil.getValue(clazz,annotationType,"value");
					}
					map.put(className, clazz);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static boolean checkClassFilter(Class clazz, ClassFilter filter) {
		return filter == null || filter.accept(clazz);
	}
	private static void processDirectory(File file, String packageName,
			ClassFilter filter, Map<String, Class> map,Class annotationType) {
		for(File f : file.listFiles(fileFilter)) {
			fillinClassMap(f,packageName,filter,map,annotationType);
		}
	}
	private static String[] getClasspathArray() {
		//return System.getProperty("java.class.path").split(System.getProperty("path.separator"));
		String[] classPath = {ClassScanner.class.getClassLoader().getResource("").getFile()};
		return classPath;
	}
	private static String getWellFormatedPackageName(String packageName) {
		return packageName.lastIndexOf('.') != packageName.length() - 1 ? packageName + '.' : packageName;
	}
}
