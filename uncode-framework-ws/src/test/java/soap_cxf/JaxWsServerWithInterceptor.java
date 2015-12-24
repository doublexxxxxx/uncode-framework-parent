package soap_cxf;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.message.Message;

import com.uncodeframework.core.ws.soap_cxf.HelloService;
import com.uncodeframework.core.ws.soap_cxf.HelloServiceImpl;

public class JaxWsServerWithInterceptor {

    public static void main(String[] args) {
        // 输入拦截器
        List<Interceptor<? extends Message>> inInterceptorList = new ArrayList<Interceptor<? extends Message>>();
        inInterceptorList.add(new LoggingInInterceptor());

        // 输出拦截器
        List<Interceptor<? extends Message>> outInterceptorList = new ArrayList<Interceptor<? extends Message>>();
        outInterceptorList.add(new LoggingOutInterceptor());

        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/soap/hello");
        factory.setServiceClass(HelloService.class);
        factory.setServiceBean(new HelloServiceImpl());
        factory.setInInterceptors(inInterceptorList); // 输入拦截器
        factory.setOutInterceptors(outInterceptorList); // 输出拦截器
        factory.create();
        System.out.println("soap ws is published");
    }
}
