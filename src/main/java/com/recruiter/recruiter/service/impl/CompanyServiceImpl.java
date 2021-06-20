package com.recruiter.recruiter.service.impl;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.repository.CompanyRepository;
import com.recruiter.recruiter.repository.JobPostRepository;
import com.recruiter.recruiter.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobPostRepository postRepository;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void updateJobPost(JobPost jobPost, Company company) {
        jobPost.setCompany(company);
        company.getPost().add(jobPost);
        postRepository.save(jobPost);
        save(company);
    }

    @Override
    public Company findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }
    
}
