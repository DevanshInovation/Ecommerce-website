package com.ecm.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ecm.model.Product;
import com.ecm.model.Seller;
import com.ecm.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req, Seller seller);
	public void deleteProduct(Long productId);
	public Product updateProduct(Long productId, Product product);
	Product findProductById(Long productId);
	List<Product> searchProducts(Long productId);
	public Page<Product> getAllProduct(String category,
			String brand,
			String colour,
			String size,
			Integer nimPrice,
			Integer maxPrice,
			Integer nimDiscount,
			String sort,
			String stock,
			Integer pageNumber
			);
	List<Product> getProductBySellerId(Long sellerId);
	
}
