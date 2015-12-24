package rest_cxf;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.uncodeframework.core.ws.rest_cxf.Product;

public class JAXRS20Client {

    public static void main(String[] args) {
        String baseAddress = "http://localhost:8080/ws/rest";

        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();

        List productList = ClientBuilder.newClient()
            .register(jsonProvider)
            .target(baseAddress)
            .path("/products")
            .request(MediaType.APPLICATION_JSON)
            .get(List.class);
        for (Object product : productList) {
            System.out.println(product);
        }

        List<Product> productList2 = ClientBuilder.newClient()
            .register(jsonProvider)
            .target(baseAddress)
            .path("/products")
            .request(MediaType.APPLICATION_JSON)
            .get(new GenericType<List<Product>>() {});
        for (Product product : productList2) {
            System.out.println(product);
        }
    }
}
