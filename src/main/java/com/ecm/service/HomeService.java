package com.ecm.service;

import java.util.List;

import com.ecm.model.Home;
import com.ecm.model.HomeCategory;

public interface HomeService {

	public Home createHomePageData(List<HomeCategory> allCategories);
}
