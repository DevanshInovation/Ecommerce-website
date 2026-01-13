package com.ecm.legacy.service;

import com.ecm.legacy.model.Seller;
import com.ecm.legacy.model.SellerReport;

public interface SellerReportService {

//	SellerReport getSellerRepote(Seller seller);
	SellerReport updateSellerReport(SellerReport sellerReport);
	SellerReport getSellerReport(Seller seller);
}
