package soap_jaxws;

import javax.xml.ws.Endpoint;

import com.uncodeframework.core.ws.soap_jaxws.HelloService;
import com.uncodeframework.core.ws.soap_jaxws.HelloServiceImpl;

public class Server {

    public static void main(String[] args) {
        String address = "http://localhost:8080/ws/soap/hello";
        HelloService helloService = new HelloServiceImpl();

        Endpoint.publish(address, helloService);
        System.out.println("soap ws is published");
    }
}
