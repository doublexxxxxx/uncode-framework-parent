package soap_cxf;

import org.apache.cxf.frontend.ServerFactoryBean;

import com.uncodeframework.core.ws.soap_cxf.HelloService;
import com.uncodeframework.core.ws.soap_cxf.HelloServiceImpl;

public class SimpleServer {

    public static void main(String[] args) {
        ServerFactoryBean factory = new ServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/soap/hello");
        factory.setServiceClass(HelloService.class);
        factory.setServiceBean(new HelloServiceImpl());
        factory.create();
        System.out.println("soap ws is published");
    }
}
