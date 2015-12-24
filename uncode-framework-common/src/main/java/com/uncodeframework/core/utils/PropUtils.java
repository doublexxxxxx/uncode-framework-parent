package com.uncodeframework.core.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

public class PropUtils {
	public static String LOCATION;
	
	public static final String CONFIG = "config.properties";
	
	static{
		try {
			String temp = URLDecoder.decode(PropUtils.class.getProtectionDomain().getCodeSource().getLocation().getFile(), "UTF-8");
			LOCATION = temp.substring(1, temp.lastIndexOf('/'));
		} catch (UnsupportedEncodingException e) {
			LOCATION = "";
		}
		
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static Properties getProperties(String filepath) throws Exception {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(LOCATION+"/"+filepath);
		prop.load(fis);
		return prop;
	}
	
	public static void SaveProperties(Properties prop,String filepath) throws Exception {
		FileOutputStream fos = new FileOutputStream(LOCATION+"/"+filepath);
		prop.store(fos, "@author Isea533");
		fos.close();
	}

	public static String getConfigValue(String key) {
		try {
			Properties properties = getProperties(CONFIG);
			if(properties.get(key)!=null){
				return properties.get(key).toString();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	public static void setConfigValue(String key,String value){
		try {
			Properties properties = getProperties(CONFIG);
			properties.setProperty(key, value);
			SaveProperties(properties, CONFIG);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}