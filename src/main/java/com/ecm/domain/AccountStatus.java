package com.ecm.domain;

public enum AccountStatus {

	 PENDING_VERIFICATION, 
	    // Account is created but not yet verified. 
	    // User must complete email/phone verification.

	    ACTIVE,
	    // Account is fully verified and currently active.
	    // User can access all platform features.

	    SUSPENDED,
	    // Account is temporarily suspended, 
	    // usually due to policy violations or suspicious activity.

	    DEACTIVATED,
	    // Account is deactivated by user choice.
	    // User may reactivate it later.

	    BANNED,
	    // Account is permanently banned due to severe or repeated violations.
	    // User cannot access or recover the account.

	    CLOSED
	    // Account is permanently closed, 
	    // usually at user request or due to long inactivity.
	    
}
