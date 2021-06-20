package com.recruiter.recruiter.repository;

import com.recruiter.recruiter.domain.JobPost;
import org.springframework.data.repository.CrudRepository;

public interface JobPostRepository extends CrudRepository<JobPost, Long> {
    
}
