package com.ecm.legacy.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecm.legacy.domain.HomeCategorySection;
import com.ecm.legacy.model.Deal;
import com.ecm.legacy.model.Home;
import com.ecm.legacy.model.HomeCategory;
import com.ecm.legacy.repository.DealRepository;
import com.ecm.legacy.service.HomeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService{
	
	private final DealRepository dealRepository;
	
	@Override
	public Home createHomePageData(List<HomeCategory> allCategories) {

	    List<HomeCategory> dealCategories = allCategories.stream()
	            .filter(category -> category.getSection() == HomeCategorySection.DEALS)
	            .toList();

	    List<Deal> createdDeals = new ArrayList<>();

	    if (dealRepository.findAll().isEmpty()) {
	        List<Deal> deals = allCategories.stream()
	                .filter(category -> category.getSection() == HomeCategorySection.DEALS)
	                .map(category -> new Deal(null, 10, category))   // id: null, discount: 10
	                .collect(Collectors.toList());

	        createdDeals = dealRepository.saveAll(deals);
	    } else {
	        createdDeals = dealRepository.findAll();
	    }

	    List<HomeCategory> gridCategories = allCategories.stream()
	            .filter(category -> category.getSection() == HomeCategorySection.GRID)
	            .collect(Collectors.toList());

	    List<HomeCategory> shopByCategories = allCategories.stream()
	            .filter(category -> category.getSection() == HomeCategorySection.SHOP_BY_CATEGORY)
	            .collect(Collectors.toList());

	    List<HomeCategory> electricCategories = allCategories.stream()
	            .filter(category -> category.getSection() == HomeCategorySection.ELECTRIC_CATEGORY)
	            .collect(Collectors.toList());

	    Home home = new Home();
	    home.setGrid(gridCategories);
	    home.setShopByCategories(shopByCategories);
	    home.setElectricCategories(electricCategories);
	    home.setDeals(createdDeals);
	    home.setDealCategories(dealCategories);

	    return home;
	}


}
