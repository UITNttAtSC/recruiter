package com.recruiter.recruiter.service;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.domain.Payment;

public interface PaymentService {

    Payment findByCompany(Company company);
}
