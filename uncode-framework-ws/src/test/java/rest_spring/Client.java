package rest_spring;

import java.util.List;
import org.springframework.web.client.RestTemplate;

public class Client {

    public static void main(String[] args) {
        String url = "http://localhost:8080/ws/rest/products";

        RestTemplate restTemplate = new RestTemplate();
        List productList = restTemplate.getForObject(url, List.class);
        for (Object product : productList) {
            System.out.println(product);
        }
    }
}
