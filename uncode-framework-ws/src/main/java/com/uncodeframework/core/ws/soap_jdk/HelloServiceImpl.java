package com.uncodeframework.core.ws.soap_jdk;

import javax.jws.WebService;

@WebService(
    serviceName = "HelloService",
    portName = "HelloServicePort",
    endpointInterface = "com.uncodeframework.core.ws.soap_jdk.HelloService"
)
public class HelloServiceImpl implements HelloService {

    public String say(String name) {
        return "hello " + name;
    }
}
