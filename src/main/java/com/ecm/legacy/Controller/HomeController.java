package com.ecm.legacy.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.legacy.response.ApiResponse;

@RestController
public class HomeController {

	@GetMapping
	public ApiResponse HomeController() {
		ApiResponse response=new ApiResponse();
		response.setMessage("hello in my ecommerce project");
		return response;
	}
}
