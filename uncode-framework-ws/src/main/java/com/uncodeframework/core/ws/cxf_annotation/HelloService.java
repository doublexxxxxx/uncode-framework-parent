package com.uncodeframework.core.ws.cxf_annotation;

import javax.jws.WebService;

import org.apache.cxf.annotations.Logging;

@WebService
@Logging
public interface HelloService {

	String say(String name);
}
