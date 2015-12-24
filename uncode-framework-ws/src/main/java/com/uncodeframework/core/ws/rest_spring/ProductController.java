package com.uncodeframework.core.ws.rest_spring;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/ws/rest")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = GET)
    public List<Product> list() {
        return productService.retrieveAllProducts();
    }
}
