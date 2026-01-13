package com.ecm.legacy.service;

import java.util.List;

import com.ecm.legacy.domain.AccountStatus;
import com.ecm.legacy.exception.SellerException;
import com.ecm.legacy.model.Seller;

public interface SellerService {
	
	Seller getSellerProfile(String jwt) throws SellerException;
	Seller createSeller(Seller seller) throws SellerException;
	Seller getSellerById(Long id) throws SellerException;
	Seller getSellerByEmail(String email) throws SellerException;
	List<Seller> getAllSellers(AccountStatus Status);
	Seller updateSeller(Long id, Seller seller) throws SellerException;
	void deleteSeller(Long id) throws SellerException;
	Seller verifyEmail(String email, String otp) throws SellerException;
	Seller updateSellerAccountStatus(Long id,
            AccountStatus status) throws SellerException;
}
