package com.uncodeframework.core.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
	private static AppContext instance;

	private volatile AbstractApplicationContext appContext;

	public synchronized static AppContext getInstance() {
		if (instance == null) {
			instance = new AppContext();
		}
		return instance;
	}

	private AppContext() {
		List<String> list = new ArrayList<String>();
		// list.add("/cfg/*.xml");
		list.add("/applicationContext-mail.xml");
		String ss[] = list.toArray(new String[] {});
		//for (int i = 0; i < ss.length; i++) {
		//	System.out.println("ss[" + i + "]" + ss[i]);
		//}
		this.appContext = new ClassPathXmlApplicationContext(ss);
	}

	public AbstractApplicationContext getAppContext() {
		return appContext;
	}
}