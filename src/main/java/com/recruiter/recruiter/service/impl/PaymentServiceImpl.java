package com.recruiter.recruiter.service.impl;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.domain.Payment;
import com.recruiter.recruiter.repository.PaymentRepository;
import com.recruiter.recruiter.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment findByCompany(Company company) {
        return paymentRepository.findByCompany(company);
    }
    
}
