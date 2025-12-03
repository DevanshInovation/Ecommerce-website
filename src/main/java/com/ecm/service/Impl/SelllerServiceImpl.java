package com.ecm.service.Impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecm.config.JwtProvider;
import com.ecm.domain.AccountStatus;
import com.ecm.domain.USER_ROLE;
import com.ecm.model.Address;
import com.ecm.model.Seller;
import com.ecm.repository.AddressRepository;
import com.ecm.repository.SellerRepository;
import com.ecm.service.SellerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SelllerServiceImpl implements SellerService{

	private final SellerRepository sellerRepository;
	private final JwtProvider jwtProvider;
	private final PasswordEncoder passwordEncoder;
	private final AddressRepository addressRepository;

	@Override
	public Seller getSellerProfile(String jwt) throws Exception {
		String email=jwtProvider.getEmailFromJwtToken(jwt);
		return this.getSellerByEmail(email);
	}

	@Override
	public Seller createSeller(Seller seller) throws Exception {
		Seller sellerExist=sellerRepository.findByEmail(seller.getEmail());
		if(sellerExist!=null) {
			throw new Exception("Seller already exist with provided email id");
		}
		Address saveAddress=addressRepository.save(seller.getPickUpAddress());
		
		Seller newSeller=new Seller();
		newSeller.setEmail(seller.getEmail());
		newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
		newSeller.setSellerName(seller.getSellerName());
		newSeller.setPickUpAddress(saveAddress);
		newSeller.setGSTIN(seller.getGSTIN());
		newSeller.setRole(USER_ROLE.ROLE_SELLER);
		newSeller.setMobile(seller.getMobile());
		newSeller.setBankDetails(seller.getBankDetails());
		newSeller.setBusinessDetails(seller.getBusinessDetails());
		
		return sellerRepository.save(newSeller);
	}

	@Override
	public Seller getSellerById(Long id) throws Exception {
		return sellerRepository.findById(id)
				.orElseThrow(()-> new Exception("seller not found with id -" +id));
	}

	@Override
	public Seller getSellerByEmail(String email) throws Exception {
		Seller seller=sellerRepository.findByEmail(email);
		if(seller==null) {
			throw new Exception("Seller not found");
		}
		return seller;
	}

	@Override
	public List<Seller> getAllSellers(AccountStatus Status) {
		
		return sellerRepository.findByAccountStatus(Status);
	}

	@Override
	public Seller updateSeller(Long id, Seller seller) throws Exception {
		Seller existingSeller=this.getSellerById(id);
		
		 // Basic seller fields
	    if (seller.getSellerName() != null) {
	        existingSeller.setSellerName(seller.getSellerName());
	    }

	    if (seller.getMobile() != null) {
	        existingSeller.setMobile(seller.getMobile());
	    }

	    if (seller.getEmail() != null) {
	        existingSeller.setEmail(seller.getEmail());
	    }

	    // BusinessDetails
	    if (seller.getBusinessDetails() != null
	            && seller.getBusinessDetails().getBusinessName() != null) {

	        existingSeller.getBusinessDetails().setBusinessName(
	                seller.getBusinessDetails().getBusinessName()
	        );
	    }

	    // BankDetails
	    if (seller.getBankDetails() != null
	            && seller.getBankDetails().getAccountHolderName() != null
	            && seller.getBankDetails().getIfscCode() != null
	            && seller.getBankDetails().getAccountNumber() != null) {

	        existingSeller.getBankDetails().setAccountHolderName(
	                seller.getBankDetails().getAccountHolderName()
	        );
	        existingSeller.getBankDetails().setAccountNumber(
	                seller.getBankDetails().getAccountNumber()
	        );
	        existingSeller.getBankDetails().setIfscCode(
	                seller.getBankDetails().getIfscCode()
	        );
	    }

	    // PickupAddress
	    if (seller.getPickUpAddress() != null
	            && seller.getPickUpAddress().getAddress() != null
	            && seller.getPickUpAddress().getMobile() != null
	            && seller.getPickUpAddress().getCity() != null
	            && seller.getPickUpAddress().getState() != null) {

	        existingSeller.getPickUpAddress().setAddress(
	                seller.getPickUpAddress().getAddress()
	        );
	        existingSeller.getPickUpAddress().setCity(
	                seller.getPickUpAddress().getCity()
	        );
	        existingSeller.getPickUpAddress().setState(
	                seller.getPickUpAddress().getState()
	        );
	        existingSeller.getPickUpAddress().setMobile(
	                seller.getPickUpAddress().getMobile()
	        );
	        existingSeller.getPickUpAddress().setPinCode(
	                seller.getPickUpAddress().getPinCode()
	        );
	    }

	    // GSTIN (optional)
	    if (seller.getGSTIN() != null) {
	        existingSeller.setGSTIN(seller.getGSTIN());
	    }

	    return sellerRepository.save(existingSeller);
		
	}

	@Override
	public void deleteSeller(Long id) throws Exception {
		Seller seller=getSellerById(id);
		sellerRepository.delete(seller);
		
	}

	@Override
	public Seller verifyEmail(String email, String otp) throws Exception {
		Seller seller = getSellerByEmail(email);

	    // yahan agar OTP verification logic hai to wo bhi check kar sakte ho
	    // if (!seller.getOtp().equals(otp)) throw new Exception("Invalid otp");

	    seller.setEmailVerified(true);
	    return sellerRepository.save(seller);
	}

	public Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception {
		 Seller seller = getSellerById(sellerId);
		    seller.setAccountStatus(status);
		    return sellerRepository.save(seller);
	}
}
