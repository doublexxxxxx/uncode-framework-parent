package com.uncodeframework.core.ws.rest_spring;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> retrieveAllProducts();

    Product retrieveProductById(long id);

    List<Product> retrieveProductsByName(String name);

    Product createProduct(Product product);

    Product updateProductById(long id, Map<String, Object> fieldMap);

    Product deleteProductById(long id);
}
