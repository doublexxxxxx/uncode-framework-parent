package com.uncodeframework.core.ws.soap_spring_cxf;

import javax.jws.WebService;

@WebService
public interface HelloService {

    String say(String name);
}
