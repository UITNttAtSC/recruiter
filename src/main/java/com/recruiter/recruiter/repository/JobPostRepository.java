package com.recruiter.recruiter.repository;

import java.util.List;

import com.recruiter.recruiter.domain.JobPost;
import org.springframework.data.repository.CrudRepository;

public interface JobPostRepository extends CrudRepository<JobPost, Long> {
    List<JobPost> findFirst5ByStatusOrderByUpdatedAtDesc(boolean status);

    List<JobPost> findAllByStatusOrderByUpdatedAtDesc(boolean status);
    
    List<JobPost> findByJobCategory(String jobCategory);
}
