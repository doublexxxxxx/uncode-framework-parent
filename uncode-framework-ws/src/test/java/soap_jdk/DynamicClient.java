package soap_jdk;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.uncodeframework.core.ws.soap_jdk.HelloService;

public class DynamicClient {

    public static void main(String[] args) {
        try {
            URL wsdl = new URL("http://localhost:8080/ws/soap/hello?wsdl");
            QName serviceName = new QName("http://soap_jdk.ws.core.uncodeframework.com/", "HelloService");
            QName portName = new QName("http://soap_jdk.ws.core.uncodeframework.com/", "HelloServicePort");
            Service service = Service.create(wsdl, serviceName);

            HelloService helloService = service.getPort(portName, HelloService.class);
            String result = helloService.say("world");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
