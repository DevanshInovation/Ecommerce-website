package com.ecm.service.Impl;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecm.exception.ProductException;
import com.ecm.model.Product;
import com.ecm.model.Seller;
import com.ecm.repository.CategoryRepository;
import com.ecm.repository.ProductRepository;
import com.ecm.request.CreateProductRequest;
import com.ecm.service.ProductService;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	
	@Override
	public Product createProduct(CreateProductRequest req, Seller seller) {
	 
		com.ecm.model.Category category1=categoryRepository.findByCategoryId(req.getCategory());
		
		if(category1==null) {
			com.ecm.model.Category category=new com.ecm.model.Category();
			category.setCategoryId(req.getCategory());
			category.setLevel(1);
			category1=categoryRepository.save(category);
		}
		
		com.ecm.model.Category category2=categoryRepository.findByCategoryId(req.getCategory());
		
		 if (category2 == null) {
			 com.ecm.model.Category category=new com.ecm.model.Category();
	            category.setCategoryId(req.getCategory2());
	            category.setLevel(2);
	            category.setParentCategory(category1);
	            category2 = categoryRepository.save(category);
	        }

		 com.ecm.model.Category category3=categoryRepository.findByCategoryId(req.getCategory());

	        if (category3 == null) {
	        	 com.ecm.model.Category category=new com.ecm.model.Category();
	            category.setCategoryId(req.getCategory3());
	            category.setParentCategory(category2);
	            category3 = categoryRepository.save(category);
	        }


	        int discountPercentage = calculateDiscountPercentage(req.getMrpPrice(), req.getSellingPrice());

	        Product product = new Product();
	        product.setSeller(seller);
	        product.setCategory(category3);
	        product.setTitle(req.getTitle());
	        product.setDescription(req.getDescription());
	        product.setColour(req.getColor());
	        product.setMrpPrice(req.getMrpPrice());
	        product.setSellingPrice(req.getSellingPrice());
	        product.setImages(req.getImages());
	        product.setSizes(req.getSizes());
	        product.setCreatedAt(LocalDateTime.now());
	        product.setDiscountPrecent(discountPercentage);

	        return productRepository.save(product);
	}

	  private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
	        if (mrpPrice <= 0) {
	            throw new IllegalArgumentException("MRP price must be greater than 0");
	        }
	        int discount = mrpPrice - sellingPrice;
	        double discountFraction = (double) discount / mrpPrice;
	        return (int) Math.round(discountFraction * 100);
	    }
	  
	@Override
	public void deleteProduct(Long productId) throws ProductException {
		Product product=findProductById(productId);
		productRepository.delete(product);
	}

	@Override
	public Product updateProduct(Long productId, Product product) throws ProductException {
		findProductById(productId);
		product.setId(productId);
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long productId) throws ProductException {
		
		return productRepository.findById(productId).orElseThrow(()-> 
		     new ProductException("product not found with id"+ productId));
	}

	@Override
	public List<Product> searchProducts(String query) {
		
		return productRepository.searchProduct(query);
	}

	@Override
	public Page<Product> getAllProduct(String category, String brand, String color, String size, Integer minPrice,
			Integer maxPrice, Integer nimDiscount, String sort, String stock, Integer pageNumber) {
		Specification<Product> spe=(root, query, criteriaBuilder)->{
			List<Predicate> predicates=new ArrayList<Predicate>();
			
			// category filter
	        if (category != null && !category.isBlank()) {
	            Join<Product, Category> categoryJoin = root.join("category");
	            predicates.add(
	                criteriaBuilder.equal(categoryJoin.get("categoryId"), category)
	            );
	        }

	        // color filter (IN clause)
	        if (color != null && !color.isEmpty()) {
	            predicates.add(
	                root.get("color").in(color)
	            );
	        }

	        // size filter (single value)
	        if (size != null && !size.isBlank()) {
	            predicates.add(
	            		criteriaBuilder.equal(root.get("size"), size)
	            );
	        }

	        // price range filter
	        if (minPrice != null) {
	            predicates.add(
	            		criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice)
	            );
	        }
	        if (maxPrice != null) {
	            predicates.add(
	            		criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice)
	            );
	        }
	        if (stock != null) {
	            predicates.add(
	            		criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice)
	            );
	        }
	        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		Pageable pageable;
			 if (sort != null && !sort.isEmpty()) {
			        switch (sort) {
			            case "price_low":
			                pageable = (Pageable) PageRequest.of(
			                        pageNumber != null ? pageNumber : 0,
			                        10,
			                        Sort.by("sellingPrice").ascending()
			                );
			                break;

			            case "price_high":
			                pageable = (Pageable) PageRequest.of(
			                        pageNumber != null ? pageNumber : 0,
			                        10,
			                        Sort.by("sellingPrice").descending()
			                );
			                break;

			            case "newest":
			                pageable = (Pageable) PageRequest.of(
			                        pageNumber != null ? pageNumber : 0,
			                        10,
			                        Sort.by("createdAt").descending()
			                );
			                break;

			            default:
			                pageable = (Pageable) PageRequest.of(
			                        pageNumber != null ? pageNumber : 0,
			                        10,
			                        Sort.by("id").ascending()
			                );
		};
			 }else {
				 pageable=(Pageable) PageRequest.of(pageNumber!=null ? pageNumber : 0, 10, Sort.unsorted());
				 
			 }
			        
		return productRepository.findAll(spe, (org.springframework.data.domain.Pageable) pageable);
	}

	@Override
	public List<Product> getProductBySellerId(Long sellerId) {
		return productRepository.findBySellerId(sellerId);
	}

}
