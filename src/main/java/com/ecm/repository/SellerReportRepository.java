package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.Seller;
import com.ecm.model.SellerReport;

public interface SellerReportRepository extends JpaRepository<SellerReport, Long>{
	
//	SellerReport findBySellerById(Long long1);
	SellerReport UpdateSellerReport(SellerReport sellerReport);
	SellerReport findBySellerById(Long id);

}
