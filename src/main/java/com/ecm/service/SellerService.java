package com.ecm.service;

import java.util.List;

import com.ecm.domain.AccountStatus;
import com.ecm.model.Seller;

public interface SellerService {
	
	Seller getSellerProfile(String jwt) throws Exception;
	Seller createSeller(Seller seller) throws Exception;
	Seller getSellerById(Long id) throws Exception;
	Seller getSellerByEmail(String email) throws Exception;
	List<Seller> getAllSellers(AccountStatus Status);
	Seller updateSeller(Long id, Seller seller) throws Exception;
	void deleteSeller(Long id) throws Exception;
	Seller verifyEmail(String email, String otp) throws Exception;

}
