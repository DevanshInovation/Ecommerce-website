package com.ecm.legacy.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ecm.legacy.exception.ProductException;
import com.ecm.legacy.model.Product;
import com.ecm.legacy.model.Seller;
import com.ecm.legacy.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req, Seller seller);
	public void deleteProduct(Long productId) throws ProductException;
	public Product updateProduct(Long productId, Product product) throws ProductException;
	Product findProductById(Long productId) throws ProductException;
	List<Product> searchProducts(String query);
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
