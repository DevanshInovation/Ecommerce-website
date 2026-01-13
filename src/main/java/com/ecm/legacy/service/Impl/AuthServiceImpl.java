package com.ecm.legacy.service.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecm.legacy.config.JwtProvider;
import com.ecm.legacy.domain.USER_ROLE;
import com.ecm.legacy.model.Cart;
import com.ecm.legacy.model.Seller;
import com.ecm.legacy.model.User;
import com.ecm.legacy.model.VerificationCode;
import com.ecm.legacy.repository.CartRepository;
import com.ecm.legacy.repository.SellerRepository;
import com.ecm.legacy.repository.UserRepository;
import com.ecm.legacy.repository.VerificationCodeRepository;
import com.ecm.legacy.request.LoginRequest;
import com.ecm.legacy.response.AuthResponse;
import com.ecm.legacy.response.SignupRequest;
import com.ecm.legacy.service.AuthService;
import com.ecm.legacy.utils.OtpUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final CartRepository cartRepository;
	private final JwtProvider jwtProvider;
	private final VerificationCodeRepository verificationCodeRepository;
	private final EmailService emailService;
	private final CustomUserServiceImpl customUserServiceImpl;
	private final SellerRepository sellerRepository;
	
	@Override
	public String createUser(SignupRequest req) throws Exception {
		
		VerificationCode verificationCode=verificationCodeRepository.findByEmail(req.getEmail());
		
		if(verificationCode==null || !verificationCode.getOtp().equals(req.getOtp())) {
			throw new Exception("Wrong otp");
		}
		
	    User user = userRepository.findByEmail(req.getEmail());

	    if (user == null) {
	        User createdUser = new User();
	        createdUser.setEmail(req.getEmail());
	        createdUser.setFullName(req.getFullName());
	        createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
	        createdUser.setMobile("8964765423");
	        createdUser.setPassword(passwordEncoder.encode(req.getOtp()));

	        user = userRepository.save(createdUser);
	        
	        Cart cart=new Cart();
	        cart.setUser(user);
	        cartRepository.save(cart);
	    }
	    
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

	    Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    return jwtProvider.generateToken(authentication);
	}
    public void sentLoginOtp(String email, USER_ROLE role) throws Exception {
		String SIGNING_PREFIX="signin_";
//		String SELLER_PREFIX="seller_";

		
		if(email.startsWith(SIGNING_PREFIX)) {
			email=email.substring(SIGNING_PREFIX.length());
			
			if(role.equals(USER_ROLE.ROLE_SELLER)) {
				Seller seller=sellerRepository.findByEmail(email);
				if(seller==null) {
					throw new Exception("Seller not exist with provided email");
				}
				
				} else {
					User user=userRepository.findByEmail(email);
					if(user==null) {
						throw new Exception("User not exist with provided email");
				}
				
			}	
		}
		
		VerificationCode isExist=verificationCodeRepository.findByEmail(email);
		
		if(isExist!=null) {
			verificationCodeRepository.delete(isExist);
		}
		
		String otp=OtpUtil.generatedOtp();
		VerificationCode verificationCode=new VerificationCode();
		verificationCode.setOtp(otp);
		verificationCode.setEmail(email);
		verificationCodeRepository.save(verificationCode);
		
		String subject="ecomExpress login/signup otp";
		String text="your login/signup otp is - "+otp;
		
		emailService.sendVerificationOtpEmail(email, otp, subject, text);
	}
	@Override
	public AuthResponse signing(LoginRequest req) throws Exception {
		String username=req.getEmail();
		String otp=req.getOtp();
		
		Authentication authentication=Authenticate(username,otp);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String tooken=jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(tooken);
		authResponse.setMessage("Login Sucess");
		
		Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
		String roleName=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
		
		authResponse.setRole(USER_ROLE.valueOf(roleName));
		return authResponse;
	}
	private Authentication Authenticate(String username, String otp) throws Exception {
		UserDetails userDetails=customUserServiceImpl.loadUserByUsername(username);
		
		String SELLER_PREFIX="seller_";
		if(username.startsWith(SELLER_PREFIX)) {
			username=username.substring(SELLER_PREFIX.length());
		}
		
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
		}
		
		VerificationCode verificationCode=verificationCodeRepository.findByEmail(username);
		
		if(verificationCode==null || !verificationCode.getOtp().equals(otp)) {
			throw new Exception("wrong otp");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
