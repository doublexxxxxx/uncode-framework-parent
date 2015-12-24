package rest_cxf;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.uncodeframework.core.ws.rest_cxf.Product;

public class CXFWebClient {

	public static void main(String[] args) {
		String baseAddress = "http://localhost:8080/ws/rest";

		List<Object> providerList = new ArrayList<Object>();
		providerList.add(new JacksonJsonProvider());

		List productList = WebClient.create(baseAddress, providerList).path("/products").accept(MediaType.APPLICATION_JSON).get(List.class);
		for (Object product : productList) {
			System.out.println(product);
		}

		List<Product> productList2 = WebClient.create(baseAddress, providerList).path("/products").accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Product>>() {
		});
		for (Product product : productList2) {
			System.out.println(product);
		}
	}
}
