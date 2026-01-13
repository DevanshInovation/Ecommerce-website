package com.ecm.legacy.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ApiResponse {
	private String message;
	
	 // Getter
    public String getMessage() {
        return message;
    }
    
    // Setter
    public void setMessage(String message) {
        this.message = message;
    }
	
}
