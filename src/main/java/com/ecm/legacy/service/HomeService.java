package com.ecm.legacy.service;

import java.util.List;

import com.ecm.legacy.model.Home;
import com.ecm.legacy.model.HomeCategory;

public interface HomeService {

	public Home createHomePageData(List<HomeCategory> allCategories);
}
