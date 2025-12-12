package com.ecm.service.Impl;

import org.springframework.stereotype.Service;

import com.ecm.model.Seller;
import com.ecm.model.SellerReport;
import com.ecm.repository.SellerReportRepository;
import com.ecm.service.SellerReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService{
	
	private final SellerReportRepository sellerReportRepository;
	

	@Override
	public SellerReport updateSellerReport(SellerReport seller) {
		return sellerReportRepository.save(seller);
	}

	@Override
	public SellerReport getSellerReport(Seller seller) {

		SellerReport sr = sellerReportRepository.findBySeller_Id(seller.getId());

	    if (sr == null) {
	        SellerReport newReport = new SellerReport();
	        newReport.setSeller(seller);

	        return sellerReportRepository.save(newReport);
	    }

	    return sr;

	}
}
