package com.uncodeframework.core.plugins.mylog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取properties文件
 */
public class Configuration {
	private static Properties propertie;
	private InputStream in;
	private static Configuration config = new Configuration();

	/**
	 * 初始化Configuration类
	 */
	public Configuration() {
		propertie = new Properties();
		try {
			in = ClassLoader.getSystemResourceAsStream("mylog.properties");
			propertie.load(in);
			in.close();
		} catch (FileNotFoundException ex) {
			System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("装载文件--->失败!");
			ex.printStackTrace();
		}
	}

	/**
	 * 重载函数，得到key的值
	 * 
	 * @param key
	 *            取得其值的键
	 * @return key的值
	 */
	public static String getValue(String key) {
		if (propertie.containsKey(key)) {
			String value = propertie.getProperty(key);// 得到某一属性的值
			return value;
		} else
			return "";
	}// end getValue()

	public static void main(String[] args) {
		System.out.println(Configuration.getValue("mail.host"));
		System.out.println(System.getProperty("mail.default.from"));
	}// end main()
}// end class ReadConfigInfo