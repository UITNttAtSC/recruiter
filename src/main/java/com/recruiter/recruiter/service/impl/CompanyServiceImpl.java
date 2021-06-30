package com.recruiter.recruiter.service.impl;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.domain.Payment;
import com.recruiter.recruiter.domain.User;
import com.recruiter.recruiter.repository.CompanyRepository;
import com.recruiter.recruiter.repository.JobPostRepository;
import com.recruiter.recruiter.repository.UserRepository;
import com.recruiter.recruiter.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobPostRepository jobPostRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Company save(User user, Company company) {
        company.setUser(user);
        userRepository.save(user);
        return companyRepository.save(company);
    }

    @Override
    public void updateJobPost(JobPost jobPost, Company company, Payment payment) {
        jobPost.setCompany(company);
        jobPost.setPayment(payment);
        company.getPost().add(jobPost);
        jobPostRepository.save(jobPost);
        // save(company);
    }

	@Override
	public Company findByUser(User user) {
		return companyRepository.findByUser(user);
	}
    @Override
    public Company findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }

    
}
