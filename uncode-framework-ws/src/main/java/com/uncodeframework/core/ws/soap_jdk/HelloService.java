package com.uncodeframework.core.ws.soap_jdk;

import javax.jws.WebService;

@WebService
public interface HelloService {

    String say(String name);
}
