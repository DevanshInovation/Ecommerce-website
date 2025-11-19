package com.ecm.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	private String title;
	
	private String description;
	
	private int mrpPrice;
	
	private int sellingPrice;
	
	private int discountPrecent;
	
	private int quantity;
	
	private String colour;
	
	@ElementCollection
	private List<String> images = new ArrayList<String>();
	
	private int numRatings;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Seller seller;
	
	private LocalDateTime createdAt;
	
	private String sizes;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();
	
	
	
}
