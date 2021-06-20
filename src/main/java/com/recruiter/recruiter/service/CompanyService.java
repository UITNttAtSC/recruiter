package com.recruiter.recruiter.service;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.domain.JobPost;

public interface CompanyService {

    Company save(Company company);
    
    Company findByCompanyName(String companyName);
    
    void updateJobPost(JobPost jobPost, Company company);
}
