package com.uncodeframework.core.ws.soap_spring_cxf_wss4j;

import javax.jws.WebService;
import org.springframework.stereotype.Component;

@WebService
@Component
public class HelloServiceImpl implements HelloService {

    public String say(String name) {
        return "hello " + name;
    }
}
