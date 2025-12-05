package com.ecm.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecm.domain.USER_ROLE;
import com.ecm.model.Seller;
import com.ecm.model.User;
import com.ecm.repository.SellerRepository;
import com.ecm.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	private final SellerRepository sellerRepo;
	private static final String SELLER_PREFIX="seller";

	/**
	 *
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if(username.startsWith(SELLER_PREFIX)) {
//		 String actualUserName=username.substring(SELLER_PREFIX.length());
		 Seller seller=sellerRepo.findByEmail(username); 
		 System.out.println(username);
//		 
		 if(seller!=null) {
			 return buildUserDetails(seller.getEmail(), seller.getPassword(), seller.getRole());
//		 }
		} else {
			User user=userRepo.findByEmail(username);
			if(user!=null) {
				return buildUserDetails(user.getEmail(), user.getPassword(), user.getRole());
			}
		}
	    throw new UsernameNotFoundException("User or seller not found with this email "+username);
	}
	
		private UserDetails buildUserDetails(String email, String password, USER_ROLE role) {
			if(role==null) role=USER_ROLE.ROLE_CUSTOMER;
			
			List<GrantedAuthority> authorityList=new ArrayList<GrantedAuthority>();
			authorityList.add(new SimpleGrantedAuthority(role.toString()));
			return new org.springframework.security.core.userdetails.User(email, password, authorityList);
		}

}
