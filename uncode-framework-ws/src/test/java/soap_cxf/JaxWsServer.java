package soap_cxf;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.uncodeframework.core.ws.soap_cxf.HelloService;
import com.uncodeframework.core.ws.soap_cxf.HelloServiceImpl;

public class JaxWsServer {

    public static void main(String[] args) {
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/soap/hello");
        factory.setServiceClass(HelloService.class);
        factory.setServiceBean(new HelloServiceImpl());
        factory.create();
        System.out.println("soap ws is published");
    }
}
