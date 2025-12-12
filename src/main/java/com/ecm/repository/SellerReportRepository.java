package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.Seller;
import com.ecm.model.SellerReport;

public interface SellerReportRepository extends JpaRepository<SellerReport, Long>{

	// Use save(...) from JpaRepository to create/update reports.
	// Derived query to find a SellerReport by the seller's id.
	SellerReport findBySeller_Id(Long id);

}
