package com.ecm.legacy.model;

import java.util.List;

import lombok.Data;

@Data
public class Home {

	 private List<HomeCategory> grid;
	    // Categories shown in grid layout on homepage (top section).
	    // Example: Fashion, Electronics, Mobiles, Appliances, etc.

	    private List<HomeCategory> shopByCategories;
	    // "Shop by Category" section items.
	    // Example: Men's Wear, Women's Wear, Home Decor, Beauty, etc.

	    private List<HomeCategory> electricCategories;
	    // Electronics-focused categories section.
	    // Example: Laptops, Smart TVs, Cameras, Smart Watches etc.

	    private List<HomeCategory> dealCategories;
	    // Categories shown under "Deals of the Day" or "Boosted Deals".
	    // Example: Trending Fashion, Daily Deals, Festive Deals etc.

	    private List<Deal> deals;
	    // List of active deals with discounts.
	    // Example: 50% Off, Lightning Deals, Limited Time Offers etc.
}
