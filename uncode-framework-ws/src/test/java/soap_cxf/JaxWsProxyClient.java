package soap_cxf;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.uncodeframework.core.ws.soap_cxf.HelloService;

public class JaxWsProxyClient {

    public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress("http://localhost:8080/ws/soap/hello");
        factory.setServiceClass(HelloService.class);

        HelloService helloService = factory.create(HelloService.class);
        String result = helloService.say("world");
        System.out.println(result);
    }
}
