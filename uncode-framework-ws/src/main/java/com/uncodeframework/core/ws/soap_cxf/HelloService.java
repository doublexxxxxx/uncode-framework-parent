package com.uncodeframework.core.ws.soap_cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloService {

    String say(@WebParam(name = "name") String name);
}
