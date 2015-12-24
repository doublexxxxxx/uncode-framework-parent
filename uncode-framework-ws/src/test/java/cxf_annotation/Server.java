package cxf_annotation;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.uncodeframework.core.plugins.mylog.Logger;
import com.uncodeframework.core.ws.cxf_annotation.HelloService;
import com.uncodeframework.core.ws.cxf_annotation.HelloServiceImpl;

public class Server {
	public static void main(String[] args) {
		Logger.initialize("logs", "uncode_log", 0);
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setAddress("http://localhost:8080/ws/soap/hello");
		factory.setServiceClass(HelloService.class);
		factory.setServiceBean(new HelloServiceImpl());
		factory.create();
		Logger.log(Logger.DEBUG, "cxf_annotation server", "soap ws is published");
	}
}
