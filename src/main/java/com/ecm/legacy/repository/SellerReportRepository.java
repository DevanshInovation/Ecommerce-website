package com.ecm.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.Seller;
import com.ecm.legacy.model.SellerReport;

public interface SellerReportRepository extends JpaRepository<SellerReport, Long>{

	// Use save(...) from JpaRepository to create/update reports.
	// Derived query to find a SellerReport by the seller's id.
	SellerReport findBySeller_Id(Long id);

}
