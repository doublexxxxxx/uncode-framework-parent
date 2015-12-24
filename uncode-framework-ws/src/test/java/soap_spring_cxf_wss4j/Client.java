package soap_spring_cxf_wss4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uncodeframework.core.ws.soap_spring_cxf_wss4j.HelloService;

public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("soap_spring_cxf_wss4j/spring-client.xml");

        HelloService helloService = context.getBean("helloService", HelloService.class);
        String result = helloService.say("world");
        System.out.println(result);
    }
}
