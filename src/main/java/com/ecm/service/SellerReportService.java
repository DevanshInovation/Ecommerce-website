package com.ecm.service;

import com.ecm.model.Seller;
import com.ecm.model.SellerReport;

public interface SellerReportService {

//	SellerReport getSellerRepote(Seller seller);
	SellerReport updateSellerReport(SellerReport sellerReport);
	SellerReport getSellerReport(Seller seller);
}
