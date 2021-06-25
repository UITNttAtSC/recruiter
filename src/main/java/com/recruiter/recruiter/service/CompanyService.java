package com.recruiter.recruiter.service;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.domain.Payment;

public interface CompanyService {

    Company save(Company company);
    
    Company findByCompanyName(String companyName);
    
    void updateJobPost(JobPost jobPost, Company company, Payment payment);
}
