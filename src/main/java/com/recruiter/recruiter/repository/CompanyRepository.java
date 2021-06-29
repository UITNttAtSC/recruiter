package com.recruiter.recruiter.repository;

import com.recruiter.recruiter.domain.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    
    Company findByCompanyName(String companyName);

    Company findByUser_Id(Long userId);
}
