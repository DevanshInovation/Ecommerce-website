package com.ecm.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecm.model.HomeCategory;
import com.ecm.repository.HomeCategoryRepository;
import com.ecm.service.HomeCategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService{
	
	private final HomeCategoryRepository homeCategoryRepository;
	
	@Override
	public HomeCategory createHomeCategory(HomeCategory homeCategory) {
		// TODO Auto-generated method stub
		return homeCategoryRepository.save(homeCategory);
	}

	@Override
	public List<HomeCategory> homeCategories(List<HomeCategory> homeCategories) {
		if(homeCategoryRepository.findAll().isEmpty()) {
			return homeCategoryRepository.saveAll(homeCategories);
		}
		return homeCategoryRepository.findAll();
	}

	@Override
	public HomeCategory updateHomeCategory(HomeCategory category, Long id) throws Exception {

	    HomeCategory existingCategory = homeCategoryRepository.findById(id)
	            .orElseThrow(() -> new Exception("Category not found"));

	    if (category.getImage() != null) {
	        existingCategory.setImage(category.getImage());
	    }

	    if (category.getCategoryId() != null) {
	        existingCategory.setCategoryId(category.getCategoryId());
	    }

	    return homeCategoryRepository.save(existingCategory);
	}


	@Override
	public List<HomeCategory> getAllHomeCategories() {
		// TODO Auto-generated method stub
		return homeCategoryRepository.findAll();
	}

}
