package com.uncodeframework.core.ws.soap_cxf;

import javax.jws.WebService;

@WebService
public class HelloServiceImpl implements HelloService {

    public String say(String name) {
        return "hello " + name;
    }
}
