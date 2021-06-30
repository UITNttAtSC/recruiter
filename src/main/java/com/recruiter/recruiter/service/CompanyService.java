package com.recruiter.recruiter.service;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.domain.Payment;
import com.recruiter.recruiter.domain.User;

public interface CompanyService {

    Company save(User user, Company company);

    Company findByUser(User user);
  
    Company findByCompanyName(String companyName);
    
    void updateJobPost(JobPost jobPost, Company company, Payment payment);
}
