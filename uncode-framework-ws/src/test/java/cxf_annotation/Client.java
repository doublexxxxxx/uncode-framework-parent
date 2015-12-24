package cxf_annotation;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

import com.uncodeframework.core.ws.cxf_annotation.HelloService;

public class Client {
	public static Logger logger = Logger.getLogger(Client.class);
	
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://localhost:8080/ws/soap/hello");
		factory.setServiceClass(HelloService.class);

		HelloService helloService = factory.create(HelloService.class);
		String result = helloService.say("world");
		logger.debug(result);
	}
}
