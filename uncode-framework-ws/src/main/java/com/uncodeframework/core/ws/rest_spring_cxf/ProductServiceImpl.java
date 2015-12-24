package com.uncodeframework.core.ws.rest_spring_cxf;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

	private static final List<Product> productList = new ArrayList<Product>();

	static {
		productList.add(new Product(1, "iphone 5s", 5000));
		productList.add(new Product(2, "ipad mini", 2500));
	}

	public List<Product> retrieveAllProducts() {
		Collections.sort(productList, new Comparator<Product>() {
			@Override
			public int compare(Product product1, Product product2) {
				return (product2.getId() > product1.getId()) ? 1 : -1;
			}
		});
		return productList;
	}

	public Product retrieveProductById(long id) {
		Product targetProduct = null;
		for (Product product : productList) {
			if (product.getId() == id) {
				targetProduct = product;
				break;
			}
		}
		return targetProduct;
	}

	public List<Product> retrieveProductsByName(String name) {
		List<Product> targetProductList = new ArrayList<Product>();
		for (Product product : productList) {
			if (product.getName().contains(name)) {
				targetProductList.add(product);
			}
		}
		return targetProductList;
	}

	public Product createProduct(Product product) {
		product.setId(new Date().getTime());
		productList.add(product);
		return product;
	}

	public Product updateProductById(long id, Map<String, Object> fieldMap) {
		Product product = retrieveProductById(id);
		if (product != null) {
			try {
				for (Map.Entry<String, Object> fieldEntry : fieldMap.entrySet()) {
					Field field = Product.class.getDeclaredField(fieldEntry.getKey());
					field.setAccessible(true);
					field.set(product, fieldEntry.getValue());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return product;
	}

	public Product deleteProductById(long id) {
		Product targetProduct = null;
		Iterator<Product> productIterator = productList.iterator();
		while (productIterator.hasNext()) {
			Product product = productIterator.next();
			if (product.getId() == id) {
				targetProduct = retrieveProductById(id);
				productIterator.remove();
				break;
			}
		}
		return targetProduct;
	}
}
