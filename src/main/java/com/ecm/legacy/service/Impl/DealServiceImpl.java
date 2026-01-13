package com.ecm.legacy.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecm.legacy.model.Deal;
import com.ecm.legacy.model.HomeCategory;
import com.ecm.legacy.repository.DealRepository;
import com.ecm.legacy.repository.HomeCategoryRepository;
import com.ecm.legacy.service.DealService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DealServiceImpl implements DealService{

	private final DealRepository dealRepository;
    private final HomeCategoryRepository homeCategoryRepository;
    
    
	@Override
	public List<Deal> getDeals() {
		// TODO Auto-generated method stub
		return dealRepository.findAll();
	}

	@Override
	public Deal createDeal(Deal deal) {
		HomeCategory category=homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);
		Deal newDeal=dealRepository.save(deal);
		newDeal.setCategory(category);
		newDeal.setDiscount(deal.getDiscount());
		return dealRepository.save(newDeal);
	}

	@Override
	public void deleteDeal(Long id) throws Exception {
	    Deal deal = dealRepository.findById(id)
	            .orElseThrow(() -> new Exception("deal not found"));
	    dealRepository.delete(deal);
	}

	@Override
	public Deal updateDeal(Deal deal, Long id) throws Exception {
	    Deal existingDeal = dealRepository.findById(id).orElse(null);
	    HomeCategory category = homeCategoryRepository
	            .findById(deal.getCategory().getId())
	            .orElse(null);

	    if (existingDeal != null) {
	        if (deal.getDiscount() != null) {
	            existingDeal.setDiscount(deal.getDiscount());
	        }
	        if (category != null) {
	            existingDeal.setCategory(category);
	        }
	        return dealRepository.save(existingDeal);
	    }
	    throw new Exception("Deal not found");
	}

}
