package com.uncodeframework.core.ws.cxf_annotation;

public class HelloServiceImpl implements HelloService {

	@Override
	public String say(String name) {
		return "hello " + name;
	}

}
