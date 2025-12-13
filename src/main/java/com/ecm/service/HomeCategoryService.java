package com.ecm.service;

import java.util.List;

import com.ecm.model.HomeCategory;

public interface HomeCategoryService {

	HomeCategory createHomeCategory(HomeCategory homeCategory);
	List<HomeCategory >homeCategories(List<HomeCategory> homeCategories);
	HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) throws Exception;
	List<HomeCategory> getAllHomeCategories();
}
