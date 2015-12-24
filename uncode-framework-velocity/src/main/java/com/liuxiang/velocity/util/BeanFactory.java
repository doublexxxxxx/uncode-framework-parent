package com.liuxiang.velocity.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class BeanFactory {
	
	private Map<String,Object> map= new HashMap<String,Object>();
	
	public void init(String xml) {
		try {
			SAXReader reader = new SAXReader();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			
			InputStream is = classLoader.getResourceAsStream(xml);
			Document doc = reader.read(is);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
