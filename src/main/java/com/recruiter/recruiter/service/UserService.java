package com.recruiter.recruiter.service;

import java.util.Set;


import com.recruiter.recruiter.domain.User;

public interface UserService {

	
	void createPasswordResetTokenForUser(final User user,final String token);
	
	User findByEmail(String email);
	
	User findByUsername(String username);
	
	User findById(Long id);
	
	User save(User user);
	
	void setUserDefaultPayment(Long userPaymentId,User user);
	
	void setUserDefaultShipping(Long userShippingId,User user);

}
