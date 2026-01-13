package com.ecm.legacy.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.legacy.config.JwtProvider;
import com.ecm.legacy.domain.AccountStatus;
import com.ecm.legacy.exception.SellerException;
import com.ecm.legacy.model.Seller;
import com.ecm.legacy.model.SellerReport;
import com.ecm.legacy.model.VerificationCode;
import com.ecm.legacy.repository.VerificationCodeRepository;
import com.ecm.legacy.request.LoginRequest;
import com.ecm.legacy.response.ApiResponse;
import com.ecm.legacy.response.AuthResponse;
import com.ecm.legacy.service.AuthService;
import com.ecm.legacy.service.SellerReportService;
import com.ecm.legacy.service.SellerService;
import com.ecm.legacy.service.Impl.EmailService;
import com.ecm.legacy.utils.OtpUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {

	private final SellerService sellerService;
	private final VerificationCodeRepository verificationRepository;
	private final AuthService authService;
	private final EmailService emailService;
	private final VerificationCodeRepository verificationCodeRepository;
	private final SellerReportService sellerReportService;
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) throws Exception{

//		String otp=req.getOtp();
//		String email=req.getEmail();
//		
//		
//		req.setEmail("Seller"+email);
		AuthResponse authResponse=authService.signing(req);
		
		return ResponseEntity.ok(authResponse);
	}
	
	@PatchMapping("/verify/{otp}")
	public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception{
		
		VerificationCode verificationCode=verificationRepository.findByOtp(otp);
		if(verificationCode==null || !verificationCode.getOtp().equals(otp)) {
			throw new Exception("wrong otp...");
		}
		
		Seller seller=sellerService.verifyEmail(verificationCode.getEmail(), otp);
		return new ResponseEntity<>(seller,HttpStatus.OK);
	}
	
	@PostMapping("/seller")
	public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {

	    Seller savedSeller = sellerService.createSeller(seller);

	    // OTP generate
	    String otp = OtpUtil.generatedOtp();

	    // verification code entity banake save karna
	    VerificationCode verificationCode = new VerificationCode();
	    verificationCode.setOtp(otp);
	    verificationCode.setEmail(savedSeller.getEmail());
	    verificationCodeRepository.save(verificationCode);

	    // email bhejna
	    String subject = "Zosh Bazaar Email Verification";
	    String text = "Welcome to Zosh Bazaar. Your verification otp is: " + otp;
	    String frontendUrl = "http://localhost:3000/verify-seller-email"; // jo bhi tumhara URL ho

	    emailService.sendVerificationOtpEmail(savedSeller.getEmail(), otp, subject, text);

	    return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws Exception {
	   Seller seller = sellerService.getSellerById(id);
	   return new ResponseEntity<>(seller, HttpStatus.OK);
    }

	@GetMapping("/profile")
	public ResponseEntity<Seller> getSellerByJwt(
        @RequestHeader("Authorization") String jwt) throws Exception {

       Seller seller = sellerService.getSellerProfile(jwt); 
       return new ResponseEntity<>(seller, HttpStatus.OK);
	 }
	
	@GetMapping("/report")
	public ResponseEntity<SellerReport> getSellerReport(
	        @RequestHeader("Authorization") String jwt) throws SellerException {

//	    String email = JwtProvider.getEmailFromJwtToken(jwt);
	    Seller seller = sellerService.getSellerProfile(jwt);
	    SellerReport report = sellerReportService.getSellerReport(seller);
	    return new ResponseEntity<>(report, HttpStatus.OK);
	}

	
	@GetMapping
	public ResponseEntity<List<Seller>> getAllSellers(
	        @RequestParam(required = false) AccountStatus status) {

	    List<Seller> sellers = sellerService.getAllSellers(status);
	    return ResponseEntity.ok(sellers);
	}

	@PatchMapping
	public ResponseEntity<Seller> updateSeller(
	        @RequestHeader("Authorization") String jwt,
	        @RequestBody Seller seller) throws Exception {

	    Seller profile = sellerService.getSellerProfile(jwt);   // ya getSellerByJwt(jwt)
	    Seller updatedSeller = sellerService.updateSeller(profile.getId(), seller);
	    return ResponseEntity.ok(updatedSeller);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
	    sellerService.deleteSeller(id);
	    return ResponseEntity.noContent().build();
	}

}
